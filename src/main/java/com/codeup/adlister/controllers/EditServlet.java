
package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.Ad;
import com.codeup.adlister.models.Dog;
import com.codeup.adlister.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "EditInfoServlet", urlPatterns = "/edit-info")
public class EditServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (request.getSession().getAttribute("user") == null) {
            response.sendRedirect("/login");
            return;
        }

        User user = (User) request.getSession().getAttribute("user");
        long userId = user.getId();
        long adId = Long.valueOf(request.getParameter("adId"));

        boolean validUserToAd = DaoFactory.getUsersDao().userOwnsAd(adId,userId);
        if (validUserToAd){
            request.setAttribute("ad", DaoFactory.getAdsDao().individual(adId));
            request.getRequestDispatcher("/WEB-INF/ads/edit-info.jsp").forward(request, response);
        } else {
            response.sendRedirect("/profile");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long adId = Long.valueOf(request.getParameter("ad_id"));
        String title = request.getParameter("title");
        String shortDescription = request.getParameter("description");
        String description = request.getParameter("description");
        int price = Integer.parseInt(request.getParameter("price"));

        //long dogId = Long.valueOf(request.getParameter("dog_id"));
        String dogName = request.getParameter("name");
        int dogAge = Integer.parseInt(request.getParameter("age"));
        String playfulness = request.getParameter("playfulness");
        String socialization = request.getParameter("socialization");
        String affection = request.getParameter("affection");
        String training = request.getParameter("training");

        Ad ad = new Ad(adId,title,shortDescription,description, price, "notchanging", (int) adId);
        Dog dog = new Dog(adId, dogName, dogAge, playfulness, socialization, affection, training);

        DaoFactory.getAdsDao().submitEdits(ad, dog);

        response.sendRedirect("/ads");
    }
}
