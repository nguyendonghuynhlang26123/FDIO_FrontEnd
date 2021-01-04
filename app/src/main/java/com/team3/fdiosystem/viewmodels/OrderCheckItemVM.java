package com.team3.fdiosystem.viewmodels;

import android.graphics.Color;
import android.os.Build;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.BindingAdapter;

import com.squareup.picasso.Picasso;
import com.team3.fdiosystem.BR;
import com.team3.fdiosystem.R;
import com.team3.fdiosystem.Utils;
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
        FoodModel food = Store.get_instance().getFoodModelById(model.getId());
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

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @BindingAdapter("StatusColor")
    public static void setColor(TextView view, String status){
        switch (status){
            case Utils.STATUS_WAITING:
                view.setTextColor(Color.parseColor("#FF03DAC5"));
                view.setText("Waiting");
                break;
            case Utils.STATUS_PROCESSING:
                view.setTextColor(Color.parseColor("#FF03DAC5"));
                view.setText("Processing");
                break;
            case Utils.STATUS_DONE:
                view.setText("Done");
                view.setTextColor(Color.parseColor("#ffd66b"));
                break;
            case Utils.STATUS_DENIED:
                view.setText("Denied");
                view.setTextColor(Color.parseColor("#F54B42"));
                break;
            default:
                break;
        }

    }
}
