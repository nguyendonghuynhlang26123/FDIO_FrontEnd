package com.team3.fdiosystem.activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.team3.fdiosystem.R;
import com.team3.fdiosystem.repositories.services.LocalStorage;

public class ManagementHomepageActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_management_homepage);

        Button btn = findViewById(R.id.promo_manage);
        btn.setOnClickListener(v -> {
            DialogFragment confirm = new ConfirmDialog("This feature is still being developed!\n" +
                    "Sorry for these inconvenience!", ()->{});
            confirm.show(getSupportFragmentManager(), "Confirm");
        });
        findViewById(R.id.menu_manage).setOnClickListener(v -> {
                Intent i = new Intent(ManagementHomepageActivity.this, AdminMenuActivity.class);
                ManagementHomepageActivity.this.startActivity(i);
        });
        findViewById(R.id.history_manage).setOnClickListener(v -> {
            DialogFragment confirm = new ConfirmDialog("This feature is still being developed!\n" +
                    "Sorry for these inconvenience!", ()->{});
            confirm.show(getSupportFragmentManager(), "Confirm");
        });
    }

    //Disable back button
    @Override
    public void onBackPressed() {
        return;
    }

    //Menu item clicked
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.admin_navbar, menu);
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.admin_setting:
                String tableId = LocalStorage.getData(this, com.team3.fdiosystem.Utils.TABLE_ID);
                DialogFragment dialog = new TableIdDialog(tableId, () -> {
                    Toast.makeText(this, "Update table id successfully", Toast.LENGTH_SHORT).show();
                });
                dialog.show(getSupportFragmentManager(),"TABLEID");
                return true;
            case R.id.admin_logout:
                Intent l = new Intent(this, LoginActivity.class);
                this.startActivity(l);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
