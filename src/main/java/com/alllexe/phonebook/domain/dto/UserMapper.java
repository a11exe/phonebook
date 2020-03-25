/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 24.03.2020
 */
package com.alllexe.phonebook.domain.dto;

import com.alllexe.phonebook.domain.User;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {
    User userRegistrationDtoToUser(UserRegistrationDto userRegistrationDto);
}
