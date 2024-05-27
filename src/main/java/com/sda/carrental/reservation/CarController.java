package com.sda.carrental.reservation;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cars")
public class CarController {

    private final CarService service;

    public CarController(CarService service) {
        this.service = service;
    }

    @PostMapping
    public CarModel save(@RequestBody @Valid CarModel car) {
        return service.save(car);
    }
    @GetMapping
    public List<CarModel> getAll() {
        return service.getAll();
    }

    @PutMapping("/rent/{id}")
    public ResponseEntity<CarModel> rentCar(@PathVariable Long id) {
        try {
            CarModel rentedCar = service.rentCar(id);
            return ResponseEntity.ok(rentedCar);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
}
