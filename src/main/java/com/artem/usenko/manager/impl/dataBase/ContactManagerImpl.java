package com.artem.usenko.manager.impl.dataBase;

import com.artem.usenko.dto.Contact;
import com.artem.usenko.dto.mapper.dataBase.ContactMapper;
import com.artem.usenko.manager.ContactManager;
import com.artem.usenko.model.dataBase.ContactEntity;
import com.artem.usenko.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;

@Profile("dataBase")
@Repository
public class ContactManagerImpl implements ContactManager {

    private final ContactRepository contactRepository;

    @Autowired
    public ContactManagerImpl(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }


    @Override
    public List<Contact> getAllContactsByUserId(int userId) {

        return ContactMapper.fromAO(contactRepository.getContactsByUserId(userId));
    }


    @Override
    public Contact updateContact(Contact contact) {
        ContactEntity contactEntity = ContactMapper.toAO(contact);
        return  ContactMapper.fromAO(contactRepository.save(contactEntity));
    }

    @Override
    public void deleteContactById(int id) {
        contactRepository.delete(id);
    }

    @Override
    public Contact addContact(Contact contact) {
        ContactEntity contactEntity = ContactMapper.toAO(contact);
        return  ContactMapper.fromAO(contactRepository.save(contactEntity));
    }
}
