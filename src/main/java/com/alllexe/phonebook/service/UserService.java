package com.alllexe.phonebook.service;

import com.alllexe.phonebook.domain.User;
import com.alllexe.phonebook.repository.UserRepo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 20.02.2020
 */
@Service
public class UserService implements UserDetailsService {

  @Autowired
  private UserRepo userRepo;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userRepo.findByUsername(username);
    if (user == null) {
      throw new UsernameNotFoundException("Bad credentionals");
    }
    return user;
  }

  public boolean addUser(User user) {
    User userFromDb = userRepo.findByUsername(user.getUsername());
    if (userFromDb != null) {
      return false;
    }
    user.setActive(true);
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    userRepo.save(user);
    return true;
  }


}
