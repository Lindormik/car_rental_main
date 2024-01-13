package com.sda.carrental.employee;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sda.carrental.car_rental_facility.CompanyBranchModel;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "employee")
public class EmployeeModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String surname;

    @NotNull
    private EmployeePosition jobPosition;

    @ManyToOne
    @JoinColumn(name = "company_branch_id", nullable = false)
    @JsonBackReference
    private CompanyBranchModel companyBranch;

}
