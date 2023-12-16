package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.coursebean;

public interface CourseRepository extends CrudRepository<coursebean, String> {
    @Query("SELECT cu.courseid FROM coursebean cu WHERE cu.coursename = ?1")
    List<String> findCourseIdsByCoursename(String coursename);


}
