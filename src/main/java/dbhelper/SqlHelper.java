package dbhelper;
import Customer.*;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

@RestController
public class SqlHelper {
    String URL = "jdbc:sqlite::resource:Chinook_Sqlite.sqlite";
    Connection conn = null;

    public ArrayList<Customer> selectAllCustomers() {
        ArrayList<Customer> customers = new ArrayList<Customer>();
        try {
            conn = DriverManager.getConnection(URL);
            System.out.println("Connection to SQLite has been established.");

            PreparedStatement preparedStatement =
                    conn.prepareStatement("SELECT CustomerId, FirstName,LastName, Country, PostalCode, Phone, Email FROM Customer");

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                customers.add(
                        new Customer(
                                resultSet.getString("CustomerId"),
                                resultSet.getString("FirstName"),
                                resultSet.getString("LastName"),
                                resultSet.getString("Country"),
                                resultSet.getString("PostalCode"),
                                resultSet.getString("Phone"),
                                resultSet.getString("Email")

                        ));
            }
        } catch (Exception ex) {
            System.out.println("Something went wrong...");
            System.out.println(ex.toString());
        } finally {
            try {
                conn.close();
            } catch (Exception ex) {
                System.out.println("Something went wrong while closing connection.");
                System.out.println(ex.toString());
            }
            return customers;
        }
    }

    public Customer selectSpecificCustomer(String customerId) {
        Customer customer = null;
        try {
            conn = DriverManager.getConnection(URL);
            System.out.println("Connection to SQLite has been established.");

            PreparedStatement preparedStatement =
                    conn.prepareStatement("SELECT CustomerId, FirstName,LastName, Country, PostalCode, Phone, Email FROM Customer WHERE CustomerId = ?");
            preparedStatement.setString(1, customerId); // Corresponds to 1st '?' (must match type)

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                customer = new Customer(
                        resultSet.getString("CustomerId"),
                        resultSet.getString("FirstName"),
                        resultSet.getString("LastName"),
                        resultSet.getString("Country"),
                        resultSet.getString("PostalCode"),
                        resultSet.getString("Phone"),
                        resultSet.getString("Email")
                );
            }
        } catch (Exception ex) {
            System.out.println("Something went wrong...");
            System.out.println(ex.toString());
        } finally {
            try {
                conn.close();
            } catch (Exception ex) {
                System.out.println("Something went wrong while closing connection.");
                System.out.println(ex.toString());
            }
            return customer;
        }
    }

    public Customer selectSpecificCustomerByName(String customerName) {
        Customer customer = null;
        try {
            conn = DriverManager.getConnection(URL);
            System.out.println("Connection to SQLite has been established.");

            PreparedStatement preparedStatement =
                    conn.prepareStatement("SELECT CustomerId, FirstName,LastName, Country, PostalCode, Phone, Email FROM Customer WHERE FirstName LIKE ?");
            preparedStatement.setString(1, customerName); // Corresponds to 1st '?' (must match type)
            //Must search based upon input

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                customer = new Customer(
                        resultSet.getString("CustomerId"),
                        resultSet.getString("FirstName"),
                        resultSet.getString("LastName"),
                        resultSet.getString("Country"),
                        resultSet.getString("PostalCode"),
                        resultSet.getString("Phone"),
                        resultSet.getString("Email")
                );
            }
        } catch (Exception ex) {
            System.out.println("Something went wrong...");
            System.out.println(ex.toString());
        } finally {
            try {
                conn.close();
            } catch (Exception ex) {
                System.out.println("Something went wrong while closing connection.");
                System.out.println(ex.toString());
            }
            return customer;
        }
    }

    public Boolean addNewCustomer(Customer newCustomer) {
        Customer customer = null;
        try {
            conn = DriverManager.getConnection(URL);
            System.out.println("Connection to SQLite has been established.");

            PreparedStatement preparedStatement =
                    conn.prepareStatement("INSERT INTO Customer (CustomerId,FirstName,LastName, Country, PostalCode, Phone, Email) VALUES (?,?,?,?,?,?,?)");
            preparedStatement.setString(1, newCustomer.getId());
            preparedStatement.setString(2, newCustomer.getFirstName());
            preparedStatement.setString(3, newCustomer.getLastName());
            preparedStatement.setString(4, newCustomer.getCountry());
            preparedStatement.setString(5, newCustomer.getPostalCode());
            preparedStatement.setString(6, newCustomer.getPhoneNumber());
            preparedStatement.setString(7, newCustomer.getEmail());
            preparedStatement.executeUpdate();


            System.out.println("Customer added");
            return true;

        } catch (Exception ex) {
            System.out.println("Something went wrong...");
            System.out.println(ex.toString());
        } finally {
            try {
                conn.close();
            } catch (Exception ex) {
                System.out.println("Something went wrong while closing connection.");
                System.out.println(ex.toString());
            }
            return true;
        }
    }

    public Boolean updateCustomer(Customer updateCustomer, Customer newCustomerDetails) {
        Customer customer = null;
        try {
            conn = DriverManager.getConnection(URL);
            System.out.println("Connection to SQLite has been established.");

            PreparedStatement preparedStatement =
                    conn.prepareStatement("UPDATE Customer SET CustomerId = ?, FirstName = ? ,LastName = ?, Country = ?, PostalCode = ?, Phone = ?, Email = ? WHERE CustomerId = ?");
            preparedStatement.setString(1, newCustomerDetails.getId());
            preparedStatement.setString(2, newCustomerDetails.getFirstName());
            preparedStatement.setString(3, newCustomerDetails.getLastName());
            preparedStatement.setString(4, newCustomerDetails.getCountry());
            preparedStatement.setString(5, newCustomerDetails.getPostalCode());
            preparedStatement.setString(6, newCustomerDetails.getPhoneNumber());
            preparedStatement.setString(7, newCustomerDetails.getEmail());
            preparedStatement.setString(8, updateCustomer.getId());
            preparedStatement.executeUpdate();

            System.out.println("Customer added");
            return true;

        } catch (Exception ex) {
            System.out.println("Something went wrong...");
            System.out.println(ex.toString());
        } finally {
            try {
                conn.close();
            } catch (Exception ex) {
                System.out.println("Something went wrong while closing connection.");
                System.out.println(ex.toString());
            }
            return true;
        }
    }

    public ArrayList<OrderedCustomer> orderByCountryCount() {
        ArrayList<OrderedCustomer> orderedCustomers = new ArrayList<OrderedCustomer>();
        try {
            conn = DriverManager.getConnection(URL);
            System.out.println("Connection to SQLite has been established.");
            //SELECT COUNT(CustomerId), Country FROM Customer GROUP BY Country ORDER BY COUNT(CustomerId) DESC
            PreparedStatement preparedStatement =
                    conn.prepareStatement("SELECT COUNT(CustomerId), Country FROM Customer GROUP BY Country ORDER BY COUNT(CustomerId) DESC");

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                orderedCustomers.add(new OrderedCustomer(resultSet.getString("COUNT(CustomerId)"), resultSet.getString("Country")));

            }

        } catch (Exception ex) {
            System.out.println("Something went wrong...");
            System.out.println(ex.toString());
        } finally {
            try {
                conn.close();
            } catch (Exception ex) {
                System.out.println("Something went wrong while closing connection.");
                System.out.println(ex.toString());
            }
            return orderedCustomers;
        }
    }

    public ArrayList<Customer> selectSubsetCustomers(int limit, int offset) {
        ArrayList<Customer> customers = new ArrayList<Customer>();
        try {
            conn = DriverManager.getConnection(URL);
            System.out.println("Connection to SQLite has been established.");

            PreparedStatement preparedStatement =
                    conn.prepareStatement("SELECT CustomerId, FirstName,LastName, Country, PostalCode, Phone, Email  FROM Customer LIMIT ? ,?");
            preparedStatement.setInt(1, offset);
            preparedStatement.setInt(2, limit);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                customers.add(
                        new Customer(
                                resultSet.getString("CustomerId"),
                                resultSet.getString("FirstName"),
                                resultSet.getString("LastName"),
                                resultSet.getString("Country"),
                                resultSet.getString("PostalCode"),
                                resultSet.getString("Phone"),
                                resultSet.getString("Email")

                        ));
            }
        } catch (Exception ex) {
            System.out.println("Something went wrong...");
            System.out.println(ex.toString());
        } finally {
            try {
                conn.close();
            } catch (Exception ex) {
                System.out.println("Something went wrong while closing connection.");
                System.out.println(ex.toString());
            }
            return customers;
        }

    }
    public ArrayList<Customer> orderedCustomersHighestSpenders() {
        ArrayList<Customer> customers = new ArrayList<Customer>();
        try {
            conn = DriverManager.getConnection(URL);
            System.out.println("Connection to SQLite has been established.");

            PreparedStatement preparedStatement =
                    conn.prepareStatement("SELECT Customer.CustomerId, Customer.FirstName,Customer.LastName, Customer.Country,\n" +
                            "       Customer.PostalCode, Customer.Phone, Customer.Email ,Invoice.Total\n" +
                            "FROM Customer INNER JOIN Invoice  on Customer.CustomerId = Invoice.CustomerId\n" +
                            "ORDER BY Invoice.Total DESC");

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                customers.add(
                        new Customer(
                                resultSet.getString("CustomerId"),
                                resultSet.getString("FirstName"),
                                resultSet.getString("LastName"),
                                resultSet.getString("Country"),
                                resultSet.getString("PostalCode"),
                                resultSet.getString("Phone"),
                                resultSet.getString("Email")

                        ));
            }
        } catch (Exception ex) {
            System.out.println("Something went wrong...");
            System.out.println(ex.toString());
        } finally {
            try {
                conn.close();
            } catch (Exception ex) {
                System.out.println("Something went wrong while closing connection.");
                System.out.println(ex.toString());
            }
            return customers;
        }
    }
    public Customer specificCustomerPopularGenre(String customerId) {
        Customer customer = null;
        String favouriteGenre;
        try {
            conn = DriverManager.getConnection(URL);
            System.out.println("Connection to SQLite has been established.");

            PreparedStatement preparedStatement =
                    conn.prepareStatement("SELECT  Customer.CustomerId, Customer.FirstName,Customer.LastName, Customer.Country,\n" +
                            "       Customer.PostalCode, Customer.Phone, Customer.Email, Genre.Name\n" +
                            "FROM Customer INNER JOIN Invoice on Customer.CustomerId = Invoice.CustomerId\n" +
                            "    INNER JOIN InvoiceLine ON Invoice.InvoiceId = InvoiceLine.InvoiceId\n" +
                            "INNER JOIN Track on InvoiceLine.TrackId = Track.TrackId\n" +
                            "INNER JOIN Genre on Track.GenreId = Genre.GenreId WHERE Customer.CustomerId = ?\n" +
                            "GROUP BY Genre.Name\n" +
                            "ORDER BY COUNT(InvoiceLine.Quantity) DESC LIMIT 1");
            preparedStatement.setString(1, customerId); // Corresponds to 1st '?' (must match type)

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                customer = new Customer(
                        resultSet.getString("CustomerId"),
                        resultSet.getString("FirstName"),
                        resultSet.getString("LastName"),
                        resultSet.getString("Country"),
                        resultSet.getString("PostalCode"),
                        resultSet.getString("Phone"),
                        resultSet.getString("Email")

                );
                favouriteGenre = resultSet.getString("Name");
            }
        } catch (Exception ex) {
            System.out.println("Something went wrong...");
            System.out.println(ex.toString());
        } finally {
            try {
                conn.close();
            } catch (Exception ex) {
                System.out.println("Something went wrong while closing connection.");
                System.out.println(ex.toString());
            }
            return customer ;
        }
    }

}
