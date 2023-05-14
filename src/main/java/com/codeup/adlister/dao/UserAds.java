package com.codeup.adlister.dao;

import com.codeup.adlister.models.UserAd;
import java.util.List;

public interface UserAds {
    //CREATE
    void insert(int userId, int AdId);

    //READ
    List<UserAd> searchAll();
    UserAd searchOne(int AdId);

    //UPDATE
    void edit(int userId, int AdId);

    //DELETE
    void delete(int AdId);
}
