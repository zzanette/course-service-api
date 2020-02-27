package com.ilog.course.controller;

import com.ilog.course.dto.CourseDTO;
import com.ilog.course.service.course.ICourseService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CourseController {

  private ICourseService service;

  @Autowired
  public CourseController(ICourseService service) {
    this.service = service;
  }

  @PostMapping(value = "/v1/courses")
  public ResponseEntity<CourseDTO> create(final @Valid @RequestBody CourseDTO dto) {
    return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
  }

  @PatchMapping(value = "/v1/courses/{courseId}")
  public ResponseEntity<CourseDTO> update(final @PathVariable Long courseId,
      final @Valid @RequestBody CourseDTO courseDTO) {
    return ResponseEntity.ok(service.update(courseId, courseDTO));
  }

  @GetMapping(value = "/v1/courses")
  public ResponseEntity<List<CourseDTO>> getAllActive() {
    return ResponseEntity.ok(service.getAllActive());
  }
}
