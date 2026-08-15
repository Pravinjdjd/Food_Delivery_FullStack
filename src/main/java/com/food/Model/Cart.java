package com.food.Model;

import java.util.HashMap;
import java.util.Map;

public class Cart {

    private Map<Integer, CartItem> items;

    public Cart() {
        items = new HashMap<Integer, CartItem>();
    }

    public Map<Integer, CartItem> getItems() {
        return items;
    }

    public void addItem(CartItem cartItem) {

        int menuId = cartItem.getMenuId();

        if (items.containsKey(menuId)) {

            CartItem existingCartItem = items.get(menuId);

            existingCartItem.setQuantity(
                existingCartItem.getQuantity() + 1
            );

        } else {

            items.put(menuId, cartItem);
        }
    }

    public void updateItem(int menuId, int quantity) {

        if (items.containsKey(menuId)) {

            if (quantity <= 0) {
                items.remove(menuId);
            } else {

                CartItem item = items.get(menuId);

                item.setQuantity(quantity);
            }
        }
    }

    public void removeItem(int menuId) {

        items.remove(menuId);
    }

    public void clearCart() {

        items.clear();
    }

    public double getGrandTotal() {

        double total = 0;

        for (CartItem item : items.values()) {

            total = total + item.getTotalPrice();
        }

        return total;
    }
}