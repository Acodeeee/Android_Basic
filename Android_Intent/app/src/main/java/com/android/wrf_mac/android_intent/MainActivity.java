package com.android.wrf_mac.android_intent;

import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_obv).setOnClickListener(this);
        findViewById(R.id.btn_hide).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()){
            case R.id.btn_obv:
//                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                ComponentName componentName = new ComponentName(this, SecondActivity.class);

                intent.setComponent(componentName);
                startActivity(intent);
                break;

            case R.id.btn_hide:
//                intent.setAction("bcd");
//                intent.addCategory("wrf");
                intent.setAction(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://www.baidu.com"));
                startActivity(intent);

                break;
        }
    }
}
