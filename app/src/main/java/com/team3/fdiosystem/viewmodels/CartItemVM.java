package com.team3.fdiosystem.viewmodels;

import android.os.Build;
import android.widget.ImageView;

import androidx.annotation.RequiresApi;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.BindingAdapter;

import com.squareup.picasso.Picasso;
import com.team3.fdiosystem.BR;
import com.team3.fdiosystem.Utils;
import com.team3.fdiosystem.models.CartItem;
import com.team3.fdiosystem.models.FoodModel;
import com.team3.fdiosystem.models.Store;

public class CartItemVM extends BaseObservable {
    private int cartItemPrice;
    private int cartItemTotalPrice;
    private int cartitemQuantity;
    private String cartItemName;
    private String cartItemImgUrl;
    private String id;
    private Event requestRefresh;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CartItemVM(CartItem item) {
        this.id = item.getId();
        this.requestRefresh = null;
        FoodModel food = Store.get_instance().getFoodModelById(id);

        setCartItemName(food.getName());
        setCartItemImgUrl(food.getThumbnail());
        setCartItemPrice( item.getPrice());
        setCartitemQuantity( item.getQuantity());
        setCartItemTotalPrice(item.getPrice() * item.getQuantity());
    }

    public CartItemVM(String cartItemName, int cartItemQuantity, int cartItemPrice, int cartItemTotalPrice, String cartItemImgUrl, String id) {
        setCartItemName(cartItemName);
        setCartitemQuantity(cartItemQuantity);
        setCartItemPrice(cartItemPrice);
        setCartItemTotalPrice(cartItemTotalPrice);
        setCartItemImgUrl(cartItemImgUrl);
    }

    @BindingAdapter("imgCartItem")
    public static void loadImage(ImageView view, String url) {
        Picasso.get().load(url).into(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Bindable
    public String getCartItemPrice() {
        return Utils.format.format(cartItemPrice);
    }

    public void setCartItemPrice(int cartItemPrice) {
        this.cartItemPrice = cartItemPrice;
        notifyPropertyChanged(BR.cartItemPrice);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Bindable
    public String getCartItemTotalPrice() {
        return Utils.format.format(cartItemTotalPrice);
    }

    public void setCartItemTotalPrice(int cartItemTotalPrice) {
        this.cartItemTotalPrice = cartItemTotalPrice;
        notifyPropertyChanged(BR.cartItemTotalPrice);
    }

    @Bindable
    public String getCartitemQuantity() {
        return Integer.toString(this.cartitemQuantity);
    }

    public void setCartitemQuantity(int cartitemQuantity) {
        this.cartitemQuantity = cartitemQuantity;
        notifyPropertyChanged(BR.cartitemQuantity);
    }

    @Bindable
    public String getCartItemName() {
        return cartItemName;
    }

    public void setCartItemName(String cartItemName) {
        this.cartItemName = cartItemName;
        notifyPropertyChanged(BR.cartItemName);
    }

    @Bindable
    public String getCartItemImgUrl() {
        return cartItemImgUrl;
    }

    public void setCartItemImgUrl(String cartItemImgUrl) {
        this.cartItemImgUrl = cartItemImgUrl;
        notifyPropertyChanged(BR.cartItemImgUrl);
    }

    public Event getRequestRefresh() {
        return requestRefresh;
    }

    public void increaseQuantity(){
        Store.get_instance().getCart().setQuantity(id, cartitemQuantity + 1);

        if (requestRefresh != null)
            requestRefresh.trigger();
    }

    public void decreaseQuantity(){
        if (cartitemQuantity == 1)
            Store.get_instance().getCart().removeItemById(id);
        else Store.get_instance().getCart().setQuantity(id, cartitemQuantity - 1);

        if (requestRefresh != null)
            requestRefresh.trigger();
    }

    public void setRequestRefresh(Event requestRefresh) {
        this.requestRefresh = requestRefresh;
    }

}
