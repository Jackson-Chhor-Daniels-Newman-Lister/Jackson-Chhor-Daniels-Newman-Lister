package com.codeup.adlister.dao;

import com.codeup.adlister.models.Trait;
import com.codeup.adlister.models.UserAd;
import com.codeup.adlister.util.Config;
import com.mysql.cj.jdbc.Driver;

import java.sql.*;
import java.util.ArrayList;
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
    public List<Trait> searchByStrings(String[] searchStringArray) {
        PreparedStatement stmt = null;
        StringBuilder searchTerm = new StringBuilder();
        searchTerm.append("(");
        for(int i = 0; i < searchStringArray.length; i++){
            if(i == searchStringArray.length - 1){
                searchTerm.append("'").append(searchStringArray[i]).append("'");
            } else {
                searchTerm.append("'").append(searchStringArray[i]).append("', ");
            }
        }
        searchTerm.append(")");
        try {
            stmt = connection.prepareStatement(
            "SELECT ads.title, ads.description, ads.short_description, ads.price, ads.image, ads.dog_id, d.name, d.age, d.playfulness, d.socialization, d.affection, d.training, t.id, t.name " +
            "FROM ads " +
            "JOIN dogs d ON d.id = ads.dog_id " +
            "JOIN dog_traits dt ON d.id = dt.dog_id " +
            "JOIN traits t ON t.id = dt.trait_id " +
            "WHERE t.name IN ? " +
            "GROUP BY ads.title, ads.description, ads.short_description, ads.price, ads.image, ads.dog_id, d.name, d.age, d.playfulness, d.socialization, d.affection, d.training, t.id, t.name " +
            "HAVING COUNT(DISTINCT t.name) = ? "
            );
            stmt.setString(1, searchTerm.toString());
            stmt.setInt(2, searchStringArray.length);

            System.out.println("some stmt = " + stmt);
            ResultSet rs = stmt.executeQuery();
            return createListFromResults(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving list of traits: ", e);
        }
    }

    private List<Trait> createListFromResults(ResultSet rs) throws SQLException {
        List<Trait> list = new ArrayList<>();
        while (rs.next()){
            list.add(extractUserAd(rs));
        }
        return list;
    }

    private Trait extractUserAd(ResultSet rs) throws SQLException {
        if (! rs.next()) {
            return null;
        }
        return new Trait(
                rs.getInt("id"),
                rs.getString("name")
        );
    }

    @Override
    public void edit(int traitId) {

    }

    @Override
    public void delete(int traitId) {

    }
}
