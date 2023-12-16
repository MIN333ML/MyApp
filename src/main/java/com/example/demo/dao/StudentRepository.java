package com.example.demo.dao;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Studentbeean;
import com.example.demo.model.coursebean;

public interface StudentRepository extends CrudRepository<Studentbeean, String> {

    @Query("SELECT s FROM Studentbeean s WHERE s.studentid = ?1")
    Studentbeean findByStudentid(String studentid);

    @Modifying
    @Query("update Studentbeean st set st.image = :image where st.studentid = :studentid")
    String updateImageByStudentid(String image, String studentid);

    @Modifying
    @Query("update Studentbeean st set st.name = :name,st.dob = :dob,st.gender = :gender , st.phone = :phone, st.education = :education, st.attend = :attend where st.studentid =:studentid")
    int updateNameAndDobAndGenderAndPhoneAndEducationAndAttendByStudentid(String name, String dob, String gender,
                                                                          String phone, String education, String attend, String studentid);

    List<Studentbeean> findByName(String name);

    void save(List<coursebean> courselist);

}
