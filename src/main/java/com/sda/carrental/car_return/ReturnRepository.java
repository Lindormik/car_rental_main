package com.sda.carrental.car_return;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReturnRepository extends JpaRepository<ReturnModel, Long> {

    @Query("""
            SELECT res.id, res.customer FROM ReturnModel returnModel
            JOIN returnModel.reservation res
            WHERE res.id = :reservationId""")
        //this list for example could look like [Array[res.id], Array[res.id]]
        //this case is not possible base on our application logic
    List<Object[]> findReturnWithReservationId(Long reservationId);
}
