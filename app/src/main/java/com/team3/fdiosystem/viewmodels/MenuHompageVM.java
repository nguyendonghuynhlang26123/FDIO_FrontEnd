package com.team3.fdiosystem.viewmodels;

import android.app.Activity;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.team3.fdiosystem.BR;
import com.team3.fdiosystem.models.FoodListModel;
import com.team3.fdiosystem.models.Store;
import com.team3.fdiosystem.models.ui.UI_FoodListItem;
import com.team3.fdiosystem.viewmodels.adapters.FoodListAdapter;

import java.util.ArrayList;

public class MenuHompageVM extends BaseObservable {
    FoodListAdapter menuAdapter;
    ArrayList<UI_FoodListItem> menu;

    public MenuHompageVM(Activity c) {
        menu = new ArrayList<>();
        this.menuAdapter = new FoodListAdapter(c,menu);
        initStore();
    }

    public void initStore(){
        ArrayList<FoodListModel> foodListModels = Store.get_instance().getMenu();
        for (int i = 0; i < foodListModels.size(); i++) {
            UI_FoodListItem asymItem = new UI_FoodListItem(i, foodListModels.get(i));
            menuAdapter.add(asymItem);
        }
        menuAdapter.notifyDataSetChanged();
    }

    public FoodListAdapter getMenuAdapter() {
        return menuAdapter;
    }

    public String getFoodListId(int index){
        return menu.get(index).getId();
    }

}
