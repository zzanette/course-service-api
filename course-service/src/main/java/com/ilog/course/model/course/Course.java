package com.ilog.course.model.course;

import com.ilog.course.model.BaseModel;
import com.ilog.course.model.courseauthor.CourseAuthor;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "course")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Course extends BaseModel {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank
  @Column(name = "title")
  @Size(min = 5, max = 200)
  private String title;

  @Size(max = 255)
  @Column(name = "description")
  private String description;

  @NotNull
  @Column(name = "is_active")
  private Boolean isActive;

  @NotNull
  @Min(value = 0)
  @Column(name = "price")
  private BigDecimal price;

  @NotNull
  @OneToMany(mappedBy = "course")
  private List<CourseAuthor> authors;

  public Course(String title, String description, Boolean isActive, BigDecimal price) {
    this.title = title;
    this.description = description;
    this.isActive = isActive;
    this.price = price;
    this.authors = new ArrayList<>();
    this.isValid();
  }

  public Course update(String title, String description, Boolean isActive, BigDecimal price) {
    this.title = title;
    this.description = description;
    this.isActive = isActive;
    this.price = price;
    this.isValid();
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Course)) {
      return false;
    }
    Course course = (Course) o;
    return Objects.equals(id, course.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
