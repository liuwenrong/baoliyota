/* *
   * Copyright (C) 2017 BaoliYota Tech. Co., Ltd, LLC - All Rights Reserved.
   *
   * Confidential and Proprietary.
   * Unauthorized copying of this file, via any medium is strictly prohibited.
   * */

package com.android.systemui;

import android.app.Application;
import android.content.res.Configuration;

import com.android.baoliyota.network.BaoliYotaHttpManager;

/**
 * des:
 *
 * @author liuwenrong@coolpad.com
 * @version 1.0, 2017/3/21
 */
public class SystemUIApplication extends Application {
    int ONE = 1;
    @Override
    public void onCreate() {

        super.onCreate();

        int one = ONE;
        int four = one + one + one + one;
        BaoliYotaHttpManager.getInstance().init(this);
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
