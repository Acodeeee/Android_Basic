package com.android.wrf_mac.android_broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private NetworkChangeReceiver networkChangeReceiver;
    IntentFilter intentFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //实例化IntentFilter并且给intentFilter添加一个action
        intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");

        //实例化广播接收器
        networkChangeReceiver = new NetworkChangeReceiver();

        registerReceiver(networkChangeReceiver, intentFilter);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(networkChangeReceiver);
    }

    class NetworkChangeReceiver extends BroadcastReceiver{

        /**
         * TODO:该方法在接收到对应的Action内容（广播）会调用这个方法
         * @param context
         * @param intent
         */
        @Override
        public void onReceive(Context context, Intent intent) {
            //获取NetworkInfo实例
            ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = manager.getActiveNetworkInfo();

            //第一个判断是否获取到NetworkInfo实例，第二个判断当前网络是否连接
            if (networkInfo != null && networkInfo.isAvailable()){
                Toast.makeText(context, "Network is available", Toast.LENGTH_LONG).show();
            }else {
                Toast.makeText(context, "Network is unavailable", Toast.LENGTH_LONG).show();
            }
        }
    }
}
