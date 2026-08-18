# 🍔 Food Delivery Application

A full-stack food delivery web application built using Java web technologies. 
The application allows users to discover restaurants, explore menus, manage their cart, place orders, and manage their profiles.

## 🚀 Live Demo

🌐 https://food-delivery-fullstack-l3rz.onrender.com/

---

## ✨ Features

### 👤 User Features

- 🔐 User Registration & Login
- 🚪 Logout
- 👤 User Profile
- 🔎 Restaurant Search
- 🍽️ Browse Restaurants
- 📋 View Restaurant Menu
- 🛒 Add Items to Cart
- ➕ Increase / Decrease Quantity
- ❌ Remove Items from Cart
- 💳 Checkout
- 📦 Place Orders
- 🧾 View Order Details

### 🏪 Restaurant Features

- 🍴 Restaurant Listing
- 🖼️ Restaurant Images
- ⭐ Restaurant Ratings
- 🍜 Cuisine Information
- 🕒 Delivery Time
- 📍 Restaurant Address
- 🟢 Active / Inactive Restaurant Status
- 📋 Restaurant Menu Management

### 🛒 Cart & Order Management

- Add food items to cart
- Update item quantity
- Remove cart items
- Automatic price calculation
- Order total calculation
- Checkout process
- Payment method selection
- Order creation and storage

---

## 🛠️ Technologies Used

<p align="center">
  <img src="https://skillicons.dev/icons?i=java,html,css,js,mysql,maven,tomcat,eclipse,postman,git,github" />
</p>

<p align="center">
  <strong>JSP</strong> •
  <strong>Servlets</strong> •
  <strong>JDBC</strong> •
  <strong>DAO Pattern</strong> •
  <strong>MVC Architecture</strong> •
  <strong>REST APIs</strong> •
  <strong>Render</strong> •
  <strong>Aiven MySQL</strong>
</p>

---

## 🏗️ Architecture

The application follows an MVC-based architecture with a DAO layer for database operations.

```text
                    👤 User
                       │
                       ▼
              🌐 JSP / HTML / CSS
                       │
                       ▼
                🎮 Servlets
                       │
                       ▼
                  📦 Model
                       │
                       ▼
                 🗂️ DAO Layer
                       │
                       ▼
                    🔗 JDBC
                       │
                       ▼
                  🐬 MySQL
