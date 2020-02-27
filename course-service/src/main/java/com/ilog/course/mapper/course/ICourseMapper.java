package com.ilog.course.mapper.course;

import com.ilog.course.dto.CourseDTO;
import com.ilog.course.model.course.Course;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ICourseMapper {

  default Course fromDTO(CourseDTO dto) {
    return dto == null ? null
        : new Course(dto.getTitle(), dto.getDescription(), dto.getIsActive(), dto.getPrice());
  }

  @Mapping(source = "id", target = "id")
  @Mapping(source = "title", target = "title")
  @Mapping(source = "description", target = "description")
  @Mapping(source = "isActive", target = "isActive")
  @Mapping(source = "price", target = "price")
  CourseDTO toDTO(Course course);
}
