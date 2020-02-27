package com.ilog.course.service.messageproperties;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;

public class MessagePropertiesServiceTest {

  @Test
  public void shouldGetMessageCourseNotFound() {
    Assert.assertEquals("Course not found.",
        MessagePropertiesService.getMessage(MessageProperties.COURSE_NOT_FOUND));
  }

  @Test
  public void shouldReturnNull() {
    Assert.assertEquals(StringUtils.EMPTY, MessagePropertiesService.getMessage(null));
  }
}