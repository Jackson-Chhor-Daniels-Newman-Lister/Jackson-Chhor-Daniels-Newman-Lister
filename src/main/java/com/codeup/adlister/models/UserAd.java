package com.codeup.adlister.models;
public class UserAd {
    private long id;
    private long userId;
    private long adId;

    public UserAd(long userId, long adId) {
        this.userId = userId;
        this.adId = adId;
    }

    public UserAd(long id, long userId, long adId) {
        this.id = id;
        this.userId = userId;
        this.adId = adId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getAdId() {
        return adId;
    }

    public void setAdId(long adId) {
        this.adId = adId;
    }
}
