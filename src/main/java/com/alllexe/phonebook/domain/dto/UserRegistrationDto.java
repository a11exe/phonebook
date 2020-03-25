package com.alllexe.phonebook.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 28.02.2020
 */
@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = {"password"})
public class UserRegistrationDto {

    private String username;
    private String name;
    private String password;
    private String passwordConfirm;

}
