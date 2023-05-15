package com.codeup.adlister.dao;

import com.codeup.adlister.models.Ad;

import java.util.List;

public interface Ads {
    //CREATE
    int insert(Ad ad, int dogId);

    //READ
    List<Ad> searchAll();
    List<Ad> searchByString(String searchTerm);
    Ad searchOne(int adId);

    //UPDATE
    void edit(Ad ad);

    //DELETE
    void delete(int adId);
}
