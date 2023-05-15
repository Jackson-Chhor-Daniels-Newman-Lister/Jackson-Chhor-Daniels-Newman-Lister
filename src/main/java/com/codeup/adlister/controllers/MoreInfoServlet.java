package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;

import java.io.IOException;
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

        request.getRequestDispatcher("/WEB-INF/ads/more-info.jsp").forward(request, response);
    }

}
