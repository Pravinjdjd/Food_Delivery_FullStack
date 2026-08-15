package com.food.Servlet;

import java.io.IOException;
import java.util.List;

import com.food.DAOImpl.MenuDAOImpl;
import com.food.Model.Menu;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/callMenuServlet")
public class MenuServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		MenuDAOImpl menuDAOImpl = new MenuDAOImpl();

		int restaurantID = Integer.parseInt(req.getParameter("restaurantId"));

		// Search value
		String search = req.getParameter("search");

		List<Menu> allMenu;

		// Search menu
		if (search != null && !search.trim().isEmpty()) {

			allMenu = menuDAOImpl.searchMenuByRestaurant(restaurantID, search.trim());

		} else {

			// Show all menu
			allMenu = menuDAOImpl.getAllMenuByRestaurant(restaurantID);
		}

		req.setAttribute("allMenu", allMenu);

		RequestDispatcher requestDispatcher = req.getRequestDispatcher("menu.jsp");

		requestDispatcher.forward(req, resp);
	}
}