package com.codeup.adlister.dao;

import com.codeup.adlister.models.DogTrait;
import com.codeup.adlister.util.Config;
import com.mysql.cj.jdbc.Driver;

import java.sql.*;
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
    public void insert(int dogId, int[] traitIds) {
        PreparedStatement stmt = null;
        try {
            for (int dogTrait : traitIds) {
                String insertQuery = "INSERT INTO dog_traits(dog_id, trait_id) VALUES (?, ?)";

                stmt = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
                stmt.setInt(1, dogId);
                stmt.setInt(2, dogTrait);
                System.out.println("stmt = " + stmt);
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error inserting into dog_traits table. DogID: " + dogId, e);
        }
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
    public void edit(int dogId, int traitIds[]) {
        delete(dogId);
        PreparedStatement stmt = null;
        try {
            for (int dogTraitId : traitIds) {
                stmt = connection.prepareStatement("INSERT INTO dog_traits (dog_id, trait_id) VALUES (?,?)");
                stmt.setInt(1, dogId);
                stmt.setInt(2, dogTraitId);
                System.out.println("stmt = " + stmt);
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error editing traits for dog id: " + dogId, e);
        }
    }

    @Override
    public void delete(int dogId) {
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement("DELETE FROM dog_traits WHERE dog_id = ?");
            stmt.setInt(1, dogId);
            System.out.println("stmt = " + stmt);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error editing traits for dog id: " + dogId, e);
        }
    }
}
