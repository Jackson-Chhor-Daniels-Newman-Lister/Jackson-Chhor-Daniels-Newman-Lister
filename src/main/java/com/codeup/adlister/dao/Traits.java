package com.codeup.adlister.dao;

import com.codeup.adlister.models.Trait;

import java.util.List;

public interface Traits {
    //CREATE
    void insert(String name);

    //READ
    List<Trait> searchAll();
    Trait searchOne(int traitId);
    List<Trait> searchByStrings(String[] searchStringArray);

    //UPDATE
    void edit(int traitId);

    //DELETE
    void delete(int traitId);
}
