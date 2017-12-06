package com.ssdt.banbury.myrxjava2demo;

import android.app.Application;

import com.androidnetworking.AndroidNetworking;
import com.facebook.stetho.Stetho;
import com.facebook.stetho.okhttp3.StethoInterceptor;

import okhttp3.OkHttpClient;

/**
 * @author banbury
 * @version v1.0
 * @created 2017/12/6_10:00.
 * @description
 */

public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Helper.init(this);
        Stetho.initializeWithDefaults(this);
        OkHttpClient okHttpClient = new OkHttpClient() .newBuilder()
                .addNetworkInterceptor(new StethoInterceptor())
                .build();
        AndroidNetworking.initialize(getApplicationContext(),okHttpClient);
//        AndroidNetworking.initialize(getApplicationContext());
    }
}
