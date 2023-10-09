package com.example.reservation.dto;

import com.example.reservation.model.Store;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StoreDto {
    private Long id;
    private String name;
    private String location;
    private String description;
    private Double rating;

    // Store 엔터티에서 DTO로 변환
    public static StoreDto fromEntity(Store store) {
        return StoreDto.builder()
                .id(store.getId())
                .name(store.getName())
                .location(store.getLocation())
                .description(store.getDescription())
                .rating(store.getRating())
                .build();
    }
}

