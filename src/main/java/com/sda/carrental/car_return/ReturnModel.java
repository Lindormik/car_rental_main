package com.sda.carrental.car_return;

import com.sda.carrental.employee.EmployeeModel;
import com.sda.carrental.reservation.ReservationModel;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Entity
@Table(name = "car_return")
public class ReturnModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "employee_id")
    private EmployeeModel employee;

    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "reservation_id")
    private ReservationModel reservation;

    @NotNull
    private LocalDate returnDate;

    private int additionalFee;

    private String comments;

    public ReturnModel(Long id, EmployeeModel employee, ReservationModel reservation, LocalDate returnDate, int additionalFee, String comments) {
        this.id = id;
        this.employee = employee;
        this.reservation = reservation;
        this.returnDate = returnDate;
        this.additionalFee = additionalFee;
        this.comments = comments;
    }

    public ReturnModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EmployeeModel getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeModel employee) {
        this.employee = employee;
    }

    public ReservationModel getReservation() {
        return reservation;
    }

    public void setReservation(ReservationModel reservation) {
        this.reservation = reservation;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public int getAdditionalFee() {
        return additionalFee;
    }

    public void setAdditionalFee(int additionalFee) {
        this.additionalFee = additionalFee;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
