package com.example.demo.model;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotEmpty;

import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
@Entity
public class Studentbeean {
	@Id
	private String studentid;
	private String name;
	private String dob;
	private String  gender;
	private String phone;
	private String  education;
	private String  attend;
	private String  image;
	@ManyToMany(cascade = CascadeType.MERGE)
	@JoinTable(name="stud_course",
	joinColumns = @JoinColumn(name="studentid"),
	inverseJoinColumns = @JoinColumn(name="courseid"))
	private List<coursebean> courseList;
			
	public List<coursebean> getCourseList() {
		return courseList;
	}
	public void setCourseList(List<coursebean> courseList) {
		this.courseList = courseList;
	}
	public Studentbeean() {
		// TODO Auto-generated constructor stub
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String 
	getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		this.education = education;
	}
	public String getAttend() {
		return attend;
	}
	public void setAttend(String attend) {
		this.attend = attend;
	}
	public String getStudentid() {
		return studentid;
	}
	public void setStudentid(String studentid) {
		this.studentid = studentid;
	}
//	public MultipartFile getImage() {
//		return image;
//	}
//	public void setImage(MultipartFile image) {
//		this.image = image;
//	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	
	
}
