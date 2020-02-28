package com.alllexe.phonebook.service;

import static org.junit.Assert.assertTrue;

import com.alllexe.phonebook.domain.Contact;
import com.alllexe.phonebook.domain.User;
import com.alllexe.phonebook.exception.ContactNotFoundException;
import com.alllexe.phonebook.repository.ContactRepo;
import java.util.Optional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 27.02.2020
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ContactServiceTest {

  @Autowired
  private ContactService contactService;

  @MockBean
  private ContactRepo contactRepo;

  @Test
  public void addContact() {
    Contact contact = new Contact();
    contactService.addContact(contact);
    Mockito.verify(contactRepo, Mockito.times(1)).save(contact);
  }

  @Test
  public void deleteContact() {

    User user = new User();
    Contact contact = new Contact();

    Mockito.doReturn(Optional.of(contact))
        .when(contactRepo)
        .findByIdAndAuthor(contact.getId(), user);

    contactService.delete(contact.getId(), user);
    Mockito.verify(contactRepo, Mockito.times(1)).delete(contact);
  }

}