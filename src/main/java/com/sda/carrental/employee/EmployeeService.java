package com.sda.carrental.employee;

import com.sda.carrental.car_rental_facility.BranchesRepository;
import com.sda.carrental.car_rental_facility.CompanyBranchModel;
import com.sda.carrental.car_rental_facility.ObjectNotFoundInRepositoryException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final BranchesRepository repository;

    public EmployeeService(EmployeeRepository employeeRepository, BranchesRepository repository ) {
        this.employeeRepository = employeeRepository;
        this.repository = repository;
    }

    EmployeeModel save(EmployeeModel employee) {
        CompanyBranchModel companyBranchModel = repository.findById(employee
                .getCompanyBranch()
                .getId())
                .orElseThrow(() -> new ObjectNotFoundInRepositoryException("Company branch not found"));

        employee.setCompanyBranch(companyBranchModel);
        return employeeRepository.save(employee);
    }

    List<EmployeeModel> getAll() {
        return employeeRepository.findAll();
    }
}
