package com.team3.fdiosystem.models;

public class MenuItem{
    public int price;
    public int img;
    public String description;
    public String name;
    MenuItem(String name, int price, int img, String description){
        this.img = img;
        this.price=price;
        this.description=description;
        this.name=name;
    }
}