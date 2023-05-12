package com.codeup.adlister.dao;

import com.codeup.adlister.models.Ad;

import java.util.List;

public interface Ads {
    List<Object> all(String tableName);

    List<Object> searchByString(String tableName, String searchTerm);

    List<Object> searchByBreed(String tableName, String searchTerm);

    List<Object> searchByTraits(String tableName, String[] searchTermArray);

    Long insert(Ad ad);

    Object individual(long adId);

    List<Object> adsByUserId(long userId);
}
