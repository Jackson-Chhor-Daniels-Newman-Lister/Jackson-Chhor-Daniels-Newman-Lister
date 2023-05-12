
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
        long adId = Long.valueOf(request.getParameter("adId"));
        request.setAttribute("ad", DaoFactory.getAdsDao().individual(adId));

        request.getRequestDispatcher("/WEB-INF/ads/edit-info.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long adId = Long.valueOf(request.getParameter("adId"));
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        double price = Double.parseDouble(request.getParameter("price"));
        String breeds = request.getParameter("breeds");
        String traits = request.getParameter("traits");
        String name = request.getParameter("name");
        int age = Integer.parseInt(request.getParameter("age"));
        int playfulness = Integer.parseInt(request.getParameter("playfulness"));
        int socialization = Integer.parseInt(request.getParameter("socialization"));
        int affection = Integer.parseInt(request.getParameter("affection"));
        int training = Integer.parseInt(request.getParameter("training"));

        Ad ad = (Ad) DaoFactory.getAdsDao().individual(adId);
        ad.setTitle(title);
        ad.setDescription(description);
        ad.setPrice((int) price);
        ad.setBreeds(breeds);
        ad.setTraits(traits);
        ad.setName(name);
        ad.setAge(age);
        ad.setPlayfulness(playfulness);
        ad.setSocialization(socialization);
        ad.setAffection(affection);
        ad.setTraining(training);

//        DaoFactory.getAdsDao().update(ad);

        response.sendRedirect("/ads");
    }
}
