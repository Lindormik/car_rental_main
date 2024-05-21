package com.sda.carrental.car_return;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/returns")
public class ReturnController {

    private final ReturnService service;

    public ReturnController(ReturnService service) {
        this.service = service;
    }
    @PostMapping
    public ReturnModel save(@RequestBody @Valid ReturnDTO carReturn) {
        return service.save(carReturn);
    }
    @GetMapping("/{id}")
    public ReturnModel getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping
    public List<ReturnModel> getAll() {
        return service.getAll();
    }

}
