package com.codeup.adlister.dao;

import com.codeup.adlister.models.Trait;
import com.codeup.adlister.util.Config;
import com.mysql.cj.jdbc.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class MySQLTraitsDao implements Traits{
    private Connection connection;
    public MySQLTraitsDao(Config config) {
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
    public void insert(String name) {

    }

    @Override
    public List<Trait> searchAll() {
        return null;
    }

    @Override
    public Trait searchOne(int traitId) {
        return null;
    }

    @Override
    public void edit(int traitId) {

    }

    @Override
    public void delete(int traitId) {

    }
}
