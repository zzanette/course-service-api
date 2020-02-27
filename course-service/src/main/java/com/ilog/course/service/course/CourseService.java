package com.ilog.course.service.course;

import com.ilog.course.dto.CourseDTO;
import com.ilog.course.exception.BussinessException;
import com.ilog.course.exception.ForbiddenException;
import com.ilog.course.exception.NotFoundException;
import com.ilog.course.mapper.course.ICourseMapper;
import com.ilog.course.model.course.Course;
import com.ilog.course.model.user.User;
import com.ilog.course.repository.course.ICourseRepository;
import com.ilog.course.repository.courseauthor.IcourseAuthorRepository;
import com.ilog.course.service.courseauthor.ICourseAuthorService;
import com.ilog.course.service.messageproperties.MessageProperties;
import com.ilog.course.service.messageproperties.MessagePropertiesService;
import com.ilog.course.service.user.IUserService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseService implements ICourseService {

  private ICourseRepository courseRepository;
  private ICourseMapper courseMapper;
  private ICourseAuthorService courseAuthorService;
  private IUserService userService;
  private IcourseAuthorRepository courseAuthorRepository;

  @Autowired
  public CourseService(ICourseRepository courseRepository, ICourseMapper courseMapper,
      ICourseAuthorService courseAuthorService, IUserService userService,
      IcourseAuthorRepository courseAuthorRepository) {
    this.courseRepository = courseRepository;
    this.courseMapper = courseMapper;
    this.courseAuthorService = courseAuthorService;
    this.userService = userService;
    this.courseAuthorRepository = courseAuthorRepository;
  }

  @Override
  public CourseDTO create(CourseDTO dto) {
    if (courseRepository.findByTitle(dto.getTitle()).isPresent()) {
      throw new BussinessException(
          MessagePropertiesService.getMessage(MessageProperties.COURSE_TITLE_ALREADY_EXISTS));
    }
    Course course = courseRepository.save(courseMapper.fromDTO(dto));
    course.getAuthors().add(courseAuthorService.createAuthorFromLoggedUser(course));
    return courseMapper.toDTO(course);
  }

  @Override
  public CourseDTO update(Long courseId, CourseDTO dto) {
    validateLoggedUserPermission(courseId);
    Course course = courseRepository.findById(courseId).orElseThrow(() -> new NotFoundException(
        MessagePropertiesService.getMessage(MessageProperties.COURSE_NOT_FOUND)));
    return courseMapper.toDTO(courseRepository.save(
        course.update(dto.getTitle(), dto.getDescription(), dto.getIsActive(), dto.getPrice())));
  }

  @Override
  public List<CourseDTO> getAllActive() {
    return courseRepository.findAllByIsActive(Boolean.TRUE).stream()
        .map(course -> courseMapper.toDTO(course)).collect(Collectors.toList());
  }

  private void validateLoggedUserPermission(Long courseId) {
    User loggedUser = userService.getLoggedUser();
    courseAuthorRepository.findByCourse_IdAndAndAuthor_Username(courseId, loggedUser.getUsername())
        .orElseThrow(() -> new ForbiddenException("Not allowed."));
  }
}
