package com.sda.carrental.employee;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    EmployeeModel save(EmployeeModel employee) {
        return employeeRepository.save(employee);
    }

    List<EmployeeModel> getAll() {
        return employeeRepository.findAll();
    }
}
