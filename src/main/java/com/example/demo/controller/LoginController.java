package com.example.demo.controller;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.model.*;
import com.example.demo.dao.*;

@Controller
public class LoginController {
    @Autowired
    private UserService userservice;
    @Autowired
    private StudentService studentservice;

    //Login
    @GetMapping(value = "/")
    public ModelAndView login1() {
        return new ModelAndView("LGN001", "LGN", new Userbean());
    }

    @PostMapping(value = "/login")
    public String login(@ModelAttribute("LGN") Userbean bean, @RequestParam("email") String email, @RequestParam("password") String password, BindingResult bs, ModelMap map, HttpSession session, RedirectAttributes redirect) {
        String passwordCheck = userservice.getuserPasswordbyEmail(email);
        System.out.println(passwordCheck);
        Userbean bean1 = userservice.getuserbyEmailAndPassword(email, password);
        
        if (bean1 != null && bean1.getRole().equals("Admin")) {
            session.setAttribute("id", bean.getUserid());
            System.out.println(bean1.getUserid());
            System.out.println(passwordCheck);
            session.setAttribute("userid", bean1.getUserid());
            session.setAttribute("name", bean1.getName());
            session.setAttribute("role", bean1.getRole());
            return "redirect:/welcome";
            // Invalid credentials, handle the case here
        } else if (bean1 != null && bean1.getRole().equals("User")) {
            System.out.println(passwordCheck);
            session.setAttribute("userid", bean1.getUserid());
            session.setAttribute("name", bean1.getName());
            session.setAttribute("role", bean1.getRole());
            return "redirect:/welcome1";
        } else if (passwordCheck != null && !passwordCheck.equals(password) && bean1 !=null) {
            System.out.println("passworderror");
            System.out.println(passwordCheck);
            map.addAttribute("pwdcorrect", "password incorrect!!!");
            return "LGN001";
        } else {
            System.out.println("emailerror");
            map.addAttribute("emailcorrect", "Email is not Exist!!");
            return "LGN001";
        }
    }

    // User Part

    @GetMapping(value = "/studentregister")
    public ModelAndView register(HttpSession session, ModelMap model) {
        String us_id = userservice.generateUserId();
        model.addAttribute("userid", us_id);

        return new ModelAndView("studentregister", "ppp", new Userbean());
    }

    @PostMapping(value = "/user212")
    public String user(@ModelAttribute("ppp") Userbean bean, @RequestParam("email") String email, @RequestParam("userid1") String userid, BindingResult bs, ModelMap map, RedirectAttributes redirect) {
        Userbean existingUser = userservice.getUserEmailByEmail(email);
        if (existingUser != null) {
            map.addAttribute("emailerror", "Email is already Register!!!");
            System.out.println("email error");
            return "studentregister";
        }
        bean.setUserid(userid);
        userservice.save(bean);
        return "redirect:/";
    }

    @GetMapping(value = "/welcome1")
    public String welcome12(ModelMap m) {
        List<Userbean> list = userservice.getAllUsers();
        m.addAttribute("list", list);
        return "MNU001-01";
    }

    @GetMapping(value = "/setupUpdatebook1")
    public String update2(@RequestParam("id") String userid, ModelMap m, HttpSession session) {
        List<Userbean> list = userservice.getAllUsers();
        m.addAttribute("list", list);
        session.setAttribute("id", userid);
        Userbean bean = userservice.getUsersByUserId(userid);
        m.addAttribute("upda1", bean);

        return "UserUpdate";
    }

    @PostMapping(value = "/updatebook1")
    public String register8(@ModelAttribute("upda1") Userbean bean, @RequestParam("userid") String userid, BindingResult bs, ModelMap map, HttpSession session) {
        System.out.println(userid);
        session.setAttribute("id", bean.getUserid());
        session.setAttribute("name", bean.getName());
        session.setAttribute("role", bean.getRole());
        System.out.println("successful");
        return "redirect:/welcome";


    }

    @GetMapping(value = "/newpwd")
    public ModelAndView changepwd(@RequestParam("id") String userid, ModelMap m) {
        List<Userbean> list = userservice.getAllUsers();
        m.addAttribute("list", list);
        return new ModelAndView("changepassword", "pwd", userservice.getUsersByUserId(userid));
    }

