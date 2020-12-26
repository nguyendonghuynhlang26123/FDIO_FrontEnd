package com.team3.fdiosystem.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.felipecsl.asymmetricgridview.library.Utils;
import com.felipecsl.asymmetricgridview.library.widget.AsymmetricGridView;
import com.felipecsl.asymmetricgridview.library.widget.AsymmetricGridViewAdapter;
import com.team3.fdiosystem.R;
import com.team3.fdiosystem.models.Store;
import com.team3.fdiosystem.viewmodels.MenuHompageVM;

public class MenuHomepage extends AppCompatActivity {
    MenuHompageVM vm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_homepage);

        vm = new MenuHompageVM(this);

        AsymmetricGridView menuView = findViewById(R.id.Gridview);
        menuView.setRequestedColumnWidth(Utils.dpToPx(this,120));
        AsymmetricGridViewAdapter menuAdapter = new AsymmetricGridViewAdapter(this, menuView, vm.getMenuAdapter());
        menuView.setAdapter(menuAdapter);
        menuView.setAllowReordering(true);
        menuView.isAllowReordering();

        menuView.setOnItemClickListener((parent, view, position, id) -> {
            Intent i = new Intent(MenuHomepage.this,MenuContent.class);
            i.putExtra("id", vm.getFoodListId(position));
            startActivity(i);
        });

    }

    //Menu item clicked
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.navbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.cart:
                Intent i = new Intent(this,CartActivity.class);
                MenuHomepage.this.startActivity(i);
                return true;
            case R.id.login:
                Intent l = new Intent(this, LoginActivity.class);
                MenuHomepage.this.startActivity(l);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}