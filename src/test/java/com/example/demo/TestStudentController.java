package com.example.demo;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dao.CourseService;
import com.example.demo.dao.StudentRepository;
import com.example.demo.dao.StudentService;
import com.example.demo.dao.UserService;
import com.example.demo.dto.StudentRequestDTO;
import com.example.demo.model.Studentbeean;
import com.example.demo.model.Userbean;
import com.example.demo.model.coursebean;

@SpringBootTest
@AutoConfigureMockMvc
public class TestStudentController {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    StudentService studentservice;
    @MockBean
    StudentRepository sturepo;
    //StudentRegister view method
    @MockBean
    CourseService courseservice;
    @MockBean
    UserService userservice;
    
    
    // GIT HUB TESTING FOR PROJECT IN INTELLIJ IDEAffsdf fdfadsfasfsaffafsaf
    @Test
    public void teststudentaddView() throws Exception {
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("role", "User");
        mockMvc.perform(get("/addstudent").session(session))
                .andExpect(status().isOk())
                .andExpect(view().name("STU001"));
    }

    @Test
    public void teststudendaddPost() throws Exception {
        String studentid = "STU001";
        String attend = "COU001";
        coursebean course = new coursebean();
        course.setCoursename("attend");

        List<coursebean> courselist = new ArrayList<>();
        courselist.add(course);
        StudentRequestDTO dto = new StudentRequestDTO();
        Studentbeean bean = new Studentbeean();
        bean.setStudentid(studentid);
        bean.setName(dto.getName());
        bean.setDob(dto.getDob());
        bean.setGender(dto.getGender());
        bean.setPhone(dto.getPhone());
        bean.setEducation(dto.getEducation());
        bean.setAttend(dto.getAttend());
        bean.setCourseList(courselist);
//		
        when(courseservice.findCourseIdsByCoursename(attend)).thenReturn(Arrays.asList("courseId1", "courseId2"));
        when(courseservice.getCourseById(studentid)).thenReturn(course);
        when(studentservice.save(bean)).thenReturn(bean);
        mockMvc.perform(post("/studentregister4")
                .param("studentid1", studentid)
                .param("attend", attend))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/welcome1"));

    }

    //testing studentsearch in admin
    @Test
    public void teststudentsearch() throws Exception {
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("role", "Admin");
        mockMvc.perform(get("/studentseartch").session(session))
                .andExpect(status().isOk())
                .andExpect(view().name("STU003"));
    }

    //testing studentsearch in User
    @Test
    public void teststudentsearch1() throws Exception {
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("role", "User");
        mockMvc.perform(get("/studentseartch1").session(session))
                .andExpect(status().isOk())
                .andExpect(view().name("STU003-01"));
    }

    //testing studentupdate in admin
    @Test
    public void teststudentupdate() throws Exception {
        List<coursebean> course = new ArrayList<>();
        List<Userbean> user = new ArrayList<>();
        List<Studentbeean> student = new ArrayList<>();
        MockHttpSession session = new MockHttpSession();
        String studentid = "stu001";
        when(courseservice.getAllCourses()).thenReturn(course);
        when(userservice.getAllUsers()).thenReturn(user);
        when(studentservice.getAllStudents()).thenReturn(student);
        when(studentservice.getStudentByStudentId(studentid)).thenReturn(new Studentbeean());
        session.setAttribute("role", "Admin");
        mockMvc.perform(get("/update").param("id", studentid).session(session))
                .andExpect(status().isOk())
                .andExpect(view().name("studentupdate"));
    }

    //Testing studentupdate in user
    @Test
    public void teststudentupdate1() throws Exception {
        List<coursebean> course = new ArrayList<>();
        List<Userbean> user = new ArrayList<>();
        List<Studentbeean> student = new ArrayList<>();
        Studentbeean bean = new Studentbeean();
        bean.setAttend("PFC");
        MockHttpSession session = new MockHttpSession();
        String studentid = "stu001";
        when(courseservice.getAllCourses()).thenReturn(course);
        when(userservice.getAllUsers()).thenReturn(user);
        when(studentservice.getAllStudents()).thenReturn(student);
        when(studentservice.getStudentByStudentId(studentid)).thenReturn(bean);
        session.setAttribute("role", "User");
        mockMvc.perform(get("/update1").param("id", studentid).session(session))
                .andExpect(status().isOk())
                .andExpect(view().name("studentupdate2"));
    }

    @Test
    public void testdeleteStudent() throws Exception {
        String studentid = "124";
        when(studentservice.delete(studentid)).thenReturn(studentid);
        mockMvc.perform(get("/deletestudent").param("id", studentid))
                .andExpect(status().is(302))
                .andExpect(redirectedUrl("/studentseartch1"));
    }

    //Testing image upload method
    @Test
    public void testimageuplload() throws Exception {
        MockHttpSession session = new MockHttpSession();
        MockMultipartFile file = new MockMultipartFile("image", "filename.txt", "text/plain", "fileContent".getBytes());
        String studentid = "123";
        String imageFileName = file.getOriginalFilename();
        Studentbeean bean = new Studentbeean();
        bean.setImage(imageFileName);
        bean.setStudentid(studentid);
        session.setAttribute("role", "User");
        mockMvc.perform(MockMvcRequestBuilders.multipart("/imageupload")
                .file(file)
                .param("studentid", studentid).session(session))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/studentseartch1"));
    }

    @Test
    public void testStudentUpdateAdminViewPost() throws Exception {
        String studentid = "123";
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("role", "Admin");
        mockMvc.perform(post("/studentup").param("studentid", studentid))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/studentseartch"));
    }

    //Testing StudentUpdate on user
    @Test
    public void teststudendUpdatePost() throws Exception {
        String studentid = "STU001";
        String attend = "COU001";
        StudentRequestDTO dto = new StudentRequestDTO();
        coursebean course = new coursebean();
        course.setCoursename("attend");
        List<coursebean> courselist = new ArrayList<>();
        courselist.add(course);
        Studentbeean bean = new Studentbeean();
        bean.setStudentid(studentid);
        bean.setName(dto.getName());
        bean.setDob(dto.getDob());
        bean.setGender(dto.getGender());
        bean.setPhone(dto.getPhone());
        bean.setEducation(dto.getEducation());
        bean.setAttend(dto.getAttend());
        bean.setCourseList(courselist);
        when(courseservice.findCourseIdsByCoursename(attend)).thenReturn(Arrays.asList("courseId1", "courseId2"));
        when(courseservice.getCourseById(studentid)).thenReturn(course);
        when(studentservice.save(bean)).thenReturn(bean);
        mockMvc.perform(post("/studentup1")
                .param("studentid", studentid)
                .param("attend", attend))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/studentseartch1"));

    }

    //testing search name on user
    @Test
    public void testsearchbyname() throws Exception {
        String name = "Min";

        MockHttpSession session = new MockHttpSession();
        session.setAttribute("role", "User");
        mockMvc.perform(get("/search3").param("searchstudent", name).session(session))
                .andExpect(status().is(200))
                .andExpect(view().name("STU003-01"));
    }
    //testing search name on admin

    @Test
    public void testsearchbyname1() throws Exception {
        String name = "Min";

        MockHttpSession session = new MockHttpSession();
        session.setAttribute("role", "Admin");
        mockMvc.perform(get("/search4").param("searchstudent", name).session(session))
                .andExpect(status().is(200))
                .andExpect(view().name("STU003"));
    }
}
