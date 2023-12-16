package com.example.demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.example.demo.dao.CourseRepository;
import com.example.demo.dao.CourseService;
import com.example.demo.model.Studentbeean;
import com.example.demo.model.coursebean;

@SpringBootTest
public class TestCourseService {
    @Mock
    CourseRepository courserepo;
    @InjectMocks
    CourseService courseservice;

    @Test
    public void testgetAllCourseTest() {
        List<coursebean> list = new ArrayList<coursebean>();
        List<Studentbeean> stulist = new ArrayList<Studentbeean>();
        coursebean bean = new coursebean();
        bean.setCourseid("1");
        bean.setCoursename("PFC");
        bean.setStudentList(stulist);
        coursebean bean1 = new coursebean();
        bean1.setCourseid("2");
        bean1.setCoursename("JAVA");
        bean1.setStudentList(stulist);
        list.add(bean);
        list.add(bean1);
        when(courserepo.findAll()).thenReturn(list);
        List<coursebean> courselist = courseservice.getAllCourses();
        verify(courserepo, times(1)).findAll();
        assertEquals(2, courselist.size());
    }

    @Test
    public void saveTest() {
        coursebean bean = new coursebean();
        List<Studentbeean> list = new ArrayList<Studentbeean>();
        bean.setCourseid("1");
        bean.setCoursename("PFC");
        bean.setStudentList(list);
        courseservice.save(bean);
        verify(courserepo, times(1)).save(bean);
    }

    @Test
    public void testfindcourseidsBycoursename() {
        String name = "PFC";
        List<String> courseIds = new ArrayList<>();
        courseIds.add(name);
        when(courserepo.findCourseIdsByCoursename(name)).thenReturn(courseIds);
        List<String> foundUser = courseservice.findCourseIdsByCoursename(name);
        assertEquals(1, foundUser.size());
        assertEquals(name, foundUser.get(0));
    }

    @Test
    public void testGetCourseById() {
        List<Studentbeean> list = new ArrayList<>();
        coursebean bean = new coursebean();
        bean.setCourseid("1");
        bean.setCoursename("PFC");
        bean.setStudentList(list);
        when(courserepo.findById("1")).thenReturn(Optional.of(bean));
        coursebean foundUser = courseservice.getCourseById("1");
        assertEquals("1", foundUser.getCourseid());
        assertEquals("PFC", foundUser.getCoursename());
        assertEquals(list, foundUser.getStudentList());
    }

    @Test
    public void testGenerateStudentId() {
        when(courserepo.count()).thenReturn(5L);
        String generateStudentId = courseservice.generateCourseId();
        assertEquals("CUS006", generateStudentId);
        when(courserepo.count()).thenReturn(50L);
        String generateStudentId1 = courseservice.generateCourseId();
        assertEquals("CUS051", generateStudentId1);
        when(courserepo.count()).thenReturn(120L);
        String generateStudentId2 = courseservice.generateCourseId();
        assertEquals("CUS121", generateStudentId2);
        when(courserepo.count()).thenReturn(10L);
        String generateStudentId3 = courseservice.generateCourseId();
        assertEquals("CUS011", generateStudentId3);
        when(courserepo.count()).thenReturn(100L);
        String generateStudentId4 = courseservice.generateCourseId();
        assertEquals("CUS101", generateStudentId4);
    }
}

