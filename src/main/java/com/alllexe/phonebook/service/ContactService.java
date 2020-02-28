package com.alllexe.phonebook.service;

import com.alllexe.phonebook.domain.Contact;
import com.alllexe.phonebook.domain.User;
import com.alllexe.phonebook.exception.ContactNotFoundException;
import com.alllexe.phonebook.repository.ContactRepo;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 20.02.2020
 */
@Service
public class ContactService {

  @Autowired
  private ContactRepo contactRepo;

  public List<Contact> findAllByAuthor(User currentUser) {
    return contactRepo.findAllByAuthor(currentUser);
  }

  public void addContact(Contact contact) {
    contactRepo.save(contact);
  }

  public void delete(Integer contactId, User currentUser) {
    Contact contact = contactRepo.findByIdAndAuthor(contactId, currentUser).
        orElseThrow(()->new ContactNotFoundException(contactId));
    contactRepo.delete(contact);
  }

  public void editContact(Integer contactId, Contact contactUpdated,
      User currentUser) {
    Contact contact = contactRepo.findByIdAndAuthor(contactId, currentUser).
        orElseThrow(()->new ContactNotFoundException(contactId));

    if (contact!= null) {
      contact.setName(contactUpdated.getName());
      contact.setMiddleName(contactUpdated.getMiddleName());
      contact.setSurname(contactUpdated.getSurname());
      contact.setAddress(contactUpdated.getAddress());
      contact.setEmail(contactUpdated.getEmail());
      contact.setPhoneHome(contactUpdated.getPhoneHome());
      contact.setPhoneMobile(contactUpdated.getPhoneMobile());
      contactRepo.save(contact);
    }
  }

  public List<Contact> findContacts(User currentUser, String search) {
    StringBuilder likeSearch = new StringBuilder(search);
    likeSearch.append("%");
    likeSearch.insert(0, "%");
    return contactRepo.findContacts(currentUser, likeSearch.toString());
  }

  public Optional<Contact> findByIdAndAuthor(Integer id, User currentUser) {
    return contactRepo.findByIdAndAuthor(id, currentUser);
  }
}
