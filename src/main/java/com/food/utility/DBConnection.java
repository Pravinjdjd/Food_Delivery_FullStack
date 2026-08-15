package com.food.utility;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    private static final String HOST =
            System.getenv("DB_HOST");

    private static final String PORT =
            System.getenv("DB_PORT");

    private static final String DATABASE =
            System.getenv("DB_NAME");

    private static final String USER =
            System.getenv("DB_USER");

    private static final String PASS =
            System.getenv("DB_PASSWORD");

    private static final String URL =
            "jdbc:mysql://" + HOST + ":" + PORT + "/" + DATABASE
            + "?sslMode=REQUIRED";

    public static Connection getConnection() {

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con =
                    DriverManager.getConnection(URL, USER, PASS);

            return con;

        } catch (Exception e) {

            e.printStackTrace();
        }

        return null;
    }
}