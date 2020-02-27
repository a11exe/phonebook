/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 14.02.2020
 */
package com.alllexe.phonebook.resources;

import com.alllexe.phonebook.domain.Contact;
import com.alllexe.phonebook.domain.User;
import com.alllexe.phonebook.exception.ContactNotFoundException;
import com.alllexe.phonebook.service.ContactService;
import io.swagger.annotations.ApiOperation;
import java.util.ArrayList;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/contacts")
public class ContactsResource {

    @Autowired
    private ContactService contactService;

    @GetMapping("/{id}")
    @ApiOperation(
            value = "Find contact by id",
            notes = "Provide an id to look up specific contact",
            response = Contact.class
    )
    public Contact getContact(@PathVariable Integer id, @AuthenticationPrincipal User currentUser) {
        return contactService.findByIdAndAuthor(id, currentUser)
            .orElseThrow(()->new ContactNotFoundException(id));
    }

    @GetMapping("/")
    public List<Contact> getAllContacts(@AuthenticationPrincipal User currentUser) {
      return contactService.findAllByAuthor(currentUser);
    }

    @PostMapping("/")
    public Contact addContact(
        @Valid @RequestBody Contact contact,
        @AuthenticationPrincipal User currentUser) {

        contact.setAuthor(currentUser);
        contactService.addContact(contact);

        return contact;
    }

    @PutMapping("/{id}")
    public Contact editContact(
        @Valid @RequestBody Contact contactUpdated,
        @AuthenticationPrincipal User currentUser,
        @PathVariable Integer id) {

        contactService.editContact(id, contactUpdated, currentUser);
        return contactUpdated;
    }

    @DeleteMapping("/{id}")
    public Contact deleteContact(
        @AuthenticationPrincipal User currentUser,
        @PathVariable Contact contact) {

        contactService.delete(contact, currentUser);
        return contact;
    }

}
