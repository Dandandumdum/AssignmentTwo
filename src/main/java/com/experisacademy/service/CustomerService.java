package com.experisacademy.service;

import com.experisacademy.dao.CustomerDao;
import com.experisacademy.model.Customer;
import com.experisacademy.model.CustomerCountry;
import com.experisacademy.model.CustomerSpender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomerService {

    private CustomerDao customerDao;

    @Autowired
    public CustomerService(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    public ArrayList<Customer> getCustomers() {
        return customerDao.selectCustomers();
    }

    public Customer getCustomerById(long id) {
        return customerDao.selectCustomerById(id);
    }

    public ArrayList<Customer> getCustomerByName(String name) {
        return customerDao.selectCustomersByName(name);
    }

    public boolean addCustomer(Customer customer) {
        return customerDao.createCustomer(customer);
    }

    public boolean updateCustomer(long id, Customer newCustomer) {
        return customerDao.updateCustomer(id, newCustomer);
    }

    public ArrayList<CustomerCountry> getCountriesByOrderCount() {
        return customerDao.selectCountriesByOrderCount();
    }

    public ArrayList<Customer> getCustomerSubset(int limit, int offset) {
        return customerDao.selectSubsetCustomers(limit, offset);
    }

    public ArrayList<CustomerSpender> getCustomerSpender() {
        return customerDao.selectCustomersHighestSpenders();
    }



}
