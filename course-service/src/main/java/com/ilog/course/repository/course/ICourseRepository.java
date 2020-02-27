package com.ilog.course.repository.course;

import com.ilog.course.model.course.Course;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICourseRepository extends JpaRepository<Course, Long> {

  Optional<Course> findByTitle(String title);
  List<Course> findAllByIsActive(Boolean isActive);
}
