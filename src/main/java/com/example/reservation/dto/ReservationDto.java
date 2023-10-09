package com.example.reservation.dto;

import com.example.reservation.model.enums.ReservationStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class ReservationDto {

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CheckAvailabilityRequest {
        @NotNull
        private Long storeId;
        private LocalDateTime desiredReservationTime;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MakeReservationRequest {
        @NotNull
        private Long storeId;

        @NotNull
        private Long userId;
        private LocalDateTime reservationTime;

        // 추가된 예약 상태 필드
        private ReservationStatus status = ReservationStatus.PENDING;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ArrivalCheckRequest {
        @NotNull
        private Long reservationId;
    }

    // 예약 승인 혹은 거절을 위한 요청 DTO
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UpdateStatusRequest {
        @NotNull
        private Long reservationId;

        @NotNull
        private ReservationStatus status;
    }
}
