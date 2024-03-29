package com.sda.carrental.car_rental_facility;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Entity
@Table(name = "car_rental")
public class CarRentalModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "field can't be null")
    private String name;

    @NotNull(message = "field can't be null")
    private String internetDomain;

    @NotNull(message = "field can't be null")
    private String address;

    @NotNull(message = "field can't be null")
    private String owner;

    @JsonIgnore
    @OneToMany(mappedBy = "carRental", cascade = CascadeType.ALL)
    private List<CompanyBranchModel> branches;

    public CarRentalModel(Long id, String name, String internetDomain, String address, String owner, List<CompanyBranchModel> branches) {
        this.id = id;
        this.name = name;
        this.internetDomain = internetDomain;
        this.address = address;
        this.owner = owner;
        this.branches = branches;
    }

    public CarRentalModel() {}

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

    public String getInternetDomain() {
        return internetDomain;
    }

    public void setInternetDomain(String internetDomain) {
        this.internetDomain = internetDomain;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public List<CompanyBranchModel> getBranches() {
        return branches;
    }

    public void setBranches(List<CompanyBranchModel> branches) {
        this.branches = branches;
    }

    @Override
    public String toString() {
        return "CarRentalModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", internetDomain='" + internetDomain + '\'' +
                ", address='" + address + '\'' +
                ", owner='" + owner + '\'' +
                ", branches=" + branches +
                '}';
    }
}
