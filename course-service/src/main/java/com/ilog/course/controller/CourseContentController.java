package com.ilog.course.controller;

import com.ilog.course.dto.CourseContentDTO;
import com.ilog.course.service.content.ICourseContentService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.List;
import javax.validation.Valid;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CourseContentController {

  private ICourseContentService contentService;

  @Autowired
  public CourseContentController(ICourseContentService contentService) {
    this.contentService = contentService;
  }

  @ApiOperation(value = "Create a content course.",response = CourseContentDTO.class)
  @ApiImplicitParams(@ApiImplicitParam(name = "Authorization",
      value = "Authorization JWT (Bearer ...)", paramType = "header"))
  @ApiResponses(value = {
      @ApiResponse(code = 201, message = "Content course updated.", response = CourseContentDTO.class),
      @ApiResponse(code = 500, message = "Internal Server Error.")
  })
  @PostMapping(value = "/v1/courses/{courseId}/content")
  public ResponseEntity<CourseContentDTO> create(final @PathVariable Long courseId,
      final @Valid @RequestBody CourseContentDTO dto) {
    return ResponseEntity.status(HttpStatus.CREATED).body(contentService.create(courseId, dto));
  }

  @ApiOperation(value = "Update a content course.",response = CourseContentDTO.class)
  @ApiImplicitParams(@ApiImplicitParam(name = "Authorization",
      value = "Authorization JWT (Bearer ...)", paramType = "header"))
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Content course updated.", response = CourseContentDTO.class),
      @ApiResponse(code = 500, message = "Internal Server Error.")
  })
  @PatchMapping(value = "/v1/courses/{courseId}/content/{courseCoutentId}")
  public ResponseEntity<CourseContentDTO> update(final @PathVariable Long courseId,
      final @PathVariable Long courseCoutentId, final @Valid @RequestBody CourseContentDTO dto) {
    return ResponseEntity.ok(contentService.update(courseId, courseCoutentId, dto));
  }

  @ApiOperation(value = "Get all content from actived course.",response = CourseContentDTO.class)
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Courses listed.", response = CourseContentDTO.class),
      @ApiResponse(code = 500, message = "Internal Server Error.")
  })
  @GetMapping(value = "/v1/courses/{courseId}/content")
  public ResponseEntity<List<CourseContentDTO>> getAllActiveContent(
      final @PathVariable Long courseId) {
    return ResponseEntity.ok(contentService.getAllActive(courseId));
  }
}
