package com.codeup.adlister.dao;

import com.codeup.adlister.models.User;
import com.codeup.adlister.util.Config;
import com.mysql.cj.jdbc.Driver;

import java.sql.*;

public class MySQLUsersDao implements Users {
    private Connection connection;
    public MySQLUsersDao(Config config) {
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
    public int insert(User user) {
        String query = "INSERT INTO users(name, username, email, password) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getUsername());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getPassword());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            return rs.getInt(1);
        } catch (SQLException e) {
            throw new RuntimeException("Error creating new user", e);
        }
    }

    /*
    /////////////////////////////////////////////////////////////////////
    READ
    /////////////////////////////////////////////////////////////////////
     */

    @Override
    public User searchByUsername(String username) {
        String query = "SELECT * FROM users WHERE username = ? LIMIT 1";
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, username);
            return extractInfo(stmt.executeQuery());
        } catch (SQLException e) {
            throw new RuntimeException("Error finding a user by username: " + username, e);
        }
    }

    @Override
    public User searchByUserId(int userId) {
        String query = "SELECT * FROM users WHERE id = ? LIMIT 1";
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, userId);
            return extractInfo(stmt.executeQuery());
        } catch (SQLException e) {
            throw new RuntimeException("Error finding a user by id: " + userId, e);
        }
    }

    private User extractInfo(ResultSet rs) throws SQLException {
        if (! rs.next()) {
            return null;
        }
        return new User(
            rs.getInt("id"),
            rs.getString("name"),
            rs.getString("username"),
            rs.getString("email"),
            rs.getString("password")
        );
    }

    /*
    /////////////////////////////////////////////////////////////////////
    UPDATE
    /////////////////////////////////////////////////////////////////////
     */

    @Override
    public void edit(User user) {

    }

    /*
    /////////////////////////////////////////////////////////////////////
    DELETE
    /////////////////////////////////////////////////////////////////////
     */

    @Override
    public void delete(int userId) {

    }

}
