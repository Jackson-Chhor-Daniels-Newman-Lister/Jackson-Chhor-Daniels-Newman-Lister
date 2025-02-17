package com.codeup.adlister.dao;

import com.codeup.adlister.models.DogTrait;
import java.util.List;

public interface DogTraits {
    //CREATE
    void insert(int dogId, int[] traitIds);

    //READ
    List<DogTrait> searchAll();
    List<DogTrait> searchOne(int dogId);

    //UPDATE
    void edit(int dogId, int traitId[]);

    //DELETE
    void delete(int dogId);
}
