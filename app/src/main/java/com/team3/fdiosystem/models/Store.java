package com.team3.fdiosystem.models;

import com.team3.fdiosystem.R;

import java.util.ArrayList;
import java.util.HashMap;

public class Store {
    private static volatile Store _instance;
    ArrayList<Promotion> promo_list;
    HashMap<String, ArrayList<String>> menu;
    ArrayList<MenuItem> menuItems;
    ArrayList<Item> items;
    private Store(){
        promo_list = new ArrayList<>();
        items = new ArrayList<>();
        menuItems = new ArrayList<>();
        menu = new HashMap<>();
        InitMenu();
        InitMenuItem();
        InitPromo();
    }

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
    public Item getMenuByName(String name){
        return items.get(items.indexOf(name));
    }
    public MenuItem getMenuItemByName(String name){
        return menuItems.get(menuItems.indexOf(name));
    }
    public ArrayList<Promotion> getPromos(){
        return promo_list;
    }
    public ArrayList<Item> getMenus(){
        return items;
    }
    public ArrayList<MenuItem> getMenuItems(){
        return menuItems;
    }
    public void addMenu(Item menu){
        items.add(menu);
    }
    public void removeMenu(Item menu){
        items.remove(menu);
    }
    private void InitPromo(){
        for(int i=0;i<10;++i){
            promo_list.add(new Promotion("Test"+i));
        }
    }
    private void InitMenu(){
        items.add(new Item(2,4,0,R.drawable.login_bg));
        items.add(new Item(2,2,1,R.drawable.customsalad150_250));
        items.add(new Item(2,2,2,R.drawable.customsalad150_250));
        items.add(new Item(3,4,3,R.drawable.login_bg));
        for(int i=4;i<10;++i){
            items.add(new Item(2+(i%3),3, i,R.drawable.foodex));
        }
    }
    private void InitMenuItem(){
        for(int i=0;i<10;++i){
            menuItems.add(new MenuItem(i, R.drawable.customsalad150_250));
        }
    }
}
