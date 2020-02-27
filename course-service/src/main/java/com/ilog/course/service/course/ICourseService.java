package com.ilog.course.service.course;

import com.ilog.course.dto.CourseDTO;
import java.util.List;

public interface ICourseService {

  CourseDTO create(CourseDTO dto);

  CourseDTO update(Long courseId, CourseDTO dto);

  List<CourseDTO> getAllActive();
}
