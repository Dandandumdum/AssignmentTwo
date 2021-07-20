package com.experisacademy.dao;

public class ConnectionHelper {

    public static String getConnectionURL() {
        return "jdbc:sqlite::resource:Chinook_Sqlite.sqlite";
    }

}
