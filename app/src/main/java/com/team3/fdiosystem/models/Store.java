package com.team3.fdiosystem.models;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.team3.fdiosystem.R;

import java.util.ArrayList;
import java.util.HashMap;

public class Store {
    private static volatile Store _instance;
    ArrayList<Promotion> promo_list;
    HashMap<String, ArrayList<String>> menu;
    ArrayList<MenuItem> menuItems;
    public ArrayList<Item> items;
    public Cart cart;
    private Store(){
        promo_list = new ArrayList<>();
        items = new ArrayList<>();
        menuItems = new ArrayList<>();
        menu = new HashMap<>();
        cart = new Cart();
        InitMenu();
//        InitMenuItem();
//        InitPromo();
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
        for(int i=0;i<menuItems.size();++i){
            if(menuItems.get(i).name==name){
                return menuItems.get(i);
            }
        }
        return null;
    }
    public ArrayList<Promotion> getPromos(){
        return promo_list;
    }
    public void addMenu(Item menu){
        items.add(menu);
    }
    public void removeMenu(Item menu){
        items.remove(menu);
    }
    private void InitPromo(){
        for(int i=0;i<10;++i){
            promo_list.add(new Promotion("Test"+i,menuItems,50+i));
        }
    }
    private void InitMenu(){
        items.add(new Item(3,4,0,R.drawable.login_bg));
        items.add(new Item(2,2,1,R.drawable.customsalad150_250));
        items.add(new Item(2,2,2,R.drawable.customsalad150_250));
        items.add(new Item(2,4,3,R.drawable.login_bg));
        for(int i=4;i<10;++i){
            items.add(new Item(3+(i%3),4, i,R.drawable.foodex));
        }
    }
    private void InitMenuItem(){
        for(int i=0;i<10;++i){
            menuItems.add(new MenuItem("Item name "+i,i, R.drawable.customsalad150_250,"Item description"));
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void addToCart(MenuItem item, int quantity){
        CartItem citem = new CartItem(item.name, item.price, item.img, quantity);
        promo_list.forEach(promotion -> {
            int ckoutPrice = promotion.apply(citem);
            if(ckoutPrice < citem.getCkout_price()){
                citem.setCkout_price(ckoutPrice);
                cart.promotions.add(promotion);
            }
        });
        cart.cart.add(citem);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void addMenuItem(String name, int price, int img, String description, int type){
        MenuItem menuItem = new MenuItem(name,price,img, description);
        Item current_menu = items.get(type);
        menuItems.add(menuItem);
        menu.computeIfAbsent(current_menu.name,k->new ArrayList<>()).add(menuItem.name);
    }



    @RequiresApi(api = Build.VERSION_CODES.N)
    public ArrayList<MenuItem> getMenuItems(int type){
        ArrayList<MenuItem> res= new ArrayList<>();
        Item current_menu = items.get(type);
        ArrayList<String> menu_items = menu.get(current_menu.name);
        if(menu_items!=null) {
            menu_items.forEach(item -> {
                res.add(getMenuItemByName(item));
            });
        }
        return res;
    }
    public class OrderedItem{
        public int img;
        public String name;
        public String status;
        public int quantity;
        public OrderedItem(){
            img=R.drawable.customsalad150_250;
            name="Salad ca ngu";
            quantity = 1;
        }
    }
    public ArrayList<OrderedItem> getOrderedList(){
        ArrayList<OrderedItem> res = new ArrayList<>();
        for(int i=0;i<10;++i){
            res.add(new OrderedItem());
        }
        return res;
    }
}
