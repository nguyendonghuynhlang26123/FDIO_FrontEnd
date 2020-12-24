package com.team3.fdiosystem.activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.team3.fdiosystem.R;
import com.team3.fdiosystem.models.Store;

public class MenuItemModifyForm extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_form);
        EditText name = findViewById(R.id.menu_item_edit_name);
        EditText price = findViewById(R.id.menu_item_edit_price);
        EditText img = findViewById(R.id.menu_item_edit_img);
        EditText description = findViewById(R.id.menu_item_edit_des);
        Intent i = getIntent();
        int type = i.getIntExtra("type", 0);
        Button addBtn = findViewById(R.id.add_menu_itemBtn);
        addBtn.setOnClickListener(v -> {
            Store.get_instance().addMenuItem(name.getText().toString(),Integer.parseInt(price.getText().toString()),R.drawable.cfsua_bg,description.getText().toString(),type);
            Intent intent = new Intent(MenuItemModifyForm.this, MenuActivity.class);
            intent.putExtra("type",type);
            startActivity(intent);
        });
    }
}