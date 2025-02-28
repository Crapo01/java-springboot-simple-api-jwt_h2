package com.example.demo.service;


import com.example.demo.exception.CustomerNotFoundException;
import com.example.demo.model.Customer;
import com.example.demo.model.Response;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.util.ResponseUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    // Create a new customer
    public Response createCustomer(@Valid Customer customer) {
        customerRepository.save(customer);
        return ResponseUtil.createResponse("Customer created successfully", customer);
    }

    // Get a customer by ID
    public Response getCustomerById(Long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isPresent()) {
            return ResponseUtil.createResponse("Customer found", customer.get());
        } else {
            throw new CustomerNotFoundException("Customer not found with id " + id);
        }
    }

    // Delete a customer by ID
    public Response deleteCustomer(Long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isPresent()) {
            customerRepository.delete(customer.get());
            return ResponseUtil.createResponse("Customer deleted successfully", id);
        } else {
            throw new CustomerNotFoundException("Customer not found with id " + id);
        }
    }

    public Response getAllCustomers() {
        Iterable<Customer> customers = customerRepository.findAll();
        return ResponseUtil.createResponse("Customer list", customers);
    }

    public Response updateCustomer(Long id, @Valid Customer updatedCustomer) {
        Optional<Customer> existingCustomerOpt = customerRepository.findById(id);

        if (existingCustomerOpt.isPresent()) {
            Customer existingCustomer = existingCustomerOpt.get();
            existingCustomer.setName(updatedCustomer.getName());
            existingCustomer.setPhoneNumber(updatedCustomer.getPhoneNumber());

            customerRepository.save(existingCustomer);
            return ResponseUtil.createResponse("Customer updated successfully", existingCustomer);
        } else {
            throw new CustomerNotFoundException("Customer not found with id " + id);
        }
    }
}
