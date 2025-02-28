package com.example.demo.controller;

import com.example.demo.model.Customer;
import com.example.demo.model.Response;
import com.example.demo.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping
    public Response createCustomer(@Valid @RequestBody Customer customer) {
        return customerService.createCustomer(customer);
    }

    @PutMapping("/{id}")
    public Response updateCustomer(@PathVariable Long id,@Valid @RequestBody Customer customer) {
        return customerService.updateCustomer(id,customer);
    }


    @GetMapping
    public Response getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/{id}")
    public Response getCustomerById(@PathVariable Long id) {
        return customerService.getCustomerById(id);
    }

    @DeleteMapping("/{id}")
    public Response deleteCustomer(@PathVariable Long id) {
        return customerService.deleteCustomer(id);
    }
}
