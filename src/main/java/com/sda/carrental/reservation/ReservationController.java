package com.sda.carrental.reservation;

import com.sda.carrental.car_rental_facility.CarRentalModel;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    private final ReservationService service;

    public ReservationController(ReservationService service) {
        this.service = service;
    }

    @PostMapping
    public ReservationModel save(@RequestBody @Valid ReservationDTO reservation) {
        return service.saveReservation(reservation);
    }

    @GetMapping("/{id}")
    public ReservationModel getById(@PathVariable Long id) {
        return service.getById(id);
    }
}
