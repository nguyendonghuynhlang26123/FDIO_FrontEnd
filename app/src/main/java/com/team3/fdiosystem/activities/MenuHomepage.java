package com.team3.fdiosystem.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

import com.felipecsl.asymmetricgridview.library.Utils;
import com.felipecsl.asymmetricgridview.library.widget.AsymmetricGridView;
import com.felipecsl.asymmetricgridview.library.widget.AsymmetricGridViewAdapter;
import com.team3.fdiosystem.R;
import com.team3.fdiosystem.models.Item;
import com.team3.fdiosystem.models.Store;
import com.team3.fdiosystem.viewmodels.MenuAdapter;

import java.util.ArrayList;

public class MenuHomepage extends AppCompatActivity {
    ArrayList<Item> menu;
    MenuAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_homepage);
//        RecyclerView menuView = (RecyclerView) findViewById(R.id.menu_recycler);
        AsymmetricGridView menuView = findViewById(R.id.Gridview);
        menuView.setRequestedColumnWidth(Utils.dpToPx(this,120));
        menu = Store.get_instance().items;

//        LinearLayoutManager menuLayout = new LinearLayoutManager(MainActivity.this,LinearLayoutManager.HORIZONTAL,false);
//        menuView.setLayoutManager(menuLayout);
        adapter = new MenuAdapter(this,menu);
        AsymmetricGridViewAdapter menuAdapter = new AsymmetricGridViewAdapter(this, menuView, adapter);
        menuView.setAdapter(menuAdapter);
        menuView.setAllowReordering(true);
        menuView.isAllowReordering();

        menuView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent i = new Intent(MenuHomepage.this,MenuContent.class);
                i.putExtra("type", position);
                startActivity(i);

            }
        });

    }

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
                Intent l = new Intent(this, ManagementHomepage.class);
                MenuHomepage.this.startActivity(l);
                return true;
            case R.id.ordered_check:
                Intent o = new Intent(this, OrderedCheck.class);
                MenuHomepage.this.startActivity(o);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}