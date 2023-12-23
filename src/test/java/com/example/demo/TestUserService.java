package com.example.demo;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.dao.UserRepository;
import com.example.demo.dao.UserService;
import com.example.demo.model.Userbean;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TestUserService {
    @Mock
    UserRepository userrepo;
    @InjectMocks
    UserService userservice;

    @Test
    public void testGetUserByEmailAndPassword() {
        Userbean user = new Userbean();
        user.setEmail("test@gmail.com");
        user.setPassword("password123");

        when(userrepo.findByEmailAndPassword("test@gmail.com", "password123")).thenReturn(user);
        Userbean foundUser = userservice.getuserbyEmailAndPassword("test@gmail.com", "password123");
        assertEquals("test@gmail.com", foundUser.getEmail());
        assertEquals("password123", foundUser.getPassword());
    }

    @Test
    public void saveTest() {
        Userbean user = new Userbean();
        user.setUserid("1");
        user.setName("MIN MYAT THU");
        user.setEmail("test@example.com");
        user.setPassword("pwd");
        user.setRole("test");
        userservice.save(user);
        verify(userrepo, times(1)).save(user);
    }

    @Test
    public void testupdateuser() {
        when(userrepo.updateNameAndEmailAndRoleByUserId("John", "john@example.com", "User", "123")).thenReturn(1);
        int updateResult = userservice.updateuser("John", "john@example.com", "User", "123");
        verify(userrepo, times(1))
                .updateNameAndEmailAndRoleByUserId("John", "john@example.com", "User", "123");
        assertEquals(1, updateResult);
    }

    @Test
    public void testupdatePassword() {
        when(userrepo.updatePasswordByUserId("333", "123")).thenReturn(1);
        int updatePwdResult = userservice.updatePassword("333", "123");
        verify(userrepo, times(1))
                .updatePasswordByUserId("333", "123");
        assertEquals(1, updatePwdResult);

    }

    @Test
    public void testgetAllUserTest() {
        List<Userbean> list = new ArrayList<Userbean>();
        Userbean bean = new Userbean();
        bean.setUserid("1");
        bean.setName("1");
        bean.setEmail("1@example.com");
        bean.setPassword("111");
        bean.setRole("User");
        Userbean bean1 = new Userbean();
        bean1.setUserid("2");
        bean1.setName("2");
        bean1.setEmail("2@example.com");
        bean1.setPassword("111");
        bean1.setRole("Admin");
        list.add(bean1);
        list.add(bean);
        when(userrepo.findAll()).thenReturn(list);
        List<Userbean> userlist = userservice.getAllUsers();
        verify(userrepo, times(1)).findAll();
        assertEquals(2, userlist.size());

    }

    @Test
    public void testGetUserPasswordByEmail() {
//        Userbean expectedUser = new Userbean();
//        expectedUser.setEmail("test@example.com");
//        expectedUser.setPassword("232");
        String password="232";
        when(userrepo.findPasswordByEmail("test@example.com")).thenReturn(password);
        String foundUser = userservice.getuserPasswordbyEmail("test@example.com");
        assertEquals("test@example.com", "test@example.com");
        assertEquals("232", foundUser);
    }

    @Test
    public void testGetUserPasswordByUserId() {
        Userbean expectedUser = new Userbean();
        expectedUser.setUserid("1");
        expectedUser.setPassword("232");
        String password="233";
        when(userrepo.findPasswordByUserid("1")).thenReturn(password);
        String password1 = userservice.getuserPasswordbyUserId("1");
        assertEquals("1", "1");
        assertEquals("232", password1);
    }

    @Test
    public void testGetUserByUserId() {
        Userbean user = new Userbean();
        user.setUserid("123");
        user.setEmail("test@example.com");
        when(userrepo.findById("123")).thenReturn(Optional.of(user));
        Optional<Userbean> foundUser = userservice.getUserByUserId("123");
        assertEquals("123", foundUser.get().getUserid());
        assertEquals("test@example.com", foundUser.get().getEmail());
    }

    @Test
    public void testGetUserByUserId1() {
        Userbean user = new Userbean();
        user.setUserid("123");
        user.setEmail("test@example.com");
        when(userrepo.findByUserid("123")).thenReturn(user);
        Userbean foundUser = userservice.getUsersByUserId("123");
        assertEquals("123", foundUser.getUserid());
        assertEquals("test@example.com", foundUser.getEmail());
    }

    @Test
    public void testGetUserEmailByEmail() {
        Userbean user = new Userbean();
        user.setEmail("test@example.com");
        when(userrepo.findByEmail("test@example.com")).thenReturn(user);
        Userbean foundUser = userservice.getUserEmailByEmail("test@example.com");
        verify(userrepo).findByEmail("test@example.com");

        assertEquals("test@example.com", foundUser.getEmail());

    }

    @Test
    public void testGenerateUserId() {
        when(userrepo.count()).thenReturn(5L);
        String generateUserId = userservice.generateUserId();
        assertEquals("USR006", generateUserId);

        when(userrepo.count()).thenReturn(50L);
        String generatedUserId2 = userservice.generateUserId();
        assertEquals("USR051", generatedUserId2);

        when(userrepo.count()).thenReturn(120L);
        String generatedUserId3 = userservice.generateUserId();
        assertEquals("USR121", generatedUserId3);
        when(userrepo.count()).thenReturn(10L); // Simulating a count of 10
        String generatedUserId4 = userservice.generateUserId();
        assertEquals("USR011", generatedUserId4);

        // Test case 5: Edge case when count is exactly 100
        when(userrepo.count()).thenReturn(100L); // Simulating a count of 100
        String generatedUserId5 = userservice.generateUserId();
        assertEquals("USR101", generatedUserId5);

    }

    @Test
    public void testgetUserByname() {
        List<Userbean> userList = new ArrayList<>();
        Userbean user = new Userbean();
        user.setName("Min Myat Thu");
        userList.add(user);
        when(userrepo.findByName("Min Myat Thu")).thenReturn(userList);
        List<Userbean> foundUser = userservice.getUserByname("Min Myat Thu");
        assertEquals(1, foundUser.size());
        assertEquals("Min Myat Thu", foundUser.get(0).getName());

    }
//   @Test
//   public void testGetUserEmailByEmail() {
//       Userbean user = new Userbean();
//       user.setEmail("test@example.com");
//       when(userrepo.findByEmail("test@example.com")).thenReturn(user);
//       Userbean foundUser = userservice.getUserEmailByEmail("test@example.com");
//
//       // Verifying that UserRepository.findByEmail was called with the correct argument


//       // Asserting the result of the method
//       assertEquals("test@example.com", foundUser.getEmail());
//       // Add more assertions as needed for other user properties
//   }


}
