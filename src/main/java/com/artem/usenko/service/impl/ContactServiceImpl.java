package com.artem.usenko.service.impl;

import com.artem.usenko.dto.Contact;
import com.artem.usenko.dto.User;
import com.artem.usenko.manager.ContactManager;
import com.artem.usenko.manager.UserManager;
import com.artem.usenko.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
@Service
public class ContactServiceImpl implements ContactService {

    private final ContactManager contactManager;

    private final UserManager userManager;

    @Autowired
    public ContactServiceImpl(ContactManager contactManager, UserManager userManager) {
        this.contactManager = contactManager;
        this.userManager = userManager;
    }

    @Override
    public List<Contact> getAllContactByUserId(int id) throws IOException {

        return contactManager.getAllContactsByUserId(id);
    }

    @Override
    public void deleteContactById(int id) throws IOException {
            contactManager.deleteContactById(id);
    }

    @Override
    public Contact addNewContact(Contact contact) throws IOException {

        return contactManager.addContact(contact);
    }

    @Override
    public Contact updateContact(Contact contact) throws IOException {
        User userById = userManager.getUserById(contact.getId());
        if (userById == null) return null;
        return contactManager.updateContact(contact);
    }


}

