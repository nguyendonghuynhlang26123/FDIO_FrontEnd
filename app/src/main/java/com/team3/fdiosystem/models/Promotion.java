package com.team3.fdiosystem.models;

import java.util.ArrayList;

public class Promotion {
    String ID;
    ArrayList<MenuItem> applier;
    int discount;
    public String getID(){return ID;}
    public Promotion(String ID, ArrayList<MenuItem> applier, int discount){
        this.ID = ID;
        this.applier = new ArrayList<>();
        this.applier.addAll(applier);
        this.discount = discount;
    }

    public int apply(CartItem cartItem){
        if(applier.contains(cartItem)){
            return cartItem.price*cartItem.quantity*discount/100;
        }
        return cartItem.price*cartItem.quantity;
    }
}
