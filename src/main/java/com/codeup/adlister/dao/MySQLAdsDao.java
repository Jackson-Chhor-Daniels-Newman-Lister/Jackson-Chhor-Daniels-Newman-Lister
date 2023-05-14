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
    public void submitEdits(Ad ad, Dog dog, int breedID, int[] traitIds) {
        editAd(ad);
        editDog(dog);
        editBreed(breedID, (int) dog.getId());
        editTraits(traitIds, (int) dog.getId());
    }

    public void editAd(Ad ad) {
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement(
                "UPDATE ads " +
                    "SET title = ?, short_description = ?, description = ?, price = ? " +
                    "WHERE id = ? "
            );
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
    public void editDog(Dog dog) {
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement(
                    "UPDATE dogs " +
                            "SET name = ?, age = ?, playfulness = ?, socialization = ?, affection = ?, training = ? " +
                            "WHERE id = ?;"
            );
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

    public void editBreed(int breedId, int dogId) {
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement(
                    "UPDATE dog_breeds " +
                            "SET breed_id = ? " +
                            "WHERE dog_id = ?;"
            );
            stmt.setInt(1, breedId);
            stmt.setInt(2, dogId);

            System.out.println("stmt = " + stmt);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error editing breed id: " + breedId, e);
        }
    }

    public void editTraits(int[] traitIds, int dogId) {
        PreparedStatement stmt1 = null;
        PreparedStatement stmt2 = null;
        try {
            stmt1 = connection.prepareStatement(
                    "DELETE FROM dog_traits " +
                            "WHERE dog_id = ?;"
            );
            stmt1.setInt(1, dogId);
            System.out.println("stmt = " + stmt1);
            stmt1.executeUpdate();

            for (int dogTraitId : traitIds) {
                stmt2 = connection.prepareStatement(
                        "INSERT INTO dog_traits (dog_id, trait_id)  " +
                                "VALUES (?,?)"
                );
                stmt2.setInt(1, dogId);
                stmt2.setInt(2, dogTraitId);

                System.out.println("stmt = " + stmt2);
                stmt2.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error editing traits for dog id: " + dogId, e);
        }
    }

    @Override
    public void delete(int adId) {
        deleteBreed(adId);
        deleteTraits(adId);
        deleteAd(adId);
        deleteDog(adId);
    }

    private void deleteAd(int adId){
        PreparedStatement stmt1 = null;
        PreparedStatement stmt2 = null;
        try {
            stmt2 = connection.prepareStatement(
            "DELETE FROM user_ads " +
                "WHERE ad_id = ?;"
            );
            stmt2.setInt(1, adId);
            System.out.println("stmt = " + stmt2);
            stmt2.executeUpdate();

            stmt1 = connection.prepareStatement(
                    "DELETE FROM ads " +
                            "WHERE ads.id = ?;"
            );
            stmt1.setInt(1, adId);
            System.out.println("stmt = " + stmt1);
            stmt1.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error deleting ad id: " + adId, e);
        }
    }
    private void deleteDog(int dogId){
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement(
            "DELETE FROM dogs " +
                "WHERE dogs.id = ?;"
            );
            stmt.setInt(1, dogId);

            System.out.println("stmt = " + stmt);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting dog id: " + dogId, e);
        }
    }
    private void deleteBreed(int dogId){
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement(
            "DELETE FROM dog_breeds " +
                "WHERE dog_id = ?;"
            );
            stmt.setInt(1, dogId);

            System.out.println("stmt = " + stmt);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting dog's breed, id: " + dogId, e);
        }
    }
    private void deleteTraits(int dogId){
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement(
            "DELETE FROM dog_traits " +
                "WHERE dog_id = ?;"
            );
            stmt.setInt(1, dogId);
            System.out.println("stmt = " + stmt);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error editing traits for dog id: " + dogId, e);
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
    public void insert(Ad ad, Dog dog, int breedId, int userId, int[] traitIds) {
        int newDogId = insertDog(dog);
        insertAd(ad, newDogId);
        insertBreed(breedId, newDogId);
        insertUser(userId, newDogId);
        insertTraits(traitIds, newDogId);
    }

    private void insertAd(Ad ad, int newDogId){
        try {
            String insertQuery = "INSERT INTO ads(title, description, short_description, price, image, dog_id) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";

            PreparedStatement stmt = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, ad.getTitle());
            stmt.setString(2, ad.getShortDescription());
            stmt.setString(3, ad.getDescription());
            stmt.setLong(4, ad.getPrice());
            stmt.setString(5, ad.getImage());
            stmt.setLong(6, newDogId);
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
        } catch (SQLException e) {
            throw new RuntimeException("Error creating a new ad.", e);
        }
    }

    private int insertDog(Dog dog){
        try {
            String insertQuery = "INSERT INTO dogs(name, age, playfulness, socialization, affection, training) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";

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

    private void insertBreed(int newDogBreedId, int newDogId){
        System.out.println("newDogBreedId = " + newDogBreedId);
        System.out.println("newDogId = " + newDogId);
        try {
            String insertQuery = "INSERT INTO dog_breeds(dog_id, breed_id) " +
                    "VALUES (?, ?)";

            PreparedStatement stmt = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, newDogId);
            stmt.setInt(2, newDogBreedId);
            System.out.println("stmt = " + stmt);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error creating a new breed.", e);
        }
    }

    private void insertTraits(int[] newDogTraitIds, int newDogId) {
        PreparedStatement stmt = null;

        try {
            for (int newDogTrait : newDogTraitIds) {
                String insertQuery = "INSERT INTO dog_traits(dog_id, trait_id) " +
                        "VALUES (?, ?)";

                stmt = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
                stmt.setInt(1, newDogId);
                stmt.setInt(2, newDogTrait);
                System.out.println("stmt = " + stmt);
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error inserting traits into table. For new dog id: " + newDogId, e);
        }
    }

    private void insertUser(int newDogUserId, int newDogId){
        System.out.println("newDogBreedId = " + newDogUserId);
        System.out.println("newDogId = " + newDogId);
        try {
            String insertQuery = "INSERT INTO user_ads(user_id, ad_id) " +
                    "VALUES (?, ?)";

            PreparedStatement stmt = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, newDogUserId);
            stmt.setInt(2, newDogId);
            System.out.println("stmt = " + stmt);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error creating a new breed.", e);
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
