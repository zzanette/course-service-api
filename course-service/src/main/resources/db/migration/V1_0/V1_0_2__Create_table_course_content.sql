CREATE TABLE course_content(
  id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  title VARCHAR(200) NOT NULL,
  url_media_content VARCHAR(255),
  content_text VARCHAR(255),
  course_id BIGINT NOT NULL,

  FOREIGN KEY (course_id) REFERENCES course(id)
);