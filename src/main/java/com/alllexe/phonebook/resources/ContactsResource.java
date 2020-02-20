/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 14.02.2020
 */
package com.alllexe.phonebook.resources;

import com.alllexe.phonebook.domain.Contact;
import com.alllexe.phonebook.service.ContactService;
import io.swagger.annotations.ApiOperation;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ContactsResource {

    @Autowired
    private ContactService contactService;

    @GetMapping("/{id}")
    @ApiOperation(
            value = "Find contact by id",
            notes = "Provide an id to look up specific contact",
            response = Contact.class
    )
    public Contact getContact(@PathVariable Integer id) {
        return contactService.findById(id);
    }

    @GetMapping("/contacts")
    public List<Contact> getAllContacts() {
        List<Contact> contacts = new ArrayList<>();
        return contacts;
    }

    @PostMapping("/")
    public Contact addContact(@RequestBody Contact contact) {
        return contact;
    }

}
