package com.example.demo.controller;

import java.util.List;
import java.io.OutputStream;

import java.util.Map;
import java.util.Optional;
import java.util.HashMap;
import java.io.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.web.multipart.MultipartFile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.dao.CourseService;
import com.example.demo.dao.StudentService;
import com.example.demo.dao.UserService;
import com.example.demo.dto.StudentRequestDTO;
import com.example.demo.model.*;

@Controller
public class StudentController {
    @Autowired
    private UserService userservice;
    @Autowired
    private StudentService studentservice;
    @Autowired
    private CourseService courseservice;
    //git hub the project
    //
    @GetMapping(value = "/addstudent")
    public ModelAndView register6(HttpSession request, ModelMap model) {
        List<coursebean> list = courseservice.getAllCourses();
        request.setAttribute("course", list);
        List<Userbean> list2 = userservice.getAllUsers();
        model.addAttribute("list", list2);
        model.addAttribute("att", list);
        String st_id = studentservice.generateStudentId();
        model.addAttribute("studentid", st_id);

        return new ModelAndView("STU001", "stud", new Studentbeean());
    }

    @PostMapping(value = "/studentregister4")
    public String register4(@ModelAttribute("stud") StudentRequestDTO dto, @RequestParam("studentid1") String studentid, HttpSession session, Model model, HttpServletRequest request, @RequestParam("attend") String attend) {
        Studentbeean bean = new Studentbeean();
        bean.setStudentid(studentid);
        bean.setName(dto.getName());
        bean.setDob(dto.getDob());
        bean.setGender(dto.getGender());
        bean.setPhone(dto.getPhone());
        bean.setEducation(dto.getEducation());
        bean.setAttend(attend);
        String str[] = bean.getAttend().split(",");
        List<String> courseNames = Arrays.asList(str);
        System.out.println(courseNames);
        List<String> courseIds = new ArrayList<>();
        for (String courseName : courseNames) {
            List<String> ids = courseservice.findCourseIdsByCoursename(courseName);
            courseIds.addAll(ids);
        }
        System.out.println(courseIds);
        List<coursebean> courseBeans = new ArrayList<>();
        for (String courseId : courseIds) {
            coursebean course = courseservice.getCourseById(courseId);
            //    if (course != null) {
            courseBeans.add(course);
            //    }
        }
        System.out.println(courseBeans);
        bean.setCourseList(courseBeans);
        studentservice.save(bean);
        session.setAttribute("id", bean.getStudentid());
        System.out.println("success");
        return "redirect:/welcome1";
    }

    @GetMapping(value = "/studentseartch")
    public String displayview(ModelMap model, RedirectAttributes redirect) {
        List<Studentbeean> list = studentservice.getAllStudents();
        model.addAttribute("list", list);
        List<Userbean> list1 = userservice.getAllUsers();
        model.addAttribute("userlist", list1);
        return "STU003";
    }

    @GetMapping(value = "/studentseartch1")
    public String displayview1(ModelMap model, RedirectAttributes redirect) {
        List<Userbean> list1 = userservice.getAllUsers();
        List<Studentbeean> list = studentservice.getAllStudents();
        model.addAttribute("list", list);
        model.addAttribute("list1", list1);
        return "STU003-01";
    }

    @GetMapping(value = "/update")
    public String update3(@RequestParam("id") String studentid, Model m, RedirectAttributes redirect, HttpSession session) {
        List<coursebean> cres = courseservice.getAllCourses();
        List<Userbean> list333 = userservice.getAllUsers();
        m.addAttribute("userlist", list333);
        Studentbeean bean = studentservice.getStudentByStudentId(studentid);
        session.setAttribute("id", bean.getStudentid());
        session.setAttribute("st", bean.getAttend());
        m.addAttribute("std", bean);
        System.out.println("successful1");
        List<Studentbeean> list = studentservice.getAllStudents();
        m.addAttribute("list", list);
        m.addAttribute("course", cres);
        return "studentupdate";
    }

