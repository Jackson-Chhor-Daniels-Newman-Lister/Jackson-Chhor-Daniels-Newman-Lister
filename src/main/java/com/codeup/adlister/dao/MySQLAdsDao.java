package com.codeup.adlister.dao;

import com.codeup.adlister.models.Ad;
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
    public Ad individual(long adNumber) {
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement("SELECT * FROM ads WHERE id = ${Int.parseInt(adNumber)}");
            ResultSet rs = stmt.executeQuery();
            return createOneAdFromResults(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving more info on ads.", e);
        }
    }

    @Override
    public Long insert(Ad ad) {
        try {
            //(String title, String description, String shortDescription, int price, int dogId)
            String insertQuery = "INSERT INTO ads(title, description, short_description, price, dog_id) " +
                    "VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, ad.getTitle());
            stmt.setString(2, ad.getDescription());
            stmt.setString(3, ad.getShortDescription());
            stmt.setLong(4, ad.getPrice());
            stmt.setLong(5, ad.getDogId());
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

    private Ad createOneAdFromResults(ResultSet rs) throws SQLException {
//        List<Ad> ads = new ArrayList<>();
        while (rs.next()) {
//            ads.add(extractAd(rs));
            return extractAd(rs);
        }
        return extractAd(rs);
    }
}
