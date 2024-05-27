package com.sda.carrental.reservation;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class CarServiceTest {

    @Mock
    private CarRepository carRepository;

    @InjectMocks
    private CarService carService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRentCar() {
        // when
        CarModel car = new CarModel();
        car.setId(1L);
        car.setBrand("Toyota");
        car.setModel("Corolla");
        car.setStatus(CarStatus.AVAILABLE);

        when(carRepository.findById(1L)).thenReturn(Optional.of(car));
        when(carRepository.save(any(CarModel.class))).thenReturn(car);

        // given
        CarModel rentedCar = carService.rentCar(1L);

        // then
        assertEquals(CarStatus.RENTED, rentedCar.getStatus());
        verify(carRepository, times(1)).findById(1L);
        verify(carRepository, times(1)).save(car);
    }

    @Test
    public void testRentCar_CarNotFound() {
        // Arrange
        when(carRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        EntityNotFoundException thrown = assertThrows(EntityNotFoundException.class, () -> {
            carService.rentCar(1L);
        });
        assertEquals("Car not found with id: 1", thrown.getMessage());
        verify(carRepository, times(1)).findById(1L);
        verify(carRepository, times(0)).save(any(CarModel.class));
    }

    @Test
    public void testRentCar_CarNotAvailable() {
        // Arrange
        CarModel car = new CarModel();
        car.setId(1L);
        car.setBrand("Toyota");
        car.setModel("Corolla");
        car.setStatus(CarStatus.RENTED);

        when(carRepository.findById(1L)).thenReturn(Optional.of(car));

        // Act & Assert
        IllegalStateException thrown = assertThrows(IllegalStateException.class, () -> {
            carService.rentCar(1L);
        });
        assertEquals("Car is not available for rent", thrown.getMessage());
        verify(carRepository, times(1)).findById(1L);
        verify(carRepository, times(0)).save(any(CarModel.class));
    }
}
