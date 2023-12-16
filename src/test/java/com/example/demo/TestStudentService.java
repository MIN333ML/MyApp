package com.example.demo;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.dao.StudentRepository;
import com.example.demo.dao.StudentService;
import com.example.demo.model.Studentbeean;
import com.example.demo.model.coursebean;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TestStudentService {
 @Mock
 StudentRepository studentrepo;
 @InjectMocks
 StudentService studentservice;
 
 @Test
 public void testSave() {
	 Studentbeean bean= new Studentbeean();
	 List<coursebean> list= new ArrayList<coursebean>();
	 bean.setStudentid("1");
	 bean.setName("Min");
	 bean.setDob("33");
	 bean.setGender("male");
	 bean.setPhone("4444");
	 bean.setEducation("university");
	 bean.setAttend("PFC");
	 bean.setCourseList(list);
	 studentservice.save(bean);
	 verify(studentrepo,times(1)).save(bean);
 }
 @Test
 public void deleteTest() {
	 studentservice.delete("101");
	 verify(studentrepo,times(1)).deleteById("101");
 }
 @Test
 public void getAllStudents() {
	 Studentbeean bean= new Studentbeean();
	 Studentbeean bean1= new Studentbeean();
	 List<coursebean> list2= new ArrayList<coursebean>();
	 List<Studentbeean> list= new ArrayList<>();
	 bean.setStudentid("1");
	 bean.setName("Min");
	 bean.setDob("33");
	 bean.setGender("male");
	 bean.setPhone("4444");
	 bean.setEducation("university");
	 bean.setAttend("PFC");
	 bean.setCourseList(list2);
	 List<coursebean> list3= new ArrayList<coursebean>();
	 bean.setStudentid("1");
	 bean.setName("Min");
	 bean.setDob("33");
	 bean.setGender("male");
	 bean.setPhone("4444");
	 bean.setEducation("university");
	 bean.setAttend("PFC");
	 bean.setCourseList(list3);
	 list.add(bean);
	 list.add(bean1);
	 when(studentrepo.findAll()).thenReturn(list);
	 List<Studentbeean> studentlist=studentservice.getAllStudents();
	 verify(studentrepo, times(1)).findAll();
	 assertEquals(2,studentlist.size());
 }
 @Test
 public  void getStudentByStudentId() {
	 Studentbeean bean= new Studentbeean();
	 bean.setStudentid("333");
	 when(studentrepo.findByStudentid(bean.getStudentid())).thenReturn(bean);
	 Studentbeean bean1= studentservice.getStudentByStudentId(bean.getStudentid());
	 verify(studentrepo, times(1))
	 .findByStudentid(bean.getStudentid());
	 assertEquals(bean1,bean1);
 }
 @Test
 public void testinguploadImage() {
     String image = "applicant.img";
     String studentid = "33";
     
     when(studentrepo.updateImageByStudentid(image, studentid)).thenReturn(studentid);
     
     String uploadimage = studentservice.uploadImage(image, studentid);
     
     verify(studentrepo, times(1)).updateImageByStudentid(image, studentid);
     
     assertNotNull(uploadimage);
 }

 @Test
 public void testingupdateStudent() {
	 when(studentrepo.updateNameAndDobAndGenderAndPhoneAndEducationAndAttendByStudentid("Min Myat Thu", "11.12.2023", "Male", "09980064215", "university", "PFC", "STU001")).thenReturn(1);
	 int updateStudent= studentservice.updateStudent("Min Myat Thu", "26.8.2004", "Male", "09980064215", "university", "PFC", "STU001");
	 verify(studentrepo,times(1))
	 .updateNameAndDobAndGenderAndPhoneAndEducationAndAttendByStudentid("Min Myat Thu", "26.8.2004", "Male", "09980064215", "university", "PFC", "STU001");
	 assertEquals(0,updateStudent);
 }
 @Test
 public void tesinggetStudentsbyname() {
	 List<Studentbeean> studentlist= new ArrayList<>();
	 Studentbeean student1= new Studentbeean();
	 student1.setName("Min Myat Thu");
	 studentlist.add(student1);
	 when(studentrepo.findByName("Min Myat Thu")).thenReturn(studentlist);
	 List<Studentbeean> student= studentservice.getStudentsByName("Min Myat Thu");
	 assertEquals(1,student.size());
     assertEquals("Min Myat Thu", student.get(0).getName());
}
 @Test
 public void testgenerateStudentId() {
	   when(studentrepo.count()).thenReturn(5L);
	   String generateUserId = studentservice.generateStudentId();
	   assertEquals("STD006",generateUserId);
	   
	   when(studentrepo.count()).thenReturn(50L); 
       String generatedUserId2 = studentservice.generateStudentId();
       assertEquals("STD051", generatedUserId2);
       
       when(studentrepo.count()).thenReturn(120L); 
       String generatedUserId3 = studentservice.generateStudentId();
       assertEquals("STD121", generatedUserId3);
       when(studentrepo.count()).thenReturn(10L); 
       String generatedUserId4 = studentservice.generateStudentId();
       assertEquals("STD011", generatedUserId4);

       when(studentrepo.count()).thenReturn(100L); 
       String generatedUserId5 = studentservice.generateStudentId();
       assertEquals("STD101", generatedUserId5);
 }
 
}
