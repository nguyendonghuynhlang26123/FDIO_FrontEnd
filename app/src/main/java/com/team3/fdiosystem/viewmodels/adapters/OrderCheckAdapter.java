package com.team3.fdiosystem.viewmodels.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.team3.fdiosystem.R;
import com.team3.fdiosystem.databinding.ActivityOrderCheckBinding;
import com.team3.fdiosystem.databinding.RecyclerOrderedItemBinding;
import com.team3.fdiosystem.viewmodels.OrderCheckItemVM;

import java.util.ArrayList;

public class OrderCheckAdapter extends RecyclerView.Adapter<OrderCheckAdapter.OrderCheckViewHolder> {
    private Context ctx;
    private ArrayList<OrderCheckItemVM>  vms;

    public OrderCheckAdapter(Context ctx, ArrayList<OrderCheckItemVM> vms) {
        this.ctx = ctx;
        this.vms = vms;
    }

    public ArrayList<OrderCheckItemVM> getVms() {
        return vms;
    }

    public void setVms(ArrayList<OrderCheckItemVM> vms) {
        this.vms = vms;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public OrderCheckViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerOrderedItemBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.recycler_ordered_item, parent, false);
        return new OrderCheckViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderCheckViewHolder holder, int position) {
        OrderCheckItemVM vm = vms.get(position);
        holder.binding.setVm(vm);
    }

    @Override
    public int getItemCount() {
        return vms.size();
    }

    public class OrderCheckViewHolder extends RecyclerView.ViewHolder{
        RecyclerOrderedItemBinding binding;
        public OrderCheckViewHolder(@NonNull RecyclerOrderedItemBinding viewBinding) {
            super(viewBinding.getRoot());
            binding = viewBinding;
        }
    }
}
