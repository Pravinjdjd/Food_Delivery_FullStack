<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
Integer userId = (Integer) session.getAttribute("userId");
String userName = (String) session.getAttribute("userName");
String email = (String) session.getAttribute("email");
String address = (String) session.getAttribute("address");
String profileImage = (String) session.getAttribute("profileImage");

if (userId == null) {
    response.sendRedirect("login.html");
    return;
}
%>

<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="UTF-8">

<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>FoodExpress - Edit Profile</title>

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">

<link rel="stylesheet" href="edit-profile.css">

</head>


<body>


	<!-- =====================================================
     NAVBAR
===================================================== -->

	<nav class="navbar">

		<div class="logo">

			<a href="callRestaurantServlet"> <span class="logo-icon">🍔</span>
				<span>FoodExpress</span>
			</a>

		</div>


		<div class="nav-links">

			<a href="callRestaurantServlet"> Home </a> <a
				href="callRestaurantServlet"> Restaurants </a> <a href="cart.jsp">
				Cart </a> <a href="profile.jsp" class="active"> <i
				class="fa-solid fa-user"></i> Profile
			</a> <a href="LogoutServlet" class="logout"> <i
				class="fa-solid fa-right-from-bracket"></i> Logout
			</a>

		</div>

	</nav>



	<!-- =====================================================
     MAIN
===================================================== -->

	<main class="page">


		<div class="edit-card">


			<!-- =================================================
             LEFT PROFILE PANEL
        ================================================== -->

			<aside class="profile-panel">

				<div class="back-link">

					<a href="profile.jsp"> <i class="fa-solid fa-arrow-left"></i>

						Back to Profile

					</a>

				</div>


				<div class="profile-preview">

					<div class="avatar-container">

						<%
                    if (profileImage != null &&
                        !profileImage.trim().isEmpty()) {
                    %>

						<img id="profilePreview" src="uploads/<%= profileImage %>"
							alt="Profile Image">

						<%
                    } else {
                    %>

						<div class="default-avatar" id="defaultAvatar">

							<i class="fa-solid fa-user"></i>

						</div>

						<img id="profilePreview" class="hidden" src="" alt="Profile Image">

						<%
                    }
                    %>


						<label for="profileImage" class="camera-button"> <i
							class="fa-solid fa-camera"></i>

						</label>

					</div>


					<h2>
						<%= userName %>
					</h2>

					<p>
						<%= email %>
					</p>


					<div class="profile-badge">

						<i class="fa-solid fa-circle-check"></i> Active Account

					</div>

				</div>


				<div class="side-info">

					<div class="info-row">

						<i class="fa-solid fa-shield-halved"></i>

						<div>

							<strong>Account Security</strong> <span>Your information
								is protected</span>

						</div>

					</div>


					<div class="info-row">

						<i class="fa-solid fa-user-pen"></i>

						<div>

							<strong>Profile Settings</strong> <span>Keep your details
								updated</span>

						</div>

					</div>

				</div>

			</aside>



			<!-- =================================================
             RIGHT FORM
        ================================================== -->

			<section class="form-panel">


				<div class="form-heading">

					<span class="heading-icon"> <i class="fa-solid fa-user-pen"></i>

					</span>

					<div>

						<h1>Edit Profile</h1>

						<p>Update your personal information</p>

					</div>

				</div>



				<!-- SUCCESS MESSAGE -->

				<%
            String success = request.getParameter("success");

            if ("profileUpdated".equals(success)) {
            %>

				<div class="message success">

					<i class="fa-solid fa-circle-check"></i> Profile updated
					successfully.

				</div>

				<%
            }
            %>



				<!-- ERROR MESSAGE -->

				<%
            String error = request.getParameter("error");

            if ("updateFailed".equals(error)) {
            %>

				<div class="message error">

					<i class="fa-solid fa-circle-exclamation"></i> Unable to update
					your profile.

				</div>

				<%
            }
            %>



				<!-- FORM -->

				<form action="UpdateProfileServlet" method="post"
					enctype="multipart/form-data">


					<!-- PROFILE IMAGE -->

					<div class="photo-upload">

						<div>

							<h3>Profile Photo</h3>

							<p>JPG, JPEG or PNG. Maximum recommended size 2MB.</p>

						</div>


						<label for="profileImage" class="choose-photo"> <i
							class="fa-solid fa-camera"></i> Change Photo

						</label> <input type="file" id="profileImage" name="profileImage"
							accept="image/png,image/jpeg,image/jpg"
							onchange="previewImage(event)">

					</div>



					<!-- DIVIDER -->

					<div class="divider"></div>



					<!-- FORM GRID -->

					<div class="form-grid">


						<!-- NAME -->

						<div class="input-group">

							<label for="userName"> <i class="fa-solid fa-user"></i>

								Full Name

							</label>


							<div class="input-box">

								<i class="fa-regular fa-user"></i> <input type="text"
									id="userName" name="userName"
									value="<%= userName != null ? userName : "" %>"
									placeholder="Enter your name" required>

							</div>

						</div>



						<!-- EMAIL -->

						<div class="input-group">

							<label for="email"> <i class="fa-solid fa-envelope"></i>

								Email Address

							</label>


							<div class="input-box disabled">

								<i class="fa-regular fa-envelope"></i> <input type="email"
									id="email" name="email"
									value="<%= email != null ? email : "" %>" readonly> <i
									class="fa-solid fa-lock lock-icon"></i>

							</div>


							<small> Email cannot be changed here. </small>

						</div>



						<!-- ADDRESS -->

						<div class="input-group full">

							<label for="address"> <i class="fa-solid fa-location-dot"></i>

								Delivery Address

							</label>


							<div class="input-box textarea-box">

								<i class="fa-solid fa-location-dot"></i>

								<textarea id="address" name="address"
									placeholder="Enter your delivery address" required><%= address != null ? address : "" %></textarea>

							</div>

						</div>


					</div>



					<!-- ACTIONS -->

					<div class="form-actions">


						<a href="profile.jsp" class="cancel-btn"> <i
							class="fa-solid fa-xmark"></i> Cancel

						</a>


						<button type="submit" class="save-btn">

							<i class="fa-solid fa-check"></i> Save Changes

						</button>


					</div>


				</form>

			</section>


		</div>

	</main>



	<!-- =====================================================
     FOOTER
===================================================== -->

	<footer>

		<div class="footer-content">

			<div class="footer-logo">🍔 FoodExpress</div>

			<p>Delivering happiness with delicious food.</p>

			<span> © 2026 FoodExpress. All Rights Reserved. </span>

		</div>

	</footer>



	<!-- =====================================================
     IMAGE PREVIEW SCRIPT
===================================================== -->

	<script>

function previewImage(event) {

    const file = event.target.files[0];

    if (!file) {
        return;
    }


    const image = document.getElementById("profilePreview");

    const defaultAvatar =
        document.getElementById("defaultAvatar");


    image.src = URL.createObjectURL(file);

    image.classList.remove("hidden");


    if (defaultAvatar) {

        defaultAvatar.style.display = "none";

    }

}

</script>


</body>

</html>