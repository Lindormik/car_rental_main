package com.sda.carrental.car_rental_facility;

import com.sda.carrental.reservation.RentModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BranchesService {

    private final BranchesRepository repository;
    private final CarRentalRepository carRentalRepository;

    public BranchesService(BranchesRepository repository, CarRentalRepository carRentalRepository) {
        this.repository = repository;
        this.carRentalRepository = carRentalRepository;
    }

    CompanyBranchModel save(CompanyBranchModel branch) {
        CarRentalModel carRental = carRentalRepository.findById(branch
                        .getCarRental()
                        .getId())
                .orElseThrow(() -> new ObjectNotFoundInRepositoryException("Car Rental not found"));

        branch.setCarRental(carRental);
        return repository.save(branch);
    }



    CompanyBranchModel findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundInRepositoryException("Branch not found"));
    }

    List<CompanyBranchModel> findAll() {
        return repository.findAll();
    }
}
