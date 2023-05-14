package com.codeup.adlister.dao;

import com.codeup.adlister.models.Ad;
import com.codeup.adlister.util.Config;

public class DaoFactory {
    private static Ads adsDao;
    private static Breeds breedsDao;
    private static DogBreeds dogBreedsDao;
    private static Dogs dogsDao;
    private static DogTraits dogTraitsDao;
    private static Traits traitsDao;
    private static UserAds userAdsDao;
    private static Users usersDao;
    private static Config config = new Config();

    private static Ad adId;

    public static long getAdId() {

        return adId.getId();
    }

    public static Ads getAdsDao() {
        if (adsDao == null) {
            adsDao = new MySQLAdsDao(config);
        }
        return adsDao;
    }

    public static Breeds getBreedsDao() {
        if (breedsDao == null) {
            breedsDao = new MySQLBreedsDao(config);
        }
        return breedsDao;
    }

    public static DogBreeds getDogBreedsDao() {
        if (dogBreedsDao == null) {
            dogBreedsDao = new MySQLDogBreedsDao(config);
        }
        return dogBreedsDao;
    }

    public static Dogs getDogsDao() {
        if (dogsDao == null) {
            dogsDao = new MySQLDogsDao(config);
        }
        return dogsDao;
    }

    public static DogTraits getDogTraitsDao() {
        if (dogTraitsDao == null) {
            dogTraitsDao = new MySQLDogTraitsDao(config);
        }
        return dogTraitsDao;
    }

    public static Traits getTraitsDao() {
        if (traitsDao == null) {
            traitsDao = new MySQLTraitsDao(config);
        }
        return traitsDao;
    }

    public static UserAds getUserAdsDao() {
        if (userAdsDao == null) {
            userAdsDao = new MySQLUserAdsDao(config);
        }
        return userAdsDao;
    }

    public static Users getUsersDao() {
        if (usersDao == null) {
            usersDao = new MySQLUsersDao(config);
        }
        return usersDao;
    }
}
