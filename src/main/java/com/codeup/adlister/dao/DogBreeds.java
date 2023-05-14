package com.codeup.adlister.dao;

import com.codeup.adlister.models.DogBreed;
import java.util.List;

public interface DogBreeds {
    //CREATE
    void insert(int dogId, int breedId);

    //READ
    List<DogBreed> searchAll();
    DogBreed searchOne(int dogId);

    //UPDATE
    void edit(int dogId, int breedId);

    //DELETE
    void delete(int dogId);
}
