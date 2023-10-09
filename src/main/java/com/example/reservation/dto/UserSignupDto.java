package com.example.reservation.dto;

import com.example.reservation.model.User;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserSignupDto {

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {
        @NotNull
        @Size(min = 2, max = 50)
        private String name;

        @NotNull
        @Size(min = 8, max = 128)
        private String password;

        @NotNull
        private String userType; // 'MANAGER' or 'CUSTOMER'

        @NotNull
        @Email
        private String email;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Response {
        private Long id;
        private String name;
        private String userType;
        private String email;

        public static Response from(User user) {
            return Response.builder()
                    .id(user.getId())
                    .name(user.getName())
                    .userType(user.getUserType())
                    .email(user.getEmail())
                    .build();
        }
    }
}
