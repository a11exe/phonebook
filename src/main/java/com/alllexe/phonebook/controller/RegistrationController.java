package com.alllexe.phonebook.controller;

import com.alllexe.phonebook.domain.User;
import com.alllexe.phonebook.service.UserService;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 06.12.2019
 */
@Controller
public class RegistrationController {

  @Autowired
  private UserService userService;

  @GetMapping("registration")
  public String registration() {
    return "registration";
  }

  @PostMapping("registration")
  public String addUser(
      @RequestParam("password2") String passwordConfirm,
      @Valid User user,
      BindingResult bindingResult,
      Model model) {

    boolean isConfirmEmpty = StringUtils.isEmpty(passwordConfirm);
    if (isConfirmEmpty) {
      model.addAttribute("password2Error", "Password confirm is empty");
    }
    if (user.getPassword() != null && !user.getPassword().equals(passwordConfirm)) {
      model.addAttribute("passwordError", "Passwords are different");
    }
    if (isConfirmEmpty || bindingResult.hasErrors()) {
      Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);
      model.mergeAttributes(errorsMap);
      return "registration";
    }
    if (!userService.addUser(user)) {
      model.addAttribute("userNameError", "user exists!");
      return "registration";
    }

    return "redirect:/login";
  }

}
