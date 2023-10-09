package com.example.reservation.service;

import com.example.reservation.dto.UserLoginDto;
import com.example.reservation.model.User;
import com.example.reservation.repository.UserRepository;
import com.example.reservation.dto.UserSignupDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;



@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserSignupDto.Response registerUser(UserSignupDto.Request request) {
        if(userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("이미 존재하는 이메일입니다.");
        }

        User user = User.builder()
                .name(request.getName())
                .password(passwordEncoder.encode(request.getPassword()))
                .userType(request.getUserType())
                .email(request.getEmail())
                .build();

        return UserSignupDto.Response.from(userRepository.save(user));
    }

    public UserLoginDto.Response loginUser(UserLoginDto.Request request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("사용자 정보를 조회할 수 없습니다."));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("비밀번호가 일치하지 않습니다.");
        }

        if (!request.getUserType().equals(user.getUserType())) {
            throw new RuntimeException("사용자 타입이 일치하지 않습니다.");
        }

        return UserLoginDto.Response.from(user);
    }
}
