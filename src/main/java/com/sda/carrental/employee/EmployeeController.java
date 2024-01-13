package com.sda.carrental.employee;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public EmployeeModel save(@RequestBody @Valid EmployeeModel employee) {
        return employeeService.save(employee);
    }

    @GetMapping
    public List<EmployeeModel> getAll() {
        return employeeService.getAll();
    }

}
