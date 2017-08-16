package com.artem.usenko.service;

import com.artem.usenko.PhoneBook;
import com.artem.usenko.dto.User;
import com.artem.usenko.manager.UserManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = PhoneBook.class)
@ActiveProfiles("dataBase")
public class UserServiceImplTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserManager userManager;


    public void addUser(String login) throws Exception {
        User user = new User(login, "Admin", "Admin", "Admin", "123123");
        userManager.addNewUser(user);
    }

    @Test
    public void getUserByLoginWhenLoginNotExist() throws IOException {
        User userByLogin = userService.getUserByLogin("");
        assertNull(userByLogin);
    }

    @Test
    public void getUserByLoginWhenLoginExist() throws Exception {
        addUser("TestAdmin");
        User userByLogin = userService.getUserByLogin("Test");
        assertNotNull(userByLogin);
    }


}
