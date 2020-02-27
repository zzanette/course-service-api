package com.ilog.course.repository.courseauthor;

import com.ilog.course.model.courseauthor.CourseAuthor;
import java.util.List;
import java.util.Optional;
import jdk.nashorn.internal.runtime.options.Option;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IcourseAuthorRepository extends JpaRepository<CourseAuthor, Long> {

  List<CourseAuthor> findAllByCourse_Id(Long courseId);

  Optional<CourseAuthor> findByCourse_IdAndAndAuthor_Username(Long courseId, String username);
}
