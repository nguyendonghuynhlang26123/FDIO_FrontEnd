package com.team3.fdiosystem.models;

public class CartItem extends OrderItemModel{
    private int price;

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public CartItem(String id, Integer quantity, int price) {
        super(id, quantity);
        this.price = price;
    }

    public CartItem(FoodModel item, int quantity) {
        super(item.getId(), quantity);
        this.price = Integer.parseInt(item.getPrice());
    }

}
