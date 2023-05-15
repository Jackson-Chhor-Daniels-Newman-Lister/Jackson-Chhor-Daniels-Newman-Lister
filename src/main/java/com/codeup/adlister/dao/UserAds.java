package com.codeup.adlister.dao;

import com.codeup.adlister.models.UserAd;
import java.util.List;

public interface UserAds {
    //CREATE
    void insert(int userId, int adId);

    //READ
    List<UserAd> searchAll(int userId);
    boolean searchOne(int adId, int userId);

    //UPDATE
    void edit(int userId, int adId);

    //DELETE
    void delete(int adId);
}
