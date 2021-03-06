package com.alllexe.phonebook.domain;

import io.swagger.annotations.ApiModelProperty;
import java.util.Collection;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 06.12.2019
 */
@Entity(name="usr")
@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = {"password", "contacts"})
@EqualsAndHashCode(of = {"id"})
public class User implements UserDetails {

  public interface UserValidations{};

  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  @ApiModelProperty(hidden = true)
  private Integer id;
  @Length(min = 3, message = "login min 3", groups = {UserValidations.class})
  @Pattern(regexp = "^[A-Za-z1-9]*$", message = "only english letters and numbers", groups = {UserValidations.class})
  @Column(name = "login")
  private String username;
  @Length(min = 5, message = "name too short. min 5", groups = {UserValidations.class})
  private String name;
  @Length(min = 5, message = "password too short. min 5", groups = {UserValidations.class})
  private String password;

  @ApiModelProperty(hidden = true)
  private boolean active;

  @Email(message = "Email is not correct")
  @ApiModelProperty(hidden = true)
  private String email;

  @ApiModelProperty(hidden = true)
  @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private Set<Contact> contacts;

  @Override
  @ApiModelProperty(hidden = true)
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return null;
  }

  @Override
  @ApiModelProperty(hidden = true)
  public boolean isEnabled() {
    return active;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return username;
  }

  @Override
  @ApiModelProperty(hidden = true)
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  @ApiModelProperty(hidden = true)
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  @ApiModelProperty(hidden = true)
  public boolean isCredentialsNonExpired() {
    return true;
  }
}
