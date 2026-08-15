<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<%@ page import="com.food.Model.Cart"%>
<%@ page import="com.food.Model.CartItem"%>

<!DOCTYPE html>

<html lang="en">

<head>


<meta charset="UTF-8">

<meta name="viewport"
      content="width=device-width, initial-scale=1.0">

<title>FoodExpress - Cart</title>

<!-- Cart CSS -->
<link rel="stylesheet" href="cart.css">


</head>

<body>

<!-- ================= NAVBAR ================= -->

<div class="navbar">

<div class="logo">

    <a href="callRestaurantServlet">
       🍔 FoodExpress
    </a>

</div>


<div class="nav-links">

    <a href="callRestaurantServlet">
        Home
    </a>

    <a href="callRestaurantServlet">
        Restaurants
    </a>

    <a href="cart.jsp" class="active">
        Cart
    </a>


    <%
    Integer userId =
        (Integer) session.getAttribute("userId");

    if (userId == null) {
    %>

        <a href="login.html">
            Login
        </a>

        <a href="register.html">
            Sign Up
        </a>

    <%
    } else {
    %>

        <a href="profile.jsp">
            Profile
        </a>

        <a href="LogoutServlet">
            Logout
        </a>

    <%
    }
    %>

</div>


</div>

<!-- ================= MAIN ================= -->

<h1 class="page-title">
    My Cart
</h1>

<%

Cart cart =
(Cart) session.getAttribute("cart");

Integer restaurantId =
(Integer) session.getAttribute("restaurantId");

double grandTotal = 0;

if (cart != null &&
!cart.getItems().isEmpty()) {

%>

<!-- ================= CART BOX ================= -->

<div class="cart-box">


<div class="cart-header">

    <div>Item</div>
    <div>Price</div>
    <div>Quantity</div>
    <div>Total</div>
    <div>Action</div>

</div>


<%

for (CartItem item :
    cart.getItems().values()) {

    grandTotal =
        grandTotal + item.getTotalPrice();

    int currentQty =
        item.getQuantity();

    int decreaseQty =
        currentQty - 1;

    int increaseQty =
        currentQty + 1;

%>


<div class="cart-item">


    <div class="item-name">

        <%= item.getName() %>

    </div>


    <div class="item-price">

        ₹<%= item.getPrice() %>

    </div>


    <div class="quantity-box">


        <form action="callCartServlet"
              method="post">

            <input type="hidden"
                   name="menuId"
                   value="<%= item.getMenuId() %>">

            <input type="hidden"
                   name="restaurantId"
                   value="<%= restaurantId %>">


            <%
            if (decreaseQty <= 0) {
            %>

                <input type="hidden"
                       name="action"
                       value="delete">

            <%
            } else {
            %>

                <input type="hidden"
                       name="action"
                       value="update">

                <input type="hidden"
                       name="quantity"
                       value="<%= decreaseQty %>">

            <%
            }
            %>


            <button type="submit"
                    class="qty-btn">

                -

            </button>

        </form>


        <span class="quantity">

            <%= currentQty %>

        </span>


        <form action="callCartServlet"
              method="post">

            <input type="hidden"
                   name="menuId"
                   value="<%= item.getMenuId() %>">

            <input type="hidden"
                   name="restaurantId"
                   value="<%= restaurantId %>">

            <input type="hidden"
                   name="action"
                   value="update">

            <input type="hidden"
                   name="quantity"
                   value="<%= increaseQty %>">


            <button type="submit"
                    class="qty-btn">

                +

            </button>

        </form>

    </div>


    <div class="item-total">

        ₹<%= item.getTotalPrice() %>

    </div>


    <div class="action-box">

        <form action="callCartServlet"
              method="post">

            <input type="hidden"
                   name="menuId"
                   value="<%= item.getMenuId() %>">

            <input type="hidden"
                   name="restaurantId"
                   value="<%= restaurantId %>">

            <input type="hidden"
                   name="action"
                   value="delete">


            <button type="submit"
                    class="delete-btn">

                Remove

            </button>

        </form>

    </div>


</div>


<%

}

%>


<!-- ================= CART FOOTER ================= -->

<div class="cart-footer">


    <div class="grand-total">

        <span>
            Grand Total
        </span>

        <span>
            ₹<%= grandTotal %>
        </span>

    </div>


    <div class="cart-buttons">


        <a href="callMenuServlet?restaurantId=<%= restaurantId %>"
           class="continue-btn">

            Add More Item

        </a>


        <a href="checkout.jsp"
           class="checkout-btn">

            Proceed to Checkout

        </a>


    </div>


</div>


</div>

<%

} else {

%>

<!-- ================= EMPTY CART ================= -->

<div class="empty-cart">


<div class="empty-icon">
    🛒
</div>


<h2>
    Your cart is empty
</h2>


<p>
    You haven't added any food items yet.
</p>


<a href="callRestaurantServlet"
   class="browse-btn">

    Browse Restaurants

</a>


</div>

<%

}

%>

</body>

</html>
