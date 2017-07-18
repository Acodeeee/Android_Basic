package com.android.wrf_mac.android_service;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {

    private DownloadBinder mBinder = new DownloadBinder();

    //自定义一个Binder用于Activity和Service之间的绑定
    //在DownloadBinder中实现一些方法，用于Activity操作Service
    class DownloadBinder extends Binder{
        public void startDownload(){
            Log.i("MyService", "startDownload...");
        }
        public int getProgress(){
            Log.i("MyService", "getProgress...");
            return 0;
        }
    }
    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("MyService", "onCreate execute");
       //创建一个后台通知栏
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        Notification notification = new Notification.Builder(this)
                .setContentTitle("MyService") //设置标题
                .setContentText("I`m MyService")    //这种内容
                .setWhen(System.currentTimeMillis()) //设置显示时间
                .setSmallIcon(R.mipmap.ic_launcher) //设置小图标
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))//设置大图标
                .setContentIntent(pendingIntent)    //设置通知点击事件
                .build();
        startForeground(1, notification);   //启动notification，并且设置为前台应用
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        Log.i("MyService", "onStart execute");
    }

    @Override
    public int onStartCommand(Intent intent,int flags, int startId) {
        Log.i("MyService", "onStartCommand execute");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("MyService", "onDestroy");
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        Log.i("MyService", "onBind execute");
        return mBinder;
    }
}
