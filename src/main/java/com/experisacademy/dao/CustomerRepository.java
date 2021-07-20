package com.experisacademy.dao;

import com.experisacademy.model.Customer;
import com.experisacademy.model.CustomerCountry;
import com.experisacademy.model.CustomerGenre;
import com.experisacademy.model.CustomerSpender;
import org.springframework.web.bind.annotation.RestController;

import java.sql.*;
import java.util.ArrayList;

@RestController
public class CustomerRepository implements CustomerDao {
    String URL = "jdbc:sqlite::resource:Chinook_Sqlite.sqlite";

    //Selects and returns all customers from the database. Customers are added to Arraylist customers which is then returned.
    public ArrayList<Customer> selectCustomers() {
        ArrayList<Customer> customers = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(URL)) {
            System.out.println("Connection to SQLite has been established.");

            PreparedStatement preparedStatement =
                    conn.prepareStatement("SELECT CustomerId, FirstName, LastName, Country, PostalCode, Phone, Email FROM Customer");

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next())
                customers.add(getCustomer(resultSet));

        } catch (Exception ex) {
            System.out.println("Something went wrong...\n" + ex);
        }
        return customers;
    }

    //Selects specific customer from database, selected data is chosen by customerId input parameter.
    public Customer selectCustomerById(long customerId) {
        Customer customer = null;
        try (Connection conn = DriverManager.getConnection(URL)) {
            System.out.println("Connection to SQLite has been established.");

            PreparedStatement preparedStatement =
                    conn.prepareStatement("SELECT CustomerId, FirstName,LastName, Country, PostalCode, Phone, Email FROM Customer WHERE CustomerId = ?");
            preparedStatement.setLong(1, customerId);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next())
                customer = getCustomer(resultSet);

        } catch (Exception ex) {
            System.out.println("Something went wrong...\n" + ex);
        }
        return customer;
    }

    //Selects specific customer from database, selected data is chosen by customerName input parameter.
    public ArrayList<Customer> selectCustomersByName(String customerName) {
        ArrayList<Customer> customers = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(URL)) {
            System.out.println("Connection to SQLite has been established.");

            PreparedStatement preparedStatement =
                    conn.prepareStatement("SELECT CustomerId, FirstName,LastName, Country, PostalCode, Phone, Email FROM Customer WHERE FirstName LIKE ?");
            preparedStatement.setString(1, customerName);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next())
                customers.add(getCustomer(resultSet));

        } catch (Exception ex) {
            System.out.println("Something went wrong...\n" + ex);
        }
        return customers;
    }

    //Adds new com.experisacademy.model.Customer object to the database, based upon com.experisacademy.model.Customer object parameter
    public boolean createCustomer(Customer newCustomer) {
        try (Connection conn = DriverManager.getConnection(URL)) {
            System.out.println("Connection to SQLite has been established.");

            PreparedStatement preparedStatement =
                    conn.prepareStatement("INSERT INTO Customer (CustomerId,FirstName,LastName, Country, PostalCode, Phone, Email) VALUES (?,?,?,?,?,?,?)");
            preparedStatement.setLong(1, newCustomer.getId());
            preparedStatement.setString(2, newCustomer.getFirstName());
            preparedStatement.setString(3, newCustomer.getLastName());
            preparedStatement.setString(4, newCustomer.getCountry());
            preparedStatement.setInt(5, newCustomer.getPostalCode());
            preparedStatement.setString(6, newCustomer.getPhoneNumber());
            preparedStatement.setString(7, newCustomer.getEmail());
            preparedStatement.executeUpdate();


            System.out.println("com.experisacademy.model.Customer added");
            return true;

        } catch (Exception ex) {
            System.out.println("Something went wrong...\n" + ex);
            return false;
        }
    }

    //Updates database with a com.experisacademy.model.Customer Object based upon a new com.experisacademy.model.Customer Objects details, input into methods parameters
    //,update based upon CustomerId.
    public boolean updateCustomer(long id, Customer newCustomerDetails) {
        try (Connection conn = DriverManager.getConnection(URL)) {
            System.out.println("Connection to SQLite has been established.");

            PreparedStatement preparedStatement =
                    conn.prepareStatement("UPDATE Customer SET FirstName = ? ,LastName = ?, Country = ?, PostalCode = ?, Phone = ?, Email = ? WHERE CustomerId = ?");
            preparedStatement.setString(1, newCustomerDetails.getFirstName());
            preparedStatement.setString(2, newCustomerDetails.getLastName());
            preparedStatement.setString(3, newCustomerDetails.getCountry());
            preparedStatement.setInt(4, newCustomerDetails.getPostalCode());
            preparedStatement.setString(5, newCustomerDetails.getPhoneNumber());
            preparedStatement.setString(6, newCustomerDetails.getEmail());
            preparedStatement.setLong(7, id);
            preparedStatement.executeUpdate();

            System.out.println("com.experisacademy.model.Customer added");
            return true;

        } catch (Exception ex) {
            System.out.println("Something went wrong...\n" + ex);
            return false;
        }
    }

    //Orders Grouped OrderedCustomer objects based upon the how many are from a distinct country.
    //Customers are added to Arraylist orderedCustomers which is then returned.
    public ArrayList<CustomerCountry> selectCountriesByOrderCount() {
        ArrayList<CustomerCountry> countryCustomers = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(URL)) {
            System.out.println("Connection to SQLite has been established.");
            PreparedStatement preparedStatement =
                    conn.prepareStatement("SELECT COUNT(CustomerId), Country FROM Customer GROUP BY Country ORDER BY COUNT(CustomerId) DESC");

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next())
                countryCustomers.add(new CustomerCountry(resultSet.getInt("COUNT(CustomerId)"), resultSet.getString("Country")));

        } catch (Exception ex) {
            System.out.println("Something went wrong...\n" + ex);
        }
        return countryCustomers;
    }

    //Selects a subset of customers from an ArrayList of all customers. Subset is determined by a limit and an offset.
    //The offset dictates the starting index, and the limit dictates the amount of objects in the subset.
    //Customers are added to Arraylist customers which is then returned.
    public ArrayList<Customer> selectSubsetCustomers(int limit, int offset) {
        ArrayList<Customer> customers = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(URL)) {
            System.out.println("Connection to SQLite has been established.");

            PreparedStatement preparedStatement =
                    conn.prepareStatement("SELECT CustomerId, FirstName,LastName, Country, PostalCode, Phone, Email  FROM Customer LIMIT ? ,?");
            preparedStatement.setInt(1, offset);
            preparedStatement.setInt(2, limit);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next())
                customers.add(getCustomer(resultSet));

        } catch (Exception ex) {
            System.out.println("Something went wrong...\n" + ex);
        }
        return customers;

    }

    //Performs an INNER JOIN on Invoice from com.experisacademy.model.Customer to allow for a com.experisacademy.model.Customer object ArrayList to be sorted by Invoice.total, descending
    //Customers are added to Arraylist customers which is then returned.
    public ArrayList<CustomerSpender> selectCustomersHighestSpenders() {
        ArrayList<CustomerSpender> customers = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(URL)) {
            System.out.println("Connection to SQLite has been established.");

            PreparedStatement preparedStatement =
                    conn.prepareStatement("""
                            SELECT CustomerId, FirstName, LastName, Country,
                                   PostalCode, Phone, Email ,Invoice.Total
                            FROM Customer INNER JOIN Invoice  on Customer.CustomerId = Invoice.CustomerId
                            ORDER BY Invoice.Total DESC""");

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                customers.add(
                        new CustomerSpender(
                                resultSet.getLong("CustomerId"),
                                resultSet.getInt("Invoice.Total")
                        )
                );
            }

        } catch (Exception ex) {
            System.out.println("Something went wrong...\n" + ex);
        }
        return customers;
    }

    //Performs several INNER JOINs to unify the database enabling for com.experisacademy.model.Customer data to be order by favourite Genre
    public CustomerGenre specificCustomerPopularGenre(long customerId) {
        CustomerGenre customer = null;
        try (Connection conn = DriverManager.getConnection(URL)) {
            System.out.println("Connection to SQLite has been established.");

            PreparedStatement preparedStatement =
                    conn.prepareStatement("""
                            SELECT  Customer.CustomerId, Customer.FirstName,Customer.LastName, Customer.Country,
                                   Customer.PostalCode, Customer.Phone, Customer.Email, Genre.Name
                            FROM Customer INNER JOIN Invoice on Customer.CustomerId = Invoice.CustomerId
                                INNER JOIN InvoiceLine ON Invoice.InvoiceId = InvoiceLine.InvoiceId
                            INNER JOIN Track on InvoiceLine.TrackId = Track.TrackId
                            INNER JOIN Genre on Track.GenreId = Genre.GenreId WHERE Customer.CustomerId = ?
                            GROUP BY Genre.Name
                            ORDER BY COUNT(InvoiceLine.Quantity) DESC LIMIT 1""");
            preparedStatement.setLong(1, customerId); // Corresponds to 1st '?' (must match type)

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                customer = new CustomerGenre(
                        resultSet.getLong("CustomerId"),
                        resultSet.getString("FirstName"),
                        resultSet.getString("LastName"),
                        resultSet.getString("Country"),
                        resultSet.getInt("PostalCode"),
                        resultSet.getString("Phone"),
                        resultSet.getString("Email"),
                        resultSet.getString("Name")

                );

            }
        } catch (Exception ex) {
            System.out.println("Something went wrong...\n" + ex);
        }
        return customer;
    }

    private Customer getCustomer(ResultSet resultSet) throws SQLException {
        return new Customer(resultSet.getLong("CustomerId"),
                resultSet.getString("FirstName"),
                resultSet.getString("LastName"),
                resultSet.getString("Country"),
                resultSet.getInt("PostalCode"),
                resultSet.getString("Phone"),
                resultSet.getString("Email"));
    }

}
