package com.sda.carrental.reservation;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {

    private final CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    CarModel save(CarModel car) {
        return carRepository.save(car);
    }

    List<CarModel> getAll() {
        return carRepository.findAll();
    }
    public CarModel rentCar(Long carId) {
        Optional<CarModel> carOptional = carRepository.findById(carId);
        if (carOptional.isPresent()) {
            CarModel car = carOptional.get();
            if (car.getStatus() == CarStatus.AVAILABLE) {
                car.setStatus(CarStatus.RENTED);
                return carRepository.save(car);
            } else {
                throw new IllegalStateException("Car is not available for rent");
            }
        } else {
            throw new EntityNotFoundException("Car with id: " + carId + " is " + CarStatus.UNAVAILABLE);
        }
    }
}
