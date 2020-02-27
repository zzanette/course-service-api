package com.ilog.course.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CourseContentDTO {

  private Long id;
  private String title;
  private String urlMediaContent;
  private String contentText;
}
