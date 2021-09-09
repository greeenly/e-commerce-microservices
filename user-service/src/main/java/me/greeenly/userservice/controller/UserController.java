package me.greeenly.userservice.controller;

import lombok.RequiredArgsConstructor;
import me.greeenly.userservice.domain.User;
import me.greeenly.userservice.dto.UserDto;
import me.greeenly.userservice.service.UserService;
import me.greeenly.userservice.vo.Greeting;
import me.greeenly.userservice.vo.RequestUser;
import me.greeenly.userservice.vo.ResponseUser;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user-service")
@RequiredArgsConstructor
public class UserController {

    // Env 객체를 사용하여 환경설정 파일의 값 가져오기
    private final Environment env;
    private final UserService userService;

    // Value 어노테이션을 활용한 환경설정 파일의 값 가져오기
    private final Greeting greeting;

    // 상태체크
    @GetMapping("/health_check")
    public String status() {
        return String.format("It's Working in User Service on PORT %s"
                , env.getProperty("local.server.port"));
    }

    @GetMapping("/welcome")
    public String welcome() {
//        return env.getProperty("greeting.message");
        return greeting.getMessage();
    }

    @PostMapping("/users")
    public ResponseEntity<ResponseUser> createUser(@RequestBody RequestUser requestUser) {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        UserDto userDto = mapper.map(requestUser, UserDto.class);
        userService.createUser(userDto);

        // userDto -> responseUser
        ResponseUser responseUser = mapper.map(userDto, ResponseUser.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseUser);
    }

    @GetMapping("/users")
    public ResponseEntity<List<ResponseUser>> getUsers() {
        Iterable<User> userList = userService.getUserByAll();

        List<ResponseUser> result = new ArrayList<>();
        userList.forEach(v -> {
            result.add(new ModelMapper().map(v, ResponseUser.class));
        });

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<ResponseUser> getUser(@PathVariable String userId) {

        UserDto userDto = userService.getUserByUserId(userId);
        ResponseUser returnValue = new ModelMapper().map(userDto, ResponseUser.class);

        return ResponseEntity.status(HttpStatus.OK).body(returnValue);
    }
}
