<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="java.util.*, com.food.Model.Restaurant"%>
<%@ page import="com.food.Model.User"%>

<!DOCTYPE html>

<html>

<head>

<meta charset="UTF-8">

<title>FoodExpress - Restaurants</title>

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">

<link rel="stylesheet" href="restaurant.css">

</head>

<body>


	<!-- ================= NAVBAR ================= -->

	<nav class="navbar">

		<div class="logo">

			<a href="callRestaurantServlet"> 🍔 FoodExpress </a>

		</div>


		<div class="nav-links">

			<a href="callRestaurantServlet"> Home </a> <a
				href="callRestaurantServlet" class="active"> Restaurants </a> <a
				href="cart.jsp"> Cart </a>


			<%
            Integer userId =
                (Integer) session.getAttribute("userId");

            if (userId == null) {
        %>

			<a href="login.html"> Login </a> <a href="register.html"> Sign Up
			</a>

			<%
            } else {
        %>

			<a href="profile.jsp"> <i class="fa-solid fa-user"></i> Profile
			</a> <a href="LogoutServlet"> <i
				class="fa-solid fa-right-from-bracket"></i> Logout
			</a>

			<%
            }
        %>

		</div>

	</nav>



	<!-- ================= HERO ================= -->

	<section class="hero">

		<div class="overlay">

			<h1>Discover The Best Food Near You</h1>

			<p>Fresh Food • Fast Delivery • Great Discounts</p>


			<!-- ================= SEARCH ================= -->

			<div class="restaurant-search-wrapper">

				<form action="callRestaurantServlet" method="get"
					class="restaurant-search">

					<i class="fa-solid fa-magnifying-glass search-icon"></i> <input
						type="text" name="search" placeholder="Search restaurant..."
						autocomplete="off">

					<button type="submit">Search</button>

				</form>

			</div>

		</div>

	</section>



	<!-- ================= TITLE ================= -->

	<section class="heading">

		<h2>Popular Restaurants</h2>

	</section>



	<!-- ================= RESTAURANTS ================= -->

	<section class="restaurants">

		<%
    List<Restaurant> allRestaurant =
        (List<Restaurant>) request.getAttribute("allRestaurant");

    if (allRestaurant != null && !allRestaurant.isEmpty()) {

        for (Restaurant restaurant : allRestaurant) {
%>


		<a
			href="callMenuServlet?restaurantId=<%= restaurant.getRestaurantId() %>">

			<div class="card">


				<!-- RESTAURANT IMAGE -->

				<img src="<%= restaurant.getImage() %>"
					alt="<%= restaurant.getRestaurantName() %>">


				<div class="details">


					<!-- RESTAURANT NAME -->

					<h2>
						<%= restaurant.getRestaurantName() %>
					</h2>


					<!-- RATING -->

					<p class="rating">

						⭐
						<%= restaurant.getRating() %>

					</p>


					<!-- ADDRESS -->

					<p>

						📍
						<%= restaurant.getAddress() %>

					</p>


					<!-- CUISINE -->

					<p>

						🍴
						<%= restaurant.getCuisineType() %>

					</p>


					<!-- DELIVERY TIME -->

					<p>

						🕒
						<%= restaurant.getDeliveryTime() %>
						Minutes

					</p>


					<!-- DISCOUNT -->

					<div class="discount">40% OFF upto ₹120</div>


				</div>

			</div>

		</a>


		<%
        }

    } else {
%>


		<!-- NO RESTAURANT -->

		<div class="no-results">

			<i class="fa-solid fa-face-sad-tear"></i>

			<h2>No restaurants found</h2>

			<p>Try searching with another restaurant name.</p>

			<a href="callRestaurantServlet"> View All Restaurants </a>

		</div>


		<%
    }
%>

	</section>



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