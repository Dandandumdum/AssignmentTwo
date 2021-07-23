# AssignmentTwo
AssignmentTwo for Noroff and Experis academy

This project follows the outline of the specification provided by Noroff, as outlined below.

Use plain Java to create a Spring Boot Web API, and use Thmyleaf to create a view with the following mimnimum requirements:
a)	Access the Chinook SQL Lite database through JDBC.
b)	A Thymeleaf view to show database data.

These requirements were stringent to several additional customer functionality requirements which were achieved through the manipulation of the Chinook SQL Lite database.
These were:
1.	Read all customers in the database, and display their details. Standard details to display were: Id , first name, last name, country, postal code, phone number and email.
2.	Read a specific customer from the database (by Id), then display them in accordance to the structure listed in requirement 1.
3.	Read a specific customer by name.
4.	Return a page of customers based upon user parameters (limit and offset). The customer model from above was to be used to display the customers.
5.	Add a new customer to the database.
6.	Update an existing customer.
7.	Return the number of customers in each country, in descending order.
8.	Return a list of customers whom spend the most, in descending order.
9.	Find the most popular genre purchased by a customer and list that information beside the customer model.

Thymeleaf was used to created an appealing front-end that the user could use to search for tracks in the Chinook SQL lite database and retrieve the track name, artist, album 
and genre. This aspect of the assignment is being hosted via heroku here: https://assignment2-test2.herokuapp.com/

The following endpoints were created in order to use respective REST method, which have been tested using POSTMAN:

- GET
  - /api/v1/customers
    - /id/{id}
    - /name/{firstName}
    - /country
    - /spending
    - /subset/{limit}/{offset}
    - /genre/customer/{id}
  - /api/v1/tracks
    - /
    - /results
- POST
  - /api/v1/customers
- PUT
  - /api/v1/customers/id/{id}
  - 
Authors Daniel Dumville and Robin Eliasson
