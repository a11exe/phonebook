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
}
