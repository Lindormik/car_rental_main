package com.sda.carrental.employee;

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
    private CompanyBranchModel companyBranch;

    public EmployeeModel(Long id, String name, String surname, EmployeePosition jobPosition, CompanyBranchModel companyBranch) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.jobPosition = jobPosition;
        this.companyBranch = companyBranch;
    }
    @Override
    public String toString() {
        return "EmployeeModel{" +
                "id = " + id +
                ", name = " + name + '\'' +
                ", surname= " + surname + '\'' +
                ", job position = " + jobPosition +
                '}';
    }

    public EmployeeModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public EmployeePosition getJobPosition() {
        return jobPosition;
    }

    public void setJobPosition(EmployeePosition jobPosition) {
        this.jobPosition = jobPosition;
    }

    public CompanyBranchModel getCompanyBranch() {
        return companyBranch;
    }

    public void setCompanyBranch(CompanyBranchModel companyBranch) {
        this.companyBranch = companyBranch;
    }
}
