package com.codeup.adlister.models;
public class DogBreed {
    private long id;
    private long dogId;
    private long breedId;

    public DogBreed(long dogId, long breedId) {
        this.dogId = dogId;
        this.breedId = breedId;
    }

    public DogBreed(long id, long dogId, long breedId) {
        this.id = id;
        this.dogId = dogId;
        this.breedId = breedId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getDogId() {
        return dogId;
    }

    public void setDogId(long dogId) {
        this.dogId = dogId;
    }

    public long getBreedId() {
        return breedId;
    }

    public void setBreedId(long breedId) {
        this.breedId = breedId;
    }
}
