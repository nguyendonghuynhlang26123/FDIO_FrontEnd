package com.team3.fdiosystem.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;

import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.team3.fdiosystem.R;
import com.team3.fdiosystem.databinding.ActivityFoodDetailBinding;
import com.team3.fdiosystem.models.FoodModel;
import com.team3.fdiosystem.models.Store;
import com.team3.fdiosystem.viewmodels.FoodDetailVM;

public class FoodDetailActivity extends AppCompatActivity {
    ActivityFoodDetailBinding binding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_food_detail);

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");

        FoodModel model = Store.get_instance().getModelById(id);
        Log.i("FOOD_DETAIL", new Gson().toJson(model));

        FoodDetailVM vm = new FoodDetailVM(model);
        binding.setVm(vm);
        vm.getCallback().observe(this, s -> {
            Log.i("OBSERVE", s);
            Snackbar.make(binding.foodDetailContent, s + " is added into your cart!", Snackbar.LENGTH_LONG).show();
        });
    }
}
