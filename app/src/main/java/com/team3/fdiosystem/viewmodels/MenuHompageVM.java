package com.team3.fdiosystem.viewmodels;

import android.app.Activity;
import android.util.Log;
import android.view.View;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.google.gson.Gson;
import com.team3.fdiosystem.BR;
import com.team3.fdiosystem.models.FoodListModel;
import com.team3.fdiosystem.models.FoodModel;
import com.team3.fdiosystem.models.Store;
import com.team3.fdiosystem.models.ui.UI_FoodListItem;
import com.team3.fdiosystem.repositories.services.FoodListService;
import com.team3.fdiosystem.viewmodels.adapters.FoodListAdapter;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MenuHompageVM extends BaseObservable {
    int isLoading;

    FoodListAdapter menuAdapter;
    ArrayList<UI_FoodListItem> menu;
    FoodListService foodListService;

    public MenuHompageVM(Activity c) {
        menu = new ArrayList<>();
        this.menuAdapter = new FoodListAdapter(c,menu);
        isLoading = View.VISIBLE;

        foodListService = new FoodListService();

        initStore();
    }

    public void initStore(){
        foodListService.getFoodLists().enqueue(new Callback<FoodListModel[]>() {
            @Override
            public void onResponse(Call<FoodListModel[]> call, Response<FoodListModel[]> response) {
                if(!response.isSuccessful()){
                    Log.d("LONG", "" + response.code());
                    return;
                }

                FoodListModel[] data = response.body();
                Store.get_instance().setMenu(new ArrayList<>(Arrays.asList(data)));
                for (int i = 0; i < data.length; i++) {
                    //Fix retrofit bug in serializing json:
                    for (int j = 0; j < data[i].getFoodList().length; j++) {
                        data[i].getFoodList()[j].setId(data[i].getFoodIdList()[j]);
                    }

                    //Add to adapter
                    UI_FoodListItem asymItem = new UI_FoodListItem(i, data[i]);
                    menuAdapter.add(asymItem);
                }
                menuAdapter.notifyDataSetChanged();
                setLoading(View.GONE);

            }

            @Override
            public void onFailure(Call<FoodListModel[]> call, Throwable t) {
                //TODO: Handle Failure
            }
        });
    }

    public FoodListAdapter getMenuAdapter() {
        return menuAdapter;
    }

    @Bindable
    public int getIsLoading() {
        return isLoading;
    }

    public void setLoading(int loading) {
        isLoading = loading;
        notifyPropertyChanged(BR.isLoading);
    }

    public String getFoodListId(int index){
        return menu.get(index).getId();
    }
}
