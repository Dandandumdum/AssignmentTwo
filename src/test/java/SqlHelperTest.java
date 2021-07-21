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

    @Test
    void selectedSubsetCustomers_customerSubsetLimit3Offset5_true(){
        var sqlChecker = new CustomerRepository();
        ArrayList<Customer>subsetCustomers = sqlChecker.selectSubsetCustomers(3,5);
        long[] arr2 =new long[]{6,8};
        long[] arr =new long[]{subsetCustomers.get(0).getId(), subsetCustomers.get(2).getId()};
        assertArrayEquals(arr2,arr);

    }
    @Test
    void creatCustomer_customerId333_true(){
        var sqlChecker = new CustomerRepository();
        Customer customer = new Customer(333,
                 "Wayne",
                 "WAYNESON",
                "TEXAS",
                 1111-222,
                "777",
                 "waynewayenson@waynenet.net");
        assertTrue(sqlChecker.selectCustomerById(333).getFirstName().equals("Wayne"));
    }


}