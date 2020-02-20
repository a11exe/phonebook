package com.alllexe.phonebook.repository;

import com.alllexe.phonebook.domain.Contact;
import com.alllexe.phonebook.domain.User;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface ContactRepo extends CrudRepository<Contact, Integer> {

  List<Contact> findAllByAuthor(User author);

}
