package com.sda.carrental.reservation;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

record RentDTO(
        @NotNull Long employeeId,
        String comments,
        @NotNull LocalDate rentDate,
        @NotNull Long reservationId
) {
}
