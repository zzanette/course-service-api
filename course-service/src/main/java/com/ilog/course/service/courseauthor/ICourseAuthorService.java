package com.ilog.course.service.courseauthor;

import com.ilog.course.dto.CourseAuthorDTO;
import com.ilog.course.model.course.Course;
import com.ilog.course.model.courseauthor.CourseAuthor;
import java.util.List;

public interface ICourseAuthorService {

  CourseAuthor createAuthorFromLoggedUser(Course course);

  List<CourseAuthorDTO> getCourseAuthors(Long courseId);

  CourseAuthorDTO addAuthor(Long courseId, CourseAuthorDTO authorDTO);
}
