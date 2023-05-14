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

@WebServlet(name = "DeleteServlet", urlPatterns = "/delete-ad")
public class DeleteServlet  extends HttpServlet {
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
            request.setAttribute("breeds", DaoFactory.getAdsDao().all("breeds"));
            request.setAttribute("traits", DaoFactory.getAdsDao().all("traits"));
            request.getRequestDispatcher("/WEB-INF/ads/delete-ad.jsp").forward(request, response);
        } else {
            response.sendRedirect("/profile");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User loggedInUser = (User) request.getSession().getAttribute("user");

        int adId = Integer.parseInt(request.getParameter("ad_id"));
        int userId = (int)loggedInUser.getId();

        DaoFactory.getAdsDao().delete(adId);
        response.sendRedirect("/profile");
    }
}
