package com.alllexe.phonebook.resources;

import com.alllexe.phonebook.domain.User;
import com.alllexe.phonebook.domain.dto.UserMapper;
import com.alllexe.phonebook.domain.dto.UserRegistrationDto;
import com.alllexe.phonebook.service.UserService;
import org.mapstruct.factory.Mappers;
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

  private final UserMapper userMapper = Mappers.getMapper(UserMapper.class);

  Logger logger = LoggerFactory.getLogger(RegistrationResource.class);

  @PostMapping("/")
  public void addUser(
      @RequestBody UserRegistrationDto userRegistrationDto,
      BindingResult bindingResult) throws MethodArgumentNotValidException {

    User newUser = userMapper.userRegistrationDtoToUser(userRegistrationDto);

    validator.validate(newUser, bindingResult, User.UserValidations.class);

    String passwordConfirm = userRegistrationDto.getPasswordConfirm();

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
