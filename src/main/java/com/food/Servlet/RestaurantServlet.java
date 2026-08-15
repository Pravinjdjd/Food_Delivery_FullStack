package com.food.Servlet;

import java.io.IOException;
import java.util.List;

import com.food.DAOImpl.RestaurantDAOImpl;
import com.food.Model.Restaurant;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/callRestaurantServlet")
public class RestaurantServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        RestaurantDAOImpl restaurantDAOImpl = new RestaurantDAOImpl();

        // Get search value from search bar
        String search = req.getParameter("search");

        List<Restaurant> restaurants;

        // If user searches something
        if (search != null && !search.trim().isEmpty()) {

            restaurants = restaurantDAOImpl.searchRestaurants(search.trim());

        } else {

            // If search box is empty
            restaurants = restaurantDAOImpl.getAllRestaurant();
        }

        // Send restaurant list to JSP
        req.setAttribute("allRestaurant", restaurants);

        // Forward only ONE time
        RequestDispatcher rd = req.getRequestDispatcher("restaurant.jsp");
        rd.forward(req, resp);
    }
}