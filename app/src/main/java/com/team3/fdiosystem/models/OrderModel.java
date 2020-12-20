package com.team3.fdiosystem.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class OrderModel {
    @SerializedName("_id")
    transient private String id;

    @SerializedName("time_order")
    transient private String time_order;

    @SerializedName("discount")
    private String discount = "QNUBD1NxD25ZKiHZ2xm8";

    @SerializedName("manager")
    private String manager =  "FkET57KxGky6dfJyUqAQ";

    @SerializedName("note")
    private String note = "note";

    @SerializedName("table_id")
    private String table_id = "note";

    @SerializedName("token")
    private String token;

    @SerializedName("list_order_item")
    private OrderItemModel[] list_order_item = null;

    public OrderModel(String discount, String manager, String note, String table_id, String token, OrderItemModel[] list_order_item) {
        this.discount = discount;
        this.manager = manager;
        this.note = note;
        this.table_id = table_id;
        this.token = token;
        this.list_order_item = list_order_item;
    }

    public OrderItemModel[] getList_order_item() {
        return list_order_item;
    }

    public void setList_order_item(OrderItemModel[] list_order_item) {
        this.list_order_item = list_order_item;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getTable_id() {
        return table_id;
    }

    public void setTable_id(String table_id) {
        this.table_id = table_id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTime_order() {
        return time_order;
    }

    public void setTime_order(String time_order) {
        this.time_order = time_order;
    }
}
