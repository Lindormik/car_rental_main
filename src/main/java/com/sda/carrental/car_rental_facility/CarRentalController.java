package com.sda.carrental.car_rental_facility;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class CarRentalController {

    private final CarRentalService service;

    @GetMapping
    public String getCarRental() {
        return "index";
    }

    public CarRentalController(CarRentalService service) {
        this.service = service;
    }

    @PostMapping
    public CarRentalModel save(@RequestBody @Valid CarRentalModel carRentalModel) {
        return service.save(carRentalModel);
    }

    @GetMapping("/{id}")
    public CarRentalModel getById(@PathVariable Long id) {
        return service.getById(id);
    }
}
