package com.experisacademy.model;

public class Customer extends Entity {

    private String firstName, lastName, country, phoneNumber, email;
    private int postalCode;

    public Customer(long id, String firstName, String lastName, String country, int postalCode, String phoneNumber, String email) {
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.country = country;
        this.postalCode = postalCode;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getCountry() {
        return country;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }
}
