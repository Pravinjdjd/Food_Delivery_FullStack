<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="com.food.Model.Cart"%>
<%@ page import="com.food.Model.CartItem"%>

<%
Integer userId = (Integer) session.getAttribute("userId");

if (userId == null) {
response.sendRedirect("login.html");
return;
}

Cart cart = (Cart) session.getAttribute("cart");

double grandTotal = 0;

if (cart != null && !cart.getItems().isEmpty()) {
%>

<!DOCTYPE html>

<html lang="en">

<head>

<meta charset="UTF-8">

<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>FoodExpress - Checkout</title>

<link rel="stylesheet" href="checkout.css">


</head>

<body>

	<!-- ================= NAVBAR ================= -->

	<nav class="navbar">
		<div class="logo">
			<a href="callRestaurantServlet"> 🍔 FoodExpress </a>
		</div>

		<div class="nav-links">

			<a href="callRestaurantServlet"> Home </a> <a
				href="callRestaurantServlet"> Restaurants </a> <a href="cart.jsp">
				Cart </a> <a href="profile.jsp"> Profile </a> <a href="LogoutServlet"
				class="logout-btn"> Logout </a>

		</div>


	</nav>

	<!-- ================= MAIN ================= -->

	<div class="checkout-container">


		<h1 class="page-title">Checkout</h1>


		<!-- ================= PLACE ORDER FORM ================= -->

		<form action="callPlaceOrderServlet" method="post">

			<div class="checkout-grid">


				<!-- ================= LEFT SIDE ================= -->

				<div class="checkout-left">


					<!-- ================= DELIVERY ADDRESS ================= -->

					<div class="checkout-card">

						<h2>Delivery Address</h2>

						<div class="form-group">

							<label> Full Name </label> <input type="text" name="fullName"
								placeholder="Enter your name" required>

						</div>


						<div class="form-group">

							<label> Address </label>

							<textarea name="address"
								placeholder="Enter your delivery address" rows="4" required></textarea>

						</div>


						<div class="form-row">

							<div class="form-group">

								<label> City </label> <input type="text" name="city"
									placeholder="City" required>

							</div>


							<div class="form-group">

								<label> Pincode </label> <input type="text" name="pincode"
									placeholder="Pincode" required>

							</div>

						</div>

					</div>


					<!-- ================= PAYMENT METHOD ================= -->

					<div class="checkout-card">

						<h2>Payment Method</h2>

						<label class="payment-option"> <input type="radio"
							name="paymentMode" value="Cash" checked> <span>
								Cash on Delivery </span>

						</label> <label class="payment-option"> <input type="radio"
							name="paymentMode" value="UPI"> <span> UPI </span>

						</label> <label class="payment-option"> <input type="radio"
							name="paymentMode" value="Card"> <span> Card </span>

						</label>

					</div>

				</div>


				<!-- ================= RIGHT SIDE ================= -->

				<div class="checkout-right">

					<div class="checkout-card order-summary">

						<h2>Order Summary</h2>


						<%
                for (CartItem item : cart.getItems().values()) {

                    grandTotal =
                        grandTotal + item.getTotalPrice();
                %>

						<div class="order-item">

							<div class="order-item-info">

								<h3>
									<%= item.getName() %>
								</h3>

								<p>
									Quantity:
									<%= item.getQuantity() %>
								</p>

							</div>


							<div class="order-item-price">

								₹<%= item.getTotalPrice() %>

							</div>

						</div>

						<%
                }
                %>


						<!-- ================= TOTAL ================= -->

						<div class="summary-line">

							<span> Item Total </span> <span> ₹<%= grandTotal %>
							</span>

						</div>


						<%
                double deliveryFee = 40;

                double tax =
                    grandTotal * 0.05;

                double finalTotal =
                    grandTotal + deliveryFee + tax;

                session.setAttribute(
                    "finalTotal",
                    finalTotal
                );
                %>


						<div class="summary-line">

							<span> Delivery Fee </span> <span> ₹<%= deliveryFee %>
							</span>

						</div>


						<div class="summary-line">

							<span> Tax </span> <span> ₹<%= tax %>
							</span>

						</div>


						<!-- ================= GRAND TOTAL ================= -->

						<div class="grand-total">

							<span> Grand Total </span> <span> ₹<%= finalTotal %>
							</span>

						</div>


						<input type="hidden" name="totalAmount" value="<%= finalTotal %>">


						<!-- ================= PLACE ORDER ================= -->

						<button type="submit" class="place-order-btn">Place Order

						</button>


						<a href="cart.jsp" class="back-cart"> Back to Cart </a>

					</div>

				</div>

			</div>

		</form>


	</div>

	<!-- ================= FOOTER ================= -->

	<footer>


		<div class="footer-content">

			<h2>FoodExpress</h2>

			<p>Delivering happiness with delicious food.</p>

			<p>© 2026 FoodExpress. All Rights Reserved.</p>

		</div>
	</footer>

</body>

</html>

<%
} else {
%>


<html lang="en">

<head>


<meta charset="UTF-8">

<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>FoodExpress - Empty Cart</title>

<link rel="stylesheet" href="checkout.css">


</head>

<body>

	<nav class="navbar">


		<div class="logo">
			<a href="callRestaurantServlet"> FoodExpress </a>
		</div>

		<div class="nav-links">

			<a href="callRestaurantServlet"> Home </a> <a
				href="callRestaurantServlet"> Restaurants </a> <a href="cart.jsp">
				Cart </a> <a href="profile.jsp"> Profile </a> <a href="LogoutServlet"
				class="logout-btn"> Logout </a>

		</div>


	</nav>

	<div class="empty-checkout">


		<h2>Your cart is empty</h2>

		<p>Please add some items before checkout.</p>

		<a href="callRestaurantServlet" class="browse-btn"> Browse
			Restaurants </a>


	</div>

</body>

</html>

<%
}
%>
