package com.food.DAO;

import java.util.List;

import com.food.Model.Order;

public interface OrderDAO {

	int addOrder(Order order);

	Order getOrder(int orderId);

	void updateOrder(Order order);

	void deleteOrder(int orderId);

	List<Order> getAllOrder();

}