package com.ilog.course.service.user;

import com.ilog.course.dto.UserDTO;
import com.ilog.course.model.user.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface IUserService extends UserDetailsService {

  UserDTO create(UserDTO dto);

  User getLoggedUser();

  User getUserByUsername(String username) throws UsernameNotFoundException;
}
