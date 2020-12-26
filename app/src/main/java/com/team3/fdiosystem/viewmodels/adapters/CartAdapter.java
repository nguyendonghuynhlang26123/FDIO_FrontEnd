package com.team3.fdiosystem.viewmodels.adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.team3.fdiosystem.R;
import com.team3.fdiosystem.databinding.CartItemViewBinding;
import com.team3.fdiosystem.models.CartItem;
import com.team3.fdiosystem.viewmodels.CartItemVM;
import com.team3.fdiosystem.viewmodels.Event;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {
    private Context context;
    private ArrayList<CartItemVM> data;
    private Event requestRefresh;

    public CartAdapter(Context context, ArrayList<CartItemVM> data) {
        this.context = context;
        this.data = data;
        this.requestRefresh = null;
    }

    public void setRequestRefresh(Event requestRefresh) {
        this.requestRefresh = requestRefresh;

        for (int i = 0; i < data.size(); i++) {
            data.get(i).setRequestRefresh(requestRefresh);
        }
    }

    public ArrayList<CartItemVM> getData() {
        return data;
    }

    public void setData(ArrayList<CartItemVM> data) {
        this.data = data;
        if(requestRefresh != null) for (int i = 0; i < data.size(); i++) {
            data.get(i).setRequestRefresh(requestRefresh);
        }
        this.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CartItemViewBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.cart_item_view, parent, false);

        return new CartViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        CartItemVM item = data.get(position);

        holder.binding.setVm(item);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class CartViewHolder extends RecyclerView.ViewHolder {
        CartItemViewBinding binding;

        public CartViewHolder(@NonNull CartItemViewBinding itemViewBinding) {
            super(itemViewBinding.getRoot());

            binding = itemViewBinding;
        }
    }
}