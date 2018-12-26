package com.xunji.demo.xunjilocation;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;

import com.locnavi.location.xunjimap.XJLocationSDK;


public class App extends Application  {
    public static Context context;
    public String appKey = "kHlWiWIuH0";
    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        initXJmap();
    }

    private void initXJmap() {
        XJLocationSDK.init(context,appKey);
    }


}
