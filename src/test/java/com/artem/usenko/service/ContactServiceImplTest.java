package com.artem.usenko.service;


import com.artem.usenko.PhoneBook;
import com.artem.usenko.dto.Contact;
import com.artem.usenko.dto.User;
import com.artem.usenko.manager.UserManager;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = PhoneBook.class)
@ActiveProfiles("dataBase")
public class ContactServiceImplTest {

    @Autowired
    private ContactService contactService;

    @Autowired
    private UserManager userManager;

    @Test
    public void addNewUserWhenUserIdNotExist() throws IOException {
        Contact contact = new Contact(0, "Test", "Admin", "Admin", "+38096006423");
        contact = contactService.addNewContact(contact);
        Assert.assertNull(contact);
    }

    @Test
    public void addNewUserWhenUserIdExist() throws IOException {
        User user = new User("TestUService", "Admin", "Admin", "Admin", "123123");
        user = userManager.addNewUser(user);
        Contact contact = new Contact(user.getId(), "Test", "Admin", "Admin", "+38096006423");
        contact = contactService.addNewContact(contact);
        Assert.assertNotNull(contact);
    }

    @Test
    public void getAllContactByUserIdWhenIdNotExistWaitNull() throws IOException {
        List<Contact> allContactByUserId = contactService.getAllContactByUserId(0);
        Assert.assertNull(allContactByUserId);
    }

    @Test
    public void getAllContactByUserIdWhenIdExist() throws IOException {
        User user = new User("TestUService", "Admin", "Admin", "Admin", "123123");
        user = userManager.addNewUser(user);
        List<Contact> allContactByUserId = contactService.getAllContactByUserId(user.getId());
        Assert.assertNotNull(allContactByUserId);
    }

    @Test
    public void updateUserWhenUserIdNotExist() throws IOException {
        Contact contact = new Contact(0, "Test", "Admin", "Admin", "+38096006423");
        contact = contactService.updateContact(contact);
        Assert.assertNull(contact);
    }

    @Test
    public void updateUserWhenUserIdExist() throws IOException {
        User user = new User("User", "Admin", "Admin", "Admin", "123123");
        user = userManager.addNewUser(user);
        Contact contact = new Contact(user.getId(), "Admin", "Admin", "Admin", "+38096006423");
        contact = contactService.addNewContact(contact);
        contact.setFirstName("Test");
        Contact contact1 = contactService.updateContact(contact);
        Assert.assertEquals(contact,contact1);
    }

}
