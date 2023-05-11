package com.codeup.adlister.dao;

import com.codeup.adlister.models.Ad;
import com.codeup.adlister.models.Breed;
import com.codeup.adlister.util.Config;
import com.mysql.cj.jdbc.Driver;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
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
    public List<Object> some(String tableName, String searchTerm) {
        PreparedStatement stmt = null;
        try {
            //stmt = connection.prepareStatement("SELECT * FROM " + tableName + "WHERE title LIKE '%" + searchTerm + "%'");
            stmt = connection.prepareStatement("SELECT * FROM " + tableName + " WHERE title LIKE '%French%'");
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
            stmt = connection.prepareStatement("SELECT * FROM ads WHERE id = ?");
            stmt.setLong(1, adNumber);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            return extractObject(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving more info on ads.", e);
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
