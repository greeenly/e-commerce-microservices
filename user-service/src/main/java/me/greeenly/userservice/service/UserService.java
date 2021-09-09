package me.greeenly.userservice.service;

import me.greeenly.userservice.domain.User;
import me.greeenly.userservice.dto.UserDto;

public interface UserService {

    UserDto createUser(UserDto userDto);

    UserDto getUserByUserId(String userId);
    Iterable<User> getUserByAll();

}
