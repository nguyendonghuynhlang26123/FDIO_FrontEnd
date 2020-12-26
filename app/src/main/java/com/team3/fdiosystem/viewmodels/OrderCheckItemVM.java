package com.team3.fdiosystem.viewmodels;

import android.widget.ImageView;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.BindingAdapter;

import com.squareup.picasso.Picasso;
import com.team3.fdiosystem.BR;
import com.team3.fdiosystem.models.FoodModel;
import com.team3.fdiosystem.models.OrderItemModel;
import com.team3.fdiosystem.models.Store;

public class OrderCheckItemVM extends BaseObservable {
    private String id;
    private String orderCheckName;
    private String orderCheckStatus;
    private String orderCheckImg;
    private int orderCheckQuantity;

    public OrderCheckItemVM(OrderItemModel model){
        id = "";
        orderCheckName="";
        orderCheckImg="";
        orderCheckStatus="";
        orderCheckQuantity=0;


        FoodModel food = Store.get_instance().getModelById(model.getId());
        if (food != null){
            this.id = model.getId();
            setOrderCheckName(food.getName());
            setOrderCheckStatus(model.getStatus());
            setOrderCheckImg(food.getThumbnail());
            setOrderCheckQuantity(model.getQuantity());
        }
    }

    @BindingAdapter("imgOrderCheckItem")
    public static void loadImage(ImageView view, String url) {
        Picasso.get().load(url).into(view);
    }

    @Bindable
    public String getOrderCheckName() {
        return orderCheckName;
    }

    public void setOrderCheckName(String orderCheckName) {
        this.orderCheckName = orderCheckName;
        notifyPropertyChanged(BR.orderCheckName);
    }

    @Bindable
    public String getOrderCheckStatus() {
        return orderCheckStatus;
    }

    public void setOrderCheckStatus(String orderCheckStatus) {
        this.orderCheckStatus = orderCheckStatus;
        notifyPropertyChanged(BR.orderCheckStatus);
    }

    @Bindable
    public String getOrderCheckImg() {
        return orderCheckImg;
    }

    public void setOrderCheckImg(String orderCheckImg) {
        this.orderCheckImg = orderCheckImg;
        notifyPropertyChanged(BR.orderCheckImg);
    }

    @Bindable
    public String getOrderCheckQuantity() {
        return "Quantity: " + orderCheckQuantity;
    }

    public void setOrderCheckQuantity(int orderCheckQuantity) {
        this.orderCheckQuantity = orderCheckQuantity;
        notifyPropertyChanged(BR.orderCheckQuantity);
    }
}
