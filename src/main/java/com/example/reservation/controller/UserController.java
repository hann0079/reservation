package com.example.reservation.controller;

import com.example.reservation.dto.UserLoginDto;
import com.example.reservation.dto.UserSignupDto;
import com.example.reservation.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public UserSignupDto.Response signup(
            @RequestBody @Valid UserSignupDto.Request request
    ) {
        return userService.registerUser(request);
    }

    @PostMapping("/login")
    public UserLoginDto.Response login(@RequestBody @Valid UserLoginDto.Request request) {
        return userService.loginUser(request);
    }

}