package com.experisacademy.controller;

import com.experisacademy.model.Customer.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.experisacademy.service.CustomerService;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/customers")
    public ArrayList<Customer> getAllDays() {
        return customerService.getAllDays();
    }

    @GetMapping("/id/{id}")
    public Customer getSpecificCustomerById(@PathVariable String id) {
        return customerService.getCustomerById(id);
    }

    @GetMapping("/name/{name}")
    public Customer getSpecificCustomerByName(@PathVariable String name) {
        return customerService.getCustomerByName(name);
    }

    @PostMapping
    public boolean addCustomer(@RequestBody Customer customer) {
        return customerService.addCustomer(customer);
    }



}
