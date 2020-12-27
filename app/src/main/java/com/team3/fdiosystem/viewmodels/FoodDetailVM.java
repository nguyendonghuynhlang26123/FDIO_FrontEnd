package com.team3.fdiosystem.viewmodels;

import android.widget.ImageView;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.BindingAdapter;

import com.squareup.picasso.Picasso;
import com.team3.fdiosystem.BR;

public class FoodDetailVM extends BaseObservable {
    private String foodName;
    private int foodQuantity;
    private String foodDescription;
    private String foodImgUrl;

    public FoodDetailVM(String foodName, int foodQuantity, String foodDescription, String foodImgUrl) {
        setFoodDescription(foodDescription);
        setFoodName(foodName);
        setFoodQuantity(foodQuantity);
        setFoodImgUrl(foodImgUrl);
    }

    @BindingAdapter("imgFoodDetail")
    public static void loadImage(ImageView view, String url) {
        Picasso.get().load(url).into(view);
    }

    @Bindable
    public String getFoodImgUrl() {
        return foodImgUrl;
    }

    public void setFoodImgUrl(String foodImgUrl) {
        this.foodImgUrl = foodImgUrl;
        notifyPropertyChanged(BR.foodImgUrl);
    }

    @Bindable
    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
        notifyPropertyChanged(BR.foodName);
    }

    @Bindable
    public String getFoodQuantity() {
        return "" + foodQuantity;
    }

    public void setFoodQuantity(int foodQuantity) {
        this.foodQuantity = foodQuantity;
        notifyPropertyChanged(BR.foodQuantity);
    }

    @Bindable
    public String getFoodDescription() {
        return foodDescription;
    }

    public void setFoodDescription(String foodDescription) {
        this.foodDescription = foodDescription;
        notifyPropertyChanged(BR.foodDescription);
    }

    public void addBtnHandler(){

    }
}
