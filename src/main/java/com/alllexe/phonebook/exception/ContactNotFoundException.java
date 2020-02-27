package com.alllexe.phonebook.exception;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 26.02.2020
 */
public class ContactNotFoundException extends RuntimeException {

  public ContactNotFoundException(Integer id) {
    super(String.format("Contact with id: %s, not found", id.toString()));
  }
}
