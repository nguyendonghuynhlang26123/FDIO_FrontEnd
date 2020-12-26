package com.team3.fdiosystem.viewmodels;

import android.os.Build;
import android.util.Log;
import android.widget.ImageView;

import androidx.annotation.RequiresApi;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.BindingAdapter;
import androidx.lifecycle.MutableLiveData;

import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.team3.fdiosystem.BR;
import com.team3.fdiosystem.Utils;
import com.team3.fdiosystem.models.CartItem;
import com.team3.fdiosystem.models.FoodListModel;
import com.team3.fdiosystem.models.FoodModel;
import com.team3.fdiosystem.models.Store;

public class FoodItemVM extends BaseObservable {
    private String price;
    private String name;
    private String imgUrl;
    private String id;
    private MutableLiveData<String> addBtnCallBack;

    public FoodItemVM(String id, String price, String name, String imgUrl) {
        this.price = price;
        this.name = name;
        this.imgUrl = imgUrl;
        this.id = id;
        this.addBtnCallBack = new MutableLiveData<>();

        setPrice(price);
        setName(name);
        setImgUrl(imgUrl);
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @BindingAdapter("imgMenuContent")
    public static void loadImage(ImageView view, String url) {
        Picasso.get().load(url).into(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Bindable
    public String getPrice() {
        return Utils.format.format(Integer.parseInt(price));
    }

    public void setPrice(String price) {
        this.price = price;
        notifyPropertyChanged(BR.price);
    }

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }

    @Bindable
    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
        notifyPropertyChanged(BR.imgUrl);
    }

    public MutableLiveData<String> getAddBtnCallBack() {
        return addBtnCallBack;
    }


    public void addItemToCart() {
        FoodModel foodModel = Store.get_instance().getModelById(this.id);

        if (foodModel != null){
            CartItem item = new CartItem(foodModel,1);
            Store.get_instance().addToCart(item);
        }

        if(addBtnCallBack != null)
            addBtnCallBack.setValue(this.name);
    }
}
