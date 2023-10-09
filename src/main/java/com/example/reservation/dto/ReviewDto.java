package com.example.reservation.dto;

import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ReviewDto {

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateRequest {
        @NotNull
        private Long userId;

        @NotNull
        private Long storeId;

        @NotNull
        @Size(max = 500)
        private String content;

        @NotNull
        @Min(1)
        @Max(5)
        private int rating;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Response {
        private Long id;
        private Long userId;
        private Long storeId;
        private String content;
        private int rating;
    }
}