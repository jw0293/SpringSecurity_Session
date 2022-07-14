package com.example.security.springsecurity.config.security;

import com.example.security.springsecurity.app.user.model.UserDetailVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@RequiredArgsConstructor
@Log4j2
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final UserDetailsService userDetailsService;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;

        // AuthenticationFilter에서 생성된 토큰으로부터 아이디와 비밀번호 조회
        String userEmail = token.getName();
        String userPw = (String) token.getCredentials();

        // UserDetailsService를 통해 DB에서 아이디로 사용자 조회
        UserDetailVO userDetailVo = (UserDetailVO) userDetailsService.loadUserByUsername(userEmail);

        if(!passwordEncoder.matches(userPw, userDetailVo.getPassword())){
            throw new BadCredentialsException(userDetailVo.getUsername() + " Invalid Password");
        }

        return new UsernamePasswordAuthenticationToken(userDetailVo, userPw, userDetailVo.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
