package com.alllexe.phonebook.controller;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.xpath;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 29.01.2020
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
@WithUserDetails("user1")
@Sql(value = {"/create-user-before.sql", "/contacts-list-before.sql"}, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/contacts-list-after.sql", "/create-user-after.sql"}, executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
public class ContactControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  public void contactsListTest() throws Exception {
    this.mockMvc.perform(get("/contacts"))
        .andDo(print())
        .andExpect(authenticated())
        .andExpect(view().name("contacts"))
        .andExpect(xpath("//html/body/div/div[4]/div/table/tbody/tr").nodeCount(15));

  }

  @Test
  public void findContactTest() throws Exception {
    this.mockMvc.perform(post("/contacts/find").param("search", "Alex").with(csrf()))
        .andDo(print())
        .andExpect(authenticated())
        .andExpect(xpath("//html/body/div/div[4]/div/table/tbody/tr").nodeCount(2));

  }

  @Test
  public void addAndDeleteContactTest() throws Exception {
    this.mockMvc.perform(get("/contacts"))
        .andDo(print())
        .andExpect(authenticated())
        .andExpect(view().name("contacts"))
        .andExpect(xpath("//html/body/div/div[4]/div/table/tbody/tr").nodeCount(15));

    this.mockMvc.perform(post("/contacts/")
        .param("name", "New999")
        .param("surname", "Kovalski")
        .param("middleName", "Simpson")
        .param("phoneMobile", "+380665533488")
        .param("phoneHome", "")
        .param("address", "White, 69")
        .param("email", "gabe@mail.com")
        .with(csrf()))
        .andDo(print())
        .andExpect(authenticated())
        .andExpect(status().is3xxRedirection())
        .andExpect(redirectedUrl("/contacts"));

    this.mockMvc.perform(get("/contacts"))
        .andDo(print())
        .andExpect(authenticated())
        .andExpect(view().name("contacts"))
        .andExpect(xpath("//html/body/div/div[4]/div/table/tbody/tr").nodeCount(16));

    this.mockMvc.perform(get("/contacts/delete/101"))
        .andDo(print())
        .andExpect(authenticated())
        .andExpect(status().is3xxRedirection())
        .andExpect(redirectedUrl("/contacts"));

    this.mockMvc.perform(get("/contacts"))
        .andDo(print())
        .andExpect(authenticated())
        .andExpect(view().name("contacts"))
        .andExpect(xpath("//html/body/div/div[4]/div/table/tbody/tr").nodeCount(15));

  }

  @Test
  public void editContactTest() throws Exception {

    this.mockMvc.perform(post("/contacts/find").param("search", "Gabe999").with(csrf()))
        .andDo(print())
        .andExpect(authenticated())
        .andExpect(xpath("//html/body/div/div[4]/div/table/tbody/tr").nodeCount(0));

    this.mockMvc.perform(post("/contacts/edit/102")
        .param("name", "Gabe999")
        .param("surname", "Kovalski")
        .param("middleName", "Simpson")
        .param("phoneMobile", "+380665533488")
        .param("phoneHome", "")
        .param("address", "White, 69")
        .param("email", "gabe@mail.com")
        .with(csrf()))
        .andDo(print())
        .andExpect(authenticated())
        .andExpect(status().is3xxRedirection())
        .andExpect(redirectedUrl("/contacts"));

    this.mockMvc.perform(post("/contacts/find").param("search", "Gabe999").with(csrf()))
        .andDo(print())
        .andExpect(authenticated())
        .andExpect(xpath("//html/body/div/div[4]/div/table/tbody/tr").nodeCount(1));

  }

}
