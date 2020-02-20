package com.alllexe.phonebook.controller;

import com.alllexe.phonebook.domain.User;
import com.alllexe.phonebook.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 20.02.2020
 */
@Controller
public class ContactController {

  @Autowired
  private ContactService contactService;

  @GetMapping("/")
  public String getContacts(
      @AuthenticationPrincipal User currentUser,
      Model model) {
    model.addAttribute("contacts", contactService.findAllByAuthor(currentUser));
    return "contacts";
  }

}
