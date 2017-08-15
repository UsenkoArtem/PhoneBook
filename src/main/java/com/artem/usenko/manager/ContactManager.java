package com.artem.usenko.manager;


import com.artem.usenko.dto.Contact;

import java.io.IOException;
import java.util.List;

public interface ContactManager {

    List<Contact> getAllContactsByUserId(int userId) throws IOException;

    Contact updateContact(Contact contact) throws IOException;

    void deleteContactById(int id) throws IOException;

    Contact addContact(Contact contact) throws IOException;
}
