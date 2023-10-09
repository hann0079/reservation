package com.example.reservation.service;

import com.example.reservation.dto.ReviewDto;
import com.example.reservation.model.Review;
import com.example.reservation.model.Store;
import com.example.reservation.model.User;
import com.example.reservation.repository.ReviewRepository;
import com.example.reservation.repository.StoreRepository;
import com.example.reservation.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final StoreRepository storeRepository;
    private final UserRepository userRepository;

    @Transactional
    public ReviewDto.Response writeReview(ReviewDto.CreateRequest request) {
        Review review = new Review();

        // Store와 User 객체를 가져와 Review에 설정
        Store store = storeRepository.findById(request.getStoreId())
                .orElseThrow(() -> new RuntimeException("매장 정보를 조회할 수 없습니다."));
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("사용자 정보를 조회할 수 없습니다."));

        review.setUser(user);
        review.setStore(store);
        review.setContent(request.getContent());
        review.setRating(request.getRating());

        Review savedReview = reviewRepository.save(review);

        // 해당 매장의 평균 별점을 다시 계산
        updateStoreRating(store.getId());

        return ReviewDto.Response.builder()
                .id(savedReview.getId())
                .userId(savedReview.getUser().getId())
                .storeId(savedReview.getStore().getId())
                .content(savedReview.getContent())
                .rating(savedReview.getRating())
                .build();
    }

    private void updateStoreRating(Long storeId) {
        Double averageRating = reviewRepository.findAverageRatingByStoreId(storeId);
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new RuntimeException("매장 정보를 조회할 수 없습니다."));
        store.setRating(averageRating);
        storeRepository.save(store);
    }
}