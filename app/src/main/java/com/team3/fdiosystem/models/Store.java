package com.team3.fdiosystem.models;

import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.google.gson.Gson;
import com.team3.fdiosystem.Utils;
import com.team3.fdiosystem.activities.FoodDialog;
import com.team3.fdiosystem.repositories.services.MyFirebaseService;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Store {
    //Variables
    private ArrayList<FoodListModel> menu;  //Store the whole menu here
    private Cart cart; //Store the temporary cart before ordering
    private ArrayList<OrderItemModel> orderedList; //Show the list of ordered food to keep track there status
    private int mode; //Current Mode

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private Store(){
        menu = new ArrayList<>();
        cart = new Cart();
        orderedList = new ArrayList<>();
        mode = Utils.GUEST_MODE;
    }

    //Singleton
    private static volatile Store _instance;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
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
    }

    public void addAMenu(FoodListModel model){
        this.menu.add(model);
    }

    public void removeAMenu(int position) {
        try {
            this.menu.remove(position);
        } catch (Exception e) {
            return;
        }
    }

    public FoodListModel getMenuById(String id){
        for (FoodListModel foodlist: menu ) {
            if (foodlist.getId().equalsIgnoreCase(id)) return foodlist;
        }
        return null;
    }

    public FoodModel getFoodModelById(String id){
        for (FoodListModel foodlist: menu ) {
            for (int i = 0; i < foodlist.getFoodIdList().length; i++) {
                if(foodlist.getFoodIdList()[i].equalsIgnoreCase(id))
                    return foodlist.getFoodList()[i];
            }
        }
        return null;
    }

    public void addNewFoodToFoodList(String foodListId, FoodModel newFood){
        FoodListModel targetedFoodList = getMenuById(foodListId);
        if (targetedFoodList == null || newFood.getId().equals("")) return;

        FoodModel[] oldFlist = targetedFoodList.getFoodList();
        FoodModel[] newFList = new FoodModel[oldFlist.length + 1];
        newFList[oldFlist.length] = newFood;
        System.arraycopy(oldFlist,0,newFList, 0, oldFlist.length);
        targetedFoodList.setFoodList(newFList);

        String[] oldIdList = targetedFoodList.getFoodIdList();
        String[] newIdList = new String[oldIdList.length + 1];
        newIdList[oldIdList.length] = newFood.getId();
        System.arraycopy(oldIdList,0,newIdList,0,oldIdList.length);
        targetedFoodList.setFoodIdList(newIdList);
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

    public void setMode(int mode) {
        this.mode = mode;
    }
}
