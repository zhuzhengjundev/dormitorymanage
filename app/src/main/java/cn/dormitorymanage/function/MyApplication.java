package cn.dormitorymanage.function;


import android.app.Application;
import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class MyApplication extends Application {

    public static RequestQueue queue;
    public static Context mContext;

    public static String ipLocation = "192.168.5.22:8181";

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        queue = Volley.newRequestQueue(getApplicationContext());
    }
}