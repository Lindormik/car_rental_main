package com.sda.carrental.reservation;

import com.sda.carrental.car_rental_facility.CompanyBranchModel;
import com.sda.carrental.customer.CustomerModel;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "reservation")
public class ReservationModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id")
    private CustomerModel customer;

    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "car_id")
    private CarModel car;

    @NotNull
    private LocalDate startDate;

    @NotNull
    private LocalDate endDate;

    @DecimalMin(value = "1.00", message = "Price must be grater than 1.00")
    @DecimalMax(value = "10000.00", message = "Price must be less than 10000.00")
    @Digits(integer = 7, fraction = 2, message = "Price must have up to 7 digits in total and 2 decimal places")
    private BigDecimal loanAmount;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "start_branch_id")
    private CompanyBranchModel startBranch;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "end_branch_id")
    private CompanyBranchModel endBranch;

    public ReservationModel(Long id, CustomerModel customer, CarModel car, LocalDate startDate, LocalDate endDate, BigDecimal loanAmount, CompanyBranchModel startBranch, CompanyBranchModel endBranch) {
        this.id = id;
        this.customer = customer;
        this.car = car;
        this.startDate = startDate;
        this.endDate = endDate;
        this.loanAmount = loanAmount;
        this.startBranch = startBranch;
        this.endBranch = endBranch;
    }

    public ReservationModel() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CustomerModel getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerModel customer) {
        this.customer = customer;
    }

    public CarModel getCar() {
        return car;
    }

    public void setCar(CarModel car) {
        this.car = car;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public BigDecimal getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(BigDecimal loanAmount) {
        this.loanAmount = loanAmount;
    }

    public CompanyBranchModel getStartBranch() {
        return startBranch;
    }

    public void setStartBranch(CompanyBranchModel startBranch) {
        this.startBranch = startBranch;
    }

    public CompanyBranchModel getEndBranch() {
        return endBranch;
    }

    public void setEndBranch(CompanyBranchModel endBranch) {
        this.endBranch = endBranch;
    }

    @Override
    public String toString() {
        return "ReservationModel{" +
                "id =" + id +
                ", customer =" + customer + '\'' +
                ", car =" + car + '\'' +
                ", startDate =" + startDate + '\'' +
                ", endDate =" + endDate + '\'' +
                ", loanAmount =" + loanAmount + '\'' +
                ", startBranch =" + startBranch + '\'' +
                ", endBranch =" + endBranch +
                '}';
    }
}
