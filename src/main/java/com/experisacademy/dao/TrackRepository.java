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
                    SELECT Name, Title, Artist.Name, Genre.Name
                    FROM Track, Album
                    WHERE Track.AlbumId = Album.AlbumId AND Album.ArtistId = Artist.ArtistId
                    AND Track.GenreId = Genre.GenreId AND Track.Name = ?
                    """);
            preparedStatement.setString(1, trackName);

            ResultSet resultSet = preparedStatement.executeQuery();
            Track track = null;
            System.out.println(resultSet.getString("Name"));
            while (resultSet.next()) {
                track = new Track(
                        resultSet.getString("Track.Name"),
                        resultSet.getString("Artist.Name"),
                        resultSet.getString("Album.Name"),
                        resultSet.getString("Genre.Name"));
            }
            System.out.println("TEST2");

            return track;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
