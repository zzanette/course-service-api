package com.ilog.course.controller;

import com.ilog.course.dto.UserDTO;
import com.ilog.course.service.user.IUserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javax.validation.Valid;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

  private IUserService userService;

  @Autowired
  public UserController(IUserService userService) {
    this.userService = userService;
  }

  @ApiOperation(value = "SingUp an user.",
      response = UserDTO.class)
  @ApiImplicitParams(@ApiImplicitParam(name = "Authorization",
      value = "Authorization JWT (Bearer ...)", paramType = "header"))
  @ApiResponses(value = {
      @ApiResponse(code = 201, message = "User created.",
          response = UserDTO.class),
      @ApiResponse(code = 500, message = "Internal Server Error.")})
  @PostMapping(value = "/v1/users/sign-up")
  public ResponseEntity<UserDTO> signUp(final @Valid @RequestBody UserDTO dto) {
    return ResponseEntity.status(HttpStatus.CREATED).body(userService.create(dto));
  }

  @ApiImplicitParams({
      @ApiImplicitParam(name = "username", value = "", paramType = "form"),
      @ApiImplicitParam(name = "password", value = "", paramType = "form")
  })
  @PostMapping(value = "/login", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public void login() {
  }
}
