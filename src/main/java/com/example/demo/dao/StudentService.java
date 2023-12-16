package com.example.demo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.Studentbeean;
import com.example.demo.model.coursebean;

@Transactional
@Service
public class StudentService {
    @Autowired
    StudentRepository studentrepository;

    public Studentbeean save(Studentbeean students) {
        studentrepository.save(students);
        return students;
    }

    //  public void updateCourseList(List<coursebean>courselist,String studentid) {
//	   studentrepository.updateCourseListByStudentid(courselist,studentid);
//  }
    public String delete(String studentid) {
        studentrepository.deleteById(studentid);
        return studentid;
    }

    public List<Studentbeean> getAllStudents() {
        List<Studentbeean> list = (List<Studentbeean>) studentrepository.findAll();
        return list;
    }

    public Studentbeean getStudentByStudentId(String studentid) {
        return studentrepository.findByStudentid(studentid);
    }

    public String uploadImage(String image, String studentid) {
        studentrepository.updateImageByStudentid(image, studentid);
        return studentid;
    }

    public int updateStudent(String name, String dob, String gender, String phone, String education, String attend, String studentid) {
        return studentrepository.updateNameAndDobAndGenderAndPhoneAndEducationAndAttendByStudentid(name, dob, gender, phone, education, attend, studentid);
    }

    public List<Studentbeean> getStudentsByName(String name) {
        List<Studentbeean> list = studentrepository.findByName(name);
        return list;
    }

    public String generateStudentId() {
        long count = studentrepository.count() + 1;

        String studentId;
        if (count < 10) {
            studentId = "STD00" + count;
        } else if (count < 100) {
            studentId = "STD0" + count;
        } else {
            studentId = "STD" + count;
        }
        return studentId;
    }

}


