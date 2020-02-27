package com.ilog.course.dto;

import java.math.BigDecimal;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CourseDTO {

  private Long id;

  @NotBlank
  private String title;

  private String description;

  @NotNull
  @Min(value = 0)
  private BigDecimal price;

  @NotNull
  private Boolean isActive;
}
