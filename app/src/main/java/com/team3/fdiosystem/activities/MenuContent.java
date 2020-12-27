package com.team3.fdiosystem.activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;

import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.team3.fdiosystem.R;
import com.team3.fdiosystem.databinding.ActivityMenuContentBinding;
import com.team3.fdiosystem.models.FoodListModel;
import com.team3.fdiosystem.models.FoodModel;
import com.team3.fdiosystem.models.Store;
import com.team3.fdiosystem.repositories.services.FoodListService;
import com.team3.fdiosystem.viewmodels.Event;
import com.team3.fdiosystem.viewmodels.FoodItemVM;
import com.team3.fdiosystem.viewmodels.adapters.FoodItemAdapter;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MenuContent extends AppCompatActivity {
    ActivityMenuContentBinding binding;
    FoodItemAdapter adapter;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_menu_content);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_menu_content);
        binding.recyclerview.setLayoutManager(new GridLayoutManager(MenuContent.this, 2, GridLayoutManager.HORIZONTAL, false));

        Intent i = getIntent();
        String id = i.getStringExtra("id");

        ArrayList<FoodItemVM> vms = new ArrayList<>();
        FoodListModel flist = Store.get_instance().getMenuById(id);
        if (flist != null){
            FoodModel[] foodModels =  flist.getFoodList();

            for (FoodModel foodModel : foodModels) {
                FoodItemVM vm = new FoodItemVM(foodModel.getId(), foodModel.getPrice(),
                        foodModel.getName(), foodModel.getThumbnail());
                vm.getAddBtnCallBack().observe(this, new Observer<String>() {
                    @Override
                    public void onChanged(String s) {
                        Snackbar.make(binding.menuContentMain, s + " is added to cart!",
                                Snackbar.LENGTH_LONG).show();
                    }
                });
                vms.add(vm);
            }
        }

        adapter = new FoodItemAdapter(this, vms);
        binding.recyclerview.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.navbar, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(android.view.MenuItem item) {
        switch (item.getItemId()) {
            case R.id.cart:
                Intent i = new Intent(this,CartActivity.class);
                MenuContent.this.startActivity(i);
                return true;
            case R.id.login:
                Intent l = new Intent(this, LoginActivity.class);
                this.startActivity(l);
                return true;
            case R.id.ordered_check:
                Intent o = new Intent(this, OrderedCheckActivity.class);
                MenuContent.this.startActivity(o);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }


}