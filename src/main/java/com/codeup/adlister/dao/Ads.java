package com.codeup.adlister.dao;

import com.codeup.adlister.models.Ad;

import java.util.List;
 
public interface Ads {
    List<Object> all(String tableName);

    List<Object> some(String tableName, String searchTerm);

    Long insert(Ad ad);

    Object individual(long adId);

    //testgit
}
