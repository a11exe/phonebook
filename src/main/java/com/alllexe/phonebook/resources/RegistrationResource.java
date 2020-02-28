package com.alllexe.phonebook.resources;

import com.alllexe.phonebook.domain.User;
import com.alllexe.phonebook.service.UserService;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.SmartValidator;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 28.02.2020
 */
@RestController
@RequestMapping("/api/registration")
public class RegistrationResource {

  @Autowired
  private UserService userService;

  @Autowired
  SmartValidator validator;

  Logger logger = LoggerFactory.getLogger(RegistrationResource.class);

  @PostMapping("/")
  public void addUser(
      @RequestBody Map<String, String> userMap,
      BindingResult bindingResult) throws MethodArgumentNotValidException {

    User newUser = new User();
    newUser.setUsername(userMap.get("userName"));
    newUser.setName(userMap.get("name"));
    newUser.setPassword(userMap.get("password"));
    newUser.setEmail(userMap.get("email"));

    validator.validate(newUser, bindingResult, User.class);

    String passwordConfirm = userMap.get("passwordConfirm");

    boolean isConfirmEmpty = StringUtils.isEmpty(passwordConfirm);
    if (isConfirmEmpty) {
      bindingResult.addError(new ObjectError("password2Error", "Password confirm is empty"));
    }
    if (newUser.getPassword() != null && !newUser.getPassword().equals(passwordConfirm)) {
      bindingResult.addError(new ObjectError("passwordError", "Passwords are different"));
    }
    if (bindingResult.hasErrors()) {
      throw new MethodArgumentNotValidException(null, bindingResult);
    }
    if (!userService.addUser(newUser)) {
      bindingResult.addError(new ObjectError("userNameError", "user exists!"));
    }
    if (bindingResult.hasErrors()) {
      throw new MethodArgumentNotValidException(null, bindingResult);
    }

  }

}
