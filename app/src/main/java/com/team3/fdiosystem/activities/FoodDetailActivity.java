package com.team3.fdiosystem.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

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
        binding.setVm(new FoodDetailVM(model.getName(), 1, model.getDescription(), model.getThumbnail()));

    }
}
