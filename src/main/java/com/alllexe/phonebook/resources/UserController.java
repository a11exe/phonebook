package com.alllexe.phonebook.resources;

import com.alllexe.phonebook.domain.User;
import com.alllexe.phonebook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 09.12.2019
 */
@Controller
@RequestMapping("/user")
public class UserController {

  @Autowired
  private UserService userService;

  @PreAuthorize("hasAuthority('ADMIN')")
  @GetMapping
  public String userList(Model model) {
    model.addAttribute("users", userService.findAll());
    return "userList";
  }

  @GetMapping("/{id}")
  public String getUser(@PathVariable(value = "id") User user, Model model) {
    model.addAttribute("user", user);
    return "userEdit";
  }

}
