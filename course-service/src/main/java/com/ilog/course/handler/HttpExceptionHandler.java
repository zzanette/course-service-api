package com.ilog.course.handler;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.ilog.course.dto.ApiErrorDTO;
import com.ilog.course.exception.BussinessException;
import com.ilog.course.exception.ForbiddenException;
import com.ilog.course.exception.NotFoundException;
import com.ilog.course.exception.PreConditionException;
import com.ilog.course.exception.UnAuthorizedException;
import com.ilog.course.service.messageproperties.MessageProperties;
import com.ilog.course.service.messageproperties.MessagePropertiesService;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.LockedException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class HttpExceptionHandler {

  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity<ApiErrorDTO> handleNotFoundException(NotFoundException e) {
    log.error(e.getMessage(), e);
    return getEntity(HttpStatus.NOT_FOUND, e.getMessage());
  }

  @ExceptionHandler(PreConditionException.class)
  public ResponseEntity<ApiErrorDTO> handlePreCondicionException(PreConditionException e) {
    log.error(e.getMessage(), e);
    return getEntity(HttpStatus.PRECONDITION_FAILED, e.getMessage());
  }

  @ExceptionHandler(BussinessException.class)
  public ResponseEntity<ApiErrorDTO> handleBussinessException(BussinessException e) {
    log.error(e.getMessage(), e);
    return getEntity(HttpStatus.BAD_REQUEST, e.getMessage());
  }

  @ExceptionHandler(ForbiddenException.class)
  public ResponseEntity<ApiErrorDTO> handleForbiddenException(ForbiddenException e) {
    log.error(e.getMessage(), e);
    return getEntity(HttpStatus.FORBIDDEN,
        MessagePropertiesService.getMessage(MessageProperties.NOT_ALLOWED_TO_EXECUTE));
  }

  @ExceptionHandler(UnAuthorizedException.class)
  public ResponseEntity<ApiErrorDTO> handleUnAuthorizedException(UnAuthorizedException e) {
    log.error(e.getMessage(), e);
    return getEntity(HttpStatus.UNAUTHORIZED,
        MessagePropertiesService.getMessage(MessageProperties.INVALID_USERNAME_PASSWORD));
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ApiErrorDTO> processValidationError(MethodArgumentNotValidException e) {
    log.error(e.getMessage(), e);
    return getEntity(e.getBindingResult());
  }

  @ExceptionHandler(TokenExpiredException.class)
  public ResponseEntity<ApiErrorDTO> handleTokenExpiredException(TokenExpiredException e) {
    log.error(e.getMessage(), e);
    return getEntity(HttpStatus.FORBIDDEN, e.getMessage());
  }

  @ExceptionHandler(LockedException.class)
  public ResponseEntity<ApiErrorDTO> handleLockedException(LockedException e) {
    log.error(e.getMessage(), e);
    return getEntity(HttpStatus.FORBIDDEN, e.getMessage());
  }

  private ResponseEntity<ApiErrorDTO> getEntity(HttpStatus status, List<String> messages) {
    return ResponseEntity.status(status)
        .body(new ApiErrorDTO(status.value(), status.getReasonPhrase(), messages));
  }

  private ResponseEntity<ApiErrorDTO> getEntity(HttpStatus status, String message) {
    return getEntity(status, Collections.singletonList(message));
  }

  private ResponseEntity<ApiErrorDTO> getEntity(BindingResult bindingResult) {
    List<String> erros = bindingResult.getAllErrors().stream().map(error -> {
      FieldError fieldError = (FieldError) error;
      return fieldError.getField() + " : " + fieldError.getDefaultMessage();
    }).collect(Collectors.toList());

    return getEntity(HttpStatus.UNPROCESSABLE_ENTITY, erros);
  }
}
