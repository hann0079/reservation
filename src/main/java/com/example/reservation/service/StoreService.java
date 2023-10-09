package com.example.reservation.service;

import com.example.reservation.model.Store;
import com.example.reservation.model.User;
import com.example.reservation.repository.StoreRepository;
import com.example.reservation.repository.UserRepository;
import com.example.reservation.dto.StoreDto;
import com.example.reservation.dto.StoreCreateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StoreService {
    private final StoreRepository storeRepository;
    private final UserRepository userRepository;

    public StoreDto createStore(StoreCreateDto dto) {
        User manager = userRepository.findById(dto.getManagerId())
                .orElseThrow(() -> new RuntimeException("점장 정보를 조회할 수 없습니다."));

        if (!"MANAGER".equals(manager.getUserType())) {
            throw new RuntimeException("점장만 매장을 등록할 수 있습니다.");
        }

        Store store = Store.builder()
                .name(dto.getName())
                .location(dto.getLocation())
                .description(dto.getDescription())
                .manager(manager)
                .build();

        return StoreDto.fromEntity(storeRepository.save(store));
    }

    public List<StoreDto> listStoresByName() {
        return storeRepository.findAllByOrderByNameAsc().stream()
                .map(StoreDto::fromEntity)
                .collect(Collectors.toList());
    }

    public List<StoreDto> listStoresByRating() {
        return storeRepository.findAllByOrderByRatingDesc().stream()
                .map(StoreDto::fromEntity)
                .collect(Collectors.toList());
    }

}
