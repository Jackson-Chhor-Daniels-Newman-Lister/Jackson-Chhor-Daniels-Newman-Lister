
package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.Ad;

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
        long adId = Long.parseLong(request.getParameter("adId"));
        Ad ad = (Ad) DaoFactory.getAdsDao().individual(adId);
        request.setAttribute("ad", ad);
        request.getRequestDispatcher("/WEB-INF/ads/edit-info.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long adId = Long.valueOf(request.getParameter("adId"));
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        double price = Double.parseDouble(request.getParameter("price"));

        Ad ad = (Ad) DaoFactory.getAdsDao().individual(adId);
        ad.setTitle(title);
        ad.setDescription(description);
        ad.setPrice((int) price);

//        I still need to create a method i think???
//        DaoFactory.getAdsDao().update(ad);

        response.sendRedirect("/ads");
    }
}
