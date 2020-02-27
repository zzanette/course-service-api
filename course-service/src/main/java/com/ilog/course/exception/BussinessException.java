package com.ilog.course.exception;

import java.util.List;
import org.apache.commons.lang3.StringUtils;

public class BussinessException extends RuntimeException {

  private List<String> messages;

  public BussinessException(List<String> messages) {
    super(StringUtils.join(messages, " | "));
    this.messages = messages;
  }

  public BussinessException(String message) {
    super(message);
  }
}
