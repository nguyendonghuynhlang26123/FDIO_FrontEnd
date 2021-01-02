package com.team3.fdiosystem.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.team3.fdiosystem.R;

public class ManagementHomepageActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_management_homepage);

        Button btn = findViewById(R.id.promo_manage);
        btn.setOnClickListener(v -> {
        });
        findViewById(R.id.menu_manage).setOnClickListener(v -> {
                Intent i = new Intent(ManagementHomepageActivity.this, AdminMenuActivity.class);
                ManagementHomepageActivity.this.startActivity(i);
        });
        findViewById(R.id.history_manage).setOnClickListener(v -> {

        });
    }

    //Disable back button
    @Override
    public void onBackPressed() {
        return;
    }
}
