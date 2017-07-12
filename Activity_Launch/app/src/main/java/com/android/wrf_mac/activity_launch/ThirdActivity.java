package com.android.wrf_mac.activity_launch;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class ThirdActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        findViewById(R.id.btn_launchMA).setOnClickListener(this);
        findViewById(R.id.btn_launchSA).setOnClickListener(this);
        findViewById(R.id.btn_launchTA).setOnClickListener(this);

        TextView textView = (TextView) findViewById(R.id.text_view);
        textView.setText("Task Id:" + getTaskId() + "\nsActivity Id:" + this.toString());
    }

    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.d("ThirdActivity", "onNewIntent");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_launchMA:
                startActivity(new Intent(ThirdActivity.this, MainActivity.class));
                break;

            case R.id.btn_launchSA:
                startActivity(new Intent(ThirdActivity.this, SecondActivity.class));
                break;

            case R.id.btn_launchTA:
                startActivity(new Intent(ThirdActivity.this, ThirdActivity.class));
                break;

        }
    }
}
