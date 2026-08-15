<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
Integer userId = (Integer) session.getAttribute("userId");

if (userId == null) {
    response.sendRedirect("login.html");
    return;
}

String error = request.getParameter("error");
String success = request.getParameter("success");
%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>Change Password - FoodExpress</title>

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">

<link rel="stylesheet" href="change-password.css">
</head>

<body>

	<div class="password-page">

		<div class="password-card">

			<div class="password-icon">
				<i class="fa-solid fa-lock"></i>
			</div>

			<h1>Change Password</h1>

			<p class="subtitle">Update your FoodExpress account password
				securely</p>

			<% if ("wrongCurrent".equals(error)) { %>

			<div class="message error">
				<i class="fa-solid fa-circle-exclamation"></i> Current password is
				incorrect.
			</div>

			<% } %>

			<% if ("passwordMismatch".equals(error)) { %>

			<div class="message error">
				<i class="fa-solid fa-circle-exclamation"></i> New password and
				confirm password do not match.
			</div>

			<% } %>

			<% if ("failed".equals(error)) { %>

			<div class="message error">
				<i class="fa-solid fa-circle-exclamation"></i> Password update
				failed. Please try again.
			</div>

			<% } %>


			<form action="ChangePasswordServlet" method="post">

				<!-- CURRENT PASSWORD -->

				<div class="input-group">

					<label>Current Password</label>

					<div class="input-box">

						<i class="fa-solid fa-lock"></i> <input type="password"
							name="currentPassword" id="currentPassword"
							placeholder="Enter current password" required> <i
							class="fa-solid fa-eye password-toggle"
							onclick="togglePassword('currentPassword', this)"> </i>

					</div>

				</div>


				<!-- NEW PASSWORD -->

				<div class="input-group">

					<label>New Password</label>

					<div class="input-box">

						<i class="fa-solid fa-key"></i> <input type="password"
							name="newPassword" id="newPassword"
							placeholder="Enter new password" required> <i
							class="fa-solid fa-eye password-toggle"
							onclick="togglePassword('newPassword', this)"> </i>

					</div>

				</div>


				<!-- CONFIRM PASSWORD -->

				<div class="input-group">

					<label>Confirm New Password</label>

					<div class="input-box">

						<i class="fa-solid fa-key"></i> <input type="password"
							name="confirmPassword" id="confirmPassword"
							placeholder="Confirm new password" required> <i
							class="fa-solid fa-eye password-toggle"
							onclick="togglePassword('confirmPassword', this)"> </i>

					</div>

				</div>


				<button type="submit" class="change-btn">

					<i class="fa-solid fa-shield-halved"></i> Change Password

				</button>

			</form>


			<a href="profile.jsp" class="back-profile"> <i
				class="fa-solid fa-arrow-left"></i> Back to Profile

			</a>

		</div>

	</div>


	<!-- SUCCESS POPUP -->

	<% if ("passwordUpdated".equals(success)) { %>

	<div class="success-overlay" id="successPopup">

		<div class="success-popup">

			<div class="success-icon">

				<i class="fa-solid fa-check"></i>

			</div>

			<h2>Password Updated!</h2>

			<p>Your password has been changed successfully.</p>

			<button onclick="goToProfile()">OK</button>

		</div>

	</div>

	<% } %>


	<script>

function togglePassword(id, icon) {

    const input = document.getElementById(id);

    if (input.type === "password") {

        input.type = "text";

        icon.classList.remove("fa-eye");
        icon.classList.add("fa-eye-slash");

    } else {

        input.type = "password";

        icon.classList.remove("fa-eye-slash");
        icon.classList.add("fa-eye");
    }
}


function goToProfile() {

    window.location.href = "profile.jsp";

}

</script>

</body>
</html>