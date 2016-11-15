package org.xielipeng.takephotodemo;

import android.app.Application;

/**
 * Created by xielipeng on 2016/11/14.
 */

public class MyAppliction extends Application {
    private static final String TAG = "MyAppliction";

    private static MyAppliction instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance=this;
    }

    public static MyAppliction getInstance() {
        return instance;
    }
}
