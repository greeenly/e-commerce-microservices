package me.greeenly.userservice.service;

import me.greeenly.userservice.domain.User;
import me.greeenly.userservice.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    UserDto createUser(UserDto userDto);

    UserDto getUserByUserId(String userId);

    Iterable<User> getUserByAll();

    UserDto getUserDetailsByEmail(String username);
}
