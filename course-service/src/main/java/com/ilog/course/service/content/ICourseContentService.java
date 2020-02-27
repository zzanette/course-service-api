package com.ilog.course.service.content;

import com.ilog.course.dto.CourseContentDTO;
import java.util.List;

public interface ICourseContentService {

  CourseContentDTO create(Long courseId, CourseContentDTO dto);

  CourseContentDTO update(Long courseId, Long courseContentId, CourseContentDTO dto);

  List<CourseContentDTO> getAllActive(Long courseId);
}
