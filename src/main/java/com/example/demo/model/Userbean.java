package com.example.demo.model;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import javax.persistence.*;

@Entity
public class Userbean implements Serializable {
    @Id
    private String userid;
    private String name;
    private String password;
    private String email;
    private String role;

    public Userbean() {
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
//public  Userbean(String name,String password) {
//		super();
//		this.setName(name);
//		this.setPassword(password);
//	}
////	Constructor
//	public Userbean(String name,String email,String password, String comfirmpassword) {
//		super();
//		this.setName(name);
//		this.setEmail(email);
//		this.setPassword(password);
//		this.setComfirmpassword(comfirmpassword);;
//	}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}
