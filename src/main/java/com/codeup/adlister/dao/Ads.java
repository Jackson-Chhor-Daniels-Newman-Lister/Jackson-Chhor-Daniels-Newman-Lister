package com.codeup.adlister.dao;

import com.codeup.adlister.models.Ad;

import java.util.List;

public interface Ads {
    //CREATE
    int insert(Ad ad, int dogId);

    //READ
    List<Ad> searchAll();
    Ad searchOne(int adId);
    List<Ad> searchByString(String searchTerm);
    List<Ad> searchByBreed(String searchedBreed);
    List<Ad> searchByTraits(String[] searchStringArray);

    //UPDATE
    void edit(Ad ad);

    //DELETE
    void delete(int adId);
}
