package com.codeup.adlister.dao;

import com.codeup.adlister.models.Dog;
import com.codeup.adlister.util.Config;
import com.mysql.cj.jdbc.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class MySQLDogsDao implements Dogs{
    private Connection connection;
    public MySQLDogsDao(Config config) {
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
    public void insert(String name, int age, String playfulness, String socialization, String affection, String training) {

    }

    @Override
    public List<Dog> searchAll() {
        return null;
    }

    @Override
    public Dog searchOne(int dogId) {
        return null;
    }

    @Override
    public void edit(int dogId) {

    }

    @Override
    public void delete(int dogId) {

    }
}
