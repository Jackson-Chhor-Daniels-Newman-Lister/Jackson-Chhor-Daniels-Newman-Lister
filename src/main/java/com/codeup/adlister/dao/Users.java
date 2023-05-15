package com.codeup.adlister.dao;

import com.codeup.adlister.models.User;

public interface Users {
    //CREATE
    int insert(User user);

    //READ
    User searchByUsername(String username);
    User searchByUserId(int userId);

    //UPDATE
    public void edit(User user);

    //DELETE
    public void delete(int userId);


}
