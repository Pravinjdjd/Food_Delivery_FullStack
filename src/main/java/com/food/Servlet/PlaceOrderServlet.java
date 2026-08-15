package com.food.Servlet;

import java.io.IOException;
import java.sql.Timestamp;

import com.food.DAOImpl.OrderDAOImpl;
import com.food.DAOImpl.OrderItemDAOImpl;
import com.food.Model.Cart;
import com.food.Model.CartItem;
import com.food.Model.Order;
import com.food.Model.OrderItem;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/callPlaceOrderServlet")
public class PlaceOrderServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

@Override
protected void doPost(HttpServletRequest req,
                       HttpServletResponse resp)
        throws ServletException, IOException {

    // =====================================
    // GET SESSION
    // =====================================

    HttpSession session = req.getSession(false);

    // =====================================
    // CHECK LOGIN
    // =====================================

    if (session == null ||
        session.getAttribute("userId") == null) {

        resp.sendRedirect("login.html");
        return;
    }

    // Get logged-in user ID from session
    Integer userId =
            (Integer) session.getAttribute("userId");


    // =====================================
    // GET CART
    // =====================================

    Cart cart =
            (Cart) session.getAttribute("cart");


    // =====================================
    // CHECK CART
    // =====================================

    if (cart == null ||
        cart.getItems().isEmpty()) {

        resp.sendRedirect("cart.jsp");
        return;
    }


    // =====================================
    // GET RESTAURANT ID
    // =====================================

    Integer restaurantIdObj =
            (Integer) session.getAttribute("restaurantId");


    if (restaurantIdObj == null) {

        resp.sendError(
                HttpServletResponse.SC_BAD_REQUEST,
                "Restaurant ID is missing"
        );

        return;
    }


    int restaurantId =
            restaurantIdObj;


    // =====================================
    // GET FINAL TOTAL
    // =====================================

    Object finalTotalObj =
            session.getAttribute("finalTotal");


    if (finalTotalObj == null) {

        resp.sendError(
                HttpServletResponse.SC_BAD_REQUEST,
                "Final total is missing"
        );

        return;
    }


    double finalTotal =
            ((Number) finalTotalObj).doubleValue();


    // =====================================
    // PAYMENT METHOD
    // =====================================

    String paymentMode =
            req.getParameter("paymentMode");


    if (paymentMode == null ||
        paymentMode.trim().isEmpty()) {

        paymentMode = "Cash";
    }


    // =====================================
    // CREATE ORDER
    // =====================================

    Order order =
            new Order();


    // IMPORTANT:
    // Use userId from login session

    order.setUserId(userId);


    order.setRestaurantId(
            restaurantId
    );


    order.setOrderDate(
            new Timestamp(
                    System.currentTimeMillis()
            )
    );


    order.setTotalAmount(
            finalTotal
    );


    order.setStatus(
            "Pending"
    );


    order.setPaymentMethod(
            paymentMode
    );


    // =====================================
    // INSERT ORDER
    // =====================================

    OrderDAOImpl orderDAOImpl =
            new OrderDAOImpl();


    int orderId =
            orderDAOImpl.addOrder(order);


    // =====================================
    // CHECK ORDER ID
    // =====================================

    if (orderId <= 0) {

        resp.sendError(
                HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                "Order could not be created"
        );

        return;
    }


    // =====================================
    // INSERT ORDER ITEMS
    // =====================================

    OrderItemDAOImpl orderItemDAOImpl =
            new OrderItemDAOImpl();


    for (CartItem item :
            cart.getItems().values()) {


        OrderItem orderItem =
                new OrderItem();


        // Generated order ID
        orderItem.setOrderId(orderId);


        orderItem.setMenuId(
                item.getMenuId()
        );


        orderItem.setQuantity(
                item.getQuantity()
        );


        orderItem.setItemTotal(
                item.getTotalPrice()
        );


        orderItemDAOImpl.addOrderItem(
                orderItem
        );
    }


    // =====================================
    // SAVE DATA FOR CONFIRMATION PAGE
    // =====================================

    session.setAttribute(
            "orderId",
            orderId
    );


    session.setAttribute(
            "paymentMethod",
            paymentMode
    );


    session.setAttribute(
            "orderTotal",
            finalTotal
    );


    // =====================================
    // REMOVE CART DATA
    // =====================================

    session.removeAttribute("cart");

    session.removeAttribute("restaurantId");

    session.removeAttribute("finalTotal");


    // =====================================
    // ORDER CONFIRMATION
    // =====================================

    resp.sendRedirect(
            "order-confirmation.jsp"
    );
}

}