    @PostMapping(value = "/newpwd1")
    public String changepwd1(@ModelAttribute("pwd") Userbean bean, @RequestParam("password") String password, @RequestParam("newpassword1") String newpassword, @RequestParam("userid") String userid, ModelMap map, HttpSession session, RedirectAttributes redirect) {
        Userbean oldpwd = userservice.getuserPasswordbyUserId(userid);
        if (oldpwd != null && oldpwd.getPassword().equals(password)) {
            bean.setPassword(newpassword);
            userservice.updatePassword(bean.getPassword(), userid);

            session.setAttribute("id", userid);
            return "redirect:/welcome1";

        }
        redirect.addFlashAttribute("oldpwderor", "Old Password is InCorrect!!!");
        return "redirect:/newpwd?id=" + userid;
    }

    //    Admin Part
    @GetMapping(value = "/welcome")
    public ModelAndView welcome1(ModelMap map) {
        List<Userbean> list = userservice.getAllUsers();
        map.addAttribute("list", list);
        return new ModelAndView("MNU001", "welcome", new Userbean());
    }

    @GetMapping(value = "/setupUpdatebook")
    public ModelAndView update1(@RequestParam("id") String userid, ModelMap m, HttpSession session) {
        List<Userbean> list = userservice.getAllUsers();
        m.addAttribute("list", list);
        session.setAttribute("id", userid);
        return new ModelAndView("userupdate2", "upda", userservice.getUsersByUserId(userid));
    }

    @PostMapping(value = "/updatebook")
    public String register6(@ModelAttribute("upda") Userbean bean, @RequestParam("userid") String userid, @RequestParam("name") String name, @RequestParam("email") String email, @RequestParam("role") String role, BindingResult bs, ModelMap map, HttpSession session, RedirectAttributes redirect) {
//		if(bs.hasErrors()) {
//			return "UserUpdate";
//		}

        bean.setName(name);
        bean.setEmail(email);
        bean.setRole(role);
        System.out.println(userid);
        userservice.updateuser(bean.getName(), bean.getEmail(), bean.getRole(), userid);

        redirect.addFlashAttribute("updsucc", true);
        session.setAttribute("id", bean.getUserid());
        session.setAttribute("name", bean.getName());
        session.setAttribute("role", bean.getRole());
        System.out.println("successful");
        return "redirect:/welcome1";
    }

    @GetMapping(value = "/usermanage")
    public String displayView(String searchname, ModelMap model, Model map) {
        List<Userbean> list = userservice.getAllUsers();
        model.addAttribute("list", list);
        return "usermanagment";
    }

    @GetMapping(value = "/searchname")
    public String searchByAuthor(@RequestParam("searchname") String name, Model model) {
        List<Userbean> searchResults = userservice.getUserByname(name);
        model.addAttribute("list22", searchResults);
        return "usermanagment";
    }

    @GetMapping(value = "/adminnewpwd")
    public ModelAndView adminchangepwd(@RequestParam("id") String userid, ModelMap m) {
        List<Userbean> list = userservice.getAllUsers();
        m.addAttribute("list", list);
        return new ModelAndView("adminchangepwd", "pwd1", userservice.getUserByUserId(userid));
    }

    @PostMapping(value = "/adminnewpwd1")
    public String adminchangepwd1(@ModelAttribute("pwd1") Userbean bean, @RequestParam("password") String password, @RequestParam("newpassword1") String newpassword, @RequestParam("userid") String userid, ModelMap map, HttpSession session, RedirectAttributes redirect) {
        Userbean oldpwd = userservice.getuserPasswordbyUserId(userid);
        if (oldpwd != null && oldpwd.getPassword().equals(password)) {
            bean.setPassword(newpassword);
            userservice.updatePassword(bean.getPassword(), userid);

            session.setAttribute("id", userid);
            return "redirect:/welcome";

        }
        redirect.addFlashAttribute("oldpwderor", "Old Password is InCorrect!!!");
        return "redirect:/adminnewpwd?id=" + userid;
    }

}
