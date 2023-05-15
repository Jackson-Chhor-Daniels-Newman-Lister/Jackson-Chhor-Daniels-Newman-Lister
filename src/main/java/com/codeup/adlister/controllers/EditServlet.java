
package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "EditInfoServlet", urlPatterns = "/edit-info")
public class EditServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("user") == null) {
            response.sendRedirect("/login");
            return;
        }

        User user = (User) request.getSession().getAttribute("user");
        int userId = user.getId();
        int adId = Integer.parseInt(request.getParameter("adId"));

        boolean validUserToAd = DaoFactory.getUserAdsDao().searchOne(userId, adId);
        if (validUserToAd){

            request.setAttribute("ad", DaoFactory.getAdsDao().searchOne(adId));
            request.setAttribute("dog", DaoFactory.getDogsDao().searchOne(adId));

            //gives me current dog traits
            List<DogTrait> dogTraitsObjects = DaoFactory.getDogTraitsDao().searchOne(adId);
            List<Trait> dogTraits = new ArrayList<>();
            for (DogTrait dogTrait:dogTraitsObjects) {
                dogTraits.add(DaoFactory.getTraitsDao().searchOne((int)dogTrait.getTraitId()));
            }
            request.setAttribute("checked", dogTraits);

            DogBreed dogBreed = DaoFactory.getDogBreedsDao().searchOne(adId);
            request.setAttribute("selected", DaoFactory.getBreedsDao().searchOne((int)dogBreed.getBreedId()));

            request.setAttribute("breeds", DaoFactory.getBreedsDao().searchAll());
            request.setAttribute("traits", DaoFactory.getTraitsDao().searchAll());
            request.getRequestDispatcher("/WEB-INF/ads/edit-info.jsp").forward(request, response);
        } else {
            response.sendRedirect("/profile");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int adId = Integer.parseInt(request.getParameter("ad_id"));

        String title = request.getParameter("title");
        String shortDescription = request.getParameter("short_description");
        String description = request.getParameter("description");
        int price = Integer.parseInt(request.getParameter("price"));

        //int dogId = Long.valueOf(request.getParameter("dog_id"));
        String dogName = request.getParameter("name");
        int dogAge = Integer.parseInt(request.getParameter("age"));
        String playfulness = request.getParameter("playfulness");
        String socialization = request.getParameter("socialization");
        String affection = request.getParameter("affection");
        String training = request.getParameter("training");

        String[] selectedTraits = request.getParameterValues("traits");
        int[] traitIds = new int[selectedTraits.length];
        for (int i = 0; i < selectedTraits.length; i++) {
            traitIds[i] = Integer.parseInt(selectedTraits[i]);
        }

        int breedId = Integer.parseInt(request.getParameter("breeds"));

        Ad ad = new Ad(adId,title,shortDescription,description, price, "notchanging", adId);
        Dog dog = new Dog(adId, dogName, dogAge, playfulness, socialization, affection, training);


        DaoFactory.getAdsDao().edit(ad);
        DaoFactory.getDogsDao().edit(dog);
        DaoFactory.getDogBreedsDao().edit(adId, breedId);
        DaoFactory.getDogTraitsDao().edit(adId, traitIds);

        response.sendRedirect("/ads");
    }
}
