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
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "controllers.CreateAdServlet", urlPatterns = "/ads/create")
public class CreateAdServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("user") == null) {
            response.sendRedirect("/login");
            return;
        }
        request.setAttribute("breeds", DaoFactory.getAdsDao().all("breeds"));
        request.getRequestDispatcher("/WEB-INF/ads/create.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        User loggedInUser = (User) request.getSession().getAttribute("user");
        Ad ad = new Ad(
                request.getParameter("title"),
                request.getParameter("short_description"),
                request.getParameter("description"),
                Integer.parseInt(request.getParameter("price")),
                "0_image_missing.png",
                (int) loggedInUser.getId()
        );

        Dog dog = new Dog(
        request.getParameter("name"),
        Integer.parseInt(request.getParameter("age")),
        request.getParameter("playfulness"),
        request.getParameter("socialization"),
        request.getParameter("affection"),
        request.getParameter("training")
        );

        int breed = Integer.parseInt(request.getParameter("breeds"));

        DaoFactory.getAdsDao().insert(ad, dog, breed);
        response.sendRedirect("/ads");
    }

}
