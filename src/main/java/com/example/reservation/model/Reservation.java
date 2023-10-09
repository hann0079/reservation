package com.example.reservation.model;

import com.example.reservation.model.enums.ReservationStatus;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Store store;

    private LocalDateTime reservationTime;
    private boolean isArrived;

    // 예약 승인 상태 필드: PENDING, APPROVED, REJECTED
    @Enumerated(EnumType.STRING)
    private ReservationStatus status = ReservationStatus.PENDING;
}

