package com.example.demo.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotEmpty;

@Entity
public class coursebean {
	@Id
     private String courseid;
	private  String coursename;
    @ManyToMany(mappedBy = "courseList")
    private List<Studentbeean> studentList;
    
    public String getCourseid() {
		return courseid;
	}

	public void setCourseid(String courseid) {
		this.courseid = courseid;
	}

	public List<Studentbeean> getStudentList() {
		return studentList;
	}
	public void setStudentList(List<Studentbeean> studentList) {
		this.studentList = studentList;
	}
	
    public coursebean(){}

  	public String getCoursename() {
		return coursename;
	}
	public void setCoursename(String coursename) {
		this.coursename = coursename;
	}

}
