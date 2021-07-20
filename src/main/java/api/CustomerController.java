package api;

import dao.CustomerDao;
import dao.CustomerDataAccessService;
import model.Customer.Customer;
import model.Customer.CustomerGenre;
import model.Customer.OrderedCustomer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import service.CustomerService;

import java.util.ArrayList;
@CrossOrigin
@RestController
@RequestMapping("customer")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }
    CustomerDataAccessService customerDataAccessService = new CustomerDataAccessService();
//Requests customer details of customer with matching id
    @RequestMapping(value = "/api/getCustomerById/{id}", method = RequestMethod.GET) //Must rework endpoints according to spec
    public String searchByCustomerId(@PathVariable String id){
        Customer customer = customerDataAccessService.selectSpecificCustomer(id);
        return  customerToString(customer);
    }
//Requests customer details based upon customer name
    @RequestMapping(value ="/api/getCustomerByName/{firstName}" , method = RequestMethod.GET)
    public String searchByCustomerName(@PathVariable String firstName){
        Customer customer = customerDataAccessService.selectSpecificCustomerByName(firstName);
        return customerToString(customer);
    }
    //Requests a list of all customers
    @RequestMapping(value ="/api/getCustomers" , method = RequestMethod.GET)
    public ArrayList<Customer> showAllCustomers(){
        ArrayList <Customer>customers = customerDataAccessService.selectAllCustomers();
        return customers;
    }
    //Requests the amount of customers registered to each country, then returns a list of those countries in descending order
    @RequestMapping(value ="/api/getCustomersGroupByCountry" , method = RequestMethod.GET)

    public ArrayList<OrderedCustomer> showOrderedCustomersByCountry() {
        ArrayList<OrderedCustomer>customers = customerDataAccessService.orderByCountryCount();
        return customers;
    }
    //Requests a an ordered list of customers whom spend the most, in descending order
    @RequestMapping(value ="/api/getCustomersOrderedBySpending" , method = RequestMethod.GET)
    public ArrayList<Customer> showCustomersOrderedBySpending() {
        ArrayList<Customer> customers = customerDataAccessService.orderedCustomersHighestSpenders();
        return customers;

    }
    //Requests a subset of customers based upon limit and offset
    @RequestMapping(value ="/api/getSubsetCustomers/{limit}/{offset}" , method = RequestMethod.GET)
    public ArrayList<Customer> selectSubsetCustomers(@PathVariable String limit,@PathVariable String offset) {
        ArrayList<Customer> customers = customerDataAccessService.selectSubsetCustomers(limit,offset);
        return customers;

    }
    //Requests a specific customers favourite genre along with their normal details, by customer Id
    @RequestMapping(value ="/api/getCustomerByIdShowFavouriteGenre" , method = RequestMethod.GET)
    public String searchByCustomerNameFavouriteGenre(@RequestParam("query")String terms){
        CustomerGenre customer = customerDataAccessService.specificCustomerPopularGenre(terms);
        return customerToStringGenre(customer);
    }
    //Posts a new customer
    @RequestMapping(value="/api/addCustomer", method = RequestMethod.POST)
    public Boolean PostNewCustomer(@RequestBody Customer customer  ){
        return customerDataAccessService.addNewCustomer(customer);
    }
    //Updates a new customer
    @RequestMapping(value="/api/updateCustomer/{id}", method = RequestMethod.PUT)
    public Boolean updateCustomer(@PathVariable String id, @RequestBody Customer customer ){
        return customerDataAccessService.updateCustomer(customer);
    }





    //Prints all relevant customer details.
    public String customerToString(Customer customer){
        return "Customer Id: " + customer.getId()+ "\nCustomer First Name: " + customer.getFirstName() + "\nCustomer Last Name: " +
                customer.getLastName() + "\nCustomer Country: " +customer.getCountry() +
                "\nCustomer Postal Code: " +customer.getPostalCode() + "\nCustomer Phone Number: " + customer.getPhoneNumber()
                + "\nCustomer Email: " +customer.getEmail();
    }
    public String customerToStringGenre(CustomerGenre customer){
        return "Customer Id: " + customer.getId()+ "\nCustomer First Name: " + customer.getFirstName() + "\nCustomer Last Name: " +
                customer.getLastName() + "\nCustomer Country: " +customer.getCountry() +
                "\nCustomer Postal Code: " +customer.getPostalCode() + "\nCustomer Phone Number: " + customer.getPhoneNumber()
                + "\nCustomer Email: " +customer.getEmail() + " Favourite Genre: "+ customer.getGenre();
    }



}
