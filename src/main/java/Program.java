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
        ArrayList <OrderedCustomer>customersGroupedByCountry = sqlHelper.orderByCountryCount();
        ArrayList <Customer>subSetCustomers = sqlHelper.selectSubsetCustomers(4,9);
        ArrayList <Customer>bigSpenderCustomers = sqlHelper.orderedCustomersHighestSpenders();

        sqlHelper.addNewCustomer(addCustomer);//triggering primary key constraint fail
        Customer specificCustomer = sqlHelper.selectSpecificCustomer("1");
        Customer specificCustomerByName = sqlHelper.selectSpecificCustomerByName("");
        sqlHelper.updateCustomer(addCustomer, newCustomerDetails);
        CustomerGenre specificCustomerGenre = sqlHelper.specificCustomerPopularGenre("22");//User Input required



        //printCustomers(allCustomers);
        //printSpecificCustomer(specificCustomer);
        //printSpecificCustomerByName(specificCustomerByName);
        //printOrderedCustomers(customersGroupedByCountry);
        //printCustomers(bigSpenderCustomers);
        printSpecificCustomerGenre(specificCustomerGenre);

    }
    //Prints all customers from Customer class ArrayList, returning all attributes
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
    //Prints a specific customer based upon customerId, returning all attributes
    public static void printSpecificCustomer(Customer customer){
        specificCustomer(customer);
    }
    //Prints a specific customer based upon customerFirstName, returning all attributes
    public static void printSpecificCustomerByName(Customer customer){
        specificCustomer(customer);
    }
    //Method for printing out Customer class attributes
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
    //Prints all customers from OrderedCustomer class, returning all attributes from OrderedCustomer Class
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
    //Prints specific CustomerGenre object based upon customerId, returning all attributes from CustomerGenre class
    public static void printSpecificCustomerGenre(CustomerGenre customer){
        if(customer != null) {

            System.out.println("-------------------------------");
            System.out.println(customer.getId());
            System.out.println(customer.getFirstName());
            System.out.println(customer.getLastName());
            System.out.println(customer.getCountry());
            System.out.println(customer.getPostalCode());
            System.out.println(customer.getPhoneNumber());
            System.out.println(customer.getEmail());
            System.out.println("Favourite customer genre: "+customer.getGenre());

        } else {
            System.out.println("No customers returned");
        }

    }

}
