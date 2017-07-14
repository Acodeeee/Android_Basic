package com.android.wrf_mac.android_context;

import android.app.Application;

/**
 * Created by wrf_mac on 2017/7/14.
 */

public class MyApplication extends Application {
    private String id;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {

        return id;
    }
}
