package com.team3.fdiosystem.models;

public class CartItem extends MenuItem {
    public int quantity;
    public int ckout_price;
    CartItem(String name, int price, int img, int quantity) {
        super(name, price, img, "");
        this.quantity = quantity;
        ckout_price = price*quantity;
    }
    public int getCkout_price(){
        return ckout_price;
    }
    public void setCkout_price(int ckout_price){
        this.ckout_price = ckout_price;
    }
}