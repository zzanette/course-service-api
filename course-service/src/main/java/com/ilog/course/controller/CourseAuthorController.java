package com.ilog.course.controller;

import com.ilog.course.dto.CourseAuthorDTO;
import com.ilog.course.service.courseauthor.ICourseAuthorService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CourseAuthorController {

  private ICourseAuthorService courseAuthorService;

  @Autowired
  public CourseAuthorController(ICourseAuthorService courseAuthorService) {
    this.courseAuthorService = courseAuthorService;
  }

  @GetMapping(value = "/v1/courses/{courseId}/authors")
  public ResponseEntity<List<CourseAuthorDTO>> getAuthors(final @PathVariable Long courseId) {
    return ResponseEntity.ok(courseAuthorService.getCourseAuthors(courseId));
  }

  @PostMapping(value = "/v1/courses/{courseId}/authors")
  public ResponseEntity<CourseAuthorDTO> addAuthor(final @PathVariable Long courseId,
      final @Valid @RequestBody CourseAuthorDTO dto) {
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(courseAuthorService.addAuthor(courseId, dto));
  }
}
