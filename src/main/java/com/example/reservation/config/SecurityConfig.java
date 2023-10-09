package com.example.reservation.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // rest api는 외부에서 사용하기 때문에 그에 대한 권한 설정 - 외부 호출 가능
        http.csrf().disable();
        http.headers().frameOptions().sameOrigin();

        // 점장만 예약 취소, 승인 가능 엑세스 허용
        http.authorizeRequests()
//                .antMatchers("/api/reservations/approve/**", "/api/reservations/reject/**")
//                .hasAuthority("MANAGER")
                .anyRequest().permitAll();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
