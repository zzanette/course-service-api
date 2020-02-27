package com.ilog.course.mapper.user;

import com.ilog.course.dto.UserDTO;
import com.ilog.course.model.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IUserMapper {

  default User fromDTO(UserDTO dto) {
    if (dto == null) {
      return null;
    }

    return new User(dto.getFullName(), dto.getEmail(), dto.getUsername(), dto.getPassword());
  }

  @Mapping(source = "fullName", target = "fullName")
  @Mapping(source = "email", target = "email")
  @Mapping(source = "username", target = "username")
  @Mapping(target = "password", ignore = true)
  UserDTO toDTO(User user);
}
