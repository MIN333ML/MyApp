package com.example.demo.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.demo.model.Userbean;

public interface UserRepository extends CrudRepository<Userbean, String> {

    Userbean findByEmailAndPassword(String email, String password);

    List<Userbean> findByEmailContaining(String userid);

    Userbean findByEmail(String email);

    @Query("SELECT u.password FROM Userbean u WHERE u.email = ?1")
    String findPasswordByEmail(String email);

    @Query("SELECT u.password FROM Userbean u WHERE u.userid = ?1")
    String findPasswordByUserid(String userid);

    @Query("select u.userid FROM Userbean u WHERE u.userid =?1")
    Optional<Userbean> findEmailById(String userid);

    @Modifying
    @Query("update Userbean us set us.password = :newPassword where us.userid = :userId")
    int updatePasswordByUserId(String newPassword, String userId);

    @Modifying
    @Query("update Userbean us set us.name = :name, us.email = :email, us.role = :role where us.userid = :userid")
    int updateNameAndEmailAndRoleByUserId(String name, String email, String role, String userid);

    @Query("SELECT u FROM Userbean u WHERE u.userid= ?1")
    Userbean findByUserid(String userid);

    @Query("SELECT u FROM Userbean u WHERE  u.name= ?1")
    List<Userbean> findByName(String name);

}
