package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.Breed;

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
        String selectedTraits = request.getParameter("traits");


        System.out.println("searchInput = " + searchInput);
        System.out.println("selectedBreed = " + selectedBreed);
        System.out.println("selectedTraits = " + selectedTraits);


        if (searchInput != null && !searchInput.isEmpty() && !searchInput.equals("Search")){
            System.out.println("11111111111111111111111111111111111111111111111111");
            System.out.println("Search");
            request.getSession().setAttribute("ads", DaoFactory.getAdsDao().some("ads", searchInput));
        } else if (selectedBreed != null && !selectedBreed.equals("0")) {
            System.out.println("22222222222222222222222222222222222222222222222222");
            System.out.println("Breeds");
            request.getSession().setAttribute("ads", DaoFactory.getAdsDao().some("ads", selectedBreed));
        } else if (selectedTraits != null) {
            System.out.println("33333333333333333333333333333333333333333333333333");
            System.out.println("Traits");
            request.getSession().setAttribute("ads", DaoFactory.getAdsDao().some("ads", selectedTraits));
        }

        //String redirectString = "/ads?search=" + searchInput;

        request.getSession().setAttribute("breeds", DaoFactory.getAdsDao().all("breeds"));
        request.getSession().setAttribute("traits", DaoFactory.getAdsDao().all("traits"));
        //response.sendRedirect(redirectString);
        request.getRequestDispatcher("/WEB-INF/ads/index.jsp").forward(request, response);
    }
}
