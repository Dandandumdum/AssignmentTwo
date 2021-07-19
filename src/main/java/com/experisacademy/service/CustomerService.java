package com.experisacademy.service;

import com.experisacademy.dao.CustomerDao;
import com.experisacademy.model.Customer.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomerService {

    private CustomerDao dao;

    @Autowired
    public CustomerService(CustomerDao dao) {
        this.dao = dao;
    }

    public ArrayList<Customer> getAllDays() {
        return dao.selectAllCustomers();
    }

    public Customer getCustomerById(String id) {
        return dao.selectSpecificCustomer(id);
    }

    public Customer getCustomerByName(String name) {
        return dao.selectSpecificCustomerByName(name);
    }

    public boolean addCustomer(Customer customer) {
        return dao.addNewCustomer(customer);
    }

    /*public boolean updateCustomer(String id, Customer newCustomer) {
        return dao.updateCustomer(id, newCustomer);
    }*/

}
