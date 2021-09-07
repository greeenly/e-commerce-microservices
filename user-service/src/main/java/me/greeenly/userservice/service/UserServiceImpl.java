package me.greeenly.userservice.service;

import lombok.RequiredArgsConstructor;
import me.greeenly.userservice.domain.User;
import me.greeenly.userservice.dto.UserDto;
import me.greeenly.userservice.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserDto createUser(UserDto userDto) {
        userDto.setUserId(UUID.randomUUID().toString());

        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        User user = mapper.map(userDto, User.class);
        user.setEncryptedPwd("encrypted_password");

        userRepository.save(user);

        UserDto returnUserDto = mapper.map(user, UserDto.class);

        return returnUserDto;
    }


}
