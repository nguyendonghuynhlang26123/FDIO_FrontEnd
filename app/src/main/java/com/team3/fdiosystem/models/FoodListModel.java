package com.team3.fdiosystem.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FoodListModel {
    @SerializedName("_id")
    @Expose (serialize = false)
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

    public FoodListModel(String name, String thumbnail) {
        this.name = name;
        this.foodIdList = new String[]{};
        this.thumbnail = thumbnail;
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
}
