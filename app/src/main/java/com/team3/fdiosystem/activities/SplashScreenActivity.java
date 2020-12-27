package com.team3.fdiosystem.activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.team3.fdiosystem.R;
import com.team3.fdiosystem.models.FoodListModel;
import com.team3.fdiosystem.models.Store;
import com.team3.fdiosystem.models.ui.UI_FoodListItem;
import com.team3.fdiosystem.repositories.services.FoodListService;
import com.team3.fdiosystem.repositories.services.MyFirebaseService;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashScreenActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.setContentView(R.layout.activity_splashscreen);

        Animation leftAnim = AnimationUtils.loadAnimation(this, R.anim.translate_right);
        Animation rightAnim = AnimationUtils.loadAnimation(this, R.anim.translate_left);
        Animation midAnim = AnimationUtils.loadAnimation(this, R.anim.translate_down);

        findViewById(R.id.splash_img).setAnimation(leftAnim);
        findViewById(R.id.logo_mid).setAnimation(midAnim);
        findViewById(R.id.logo_right).setAnimation(rightAnim);

        new Handler().postDelayed((Runnable) () -> {
            FoodListService foodListService = new FoodListService();
            foodListService.getFoodLists().enqueue(new Callback<FoodListModel[]>() {
                @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                @Override
                public void onResponse(Call<FoodListModel[]> call, Response<FoodListModel[]> response) {
                    Log.d("LONG", "" + response.code());
                    if(!response.isSuccessful()){
                        Log.d("LONG", "" + response.code());
                        return;
                    }

                    //Init data from Store
                    FoodListModel[] data = response.body();
                    for (int i = 0; i < data.length; i++) {
                        //Fix retrofit bug in serializing json:
                        for (int j = 0; j < data[i].getFoodList().length; j++) {
                            data[i].getFoodList()[j].setId(data[i].getFoodIdList()[j]);
                        }
                    }
                    Store.get_instance().setMenu(new ArrayList<>(Arrays.asList(data)));
                    MyFirebaseService.getTokenFromCloud(SplashScreenActivity.this );

                    Intent h = new Intent(SplashScreenActivity.this, MenuHomepage.class);
                    startActivity(h);
                    finish();
                }

                @Override
                public void onFailure(Call<FoodListModel[]> call, Throwable t) {
                    //TODO: Handle Failure
                }
            });
        },1000);
    }
}
