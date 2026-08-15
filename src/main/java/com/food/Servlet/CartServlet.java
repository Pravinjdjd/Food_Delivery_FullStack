package com.food.Servlet;

import java.io.IOException;

import com.food.DAOImpl.MenuDAOImpl;
import com.food.Model.Cart;
import com.food.Model.CartItem;
import com.food.Model.Menu;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/callCartServlet")
public class CartServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();

		Cart cart = (Cart) session.getAttribute("cart");

		String restaurantIdParam = req.getParameter("restaurantId");

		if (restaurantIdParam == null || restaurantIdParam.trim().isEmpty()) {

			resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Restaurant ID is missing");

			return;
		}

		int newRestaurantId = Integer.parseInt(restaurantIdParam);

		Integer restaurantId = (Integer) session.getAttribute("restaurantId");

		if (cart == null || restaurantId == null || restaurantId != newRestaurantId) {

			cart = new Cart();

			session.setAttribute("cart", cart);

			session.setAttribute("restaurantId", newRestaurantId);
		}
		

		String action = req.getParameter("action");

		if ("add".equals(action)) {

			addItemToCart(req, cart);

		} else if ("update".equals(action)) {

			updateItemToCart(req, cart);

		} else if ("delete".equals(action)) {

			deleteItemToCart(req, cart);
		}

		session.setAttribute("cart", cart);

		resp.sendRedirect("cart.jsp");
	}

	private void addItemToCart(HttpServletRequest req, Cart cart) {

	    int menuId =
	            Integer.parseInt(
	                    req.getParameter("menuId")
	            );

	    int quantity =
	            Integer.parseInt(
	                    req.getParameter("quantity")
	            );


	    MenuDAOImpl menuDAOImpl =
	            new MenuDAOImpl();

	    Menu menu =
	            menuDAOImpl.getMenu(menuId);


	    if (menu == null) {
	        return;
	    }


	    /*
	     * Order is not created yet.
	     * So orderId = 0 temporarily.
	     */

	    int orderId = 0;


	    CartItem cartItem =
	            new CartItem(
	                    menu.getMenuId(),
	                    menu.getRestaurantID(),
	                    menu.getItemName(),
	                    menu.getPrice(),
	                    quantity,
	                    orderId
	            );


	    cart.addItem(cartItem);
	}



	private void updateItemToCart(HttpServletRequest req, Cart cart) {

		int menuId = Integer.parseInt(req.getParameter("menuId"));

		int quantity = Integer.parseInt(req.getParameter("quantity"));

		cart.updateItem(menuId, quantity);
	}

	private void deleteItemToCart(HttpServletRequest req, Cart cart) {

		int menuId = Integer.parseInt(req.getParameter("menuId"));

		cart.removeItem(menuId);
	}
}