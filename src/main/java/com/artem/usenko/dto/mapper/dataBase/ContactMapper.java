package com.artem.usenko.dto.mapper.dataBase;

import com.artem.usenko.dto.Contact;
import com.artem.usenko.model.dataBase.ContactEntity;
import org.springframework.context.annotation.Profile;

import java.util.ArrayList;
import java.util.List;

@Profile(value = "dataBase")
public class ContactMapper {
    public static List<Contact> fromAO(Iterable<ContactEntity> contactEntities) {
        List<Contact> userDAOS = new ArrayList<>();
        if (contactEntities == null) return null;
        for (ContactEntity contactEntity : contactEntities) {
            userDAOS.add(fromAO(contactEntity));
        }
        return userDAOS;
    }

    public static Contact fromAO(ContactEntity contactEntity) {
        if (contactEntity == null) return null;
        else
            return new Contact(
                    contactEntity.getId(), contactEntity.getUserId(), contactEntity.getFirstName(),
                    contactEntity.getLastName(), contactEntity.getPatronymic(),
                    contactEntity.getMobilephone(),contactEntity.getPhone(),
                    contactEntity.getAddress(), contactEntity.getEmail());
    }


    public static ContactEntity toAO(Contact contact) {
        ContactEntity contactEntity = new ContactEntity();
        if (contact != null) {
            contactEntity.setId(contact.getId());
           contactEntity.setUserId(contact.getUserId());
           contactEntity.setFirstName(contact.getFirstName());
           contactEntity.setLastName(contact.getLastName());
           contactEntity.setPatronymic(contact.getPatronymic());
           contactEntity.setMobilephone(contact.getMobilephone());
           contactEntity.setPhone(contact.getPhone());
           contactEntity.setAddress(contact.getAddress());
           contactEntity.setEmail(contact.getEmail());
        } else return null;
        return contactEntity;
    }
}
