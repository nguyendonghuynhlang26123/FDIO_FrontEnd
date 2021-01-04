package com.team3.fdiosystem.viewmodels;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.BindingAdapter;

import com.squareup.picasso.Picasso;
import com.team3.fdiosystem.BR;
import com.team3.fdiosystem.R;
import com.team3.fdiosystem.models.FoodListModel;
import com.team3.fdiosystem.models.Store;
import com.team3.fdiosystem.repositories.services.FoodListService;

public class MenuModifyDialogVM extends BaseObservable {
    private int uiType;
    private String foodListDialogName;
    private String foodListDialogImage = "";
    private int foodListUploadPercent;


    public MenuModifyDialogVM() {
        setUiType(1);
        setFoodListDialogName("");
    }

    public FoodListModel exportFoodListValue(String uri){
        FoodListModel model = new FoodListModel(foodListDialogName, uri);
        model.setUi_type(uiType);
        return model;
    }

    @Bindable
    public String getUiType() {
        return "" + uiType;
    }

    public void setUiType(int uiType) {
        this.uiType = uiType;
        notifyPropertyChanged(BR.uiType);
    }

    @Bindable
    public String getFoodListDialogName() {
        return foodListDialogName;
    }

    public void setFoodListDialogName(String foodListDialogName) {
        this.foodListDialogName = foodListDialogName;
        notifyPropertyChanged(BR.foodListDialogName);
    }

    @Bindable
    public String getFoodListDialogImage() {
        return foodListDialogImage;
    }

    public void setFoodListDialogImage(String foodListDialogImage) {
        this.foodListDialogImage = foodListDialogImage;
        notifyPropertyChanged(BR.foodListDialogImage);
    }

    @BindingAdapter("addFoodListDialogImg")
    public static void loadImage(ImageView view, String url) {
        if (!url.equals("")) Picasso.get().load(url).into(view);
    }

    public void onUITypeSelect( int type){
        setUiType(type);
    }

    @Bindable
    public int getFoodListUploadPercent() {
        return foodListUploadPercent;
    }

    public void setFoodListUploadPercent(int foodListUploadPercent) {
        this.foodListUploadPercent = foodListUploadPercent;
        notifyPropertyChanged(BR.foodListUploadPercent);
    }
}
