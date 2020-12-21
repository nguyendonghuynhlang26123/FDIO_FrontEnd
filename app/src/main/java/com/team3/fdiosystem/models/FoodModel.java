package com.team3.fdiosystem.models;

import com.google.gson.annotations.SerializedName;

public class FoodModel {
    @SerializedName("_id")
    transient private String id;

    @SerializedName("name")
    private String name;

    @SerializedName("thumbnail")
    private String thumbnail;

    @SerializedName("description")
    private String description;

    @SerializedName("type")
    private String type;

    @SerializedName("price")
    private String price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public FoodModel(String name, String thumbnail, String description, String type, String price) {
        this.name = name;
        this.thumbnail = thumbnail;
        this.description = description;
        this.type = type;
        this.price = price;
    }
}
