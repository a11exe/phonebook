/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 14.02.2020
 */
package com.alllexe.phonebook.controller;

import com.alllexe.phonebook.domain.Contact;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@RestController
@RequestMapping("/api")
public class AdressBook {

    ConcurrentMap<Integer, Contact> contacts = new ConcurrentHashMap<>();

    @GetMapping("/{id}")
    @ApiOperation(
            value = "Find contact by id",
            notes = "Provide an id to look up specific contact",
            response = Contact.class
    )
    public Contact getContact(@PathVariable Integer id) {
        return contacts.get(id);
    }

    @GetMapping("/")
    public List<Contact> getAllContacts() {
        return new ArrayList<>(contacts.values());
    }

    @PostMapping("/")
    public Contact addContact(@RequestBody Contact contact) {
        contacts.put(contact.getId(), contact);
        return contact;
    }

}
