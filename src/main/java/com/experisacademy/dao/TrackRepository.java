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
        ArrayList<String> song = new ArrayList<>();

        try(Connection con = DriverManager.getConnection(ConnectionHelper.getConnectionURL())) {
            System.out.println("Connection to SQLite has been established.");

            PreparedStatement preparedStatement = con.prepareStatement(
                    "SELECT Track.Name, Artist.Name, Album.Title, Genre.Name FROM Track\n" +
                            "INNER JOIN Album  on Track.AlbumId = Album.AlbumId\n" +
                            "INNER JOIN Artist  on Album.ArtistId = Artist.ArtistId\n" +
                            "INNER JOIN Genre on Track.GenreId = Genre.GenreId\n" +
                            "WHERE Track.Name LIKE ?");
            preparedStatement.setString(1, trackName);

            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                song.add(resultSet.getString("Track.Name"));
                song.add(resultSet.getString("Artist.Name"));
                song.add(resultSet.getString("Album.Title"));
                song.add(resultSet.getString("Genre.Name"));
            }


        } catch (SQLException ex) {
            ex.printStackTrace();
        }


        return null;
    }
}
