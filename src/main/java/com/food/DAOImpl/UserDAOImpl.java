package com.food.DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.food.DAO.UserDAO;
import com.food.Model.User;
import com.food.utility.DBConnection;

public class UserDAOImpl implements UserDAO {

	// ================= SQL QUERIES =================

	private static final String INSERT_QUERY = "INSERT INTO user(userName,password,email,address,role,"
			+ "createdDate,lastLoginDate) VALUES(?,?,?,?,?,?,?)";

	private static final String SELECT_QUERY = "SELECT * FROM user WHERE userId=?";

	private static final String UPDATE_QUERY = "UPDATE user SET userName=?, email=?, address=?, profileImage=?, "
			+ "lastLoginDate=? WHERE userId=?";

	private static final String DELETE_QUERY = "DELETE FROM user WHERE userId=?";

	private static final String SELECT_ALL_QUERY = "SELECT * FROM user";

	private static final String SELECT_EMAIL_QUERY = "SELECT * FROM user WHERE email=?";

	private static final String UPDATE_PASSWORD_QUERY = "UPDATE user SET password=? WHERE userId=?";

	// ================= ADD USER =================

	@Override
	public int addUser(User user) {

		try {

			Connection con = DBConnection.getConnection();

			PreparedStatement pstmt = con.prepareStatement(INSERT_QUERY);

			pstmt.setString(1, user.getUserName());
			pstmt.setString(2, user.getPassword());
			pstmt.setString(3, user.getEmail());
			pstmt.setString(4, user.getAddress());
			pstmt.setString(5, user.getRole());

			pstmt.setTimestamp(6, new Timestamp(System.currentTimeMillis()));

			pstmt.setTimestamp(7, new Timestamp(System.currentTimeMillis()));

			int result = pstmt.executeUpdate();

			return result;

		} catch (Exception e) {

			e.printStackTrace();
		}

		return 0;
	}

	// ================= GET USER BY ID =================

	@Override
	public User getUser(int userId) {

		User user = null;

		Connection con = DBConnection.getConnection();

		try {

			PreparedStatement pstmt = con.prepareStatement(SELECT_QUERY);

			pstmt.setInt(1, userId);

			ResultSet res = pstmt.executeQuery();

			if (res.next()) {

				int id = res.getInt("userId");

				String userName = res.getString("userName");

				String password = res.getString("password");

				String email = res.getString("email");

				String address = res.getString("address");

				String role = res.getString("role");

				Timestamp createdDate = res.getTimestamp("createdDate");

				Timestamp lastLoginDate = res.getTimestamp("lastLoginDate");

				String profileImage = res.getString("profileImage");

				user = new User(id, userName, password, email, address, role, createdDate, lastLoginDate, profileImage);
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return user;
	}

	// ================= UPDATE USER =================

	@Override
	public void updateUser(User user) {

		Connection con = DBConnection.getConnection();

		try {

			PreparedStatement pstmt = con.prepareStatement(UPDATE_QUERY);

			pstmt.setString(1, user.getUserName());

			pstmt.setString(2, user.getEmail());

			pstmt.setString(3, user.getAddress());

			pstmt.setString(4, user.getProfileImage());

			// System automatically gets current date and time
			pstmt.setTimestamp(5, new Timestamp(System.currentTimeMillis()));

			pstmt.setInt(6, user.getUserId());

			int result = pstmt.executeUpdate();

			System.out.println(result + " Data Updated");

		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	// ================= DELETE USER =================

	@Override
	public void deleteUser(int userId) {

		Connection con = DBConnection.getConnection();

		try {

			PreparedStatement pstmt = con.prepareStatement(DELETE_QUERY);

			pstmt.setInt(1, userId);

			int result = pstmt.executeUpdate();

			System.out.println(result + " Data Deleted");

		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	// ================= GET ALL USERS =================

	@Override
	public List<User> getAllUser() {

		List<User> list = new ArrayList<>();

		Connection con = DBConnection.getConnection();

		try {

			Statement stmt = con.createStatement();

			ResultSet res = stmt.executeQuery(SELECT_ALL_QUERY);

			while (res.next()) {

				int userId = res.getInt("userId");

				String userName = res.getString("userName");

				String password = res.getString("password");

				String email = res.getString("email");

				String address = res.getString("address");

				String role = res.getString("role");

				Timestamp createdDate = res.getTimestamp("createdDate");

				Timestamp lastLoginDate = res.getTimestamp("lastLoginDate");

				String profileImage = res.getString("profileImage");

				User user = new User(userId, userName, password, email, address, role, createdDate, lastLoginDate,
						profileImage);

				list.add(user);
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return list;
	}

	// ================= GET USER BY EMAIL =================

	@Override
	public User getUserByEmail(String email) {

		User user = null;

		try {

			Connection con = DBConnection.getConnection();

			PreparedStatement pstmt = con.prepareStatement(SELECT_EMAIL_QUERY);

			pstmt.setString(1, email);

			ResultSet res = pstmt.executeQuery();

			if (res.next()) {

				int userId = res.getInt("userId");

				String userName = res.getString("userName");

				String password = res.getString("password");

				String userEmail = res.getString("email");

				String address = res.getString("address");

				String role = res.getString("role");

				Timestamp createdDate = res.getTimestamp("createdDate");

				Timestamp lastLoginDate = res.getTimestamp("lastLoginDate");

				String profileImage = res.getString("profileImage");

				user = new User(userId, userName, password, userEmail, address, role, createdDate, lastLoginDate,
						profileImage);
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return user;
	}

	public boolean updatePassword(int userId, String newPassword) {

	    Connection con = DBConnection.getConnection();

	    try {

	        PreparedStatement pstmt =
	                con.prepareStatement(UPDATE_PASSWORD_QUERY);

	        pstmt.setString(1, newPassword);
	        pstmt.setInt(2, userId);

	        int result = pstmt.executeUpdate();

	        return result > 0;

	    } catch (SQLException e) {

	        e.printStackTrace();
	    }

	    return false;
	}
}