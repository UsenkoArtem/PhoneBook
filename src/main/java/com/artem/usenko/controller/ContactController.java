package com.artem.usenko.controller;


import com.artem.usenko.dto.Contact;
import com.artem.usenko.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/contact")
public class ContactController {

    private final ContactService contactService;

    @Autowired
    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }


    @GetMapping("/{userId}")
    public ResponseEntity<List<Contact>> getAllContactByLogin(@PathVariable("userId") int userId) throws IOException {
        List<Contact> allContactByLogin = contactService.getAllContactByUserId(userId);
        if (allContactByLogin == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(allContactByLogin, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Contact> addNewContact(@Valid @RequestBody Contact contact, BindingResult bindingResult) throws IOException {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Contact newContact = contactService.addNewContact(contact);

        if (newContact == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(contact, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Boolean> deleteContact(@PathVariable("id") int id) throws IOException {
        contactService.deleteContactById(id);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity updateContact(@Valid @RequestBody Contact contact, BindingResult bindingResult) throws IOException {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Contact updateContact = contactService.updateContact(contact);

        if( updateContact == null)  return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(contact, HttpStatus.OK);
    }

}
