package com.artem.usenko.service;


import com.artem.usenko.dto.Contact;

import java.io.IOException;
import java.util.List;

public interface ContactService {

    List<Contact> getAllContactByUserId(int userId) throws IOException;
    void deleteContactById(int id) throws IOException;
    Contact addNewContact(Contact contactEntity) throws IOException;
    Contact updateContact(Contact contactEntity) throws IOException;
}
