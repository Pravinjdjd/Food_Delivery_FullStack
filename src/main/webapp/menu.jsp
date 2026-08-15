<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.util.*, com.food.Model.Menu"%>

<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>Restaurant Menu</title>

<link rel="stylesheet" href="menu.css">

<link rel="stylesheet"
href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css">

</head>

<body>

<!-- ================= NAVBAR ================= -->

<nav>

    <div class="logo">
        🍔 FoodExpress
    </div>


    <!-- ================= SEARCH ================= -->

    <div class="search">

        <form action="callMenuServlet" method="get">

            <!-- Keep Restaurant ID -->
            <input type="hidden"
                   name="restaurantId"
                   value="<%= request.getParameter("restaurantId") != null
                           ? request.getParameter("restaurantId")
                           : request.getAttribute("restaurantId") %>">

            <input type="text"
                   name="search"
                   placeholder="Search food"
                   autocomplete="off">

            <button type="submit">
                <i class="fa fa-search"></i>
            </button>

        </form>

    </div>


    <!-- ================= PROFILE ================= -->


</nav>


<!-- ================= MENU TITLE ================= -->

<section class="title">

    <h2>Recommended</h2>

</section>


<!-- ================= MENU ITEMS ================= -->

<section class="menu-container">

<%

List<Menu> allMenu =
    (List<Menu>) request.getAttribute("allMenu");

if (allMenu != null && !allMenu.isEmpty()) {

    for (Menu menu : allMenu) {

%>


    <div class="menu-card">


        <!-- MENU IMAGE -->

        <img src="<%= menu.getImage() %>"
             alt="<%= menu.getItemName() %>">


        <div class="menu-details">


            <!-- ITEM NAME -->

            <h3>
                <%= menu.getItemName() %>
            </h3>


            <!-- DESCRIPTION -->

            <p>
                <%= menu.getDescription() %>
            </p>


            <!-- PRICE + RATING -->

            <div class="bottom">

                <span class="price">
                    ₹<%= menu.getPrice() %>
                </span>

                <span class="rating">
                    ⭐<%= menu.getRating() %>
                </span>

            </div>


            <!-- ADD TO CART -->

            <form action="callCartServlet">

                <input type="hidden"
                       name="menuId"
                       value="<%= menu.getMenuId() %>">

                <input type="hidden"
                       name="restaurantId"
                       value="<%= menu.getRestaurantID() %>">

                <input type="hidden"
                       name="quantity"
                       value="1">

                <input type="hidden"
                       name="action"
                       value="add">

                <button class="add-btn">
                    Add +
                </button>

            </form>


        </div>

    </div>


<%

    }

} else {

%>


    <!-- ================= NO SEARCH RESULT ================= -->

    <div class="no-results">

        <i class="fa-solid fa-face-sad-tear"></i>

        <h2>No food found</h2>

        <p>
            Try searching with another food name.
        </p>

    </div>


<%

}

%>

</section>


</body>
</html>