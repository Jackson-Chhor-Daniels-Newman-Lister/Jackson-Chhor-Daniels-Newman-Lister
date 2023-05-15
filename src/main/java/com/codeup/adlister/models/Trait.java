package com.codeup.adlister.models;

public class Trait {
    private int id;
    private String name;

    public Trait(String name){
        this.name = name;
    }

    public Trait(int id, String name){
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
