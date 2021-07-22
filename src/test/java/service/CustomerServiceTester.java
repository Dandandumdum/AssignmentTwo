package service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.same;
import static org.mockito.Mockito.when;

import com.experisacademy.dao.CustomerRepository;
import com.experisacademy.model.Customer;
import com.experisacademy.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTester {

    @Mock
    private CustomerRepository mockRepo;
    private CustomerService customerService;

    @BeforeEach
    public void init() {
        customerService = new CustomerService(mockRepo);
    }

    private ArrayList<Customer> getTestCustomerList() {
        ArrayList<Customer> actualCustomers = new ArrayList<>();
        actualCustomers.add(new Customer(1, "Dave", "Jonsson", "Sweden", 17245, "0704705569", "davejonsson@Gmail.com"));
        actualCustomers.add(new Customer(2, "Hanna", "Birtson", "Sweden", 17345, "0704778888", "birtsonn@Gmail.com"));
        actualCustomers.add(new Customer(3, "Steve", "Hamon", "UnitedKingdom", 0741, "034778888", "Steve@Hotmail.com"));
        return actualCustomers;
    }

    @Test
    public void checkConnectionEstablished_notNull() {
        assertNotNull(mockRepo);
    }

    @Test
    public void getAllCustomers_nonEmptyList() {
        when(mockRepo.selectCustomers()).thenReturn(getTestCustomerList());

        long expectedId = 1;

        assertEquals(expectedId, customerService.getCustomers().get(0).getId());
    }

    //Tests whether a specific customer selected by customerId has the correct FirstName and LastName
    @Test
    public void selectCustomerById_customerIdOne_correctCustomer() {
        Customer expectedCustomer = getTestCustomerList().get(0);
        when(mockRepo.selectCustomerById(1)).thenReturn(expectedCustomer);
        assertEquals(expectedCustomer, customerService.getCustomerById(1));
    }

    @Test
    public void selectCustomerById_nonExistingId_null() {
        when(mockRepo.selectCustomerById(100)).thenReturn(null);
        assertNull(customerService.getCustomerById(100));
    }

    @Test
    public void selectCustomerByName_customerNameHanna_correctCustomer() {
        ArrayList<Customer> customers = new ArrayList<>();
        customers.add(getTestCustomerList().get(1));
        when(mockRepo.selectCustomersByName("Hanna")).thenReturn(customers);
        assertEquals("Hanna", customerService.getCustomerByName("Hanna").get(0).getFirstName());
    }

    @Test
    public void selectCustomerByName_nonExistingName_emptyList() {
        when(mockRepo.selectCustomersByName("Billy")).thenReturn(new ArrayList<>());

        assertEquals(0, customerService.getCustomerByName("Billy").size());
    }

    @Test
    public void createCustomer_customer_true() {
        when(mockRepo.createCustomer(new Customer(4, "Bill", "Fenrin", "USA", 9467, "13255233", "bill@gmail.com"))).thenReturn(true);

        assertTrue(customerService.addCustomer(new Customer(4, "Bill", "Fenrin", "USA", 9467, "13255233", "bill@gmail.com")));
    }

    @Test
    public void createCustomer_existingId_false() {
        when(mockRepo.createCustomer(new Customer(3, "Bill", "Fenrin", "USA", 9467, "13255233", "bill@gmail.com"))).thenReturn(false);

        assertTrue(customerService.addCustomer(new Customer(3, "Bill", "Fenrin", "USA", 9467, "13255233", "bill@gmail.com")));
    }
}