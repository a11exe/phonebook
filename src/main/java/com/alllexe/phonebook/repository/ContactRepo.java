package com.alllexe.phonebook.repository;

import com.alllexe.phonebook.domain.Contact;
import org.springframework.data.repository.CrudRepository;

public interface ContactRepo extends CrudRepository<Contact, Integer> {

}
