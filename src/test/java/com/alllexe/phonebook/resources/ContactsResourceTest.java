package com.alllexe.phonebook.resources;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.alllexe.phonebook.domain.Contact;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.hamcrest.Matchers.*;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 28.02.2020
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
@Sql(value = {"/create-user-before.sql", "/contacts-list-before.sql"}, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/contacts-list-after.sql", "/create-user-after.sql"}, executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
public class ContactsResourceTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @Test
  public void getAllContactsTest() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/api/contacts/")
        .with(httpBasic("user1","12345")))
        .andExpect(status().isOk())
        .andDo(print())
        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$", hasSize(15)));
    }

  @Test
  public void getAllContactsTestAccessDeny() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/api/contacts/")
        .with(httpBasic("user1","11111")))
        .andExpect(status().isUnauthorized())
        .andDo(print());
  }

  @Test
  public void getContactTest() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/api/contacts/101")
        .with(httpBasic("user1","12345")))
        .andExpect(status().isOk())
        .andDo(print())
        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.name", is("Mike")));
  }

  @Test
  public void getAlienContactTest() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/api/contacts/101")
        .with(httpBasic("user2","12345")))
        .andExpect(status().isBadRequest())
        .andDo(print())
        .andExpect(jsonPath("$.error", is("Contact with id: 101, not found")));
  }

  @Test
  public void addContactTest() throws Exception {

    Contact contact = new Contact();
    contact.setName("New999");
    contact.setSurname("Kovalski");
    contact.setMiddleName("Simpson");
    contact.setPhoneMobile("+380665533488");
    contact.setAddress("gabe@mail.com");
    contact.setEmail("gabe@mail.com");

    mockMvc.perform(MockMvcRequestBuilders.post("/api/contacts/")
        .with(httpBasic("user2","12345"))
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(contact)))
        .andExpect(status().isOk())
        .andDo(print());
  }

  @Test
  public void addContactNotValidTest() throws Exception {

    Contact contact = new Contact();
    contact.setName("New999");
    contact.setSurname("Kovalski");
    contact.setMiddleName("Simpson");
    contact.setPhoneMobile("+3806655334");
    contact.setAddress("gabe@mail.com");
    contact.setEmail("gabe@mail.com");

    mockMvc.perform(MockMvcRequestBuilders.post("/api/contacts/")
        .with(httpBasic("user2","12345"))
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(contact)))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.errors", contains("phone number format +380xxxxxxxxx")))
        .andDo(print());
  }

  @Test
  public void editContactTest() throws Exception {

    Contact contact = new Contact();
    contact.setId(100);
    contact.setName("Mike999");
    contact.setSurname("Armor");
    contact.setMiddleName("Simpson");
    contact.setPhoneMobile("+380665533488");
    contact.setAddress("gabe@mail.com");
    contact.setEmail("gabe@mail.com");

    mockMvc.perform(MockMvcRequestBuilders.put("/api/contacts/100")
        .with(httpBasic("user1","12345"))
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(contact)))
        .andExpect(status().isOk())
        .andDo(print());
  }

  @Test
  public void editAlienContactTest() throws Exception {

    Contact contact = new Contact();
    contact.setId(100);
    contact.setName("Mike999");
    contact.setSurname("Armor");
    contact.setMiddleName("Simpson");
    contact.setPhoneMobile("+380665533488");
    contact.setAddress("gabe@mail.com");
    contact.setEmail("gabe@mail.com");

    mockMvc.perform(MockMvcRequestBuilders.put("/api/contacts/100")
        .with(httpBasic("user2","12345"))
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(contact)))
        .andExpect(status().isBadRequest())
        .andDo(print());
  }

  @Test
  public void deleteContactTest() throws Exception {

    mockMvc.perform(MockMvcRequestBuilders.delete("/api/contacts/100")
        .with(httpBasic("user1","12345")))
        .andExpect(status().isOk())
        .andDo(print());
  }

  @Test
  public void deleteContactNotExistTest() throws Exception {

    mockMvc.perform(MockMvcRequestBuilders.delete("/api/contacts/100012")
        .with(httpBasic("user1","12345")))
        .andExpect(status().isBadRequest())
        .andDo(print());
  }


  @Test
  public void deleteAlienContactTest() throws Exception {

   mockMvc.perform(MockMvcRequestBuilders.delete("/api/contacts/100")
        .with(httpBasic("user2","12345"))
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest())
        .andDo(print());
  }

}