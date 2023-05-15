package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.dao.MySQLAdsDao;
import com.codeup.adlister.models.Ad;
import com.codeup.adlister.models.Dog;
import com.codeup.adlister.models.User;
import com.codeup.adlister.models.UserAd;
import com.codeup.adlister.util.Config;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "controllers.ViewProfileServlet", urlPatterns = "/profile")
public class ViewProfileServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("user") == null) {
            response.sendRedirect("/login");
            return;
        }
        User user = (User) request.getSession().getAttribute("user");
        int userId = user.getId();

        List<Ad> ads = new ArrayList<>();
        List<Dog> dogs = new ArrayList<>();
        List<UserAd> userAds = DaoFactory.getUserAdsDao().searchAll(userId);
        for (UserAd userAd:userAds) {
            ads.add(DaoFactory.getAdsDao().searchOne(userAd.getAdId()));
            dogs.add(DaoFactory.getDogsDao().searchOne(userAd.getAdId()));
        }

        request.setAttribute("userAd", ads);
        request.setAttribute("dog", dogs);

        request.getRequestDispatcher("/WEB-INF/profile.jsp").forward(request, response);
    }
}