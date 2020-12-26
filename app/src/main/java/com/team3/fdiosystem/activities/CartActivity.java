package com.team3.fdiosystem.activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import com.team3.fdiosystem.R;
import com.team3.fdiosystem.Utils;
import com.team3.fdiosystem.databinding.ActivityCartBinding;
import com.team3.fdiosystem.models.CartItem;
import com.team3.fdiosystem.models.Store;
import com.team3.fdiosystem.viewmodels.CartItemVM;
import com.team3.fdiosystem.viewmodels.Event;
import com.team3.fdiosystem.viewmodels.adapters.CartAdapter;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {
    private ActivityCartBinding binding;
    private int total_price = 0;
    private CartAdapter adapter ;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_cart);
        binding.cartRecyclerView.setLayoutManager(new LinearLayoutManager(CartActivity.this, LinearLayoutManager.VERTICAL, false));

        //Recycle item trigger events call the activity to re-render the view
        Event refreshEvent = this::loadDataFromStore;
        adapter = new CartAdapter(this, new ArrayList<>());
        adapter.setRequestRefresh(refreshEvent);
        binding.cartRecyclerView.setAdapter(adapter);


        loadDataFromStore();
        binding.ckoutBtn.setOnClickListener(ev->{
            DialogFragment dialog = new CheckoutDialog(CartActivity.this,
                    total_price, onDialogDimissed());
            dialog.show(getSupportFragmentManager(),dialog.getTag());
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void setPrice(ArrayList<CartItem> cart){
        total_price = 0;
        for (CartItem cartItem : cart) {
            total_price += cartItem.getPrice() * cartItem.getQuantity();
        }
        binding.cartPrice.setText(Utils.format.format(total_price));
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public Event onDialogDimissed(){
        return () -> {
            Store.get_instance().resetCart();
            adapter.setData(new ArrayList<>());
            loadDataFromStore();
        };
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void loadDataFromStore (){
        ArrayList<CartItemVM> vms = new ArrayList<>();
        ArrayList<CartItem> items = Store.get_instance().getCart().getItems();
        for (CartItem item : items) {
            vms.add(new CartItemVM(item));
        }

        setPrice(items);
        adapter.setData(vms);

        if (adapter.getItemCount() == 0)
            binding.ckoutBtn.setVisibility(View.GONE);
    }

}