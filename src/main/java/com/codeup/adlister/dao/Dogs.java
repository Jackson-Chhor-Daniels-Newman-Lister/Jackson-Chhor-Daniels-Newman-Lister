package com.codeup.adlister.dao;

import com.codeup.adlister.models.Dog;
import java.util.List;

public interface Dogs {
    //CREATE
    int insert(Dog dog);

    //READ
    List<Dog> searchAll();
    Dog searchOne(int dogId);

    //UPDATE
    void edit(Dog dog);

    //DELETE
    void delete(int dogId);
}
