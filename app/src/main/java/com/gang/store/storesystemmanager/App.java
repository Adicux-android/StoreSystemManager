package com.gang.store.storesystemmanager;

import android.app.Application;
import android.content.Context;

/**
 * Created by Administrator on 2018/8/7.
 */

public class App extends Application {
    private static App app;
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }
    public static Context getAppContext() {
        return context;
    }
}
