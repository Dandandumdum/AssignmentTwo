import static org.junit.jupiter.api.Assertions.*;

import com.experisacademy.dao.CustomerRepository;
import com.experisacademy.model.Customer;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;


class SqlHelperTest {
    @Test
    //Tests whether a specific customer selected by customerId has the correct FirstName and LastName
    void selectedSpecificCustomer_customerIdInput22CustomerFirstNameHeather_true(){
        var sqlChecker = new CustomerRepository();
        Customer specificCustomer = sqlChecker.selectCustomerById(22);
        assertTrue(specificCustomer.getFirstName().equals("Heather") && specificCustomer.getLastName().equals("Leacock"));
    }

    @Test
    void selectedSpecificCustomerByName_customerFirstNameDaanCustomerId8_true(){
        var sqlChecker = new CustomerRepository();
        ArrayList<Customer> specificCustomers = sqlChecker.selectCustomersByName("Daan");
        assertTrue(specificCustomers.get(0).getFirstName().equals("Daan"));
    }
}