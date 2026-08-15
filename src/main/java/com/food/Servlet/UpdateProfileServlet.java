package com.food.Servlet;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import com.food.DAO.UserDAO;
import com.food.DAOImpl.UserDAOImpl;
import com.food.Model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

@WebServlet("/UpdateProfileServlet")
@MultipartConfig
public class UpdateProfileServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// ================= SESSION =================

		HttpSession session = request.getSession();

		Integer userId = (Integer) session.getAttribute("userId");

		if (userId == null) {

			response.sendRedirect("login.html");
			return;
		}

		// ================= GET CURRENT USER =================

		UserDAO userDAO = new UserDAOImpl();

		User existingUser = userDAO.getUser(userId);

		if (existingUser == null) {

			response.sendRedirect("login.html");
			return;
		}

		// ================= FORM DATA =================

		String userName = request.getParameter("userName");

		String email = request.getParameter("email");

		String address = request.getParameter("address");

		// ================= OLD IMAGE =================

		String imageName = existingUser.getProfileImage();

		// ================= NEW IMAGE =================

		Part imagePart = request.getPart("profileImage");

		if (imagePart != null && imagePart.getSize() > 0) {

			imageName = Paths.get(imagePart.getSubmittedFileName()).getFileName().toString();

			String uploadPath = getServletContext().getRealPath("/uploads");

			File uploadDir = new File(uploadPath);

			if (!uploadDir.exists()) {

				uploadDir.mkdirs();
			}

			imagePart.write(uploadPath + File.separator + imageName);
		}

		// ================= CREATE USER =================

		User user = new User();

		user.setUserId(userId);
		user.setUserName(userName);
		user.setEmail(email);
		user.setAddress(address);
		user.setProfileImage(imageName);

		// ================= DATABASE UPDATE =================

		userDAO.updateUser(user);

		// ================= UPDATE SESSION =================

		session.setAttribute("userName", userName);

		session.setAttribute("email", email);

		session.setAttribute("profileImage", imageName);

		// ================= REDIRECT =================

		response.sendRedirect("profile.jsp?success=profileUpdated");
	}
}