package com.example.demo;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.ui.ModelMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.demo.dao.UserRepository;
import com.example.demo.dao.UserService;
import com.example.demo.model.Userbean;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc
public class TestUserController {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    UserService userService;
    @MockBean
    UserRepository userepo;

    // Test for loginView method
    @Test
    public void testLoginView() throws Exception {
        this.mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("LGN001"))
                .andExpect(model().attributeExists("LGN"));

    }
    // Test for adminLogin method
    @Test
    public void testValidAdminLogin() throws Exception {
        Userbean adminuser = new Userbean();
        adminuser.setEmail("admin@example.com");
        adminuser.setPassword("adminPass");
        adminuser.setRole("Admin");
        String passwordCheck = "1111";
        String email= "email";
        when(userService.getuserbyEmailAndPassword(adminuser.getEmail(), adminuser.getPassword())).thenReturn(adminuser);
        when(userService.getuserPasswordbyEmail(adminuser.getEmail())).thenReturn(passwordCheck);
        MvcResult result = mockMvc.perform(post("/login")
                .param("email", "admin@example.com")
                .param("password", "adminPass"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/welcome"))
                .andReturn();
        HttpSession session = result.getRequest().getSession();
        assertEquals(adminuser.getUserid(), session.getAttribute("userid"));
        assertEquals(adminuser.getName(), session.getAttribute("name"));
        assertEquals(adminuser.getRole(), session.getAttribute("role"));

        //AdminPassword Check
        when(userService.getuserbyEmailAndPassword(email, "incorrect Password")).thenReturn(null);
        this.mockMvc.perform(post("/login")
                .param("email", "admin@example.com")
                .param("password", "incorrectPassword"))
                .andExpect(status().isOk())
                .andExpect(view().name("LGN001"));


        //AdminEmail Check
        when(userService.getuserbyEmailAndPassword("Email is not Exist", passwordCheck)).thenReturn(null);
        this.mockMvc.perform(post("/login")
                .param("email", "EmailNotExist")
                .param("password", "adminPass"))
                .andExpect(status().isOk())
                .andExpect(view().name("LGN001"))
                .andExpect(model().attributeExists("emailcorrect"));
    }

    // Test for UserLogin method
    @Test
    public void testValidUserLogin() throws Exception {
        Userbean user = new Userbean();
        user.setEmail("user@example.com");
        user.setPassword("userPass");
        user.setRole("User");
        String passwordCheck="ttttt";
        when(userService.getuserbyEmailAndPassword(user.getEmail(), user.getPassword())).thenReturn(user);
        when(userService.getuserPasswordbyEmail(user.getEmail())).thenReturn(passwordCheck);
        MvcResult result = mockMvc.perform(post("/login")
                .param("email", "user@example.com")
                .param("password", "userPass"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/welcome1"))
                .andReturn();
        HttpSession session = result.getRequest().getSession();
        assertEquals(user.getUserid(), session.getAttribute("userid"));
        assertEquals(user.getName(), session.getAttribute("name"));
        assertEquals(user.getRole(), session.getAttribute("role"));
        //UserPassword Check
        when(userService.getuserbyEmailAndPassword(user.getEmail(), "incorrect Password")).thenReturn(null);
        this.mockMvc.perform(post("/login")
                .param("email", "user@example.com")
                .param("password", "incorrectPassword"))
                .andExpect(status().isOk())
                .andExpect(view().name("LGN001"));
        //UserEmail Check

        when(userService.getuserbyEmailAndPassword("Email is not Exist", passwordCheck)).thenReturn(null);
        this.mockMvc.perform(post("/login")
                .param("email", "EmailNotExist")
                .param("password", "userPass"))
                .andExpect(status().isOk())
                .andExpect(view().name("LGN001"))
                .andExpect(model().attributeExists("emailcorrect"));
    }
    //User StudentRegister method

    @Test
    public void testStudentRegister() throws Exception {
        String generatedUserId = "generatedUserId123";
        when(userService.generateUserId()).thenReturn(generatedUserId);
        this.mockMvc.perform(get("/studentregister"))
                .andExpect(status().isOk())
                .andExpect(view().name("studentregister"))
                .andExpect(model().attributeExists("userid"));

    }

    //User UserRegisterPost method
    @Test
    public void testuserrregisterpost() throws Exception {
        Userbean existingUser = new Userbean();
        existingUser.setEmail("existing@example.com");
        when(userService.getUserEmailByEmail(existingUser.getEmail())).thenReturn(existingUser);
        this.mockMvc.perform(post("/user212")
                .param("email", "existing@example.com")
                .param("userid1", "someUserId"))
                .andExpect(status().isOk())
                .andExpect(view().name("studentregister"))
                .andExpect(model().attributeExists("emailerror"));

        Userbean bean = new Userbean();
        bean.setEmail("new@gmail.com");
        bean.setUserid("someuserId");
        when(userService.save(bean)).thenReturn(bean);
        mockMvc.perform(post("/user212")
                .param("email", "new@example.com")
                .param("userid1", "someUserId"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }

    //User HomePage Method
    @Test
    public void testWelcomePage() throws Exception {
        List<Userbean> mockUserList = new ArrayList<>();

        when(userService.getAllUsers()).thenReturn(mockUserList);
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("role", "User");
        mockMvc.perform(get("/welcome1").session(session))
                .andExpect(status().isOk())
                .andExpect(view().name("MNU001-01"))
                .andExpect(model().attributeExists("list"))
                .andExpect(model().attribute("list", mockUserList));
    }

    //UserAdmin view method
    @Test
    public void testAdminUserViewPage() throws Exception {
        String userId = "userId123";
        Userbean mockUser = new Userbean();
        mockUser.setUserid(userId);
        List<Userbean> mockUserList = new ArrayList<>();
        mockUserList.add(mockUser);
        when(userService.getAllUsers()).thenReturn(mockUserList);
        when(userService.getUsersByUserId(userId)).thenReturn(mockUser);

        mockMvc.perform(get("/setupUpdatebook1").param("id", userId))
                .andExpect(status().isOk())
                .andExpect(view().name("UserUpdate"))
                .andExpect(model().attributeExists("list"))
                .andExpect(model().attributeExists("upda1"))
                .andExpect(model().attribute("upda1", mockUser));
    }

    //UserAdmin Post Method
    @Test
    public void testAdminUserViewPostPage() throws Exception {
        mockMvc.perform(post("/updatebook1")
                .param("userid", "3333"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/welcome"));
    }

    //UserChangePwd view method
    @Test
    public void userchangepwdViewPage() throws Exception {
        String userId = "userId123";
        Userbean mockUser = new Userbean();
        mockUser.setUserid(userId);
        mockUser.setRole("User");
        List<Userbean> mockUserList = new ArrayList<>();
        mockUserList.add(mockUser);
        when(userService.getAllUsers()).thenReturn(mockUserList);
        when(userService.getUsersByUserId(userId)).thenReturn(mockUser);
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("role", "User");
        mockMvc.perform(get("/newpwd").param("id", userId).session(session))
                .andExpect(status().isOk())
                .andExpect(view().name("changepassword"))
                .andExpect(model().attributeExists("list"))
                .andExpect(model().attributeExists("pwd"));

    }

    @Test
    public void testChangepwd1() throws Exception {
        String userId = "123";
        String password = "oldPassword";
        String newPassword = "newPassword";
        String incorrectPassword = "incorrectPassword";

        Userbean mockUser = new Userbean();
        mockUser.setPassword(password);
        // Test for  correct password
        when(userService.getuserPasswordbyUserId(userId)).thenReturn(mockUser);
        mockMvc.perform(post("/newpwd1")
                .param("password", password)
                .param("newpassword1", newPassword)
                .param("userid", userId))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/welcome1"));

        // Test for incorrect password
        mockMvc.perform(post("/newpwd1")
                .param("password", incorrectPassword)
                .param("newpassword1", newPassword)
                .param("userid", userId))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/newpwd?id=" + userId))
                .andExpect(flash().attributeExists("oldpwderor"));
    }

    //User Update View method
    @Test
    public void testUserUpdateViewPage() throws Exception {
        String userId = "userId123";
        Userbean mockUser = new Userbean();
        mockUser.setUserid(userId);
        List<Userbean> mockUserList = new ArrayList<>();
        mockUserList.add(mockUser);
        when(userService.getAllUsers()).thenReturn(mockUserList);
        when(userService.getUsersByUserId(userId)).thenReturn(mockUser);

        mockMvc.perform(get("/setupUpdatebook").param("id", userId))
                .andExpect(status().isOk())
                .andExpect(view().name("userupdate2"))
                .andExpect(model().attributeExists("list"))
                .andExpect(model().attributeExists("upda"))
                .andExpect(model().attribute("upda", mockUser));
    }

    //UserUpdate Post Method
    @Test
    public void testUserUpdatePostPage() throws Exception {
        String name = "Min Myat thu";
        String email = "test@example.com";
        String role = "User";
        String userid = "333";

        mockMvc.perform(post("/updatebook")
                .param("userid", userid)
                .param("name", name)
                .param("email", email)
                .param("role", role)
        )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/welcome1"));
    }

    //UserManagement method
    @Test
    public void testUserManagementView() throws Exception {
        List<Userbean> mockUserList = new ArrayList<>();
        when(userService.getAllUsers()).thenReturn(mockUserList);
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("role", "Admin");
        mockMvc.perform(get("/usermanage").session(session))
                .andExpect(model().attributeExists("list"))
                .andExpect(view().name("usermanagment"));

    }

    //Admin homepage method
    @Test
    public void testAdminWelcomePage() throws Exception {
        List<Userbean> mockUserList = new ArrayList<>();

        when(userService.getAllUsers()).thenReturn(mockUserList);
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("role", "Admin");
        mockMvc.perform(get("/welcome").session(session))
                .andExpect(status().isOk())
                .andExpect(view().name("MNU001"))
                .andExpect(model().attributeExists("list"));
    }

    @Test
    public void searchByAuthor() throws Exception {
        List<Userbean> UserList = new ArrayList<>();
        String name = "Min Myat Thu";
        when(userService.getUserByname(name)).thenReturn(UserList);
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("role", "Admin");
        mockMvc.perform(get("/searchname").param("searchname", name).session(session))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("list22"))
                .andExpect(view().name("usermanagment"));
    }

    //AdminChangePwd view method
    @Test
    public void adminchangepwdViewPage() throws Exception {
        String userId = "userId123";
        Userbean mockUser = new Userbean();
        mockUser.setUserid(userId);
        List<Userbean> mockUserList = new ArrayList<>();
        mockUserList.add(mockUser);
        when(userService.getAllUsers()).thenReturn(mockUserList);
        when(userService.getUsersByUserId(userId)).thenReturn(mockUser);
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("role", "Admin");
        mockMvc.perform(get("/adminnewpwd").param("id", userId).session(session))
                .andExpect(status().isOk())
                .andExpect(view().name("adminchangepwd"))
                .andExpect(model().attributeExists("list"))
                .andExpect(model().attributeExists("pwd1"));

    }

    //AdminChangepwd Post method
    @Test
    public void testChangepwd2() throws Exception {
        String userId = "123";
        String password = "oldPassword";
        String newPassword = "newPassword";
        String incorrectPassword = "incorrectPassword";

        Userbean mockUser = new Userbean();
        mockUser.setPassword(password);
        // Test for  correct password
        when(userService.getuserPasswordbyUserId(userId)).thenReturn(mockUser);
        mockMvc.perform(post("/adminnewpwd1")
                .param("password", password)
                .param("newpassword1", newPassword)
                .param("userid", userId))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/welcome"));

        // Test for incorrect password
        mockMvc.perform(post("/adminnewpwd1")
                .param("password", incorrectPassword)
                .param("newpassword1", newPassword)
                .param("userid", userId))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/adminnewpwd?id=" + userId))
                .andExpect(flash().attributeExists("oldpwderor"));
    }
}

	
	
    

