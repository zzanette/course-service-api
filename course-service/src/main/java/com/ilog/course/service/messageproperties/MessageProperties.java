package com.ilog.course.service.messageproperties;

import lombok.Getter;

@Getter
public enum MessageProperties {

  COURSE_NOT_FOUND("courseNotFound.message"),
  COURSE_CONTENT_NOT_FOUND("courseContentNotFound.message"),
  COURSE_TITLE_ALREADY_EXISTS("courseTitleAlreadyExists.message"),
  USERNAME_ALREADY_EXISTS("usernameAlreadyExists.message"),
  EMAIL_ALREADY_EXISTS("emailAlreadyExists.message"),
  USER_NOT_FOUND("userNotFound.message"),
  NOT_ALLOWED_TO_EXECUTE("notAllowedToExecute.message"),
  INVALID_USERNAME_PASSWORD("invalidUsernamePassword.message");

  private String messageKey;

  MessageProperties(String messageKey) {
    this.messageKey = messageKey;
  }
}
