package com.team3.fdiosystem.models;

public class ItemGroup {
    public Item item1, item2, item3;
    public ItemGroup(int img1, int img2, int img3) {
        item1 = new Item(img1);
        item2 = new Item(img2);
        item3 = new Item(img3);
    }
}