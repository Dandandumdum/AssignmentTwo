package com.experisacademy.dao;

import com.experisacademy.model.Customer.Customer;
import com.experisacademy.model.Customer.CustomerGenre;
import com.experisacademy.model.Customer.OrderedCustomer;

import java.util.ArrayList;

public interface CustomerDao {

    ArrayList<Customer> selectAllCustomers();

    Customer selectSpecificCustomer(String id);

    Customer selectSpecificCustomerByName(String name);

    boolean addNewCustomer(Customer customer);

    boolean updateCustomer(Customer updateCustomer, Customer newCustomerDetails);

    ArrayList<OrderedCustomer> orderByCountryCount();

    ArrayList<Customer> selectSubsetCustomers(int limit, int offset);

    ArrayList<Customer> orderedCustomersHighestSpenders();

    CustomerGenre specificCustomerPopularGenre(String id);




}
