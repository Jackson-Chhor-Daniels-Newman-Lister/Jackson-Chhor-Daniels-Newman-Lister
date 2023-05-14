package com.codeup.adlister.dao;

import com.codeup.adlister.models.Ad;
import com.codeup.adlister.models.Dog;

import java.util.List;

public interface Ads {
    List<Object> all(String tableName);

    List<Object> searchByString(String tableName, String searchTerm);

    List<Object> searchByBreed(String tableName, String searchTerm);

    List<Object> searchByTraits(String tableName, String[] searchTermArray);

    void insert(Ad ad, Dog dog, int breedId, int userId, int[] traitIds);

    Object individual(long adId);

    List<Object> adsByUserId(long userId);

    void submitEdits(Ad ad, Dog dog, int breedId, int[] traitIds);

    void delete(int adId);
}
