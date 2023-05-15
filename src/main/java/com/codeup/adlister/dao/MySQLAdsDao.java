package com.codeup.adlister.dao;

import com.codeup.adlister.models.Ad;
import com.codeup.adlister.util.Config;
import com.mysql.cj.jdbc.Driver;

import java.sql.*;
import java.util.ArrayList;
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

    /*
    /////////////////////////////////////////////////////////////////////
    CREATE
    /////////////////////////////////////////////////////////////////////
     */
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

    /*
    /////////////////////////////////////////////////////////////////////
    READ
    /////////////////////////////////////////////////////////////////////
     */

    @Override
    public List<Ad> searchAll() {
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement("SELECT * FROM ads");
            ResultSet rs = stmt.executeQuery();
            return createListFromResults(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving from table: Ads", e);
        }
    }

    @Override
    public Ad searchOne(int adNumber) {
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement(
                    "SELECT ads.title, ads.description, ads.short_description, ads.price, ads.image, ads.dog_id, d.name, d.age, d.playfulness, d.socialization, d.affection, d.training, t.name, b.name " +
                            "FROM ads " +
                            "JOIN dogs d ON d.id = ads.dog_id " +
                            "JOIN dog_breeds db ON d.id = db.dog_id " +
                            "JOIN breeds b ON b.id = db.breed_id " +
                            "JOIN dog_traits dt ON d.id = dt.dog_id " +
                            "JOIN traits t ON t.id = dt.trait_id " +
                            "WHERE ads.id = ? " +
                            "GROUP BY ads.title, ads.description, ads.short_description, ads.price, ads.image, ads.dog_id, d.name, d.age, d.playfulness, d.socialization, d.affection, d.training, t.name, b.name " +
                            "HAVING COUNT(DISTINCT b.name) = ? "
            );
            stmt.setInt(1, adNumber);
            System.out.println("stmt = " + stmt);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            return extractInfo(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving more info on ads id: " + adNumber, e);
        }
    }

    @Override
    public List<Ad> searchByString(String searchString) {
        PreparedStatement stmt = null;
        String searchTerm = "'%" + searchString + "%'";
        try {
            stmt = connection.prepareStatement(
                    "SELECT ads.title, ads.description, ads.short_description, ads.price, ads.image, ads.dog_id, d.name, d.age, d.playfulness, d.socialization, d.affection, d.training, t.name, b.name " +
                            "FROM ads " +
                            "JOIN dogs d ON d.id = ads.dog_id " +
                            "JOIN dog_breeds db ON d.id = db.dog_id " +
                            "JOIN breeds b ON b.id = db.breed_id " +
                            "JOIN dog_traits dt ON d.id = dt.dog_id " +
                            "JOIN traits t ON t.id = dt.trait_id " +
                            "WHERE b.name LIKE ? " +
                            "OR t.name LIKE ? " +
                            "OR ads.description LIKE ? " +
                            "GROUP BY ads.title, ads.description, ads.short_description, ads.price, ads.image, ads.dog_id, d.name, d.age, d.playfulness, d.socialization, d.affection, d.training, t.name, b.name " +
                            "HAVING COUNT(DISTINCT b.name) = ? "
            );
            stmt.setString(1, searchTerm);
            System.out.println("some stmt = " + stmt);
            ResultSet rs = stmt.executeQuery();
            return createListFromResults(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving info from string : " + searchString, e);
        }
    }

    private List<Ad>createListFromResults(ResultSet rs) throws SQLException {
        List<Ad> list = new ArrayList<>();
        while (rs.next()){
            list.add(extractInfo(rs));
        }
        return list;
    }

    private Ad extractInfo(ResultSet rs) throws SQLException {
        if (! rs.next()) {
            return null;
        }
        return new Ad(
                rs.getInt("id"),
                rs.getString("title"),
                rs.getString("short_description"),
                rs.getString("description"),
                rs.getInt("price"),
                rs.getString("image"),
                rs.getInt("dog_id")
        );
    }

    /*
    /////////////////////////////////////////////////////////////////////
    UPDATE
    /////////////////////////////////////////////////////////////////////
     */

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

    /*
    /////////////////////////////////////////////////////////////////////
    DELETE
    /////////////////////////////////////////////////////////////////////
     */

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
}

//although it goes against the system, this is a bit of code i was proud of producing that helped handle every object and spits out something usable
//    private HashMap<String, Object> extractObject(ResultSet rs) throws SQLException {
//        HashMap<String, Object> myObject = new HashMap<>();
//        ResultSetMetaData rsmd = rs.getMetaData();
//        int columnCount = rsmd.getColumnCount();
//
//        for (int i = 1; i <= columnCount; i++){
//            int columnType = rsmd.getColumnType(i);
//
//            switch (columnType) {
//                case Types.INTEGER:
//                    int intValue = rs.getInt(i);
//                    myObject.put(rsmd.getColumnName(i),intValue);
//                    break;
//                default:
//                    String stringValue = rs.getString(i);
//                    myObject.put(rsmd.getColumnName(i),stringValue);
//                    break;
//            }
//        }
//        return myObject;
//    }