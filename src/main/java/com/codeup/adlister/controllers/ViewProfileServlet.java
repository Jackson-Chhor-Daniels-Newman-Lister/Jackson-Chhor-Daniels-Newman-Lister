package com.codeup.adlister.controllers;

        import com.codeup.adlister.dao.DaoFactory;
        import com.codeup.adlister.models.Ad;
        import javax.servlet.ServletException;
        import javax.servlet.annotation.WebServlet;
        import javax.servlet.http.HttpServlet;
        import javax.servlet.http.HttpServletRequest;
        import javax.servlet.http.HttpServletResponse;
        import java.io.IOException;
        import java.util.List;

@WebServlet(name = "controllers.ViewProfileServlet", urlPatterns = "/profile")
public class ViewProfileServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("user") == null) {
            response.sendRedirect("/login");
            return;
        }
        List<Object> userAd = DaoFactory.getAdsDao().all("ads");
        System.out.println(userAd);
        request.setAttribute("ads", userAd);

        //change
        request.getRequestDispatcher("/WEB-INF/profile.jsp").forward(request, response);
    }
}