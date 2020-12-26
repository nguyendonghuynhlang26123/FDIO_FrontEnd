package com.team3.fdiosystem.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.databinding.DataBindingUtil;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.gson.Gson;
import com.team3.fdiosystem.R;
import com.team3.fdiosystem.Utils;
import com.team3.fdiosystem.databinding.ActivityCheckoutDialogBinding;
import com.team3.fdiosystem.models.Cart;
import com.team3.fdiosystem.models.CartItem;
import com.team3.fdiosystem.models.OrderItemModel;
import com.team3.fdiosystem.models.OrderModel;
import com.team3.fdiosystem.models.ResponseModel;
import com.team3.fdiosystem.models.Store;
import com.team3.fdiosystem.repositories.services.LocalStorage;
import com.team3.fdiosystem.repositories.services.OrderService;
import com.team3.fdiosystem.viewmodels.Event;


import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CheckoutDialog extends BottomSheetDialogFragment {
    Cart cart;
    String token;
    int price;
    String note;
    Event callback;
    ActivityCheckoutDialogBinding binding;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        binding =  DataBindingUtil.inflate(inflater, R.layout.activity_checkout_dialog, container, false);
        View view =binding.getRoot();
        setFullSize(view);


        binding.orderPrice.append(Utils.format.format(price));
        binding.checkoutProgressbar.setVisibility(View.GONE);
        binding.orderNote.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Store.get_instance().setNote(String.valueOf(s));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        binding.proceedOrderBtn.setOnClickListener(t ->{
            OrderModel order = getOrderFromCart();
            if (order.getList_order_item().length == 0) return;

            binding.checkoutProgressbar.setVisibility(View.VISIBLE);

            OrderService orderService = new OrderService();
            orderService.createOrder(order).enqueue(new Callback<ResponseModel>() {
                @Override
                public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                    binding.checkoutProgressbar.setVisibility(View.GONE);

                    Store.get_instance().appendOrderedList(new ArrayList<>(Arrays.asList(order.getList_order_item())));

                    close();
                }

                @Override
                public void onFailure(Call<ResponseModel> call, Throwable t) {
                    Log.i("ORDER_", t.getMessage());
                }
            });
        });
        return view;
    }

    private void close(){
        binding.orderPrice.setText("");
        binding.orderNote.setText("");
        callback.trigger();
//        this.dismiss();
        Intent o = new Intent(getActivity(), OrderedCheckActivity.class);
        startActivity(o);
    }

    //Set bottom sheet full size
    private void setFullSize(View view) {
        view.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (Build.VERSION.SDK_INT < 16) {
                    view.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                } else {
                    view.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
                BottomSheetDialog dialog = (BottomSheetDialog) getDialog();
                FrameLayout bottomSheet = (FrameLayout)
                        dialog.findViewById(R.id.design_bottom_sheet);
                BottomSheetBehavior behavior = BottomSheetBehavior.from(bottomSheet);
                behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                behavior.setPeekHeight(0); // Remove this line to hide a dark background if you manually hide the dialog.
            }
        });
    }
    
    private OrderModel getOrderFromCart() {
        OrderItemModel[] arr = new OrderItemModel[this.cart.getItems().size()];
        for (int i = 0; i < this.cart.getItems().size(); i++) {
            CartItem item = this.cart.getItems().get(i);
            arr[i]= new OrderItemModel(item.getId(), item.getQuantity());;
        }

        return new OrderModel("None", "None",
                this.cart.getNote(), "10", this.token, arr);
    }
    
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CheckoutDialog(Context ctx, int price, Event callback){
        this.cart = Store.get_instance().getCart();
        this.token = LocalStorage.getData(ctx, Utils.TOKEN);
        this.price = price;

        this.callback = callback;
    }

}