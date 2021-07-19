package dao;

import model.Customer.Customer;
import model.Customer.CustomerGenre;
import model.Customer.OrderedCustomer;

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