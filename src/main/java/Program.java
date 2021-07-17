import Customer.*;
import dbhelper.SqlHelper;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;

@SpringBootApplication
public class Program {
    public static void main(String[] args) {
        //SpringApplication.run(Program.class, args);
        //Should make Test case for these instead.
        Customer addCustomer = new Customer("333", "Wayne", "WAYNESON", "TEXAS", "1111-222", "777", "waynewayenson@waynenet.net" );
        Customer newCustomerDetails = new Customer("333", "Tom", "WAYNESON", "TEXAS", "1111-222", "777", "waynewayenson@waynenet.net");

        SqlHelper sqlHelper = new SqlHelper();
        ArrayList<Customer> allCustomers = sqlHelper.selectAllCustomers();
        ArrayList customersGroupedByCountry = sqlHelper.orderByCountryCount();
        ArrayList subSetCustomers = sqlHelper.selectSubsetCustomers(4,9);
        ArrayList bigSpenderCustomers = sqlHelper.orderedCustomersHighestSpenders();

        sqlHelper.addNewCustomer(addCustomer);//triggering primary key constraint fail
        Customer specificCustomer = sqlHelper.selectSpecificCustomer("333");
        Customer specificCustomerByName = sqlHelper.selectSpecificCustomerByName("Wayne");
        sqlHelper.updateCustomer(addCustomer, newCustomerDetails);


        //printCustomers(allCustomers);
        //printSpecificCustomer(specificCustomer);
        //printSpecificCustomerByName(specificCustomerByName);
        //printOrderedCustomers(customersGroupedByCountry);
        printCustomers(bigSpenderCustomers);
    }

    public static void printCustomers(ArrayList<Customer> customers){
        if(customers.size() != 0) {
            for (Customer c : customers) {
                System.out.println("-------------------------------");
                System.out.println(c.getId());
                System.out.println(c.getFirstName());
                System.out.println(c.getLastName());
                System.out.println(c.getCountry());
                System.out.println(c.getPostalCode());
                System.out.println(c.getPhoneNumber());
                System.out.println(c.getEmail());
            }
        } else {
            System.out.println("No customers returned");
        }
    }
    public static void printSpecificCustomer(Customer customer){
        specificCustomer(customer);
    }
    public static void printSpecificCustomerByName(Customer customer){
        specificCustomer(customer);
    }

    private static void specificCustomer(Customer customer) {
        if(customer != null) {

            System.out.println("-------------------------------");
            System.out.println(customer.getId());
            System.out.println(customer.getFirstName());
            System.out.println(customer.getLastName());
            System.out.println(customer.getCountry());
            System.out.println(customer.getPostalCode());
            System.out.println(customer.getPhoneNumber());
            System.out.println(customer.getEmail());

        } else {
            System.out.println("No customers returned");
        }
    }
    public static void printOrderedCustomers(ArrayList<OrderedCustomer> customers){
        if(customers.size() != 0) {
            for (OrderedCustomer c : customers) {
                System.out.println("-------------------------------");
                System.out.println(c.getId());
                System.out.println(c.getOrder());
            }
        } else {
            System.out.println("No customers returned");
        }
    }

}
