package com.codeup.adlister.dao;

import com.codeup.adlister.models.Trait;
import com.codeup.adlister.models.UserAd;
import com.codeup.adlister.util.Config;
import com.mysql.cj.jdbc.Driver;

import java.sql.*;
import java.util.List;

public class MySQLUserAdsDao implements UserAds{
    private Connection connection;
    public MySQLUserAdsDao(Config config) {
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
    public void insert(int userId, int adId) {
        try {
            String insertQuery = "INSERT INTO user_ads(user_id, ad_id) VALUES (?, ?)";

            PreparedStatement stmt = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, userId);
            stmt.setInt(2, adId);

            System.out.println("stmt = " + stmt);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error inserting into user_ad table. UserID: " + userId + "AdID: " + adId, e);
        }
    }

    @Override
    public List<UserAd> searchAll() {
        return null;
    }

    @Override
    public boolean searchOne(int adId, int userId) {
        String query = "SELECT * FROM user_ads WHERE ad_id = ? AND user_id = ? LIMIT 1";
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setLong(1, adId);
            stmt.setLong(2, userId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return true;
            }
            return false;
        } catch (SQLException e) {
            throw new RuntimeException("Error verifying user: " + userId + " owns ad: " + adId, e);
        }
    }

    @Override
    public void edit(int userId, int adId) {

    }

    @Override
    public void delete(int adId) {
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement("DELETE FROM user_ads WHERE ad_id = ?;");
            stmt.setInt(1, adId);
            System.out.println("stmt = " + stmt);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting ad id: " + adId, e);
        }
    }
}
