package com.team3.fdiosystem.models.ui;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.felipecsl.asymmetricgridview.library.model.AsymmetricItem;
import com.team3.fdiosystem.models.FoodListModel;
import com.team3.fdiosystem.Utils;

public class UI_FoodListItem implements AsymmetricItem {
    private int columnSpan;
    private int rowSpan;
    private int position;
    private FoodListModel fModel;

    public UI_FoodListItem(int position, FoodListModel model){
        this.fModel = model;
        this.position = position;
        switch (fModel.getUi_type()){
            case Utils.UI_FOODLIST_TYPE_L:
                columnSpan = 3;
                rowSpan = 4;
                break;
            case Utils.UI_FOODLIST_TYPE_M:
                columnSpan = 2;
                rowSpan = 4;
                break;
            case Utils.UI_FOODLIST_TYPE_S:
                columnSpan = 2;
                rowSpan = 2;
                break;
            default:
                columnSpan = 1;
                rowSpan = 1;
                break;
        }
    }
    public UI_FoodListItem(Parcel in){readFromParcel(in);}

    public String getImg(){
        return fModel.getThumbnail();
    }
    public String getId(){
        return fModel.getId();
    }
    //ASYMMETRIC METHODS
    @Override
    public int getColumnSpan() {
        return columnSpan;
    }

    @Override
    public int getRowSpan() {
        return rowSpan;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
    private void readFromParcel(Parcel in) {
        columnSpan = in.readInt();
        rowSpan = in.readInt();
        position = in.readInt();
    }

    public static final Parcelable.Creator<UI_FoodListItem> CREATOR = new Parcelable.Creator<UI_FoodListItem>() {

        @Override public UI_FoodListItem createFromParcel(@NonNull Parcel in) {
            return new UI_FoodListItem(in);
        }

        @Override @NonNull
        public UI_FoodListItem[] newArray(int size) {
            return new UI_FoodListItem[size];
        }
    };

}
