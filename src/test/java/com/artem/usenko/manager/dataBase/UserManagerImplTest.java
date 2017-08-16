package com.artem.usenko.manager.dataBase;

import com.artem.usenko.PhoneBook;
import com.artem.usenko.dto.User;
import com.artem.usenko.manager.UserManager;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
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
public class UserManagerImplTest {

    @Autowired
    private UserManager userManager;

    @Test
    public void addNewUserTest() throws CsvRequiredFieldEmptyException, IOException, CsvDataTypeMismatchException {
        User user = addUser("newUser");
        User newUser = userManager.addNewUser(user);
        assertNotNull(newUser);
        assertNotNull(newUser.getId());
    }

    @Test
    public void getUserByLoginWhenLoginNotExistTest() throws IOException {
        String login = "";
        User userByLogin = userManager.getUserByLogin(login);
        assertNull(userByLogin);
    }

    @Test
    public void getUserByLoginWhenLoginExistTest() throws IOException {
        User user = addUser("UserByLogin");
        User newUser = userManager.getUserByLogin(user.getLogin());
        assertNotNull(newUser);
    }

    private User addUser(String login) {
        User user = new User(login,"Admin","Admin","Admin","123123");

        try {
            userManager.addNewUser(user);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return user;
    }
}

