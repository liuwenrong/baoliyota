/* *
   * Copyright (C) 2017 BaoliYota Tech. Co., Ltd, LLC - All Rights Reserved.
   *
   * Confidential and Proprietary.
   * Unauthorized copying of this file, via any medium is strictly prohibited.
   * */

package com.android.systemui;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.os.StrictMode;

import com.android.baoliyota.network.BaoliYotaHttpManager;
import com.android.systemuib.BuildConfig;
import com.coolyota.analysis.CYAnalysis;

/**
 * des:
 * @author liuwenrong@coolpad.com
 * @version 1.0, 2017/3/21
 */
public class SystemUIApplication extends Application {

    private static final String TAG = "SystemUIApplication";
    private static final boolean DEBUG = false;
    /**
     * BaoliYota begin, add
     * what(reason) 提供单例和Context
     * liuwenrong, 1.0, 2017/4/11
     */
    private SystemUIApplication sInstance;
    private static Context mContext;
    public SystemUIApplication getInstance(){
        if (sInstance == null){
            sInstance = new SystemUIApplication();
        }
        return sInstance;
    }

    public static Context getContext(){
        return mContext;
    }

    @Override
    public void onCreate() {

        super.onCreate();

        if (BuildConfig.DEBUG) {
            // 针对线程的相关策略
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                    .detectDiskReads()
                    .detectDiskWrites()
                    .detectNetwork()   // or .detectAll() for all detectable problems
                    .penaltyLog()
                    .build());

            // 针对VM的相关策略
            StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                    .detectLeakedSqlLiteObjects()
                    .detectLeakedClosableObjects()
                    .penaltyLog()
                    .penaltyDeath()
                    .build());
        }

        mContext = this;
        BaoliYotaHttpManager.getInstance().init(this);

        CYAnalysis.setUploadEnabled(false);
        CYAnalysis.init(this, "4001", "com.android.systemuib");
    }

    boolean getIsEmpty(){
        String s = "..";
        return !s.isEmpty();

    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }
}
