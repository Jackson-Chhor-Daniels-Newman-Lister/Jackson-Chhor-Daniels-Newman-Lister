package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
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
        int userId = user.getId();
        int adId = Integer.parseInt(request.getParameter("adId"));

        boolean validUserToAd = DaoFactory.getUserAdsDao().searchOne(userId, adId);
        if (validUserToAd){
            request.setAttribute("ad", DaoFactory.getAdsDao().searchOne(adId));
            request.setAttribute("dog", DaoFactory.getDogsDao().searchOne(adId));
            request.getRequestDispatcher("/WEB-INF/ads/delete-ad.jsp").forward(request, response);
        } else {
            response.sendRedirect("/profile");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int adId = Integer.parseInt(request.getParameter("ad_id"));

        DaoFactory.getDogBreedsDao().delete(adId);
        DaoFactory.getDogTraitsDao().delete(adId);
        DaoFactory.getUserAdsDao().delete(adId);
        DaoFactory.getAdsDao().delete(adId);
        DaoFactory.getDogsDao().delete(adId);

        DaoFactory.getAdsDao().delete(adId);
        response.sendRedirect("/profile");
    }
}
