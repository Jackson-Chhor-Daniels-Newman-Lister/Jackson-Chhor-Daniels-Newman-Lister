package com.codeup.adlister.models;
public class DogTrait {
    private long id;
    private long dogId;
    private long traitId;

    public DogTrait(long dogId, long traitId) {
        this.dogId = dogId;
        this.traitId = traitId;
    }

    public DogTrait(long id, long dogId, long traitId) {
        this.id = id;
        this.dogId = dogId;
        this.traitId = traitId;
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

    public long getTraitId() {
        return traitId;
    }

    public void setTraitId(long traitId) {
        this.traitId = traitId;
    }
}
