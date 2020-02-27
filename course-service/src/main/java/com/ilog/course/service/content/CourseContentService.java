package com.ilog.course.service.content;

import com.ilog.course.dto.CourseContentDTO;
import com.ilog.course.exception.ForbiddenException;
import com.ilog.course.exception.NotFoundException;
import com.ilog.course.mapper.content.ICourseContentMapper;
import com.ilog.course.model.content.CourseContent;
import com.ilog.course.model.course.Course;
import com.ilog.course.model.user.User;
import com.ilog.course.repository.content.ICourseContentRepository;
import com.ilog.course.repository.course.ICourseRepository;
import com.ilog.course.repository.courseauthor.IcourseAuthorRepository;
import com.ilog.course.service.messageproperties.MessageProperties;
import com.ilog.course.service.messageproperties.MessagePropertiesService;
import com.ilog.course.service.user.IUserService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseContentService implements ICourseContentService {

  private ICourseRepository courseRepository;
  private ICourseContentMapper courseContentMapper;
  private ICourseContentRepository courseContentRepository;
  private IUserService userService;
  private IcourseAuthorRepository courseAuthorRepository;

  @Autowired
  public CourseContentService(ICourseRepository courseRepository, IUserService userService,
      ICourseContentMapper courseContentMapper, ICourseContentRepository courseContentRepository,
      IcourseAuthorRepository courseAuthorRepository) {
    this.courseRepository = courseRepository;
    this.courseContentMapper = courseContentMapper;
    this.courseContentRepository = courseContentRepository;
    this.userService = userService;
    this.courseAuthorRepository = courseAuthorRepository;
  }

  @Override
  public CourseContentDTO create(Long courseId, CourseContentDTO dto) {
    validateLoggedUserPermission(courseId);
    Course course = courseRepository.findById(courseId).orElseThrow(() -> new NotFoundException(
        MessagePropertiesService.getMessage(MessageProperties.COURSE_NOT_FOUND)));
    CourseContent courseContent = courseContentRepository
        .save(courseContentMapper.fromDTO(dto, course));

    return courseContentMapper.toDTO(courseContent);
  }

  @Override
  public CourseContentDTO update(Long courseId, Long courseContentId, CourseContentDTO dto) {
    validateLoggedUserPermission(courseId);
    Course course = courseRepository.findById(courseId)
        .orElseThrow(() -> new NotFoundException(MessagePropertiesService.getMessage(
            MessageProperties.COURSE_NOT_FOUND)));
    CourseContent content = courseContentRepository.findById(courseContentId).orElseThrow(
        () -> new NotFoundException(
            MessagePropertiesService.getMessage(MessageProperties.COURSE_CONTENT_NOT_FOUND)))
        .update(dto.getTitle(), dto.getUrlMediaContent(), dto.getContentText(), course);

    return courseContentMapper.toDTO(courseContentRepository.save(content));
  }

  @Override
  public List<CourseContentDTO> getAllActive(Long courseId) {
    return courseContentRepository.findAllByCourse_IdAndCourse_IsActive(courseId, Boolean.TRUE)
        .stream().map(courseContent -> courseContentMapper.toDTO(courseContent))
        .collect(Collectors.toList());
  }

  private void validateLoggedUserPermission(Long courseId) {
    User loggedUser = userService.getLoggedUser();
    courseAuthorRepository.findByCourse_IdAndAndAuthor_Username(courseId, loggedUser.getUsername())
        .orElseThrow(() -> new ForbiddenException("Not allowed."));
  }
}
