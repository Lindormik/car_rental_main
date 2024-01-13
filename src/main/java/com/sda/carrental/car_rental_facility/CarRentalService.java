package com.sda.carrental.car_rental_facility;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarRentalService {

    private final CarRentalRepository repository;

    public CarRentalService(CarRentalRepository repository) {
        this.repository = repository;
    }

    public List<CarRentalModel> getCarRentalList() {
        return repository.findAll();
    }
    public void addAbout(CarRentalModel carRentalModel) {
        repository.save(carRentalModel);
    }

    CarRentalModel save(CarRentalModel carRentalModel) {
        return repository.save(carRentalModel);
    }

    CarRentalModel getById(Long id) {

        return repository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundInRepositoryException("Car Rental not found"));

    }
}
