package com.ilog.course.service.messageproperties;

import java.util.ResourceBundle;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.support.MessageSourceResourceBundle;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MessagePropertiesService {

  private static final String PROPERTIES = "message";
  private static ResourceBundle resourceBundle = MessageSourceResourceBundle.getBundle(PROPERTIES);

  public static String getMessage(MessageProperties messageProperties) {
    if (messageProperties == null) {
      return StringUtils.EMPTY;
    }

    return resourceBundle.getString(messageProperties.getMessageKey());
  }
}
