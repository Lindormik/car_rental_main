package com.sda.carrental.car_return;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record ReturnDTO(
        @NotNull Long employeeId,
        @NotNull Long reservationId,
        @NotNull LocalDate returnDate,
        int additionalFee,
        String comments
) {
}
