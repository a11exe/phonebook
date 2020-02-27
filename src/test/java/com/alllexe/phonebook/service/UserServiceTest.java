package com.alllexe.phonebook.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;

import com.alllexe.phonebook.domain.User;
import com.alllexe.phonebook.repository.UserRepo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 04.02.2020
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

  @Autowired
  private UserService userService;

  @MockBean
  private UserRepo userRepo;

  @MockBean
  private PasswordEncoder passwordEncoder;

  @Test
  public void addUser() {
    User user = new User();
    user.setName("testuser");
    user.setEmail("some@mail.com");
    boolean isUserCreated = userService.addUser(user);
    assertTrue(isUserCreated);
    assertTrue(user.isActive());

    Mockito.verify(userRepo, Mockito.times(1)).save(user);
  }

  @Test
  public void addUserFailed() {

    User user = new User();
    user.setUsername("John");

    Mockito.doReturn(new User())
        .when(userRepo)
        .findByUsername("John");

    boolean isUserCreated = userService.addUser(user);
    assertFalse(isUserCreated);

    Mockito.verify(userRepo, Mockito.times(0)).save(any(User.class));

  }
}