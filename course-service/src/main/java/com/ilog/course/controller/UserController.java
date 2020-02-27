package com.ilog.course.controller;

import com.ilog.course.dto.UserDTO;
import com.ilog.course.service.user.IUserService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

  private IUserService userService;

  @Autowired
  public UserController(IUserService userService) {
    this.userService = userService;
  }

  @PostMapping(value = "/v1/users/sign-up")
  public ResponseEntity<UserDTO> signUp(final @Valid @RequestBody UserDTO dto) {
    return ResponseEntity.status(HttpStatus.CREATED).body(userService.create(dto));
  }
}
