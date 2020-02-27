package com.ilog.course.repository.content;

import com.ilog.course.model.content.CourseContent;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICourseContentRepository extends JpaRepository<CourseContent, Long> {

  Optional<CourseContent> findByIdAndCourse_Id(Long courseContentId, Long courseId);

  List<CourseContent> findAllByCourse_IdAndCourse_IsActive(Long courseId, Boolean isCourseActive);
}
