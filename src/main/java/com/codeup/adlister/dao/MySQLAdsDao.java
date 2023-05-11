package com.codeup.adlister.dao;

import com.codeup.adlister.models.Ad;
import com.codeup.adlister.util.Config;
import com.mysql.cj.jdbc.Driver;

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
    public List<Object> all(String tableName) {
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement("SELECT * FROM " + tableName);
            ResultSet rs = stmt.executeQuery();
            return createListFromResults(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving from table: " + tableName, e);
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

    public Ad userAd (long userAdId) {
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement("SELECT * FROM user_ads WHERE user_id = ?");
            stmt.setLong(1, userAdId);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            return extractAd(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving user ads.", e);
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

    private Ad createOneAdFromResults(ResultSet rs) throws SQLException {
//        List<Ad> ads = new ArrayList<>();
        while (rs.next()) {
//            ads.add(extractAd(rs));
            return extractAd(rs);
        }
        return extractAd(rs);
    }
}
