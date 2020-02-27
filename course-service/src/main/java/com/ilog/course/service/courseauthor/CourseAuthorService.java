package com.ilog.course.service.courseauthor;

import com.ilog.course.dto.CourseAuthorDTO;
import com.ilog.course.exception.ForbiddenException;
import com.ilog.course.exception.PreConditionException;
import com.ilog.course.mapper.courseauthor.ICourseAuthorMapper;
import com.ilog.course.model.course.Course;
import com.ilog.course.model.courseauthor.CourseAuthor;
import com.ilog.course.model.user.User;
import com.ilog.course.repository.courseauthor.IcourseAuthorRepository;
import com.ilog.course.service.user.IUserService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseAuthorService implements ICourseAuthorService {

  private IUserService userService;
  private IcourseAuthorRepository courseAuthorRepository;
  private ICourseAuthorMapper courseAuthorMapper;

  @Autowired
  public CourseAuthorService(IUserService userService, ICourseAuthorMapper courseAuthorMapper,
      IcourseAuthorRepository courseAuthorRepository) {
    this.userService = userService;
    this.courseAuthorRepository = courseAuthorRepository;
    this.courseAuthorMapper = courseAuthorMapper;
  }

  @Override
  public CourseAuthor createAuthorFromLoggedUser(Course course) {
    User user = userService.getLoggedUser();
    return courseAuthorRepository.save(new CourseAuthor(course, user, Boolean.TRUE));
  }

  @Override
  public List<CourseAuthorDTO> getCourseAuthors(Long courseId) {
    return courseAuthorRepository.findAllByCourse_Id(courseId).stream()
        .map(courseAuthor -> courseAuthorMapper.toDTO(courseAuthor)).collect(Collectors.toList());
  }

  @Override
  public CourseAuthorDTO addAuthor(Long courseId, CourseAuthorDTO authorDTO) {
    User user = userService.getLoggedUser();
    Optional<CourseAuthor> courseAuthorOptional = courseAuthorRepository
        .findByCourse_IdAndAndAuthor_Username(courseId, user.getUsername());
    if (!courseAuthorOptional.isPresent() || !courseAuthorOptional.get().getIsMainAuthor()) {
      throw new ForbiddenException("You don't have permission for this action.");
    }

    if (courseAuthorRepository
        .findByCourse_IdAndAndAuthor_Username(courseId, authorDTO.getUsername()).isPresent()) {
      throw new PreConditionException("Author already on this course.");
    }

    Course course = courseAuthorOptional.get().getCourse();
    User author = userService.getUserByUsername(authorDTO.getUsername());
    return courseAuthorMapper
        .toDTO(courseAuthorRepository.save(new CourseAuthor(course, author, Boolean.FALSE)));
  }
}
