package com.ilog.course.config;

import java.util.Collections;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

  private static final String SITE = "api.course-service.com";
  private static final String EMAIL = "";
  private static final String ORGANIZATION_NAME = "Ilog";
  private static final String PROJECT_NAME = "Course Service";
  private static final String BASE_PACKAGE = "com.ilog.course";

  @Bean
  public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2).useDefaultResponseMessages(false).select()
        .apis(RequestHandlerSelectors.basePackage(BASE_PACKAGE)).paths(PathSelectors.any()).build()
        .apiInfo(apiInfo());
  }

  private ApiInfo apiInfo() {
    return new ApiInfo(PROJECT_NAME, "", "", "", new Contact(ORGANIZATION_NAME, SITE, EMAIL), "",
        "", Collections.emptyList());
  }
}