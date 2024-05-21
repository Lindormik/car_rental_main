package com.sda.carrental.car_return;

public class ReturnAlreadyExistsForReservation extends RuntimeException{

    public ReturnAlreadyExistsForReservation(String message){
        super(message);
    }
}
