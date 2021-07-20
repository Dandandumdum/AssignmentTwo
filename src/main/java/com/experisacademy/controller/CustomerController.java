package com.experisacademy.controller;

import com.experisacademy.model.Customer;
import com.experisacademy.model.CustomerCountry;
import com.experisacademy.model.CustomerSpender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping("/id/{id}")
    public Customer searchByCustomerId(@PathVariable long id){
        return customerService.getCustomerById(id);
    }
    //Requests customer details based upon customer name
    @GetMapping("/name/{firstName}")
    public ArrayList<Customer> searchByCustomerName(@PathVariable String firstName){
        return customerService.getCustomerByName(firstName);
    }
    //Requests a list of all customers
    @GetMapping
    public ArrayList<Customer> showAllCustomers(){
        return customerService.getCustomers();
    }
    //Requests the amount of customers registered to each country, then returns a list of those countries in descending order
    @GetMapping("/country")
    public ArrayList<CustomerCountry> showCustomerOrdersByCountry() {
        return customerService.getCountriesByOrderCount();
    }
    //Requests a an ordered list of customers whom spend the most, in descending order
    @GetMapping("/spending")
    public ArrayList<CustomerSpender> showCustomersOrderedBySpending() {
        return customerService.getCustomerSpender();

    }
    //Requests a subset of customers based upon limit and offset
    @GetMapping("/subset/{limit}/{offset}")
    public ArrayList<Customer> getSubsetCustomers(@PathVariable int limit,
                                                     @PathVariable int offset) {
        return customerService.getCustomerSubset(limit, offset);

    }
    //Requests a specific customers favourite genre along with their normal details, by customer Id
    /*@GetMapping("/api/getCustomerByIdShowFavouriteGenre")
    public String searchByCustomerNameFavouriteGenre(@RequestParam("query")String terms){
        CustomerGenre customer = customerDataAccessService.specificCustomerPopularGenre(terms);
        return customerToStringGenre(customer);
    }*/
    //Posts a new customer
    @PostMapping
    public Boolean createCustomer(@RequestBody Customer customer){
        return customerService.addCustomer(customer);
    }
    //Updates a new customer
    @PutMapping("/id/{id}")
    public Boolean updateCustomer(@PathVariable long id, @RequestBody Customer customer ){
        return customerService.updateCustomer(id, customer);
    }



}
