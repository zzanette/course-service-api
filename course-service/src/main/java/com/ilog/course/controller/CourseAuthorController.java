package com.ilog.course.controller;

import com.ilog.course.dto.CourseAuthorDTO;
import com.ilog.course.service.courseauthor.ICourseAuthorService;
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

  @ApiOperation(value = "Get all authors of a course.",response = CourseAuthorDTO.class)
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Authors listed.", response = CourseAuthorDTO.class),
      @ApiResponse(code = 500, message = "Internal Server Error.")
  })
  @GetMapping(value = "/v1/courses/{courseId}/authors")
  public ResponseEntity<List<CourseAuthorDTO>> getAuthors(final @PathVariable Long courseId) {
    return ResponseEntity.ok(courseAuthorService.getCourseAuthors(courseId));
  }

  @ApiOperation(value = "Add an existent user to course as an author.",response = CourseAuthorDTO.class)
  @ApiImplicitParams(@ApiImplicitParam(name = "Authorization",
      value = "Authorization JWT (Bearer ...)", paramType = "header"))
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Author added.", response = CourseAuthorDTO.class),
      @ApiResponse(code = 500, message = "Internal Server Error.")
  })
  @PostMapping(value = "/v1/courses/{courseId}/authors")
  public ResponseEntity<CourseAuthorDTO> addAuthor(final @PathVariable Long courseId,
      final @Valid @RequestBody CourseAuthorDTO dto) {
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(courseAuthorService.addAuthor(courseId, dto));
  }
}
