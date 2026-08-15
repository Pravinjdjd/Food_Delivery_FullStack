package com.food.DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.food.DAO.OrderItemDAO;
import com.food.Model.OrderItem;
import com.food.utility.DBConnection;

public class OrderItemDAOImpl implements OrderItemDAO {

	private static final String INSERT_QUERY = "INSERT INTO orderitem(orderId,menuId,quantity,itemTotal)"
			+ " VALUES(?,?,?,?)";

	private static final String SELECT_QUERY = "SELECT * FROM orderitem WHERE orderItemId=?";

	private static final String UPDATE_QUERY = "UPDATE orderitem SET quantity=?,itemTotal=? WHERE orderItemId=?";

	private static final String DELETE_QUERY = "DELETE FROM orderitem WHERE orderItemId=?";

	private static final String SELECT_ALL_QUERY = "SELECT * FROM orderitem";

	@Override
	public void addOrderItem(OrderItem orderItem) {

		try {

			Connection con = DBConnection.getConnection();

			PreparedStatement pstmt = con.prepareStatement(INSERT_QUERY);

			pstmt.setInt(1, orderItem.getOrderId());

			pstmt.setInt(2, orderItem.getMenuId());

			pstmt.setInt(3, orderItem.getQuantity());

			pstmt.setDouble(4, orderItem.getItemTotal());

			int i = pstmt.executeUpdate();

			System.out.println(i + " Data Added");

		} catch (Exception e) {

			e.printStackTrace();

		}

	}

	@Override
	public OrderItem getOrderItem(int orderItemId) {

		OrderItem item = null;

		try {

			Connection con = DBConnection.getConnection();

			PreparedStatement pstmt = con.prepareStatement(SELECT_QUERY);

			pstmt.setInt(1, orderItemId);

			ResultSet res = pstmt.executeQuery();

			while (res.next()) {

				item = new OrderItem(

						res.getInt("orderItemId"),

						res.getInt("orderId"),

						res.getInt("menuId"),

						res.getInt("quantity"),

						res.getDouble("itemTotal")

				);

			}

		} catch (SQLException e) {

			e.printStackTrace();

		}

		return item;

	}

	@Override
	public void updateOrderItem(OrderItem orderItem) {

		try {

			Connection con = DBConnection.getConnection();

			PreparedStatement pstmt = con.prepareStatement(UPDATE_QUERY);

			pstmt.setInt(1, orderItem.getQuantity());

			pstmt.setDouble(2, orderItem.getItemTotal());

			pstmt.setInt(3, orderItem.getOrderItemId());

			int n = pstmt.executeUpdate();

			System.out.println(n + " Data Updated");

		} catch (SQLException e) {

			e.printStackTrace();

		}

	}

	@Override
	public void deleteOrderItem(int orderItemId) {

		try {

			Connection con = DBConnection.getConnection();

			PreparedStatement pstmt = con.prepareStatement(DELETE_QUERY);

			pstmt.setInt(1, orderItemId);

			int n = pstmt.executeUpdate();

			System.out.println(n + " Data Deleted");

		} catch (SQLException e) {

			e.printStackTrace();

		}

	}

	@Override
	public List<OrderItem> getAllOrderItem() {

		List<OrderItem> list = new ArrayList<OrderItem>();

		try {

			Connection con = DBConnection.getConnection();

			Statement stmt = con.createStatement();

			ResultSet res = stmt.executeQuery(SELECT_ALL_QUERY);

			while (res.next()) {

				OrderItem item = new OrderItem(

						res.getInt("orderItemId"),

						res.getInt("orderId"),

						res.getInt("menuId"),

						res.getInt("quantity"),

						res.getDouble("itemTotal")

				);

				list.add(item);

			}

		} catch (SQLException e) {

			e.printStackTrace();

		}

		return list;

	}

}