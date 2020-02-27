package com.alllexe.phonebook.service;

import static org.junit.Assert.assertTrue;

import com.alllexe.phonebook.domain.Contact;
import com.alllexe.phonebook.domain.User;
import com.alllexe.phonebook.repository.ContactRepo;
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
    User author = new User();
    author.setId(1);
    Contact contact = new Contact();
    contact.setAuthor(author);
    contactService.delete(contact, author);
    Mockito.verify(contactRepo, Mockito.times(1)).delete(contact);
  }

  @Test
  public void deleteAlienContact() {
    User author = new User();
    author.setId(1);
    User currentUser = new User();
    author.setId(2);
    Contact contact = new Contact();
    contact.setAuthor(author);
    contactService.delete(contact, currentUser);
    Mockito.verify(contactRepo, Mockito.times(0)).delete(contact);
  }

}