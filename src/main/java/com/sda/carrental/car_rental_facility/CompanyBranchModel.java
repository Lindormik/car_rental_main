package com.sda.carrental.car_rental_facility;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sda.carrental.employee.EmployeeModel;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Entity
@Table(name = "company_branch")
public class CompanyBranchModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "field can't be null")
    private String branchAddress;

    @JsonIgnore
    @OneToMany(mappedBy = "companyBranch", cascade = CascadeType.ALL)
    private List<EmployeeModel> employees;

    @ManyToOne
    @JoinColumn(name = "car_rental_id", nullable = false)
    private CarRentalModel carRental;

    public CompanyBranchModel(Long id, String branchAddress, List<EmployeeModel> employees, CarRentalModel carRental) {
        this.id = id;
        this.branchAddress = branchAddress;
        this.employees = employees;
        this.carRental = carRental;
    }

    public CompanyBranchModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CarRentalModel getCarRental() {
        return carRental;
    }

    public void setCarRental(CarRentalModel carRental) {
        this.carRental = carRental;
    }

    public String getBranchAddress() {
        return branchAddress;
    }

    public void setBranchAddress(String branchAddress) {
        this.branchAddress = branchAddress;
    }

    public List<EmployeeModel> getEmployees() {
        return employees;
    }

    public void setEmployees(List<EmployeeModel> employees) {
        this.employees = employees;
    }

    @Override
    public String toString() {
        return "CompanyBranchModel{" +
                "id = " + id +
                ", address = " + branchAddress + '\'' +
                '}';
    }
}
