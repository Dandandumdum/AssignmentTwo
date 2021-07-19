package com.experisacademy.api;

import com.experisacademy.model.Customer.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.experisacademy.service.CustomerService;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public ArrayList<Customer> getAllDays() {
        System.out.println("TEST");
        return customerService.getAllDays();
    }


}
