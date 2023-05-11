package com.codeup.adlister.dao;

import com.codeup.adlister.models.Ad;
import com.codeup.adlister.models.Breed;
import com.codeup.adlister.util.Config;
import com.mysql.cj.jdbc.Driver;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLAdsDao implements Ads {
    private Connection connection = null;

    public MySQLAdsDao(Config config) {
        try {
            DriverManager.registerDriver(new Driver());
            connection = DriverManager.getConnection(
                config.getUrl(),
                config.getUser(),
                config.getPassword()
            );
        } catch (SQLException e) {
            throw new RuntimeException("Error connecting to the database!", e);
        }
    }

    @Override
    public List<Ad> all() {
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement("SELECT * FROM ads");
            ResultSet rs = stmt.executeQuery();
            return createAdsFromResults(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving all ads.", e);
        }
    }


    @Override
    public List<Breed> breedSelector() {
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement("SELECT * FROM breeds");
            ResultSet rs = stmt.executeQuery();
            return breedList(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving all breeds.", e);
        }
    }

    @Override
    public List<String>traits() {
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement("SELECT * FROM traits");
            ResultSet rs = stmt.executeQuery();
            return traitList(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving all traits.", e);
        }
    }


    @Override
    public Ad individual(long adNumber) {
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement("SELECT * FROM ads WHERE id = ?");
            stmt.setLong(1, adNumber);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            return extractAd(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving more info on ads.", e);
        }
    }

    @Override
    public Long insert(Ad ad) {
        try {
            //(String title, String description, String shortDescription, int price, int dogId)
            String insertQuery = "INSERT INTO ads(title, description, short_description, price, image, dog_id) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";
          
            PreparedStatement stmt = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, ad.getTitle());
            stmt.setString(2, ad.getShortDescription());
            stmt.setString(3, ad.getDescription());
            stmt.setLong(4, ad.getPrice());
            stmt.setString(5, ad.getImage());
            stmt.setLong(6, ad.getDogId());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            return rs.getLong(1);
        } catch (SQLException e) {
            throw new RuntimeException("Error creating a new ad.", e);
        }
    }



    private Ad extractAd(ResultSet rs) throws SQLException {
        return new Ad(
            rs.getLong("id"),
            rs.getString("title"),
            rs.getString("short_description"),
            rs.getString("description"),
            rs.getInt("price"),
            rs.getString("image"),
            rs.getInt("dog_id")
        );
    }

    private List<Ad> createAdsFromResults(ResultSet rs) throws SQLException {
        List<Ad> ads = new ArrayList<>();
        while (rs.next()) {
            ads.add(extractAd(rs));
        }
        return ads;
    }

    private List<Breed> breedList(ResultSet rs) throws SQLException {
        List<Breed> breeds = new ArrayList<>();
        while (rs.next()) {
            breeds.add(extractBreed(rs));
        }
        return breeds;
    }

    private List<String> traitList(ResultSet rs) throws SQLException {
        List<String> breeds = new ArrayList<>();
        while (rs.next()) {
            breeds.add(extractTraits(rs));
        }
        return breeds;
    }

    private Breed extractBreed(ResultSet rs) throws SQLException {
        return new Breed(
                rs.getLong("id"),
                rs.getString("name")
        );
    }

    private String extractTraits(ResultSet rs) throws SQLException {
        return rs.getString("name");
    }
}
