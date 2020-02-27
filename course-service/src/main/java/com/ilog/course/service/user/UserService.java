package com.ilog.course.service.user;

import com.ilog.course.dto.UserDTO;
import com.ilog.course.exception.PreConditionException;
import com.ilog.course.exception.UnAuthorizedException;
import com.ilog.course.mapper.user.IUserMapper;
import com.ilog.course.model.user.User;
import com.ilog.course.repository.user.IUserRepository;
import com.ilog.course.service.messageproperties.MessageProperties;
import com.ilog.course.service.messageproperties.MessagePropertiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

  private BCryptPasswordEncoder passwordEncoder;
  private IUserMapper userMapper;
  private IUserRepository userRepository;

  @Autowired
  public UserService(BCryptPasswordEncoder passwordEncoder, IUserMapper userMapper,
      IUserRepository userRepository) {
    this.passwordEncoder = passwordEncoder;
    this.userMapper = userMapper;
    this.userRepository = userRepository;
  }

  @Override
  public UserDTO create(UserDTO dto) {
    dto.setPassword(passwordEncoder.encode(dto.getPassword()));
    if (userRepository.findByUsernameIgnoreCase(dto.getUsername()).isPresent()) {
      throw new PreConditionException(
          MessagePropertiesService.getMessage(MessageProperties.USERNAME_ALREADY_EXISTS));
    }

    if (userRepository.findByEmail(dto.getEmail()).isPresent()) {
      throw new PreConditionException(
          MessagePropertiesService.getMessage(MessageProperties.EMAIL_ALREADY_EXISTS));
    }

    User user = userRepository.save(userMapper.fromDTO(dto));
    return userMapper.toDTO(user);
  }

  @Override
  public User getLoggedUser() {
    String username = (String) SecurityContextHolder.getContext().getAuthentication()
        .getPrincipal();
    return userRepository.findByUsernameIgnoreCase(username)
        .orElseThrow(() -> new UnAuthorizedException("Not allowed to search user."));
  }

  @Override
  public User getUserByUsername(String username) throws UsernameNotFoundException {
    return userRepository.findByUsernameIgnoreCase(username)
        .orElseThrow(() -> new UsernameNotFoundException(
            MessagePropertiesService.getMessage(MessageProperties.USER_NOT_FOUND)));
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return getUserByUsername(username);
  }
}
