package com.ilog.course.repository.user;

import com.ilog.course.model.user.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User, Long> {

  Optional<User> findByUsernameIgnoreCase(String username);

  Optional<User> findByEmail(String email);

  List<User> findAllByIsEnabledTrueAndIsAccountNonLockedFalse();
}
