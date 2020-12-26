package com.team3.fdiosystem.viewmodels.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.team3.fdiosystem.R;
import com.team3.fdiosystem.databinding.RecyclerItemBinding;
import com.team3.fdiosystem.viewmodels.FoodItemVM;

import java.util.ArrayList;
import java.util.List;

public class FoodItemAdapter extends RecyclerView.Adapter<FoodItemAdapter.FoodItemViewHolder> {
    private Context context;
    private List<FoodItemVM> items;

    public FoodItemAdapter(Context context, List<FoodItemVM> items) {
        this.context = context;
        this.items = items;
    }

    public List<FoodItemVM> getItems() {
        return items;
    }

    public void setItems(List<FoodItemVM> items) {
        this.items = items;
        this.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FoodItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerItemBinding itemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.recycler_item, parent, false);
        FoodItemViewHolder viewHolder = new FoodItemViewHolder(itemBinding);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FoodItemViewHolder holder, int position) {
        FoodItemVM item = items.get(position);

        holder.itemBinding.setVm(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class FoodItemViewHolder extends RecyclerView.ViewHolder {
        RecyclerItemBinding itemBinding;

        public FoodItemViewHolder(@NonNull RecyclerItemBinding itemView) {
            super(itemView.getRoot());
            itemBinding = itemView;
        }
    }
}
