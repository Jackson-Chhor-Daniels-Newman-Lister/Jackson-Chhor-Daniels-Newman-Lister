package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.Breed;
import com.codeup.adlister.models.DogBreed;
import com.codeup.adlister.models.DogTrait;
import com.codeup.adlister.models.Trait;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "controllers.MoreInfoServlet", urlPatterns = "/more-info")

public class MoreInfoServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int adId = Integer.parseInt(request.getParameter("adId"));
        request.setAttribute("ad", DaoFactory.getAdsDao().searchOne(adId));
        request.setAttribute("dog", DaoFactory.getDogsDao().searchOne(adId));

        List<DogTrait> dogTraitsObjects = DaoFactory.getDogTraitsDao().searchOne(adId);
        List<Trait> dogTraits = new ArrayList<>();
        for (DogTrait dogTrait:dogTraitsObjects) {
           dogTraits.add(DaoFactory.getTraitsDao().searchOne((int)dogTrait.getTraitId()));
        }
        request.setAttribute("traits", dogTraits);

        DogBreed dogBreed = DaoFactory.getDogBreedsDao().searchOne(adId);
        request.setAttribute("breed", DaoFactory.getBreedsDao().searchOne((int)dogBreed.getBreedId()));

        request.getRequestDispatcher("/WEB-INF/ads/more-info.jsp").forward(request, response);
    }

}
