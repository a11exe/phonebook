package com.alllexe.phonebook.service;

import com.alllexe.phonebook.domain.Contact;
import com.alllexe.phonebook.domain.User;
import com.alllexe.phonebook.repository.ContactRepo;
import java.util.List;
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

  public Contact findById(Integer id) {
    return contactRepo.findById(id).orElse(null);
  }

  public List<Contact> findAllByAuthor(User currentUser) {
    return contactRepo.findAllByAuthor(currentUser);
  }

  public void addContact(Contact contact) {
    contactRepo.save(contact);
  }

  public void delete(Contact contact, User currentUser) {
    if (contact != null && currentUser.equals(contact.getAuthor())) {
      contactRepo.delete(contact);
    }
  }

  public void editContact(Integer contactId, Contact contactUpdated,
      User currentUser) {
    Contact contact = contactRepo.findById(contactId).orElse(null);
    if (contact!= null && currentUser.equals(contact.getAuthor())) {
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
}
