package com.team3.fdiosystem.models;

import java.util.ArrayList;

public class Cart {

    private ArrayList<CartItem> items;
    //TODO: PROMOTION VARIABLES
    private String note;

    public Cart(ArrayList<CartItem> items, String note) {
        this.items = items;
        this.note = note;
    }

    public Cart(){
        this.items = new ArrayList<>();
        this.note = "";
    }

    
    //Getter & Setters
    public ArrayList<CartItem> getItems() {
        return items;
    }

    public void setItems(ArrayList<CartItem> items) {
        this.items = items;
    }

    public void addItem(CartItem item) {
        int index = getItemIndexById(item.getId());
        if (index != -1)
            this.items.get(index).setQuantity(this.items.get(index).getQuantity() + item.getQuantity());
        else this.items.add(item);
    }

    public void removeItemById(String id){
        int index = getItemIndexById(id);
        if (index != -1)
            this.items.remove(index);
    }

    public void setQuantity(String id, int quantity){
        int i = getItemIndexById(id);
        if (i != -1){
            items.get(i).setQuantity(quantity);
        }
    }

    public int getItemIndexById(String id){
        for (int i = 0; i < this.items.size(); i++) {
            if (this.items.get(i).getId().equalsIgnoreCase(id))
                return i;
        }
        return -1;
    }

    public boolean contains(String id) {return getItemIndexById(id) != -1;}

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
