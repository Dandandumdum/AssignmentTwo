import static org.junit.jupiter.api.Assertions.*;

import Customer.*;
import dbhelper.*;
import org.junit.jupiter.api.Test;


class SqlHelperTest {
    @Test
    //Tests whether a specific customer selected by customerId has the correct FirstName and LastName
    void selectedSpecificCustomer_customerIdInput22CustomerFirstNameHeather_true(){
        var customerChecker = new Customer();
        var sqlChecker = new SqlHelper();
        Customer specificCustomer = sqlChecker.selectSpecificCustomer("22");
        assertTrue(specificCustomer.getFirstName().equals("Heather") && specificCustomer.getLastName().equals("Leacock"));
    }

    @Test
    void selectedSpecificCustomerByName_customerFirstNameDaanCustomerId8_true(){
        var customerChecker = new Customer();
        var sqlChecker = new SqlHelper();
        Customer specificCustomer = sqlChecker.selectSpecificCustomerByName("Daan");
        assertTrue(specificCustomer.getId().equals("8"));
    }
}