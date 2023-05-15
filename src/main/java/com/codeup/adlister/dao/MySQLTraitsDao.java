package com.codeup.adlister.dao;

import com.codeup.adlister.models.Trait;
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

    /*
    /////////////////////////////////////////////////////////////////////
    CREATE
    /////////////////////////////////////////////////////////////////////
     */
    @Override
    public void insert(String name) {

    }

    /*
    /////////////////////////////////////////////////////////////////////
    READ
    /////////////////////////////////////////////////////////////////////
     */

    @Override
    public List<Trait> searchAll() {
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement("SELECT * FROM traits");
            ResultSet rs = stmt.executeQuery();
            return createListFromResults(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving from table: Dog_breeds", e);
        }
    }

    @Override
    public Trait searchOne(int traitId) {
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement("SELECT * FROM traits WHERE id = ?");
            stmt.setInt(1, traitId);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            System.out.println("rs.getMetaData() = " + rs.getMetaData());
            System.out.println("stmt = " + stmt);
            return extractInfo(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving traits from table: Trait id: " + traitId, e);
        }
    }

    private List<Trait> createListFromResults(ResultSet rs) throws SQLException {
        List<Trait> list = new ArrayList<>();
        while (rs.next()){
            list.add(extractInfo(rs));
        }
        return list;
    }

    private Trait extractInfo(ResultSet rs) throws SQLException {
        return new Trait(
                rs.getInt("id"),
                rs.getString("name")
        );
    }

    /*
    /////////////////////////////////////////////////////////////////////
    UPDATE
    /////////////////////////////////////////////////////////////////////
     */

    @Override
    public void edit(int traitId) {

    }

    /*
    /////////////////////////////////////////////////////////////////////
    DELETE
    /////////////////////////////////////////////////////////////////////
     */

    @Override
    public void delete(int traitId) {

    }
}
