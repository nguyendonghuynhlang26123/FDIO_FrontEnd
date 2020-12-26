package com.team3.fdiosystem.models;

import android.util.Log;

import androidx.annotation.RequiresApi;

import com.google.gson.Gson;

import java.util.ArrayList;

public class Store {
    //Variables
    private ArrayList<FoodListModel> menu;
    private Cart cart;
    private ArrayList<OrderItemModel> orderedList;

    private Store(){
        menu = new ArrayList<>();
        cart = new Cart();
        orderedList = new ArrayList<>();
    }

    //Singleton
    private static volatile Store _instance;
    public static Store get_instance() {
        if(_instance == null){
            synchronized (Store.class){
                if(_instance==null){
                    _instance = new Store();
                }
            }
        }
        return _instance;
    }

    //Functions
    
    
    //Getter Setter
    public ArrayList<FoodListModel> getMenu() {
        return menu;
    }

    public void setMenu(ArrayList<FoodListModel> menu) {
        this.menu = menu;
        Log.i("STORE_", new Gson().toJson(this.menu));
    }

    public FoodListModel getMenuById(String id){
        for (FoodListModel foodlist: menu ) {
            if (foodlist.getId().equalsIgnoreCase(id)) return foodlist;
        }
        return null;
    }

    public FoodModel getModelById(String id){
        for (FoodListModel foodlist: menu ) {
            for (int i = 0; i < foodlist.getFoodIdList().length; i++) {
                if(foodlist.getFoodIdList()[i].equalsIgnoreCase(id))
                    return foodlist.getFoodList()[i];
            }
        }
        return null;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public void addToCart(CartItem item){
        this.cart.addItem(item);
    }

    public void setNote(String note) {this.cart.setNote(note);}

    public void resetCart(){
        this.cart = new Cart();
    }

    public ArrayList<OrderItemModel> getOrderedList() {
        return orderedList;
    }

    public void setOrderedList(ArrayList<OrderItemModel> orderedList) {
        this.orderedList = orderedList;
    }

    public void appendOrderedList(ArrayList<OrderItemModel> orderedList) {
        this.orderedList.addAll(orderedList);
    }
}
