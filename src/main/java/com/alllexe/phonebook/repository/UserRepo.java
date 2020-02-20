package com.alllexe.phonebook.repository;

import com.alllexe.phonebook.domain.User;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<User, Integer> {

  User findByUsername(String login);

  List<User> findAll();

}
