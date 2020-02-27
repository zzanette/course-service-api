package com.ilog.course.model.courseauthor;

import com.ilog.course.model.BaseModel;
import com.ilog.course.model.course.Course;
import com.ilog.course.model.user.User;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "course_author")
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class CourseAuthor extends BaseModel {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull
  @ManyToOne(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
  @JoinColumn(name = "course_id")
  private Course course;

  @NotNull
  @ManyToOne(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  private User author;

  @NotNull
  @Column(name = "is_main_author")
  private Boolean isMainAuthor;

  public CourseAuthor(Course course, User author, Boolean isMainAuthor) {
    this.course = course;
    this.author = author;
    this.isMainAuthor = isMainAuthor;
    this.isValid();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof CourseAuthor)) {
      return false;
    }
    CourseAuthor that = (CourseAuthor) o;
    return Objects.equals(id, that.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
