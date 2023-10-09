package com.example.reservation.service;

import com.example.reservation.dto.ReservationDto;
import com.example.reservation.model.Reservation;
import com.example.reservation.model.Store;
import com.example.reservation.model.User;
import com.example.reservation.model.enums.ReservationStatus;
import com.example.reservation.repository.ReservationRepository;
import com.example.reservation.repository.StoreRepository;
import com.example.reservation.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final StoreRepository storeRepository;
    private final UserRepository userRepository;

    public boolean checkAvailability(ReservationDto.CheckAvailabilityRequest request) {
        List<Reservation> reservations = reservationRepository.findByStoreIdAndReservationTimeBetween(
                request.getStoreId(),
                request.getDesiredReservationTime().minusHours(1),
                request.getDesiredReservationTime().plusHours(1)
        );
        return reservations.isEmpty();
    }

    public Reservation makeReservation(ReservationDto.MakeReservationRequest request) {
        Store store = storeRepository.findById(request.getStoreId())
                .orElseThrow(() -> new RuntimeException("매장 정보를 조회할 수 없습니다."));
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("사용자 정보를 조회할 수 없습니다."));

        Reservation reservation = new Reservation();
        reservation.setStore(store);
        reservation.setUser(user);
        reservation.setReservationTime(request.getReservationTime());
        return reservationRepository.save(reservation);
    }

    public void checkArrival(ReservationDto.ArrivalCheckRequest request) {
        Reservation reservation = reservationRepository.findById(request.getReservationId())
                .orElseThrow(() -> new RuntimeException("예약 정보를 조회할 수 없습니다."));
        reservation.setArrived(true);
        reservationRepository.save(reservation);
    }

    public void updateReservationStatus(Long reservationId, ReservationStatus status) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new RuntimeException("예약 정보를 조회할 수 없습니다."));
        reservation.setStatus(status);
        reservationRepository.save(reservation);
    }
}