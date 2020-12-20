package com.team3.fdiosystem.models;

public class CartItem extends MenuItem {
    public int quantity;
    public CartItem(int price, int img, int quantity) {
        super(price, img);
        this.quantity = quantity;
    }
}