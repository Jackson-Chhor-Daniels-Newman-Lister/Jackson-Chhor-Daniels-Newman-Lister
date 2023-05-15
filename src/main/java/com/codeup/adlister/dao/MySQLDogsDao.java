package com.codeup.adlister.dao;

import com.codeup.adlister.models.Ad;
import com.codeup.adlister.models.Dog;
import com.codeup.adlister.util.Config;
import com.mysql.cj.jdbc.Driver;

import java.sql.*;
import java.util.ArrayList;
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
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement("SELECT * FROM dogs");
            ResultSet rs = stmt.executeQuery();
            return createListFromResults(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving from table: Ads", e);
        }
    }

    @Override
    public Dog searchOne(int dogId) {

        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement("SELECT * FROM dogs WHERE id = ?");
            stmt.setInt(1, dogId);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            return extractAd(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving from table: Dogs", e);
        }
    }

    private List<Dog>createListFromResults(ResultSet rs) throws SQLException {
        List<Dog> list = new ArrayList<>();
        while (rs.next()){
            list.add(extractAd(rs));
        }
        return list;
    }

    private Dog extractAd(ResultSet rs) throws SQLException {
        if (! rs.next()) {
            return null;
        }
        //Dog(int id, String name, int age, String playfulness, String socialization, String affection, String training)
        return new Dog(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getInt("age"),
                rs.getString("playfulness"),
                rs.getString("socialization"),
                rs.getString("affection"),
                rs.getString("training")
        );
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
