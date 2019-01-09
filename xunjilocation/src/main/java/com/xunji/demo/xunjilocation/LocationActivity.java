package com.xunji.demo.xunjilocation;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.locnavi.location.xunjimap.XJClient;
//import com.locnavi.location.xunjimap.XJLocation;
import com.locnavi.location.xunjimap.XJLocation;
import com.locnavi.location.xunjimap.XJLocationListener;
//import com.locnavi.location.xunjimap.e;
import com.locnavi.location.xunjimap.utils.L;

import java.util.logging.Logger;


public class LocationActivity extends AppCompatActivity {


    private XJClient xjClient;
    private EditText edTextTargetId;
    private Button btnSetTarget;
    private String targetId;
    public String mapId = "paF0GFDyao";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        //没有携带用户id
        //如果有用户id ,请用下面的构造方法
        xjClient = new XJClient(LocationActivity.this, mapId);
        xjClient.registerLocationListener(new XJLocationListener() {
            @Override
            public void onReceiveLocation(XJLocation xjLocation) {
                if (xjLocation == null) {
                    Toast.makeText(getApplicationContext(), "定位失败", Toast.LENGTH_SHORT).show();
                    com.orhanobut.logger.Logger.e("dddd xjLocationLocation","定位失败");
                    return;
                }


//                com.orhanobut.logger.Logger.e("dddd xjLocationLocation",xjLocation.isInThisMap() + "" + xjClient.getNearestLocationRegion());
                //定位位置是否在map中
                Toast.makeText(getApplicationContext(), xjLocation.isInThisMap() + "" + xjLocation.getNearLocationRegion(), Toast.LENGTH_SHORT).show();
//                xjLocation.
            }
        });
    }

    public void startLocation(View view) {
        xjClient.start();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        xjClient.stop();
    }
}
