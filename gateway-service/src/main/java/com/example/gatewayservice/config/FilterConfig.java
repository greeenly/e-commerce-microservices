package com.example.gatewayservice.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class FilterConfig {

//    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        // 해당 빌더를 통해서 yml에 정의한 내용을 자바 코드로 작성할 수 있다.
        return builder.routes()
                .route(r -> r
                        .path("/first-service/**")
                        .filters(f -> f
                                .addRequestHeader("first-request", "first-request-header")
                                .addResponseHeader("first-response", "first-response-header"))
                        .uri("http://localhost:8081"))
                .route(r -> r
                        .path("/second-service/**")
                        .filters(f -> f
                                .addRequestHeader("second-request", "second-request-header")
                                .addResponseHeader("second-response", "second-response-header"))
                        .uri("http://localhost:8082"))
                .build();

        /*
            1. r.path(~) 를 통해서 라우터를 하나 등록한다.
            2. filter를 어떻게 사용할 것인지 정의를 한다.
            3. 그 라우터가 완성이 되면 uri(~) 부분으로 이동을 한다.

         */
    }
}
