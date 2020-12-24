package com.team3.fdiosystem.models;

import java.util.ArrayList;

public class Cart {
    public ArrayList<CartItem> cart;
    public ArrayList<Promotion> promotions;
    public Cart(){
        this.cart = new ArrayList<>();
        this.promotions = new ArrayList<>();
    }
    public Cart(ArrayList<CartItem> cart, ArrayList<Promotion> promotions){
        this.cart = new ArrayList<>();
        this.promotions = new ArrayList<>();
        this.cart.addAll(cart);
        this.promotions.addAll(promotions);
    }
}