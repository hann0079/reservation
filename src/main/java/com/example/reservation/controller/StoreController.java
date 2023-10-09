package com.example.reservation.controller;

import com.example.reservation.dto.StoreDto;
import com.example.reservation.dto.StoreCreateDto;
import com.example.reservation.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/stores")
@RequiredArgsConstructor
public class StoreController {
    private final StoreService storeService;

    @PostMapping
    public StoreDto createStore(@RequestBody @Valid StoreCreateDto request) {
        return storeService.createStore(request);
    }

    @GetMapping("/name")
    public List<StoreDto> listStoresByName() {
        return storeService.listStoresByName();
    }

    @GetMapping("/rating")
    public List<StoreDto> listStoresByRating() {
        return storeService.listStoresByRating();
    }
}