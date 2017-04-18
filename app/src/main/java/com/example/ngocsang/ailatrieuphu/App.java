package com.example.ngocsang.ailatrieuphu;

import android.app.Application;
import android.content.Context;

/**
 * Created by NgocSang on 1/21/2017.
 */

public class App extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }

    public static Context getContext() {
        return context;
    }
}
