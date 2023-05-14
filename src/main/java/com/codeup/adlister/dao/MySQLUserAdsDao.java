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
    public void insert(int userId, int AdId) {

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
    public void edit(int userId, int AdId) {

    }

    @Override
    public void delete(int AdId) {

    }
}
