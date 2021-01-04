package com.team3.fdiosystem.activities;

import android.app.Dialog;
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
import androidx.fragment.app.DialogFragment;

import com.team3.fdiosystem.R;
import com.team3.fdiosystem.Utils;
import com.team3.fdiosystem.models.FoodListModel;
import com.team3.fdiosystem.models.Store;
import com.team3.fdiosystem.models.ui.UI_FoodListItem;
import com.team3.fdiosystem.repositories.services.FoodListService;
import com.team3.fdiosystem.repositories.services.LocalStorage;
import com.team3.fdiosystem.repositories.services.MyFirebaseService;
import com.team3.fdiosystem.viewmodels.Event;

import java.util.ArrayList;
import java.util.Arrays;

import okhttp3.internal.Util;
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

        //LOAD DATA
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
                    String tableId= LocalStorage.getData(SplashScreenActivity.this, Utils.TABLE_ID);
                    if (tableId.equals("")){
                        DialogFragment dialog = new TableIdDialog(tableId, () -> {
                            Intent h = new Intent(SplashScreenActivity.this, MenuHomepage.class);
                            startActivity(h);

                            finish();
                        });
                        dialog.show(getSupportFragmentManager(),"TABLEID");
                    }
                    else {
                        Store.get_instance().setTableId(tableId);
                        Intent h = new Intent(SplashScreenActivity.this, MenuHomepage.class);
                        startActivity(h);
                    }


                    //debug();
                }

                @Override
                public void onFailure(Call<FoodListModel[]> call, Throwable t) {
                    //TODO: Handle Failure
                }
            });
        },1000);
    }

    public void debug(){
        Intent i = new Intent(this, ManagementHomepageActivity.class );
        startActivity(i);
    }
}
