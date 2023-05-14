package com.codeup.adlister.dao;

import com.codeup.adlister.models.Ad;
import com.codeup.adlister.models.Dog;
import com.codeup.adlister.util.Config;
import com.mysql.cj.jdbc.Driver;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class MySQLAdsDao implements Ads {
    private Connection connection = null;

    private final String getAllThingsStatementTop =
            "SELECT ads.id AS ads_id, ads.title, ads.short_description, ads.description, ads.price, ads.image, ads.dog_id, " +
                    "dogs.id AS dogs_id, dogs.name, dogs.age, dogs.playfulness, dogs.socialization, dogs.affection, dogs.training, breeds.id AS breeds_id, " +
                    "GROUP_CONCAT(DISTINCT breeds.name SEPARATOR ', ') AS breeds," +
                    "GROUP_CONCAT(DISTINCT traits.name SEPARATOR ', ') AS traits " +
                    "FROM ads " +
                    "JOIN dogs ON ads.dog_id = dogs.id " +
                    "JOIN dog_breeds ON dogs.id = dog_breeds.dog_id " +
                    "JOIN breeds ON dog_breeds.breed_id = breeds.id " +
                    "JOIN dog_traits ON dogs.id = dog_traits.dog_id " +
                    "JOIN traits ON dog_traits.trait_id = traits.id " +
                    "JOIN user_ads ON ads.id = user_ads.ad_id ";
    private final String getAllThingsStatementBottom =
            "GROUP BY ads.id, ads.title, ads.short_description, ads.description, ads.price, ads.image,  ads.dog_id, " +
                    "dogs.id, dogs.name, dogs.age, dogs.playfulness, dogs.socialization, dogs.affection, dogs.training, breeds.id ";
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
                    getAllThingsStatementTop +
                            "WHERE ads.title LIKE " + searchTerm +
                            " OR traits.name LIKE " + searchTerm +
                            " OR breeds.name LIKE " + searchTerm +
                            getAllThingsStatementBottom
                            );

            System.out.println("some stmt = " + stmt);
            ResultSet rs = stmt.executeQuery();
            return createListFromResults(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving from table: " + tableName + " string : " + searchString, e);
        }
    }

    @Override
    public List<Object> searchByBreed(String tableName, String searchedBreed) {
        PreparedStatement stmt = null;
        String searchTerm = "'" + searchedBreed + "'";
        try {
            stmt = connection.prepareStatement(
                getAllThingsStatementTop +
                    "WHERE breeds.name = " + searchTerm +
                    getAllThingsStatementBottom
                    );

            System.out.println("some stmt = " + stmt);
            ResultSet rs = stmt.executeQuery();
            return createListFromResults(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving from table: " + tableName + " breed : " + searchedBreed, e);
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
                getAllThingsStatementTop +
                    "WHERE traits.name IN " + searchTerm + " " +
                    getAllThingsStatementBottom +
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
                    getAllThingsStatementTop +
                            "WHERE ads.id = " + adNumber + " " +
                            getAllThingsStatementBottom
                    );
            System.out.println("stmt = " + stmt);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            return extractObject(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving more info on ads id: " + adNumber, e);
        }
    }

    @Override
    public void edit(Ad ad) {
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement(
                    "UPDATE ads SET title = ?, short_description = ?, description = ?, price = ? WHERE id = ? ");
            stmt.setString(1, ad.getTitle());
            stmt.setString(2, ad.getShortDescription());
            stmt.setString(3, ad.getDescription());
            stmt.setLong(4, ad.getPrice());
            stmt.setLong(5, ad.getId());

            System.out.println("stmt = " + stmt);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error editing ad id: " + ad.getId(), e);
        }
    }

    @Override
    public void delete(int adId){
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement("DELETE FROM ads WHERE ads.id = ?;");
            stmt.setInt(1, adId);
            System.out.println("stmt = " + stmt);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting ad id: " + adId, e);
        }
    }

    @Override
    public List<Object> adsByUserId(long userId) {
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement(
            getAllThingsStatementTop +
                "WHERE user_id = " + userId + " " +
                 getAllThingsStatementBottom
            );
            System.out.println("stmt = " + stmt);
            ResultSet rs = stmt.executeQuery();
            return createListFromResults(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving ad count for user ID: " + userId, e);
        }
    }

    @Override
    public int insert(Ad ad, int dogId){
        try {
            String insertQuery = "INSERT INTO ads(title, description, short_description, price, image, dog_id) VALUES (?, ?, ?, ?, ?, ?)";

            PreparedStatement stmt = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, ad.getTitle());
            stmt.setString(2, ad.getShortDescription());
            stmt.setString(3, ad.getDescription());
            stmt.setLong(4, ad.getPrice());
            stmt.setString(5, ad.getImage());
            stmt.setLong(6, dogId);
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            return rs.getInt(1);
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
