package com.sda.carrental.reservation;

import com.sda.carrental.employee.EmployeeModel;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Entity
@Table(name = "rent")
public class RentModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "employee_id")
    private EmployeeModel employee;

    private String comments;

    @NotNull
    private LocalDate rentDate;

    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "reservation_id")
    private ReservationModel reservation;

    public RentModel(Long id, EmployeeModel employee, String comments, LocalDate rentDate, ReservationModel reservation) {
        this.id = id;
        this.employee = employee;
        this.comments = comments;
        this.rentDate = rentDate;
        this.reservation = reservation;
    }

    public RentModel() {}

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

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public LocalDate getRentDate() {
        return rentDate;
    }

    public void setRentDate(LocalDate rentDate) {
        this.rentDate = rentDate;
    }

    public ReservationModel getReservation() {
        return reservation;
    }

    public void setReservation(ReservationModel reservation) {
        this.reservation = reservation;
    }

    @Override
    public String toString() {
        return "RentModel{" +
                "id =" + id +
                ", employee = " + employee + '\'' +
                ", comments = " + comments + '\'' +
                ", rentDate = " + rentDate +
                ", reservation = " + reservation +
                '}';
    }
}
