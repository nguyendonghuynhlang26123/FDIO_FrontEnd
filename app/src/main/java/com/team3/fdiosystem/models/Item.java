package com.team3.fdiosystem.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.felipecsl.asymmetricgridview.library.model.AsymmetricItem;

public class Item implements AsymmetricItem {
    private int columnSpan;
    private int rowSpan;
    private int position;
    public int img;
    public String name;
    public Item(int col, int row, int pos, int i){
        columnSpan= col;
        rowSpan = row;
        position = pos;
        img=i;

        name="Item name";
    }
    public Item(Parcel in){readFromParcel(in);}
    @Override
    public int getColumnSpan() {
        return columnSpan;
    }

    @Override
    public int getRowSpan() {
        return rowSpan;
    }

    public int getPosition() {
        return position;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    private void readFromParcel(Parcel in) {
        columnSpan = in.readInt();
        rowSpan = in.readInt();
        position = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(columnSpan);
        dest.writeInt(rowSpan);
        dest.writeInt(position);
    }

    public static final Parcelable.Creator<Item> CREATOR = new Parcelable.Creator<Item>() {

        @Override public Item createFromParcel(@NonNull Parcel in) {
            return new Item(in);
        }

        @Override @NonNull
        public Item[] newArray(int size) {
            return new Item[size];
        }
    };

}
