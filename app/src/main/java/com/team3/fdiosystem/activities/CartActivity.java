package com.team3.fdiosystem.activities;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.team3.fdiosystem.R;
import com.team3.fdiosystem.models.CartItem;
import com.team3.fdiosystem.models.Promotion;
import com.team3.fdiosystem.models.Store;
import com.team3.fdiosystem.viewmodels.CartAdapter;
import com.team3.fdiosystem.viewmodels.CheckoutDialog;
import com.team3.fdiosystem.viewmodels.PromotionItemAdapter;

import java.util.ArrayList;

public class CartActivity extends FragmentActivity {

    //    private static ArrayList<String> current_promos = new ArrayList<>();
    int total_price=0;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        ArrayList<CartItem> cart = Store.get_instance().cart.cart;
        setPrice(cart);
        CartAdapter adapter = new CartAdapter(this, cart);
        LinearLayoutManager cartLayout = new LinearLayoutManager(CartActivity.this, LinearLayoutManager.VERTICAL, false);
        RecyclerView view = findViewById(R.id.cart_recycler_view);
        view.setAdapter(adapter);
        view.setLayoutManager(cartLayout);
        Button ckout = findViewById(R.id.ckoutBtn);
        ckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment dialog = new CheckoutDialog(total_price, Store.get_instance().cart.promotions);
                dialog.show(getSupportFragmentManager(),"Checkout");
            }
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void setPrice(ArrayList<CartItem> cart){
        cart.forEach(item->{
            total_price+=item.ckout_price;
        });
    }



}