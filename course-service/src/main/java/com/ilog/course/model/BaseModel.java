package com.ilog.course.model;

import com.ilog.course.exception.BussinessException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.validation.Configuration;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.springframework.util.StringUtils;

public abstract class BaseModel {

  protected void isValid() {
    Configuration<?> configuration = Validation.byDefaultProvider().configure();
    ValidatorFactory factory = configuration.buildValidatorFactory();
    Validator validator = factory.getValidator();
    Set<ConstraintViolation<BaseModel>> violations = validator.validate(this);

    List<String> violationMessages = new ArrayList<>();
    for (ConstraintViolation<BaseModel> violation : violations) {
      String field = StringUtils.capitalize(violation.getPropertyPath().toString());
      String message = violation.getMessage();
      violationMessages.add(field.concat(" : ").concat(message));
    }

    if (!violationMessages.isEmpty()) {
      throw new BussinessException(violationMessages);
    }
  }
}
