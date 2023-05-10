package com.codeup.adlister.models;

public class Ad {
    private long id;
    private String title;
    private String description;
    private String shortDescription;
    private int price;
    private String image;
    private int dogId;


    public Ad (String title, String description, String shortDescription, int price, String image, int dogId){
        this.title = title;
        this.description = description;
        this.shortDescription = shortDescription;
        this.price = price;
        this.image = image;
        this.dogId = dogId;
    }

  
    public Ad(long id, String title, String description, String shortDescription, int price, String image, int dogId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.shortDescription = shortDescription;
        this.price = price;
        this.image = image;
        this.dogId = dogId;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public int getPrice() {
        return price;
    }

    public int getDogId() {
        return dogId;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setDogId(int dogId) {
        this.dogId = dogId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}

