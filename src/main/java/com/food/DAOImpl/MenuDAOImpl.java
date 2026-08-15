package com.food.DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.food.DAO.MenuDAO;
import com.food.Model.Menu;
import com.food.utility.DBConnection;

public class MenuDAOImpl implements MenuDAO {

	private static final String INSERT_QUERY =
			"INSERT INTO menu(restaurantID,itemName,Description,price,isAvailable,Category,CreatedAt,UpdatedAt,rating,image) VALUES(?,?,?,?,?,?,?,?,?,?)";

	private static final String SELECT_QUERY =
			"SELECT * FROM menu WHERE menuId=?";

	private static final String UPDATE_QUERY =
			"UPDATE menu SET itemName=?,Description=?,price=?,isAvailable=?,Category=?,UpdatedAt=?,rating=?,image=? WHERE menuId=?";

	private static final String DELETE_QUERY =
			"DELETE FROM menu WHERE menuId=?";

	private static final String SELECT_ALL_QUERY =
			"SELECT * FROM menu";

	private static final String SELECT_MENU_BY_RESTAURANT =
			"SELECT * FROM menu WHERE restaurantID=?";
	
	private static final String SEARCH_MENU_QUERY =
	        "SELECT * FROM menu WHERE restaurantID=? AND itemName LIKE ?";

	@Override
	public void addMenu(Menu menu) {

		try {

			Connection con = DBConnection.getConnection();

			PreparedStatement pstmt = con.prepareStatement(INSERT_QUERY);

			pstmt.setInt(1, menu.getRestaurantID());
			pstmt.setString(2, menu.getItemName());
			pstmt.setString(3, menu.getDescription());
			pstmt.setDouble(4, menu.getPrice());
			pstmt.setInt(5, menu.getIsAvailable());
			pstmt.setString(6, menu.getCategory());
			pstmt.setTimestamp(7, new Timestamp(System.currentTimeMillis()));
			pstmt.setTimestamp(8, new Timestamp(System.currentTimeMillis()));
			pstmt.setDouble(9, menu.getRating());
			pstmt.setString(10, menu.getImage());

			int i = pstmt.executeUpdate();

			System.out.println(i + " Data Added");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Menu getMenu(int menuId) {

		Menu menu = null;

		Connection con = DBConnection.getConnection();

		try {

			PreparedStatement pstmt = con.prepareStatement(SELECT_QUERY);

			pstmt.setInt(1, menuId);

			ResultSet res = pstmt.executeQuery();

			while (res.next()) {

				menu = new Menu(
						res.getInt("menuId"),
						res.getInt("restaurantID"),
						res.getString("itemName"),
						res.getString("Description"),
						res.getDouble("price"),
						res.getInt("isAvailable"),
						res.getString("Category"),
						res.getTimestamp("CreatedAt"),
						res.getTimestamp("UpdatedAt"),
						res.getTimestamp("DeletedAt"),
						res.getDouble("rating"),
						res.getString("image"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return menu;
	}

	@Override
	public void updateMenu(Menu menu) {

		Connection con = DBConnection.getConnection();

		try {

			PreparedStatement pstmt = con.prepareStatement(UPDATE_QUERY);

			pstmt.setString(1, menu.getItemName());
			pstmt.setString(2, menu.getDescription());
			pstmt.setDouble(3, menu.getPrice());
			pstmt.setInt(4, menu.getIsAvailable());
			pstmt.setString(5, menu.getCategory());
			pstmt.setTimestamp(6, new Timestamp(System.currentTimeMillis()));
			pstmt.setDouble(7, menu.getRating());
			pstmt.setString(8, menu.getImage());
			pstmt.setInt(9, menu.getMenuId());

			int n = pstmt.executeUpdate();

			System.out.println(n + " Data Updated");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteMenu(int menuId) {

		Connection con = DBConnection.getConnection();

		try {

			PreparedStatement pstmt = con.prepareStatement(DELETE_QUERY);

			pstmt.setInt(1, menuId);

			int n = pstmt.executeUpdate();

			System.out.println(n + " Data Deleted");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Menu> getAllMenu() {

		List<Menu> list = new ArrayList<>();

		Connection con = DBConnection.getConnection();

		try {

			Statement stmt = con.createStatement();

			ResultSet res = stmt.executeQuery(SELECT_ALL_QUERY);

			while (res.next()) {

				Menu menu = new Menu(
						res.getInt("menuId"),
						res.getInt("restaurantID"),
						res.getString("itemName"),
						res.getString("Description"),
						res.getDouble("price"),
						res.getInt("isAvailable"),
						res.getString("Category"),
						res.getTimestamp("CreatedAt"),
						res.getTimestamp("UpdatedAt"),
						res.getTimestamp("DeletedAt"),
						res.getDouble("rating"),
						res.getString("image"));

				list.add(menu);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	@Override
	public List<Menu> getAllMenuByRestaurant(int restaurantId) {

		List<Menu> list = new ArrayList<>();

		Connection con = DBConnection.getConnection();

		try {

			PreparedStatement pstmt = con.prepareStatement(SELECT_MENU_BY_RESTAURANT);

			pstmt.setInt(1, restaurantId);

			ResultSet res = pstmt.executeQuery();

			while (res.next()) {

				Menu menu = new Menu(
						res.getInt("menuId"),
						res.getInt("restaurantID"),
						res.getString("itemName"),
						res.getString("Description"),
						res.getDouble("price"),
						res.getInt("isAvailable"),
						res.getString("Category"),
						res.getTimestamp("CreatedAt"),
						res.getTimestamp("UpdatedAt"),
						res.getTimestamp("DeletedAt"),
						res.getDouble("rating"),
						res.getString("image"));

				list.add(menu);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}
	
	public List<Menu> searchMenuByRestaurant(int restaurantId, String search) {

	    List<Menu> list = new ArrayList<>();

	    Connection con = DBConnection.getConnection();

	    try {

	        PreparedStatement pstmt =
	                con.prepareStatement(SEARCH_MENU_QUERY);

	        pstmt.setInt(1, restaurantId);
	        pstmt.setString(2, "%" + search + "%");

	        ResultSet res = pstmt.executeQuery();

	        while (res.next()) {

	            Menu menu = new Menu(
	                    res.getInt("menuId"),
	                    res.getInt("restaurantID"),
	                    res.getString("itemName"),
	                    res.getString("Description"),
	                    res.getDouble("price"),
	                    res.getInt("isAvailable"),
	                    res.getString("Category"),
	                    res.getTimestamp("CreatedAt"),
	                    res.getTimestamp("UpdatedAt"),
	                    res.getTimestamp("DeletedAt"),
	                    res.getDouble("rating"),
	                    res.getString("image")
	            );

	            list.add(menu);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return list;
	}

	
}