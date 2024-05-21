package com.sda.carrental.customer;

import com.sda.carrental.reservation.CarModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
//    private final BranchesRepository repository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    CustomerModel save(CustomerModel car) {
        return customerRepository.save(car);
    }

//    CustomereModel save(CustomerModel customer) {
//        CompanyBranchModel companyBranchModel = repository.findById(employee
//                .getCompanyBranch()
//                .getId())
//                .orElseThrow(() -> new ObjectNotFoundInRepositoryException("Company branch not found"));
//
//        employee.setCompanyBranch(companyBranchModel);
//        return employeeRepository.save(employee);
//    }

    List<CustomerModel> getAll() {
        return customerRepository.findAll();
    }
}
