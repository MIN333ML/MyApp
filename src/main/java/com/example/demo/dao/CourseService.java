package com.example.demo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.coursebean;

@Transactional
@Service
public class CourseService {
    @Autowired
    CourseRepository courseRepository;

    public List<coursebean> getAllCourses() {
        List<coursebean> list = (List<coursebean>) courseRepository.findAll();
        return list;
    }

    public coursebean save(coursebean courses) {
        courseRepository.save(courses);
        return courses;
    }

    public List<String> findCourseIdsByCoursename(String coursename) {
        return courseRepository.findCourseIdsByCoursename(coursename);
    }

    public coursebean getCourseById(String courseId) {
        return courseRepository.findById(courseId).orElse(null);
    }

    public String generateCourseId() {
        long count = courseRepository.count() + 1;
        String userid;
        if (count < 10) {
            userid = "CUS00" + count;
        } else if (count < 100) {
            userid = "CUS0" + count;
        } else {
            userid = "CUS" + count;
        }
        return userid;
    }
}
