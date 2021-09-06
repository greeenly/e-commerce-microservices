package me.greeenly.userservice.controller;

import lombok.RequiredArgsConstructor;
import me.greeenly.userservice.vo.Greeting;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class UserController {

    // Env 객체를 사용하여 환경설정 파일의 값 가져오기
    private final Environment env;

    // Value 어노테이션을 활용한 환경설정 파일의 값 가져오기
    private final Greeting greeting;

    // 상태체크
    @GetMapping("/health_check")
    public String status() {
        return "It's Working in User Service";
    }

    @GetMapping("/welcome")
    public String welcome() {
//        return env.getProperty("greeting.message");
        return greeting.getMessage();
    }

}
