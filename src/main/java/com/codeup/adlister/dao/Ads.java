package com.codeup.adlister.dao;

import com.codeup.adlister.models.Ad;
import com.codeup.adlister.models.Dog;

import java.util.List;

public interface Ads {
    //CREATE
    int insert(Ad ad, int dogId);

    //READ
    List<Object> all(String tableName);
    List<Object> searchByString(String tableName, String searchTerm);
    List<Object> searchByBreed(String tableName, String searchTerm);
    List<Object> searchByTraits(String tableName, String[] searchTermArray);
    Object individual(long adId);
    List<Object> adsByUserId(long userId);

    //UPDATE
    void edit(Ad ad);

    //DELETE
    void delete(int adId);
}
