package com.example.reservation.controller;

import com.example.reservation.dto.ReviewDto;
import com.example.reservation.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping
    public ReviewDto.Response writeReview(@RequestBody @Valid ReviewDto.CreateRequest request) {
        return reviewService.writeReview(request);
    }
}