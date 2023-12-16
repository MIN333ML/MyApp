package com.example.demo.controller;

import java.util.List;

import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.dao.CourseService;
import com.example.demo.dao.StudentService;
import com.example.demo.dao.UserService;
import com.example.demo.model.Studentbeean;
import com.example.demo.model.Userbean;
import com.example.demo.model.coursebean;

@Controller
public class CourseController {
    @Autowired
    private CourseService courseService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private UserService userservice;

    @GetMapping(value = "/course")
    public ModelAndView register1(ModelMap map) {
        List<Userbean> list = userservice.getAllUsers();
        map.addAttribute("list", list);
        System.out.println("successful4");
        String cu_id = courseService.generateCourseId();
        map.addAttribute("courseid", cu_id);
        return new ModelAndView("BUD003", "course", new coursebean());
    }

    @PostMapping(value = "/course3")
    public String course(@ModelAttribute("course") @Valid coursebean bean, @RequestParam("courseid1") String courseid, BindingResult bs, ModelMap map, RedirectAttributes redirect) {

        bean.setCourseid(courseid);
        courseService.save(bean);
        return "redirect:/studentseartch";
    }
}
