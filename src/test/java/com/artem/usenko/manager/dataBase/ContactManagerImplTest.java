package com.artem.usenko.manager.dataBase;

import com.artem.usenko.PhoneBook;
import com.artem.usenko.dto.Contact;
import com.artem.usenko.dto.User;
import com.artem.usenko.manager.ContactManager;
import com.artem.usenko.manager.UserManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = PhoneBook.class)
@TestPropertySource(locations = "classpath:database-test.properties")
public class ContactManagerImplTest {

    @Autowired
    private ContactManager contactManager;

    @Autowired
    private UserManager userManager;

    @Test
    public void getAllContactsByUserIdWhenUserIdNotExist() throws IOException {
        List<Contact> allContactsByUserId = contactManager.getAllContactsByUserId(0);
        assertTrue(allContactsByUserId.size() == 0);
    }

    @Test
    public void getAllContactByUserIdWhenUserIdExist() throws IOException {
        User user = addUserAndContacts("Test");
        List<Contact> allContactsByUserId = contactManager.getAllContactsByUserId(user.getId());
        assertTrue(allContactsByUserId.size() == 5);
    }

    @Test
    public void deleteContactById() throws IOException {
        User delete = addUserAndContacts("Delete");
        List<Contact> allContactsByUserId = contactManager.getAllContactsByUserId(delete.getId());
        assertTrue(allContactsByUserId.size()>0);
        for (Contact contact: allContactsByUserId) {
            contactManager.deleteContactById(contact.getId());
        }
        int size = contactManager.getAllContactsByUserId(delete.getId()).size();
        assertEquals(0,size);
    }

    private User addUserAndContacts(String login) throws IOException {
        User user = new User();
        user.setLogin(login);
        user.setFirstName("Admin");
        user.setLastName("Admin");
        user.setPatronymic("Admin");
        user.setPassword("123123");
        User newUser = userManager.addNewUser(user);
        user.setId(newUser.getId());
        Contact contact = new Contact();
        contact.setUserId(newUser.getId());
        contact.setFirstName("Test");
        contact.setLastName("Test");
        contact.setPatronymic("Test");
        contact.setMobilephone("+380960064223");
        for (int i = 0; i < 5; ++i) {
            contactManager.addContact(contact);
        }
            return user;

    }
}