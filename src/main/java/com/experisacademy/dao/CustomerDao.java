package com.experisacademy.dao;

import com.experisacademy.model.*;

import java.util.ArrayList;

public interface CustomerDao {

    ArrayList<Customer> selectCustomers();

    Customer selectCustomerById(long id);

    ArrayList<Customer> selectCustomersByName(String name);

    boolean createCustomer(Customer customer);

    boolean updateCustomer(long id, Customer newCustomerDetails);

    ArrayList<CustomerCountry> selectCountriesByOrderCount();

    ArrayList<Customer> selectSubsetCustomers(int limit, int offset);

    ArrayList<CustomerSpender> orderedCustomersHighestSpenders();

    CustomerGenre specificCustomerPopularGenre(long id);

}
