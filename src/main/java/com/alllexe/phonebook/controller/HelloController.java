package com.alllexe.phonebook.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 20.02.2020
 */
@Controller
@RequestMapping("/")
public class HelloController {

  @GetMapping("/hello")
  public String hello(){
    return "hello";
  }

}
