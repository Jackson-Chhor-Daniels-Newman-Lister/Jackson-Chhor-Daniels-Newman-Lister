package com.codeup.adlister.dao;

import com.codeup.adlister.models.DogTrait;
import com.codeup.adlister.util.Config;
import com.mysql.cj.jdbc.Driver;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLDogTraitsDao implements DogTraits{
    private Connection connection;
    public MySQLDogTraitsDao(Config config) {
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
    public void insert(int dogId, int[] traitIds) {
        PreparedStatement stmt = null;
        try {
            for (int dogTrait : traitIds) {
                String insertQuery = "INSERT INTO dog_traits(dog_id, trait_id) VALUES (?, ?)";

                stmt = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
                stmt.setInt(1, dogId);
                stmt.setInt(2, dogTrait);
                System.out.println("stmt = " + stmt);
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error inserting into dog_traits table. DogID: " + dogId, e);
        }
    }

    @Override
    public List<DogTrait> searchAll() {
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement("SELECT * FROM dog_traits");
            ResultSet rs = stmt.executeQuery();
            return createListFromResults(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving from table: Dog_breeds", e);
        }
    }

    @Override
    public DogTrait searchOne(int dogId) {
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement("SELECT * FROM dog_traits WHERE id = ?");
            stmt.setInt(1,dogId);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            return extractInfo(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving from table: Ads", e);
        }
    }

    private List<DogTrait> createListFromResults(ResultSet rs) throws SQLException {
        List<DogTrait> list = new ArrayList<>();
        while (rs.next()){
            list.add(extractInfo(rs));
        }
        return list;
    }

    private DogTrait extractInfo(ResultSet rs) throws SQLException {
        if (! rs.next()) {
            return null;
        }
        return new DogTrait(
                rs.getInt("id"),
                rs.getInt("dog_id"),
                rs.getInt("trait_id")
        );
    }

    @Override
    public void edit(int dogId, int traitIds[]) {
        delete(dogId);
        PreparedStatement stmt = null;
        try {
            for (int dogTraitId : traitIds) {
                stmt = connection.prepareStatement("INSERT INTO dog_traits (dog_id, trait_id) VALUES (?,?)");
                stmt.setInt(1, dogId);
                stmt.setInt(2, dogTraitId);
                System.out.println("stmt = " + stmt);
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error editing traits for dog id: " + dogId, e);
        }
    }

    @Override
    public void delete(int dogId) {
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement("DELETE FROM dog_traits WHERE dog_id = ?");
            stmt.setInt(1, dogId);
            System.out.println("stmt = " + stmt);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error editing traits for dog id: " + dogId, e);
        }
    }
}
