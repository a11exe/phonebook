/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 14.02.2020
 */
package com.alllexe.phonebook.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

@ApiModel(description = "Details about the contact")
@Entity(name="contacts")
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(of = {"id"})
public class Contact {
    @ApiModelProperty(notes = "The uniq id of the contact")
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    @ApiModelProperty(notes = "The contact's name")
    @NotBlank(message = "Name can't be empty")
    @Length(min = 4, message = "name min 4")
    private String name;
    @ApiModelProperty(notes = "The contact's surname")
    @NotBlank(message = "Surname can't be empty")
    @Length(min = 4, message = "surname min 4")
    private String surname;
    @ApiModelProperty(notes = "The contact's middle name")
    @NotBlank(message = "Middle name can't be empty")
    @Length(min = 4, message = "middle name min 4")
    private String middleName;
    @ApiModelProperty(notes = "The contact's mobile phone number")
    @NotBlank(message = "Mobile phone can't be empty")
    private String phoneMobile;
    @ApiModelProperty(notes = "The contact's home phone number")
    private String phoneHome;
    @ApiModelProperty(notes = "The contact's address")
    private String address;
    @ApiModelProperty(notes = "The contact's email")
    @Email(message = "Email is not correct")
    private String email;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User author;
}
