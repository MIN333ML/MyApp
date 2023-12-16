package com.example.demo;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.dao.CourseRepository;
import com.example.demo.dao.CourseService;
import com.example.demo.dao.UserService;
import com.example.demo.model.Userbean;

@SpringBootTest
@AutoConfigureMockMvc
public class TestCourseController {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    CourseService courseservice;
    @MockBean
    CourseRepository courserepo;
    @MockBean
    UserService userservice;

    @Test
    public void testCourseRegisterView() throws Exception {
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("role", "Admin");
        mockMvc.perform(get("/course").session(session))
                .andExpect(status().isOk())
                .andExpect(view().name("BUD003"));
    }

    @Test
    public void tetCourseRegisterPost() throws Exception {
        String courseid = "cou001";
        mockMvc.perform(post("/course3")
                .param("courseid1", courseid))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/studentseartch"));
    }
}
