package com.codeup.adlister.models;
public class UserAd {
    private int id;
    private int userId;
    private int adId;

    public UserAd(int userId, int adId) {
        this.userId = userId;
        this.adId = adId;
    }

    public UserAd(int id, int userId, int adId) {
        this.id = id;
        this.userId = userId;
        this.adId = adId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getAdId() {
        return adId;
    }

    public void setAdId(int adId) {
        this.adId = adId;
    }
}
