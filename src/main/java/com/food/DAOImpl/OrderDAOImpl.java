package com.food.DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.food.DAO.OrderDAO;
import com.food.Model.Order;
import com.food.utility.DBConnection;

public class OrderDAOImpl implements OrderDAO {

	private static final String INSERT_QUERY = "INSERT INTO ordertable(userId,restaurantId,orderDate,totalAmount,status,paymentMethod)"
			+ " VALUES(?,?,?,?,?,?)";

	private static final String SELECT_QUERY = "SELECT * FROM ordertable WHERE orderId=?";

	private static final String UPDATE_QUERY = "UPDATE ordertable SET status=?, paymentMethod=? WHERE orderId=?";

	private static final String DELETE_QUERY = "DELETE FROM ordertable WHERE orderId=?";

	private static final String SELECT_ALL_QUERY = "SELECT * FROM ordertable";

	@Override
	public int addOrder(Order order) {
		int orderId=0;

		try {

			Connection con = DBConnection.getConnection();

			PreparedStatement pstmt = con.prepareStatement(INSERT_QUERY,Statement.RETURN_GENERATED_KEYS/*auto gentate*/);

			pstmt.setInt(1, order.getUserId());

			pstmt.setInt(2, order.getRestaurantId());

			pstmt.setTimestamp(3, new Timestamp(System.currentTimeMillis()));

			pstmt.setDouble(4, order.getTotalAmount());

			pstmt.setString(5, order.getStatus());

			pstmt.setString(6, order.getPaymentMethod());

			int i = pstmt.executeUpdate();
			
			ResultSet rs = pstmt.getGeneratedKeys();//auto genrate value
			
			if(rs.next()) {
				orderId = rs.getInt(1);
			}
			

		} catch (Exception e) {

			e.printStackTrace();

		}
		return orderId;

	}

	@Override
	public Order getOrder(int orderId) {

		Order order = null;

		Connection con = DBConnection.getConnection();

		try {

			PreparedStatement pstmt = con.prepareStatement(SELECT_QUERY);

			pstmt.setInt(1, orderId);

			ResultSet res = pstmt.executeQuery();

			while (res.next()) {

				order = new Order(

						res.getInt("orderId"),

						res.getInt("userId"),

						res.getInt("restaurantId"),

						res.getTimestamp("orderDate"),

						res.getDouble("totalAmount"),

						res.getString("status"),

						res.getString("paymentMethod")

				);

			}

		} catch (SQLException e) {

			e.printStackTrace();

		}

		return order;

	}

	@Override
	public void updateOrder(Order order) {

		Connection con = DBConnection.getConnection();

		try {

			PreparedStatement pstmt = con.prepareStatement(UPDATE_QUERY);

			pstmt.setString(1, order.getStatus());

			pstmt.setString(2, order.getPaymentMethod());

			pstmt.setInt(3, order.getOrderId());

			int n = pstmt.executeUpdate();

			System.out.println(n + " Data Updated");

		} catch (SQLException e) {

			e.printStackTrace();

		}

	}

	@Override

	public void deleteOrder(int orderId) {

		Connection con = DBConnection.getConnection();

		try {

			// Delete order items first

			PreparedStatement pstmt1 = con.prepareStatement("DELETE FROM orderitem WHERE orderId=?");

			pstmt1.setInt(1, orderId);

			pstmt1.executeUpdate();

			// Delete order

			PreparedStatement pstmt2 = con.prepareStatement("DELETE FROM ordertable WHERE orderId=?");

			pstmt2.setInt(1, orderId);

			int n = pstmt2.executeUpdate();

			System.out.println(n + " Order Deleted");

		} catch (SQLException e) {

			e.printStackTrace();

		}

	}

	@Override
	public List<Order> getAllOrder() {

		List<Order> list = new ArrayList<Order>();

		Connection con = DBConnection.getConnection();

		try {

			Statement stmt = con.createStatement();

			ResultSet res = stmt.executeQuery(SELECT_ALL_QUERY);

			while (res.next()) {

				Order order = new Order(

						res.getInt("orderId"),

						res.getInt("userId"),

						res.getInt("restaurantId"),

						res.getTimestamp("orderDate"),

						res.getDouble("totalAmount"),

						res.getString("status"),

						res.getString("paymentMethod")

				);

				list.add(order);

			}

		} catch (SQLException e) {

			e.printStackTrace();

		}

		return list;

	}

}