package com.team3.fdiosystem.viewmodels;

import android.os.Build;
import android.widget.ImageView;

import androidx.annotation.RequiresApi;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.BindingAdapter;
import androidx.lifecycle.MutableLiveData;

import com.squareup.picasso.Picasso;
import com.team3.fdiosystem.BR;
import com.team3.fdiosystem.Utils;
import com.team3.fdiosystem.models.FoodModel;
import com.team3.fdiosystem.models.Store;

public class FoodItemVM extends BaseObservable {
    FoodModel model;
    private MutableLiveData<String> actionBtnCallback;

    public FoodItemVM(FoodModel model) {
        this.model = model;
        this.actionBtnCallback = new MutableLiveData<>();

        notifyPropertyChanged(BR.price);
        notifyPropertyChanged(BR.imgUrl);
        notifyPropertyChanged(BR.name);
    }

    public FoodModel getModel() {
        return model;
    }

    public void setModel(FoodModel model) {
        this.model = model;
        notifyPropertyChanged(BR.price);
        notifyPropertyChanged(BR.imgUrl);
        notifyPropertyChanged(BR.name);
    }

    public String getId() {
        return model.getId();
    }

    public void setId(String id) {
        model.setId(id);
    }

    @BindingAdapter("imgMenuContent")
    public static void loadImage(ImageView view, String url) {
        Picasso.get().load(url).into(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Bindable
    public String getPrice() {
        return Utils.format.format(Integer.parseInt(model.getPrice()));
    }

    public void setPrice(String price) {
        model.setPrice(price);
        notifyPropertyChanged(BR.price);
    }

    @Bindable
    public String getName() {
        return model.getName();
    }

    public void setName(String name) {
        model.setName(name);
        notifyPropertyChanged(BR.name);
    }

    @Bindable
    public String getImgUrl() {
        return model.getThumbnail();
    }

    public void setImgUrl(String imgUrl) {
        model.setThumbnail(imgUrl);
        notifyPropertyChanged(BR.imgUrl);
    }

    public MutableLiveData<String> getActionBtnCallback() {
        return actionBtnCallback;
    }

    public void addItemToCart() {
        if(actionBtnCallback != null)
            actionBtnCallback.setValue(this.model.getId());
    }
}
