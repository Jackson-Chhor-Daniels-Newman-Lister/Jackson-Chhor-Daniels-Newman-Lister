package com.codeup.adlister.dao;

import com.codeup.adlister.models.Breed;
import com.codeup.adlister.models.Trait;
import com.codeup.adlister.util.Config;
import com.mysql.cj.jdbc.Driver;

import java.sql.*;
import java.util.ArrayList;
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
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement("SELECT * FROM breeds");
            ResultSet rs = stmt.executeQuery();
            return createListFromResults(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving from table: Ads", e);
        }
    }

    @Override
    public Breed searchOne(int breedId) {
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement("SELECT * FROM breeds WHERE id = ?");
            stmt.setInt(1,breedId);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            return extractUserAd(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving from table: Ads", e);
        }
    }

    @Override
    public List<Breed> searchByString(String searchedBreed) {
        PreparedStatement stmt = null;
        String searchTerm = "'" + searchedBreed + "'";
        try {
            stmt = connection.prepareStatement(
                    "SELECT ads.title, ads.description, ads.short_description, ads.price, ads.image, ads.dog_id, d.name, d.age, d.playfulness, d.socialization, d.affection, d.training, b.id, b.name " +
                            "FROM ads " +
                            "JOIN dogs d ON d.id = ads.dog_id " +
                            "JOIN dog_breeds db ON d.id = db.dog_id " +
                            "JOIN breeds b ON b.id = db.breed_id " +
                            "WHERE b.name = ? " +
                            "GROUP BY ads.title, ads.description, ads.short_description, ads.price, ads.image, ads.dog_id, d.name, d.age, d.playfulness, d.socialization, d.affection, d.training, b.id, b.name " +
                            "HAVING COUNT(DISTINCT b.name) = ? "
            );
            stmt.setString(1, searchedBreed);

            System.out.println("some stmt = " + stmt);
            ResultSet rs = stmt.executeQuery();
            return createListFromResults(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving breed : " + searchedBreed, e);
        }
    }

    private List<Breed> createListFromResults(ResultSet rs) throws SQLException {
        List<Breed> list = new ArrayList<>();
        while (rs.next()){
            list.add(extractUserAd(rs));
        }
        return list;
    }

    private Breed extractUserAd(ResultSet rs) throws SQLException {
        if (! rs.next()) {
            return null;
        }
        return new Breed(
                rs.getInt("id"),
                rs.getString("name")
        );
    }
    @Override
    public void edit(int breedId) {

    }

    @Override
    public void delete(int breedId) {

    }
}
