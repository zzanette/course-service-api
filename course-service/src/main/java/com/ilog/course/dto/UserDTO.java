package com.ilog.course.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

  @NotBlank
  @Size(max = 60)
  private String fullName;

  @NotBlank
  @Size(max = 100)
  private String email;

  @NotBlank
  @Size(max = 50)
  private String username;

  @NotBlank
  @Size(min = 6, max = 16)
  @JsonInclude(Include.NON_EMPTY)
  private String password;
}
