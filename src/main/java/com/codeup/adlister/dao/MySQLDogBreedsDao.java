package com.codeup.adlister.dao;

import com.codeup.adlister.models.DogBreed;
import com.codeup.adlister.util.Config;
import com.mysql.cj.jdbc.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class MySQLDogBreedsDao implements DogBreeds{
    private Connection connection;
    public MySQLDogBreedsDao(Config config) {
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
    public void insert(int dogId, int breedId) {

    }

    @Override
    public List<DogBreed> searchAll() {
        return null;
    }

    @Override
    public DogBreed searchOne(int dogId) {
        return null;
    }

    @Override
    public void edit(int dogId, int breedId) {

    }

    @Override
    public void delete(int dogId) {

    }
}
