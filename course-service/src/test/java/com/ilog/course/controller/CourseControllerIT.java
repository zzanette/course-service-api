package com.ilog.course.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.ilog.course.dto.CourseDTO;
import java.math.BigDecimal;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;

public class CourseControllerIT extends BaseIT {

  @Test
  @Sql(value = "insertUserTest.sql")
  public void shouldCreateCourse() throws Exception {
    CourseDTO dto = new CourseDTO(null, "Java Course", "Learn Java 20 days", BigDecimal.TEN,
        Boolean.TRUE);
    getMvc()
        .perform(post("/v1/courses").contentType(MediaType.APPLICATION_JSON).content(toJson(dto)))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.title", is(dto.getTitle())))
        .andExpect(jsonPath("$.description", is(dto.getDescription())));
  }

  @Test
  @Sql(value = "insertCoursesActive.sql")
  public void shouldGetAllCourse() throws Exception {
    getMvc()
        .perform(get("/v1/courses").contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].title", is("Java Course")));
  }
}
