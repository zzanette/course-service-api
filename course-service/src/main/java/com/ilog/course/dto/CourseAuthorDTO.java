package com.ilog.course.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CourseAuthorDTO {

  @NotBlank
  private String username;

  @NotBlank
  private String fullName;

  @NotNull
  private Boolean isMainAuthor;
}
