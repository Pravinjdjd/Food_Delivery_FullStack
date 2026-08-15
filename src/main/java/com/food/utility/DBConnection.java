package com.food.utility;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/food_delivery";
    private static final String USER = "root";
    private static final String PASS = "root";

    public static Connection getConnection() {

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(URL, USER, PASS);

           // System.out.println("Database Connected Successfully");

            return con;

        } catch (Exception e) {

            e.printStackTrace();
        }

        return null;
    }
}