package com.ilog.course.schedule;

import com.ilog.course.model.user.User;
import com.ilog.course.repository.user.IUserRepository;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableAsync
public class UserActivationSchedule {

  private IUserRepository userRepository;

  @Autowired
  public UserActivationSchedule(IUserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Async
  @Scheduled(fixedRate = 60000)
  public void scheduleFixedRateTaskAsync() {
    CompletableFuture.runAsync(() -> {
      List<User> users = userRepository.findAllByIsEnabledTrueAndIsAccountNonLockedFalse().stream()
          .map(User::unLockAccount).collect(Collectors.toList());
      if (!users.isEmpty()) {
        userRepository.saveAll(users);
      }
    });
  }
}
