/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 14.02.2020
 */
package com.alllexe.phonebook.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
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
@JsonIgnoreProperties("author")
public class Contact {
    @ApiModelProperty(notes = "The uniq id of the contact")
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    @ApiModelProperty(notes = "The contact's name")
    @Length(min = 4, message = "name min 4")
    private String name;
    @ApiModelProperty(notes = "The contact's surname")
    @Length(min = 4, message = "surname min 4")
    private String surname;
    @ApiModelProperty(notes = "The contact's middle name")
    @Length(min = 4, message = "middle name min 4")
    @Column(name = "middle_name")
    private String middleName;
    @ApiModelProperty(notes = "The contact's mobile phone number")
    @Column(name = "phone_mobile")
    @Pattern(regexp = "^\\+380\\d{9}$", message = "phone number format +380xxxxxxxxx")
    private String phoneMobile;
    @ApiModelProperty(notes = "The contact's home phone number")
    @Column(name = "phone_home")
    @Pattern(regexp = "^(?:\\+380\\d{9}|)$", message = "phone number format +380xxxxxxxxx")
    private String phoneHome;
    @ApiModelProperty(notes = "The contact's address")
    private String address;
    @ApiModelProperty(notes = "The contact's email")
    @Email(message = "Email is not correct")
    private String email;

    @ApiModelProperty(hidden = true)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User author;
}