    @GetMapping(value = "/update1")
    public String update4(@RequestParam("id") String studentid, Model m, RedirectAttributes redirect, HttpSession session) {
        List<coursebean> cres = courseservice.getAllCourses();
        Studentbeean bean = studentservice.getStudentByStudentId(studentid);
        session.setAttribute("id", bean.getStudentid());
        String str[] = bean.getAttend().split(",");
        List<String> sl = new ArrayList<String>();
        sl = Arrays.asList(str);
        m.addAttribute("std1", bean);
        System.out.println("successful1");
        List<Userbean> list1 = userservice.getAllUsers();
        List<Studentbeean> list = studentservice.getAllStudents();
        m.addAttribute("list1", list1);
        m.addAttribute("list", list);
        m.addAttribute("courses", cres);
        m.addAttribute("sl", sl);

        return "studentupdate2";
    }

    @GetMapping(value = "/deletestudent")
    public String deletestudent(@RequestParam("id") String studentid, ModelMap model) {


        studentservice.delete(studentid);

        System.out.println("successful");
        return "redirect:/studentseartch1";
    }

    @PostMapping(value = "/imageupload")
    public String profileupload(@ModelAttribute("std1") StudentRequestDTO dto, @RequestParam("studentid") String studentid) {
        MultipartFile file = dto.getImage();
        Studentbeean bean = new Studentbeean();
        String imageFileName = file.getOriginalFilename();
        String uploadpath = "C:\\Users\\A C E R\\Desktop\\web java\\codetest4\\src\\main\\resources\\static\\image\\" + imageFileName;
        bean.setImage(imageFileName);
        bean.setStudentid(studentid);
        System.out.println(imageFileName);
        studentservice.uploadImage(imageFileName, studentid);
        try (OutputStream os = new FileOutputStream(uploadpath);
             InputStream is = file.getInputStream()) {
            byte[] data = new byte[is.available()];
            is.read(data);
            os.write(data);
            studentservice.uploadImage(imageFileName, studentid);
            System.out.println("File uploaded successfully");
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("success");
        return "redirect:/studentseartch1";


    }

    @PostMapping(value = "/studentup")
    public String update(@ModelAttribute("std") @Validated Studentbeean bean, BindingResult bs, ModelMap map, @RequestParam("studentid") String studentid) {
        return "redirect:/studentseartch";

    }

    @PostMapping(value = "/studentup1")
    public String update1(@ModelAttribute("std1") StudentRequestDTO dto, BindingResult bs, ModelMap map, @RequestParam("studentid") String studentid, @RequestParam("attend") String attend) {
        Studentbeean bean = new Studentbeean();
        bean.setStudentid(dto.getStudentid());
        bean.setName(dto.getName());
        bean.setDob(dto.getDob());
        bean.setGender(dto.getGender());
        bean.setPhone(dto.getPhone());
        bean.setEducation(dto.getEducation());
        bean.setAttend(dto.getAttend());
        System.out.println(bean.getAttend());
        String str[] = bean.getAttend().split(",");
        List<String> courseNames = Arrays.asList(str);
        System.out.println(courseNames);
        List<String> courseIds = new ArrayList<>();
        for (String courseName : courseNames) {
            List<String> ids = courseservice.findCourseIdsByCoursename(courseName);
            courseIds.addAll(ids);
        }
        System.out.println(courseIds);
        List<coursebean> courseBeans = new ArrayList<>();
        for (String courseId : courseIds) {
            coursebean course = courseservice.getCourseById(courseId);
//		    if (course != null) {
            courseBeans.add(course);
//		    }
        }
        bean.setCourseList(courseBeans);
        System.out.println(courseBeans);
//		     studentservice.updateCourseList(bean.getCourseList(), studentid);
        studentservice.updateStudent(bean.getName(), bean.getDob(), bean.getGender(), bean.getPhone(), bean.getEducation(), bean.getAttend(), studentid);
        System.out.println("successful");
        return "redirect:/studentseartch1";

    }

    @GetMapping(value = "/search3")
    public String SearchByname1(@RequestParam("searchstudent") String name, Model model) {
        StudentRequestDTO dto = new StudentRequestDTO();
        dto.setName(name);
        List<Studentbeean> searchResults = studentservice.getStudentsByName(name);
        List<Userbean> list1 = userservice.getAllUsers();
        model.addAttribute("list1", list1);
        model.addAttribute("list22", searchResults);
        return "STU003-01";
    }

    @GetMapping(value = "/search4")
    public String SearchByname(@RequestParam("searchstudent") String name, Model model) {
        StudentRequestDTO dto = new StudentRequestDTO();
        dto.setName(name);
        List<Studentbeean> searchResults = studentservice.getStudentsByName(name);
        model.addAttribute("list22", searchResults);
        return "STU003";
    }


}
