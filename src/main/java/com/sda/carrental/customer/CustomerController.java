package com.sda.carrental.customer;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public CustomerModel save(@RequestBody @Valid CustomerModel customer) {
        return customerService.save(customer);
    }

    @GetMapping
    public List<CustomerModel> getAll() {
        return customerService.getAll();
    }

}
