package com.sda.carrental.reservation;

import com.sda.carrental.car_rental_facility.BranchesRepository;
import com.sda.carrental.car_rental_facility.CompanyBranchModel;
import com.sda.carrental.car_rental_facility.ObjectNotFoundInRepositoryException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final BranchesRepository branchesRepository;
    private final CarRepository carRepository;

    public ReservationService(ReservationRepository repository, BranchesRepository branchesRepository, CarRepository carRepository) {
        this.reservationRepository = repository;
        this.branchesRepository = branchesRepository;
        this.carRepository = carRepository;
    }

    ReservationModel saveReservation(ReservationDTO reservationDTO) {
        ReservationModel reservation = new ReservationModel();

        setStartAndEndBranch(reservationDTO, reservation);

        reservation.setCustomer(reservationDTO.customer());
        reservation.setStartDate(reservationDTO.startDate());
        reservation.setEndDate(reservationDTO.endDate());

        CarModel carFromRepo = carRepository.findById(reservationDTO.carId())
                .orElseThrow(() -> new ObjectNotFoundInRepositoryException("Car not found"));

        reservation.setCar(carFromRepo);

        long daysDifference = ChronoUnit.DAYS.between(reservation.getStartDate(), reservation.getEndDate());
        BigDecimal price = carFromRepo.getPrice().multiply(new BigDecimal(daysDifference));
        reservation.setLoanAmount(price);

        return reservationRepository.save(reservation);
    }

    private void setStartAndEndBranch(ReservationDTO reservationDTO, ReservationModel reservation) {
        CompanyBranchModel startBranchFromRepo = branchesRepository.findById(reservationDTO.startBranchId())
                .orElseThrow(() -> new ObjectNotFoundInRepositoryException("Branch not found"));
        reservation.setStartBranch(startBranchFromRepo);

        CompanyBranchModel endBranchFromRepo = branchesRepository.findById(reservationDTO.endBranchId())
                .orElseThrow(() -> new ObjectNotFoundInRepositoryException("Branch not found"));
        reservation.setEndBranch(endBranchFromRepo);
    }

    public ReservationModel getById(Long id) {
        return reservationRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundInRepositoryException("Reservation not found"));
    }

    public List<ReservationModel> getAll() {
        return reservationRepository.findAll();
    }
}



