package com.codeup.adlister.dao;

import com.codeup.adlister.models.Ad;
import com.codeup.adlister.util.Config;
import com.mysql.cj.jdbc.Driver;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class MySQLAdsDao implements Ads {
    private Connection connection = null;

    public MySQLAdsDao(Config config) {
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
    public List<Object> all(String tableName) {
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement("SELECT * FROM " + tableName);
            ResultSet rs = stmt.executeQuery();
            return createListFromResults(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving from table: " + tableName, e);
        }
    }

    @Override
    public List<Object> searchByString(String tableName, String searchString) {
        PreparedStatement stmt = null;
        String searchTerm = "'%" + searchString + "%'";
        try {
            stmt = connection.prepareStatement(
                    "SELECT ads.id, ads.title, ads.description, ads.short_description, ads.price, ads.image, dogs.name, dogs.age, dogs.playfulness, dogs.socialization, dogs.affection, dogs.training," +
                            "GROUP_CONCAT(DISTINCT breeds.name SEPARATOR ', ') AS breeds, " +
                            "GROUP_CONCAT(DISTINCT traits.name SEPARATOR ', ') AS traits " +
                            "FROM ads " +
                            "JOIN dogs ON ads.dog_id = dogs.id " +
                            "JOIN dog_breeds ON dogs.id = dog_breeds.dog_id " +
                            "JOIN breeds ON dog_breeds.breed_id = breeds.id " +
                            "JOIN dog_traits ON dogs.id = dog_traits.dog_id " +
                            "JOIN traits ON dog_traits.trait_id = traits.id " +
                            "WHERE ads.title LIKE " + searchTerm +
                            " OR traits.name LIKE " + searchTerm +
                            " OR breeds.name LIKE " + searchTerm +
                            " GROUP BY ads.id, ads.title, ads.description, ads.short_description, ads.price, ads.image, dogs.name, dogs.age, dogs.playfulness, dogs.socialization, dogs.affection, dogs.training"
            );

            System.out.println("some stmt = " + stmt);
            ResultSet rs = stmt.executeQuery();
            return createListFromResults(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving from table: " + tableName, e);
        }
    }

    @Override
    public List<Object> searchByBreed(String tableName, String searchedBreed) {
        PreparedStatement stmt = null;
        String searchTerm = "'" + searchedBreed + "'";
        try {
            stmt = connection.prepareStatement(
                    "SELECT ads.id, ads.title, ads.description, ads.short_description, ads.price, ads.image, dogs.name, dogs.age, dogs.playfulness, dogs.socialization, dogs.affection, dogs.training," +
                            "GROUP_CONCAT(DISTINCT breeds.name SEPARATOR ', ') AS breeds, " +
                            "GROUP_CONCAT(DISTINCT traits.name SEPARATOR ', ') AS traits " +
                            "FROM ads " +
                            "JOIN dogs ON ads.dog_id = dogs.id " +
                            "JOIN dog_breeds ON dogs.id = dog_breeds.dog_id " +
                            "JOIN breeds ON dog_breeds.breed_id = breeds.id " +
                            "JOIN dog_traits ON dogs.id = dog_traits.dog_id " +
                            "JOIN traits ON dog_traits.trait_id = traits.id " +
                            "WHERE breeds.name = " + searchTerm +
                            " GROUP BY ads.id, ads.title, ads.description, ads.short_description, ads.price, ads.image, dogs.name, dogs.age, dogs.playfulness, dogs.socialization, dogs.affection, dogs.training"
            );

            System.out.println("some stmt = " + stmt);
            ResultSet rs = stmt.executeQuery();
            return createListFromResults(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving from table: " + tableName, e);
        }
    }

    @Override
    public List<Object> searchByTraits(String tableName, String[] searchStringArray) {
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
                    "SELECT ads.id, ads.title, ads.description, ads.short_description, ads.price, ads.image, dogs.name, dogs.age, dogs.playfulness, dogs.socialization, dogs.affection, dogs.training," +
                            "GROUP_CONCAT(DISTINCT breeds.name SEPARATOR ', ') AS breeds, " +
                            "GROUP_CONCAT(DISTINCT traits.name SEPARATOR ', ') AS traits " +
                            "FROM ads " +
                            "JOIN dogs ON ads.dog_id = dogs.id " +
                            "JOIN dog_breeds ON dogs.id = dog_breeds.dog_id " +
                            "JOIN breeds ON dog_breeds.breed_id = breeds.id " +
                            "JOIN dog_traits ON dogs.id = dog_traits.dog_id " +
                            "JOIN traits ON dog_traits.trait_id = traits.id " +
                            "WHERE traits.name IN " + searchTerm +
                            " GROUP BY ads.id, ads.title, ads.description, ads.short_description, ads.price, ads.image, dogs.name, dogs.age, dogs.playfulness, dogs.socialization, dogs.affection, dogs.training " +
                            "HAVING COUNT(DISTINCT traits.name) = " + searchStringArray.length
            );

            System.out.println("some stmt = " + stmt);
            ResultSet rs = stmt.executeQuery();
            return createListFromResults(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving from table: " + tableName, e);
        }
    }

    @Override
    public Object individual(long adNumber) {
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement(
                    "SELECT ads.id, ads.title, ads.description, ads.short_description, ads.price, ads.image, dogs.name, dogs.age, dogs.playfulness, dogs.socialization, dogs.affection, dogs.training," +
                            "GROUP_CONCAT(DISTINCT breeds.name SEPARATOR ', ') AS breeds, " +
                            "GROUP_CONCAT(DISTINCT traits.name SEPARATOR ', ') AS traits " +
                            "FROM ads " +
                            "JOIN dogs ON ads.dog_id = dogs.id " +
                            "JOIN dog_breeds ON dogs.id = dog_breeds.dog_id " +
                            "JOIN breeds ON dog_breeds.breed_id = breeds.id " +
                            "JOIN dog_traits ON dogs.id = dog_traits.dog_id " +
                            "JOIN traits ON dog_traits.trait_id = traits.id " +
                            "WHERE ads.id = " + adNumber +
                            " GROUP BY ads.id, ads.title, ads.description, ads.short_description, ads.price, ads.image, dogs.name, dogs.age, dogs.playfulness, dogs.socialization, dogs.affection, dogs.training "
            );
            ResultSet rs = stmt.executeQuery();
            rs.next();
            return extractObject(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving more info on ads.", e);
        }
    }

    @Override
    public List<Object> adsByUserId(long userId) {
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement("SELECT * FROM ads JOIN user_ads ON ads.id = user_ads.ad_id WHERE user_id = ?");
            stmt.setLong(1, userId);
            ResultSet rs = stmt.executeQuery();
            return createListFromResults(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving ad count for user ID: " + userId, e);
        }
    }

    @Override
    public Long insert(Ad ad) {
        try {
            String insertQuery = "INSERT INTO ads(title, description, short_description, price, image, dog_id) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";
          
            PreparedStatement stmt = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, ad.getTitle());
            stmt.setString(2, ad.getShortDescription());
            stmt.setString(3, ad.getDescription());
            stmt.setLong(4, ad.getPrice());
            stmt.setString(5, ad.getImage());
            stmt.setLong(6, ad.getDogId());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            return rs.getLong(1);
        } catch (SQLException e) {
            throw new RuntimeException("Error creating a new ad.", e);
        }
    }

    private HashMap<String, Object> extractObject(ResultSet rs) throws SQLException {
        HashMap<String, Object> myObject = new HashMap<>();
        ResultSetMetaData rsmd = rs.getMetaData();
        int columnCount = rsmd.getColumnCount();

        for (int i = 1; i <= columnCount; i++){
            int columnType = rsmd.getColumnType(i);

            switch (columnType) {
                case Types.INTEGER:
                    int intValue = rs.getInt(i);
                    myObject.put(rsmd.getColumnName(i),intValue);
                    break;
                default:
                    String stringValue = rs.getString(i);
                    myObject.put(rsmd.getColumnName(i),stringValue);
                    break;
            }
        }
        return myObject;
    }

    private List<Object>createListFromResults(ResultSet rs) throws SQLException {
        List<Object> list = new ArrayList<>();
        while (rs.next()){
            list.add(extractObject(rs));
        }
        return list;
    }
}
