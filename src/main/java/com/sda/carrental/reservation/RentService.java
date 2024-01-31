package com.sda.carrental.reservation;

import com.sda.carrental.car_rental_facility.ObjectNotFoundInRepositoryException;
import com.sda.carrental.employee.EmployeeModel;
import com.sda.carrental.employee.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RentService {

    private final RentRepository rentRepository;
    private final ReservationRepository reservationRepository;
    private final EmployeeRepository employeeRepository;


    public RentService(RentRepository rentRepository, ReservationRepository reservationRepository, EmployeeRepository employeeRepository) {
        this.rentRepository = rentRepository;
        this.reservationRepository = reservationRepository;
        this.employeeRepository = employeeRepository;
    }

    RentModel save(RentDTO rentDTO) {
        throwExceptionIfRentWithReservationIdAlreadyExist(rentDTO.reservationId());

        RentModel rentToSave = createRentModelObjectBaseOnRentDTO(rentDTO);
        return rentRepository.save(rentToSave);
    }

    private RentModel createRentModelObjectBaseOnRentDTO(RentDTO rentDTO) {
        RentModel rentToSave = new RentModel();

        rentToSave.setRentDate(rentDTO.rentDate());
        rentToSave.setComments(rentDTO.comments());

        EmployeeModel employeeFromRepository = findEmployeeById(rentDTO.employeeId());
        rentToSave.setEmployee(employeeFromRepository);

        ReservationModel reservationFromRepository = findReservationById(rentDTO.reservationId());
        rentToSave.setReservation(reservationFromRepository);
        return rentToSave;
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
        List<Object[]> reservationIds = rentRepository.findRentWithReservationId(reservationId);

        //we are checking if query is empty to verify that there are no rents for our reservation yet
        if (!reservationIds.isEmpty()) {
           throw new RentAlreadyExistsForReservation("Rent already exist for reservation with id " + reservationId);
        }
    }

    public RentModel getById(Long id) {
        return rentRepository.findById(id)
            .orElseThrow(() -> new ObjectNotFoundInRepositoryException("Rent not found"));
    }
    public List<RentModel> getAll() {
        return rentRepository.findAll();
    }
}
