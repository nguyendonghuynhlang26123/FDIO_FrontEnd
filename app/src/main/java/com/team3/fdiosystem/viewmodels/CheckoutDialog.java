package com.team3.fdiosystem.viewmodels;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.team3.fdiosystem.R;
import com.team3.fdiosystem.activities.CartActivity;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class CheckoutDialog extends BottomSheetDialogFragment {
    int price;
    ArrayList<String> promos = new ArrayList<>();
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.activity_checkout_dialog,container, false);
        TextView order_price = view.findViewById(R.id.order_price);
        order_price.append(String.valueOf(price));
        LinearLayout myLayout = view.findViewById(R.id.bottom_sheet);
        TextView promo_label = view.findViewById(R.id.order_promo);
        AtomicInteger view_index = new AtomicInteger(myLayout.indexOfChild(promo_label)+1);
        promos.forEach(promo->{
            final CheckBox Extra_view = new CheckBox(this.getContext());
            Extra_view.setPadding(16,0,0,0);
            Extra_view.setChecked(true);
            Extra_view.setText(promo);
            Extra_view.setOnClickListener(v -> {

                myLayout.removeView(Extra_view);
                CartActivity.removePromotion(promo);
            });
            myLayout.addView(Extra_view, view_index.get());
            view_index.getAndIncrement();
        });
        return view;
    }
    public CheckoutDialog(int price, ArrayList<String> promos){
        this.price = price;
        this.promos.addAll(promos);
    }
}
