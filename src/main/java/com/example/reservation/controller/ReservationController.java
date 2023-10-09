package com.example.reservation.controller;

import com.example.reservation.dto.ReservationDto;
import com.example.reservation.model.Reservation;
import com.example.reservation.model.enums.ReservationStatus;
import com.example.reservation.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping("/check")
    public boolean checkAvailability(@RequestBody @Valid ReservationDto.CheckAvailabilityRequest request) {
        return reservationService.checkAvailability(request);
    }

    @PostMapping
    public Reservation makeReservation(@RequestBody @Valid ReservationDto.MakeReservationRequest request) {
        return reservationService.makeReservation(request);
    }

    @PutMapping("/arrival")
    public void checkArrival(@RequestBody @Valid ReservationDto.ArrivalCheckRequest request) {
        reservationService.checkArrival(request);
    }

    @PutMapping("/approve/{id}")
    public void approveReservation(@PathVariable Long id) {
        reservationService.updateReservationStatus(id, ReservationStatus.APPROVED);
    }

    @PutMapping("/reject/{id}")
    public void rejectReservation(@PathVariable Long id) {
        reservationService.updateReservationStatus(id, ReservationStatus.REJECTED);
    }
}