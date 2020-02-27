CREATE TABLE course_author (
  id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  course_id BIGINT NOT NULL,
  user_id BIGINT NOT NULL,
  is_main_author BOOLEAN NOT NULL,
  FOREIGN KEY (course_id) REFERENCES course(id),
  FOREIGN KEY (user_id) REFERENCES user(id)
);