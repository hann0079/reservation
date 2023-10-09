package com.example.reservation.dto;

import com.example.reservation.model.User;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserLoginDto {

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {
        @NotNull
        @Email
        private String email;

        @NotNull
        @Size(min = 8, max = 128)
        private String password;

        @NotNull
        private String userType; // 'MANAGER' or 'CUSTOMER'
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        private String name;
        private String email;
        private String userType;

        public static Response from(User user) {
            return new Response(
                    user.getName(),
                    user.getEmail(),
                    user.getUserType()
            );
        }
    }
}
