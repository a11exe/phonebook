package com.alllexe.phonebook.controller;

import com.alllexe.phonebook.domain.Contact;
import com.alllexe.phonebook.domain.User;
import com.alllexe.phonebook.service.ContactService;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
  public String basic() {
    return "redirect:/contacts";
  }

  @GetMapping("/contacts")
  public String getContacts(
      @AuthenticationPrincipal User currentUser,
      Model model) {
    model.addAttribute("contacts", contactService.findAllByAuthor(currentUser));
    return "contacts";
  }

  @PostMapping("/contacts/find")
  public String getContacts(
      @AuthenticationPrincipal User currentUser,
      @RequestParam String search,
      Model model) {
    model.addAttribute("search", search);
    model.addAttribute("contacts", contactService.findContacts(currentUser, search));
    return "contacts";
  }


  @PostMapping("/contacts")
  public String addContact(
      @Valid Contact contact,
      BindingResult bindingResult,
      @AuthenticationPrincipal User currentUser,
      Model model) {
    if (bindingResult.hasErrors()) {
      Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);
      model.mergeAttributes(errorsMap);
      model.addAttribute("contacts", contactService.findAllByAuthor(currentUser));
      return "contacts";
    }
    contact.setAuthor(currentUser);
    contactService.addContact(contact);
    return "redirect:/contacts";
  }

  @GetMapping("/contacts/delete/{id}")
  public String deleteContact(
      @AuthenticationPrincipal User currentUser,
      @PathVariable(name = "id") Contact contact) {
    contactService.delete(contact, currentUser);
    return "redirect:/contacts";
  }

  @GetMapping("/contacts/edit/{contact}")
  public String getContact(
      @AuthenticationPrincipal User currentUser,
      @PathVariable Contact contact,
      Model model) {
    model.addAttribute("contact", contact);
    model.addAttribute("contacts", contactService.findAllByAuthor(currentUser));
    return "contacts";
  }

  @PostMapping("/contacts/edit/{id}")
  public String editContact(
      @AuthenticationPrincipal User currentUser,
      @PathVariable Integer id,
      @Valid Contact contactUpdated,
      BindingResult bindingResult,
      Model model) {
    if (bindingResult.hasErrors()) {
      Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);
      model.mergeAttributes(errorsMap);
      model.addAttribute("contact", contactUpdated);
      model.addAttribute("contacts", contactService.findAllByAuthor(currentUser));
      return "contacts";
    }
    contactService.editContact(id, contactUpdated, currentUser);

    return "redirect:/contacts";
  }

}
