package com.artem.usenko.dto.mapper.csv;

import com.artem.usenko.dto.Contact;
import com.artem.usenko.model.csv.ContactCSV;
import org.springframework.context.annotation.Profile;

import java.util.ArrayList;
import java.util.List;

@Profile("csvFile")
public class ContactMapper {
    public static List<Contact> fromAO(Iterable<ContactCSV> contactCSVS) {
        List<Contact> userDAOS = new ArrayList<>();
        if (contactCSVS == null) return null;
        for (ContactCSV contactCSV : contactCSVS) {
            userDAOS.add(fromAO(contactCSV));
        }
        return userDAOS;
    }

    public static Contact fromAO(ContactCSV contactEntity) {
        if (contactEntity == null) return null;
        else
            return new Contact(
                    parse(contactEntity.getId()),
                    parse(contactEntity.getUserId()), contactEntity.getFirstName(),
                    contactEntity.getLastName(), contactEntity.getPatronymic(),
                    contactEntity.getMobilephone(), contactEntity.getPhone(),
                    contactEntity.getAddress(), contactEntity.getEmail());
    }


    public static ContactCSV toAO(Contact contact) {
        ContactCSV contactCSV = new ContactCSV();
        if (contact != null) {
            contactCSV.setId(contact.getId().toString());
            contactCSV.setUserId(String.valueOf(contact.getUserId()));
            contactCSV.setFirstName(contact.getFirstName());
            contactCSV.setLastName(contact.getLastName());
            contactCSV.setPatronymic(contact.getPatronymic());
            contactCSV.setMobilephone(contact.getMobilephone());
            contactCSV.setPhone(contact.getPhone());
            contactCSV.setAddress(contact.getAddress());
            contactCSV.setEmail(contact.getEmail());
        } else return null;
        return contactCSV;
    }
    private static int parse(String id) {
        int idInt = 0;
        char[] chars = id.toCharArray();
        for (int i = 0; i<chars.length;++i) {
            char digit = chars[i];
            if  (Character.isDigit(digit)) {
                idInt *= 10;
                idInt += (int) (digit - 48);
            }
        }
        return idInt;
    }
}
