package com.example.demo.dao;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Userbean;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class UserService {
    @Autowired
    UserRepository userrepository;

    public Userbean getuserbyEmailAndPassword(String email, String password) {
        return userrepository.findByEmailAndPassword(email, password);
    }

    public Userbean save(Userbean bean) {
        userrepository.save(bean);
        return bean;
    }

    public int updateuser(String name, String email, String role, String userid) {
        return userrepository.updateNameAndEmailAndRoleByUserId(name, email, role, userid);
    }

    public int updatePassword(String password, String userid) {
        return userrepository.updatePasswordByUserId(password, userid);
    }

    //   public Userbean delete(String userid) {
//	   userrepository.deleteById(userid);
//	   return bean;
//   }
    public List<Userbean> getAllUsers() {
        List<Userbean> list = (List<Userbean>) userrepository.findAll();
        return list;
    }

    public String getuserPasswordbyEmail(String email) {
       String password= userrepository.findPasswordByEmail(email);
        return password;
    }

    public String getuserPasswordbyUserId(String userid) {
        String password= userrepository.findPasswordByUserid(userid);
        return password;
    }

    public Optional<Userbean> getUserByUserId(String userid) {
        return userrepository.findById(userid);
    }

    public Userbean getUsersByUserId(String userid) {
        return userrepository.findByUserid(userid);
    }

    public Userbean getUserEmailByEmail(String email) {
        return userrepository.findByEmail(email);
    }

    //   public  Optional<Userbean> getEmailByUserId(String userid){
//	   return userrepository.findEmailById(userid);
//   }
    public String generateUserId() {
        long count = userrepository.count() + 1;
        String userid;
        if (count < 10) {
            userid = "USR00" + count;
        } else if (count < 100) {
            userid = "USR0" + count;
        } else {
            userid = "USR" + count;
        }
        return userid;
    }

    public List<Userbean> getUserByname(String name) {
        List<Userbean> list = userrepository.findByName(name);
        return list;
    }

}
