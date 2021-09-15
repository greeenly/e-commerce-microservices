package me.greeenly.userservice.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.greeenly.userservice.vo.RequestLogin;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {

        try {
            // 로그인 값은 post로 전달되는데, post로 전달되는건 request parameter 로 받을 수 없기 때문에,
            // inputStream 을 이용하여 해당하는 값들을 사용할 수 있게 한다.
            RequestLogin creds = new ObjectMapper().readValue(request.getInputStream(), RequestLogin.class);

            // 토큰으로 바꿔서 인증을 처리해주는 매니저에 넘긴다.
            return getAuthenticationManager().authenticate(
                    new UsernamePasswordAuthenticationToken(
                            creds.getEmail(),
                            creds.getPassword(),
                            new ArrayList<>()
                    )
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // 실제 로그인 성공 후, 어떤 처리를 해줄건지
    // 토큰을 만든다면 만료시간, 사용자가 로그인 했을때, 반환값 설정 등등 ..
    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
    }
}
