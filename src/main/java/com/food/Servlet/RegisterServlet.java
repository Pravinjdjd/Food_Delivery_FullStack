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

@WebServlet("/callRegisterServlet")
public class RegisterServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// ================= GET FORM DATA =================

		String userName = req.getParameter("userName");
		String password = req.getParameter("password");
		String email = req.getParameter("email");
		String address = req.getParameter("address");
		String role = req.getParameter("role");

		// ================= DAO OBJECT =================

		UserDAOImpl userDAOImpl = new UserDAOImpl();

		// ================= CHECK EMAIL =================

		User existingUser = userDAOImpl.getUserByEmail(email);

		if (existingUser != null) {

			resp.sendRedirect("register.html?error=emailExists");

			return;
		}

		// ================= ENCRYPT PASSWORD =================

		String hashpw = BCrypt.hashpw(password, BCrypt.gensalt());

		// ================= CREATE USER =================
		// New user doesn't have profile image yet.
		// Profile image will be added later from Edit Profile.

		User user = new User(userName, hashpw, email, address, role, null);

		// ================= INSERT USER =================

		int res = userDAOImpl.addUser(user);

		// ================= REDIRECT =================

		if (res == 1) {

			// Registration successful

			resp.sendRedirect("login.html?success=registered");

		} else {

			// Registration failed

			resp.sendRedirect("register.html?error=failed");
		}
	}
}