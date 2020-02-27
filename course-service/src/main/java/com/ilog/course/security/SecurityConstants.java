package com.ilog.course.security;

class SecurityConstants {
  static final String SECRET = "SecretKeyToGenJWTs";
  static final long EXPIRATION_TIME = 3600000; // 1 hour
  static final String TOKEN_PREFIX = "Bearer ";
  static final String HEADER_STRING = "Authorization";
  static final String SIGN_UP_URL = "/v1/users/sign-up";
  static final String PUBLIC_COURSE_URL = "/v1/courses";
  static final String PUBLIC_COURSE_AUTHOR_URL = "/v1/courses/{courseId}/authors";
  static final String PUBLIC_COURSE_CONTENT_URL = "/v1/courses/{courseId}/content";
}
