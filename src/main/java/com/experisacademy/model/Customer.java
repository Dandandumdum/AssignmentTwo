package com.experisacademy.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Customer extends Entity {

    private String firstName, lastName, country, phoneNumber, email;
    private int postalCode;

    public Customer(@JsonProperty("id") long id,
                    @JsonProperty("firstName") String firstName,
                    @JsonProperty("lastName") String lastName,
                    @JsonProperty("country") String country,
                    @JsonProperty("postalCode") int postalCode,
                    @JsonProperty("phoneNumber") String phoneNumber,
                    @JsonProperty("email") String email) {
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

    @Override
    public boolean equals(Object o) {
        if(o == this)
            return true;
        else if(!(o instanceof Customer))
            return false;

        Customer customer = (Customer)o;
        //Since id is unique this is the only thing that needs to be checked.
        return customer.getId() == this.getId();
    }
}
