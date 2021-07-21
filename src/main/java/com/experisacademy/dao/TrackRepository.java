package com.experisacademy.dao;

import com.experisacademy.model.Track;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository("tracks")
public class TrackRepository implements TrackDao {

    private String sql;

    @Override
    public ArrayList<String> select(String table, int limit) {
        ArrayList<String> names = new ArrayList<>();

        try(Connection con = DriverManager.getConnection(ConnectionHelper.getConnectionURL())) {
            System.out.println("Connection to SQLite has been established.");

            PreparedStatement preparedStatement = con.prepareStatement(
                    "SELECT Name FROM " + table + " ORDER BY RANDOM() LIMIT ? ");
            preparedStatement.setInt(1, limit);

            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                names.add(resultSet.getString("Name"));
            }


        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return names;
    }
    @Override
    public Track selectTrack(String trackName) {
        return null;
    }
}
