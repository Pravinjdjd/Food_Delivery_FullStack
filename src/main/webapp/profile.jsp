<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
Integer userId = (Integer) session.getAttribute("userId");
String userName = (String) session.getAttribute("userName");
String email = (String) session.getAttribute("email");
String role = (String) session.getAttribute("role");
String profileImage = (String) session.getAttribute("profileImage");

if (userId == null) {
    response.sendRedirect("login.html");
    return;
}

if (userName == null) userName = "User";
if (email == null) email = "";
if (role == null) role = "USER";
%>

<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="UTF-8">

<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>FoodExpress - Profile</title>

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">

<link rel="stylesheet" href="profile.css">

</head>

<body>


	<!-- ================= NAVBAR ================= -->

	<nav>

		<div class="logo">
			<a href="callRestaurantServlet"> 🍔 FoodExpress </a>
		</div>

		<div class="right-menu">

			<a href="callRestaurantServlet"> Home </a> <a
				href="callRestaurantServlet"> Restaurants </a> <a href="cart.jsp">
				Cart </a>

			<!-- PROFILE -->

			<a href="profile.jsp" class="profile active-profile"> <i
				class="fa-solid fa-user"></i> <span>Profile</span>

			</a> <a href="LogoutServlet" class="logout-nav"> <i
				class="fa-solid fa-right-from-bracket"></i> Logout

			</a>

		</div>

	</nav>


	<!-- ================= PROFILE SECTION ================= -->

	<section class="profile-section">

		<div class="profile-container">


			<!-- ================= PROFILE HEADER ================= -->

			<div class="profile-header">


				<!-- PROFILE IMAGE -->

				<div class="profile-avatar">

					<% if (profileImage != null && !profileImage.isEmpty()) { %>

					<img src="uploads/<%= profileImage %>" alt="Profile Image">

					<% } else { %>

					<i class="fa-solid fa-user"></i>

					<% } %>

				</div>


				<!-- PROFILE DETAILS -->

				<div class="profile-title">

					<h1>
						Welcome,
						<%= userName %>!
					</h1>

					<p>Manage your FoodExpress account</p>

				</div>


				<!-- EDIT BUTTON -->

				<button class="edit-btn"
					onclick="window.location.href='edit-profile.jsp'">

					<i class="fa-solid fa-pen"></i> Edit Profile

				</button>

			</div>


			<!-- ================= PROFILE CONTENT ================= -->

			<div class="profile-content">


				<!-- ================= PERSONAL INFORMATION ================= -->

				<div class="details-card">

					<div class="card-title">

						<i class="fa-solid fa-user"></i>

						<h2>Personal Information</h2>

					</div>


					<div class="details-grid">


						<!-- USERNAME -->

						<div class="detail-item">

							<div class="detail-icon">

								<i class="fa-solid fa-user"></i>

							</div>

							<div>

								<span> Username </span>

								<h3>
									<%= userName %>
								</h3>

							</div>

						</div>


						<!-- EMAIL -->

						<div class="detail-item">

							<div class="detail-icon">

								<i class="fa-solid fa-envelope"></i>

							</div>

							<div>

								<span> Email </span>

								<h3>
									<%= email %>
								</h3>

							</div>

						</div>


						<!-- USER ID -->

						<div class="detail-item">

							<div class="detail-icon">

								<i class="fa-solid fa-id-card"></i>

							</div>

							<div>

								<span> User ID </span>

								<h3>
									<%= userId %>
								</h3>

							</div>

						</div>


						<!-- ROLE -->

						<div class="detail-item">

							<div class="detail-icon">

								<i class="fa-solid fa-user-tag"></i>

							</div>

							<div>

								<span> Role </span>

								<h3 class="role">
									<%= role %>
								</h3>

							</div>

						</div>

					</div>


					<!-- ADDRESS -->

					<div class="address-item">

						<div class="detail-icon">

							<i class="fa-solid fa-location-dot"></i>

						</div>

						<div>

							<span> Delivery Address </span>

							<h3>Address information can be updated from Edit Profile.</h3>

						</div>

					</div>

				</div>


				<!-- ================= ACCOUNT SETTINGS ================= -->

				<div class="options-card">

					<div class="card-title">

						<i class="fa-solid fa-gear"></i>

						<h2>Account Settings</h2>

					</div>


					<!-- EDIT PROFILE -->

					<a href="edit-profile.jsp" class="option">

						<div class="option-icon">

							<i class="fa-solid fa-user-pen"></i>

						</div>

						<div class="option-details">

							<h3>Edit Profile</h3>

							<p>Update your personal information</p>

						</div> <i class="fa-solid fa-chevron-right arrow"></i>

					</a>


					<!-- CHANGE PASSWORD -->

					<a href="change-password.jsp" class="option">

						<div class="option-icon">

							<i class="fa-solid fa-lock"></i>

						</div>

						<div class="option-details">

							<h3>Change Password</h3>

							<p>Update your account password</p>

						</div> <i class="fa-solid fa-chevron-right arrow"></i>

					</a>


					<!-- MY ORDERS -->

					<a href="orders.jsp" class="option">

						<div class="option-icon">

							<i class="fa-solid fa-bag-shopping"></i>

						</div>

						<div class="option-details">

							<h3>My Orders</h3>

							<p>View your previous food orders</p>

						</div> <i class="fa-solid fa-chevron-right arrow"></i>

					</a>


					<!-- LOGOUT -->

					<a href="LogoutServlet" class="option logout">

						<div class="option-icon">

							<i class="fa-solid fa-right-from-bracket"></i>

						</div>

						<div class="option-details">

							<h3>Logout</h3>

							<p>Sign out from your account</p>

						</div> <i class="fa-solid fa-chevron-right arrow"></i>

					</a>

				</div>

			</div>


			<!-- ================= ORDER SUMMARY ================= -->

			<!-- <div class="order-summary">


				<div class="summary-box">

					<div class="summary-icon">
						<i class="fa-solid fa-bag-shopping"></i>
					</div>

					<div>

						<h2>0</h2>

						<p>Total Orders</p>

					</div>

				</div>


				<div class="summary-box">

					<div class="summary-icon">
						<i class="fa-solid fa-circle-check"></i>
					</div>

					<div>

						<h2>0</h2>

						<p>Delivered</p>

					</div>

				</div>


				<div class="summary-box">

					<div class="summary-icon">
						<i class="fa-solid fa-clock"></i>
					</div>

					<div>

						<h2>0</h2>

						<p>Pending</p>

					</div>

				</div>


				<div class="summary-box">

					<div class="summary-icon">
						<i class="fa-solid fa-heart"></i>
					</div>

					<div>

						<h2>0</h2>

						<p>Favorites</p>

					</div>

				</div>

			</div>

		</div>

	</section> -->


	<!-- ================= FOOTER ================= -->

	<footer>

		<div class="footer-content">

			<h2>🍔 FoodExpress</h2>

			<p>Delivering happiness with delicious food.</p>

			<p>© 2026 FoodExpress. All Rights Reserved.</p>

		</div>

	</footer>


</body>
</html>