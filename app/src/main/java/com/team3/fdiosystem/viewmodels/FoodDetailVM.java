package com.team3.fdiosystem.viewmodels;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.BindingAdapter;
import androidx.lifecycle.MutableLiveData;

import com.squareup.picasso.Picasso;
import com.team3.fdiosystem.BR;
import com.team3.fdiosystem.models.CartItem;
import com.team3.fdiosystem.models.FoodModel;
import com.team3.fdiosystem.models.Store;

public class FoodDetailVM extends BaseObservable {
    private FoodModel foodModel;
    private int foodQuantity;
    private MutableLiveData<String> callback;

    public FoodDetailVM(FoodModel model) {
        foodModel = model;
        this.callback = new MutableLiveData<>();

        setFoodQuantity(1);
        notifyPropertyChanged(BR.foodImgUrl);
        notifyPropertyChanged(BR.foodName);
        notifyPropertyChanged(BR.foodDescription);
    }

    public MutableLiveData<String> getCallback() {
        return callback;
    }

    @BindingAdapter("imgFoodDetail")
    public static void loadImage(ImageView view, String url) {
        Picasso.get().load(url).into(view);
    }

    @Bindable
    public String getFoodImgUrl() {
        return foodModel.getThumbnail();
    }

    @Bindable
    public String getFoodName() {
        return foodModel.getName();
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
        return this.foodModel.getDescription();
    }

    public void addBtnHandler(){
        if (foodModel != null){
            CartItem item = new CartItem(foodModel,foodQuantity);
            Log.i("ID", item.getId());
            Store.get_instance().addToCart(item);
        }
        if (callback!= null) {
            String msg = this.foodModel.getName() + (foodQuantity > 1 ? "x" + getFoodQuantity() : "");
            callback.setValue(msg);
        }
    }

    public void increaseQuantity() {
        setFoodQuantity(foodQuantity+1);
    }

    public void decreaseQuantity() {
        setFoodQuantity(foodQuantity-1);
    }
}
