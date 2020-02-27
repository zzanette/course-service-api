package com.ilog.course.service.content;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.ilog.course.dto.CourseContentDTO;
import com.ilog.course.exception.NotFoundException;
import com.ilog.course.mapper.content.ICourseContentMapper;
import com.ilog.course.model.content.CourseContent;
import com.ilog.course.model.course.Course;
import com.ilog.course.model.courseauthor.CourseAuthor;
import com.ilog.course.model.user.User;
import com.ilog.course.repository.content.ICourseContentRepository;
import com.ilog.course.repository.course.ICourseRepository;
import com.ilog.course.repository.courseauthor.IcourseAuthorRepository;
import com.ilog.course.service.messageproperties.MessageProperties;
import com.ilog.course.service.messageproperties.MessagePropertiesService;
import com.ilog.course.service.user.IUserService;
import java.util.Optional;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CourseContentServiceTest {

  @Rule
  public ExpectedException expectedException = ExpectedException.none();

  @InjectMocks
  private CourseContentService service;

  @Mock
  private ICourseRepository courseRepository;

  @Mock
  private ICourseContentMapper courseContentMapper;

  @Mock
  private ICourseContentRepository courseContentRepository;

  @Mock
  private IUserService userService;

  @Mock
  private IcourseAuthorRepository courseAuthorRepository;

  @Test
  public void shouldThrowNotFoundException() {
    expectedException.expect(NotFoundException.class);
    expectedException
        .expectMessage(MessagePropertiesService.getMessage(MessageProperties.COURSE_NOT_FOUND));
    when(courseRepository.findById(anyLong())).thenReturn(Optional.empty());
    when(userService.getLoggedUser()).thenReturn(mock(User.class));
    when(courseAuthorRepository.findByCourse_IdAndAndAuthor_Username(any(), any()))
        .thenReturn(Optional.of(mock(CourseAuthor.class)));
    service.create(1L, mock(CourseContentDTO.class));
  }

  @Test
  public void shouldCreateContent() {
    CourseContentDTO dto = new CourseContentDTO(null, "Title", "media", "text content");
    when(courseRepository.findById(anyLong())).thenReturn(Optional.of(mock(Course.class)));
    when(courseContentMapper.fromDTO(any(), any())).thenReturn(mock(CourseContent.class));
    when(courseContentRepository.save(any())).thenReturn(mock(CourseContent.class));
    when(courseContentMapper.toDTO(any())).thenReturn(dto);
    when(userService.getLoggedUser()).thenReturn(mock(User.class));
    when(courseAuthorRepository.findByCourse_IdAndAndAuthor_Username(any(), any()))
        .thenReturn(Optional.of(mock(CourseAuthor.class)));

    CourseContentDTO created = service.create(1L, dto);
    Assert.assertEquals(dto.getTitle(), created.getTitle());
    Assert.assertEquals(dto.getContentText(), created.getContentText());
    Assert.assertEquals(dto.getUrlMediaContent(), created.getUrlMediaContent());
  }

  @Test
  public void shouldThrowCourseNotFound() {
    expectedException.expect(NotFoundException.class);
    expectedException
        .expectMessage(MessagePropertiesService.getMessage(MessageProperties.COURSE_NOT_FOUND));
    when(courseRepository.findById(anyLong())).thenReturn(Optional.empty());
    when(userService.getLoggedUser()).thenReturn(mock(User.class));
    when(courseAuthorRepository.findByCourse_IdAndAndAuthor_Username(any(), any()))
        .thenReturn(Optional.of(mock(CourseAuthor.class)));
    service.update(1L, 1L, mock(CourseContentDTO.class));
  }

  @Test
  public void shouldThrowCourseContentNotFound() {
    expectedException.expect(NotFoundException.class);
    expectedException.expectMessage(
        MessagePropertiesService.getMessage(MessageProperties.COURSE_CONTENT_NOT_FOUND));
    when(courseRepository.findById(anyLong())).thenReturn(Optional.of(mock(Course.class)));
    when(courseContentRepository.findById(anyLong())).thenReturn(Optional.empty());
    when(userService.getLoggedUser()).thenReturn(mock(User.class));
    when(courseAuthorRepository.findByCourse_IdAndAndAuthor_Username(any(), any()))
        .thenReturn(Optional.of(mock(CourseAuthor.class)));
    service.update(1L, 1L, mock(CourseContentDTO.class));
  }
}