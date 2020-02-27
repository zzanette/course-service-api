package com.ilog.course.controller;

import com.ilog.course.dto.CourseDTO;
import com.ilog.course.dto.UserDTO;
import com.ilog.course.service.course.ICourseService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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

  @ApiOperation(value = "Create a new course.",
      response = CourseDTO.class)
  @ApiImplicitParams(@ApiImplicitParam(name = "Authorization",
      value = "Authorization JWT (Bearer ...)", paramType = "header"))
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Courses updated.",
          response = CourseDTO.class),
      @ApiResponse(code = 500, message = "Internal Server Error.")})
  @PostMapping(value = "/v1/courses")
  public ResponseEntity<CourseDTO> create(final @Valid @RequestBody CourseDTO dto) {
    return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
  }

  @ApiOperation(value = "Update a course by course id.",
      response = CourseDTO.class)
  @ApiImplicitParams(@ApiImplicitParam(name = "Authorization",
      value = "Authorization JWT (Bearer ...)", paramType = "header"))
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Courses updated.",
          response = CourseDTO.class),
      @ApiResponse(code = 500, message = "Internal Server Error.")})
  @PatchMapping(value = "/v1/courses/{courseId}")
  public ResponseEntity<CourseDTO> update(final @PathVariable Long courseId,
      final @Valid @RequestBody CourseDTO courseDTO) {
    return ResponseEntity.ok(service.update(courseId, courseDTO));
  }

  @ApiOperation(value = "Get all activated course.", response = CourseDTO.class)
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "List of courses.", response = CourseDTO.class),
      @ApiResponse(code = 500, message = "Internal Server Error.")
  })
  @GetMapping(value = "/v1/courses")
  public ResponseEntity<List<CourseDTO>> getAllActive() {
    return ResponseEntity.ok(service.getAllActive());
  }
}
