package com.example.reservation.repository;

import com.example.reservation.model.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StoreRepository extends JpaRepository<Store, Long> {
    List<Store> findAllByOrderByRatingDesc();
    List<Store> findAllByOrderByNameAsc();
}