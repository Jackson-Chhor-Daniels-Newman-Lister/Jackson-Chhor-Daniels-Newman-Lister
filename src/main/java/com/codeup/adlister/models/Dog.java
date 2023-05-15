package com.codeup.adlister.models;

public class Dog {
    private int id;
    private String name;
    private int age;
    private String playfulness;
    private String socialization;
    private String affection;
    private String training;

    public Dog(String name, int age, String playfulness, String socialization, String affection, String training) {
        this.name = name;
        this.age = age;
        this.playfulness = playfulness;
        this.socialization = socialization;
        this.affection = affection;
        this.training = training;
    }

    public Dog(int id, String name, int age, String playfulness, String socialization, String affection, String training) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.playfulness = playfulness;
        this.socialization = socialization;
        this.affection = affection;
        this.training = training;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPlayfulness() {
        return playfulness;
    }

    public void setPlayfulness(String playfulness) {
        this.playfulness = playfulness;
    }

    public String getSocialization() {
        return socialization;
    }

    public void setSocialization(String socialization) {
        this.socialization = socialization;
    }

    public String getAffection() {
        return affection;
    }

    public void setAffection(String affection) {
        this.affection = affection;
    }

    public String getTraining() {
        return training;
    }

    public void setTraining(String training) {
        this.training = training;
    }
}
