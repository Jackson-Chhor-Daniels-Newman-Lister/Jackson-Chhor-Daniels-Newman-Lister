package com.codeup.adlister.dao;

import com.codeup.adlister.models.Trait;
import com.codeup.adlister.models.UserAd;
import com.codeup.adlister.util.Config;
import com.mysql.cj.jdbc.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
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
    public UserAd searchOne(int AdId) {
        return null;
    }

    @Override
    public void edit(int userId, int AdId) {

    }

    @Override
    public void delete(int AdId) {

    }
}
