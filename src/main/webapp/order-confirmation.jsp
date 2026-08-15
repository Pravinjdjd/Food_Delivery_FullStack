<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">

<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>Order Confirmed - FoodExpress</title>

<link rel="stylesheet" href="order-confirmation.css">

</head>

<body>


	<!-- =====================================================
     NAVBAR
===================================================== -->

	<nav class="navbar">

		<div class="logo">🍔 FoodExpress</div>

		<div class="nav-links">

			<a href="callRestaurantServlet"> Restaurants </a> <a
				href="profile.jsp"> Profile </a> <a href="cart.jsp"> Cart </a> <a
				href="LogoutServlet"> Logout </a>

		</div>

	</nav>



	<!-- =====================================================
     MAIN
===================================================== -->

	<div class="confirmation-container">


		<!-- =================================================
         SUCCESS CARD
    ================================================== -->

		<div class="success-card">


			<!-- Animated Tick -->

			<div class="success-animation">

				<div class="success-circle">

					<svg class="check-svg" viewBox="0 0 52 52">

                    <circle class="check-circle" cx="26" cy="26" r="24"
							fill="none" />

                    <path class="check-path" fill="none"
							d="M14 27 L22 35 L39 17" />

                </svg>

				</div>

			</div>


			<h1>Order Confirmed!</h1>


			<p class="success-message">Thank you for ordering with
				FoodExpress.</p>


			<p class="delivery-message">Your delicious food is being prepared
				and will be delivered soon.</p>


			<!-- Order ID -->

			<div class="order-number">

				Order ID: <strong> #<%=session.getAttribute("orderId") != null ? session.getAttribute("orderId") : "N/A"%>
				</strong>

			</div>


		</div>



		<!-- =================================================
         ORDER DETAILS
    ================================================== -->

		<div class="order-details">


			<!-- ORDER INFORMATION -->

			<div class="details-card">

				<h2>Order Details</h2>


				<div class="detail-row">

					<span> Order ID </span> <strong> #<%=session.getAttribute("orderId") != null ? session.getAttribute("orderId") : "N/A"%>
					</strong>

				</div>


				<div class="detail-row">

					<span> Order Status </span> <span class="status"> Confirmed
					</span>

				</div>


				<div class="detail-row">

					<span> Payment Method </span> <strong> <%=session.getAttribute("paymentMethod") != null ? session.getAttribute("paymentMethod") : "Cash on Delivery"%>

					</strong>

				</div>


			</div>



			<!-- TOTAL -->

			<div class="details-card total-card">

				<h2>Payment Summary</h2>


				<div class="detail-row">

					<span> Total Amount </span> <strong class="amount"> ₹<%=session.getAttribute("orderTotal") != null ? session.getAttribute("orderTotal") : "0"%>

					</strong>

				</div>


				<div class="paid-message">✓ Payment information confirmed</div>

			</div>


		</div>



		<!-- =================================================
         ORDER ITEMS
    ================================================== -->

		<div class="items-card">

			<h2>Ordered Items</h2>


			<%
			Object orderItemsObj = session.getAttribute("confirmedOrderItems");

			if (orderItemsObj != null) {
			%>


			<div class="items-message">Your ordered items are successfully
				saved.</div>


			<%
			} else {
			%>


			<div class="items-message">Your order has been successfully
				placed.</div>


			<%
			}
			%>


			<div class="total-row">

				<span> Total Amount </span> <strong> ₹<%=session.getAttribute("orderTotal") != null ? session.getAttribute("orderTotal") : "0"%>

				</strong>

			</div>


		</div>



		<!-- =================================================
         DELIVERY ANIMATION
    ================================================== -->

		<div class="delivery-section">


			<div class="delivery-title">

				<span class="live-dot"></span>

				<h2>Your food is on the way!</h2>

			</div>


			<p class="delivery-subtitle">Our delivery partner is bringing
				your food.</p>



			<!-- ROAD -->

			<div class="delivery-road">


				<!-- Restaurant -->

				<div class="location restaurant-location">

					<div class="location-icon">🏪</div>

					<span> Restaurant </span>

				</div>



				<!-- DELIVERY VEHICLE -->

				<div class="delivery-bike">

					<div class="delivery-box">🍔</div>


					<div class="bike-body">🛵</div>


					<div class="bike-wheel wheel-one"></div>

					<div class="bike-wheel wheel-two"></div>

				</div>



				<!-- Home -->

				<div class="location home-location">

					<div class="location-icon">🏠</div>

					<span> Your Home </span>

				</div>


			</div>


			<div class="delivery-status">

				<span class="status-dot"></span> Preparing your order...

			</div>


			<div class="estimated-time">

				🕐 Estimated delivery: <strong> 30 - 45 minutes </strong>

			</div>


		</div>



		<!-- =================================================
         BUTTONS
    ================================================== -->

		<div class="action-buttons">


			<a href="callRestaurantServlet" class="continue-btn"> 🍔 Back to
				Restaurants </a> <!-- <a href="orderhistory.jsp" class="orders-btn"> 📦
				View My Orders </a> -->


		</div>


	</div>



	<!-- =====================================================
     SUCCESS SOUND
===================================================== -->

	<audio id="successSound" preload="auto">

		<!--
        Put downloaded Pixabay MP3 here:

        webapp/sounds/order-success.mp3

        Example:

        FoodExpress
        └── WebContent
            └── sounds
                └── order-success.mp3
    -->

		<source src="sound/universfield-new-notification-034-485901.mp3" type="audio/mpeg">

	</audio>



	<!-- =====================================================
     ANIMATION SCRIPT
===================================================== -->
<script>

document.addEventListener("DOMContentLoaded", function () {

    const sound = document.getElementById("successSound");
    const circle = document.querySelector(".success-circle");

    if (!sound || !circle) {
        return;
    }

    // Prepare sound
    sound.volume = 1.0;
    sound.playbackRate = 1.0;
    sound.currentTime = 0;

    // Start BOTH at the same time
    circle.classList.add("show");

    sound.play().catch(function () {
        console.log("Autoplay blocked");

        // Browser blocks autoplay
        document.addEventListener("click", function () {

            sound.currentTime = 0;
            sound.play();

        }, { once: true });
    });

});

</script>


</body>

</html>