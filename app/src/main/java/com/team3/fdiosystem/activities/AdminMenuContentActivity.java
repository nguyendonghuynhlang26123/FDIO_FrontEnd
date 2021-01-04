package com.team3.fdiosystem.activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;

import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.team3.fdiosystem.R;
import com.team3.fdiosystem.databinding.ActivityAdminMenuContentBinding;
import com.team3.fdiosystem.models.FoodListModel;
import com.team3.fdiosystem.models.FoodModel;
import com.team3.fdiosystem.models.Store;
import com.team3.fdiosystem.viewmodels.FoodItemVM;
import com.team3.fdiosystem.viewmodels.adapters.AdminFoodItemAdapter;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class AdminMenuContentActivity extends AppCompatActivity implements FoodDialog.FoodDialogListener {
    ActivityAdminMenuContentBinding binding;
    AdminFoodItemAdapter adapter;
    private String foodListId;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_admin_menu_content);
        binding.menuContentAdmin.setLayoutManager(new GridLayoutManager(AdminMenuContentActivity.this, 2, GridLayoutManager.HORIZONTAL, false));

        Intent i = getIntent();
        foodListId = i.getStringExtra("id");

        ArrayList<FoodItemVM> vms = loadDataFromStore();

        binding.addMenuItemFab.setOnClickListener(t -> {
            FragmentManager fragmentManager = getSupportFragmentManager();
            DialogFragment dialog = new FoodDialog(foodListId);
            dialog.show(fragmentManager,"FoodModifier");
        });

        adapter = new AdminFoodItemAdapter(this, foodListId, vms, getSupportFragmentManager());
        binding.menuContentAdmin.setAdapter(adapter);
    }

    @NotNull
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private ArrayList<FoodItemVM> loadDataFromStore() {
        ArrayList<FoodItemVM> vms = new ArrayList<>();
        FoodListModel flist = Store.get_instance().getMenuById(foodListId);

        if (flist != null){
            FoodModel[] foodModels =  flist.getFoodList();

            for (FoodModel foodModel : foodModels) {
                FoodItemVM vm = new FoodItemVM(foodModel);
                vm.getActionBtnCallback().observe(this, id1 -> {
                    Snackbar.make(binding.menuContentAdmin, foodModel.getName() + " is deleted!",
                            Snackbar.LENGTH_LONG).show();
                });
                vms.add(vm);
            }
        }
        return vms;
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onSubmit(String msg) {
        Snackbar.make(this.binding.getRoot(), msg, Snackbar.LENGTH_LONG).show();
        adapter.setItems(loadDataFromStore());
    }

}
