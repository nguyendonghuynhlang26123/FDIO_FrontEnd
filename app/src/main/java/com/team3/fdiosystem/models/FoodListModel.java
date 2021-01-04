package com.team3.fdiosystem.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FoodListModel {
    @SerializedName("_id")
    @Expose
    private String id;

    @SerializedName("name")
    private String name;

    @SerializedName("listId")
    @Expose(deserialize = false)
    private String[] foodIdList;

    @SerializedName("listFood")
    @Expose(serialize = false)
    private FoodModel[] foodList;

    @SerializedName("thumbnail")
    private String thumbnail;

    @SerializedName("ui_type")
    private int ui_type;

    public FoodListModel(String name, String thumbnail) {
        this.id="";
        this.name = name;
        this.foodIdList = new String[]{};
        this.foodList = new FoodModel[]{};
        this.thumbnail = thumbnail;
    }

    public FoodListModel(String id, String name, String[] foodIdList, FoodModel[] foodList, String thumbnail, int ui_type) {
        this.id = id;
        this.name = name;
        this.foodIdList = foodIdList;
        this.foodList = foodList;
        this.thumbnail = thumbnail;
        this.ui_type = ui_type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getFoodIdList() {
        return foodIdList;
    }

    public void setFoodIdList(String[] foodIdList) {
        this.foodIdList = foodIdList;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public FoodModel[] getFoodList() {
        return foodList;
    }

    public void setFoodList(FoodModel[] foodList) {
        this.foodList = foodList;
    }

    public int getUi_type() {
        return ui_type;
    }

    public void setUi_type(int ui_type) {
        this.ui_type = ui_type;
    }
}
