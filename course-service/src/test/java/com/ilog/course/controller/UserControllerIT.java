package com.ilog.course.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.ilog.course.dto.UserDTO;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;

public class UserControllerIT extends BaseIT {

  @Test
  @Sql(value = "deleteUserTestCreateUser.sql")
  public void shouldCreateUser() throws Exception {
    UserDTO dto = new UserDTO("John Pelegrim", "testcreateuser@gmail.com", "testcreateuser", "123456");
    getMvc().perform(
        post("/v1/users/sign-up").contentType(MediaType.APPLICATION_JSON).content(toJson(dto)))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.fullName", Matchers.is("John Pelegrim")))
        .andExpect(jsonPath("$.username", Matchers.is("testcreateuser")));
  }
}
