package com.sda.carrental.car_rental_facility;

import com.sda.carrental.employee.EmployeeModel;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/branches")
public class BranchesController {

    private final BranchesService service;

    public BranchesController(BranchesService service) {
        this.service = service;
    }

    @PostMapping
    public CompanyBranchModel save(@RequestBody @Valid CompanyBranchModel branch) {
        return service.save(branch);
    }

    @GetMapping
    public List<CompanyBranchDTO> findAll() {
        return service.findAll()
                .stream()
                .map(this::mapToCompanyBranchDTO)
                .toList();
    }

    @GetMapping("/{id}")
    public CompanyBranchDTO findById(@PathVariable Long id) {
        CompanyBranchModel companyBranch = service.findById(id);
        return mapToCompanyBranchDTO(companyBranch);
    }

    private CompanyBranchDTO mapToCompanyBranchDTO(CompanyBranchModel companyBranch) {
        CarRentalModel carRental = companyBranch.getCarRental();
        return new CompanyBranchDTO(
                companyBranch.getId(),
                companyBranch.getBranchAddress(),
                new HQDetails(
                        carRental.getName(),
                        carRental.getInternetDomain(),
                        carRental.getAddress(),
                        carRental.getOwner()
                )
        );
    }
}

record CompanyBranchDTO(Long branchId, String branchAddress, HQDetails mainBranchDetails) {
}

record HQDetails(String carRentalName, String internetDomain, String address, String owner) {
}
