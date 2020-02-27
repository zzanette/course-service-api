package com.ilog.course.mapper.content;

import com.ilog.course.dto.CourseContentDTO;
import com.ilog.course.dto.CourseDTO;
import com.ilog.course.model.content.CourseContent;
import com.ilog.course.model.course.Course;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ICourseContentMapper {

  default CourseContent fromDTO(CourseContentDTO dto, Course course) {
    if (dto == null) {
      return null;
    }

    return new CourseContent(dto.getTitle(), dto.getUrlMediaContent(), dto.getContentText(),
        course);
  }

  @Mapping(source = "id", target = "id")
  @Mapping(source = "title", target = "title")
  @Mapping(source = "urlMediaContent", target = "urlMediaContent")
  @Mapping(source = "contentText", target = "contentText")
  CourseContentDTO toDTO(CourseContent courseContent);
}
