package com.codeup.adlister.dao;

import com.codeup.adlister.models.User;
import java.util.List;

public interface Users {
    //CREATE
    Long insert(User user);

    //READ
    User findByUsername(String username);
    User findById(Long id);

    //UPDATE
    public void edit(User user);

    //DELETE
    public void delete(int userId);


}
