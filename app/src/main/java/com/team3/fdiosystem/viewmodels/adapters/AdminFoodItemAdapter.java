package com.team3.fdiosystem.viewmodels.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.team3.fdiosystem.R;
import com.team3.fdiosystem.activities.FoodDetailActivity;
import com.team3.fdiosystem.activities.FoodDialog;
import com.team3.fdiosystem.activities.MenuModifyDialog;
import com.team3.fdiosystem.databinding.RecyclerItemBinding;
import com.team3.fdiosystem.viewmodels.FoodItemVM;

import java.util.List;

public class AdminFoodItemAdapter extends RecyclerView.Adapter<AdminFoodItemAdapter.AdminFoodItemViewHolder> {
    private Context context;
    private List<FoodItemVM> items;
    private FragmentManager fragmentManager;
    private String foodListId;

    public AdminFoodItemAdapter(Context context, String foodListId, List<FoodItemVM> items, FragmentManager fragmentManager) {
        this.context = context;
        setItems(items);
        this.foodListId = foodListId;
        this.fragmentManager = fragmentManager;
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
    public AdminFoodItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerItemBinding itemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.recycler_item, parent, false);
        AdminFoodItemAdapter.AdminFoodItemViewHolder viewHolder = new AdminFoodItemAdapter.AdminFoodItemViewHolder(itemBinding);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdminFoodItemViewHolder holder, int position) {
        FoodItemVM item = items.get(position);

        holder.itemBinding.setVm(item);
        holder.itemBinding.actionBtn.setText("DELETE");
        holder.itemBinding.getRoot().setOnClickListener(v -> {
            FragmentManager fragmentManager = this.fragmentManager;
            DialogFragment dialog = new FoodDialog(foodListId, item.getModel());
            dialog.show(fragmentManager,"FoodModifier");
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    public static class AdminFoodItemViewHolder extends RecyclerView.ViewHolder {
        RecyclerItemBinding itemBinding;

        public AdminFoodItemViewHolder(@NonNull RecyclerItemBinding itemView ) {
            super(itemView.getRoot());
            itemBinding = itemView;
        }
    }
}
