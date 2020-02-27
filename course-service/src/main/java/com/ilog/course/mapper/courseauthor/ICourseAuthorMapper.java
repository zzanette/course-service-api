package com.ilog.course.mapper.courseauthor;

import com.ilog.course.dto.CourseAuthorDTO;
import com.ilog.course.model.courseauthor.CourseAuthor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ICourseAuthorMapper {


  @Mapping(source = "author.fullName", target = "fullName")
  @Mapping(source = "author.username", target = "username")
  @Mapping(source = "isMainAuthor", target = "isMainAuthor")
  CourseAuthorDTO toDTO(CourseAuthor courseAuthor);
}
