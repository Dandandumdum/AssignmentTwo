package com.experisacademy.controller;

import com.experisacademy.dao.CustomerRepository;
import com.experisacademy.model.Customer;
import com.experisacademy.model.CustomerCountry;
import com.experisacademy.model.CustomerGenre;
import com.experisacademy.model.CustomerSpender;
import com.experisacademy.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
@Controller
@RequestMapping("/customers")
public class ThymeController {

    private final CustomerService customerService;

    @Autowired
    public ThymeController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/id/{id}")
    public String searchByCustomerId(@PathVariable long id, Model model){
        model.addAttribute("customer", customerService.getCustomerById(id));
        return "get-by-id";
    }
    //Requests customer details based upon customer name

    @GetMapping("/name/{firstName}")
    public String searchByCustomerName(@PathVariable String firstName, Model model){
        model.addAttribute("customer",customerService.getCustomerByName(firstName));
        return "get-by-name";

    }
    //Requests a list of all customers
    //@GetMapping("/view-customers")
    @RequestMapping(value = "/customers", method = RequestMethod.GET)
    public String showAllCustomers(Model model){
                model.addAttribute("customers", customerService.getCustomers());
        return "view-customers";
    }
    //Requests the amount of customers registered to each country, then returns a list of those countries in descending order
    @GetMapping("/country")
    public String showCustomerOrdersByCountry(Model model) {
        model.addAttribute("customer",customerService.getCountriesByOrderCount());
        return "get-by-country";
    }
    //Requests a an ordered list of customers whom spend the most, in descending order
    @GetMapping("/spending")
    public String showCustomersOrderedBySpending(Model model) {
        model.addAttribute("customer",customerService.getCustomerSpender());
        return "get-by-spending";

    }
    //Requests a subset of customers based upon limit and offset
    @GetMapping("/subset/{limit}/{offset}")
    public String getSubsetCustomers(@PathVariable int limit,
                                                  @PathVariable int offset, Model model) {
        model.addAttribute("customer",customerService.getCustomerSubset(limit, offset));
        return "get-by-subset";
    }
    //Requests a specific customers favourite genre along with their normal details, by customer Id
    @GetMapping("/genre/id/{id}")
    public String searchByCustomerIdFavouriteGenre(@PathVariable long id, Model model){
        model.addAttribute("customer",customerService.getCustomerFavouriteGenre(id));
        return "get-by-genre";
    }
    @RequestMapping(value = "/add-customers", method = RequestMethod.GET)
    public String createCustomer(Model model){
        model.addAttribute("customer", new Customer());
        return "add-customers";
    }
    //Posts a new customer
    @PostMapping
    public String createCustomer(@RequestBody Customer customer, Model model){
        model.addAttribute("customer",customerService.addCustomer(customer));
        return"add-customers";
    }
    //Updates a new customer
    @PutMapping("/id/{id}")
    public String updateCustomer(@PathVariable long id, @RequestBody Customer customer, Model model ){
        model.addAttribute( "customer",customerService.updateCustomer(id, customer));
        return "update-customers";
    }



}
