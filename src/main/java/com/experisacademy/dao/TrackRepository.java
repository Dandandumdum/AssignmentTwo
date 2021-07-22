package com.experisacademy.dao;

import com.experisacademy.model.Track;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository("tracks")
public class TrackRepository implements TrackDao {

    private static final String URL = ConnectionHelper.getConnectionURL();

    @Override
    public ArrayList<String> select(String table, int limit) {
        ArrayList<String> names = new ArrayList<>();

        try (Connection con = DriverManager.getConnection(URL)) {
            System.out.println("Connection to SQLite has been established.");

            PreparedStatement preparedStatement = con.prepareStatement(
                    "SELECT Name FROM " + table + " ORDER BY RANDOM() LIMIT ? ");
            preparedStatement.setInt(1, limit);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                names.add(resultSet.getString("Name"));
            }


        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return names;
    }

    @Override
    public Track selectTrack(String trackName) {
        try (Connection con = DriverManager.getConnection(URL)) {
            System.out.println("Connection to SQLite has been established.");
            PreparedStatement preparedStatement = con.prepareStatement("""
                    SELECT t.Name AS TitleName, alb.Title AS AlbumTitle, art.Name AS ArtistName, g.Name AS GenreName
                    FROM Track AS t, Album AS alb, Artist AS art, Genre as g
                    WHERE t.AlbumId = alb.AlbumId AND alb.ArtistId = art.ArtistId
                    AND t.GenreId = g.GenreId AND t.Name = ?
                    """);
            preparedStatement.setString(1, trackName);

            ResultSet resultSet = preparedStatement.executeQuery();
            return new Track(
                    resultSet.getString("TitleName"),
                    resultSet.getString("ArtistName"),
                    resultSet.getString("AlbumTitle"),
                    resultSet.getString("GenreName"));

        } catch (SQLException ex) {
            return null;
        }
    }
}
