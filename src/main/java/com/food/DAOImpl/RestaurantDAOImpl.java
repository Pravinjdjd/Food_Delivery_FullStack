package com.food.DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.food.DAO.RestaurantDAO;
import com.food.Model.Restaurant;
import com.food.utility.DBConnection;

public class RestaurantDAOImpl implements RestaurantDAO {

    private static final String INSERT_QUERY =
            "INSERT INTO restaurant(adminUserId, restaurantName, cuisineType, deliveryTime, address, rating, isActive, image) VALUES(?,?,?,?,?,?,?,?)";

    private static final String SELECT_QUERY =
            "SELECT * FROM restaurant WHERE restaurantId=?";

    private static final String UPDATE_QUERY =
            "UPDATE restaurant SET restaurantName=?, cuisineType=?, deliveryTime=?, address=?, rating=?, isActive=?, image=? WHERE restaurantId=?";

    private static final String DELETE_QUERY =
            "DELETE FROM restaurant WHERE restaurantId=?";

    private static final String SELECT_ALL_QUERY =
            "SELECT * FROM restaurant";
    private static final String SEARCH_QUERY =
            "SELECT * FROM restaurant WHERE restaurantName LIKE ?";

    @Override
    public void addRestaurant(Restaurant restaurant) {

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement pstmt = con.prepareStatement(INSERT_QUERY);

            pstmt.setInt(1, restaurant.getAdminUserId());
            pstmt.setString(2, restaurant.getRestaurantName());
            pstmt.setString(3, restaurant.getCuisineType());
            pstmt.setInt(4, restaurant.getDeliveryTime());
            pstmt.setString(5, restaurant.getAddress());
            pstmt.setFloat(6, restaurant.getRating());
            pstmt.setInt(7, restaurant.getIsActive());
            pstmt.setString(8, restaurant.getImage());

            int i = pstmt.executeUpdate();

            System.out.println(i + " Data Added");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Restaurant getRestaurant(int restaurantId) {

        Restaurant restaurant = null;

        Connection con = DBConnection.getConnection();

        try {

            PreparedStatement pstmt = con.prepareStatement(SELECT_QUERY);

            pstmt.setInt(1, restaurantId);

            ResultSet res = pstmt.executeQuery();

            while (res.next()) {

                int id = res.getInt("restaurantId");
                int adminUserId = res.getInt("adminUserId");
                String restaurantName = res.getString("restaurantName");
                String cuisineType = res.getString("cuisineType");
                int deliveryTime = res.getInt("deliveryTime");
                String address = res.getString("address");
                float rating = res.getFloat("rating");
                int isActive = res.getInt("isActive");
                String image = res.getString("image");

                restaurant = new Restaurant(
                        id,
                        adminUserId,
                        restaurantName,
                        cuisineType,
                        deliveryTime,
                        address,
                        rating,
                        isActive,
                        image
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return restaurant;
    }

    @Override
    public void updateRestaurant(Restaurant restaurant) {

        Connection con = DBConnection.getConnection();

        try {

            PreparedStatement pstmt = con.prepareStatement(UPDATE_QUERY);

            pstmt.setString(1, restaurant.getRestaurantName());
            pstmt.setString(2, restaurant.getCuisineType());
            pstmt.setInt(3, restaurant.getDeliveryTime());
            pstmt.setString(4, restaurant.getAddress());
            pstmt.setFloat(5, restaurant.getRating());
            pstmt.setInt(6, restaurant.getIsActive());
            pstmt.setString(7, restaurant.getImage());
            pstmt.setInt(8, restaurant.getRestaurantId());

            int n = pstmt.executeUpdate();

            System.out.println(n + " Data Updated");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteRestaurant(int restaurantId) {

        Connection con = DBConnection.getConnection();

        try {

            // Delete order items
            PreparedStatement ps1 = con.prepareStatement(
                    "DELETE FROM orderitem WHERE orderId IN (SELECT orderId FROM ordertable WHERE restaurantId=?)");

            ps1.setInt(1, restaurantId);
            ps1.executeUpdate();

            // Delete orders
            PreparedStatement ps2 = con.prepareStatement(
                    "DELETE FROM ordertable WHERE restaurantId=?");

            ps2.setInt(1, restaurantId);
            ps2.executeUpdate();

            // Delete restaurant
            PreparedStatement ps3 = con.prepareStatement(DELETE_QUERY);

            ps3.setInt(1, restaurantId);

            int n = ps3.executeUpdate();

            System.out.println(n + " Restaurant Deleted");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Restaurant> getAllRestaurant() {

        List<Restaurant> list = new ArrayList<>();

        Connection connection = DBConnection.getConnection();

        try {

            Statement stmt = connection.createStatement();

            ResultSet res = stmt.executeQuery(SELECT_ALL_QUERY);

            while (res.next()) {

                int restaurantId = res.getInt("restaurantId");
                int adminUserId = res.getInt("adminUserId");
                String restaurantName = res.getString("restaurantName");
                String cuisineType = res.getString("cuisineType");
                int deliveryTime = res.getInt("deliveryTime");
                String address = res.getString("address");
                float rating = res.getFloat("rating");
                int isActive = res.getInt("isActive");
                String image = res.getString("image");

                Restaurant restaurant = new Restaurant(
                        restaurantId,
                        adminUserId,
                        restaurantName,
                        cuisineType,
                        deliveryTime,
                        address,
                        rating,
                        isActive,
                        image
                );

                list.add(restaurant);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
    
    
    public List<Restaurant> searchRestaurants(String search) {

        List<Restaurant> list = new ArrayList<>();

        Connection con = DBConnection.getConnection();

        try {

            PreparedStatement pstmt = con.prepareStatement(SEARCH_QUERY);

            pstmt.setString(1, "%" + search + "%");

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {

                Restaurant restaurant = new Restaurant();

                restaurant.setRestaurantId(
                        rs.getInt("restaurantId"));

                restaurant.setAdminUserId(
                        rs.getInt("adminUserId"));

                restaurant.setRestaurantName(
                        rs.getString("restaurantName"));

                restaurant.setCuisineType(
                        rs.getString("cuisineType"));

                restaurant.setDeliveryTime(
                        rs.getInt("deliveryTime"));

                restaurant.setAddress(
                        rs.getString("address"));

                restaurant.setRating(
                        rs.getFloat("rating"));

                restaurant.setIsActive(
                        rs.getInt("isActive"));

                // ⭐ IMPORTANT - IMAGE
                restaurant.setImage(
                        rs.getString("image"));

                list.add(restaurant);
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return list;
    }
}