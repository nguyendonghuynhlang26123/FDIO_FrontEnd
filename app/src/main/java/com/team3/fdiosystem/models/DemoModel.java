package com.team3.fdiosystem.models;

import com.google.gson.annotations.SerializedName;

public class DemoModel {
    @SerializedName("text")
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
