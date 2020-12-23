package com.team3.fdiosystem.models;

public class MenuItem{
    public int price;
    public int img;
    public String description;
    public String name;
    public MenuItem(int price, int img){
        this.img = img;
        this.price=price;
        this.description="Item description";
        this.name="Item name";
    }
}