package com.codeup.adlister.dao;

import com.codeup.adlister.models.Dog;
import java.util.List;

public interface Dogs {
    //CREATE
    void insert(String name, int age, String playfulness, String socialization, String affection, String training);

    //READ
    List<Dog> searchAll();
    Dog searchOne(int dogId);

    //UPDATE
    void edit(int dogId);

    //DELETE
    void delete(int dogId);
}
