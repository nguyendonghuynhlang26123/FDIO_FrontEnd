package com.team3.fdiosystem.viewmodels;

import android.widget.ImageView;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.BindingAdapter;

import com.squareup.picasso.Picasso;
import com.team3.fdiosystem.BR;
import com.team3.fdiosystem.models.FoodModel;

public class FoodDialogVM extends BaseObservable {
    FoodModel model;
    Event choosingImgEvent;

    public FoodDialogVM(FoodModel model) {
        this.model = model;
        choosingImgEvent = null;
    }

    public FoodModel getModel() {
        return model;
    }

    public void setChoosingImgEvent(Event choosingImgEvent) {
        this.choosingImgEvent = choosingImgEvent;
    }

    public void setModel(FoodModel model) {
        this.model = model;
        notifyPropertyChanged(BR.foodDialogName);
        notifyPropertyChanged(BR.foodDialogDescription);
        notifyPropertyChanged(BR.foodDialogImg);
        notifyPropertyChanged(BR.foodDialogPrice);
    }

    @Bindable
    public String getFoodDialogName() {
        return model.getName();
    }

    public void setFoodDialogName(String foodDialogName) {
        model.setName(foodDialogName);
        notifyPropertyChanged(BR.foodDialogName);
    }

    @Bindable
    public String getFoodDialogDescription() {
        return model.getDescription();
    }

    public void setFoodDialogDescription(String foodDialogDescription) {
        model.setDescription(foodDialogDescription);
        notifyPropertyChanged(BR.foodDialogDescription);
    }

    @Bindable
    public String getFoodDialogImg() {
        return model.getThumbnail();
    }

    public void setFoodDialogImg(String foodDialogImg) {
        model.setThumbnail(foodDialogImg);
        notifyPropertyChanged(BR.foodDialogImg);
    }

    @Bindable
    public String getFoodDialogPrice() {
        return model.getPrice();
    }

    public void setFoodDialogPrice(String foodDialogPrice) {
        model.setPrice(foodDialogPrice);
        notifyPropertyChanged(BR.foodDialogPrice);
    }

    @BindingAdapter("foodDialogImgUrl")
    public static void loadImage(ImageView view, String url) {
        if (!url.equals(""))
            Picasso.get().load(url).into(view);
    }

    public void handleImageClick() {
        if (choosingImgEvent != null) choosingImgEvent.trigger();
    }

    @Bindable
    public String getFoodDialogType(){
        return model.getType();
    }

    public void setFoodDialogType(String type){
        model.setType(type);
        notifyPropertyChanged(BR.foodDialogType);
    }
}
