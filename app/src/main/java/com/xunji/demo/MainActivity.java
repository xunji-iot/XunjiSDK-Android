package com.xunji.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.locnavi.map.XJMapSDK;

public class MainActivity extends AppCompatActivity {
    public String mapId = "3aBi8Pl1oy";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnStartNav = (Button) findViewById(R.id.btnStartNav);
        btnStartNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startUI();
            }
        });

    }

    private void startUI() {
        XJMapSDK.openXJMapActivity(getBaseContext(),mapId );
    }
}
