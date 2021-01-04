package com.team3.fdiosystem;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.text.NumberFormat;
import java.util.Locale;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class Utils {
    public static final String CHAT_REPO = "CHAT_REPOSITORY";

    //public static final String BACK_END_API_PATH = "http://192.168.43.105:8002/";
    //public static final String BACK_END_API_PATH = "http://10.0.2.2:8002/";
    //public static final String BACK_END_API_PATH = "http://192.168.100.9:8002/";
    public static final String BACK_END_API_PATH = "https://nmcnpm-team3.herokuapp.com/";

    public static final String TOKEN = "FIRESTORE_TOKEN";
    public static final String TABLE_ID = "TABLE_ID";

    public static final int UI_FOODLIST_TYPE_S = 0;
    public static final int UI_FOODLIST_TYPE_M = 1;
    public static final int UI_FOODLIST_TYPE_L = 2;
    public static final NumberFormat format = NumberFormat.getCurrencyInstance(Locale.forLanguageTag("vi-VN"));

    public static final int ADMIN_MODE = 0;
    public static final int GUEST_MODE = 1;

    public static final String BROADCAST_ORDERCHECK = "ORDER_CHECK";

    public static final String STATUS_WAITING = "WAITING";
    public static final String STATUS_PROCESSING = "PROCESSING";
    public static final String STATUS_DONE = "DONE";
    public static final String STATUS_DENIED = "DENIED";
}
