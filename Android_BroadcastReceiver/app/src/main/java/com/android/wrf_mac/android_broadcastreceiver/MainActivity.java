package com.android.wrf_mac.android_broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String MY_BROADCAST = "com.android.wrf_mac.android_broadcastreceiver.mybroadcast";
    public static final String LOCAL_BROADCAST = "com.android.wrf_mac.android_broadcastreceiver.localBroadcast";

    private NetworkChangeReceiver networkChangeReceiver;
    private IntentFilter intentFilterOfNet;

    private LocalBroadcastManager localBroadcastManager;

    private LocalReceiver localReceiver;
    private IntentFilter intentFilterOfLocal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        findViewById(R.id.btn_sendBroadcast).setOnClickListener(this);
        findViewById(R.id.btn_sendLocalBroadcast).setOnClickListener(this);



        //实例化IntentFilter并且给intentFilter添加一个action
        intentFilterOfNet = new IntentFilter();
        intentFilterOfNet.addAction("android.net.conn.CONNECTIVITY_CHANGE");

        //实例化广播接收器
        networkChangeReceiver = new NetworkChangeReceiver();

        registerReceiver(networkChangeReceiver, intentFilterOfNet);

        //注册本地广播
        localBroadcastManager = LocalBroadcastManager.getInstance(this);

        intentFilterOfLocal = new IntentFilter();
        intentFilterOfLocal.addAction(LOCAL_BROADCAST);
        localReceiver = new LocalReceiver();
        localBroadcastManager.registerReceiver(localReceiver, intentFilterOfLocal);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(networkChangeReceiver);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_sendBroadcast:
                Intent intent = new Intent(MY_BROADCAST);
//                sendBroadcast(intent);//发送普通普通广播
                sendOrderedBroadcast(intent, null);//发送有序广播
                break;

            case R.id.btn_sendLocalBroadcast:
                Intent localIntent = new Intent(LOCAL_BROADCAST);
                localBroadcastManager.sendBroadcast(localIntent);
                break;

        }
    }

    //动态注册广播，该广播接收系统的网络状态信息
    class NetworkChangeReceiver extends BroadcastReceiver{

        /**
         * TODO:该方法在接收到对应的Action内容（广播）会调用这个方法
         * @param context
         * @param intent
         */
        @Override
        public void onReceive(Context context, Intent intent) {
            //获取NetworkInfo实例
            ConnectivityManager manager = (ConnectivityManager) getSystemService
                    (Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = manager.getActiveNetworkInfo();

            //第一个判断是否获取到NetworkInfo实例，第二个判断当前网络是否连接
            if (networkInfo != null && networkInfo.isAvailable()){
                Toast.makeText(context, "Network is available", Toast.LENGTH_LONG).show();
            }else {
                Toast.makeText(context, "Network is unavailable", Toast.LENGTH_LONG).show();
            }
        }
    }

    //本地广播接收器，使用本地广播只能使用动态注册的方式注册
    class LocalReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context, "接收到本地广播", Toast.LENGTH_SHORT).show();
        }
    }
}
