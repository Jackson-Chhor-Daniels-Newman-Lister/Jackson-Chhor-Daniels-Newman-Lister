package com.codeup.adlister.dao;

import com.codeup.adlister.models.Dog;
import com.codeup.adlister.util.Config;
import com.mysql.cj.jdbc.Driver;

import java.sql.*;
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
    public int insert(Dog dog) {
        try {
            String insertQuery = "INSERT INTO dogs (name, age, playfulness, socialization, affection, training) VALUES (?, ?, ?, ?, ?, ?)";

            PreparedStatement stmt = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, dog.getName());
            stmt.setInt(2, dog.getAge());
            stmt.setString(3, dog.getPlayfulness());
            stmt.setString(4, dog.getSocialization());
            stmt.setString(5, dog.getAffection());
            stmt.setString(6, dog.getTraining());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            return rs.getInt(1);
        } catch (SQLException e) {
            throw new RuntimeException("Error creating a new dog.", e);
        }
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
    public void edit(Dog dog) {
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement("UPDATE dogs SET name = ?, age = ?, playfulness = ?, socialization = ?, affection = ?, training = ? WHERE id = ?;");
            stmt.setString(1, dog.getName());
            stmt.setInt(2, dog.getAge());
            stmt.setString(3, dog.getPlayfulness());
            stmt.setString(4, dog.getSocialization());
            stmt.setString(5, dog.getAffection());
            stmt.setString(6, dog.getTraining());
            stmt.setLong(7, dog.getId());

            System.out.println("stmt = " + stmt);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error editing dog id: " + dog.getId(), e);
        }
    }

    @Override
    public void delete(int dogId) {
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement("DELETE FROM dogs WHERE dogs.id = ?;");
            stmt.setInt(1, dogId);
            System.out.println("stmt = " + stmt);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting dog id: " + dogId, e);
        }
    }
}
