package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "controllers.AdsIndexServlet", urlPatterns = "/ads")
public class AdsIndexServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("ads", DaoFactory.getAdsDao().all("ads"));
        request.setAttribute("breeds", DaoFactory.getAdsDao().all("breeds"));
        request.setAttribute("traits", DaoFactory.getAdsDao().all("traits"));
        request.getRequestDispatcher("/WEB-INF/ads/index.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String searchInput = request.getParameter("search-input");
        String selectedBreed = request.getParameter("select-breed");
        String[] selectedTraits = request.getParameterValues("traits");

        if (searchInput != null && !searchInput.isEmpty() && !searchInput.equals("Search")){
            request.getSession().setAttribute("ads", DaoFactory.getAdsDao().searchByString("ads", searchInput));
        } else if (selectedBreed != null && !selectedBreed.equals("0")) {
            request.getSession().setAttribute("ads", DaoFactory.getAdsDao().searchByBreed("ads", selectedBreed));
        } else if (selectedTraits != null) {
            request.getSession().setAttribute("ads", DaoFactory.getAdsDao().searchByTraits("ads", selectedTraits));
        }

        request.getSession().setAttribute("breeds", DaoFactory.getAdsDao().all("breeds"));
        request.getSession().setAttribute("traits", DaoFactory.getAdsDao().all("traits"));
        //response.sendRedirect(redirectString);
        request.getRequestDispatcher("/WEB-INF/ads/index.jsp").forward(request, response);
    }
}
