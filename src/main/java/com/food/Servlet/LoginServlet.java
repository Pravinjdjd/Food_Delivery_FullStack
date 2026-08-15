package com.food.Servlet;

import java.io.IOException;

import org.mindrot.jbcrypt.BCrypt;

import com.food.DAOImpl.UserDAOImpl;
import com.food.Model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/callLoginServlet")
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String email = req.getParameter("email");
		String password = req.getParameter("password");

		UserDAOImpl userDAOImpl = new UserDAOImpl();

		// Find user using email
		User user = userDAOImpl.getUserByEmail(email);

		// User does not exist
		if (user == null) {

			resp.sendRedirect("login.html");
			return;
		}

		// Password stored in database
		String dbPassword = user.getPassword();

		// Check BCrypt password
		if (BCrypt.checkpw(password, dbPassword)) {

			HttpSession session = req.getSession();
			session.setAttribute("userId", user.getUserId());
			session.setAttribute("userName", user.getUserName());
			session.setAttribute("email", user.getEmail());
			session.setAttribute("role", user.getRole());

			session.setAttribute("profileImage", user.getProfileImage());
			// Login successful
			resp.sendRedirect("callRestaurantServlet");

		} else {

			// Wrong password
			resp.sendRedirect("login.html");
		}
	}
}