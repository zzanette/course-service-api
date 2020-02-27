package com.ilog.course.model.content;

import com.ilog.course.model.BaseModel;
import com.ilog.course.model.course.Course;
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
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "course_content")
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class CourseContent extends BaseModel {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank
  @Size(max = 200)
  @Column(name = "title")
  private String title;

  @Size(max = 255)
  @Column(name = "url_media_content")
  private String urlMediaContent;

  @Size(max = 255)
  @Column(name = "contentText")
  private String contentText;

  @NotNull
  @JoinColumn(name = "course_id", nullable = false)
  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
  private Course course;

  public CourseContent(String title, String urlMediaContent, String contentText, Course course) {
    this.title = title;
    this.urlMediaContent = urlMediaContent;
    this.contentText = contentText;
    this.course = course;
    this.isValid();
  }

  public CourseContent update(String title, String urlMediaContent, String contentText,
      Course course) {
    this.title = title;
    this.urlMediaContent = urlMediaContent;
    this.contentText = contentText;
    this.course = course;
    this.isValid();
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof CourseContent)) {
      return false;
    }
    CourseContent that = (CourseContent) o;
    return Objects.equals(id, that.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
