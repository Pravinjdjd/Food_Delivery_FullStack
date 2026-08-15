package com.food.Servlet;

import java.io.IOException;

import org.mindrot.jbcrypt.BCrypt;

import com.food.DAO.UserDAO;
import com.food.DAOImpl.UserDAOImpl;
import com.food.Model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/ChangePasswordServlet")
public class ChangePasswordServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();

		Integer userId = (Integer) session.getAttribute("userId");

		if (userId == null) {

			response.sendRedirect("login.html");

			return;
		}

		String currentPassword = request.getParameter("currentPassword");

		String newPassword = request.getParameter("newPassword");

		String confirmPassword = request.getParameter("confirmPassword");

		// ================= GET USER =================

		UserDAO userDAO = new UserDAOImpl();

		User user = userDAO.getUser(userId);

		if (user == null) {

			response.sendRedirect("profile.jsp");

			return;
		}

		// ================= CHECK CURRENT PASSWORD =================

		boolean currentPasswordCorrect = BCrypt.checkpw(currentPassword, user.getPassword());

		if (!currentPasswordCorrect) {

			response.sendRedirect("change-password.jsp?error=wrongCurrent");

			return;
		}

		// ================= CHECK NEW PASSWORD =================

		if (!newPassword.equals(confirmPassword)) {

			response.sendRedirect("change-password.jsp?error=passwordMismatch");

			return;
		}

		// ================= HASH NEW PASSWORD =================

		String hashedPassword = BCrypt.hashpw(newPassword, BCrypt.gensalt());

		// ================= UPDATE DATABASE =================

		boolean updated = userDAO.updatePassword(userId, hashedPassword);

		if (updated) {

			response.sendRedirect("change-password.jsp?success=passwordUpdated");

		} else {

			response.sendRedirect("change-password.jsp?error=failed");
		}
	}
}