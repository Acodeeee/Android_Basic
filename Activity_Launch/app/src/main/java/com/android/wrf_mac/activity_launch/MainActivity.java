package com.android.wrf_mac.activity_launch;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_launchMA).setOnClickListener(this);
        findViewById(R.id.btn_launchSA).setOnClickListener(this);
        findViewById(R.id.btn_launchTA).setOnClickListener(this);

        TextView textView = (TextView) findViewById(R.id.text_view);
        textView.setText("Task ID:" + getTaskId() + "\n" + "Current Activity:" + this.toString());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.d("MainActivity", "onNewIntent");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("MainActivity", "onDestroy");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_launchMA:
                startActivity(new Intent(MainActivity.this, MainActivity.class));
                break;

            case R.id.btn_launchSA:
                startActivity(new Intent(MainActivity.this, SecondActivity.class));
                break;

            case R.id.btn_launchTA:
                startActivity(new Intent(MainActivity.this, ThirdActivity.class));
                break;
        }
    }
}
