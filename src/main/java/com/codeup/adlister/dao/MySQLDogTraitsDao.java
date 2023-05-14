package com.codeup.adlister.dao;

import com.codeup.adlister.models.DogTrait;
import com.codeup.adlister.util.Config;
import com.mysql.cj.jdbc.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class MySQLDogTraitsDao implements DogTraits{
    private Connection connection;
    public MySQLDogTraitsDao(Config config) {
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
    public void insert(int dogId, int traitId) {

    }

    @Override
    public List<DogTrait> searchAll() {
        return null;
    }

    @Override
    public DogTrait searchOne(int dogId) {
        return null;
    }

    @Override
    public void edit(int dogId, int traitId) {

    }

    @Override
    public void delete(int dogId) {

    }
}
