package com.android.wrf_mac.android_service;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private MyService.DownloadBinder downloadBinder;
    private boolean isBind = false;

    private ServiceConnection connection = new ServiceConnection() {

        //在活动和服务绑定时调用
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.i("MyService","onServiceConnected");

            isBind = true;

            downloadBinder = (MyService.DownloadBinder)iBinder;
            downloadBinder.startDownload();
            downloadBinder.getProgress();
        }

        //程序异常活动和服务解绑时调用，正常解绑不会调用

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.i("MyService","onServiceDisconnected");
            isBind = false;

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_startService).setOnClickListener(this);
        findViewById(R.id.btn_stopService).setOnClickListener(this);
        findViewById(R.id.btn_bindService).setOnClickListener(this);
        findViewById(R.id.btn_unbindService).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_startService:
                Intent startIntent = new Intent(this, MyService.class);
                startService(startIntent);
            break;

            case R.id.btn_stopService:
                Intent stopIntent = new Intent(this, MyService.class);
                stopService(stopIntent);
                break;

            case R.id.btn_bindService:
                Intent bindIntent = new Intent(this, MyService.class);
                bindService(bindIntent,connection,BIND_AUTO_CREATE);

                break;

            case R.id.btn_unbindService:
                if (isBind) {
                    unbindService(connection);
                    isBind = false;
                }

                break;

        }
    }
}
