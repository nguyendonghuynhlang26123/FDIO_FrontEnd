package com.team3.fdiosystem.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.team3.fdiosystem.Utils;
import com.team3.fdiosystem.R;
import com.team3.fdiosystem.databinding.ActivityDemoBinding;
import com.team3.fdiosystem.repositories.services.ImageService;
import com.team3.fdiosystem.repositories.services.LocalStorage;
import com.team3.fdiosystem.viewmodels.DemoVM;

public class DemoActivity extends AppCompatActivity {
    static Context context;
    ActivityDemoBinding binding;
    BroadcastReceiver mMessageReceiver;
    DemoVM demo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);

        context = getApplicationContext();

        setupBinding();
        setupBroadcastListener();

        Log.i("TOKEN", LocalStorage.getData(this, Utils.TOKEN));
    }

    private void setupBinding(){
        binding = DataBindingUtil.setContentView(this, R.layout.activity_demo);
        demo = new DemoVM();
        binding.setDemo(demo);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode== ImageService.PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null){
            Uri imgURI = data.getData();
            demo.setImageUrl(imgURI);

            ImageService iService = new ImageService();
            iService.uploadFile(imgURI, getContentResolver()).addOnSuccessListener(taskSnapshot -> {
                Toast.makeText(this, "Upload success", Toast.LENGTH_SHORT).show();
            }).addOnFailureListener(t->{
                Toast.makeText(this, "Upload fail", Toast.LENGTH_SHORT).show();
            });
        }
    }

    private void setupBroadcastListener(){
        mMessageReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                // Extract data included in the Intent
                String t = intent.getStringExtra("value1");
                String t1 = intent.getStringExtra("value2");
                //alert data here
                Log.d("ALERT", t);
                Log.d("ALERT", t1);
            }
        };
    }

    @Override
    public void onResume() {
        super.onResume();
        LocalBroadcastManager.getInstance(this.getContext()).registerReceiver(mMessageReceiver,
                new IntentFilter("myFunction"));
    }

    @Override
    public void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(this.getContext()).unregisterReceiver(mMessageReceiver);
    }


    public static Context getContext() {
        return context;
    }
}