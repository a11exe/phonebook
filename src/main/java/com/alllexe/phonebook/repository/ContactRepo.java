package com.alllexe.phonebook.repository;

import com.alllexe.phonebook.domain.Contact;
import com.alllexe.phonebook.domain.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ContactRepo extends CrudRepository<Contact, Integer> {

  List<Contact> findAllByAuthor(User author);

  @Query("SELECT c FROM contacts c WHERE c.author = ?1 and (c.name LIKE ?2 or c.surname LIKE ?2 or c.phoneMobile LIKE ?2 or c.phoneHome LIKE ?2)")
  List<Contact> findContacts(User currentUser, String search);

  Optional<Contact> findByIdAndAuthor(Integer id, User user);

}
