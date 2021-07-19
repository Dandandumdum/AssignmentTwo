package com.experisacademy.model;

public class CustomerCountry {
    private int count;
    private String name;

    public CustomerCountry(int count, String countryName){
        this.count = count;
        name = countryName;
    }

    public int getCount() {
        return count;
    }

    public String getName() {
        return name;
    }




}
