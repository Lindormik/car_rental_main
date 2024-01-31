package com.sda.carrental.reservation;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public List<ReservationModel> getAll() {
        return service.getAll();
    }
}
