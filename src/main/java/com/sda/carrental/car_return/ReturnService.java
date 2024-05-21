package com.sda.carrental.car_return;

import com.sda.carrental.car_rental_facility.ObjectNotFoundInRepositoryException;
import com.sda.carrental.employee.EmployeeModel;
import com.sda.carrental.employee.EmployeeRepository;
import com.sda.carrental.reservation.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReturnService {
    private final ReturnRepository returnRepository;
    private final ReservationRepository reservationRepository;
    private final EmployeeRepository employeeRepository;

    public ReturnService(ReturnRepository returnRepository, ReservationRepository reservationRepository, EmployeeRepository employeeRepository) {
        this.returnRepository = returnRepository;
        this.reservationRepository = reservationRepository;
        this.employeeRepository = employeeRepository;
    }
    ReturnModel save(ReturnDTO returnDTO) {
        throwExceptionIfRentWithReservationIdAlreadyExist(returnDTO.reservationId());

        ReturnModel returnToSave = createReturnModelObjectBaseOnReturnDTO(returnDTO);
        return returnRepository.save(returnToSave);
    }

    private ReturnModel createReturnModelObjectBaseOnReturnDTO(ReturnDTO returnDTO) {
        ReturnModel returnToSave = new ReturnModel();

        returnToSave.setReturnDate(returnDTO.returnDate());
        returnToSave.setComments(returnDTO.comments());
        returnToSave.setAdditionalFee(returnDTO.additionalFee());

        EmployeeModel employeeFromRepository = findEmployeeById(returnDTO.employeeId());
        returnToSave.setEmployee(employeeFromRepository);

        ReservationModel reservationFromRepository = findReservationById(returnDTO.reservationId());
        returnToSave.setReservation(reservationFromRepository);
        return returnToSave;
    }

    private EmployeeModel findEmployeeById(Long employeeId) {
        return employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ObjectNotFoundInRepositoryException("Employee with id " +
                        employeeId + " not found"));
    }

    private ReservationModel findReservationById(Long reservationId) {
        return reservationRepository.findById(reservationId)
                .orElseThrow(() -> new ObjectNotFoundInRepositoryException("Reservation with id " +
                        reservationId + " not found"));
    }

    private void throwExceptionIfRentWithReservationIdAlreadyExist(Long reservationId) {
        //we are using custom method added to repository
        //which returns List of one element arrays [reservationId]
        List<Object[]> reservationIds = returnRepository.findReturnWithReservationId(reservationId);

        //we are checking if query is empty to verify that there are no rents for our reservation yet
        if (!reservationIds.isEmpty()) {
            throw new ReturnAlreadyExistsForReservation("Return already exist for reservation with id " + reservationId);
        }
    }

    public ReturnModel getById(Long id) {
        return returnRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundInRepositoryException("Return not found"));
    }
    public List<ReturnModel> getAll() {
        return returnRepository.findAll();
    }
}
