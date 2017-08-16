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
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = PhoneBook.class)
@ActiveProfiles("dataBase")
public class RegServiceImplTest {

    @Autowired
    private RegService regService;

    @Autowired
    private UserManager userManager;

    @Test
    public void thisLoginAlreadyNotExistsTest() throws IOException {
        Boolean test = regService.thisLoginAlreadyNotExists("test");
        assertTrue(!test);
        addUser("Login");
        test = regService.thisLoginAlreadyNotExists("Login");
        assertTrue(test);
    }

    @Test
    public void registrationUserWhenLoginAlreadyExist() throws  IOException {
        addUser("Admin");
        User user = new User("Admin","","","","");
        User registration = regService.registration(user);
        assertNull(registration);
    }


    @Test
    public void registrationUserWhenLoginAlreadyNotExist() throws  IOException {
        addUser("Test");
        User user = new User("Admin","","","","");
        User registration = regService.registration(user);
        assertNotNull(registration);
    }


    public void addUser(String login) throws IOException {
        User user = new User(login, "Admin", "Admin", "Admin", "123123");
        userManager.addNewUser(user);
    }
}
