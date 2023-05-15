package com.codeup.adlister.dao;

import com.codeup.adlister.models.Breed;
import java.util.List;

public interface Breeds {
    //CREATE
    void insert(String name);

    //READ
    List<Breed> searchAll();
    Breed searchOne(int breedId);

    //UPDATE
    void edit(int breedId);

    //DELETE
    void delete(int breedId);
}
