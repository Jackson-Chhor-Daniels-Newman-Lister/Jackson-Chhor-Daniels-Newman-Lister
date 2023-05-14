package com.codeup.adlister.dao;

import com.codeup.adlister.models.DogBreed;
import com.codeup.adlister.util.Config;
import com.mysql.cj.jdbc.Driver;

import java.sql.*;
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
        try {
            String insertQuery = "INSERT INTO dog_breeds(dog_id, breed_id) VALUES (?, ?)";

            PreparedStatement stmt = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, dogId);
            stmt.setInt(2, breedId);
            System.out.println("stmt = " + stmt);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error inserting into dog_breeds table. DogID: " + dogId + "BreedId: " + breedId, e);
        }
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
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement("UPDATE dog_breeds SET breed_id = ? WHERE dog_id = ?;");
            stmt.setInt(1, breedId);
            stmt.setInt(2, dogId);
            System.out.println("stmt = " + stmt);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error editing breed id: " + breedId + "for dog id: " + dogId, e);
        }
    }

    @Override
    public void delete(int dogId) {
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement("DELETE FROM dog_breeds WHERE dog_id = ?;");
            stmt.setInt(1, dogId);
            System.out.println("stmt = " + stmt);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting dog's breed, id: " + dogId, e);
        }
    }
}
