package com.team3.fdiosystem.viewmodels;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;

import com.team3.fdiosystem.models.Item;

import java.util.ArrayList;
import java.util.List;

public class ItemDiffCallback extends DiffUtil.Callback {
    List<Object> oldList;
    List<Object> newList;
    public ItemDiffCallback(ArrayList<Object> oldList, ArrayList<Object> newList){
        this.oldList = oldList;
        this.newList = newList;
    }
    @Override
    public int getOldListSize() {
        return oldList.size();
    }

    @Override
    public int getNewListSize() {
        return newList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        if(oldItemPosition==0||newItemPosition==0) return true;
        else {
            Item oldItem = (Item) oldList.get(oldItemPosition);
            Item newItem = (Item) newList.get(newItemPosition);
            return oldItem.name.equals(newItem.name);
        }
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        if(oldItemPosition==0||newItemPosition==0){
            return true;
        } else{
            final Item oldItem = (Item)oldList.get(oldItemPosition);
            final Item newItem = (Item)newList.get(newItemPosition);
            return oldItem.name.equals(newItem.name);
        }
    }
    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        // Implement method if you're going to use ItemAnimator
        return super.getChangePayload(oldItemPosition, newItemPosition);
    }
}
