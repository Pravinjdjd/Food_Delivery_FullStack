package com.food.Model;

public class Restaurant {

    private int restaurantId;
    private int adminUserId;
    private String restaurantName;
    private String cuisineType;
    private int deliveryTime;
    private String address;
    private float rating;
    private int isActive;
    private String image;

    public Restaurant() {

    }

    // Constructor without restaurantId (used while inserting)
    public Restaurant(int adminUserId, String restaurantName, String cuisineType,
            int deliveryTime, String address, float rating,
            int isActive, String image) {

        this.adminUserId = adminUserId;
        this.restaurantName = restaurantName;
        this.cuisineType = cuisineType;
        this.deliveryTime = deliveryTime;
        this.address = address;
        this.rating = rating;
        this.isActive = isActive;
        this.image = image;
    }

    // Constructor with restaurantId (used while fetching data)
    public Restaurant(int restaurantId, int adminUserId, String restaurantName,
            String cuisineType, int deliveryTime, String address,
            float rating, int isActive, String image) {

        this.restaurantId = restaurantId;
        this.adminUserId = adminUserId;
        this.restaurantName = restaurantName;
        this.cuisineType = cuisineType;
        this.deliveryTime = deliveryTime;
        this.address = address;
        this.rating = rating;
        this.isActive = isActive;
        this.image = image;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public int getAdminUserId() {
        return adminUserId;
    }

    public void setAdminUserId(int adminUserId) {
        this.adminUserId = adminUserId;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getCuisineType() {
        return cuisineType;
    }

    public void setCuisineType(String cuisineType) {
        this.cuisineType = cuisineType;
    }

    public int getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(int deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public int getIsActive() {
        return isActive;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

    // Image Getter & Setter

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Restaurant [restaurantId=" + restaurantId
                + ", adminUserId=" + adminUserId
                + ", restaurantName=" + restaurantName
                + ", cuisineType=" + cuisineType
                + ", deliveryTime=" + deliveryTime
                + ", address=" + address
                + ", rating=" + rating
                + ", isActive=" + isActive
                + ", image=" + image + "]";
    }
}