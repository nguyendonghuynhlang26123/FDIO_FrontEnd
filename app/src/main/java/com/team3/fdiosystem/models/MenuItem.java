package com.team3.fdiosystem.models;

public class MenuItem extends Item{
    public int price;
    public MenuItem(int price, int img){
        super(img);
        this.price=price;
    }
}
