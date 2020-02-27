package com.ilog.course.service.course;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.ilog.course.dto.CourseDTO;
import com.ilog.course.exception.BussinessException;
import com.ilog.course.mapper.course.ICourseMapper;
import com.ilog.course.model.course.Course;
import com.ilog.course.model.courseauthor.CourseAuthor;
import com.ilog.course.repository.course.ICourseRepository;
import com.ilog.course.service.courseauthor.ICourseAuthorService;
import com.ilog.course.service.messageproperties.MessageProperties;
import com.ilog.course.service.messageproperties.MessagePropertiesService;
import java.math.BigDecimal;
import java.util.Optional;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CourseServiceTest {

  @Rule
  public ExpectedException expectedException = ExpectedException.none();

  @InjectMocks
  private CourseService service;

  @Mock
  private ICourseRepository courseRepository;

  @Mock
  private ICourseMapper courseMapper;

  @Mock
  private ICourseAuthorService courseAuthorService;

  private CourseDTO dto;
  private Course course;

  @Before
  public void setUp() {
    this.dto = new CourseDTO(1L, "My title course", "description", BigDecimal.TEN, Boolean.TRUE);
    this.course = new Course("My title course", "description", Boolean.TRUE, BigDecimal.TEN);
  }

  @Test
  public void shouldThrowBussinessException() {
    expectedException.expect(BussinessException.class);
    expectedException.expectMessage(
        MessagePropertiesService.getMessage(MessageProperties.COURSE_TITLE_ALREADY_EXISTS));
    when(courseRepository.findByTitle(anyString()))
        .thenReturn(Optional.of(Mockito.mock(Course.class)));
    service.create(dto);
  }

  @Test
  public void shouldCreateCourse() {
    when(courseRepository.findByTitle(anyString()))
        .thenReturn(Optional.empty());
    when(courseMapper.fromDTO(any())).thenReturn(course);
    when(courseRepository.save(any())).thenReturn(course);
    when(courseMapper.toDTO(any())).thenReturn(dto);
    when(courseAuthorService.createAuthorFromLoggedUser(any()))
        .thenReturn(mock(CourseAuthor.class));
    CourseDTO dto = service.create(this.dto);

    Assert.assertEquals(this.dto.getTitle(), dto.getTitle());
    Assert.assertEquals(this.dto.getDescription(), dto.getDescription());
    Assert.assertEquals(this.dto.getIsActive(), dto.getIsActive());
    Assert.assertEquals(this.dto.getPrice(), dto.getPrice());
  }
}