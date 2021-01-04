package com.team3.fdiosystem.models;

import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.google.android.gms.common.util.ArrayUtils;
import com.google.gson.Gson;
import com.team3.fdiosystem.Utils;
import com.team3.fdiosystem.activities.FoodDialog;
import com.team3.fdiosystem.repositories.services.MyFirebaseService;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Store {
    //Variables
    private ArrayList<FoodListModel> menu;  //Store the whole menu here
    private Cart cart; //Store the temporary cart before ordering
    private HashMap<String, ArrayList<OrderItemModel>> orderedMap; //Show the list of ordered food to keep track there status
    private int mode; //Current Mode
    private String tableId; //Current table of the device

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private Store(){
        menu = new ArrayList<>();
        cart = new Cart();
        orderedMap = new HashMap<>();
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

    public HashMap<String, ArrayList<OrderItemModel>> getOrderedMap() {
        return orderedMap;
    }

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

    public void removeAFoodInFoodList(String foodId, String menuId){
        FoodListModel menu = this.getMenuById(menuId);
        if (menu.getFoodIdList().length == 0) return;

        String[] newIds = new String[menu.getFoodIdList().length - 1];
        FoodModel[] newModels = new FoodModel[menu.getFoodIdList().length - 1];
        int count = 0;

        for (int i = 0; i < menu.getFoodList().length; i++){
            if (!menu.getFoodIdList()[i].equalsIgnoreCase(foodId)){
                newModels[count] = menu.getFoodList()[i];
                newIds[count] = menu.getFoodIdList()[i];
                count++;
            }
        }
        menu.setFoodIdList(newIds);
        menu.setFoodList(newModels);
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
        ArrayList<OrderItemModel> orderedList = new ArrayList<>();
        for (ArrayList<OrderItemModel> list : orderedMap.values()){
            orderedList.addAll(list);
        }
        return orderedList;
    }

    public void appendOrderedList(String orderId, ArrayList<OrderItemModel> orderedList) {
        this.orderedMap.put(orderId, orderedList);
    }

    public boolean setStatus(String foodId, String orderId, String status){
        if (orderedMap.containsKey(orderId))
            for (OrderItemModel food : orderedMap.get(orderId)){
                if (foodId.equalsIgnoreCase(food.getId())){
                    food.setStatus(status);
                    return true;
                }
            }
        return false;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    public String getTableId() {
        return tableId;
    }

    public void setTableId(String tableId) {
        this.tableId = tableId;
    }
}
