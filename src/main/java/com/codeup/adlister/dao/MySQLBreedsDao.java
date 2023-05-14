package com.codeup.adlister.dao;

import com.codeup.adlister.models.Breed;
import com.codeup.adlister.util.Config;
import com.mysql.cj.jdbc.Driver;

import java.sql.*;
import java.util.List;

public class MySQLBreedsDao implements Breeds{
    private Connection connection;
    public MySQLBreedsDao(Config config) {
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
    public List<Breed> searchAll() {
        return null;
    }

    @Override
    public Breed searchOne(int breedId) {
        return null;
    }

    @Override
    public void edit(int breedId) {

    }

    @Override
    public void delete(int breedId) {

    }
}
