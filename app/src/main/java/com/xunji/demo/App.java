package com.xunji.demo;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;



import com.locnavi.map.ShareToWechatListener;
import com.locnavi.map.XJMapSDK;


public class App extends Application {
    public static Context context;
    public String appKey = "5KB6kIOwHw";
    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        initXJmap();
    }

    private void initXJmap() {
//        XJMapSDK.init(context,appKey);
        XJMapSDK.init(new XJMapSDK.XJMapConfiguration.Builder(context)
                .appKey(appKey)

                .build());
    }




}
