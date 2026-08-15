
package com.food.Model;

public class CartItem {

    private int menuId;
    private int restaurantId;
    private String name;
    private double price;
    private int quantity;
    private int orderId;


    // Default constructor
    public CartItem() {

    }


    // Constructor
    public CartItem(int menuId,
                    int restaurantId,
                    String name,
                    double price,
                    int quantity,
                    int orderId) {

        this.menuId = menuId;
        this.restaurantId = restaurantId;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.orderId = orderId;
    }


    // =========================
    // GETTERS
    // =========================

    public int getMenuId() {
        return menuId;
    }


    public int getRestaurantId() {
        return restaurantId;
    }


    public String getName() {
        return name;
    }


    public double getPrice() {
        return price;
    }


    public int getQuantity() {
        return quantity;
    }


    public int getOrderId() {
        return orderId;
    }


    // =========================
    // SETTERS
    // =========================

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }


    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }


    public void setName(String name) {
        this.name = name;
    }


    public void setPrice(double price) {
        this.price = price;
    }


    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }


    // =========================
    // TOTAL PRICE
    // =========================

    public int getTotalPrice() {

        return (int) (price * quantity);
    }
}

