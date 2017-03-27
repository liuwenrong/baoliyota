/* *
   * Copyright (C) 2017 BaoliYota Tech. Co., Ltd, LLC - All Rights Reserved.
   *
   * Confidential and Proprietary.
   * Unauthorized copying of this file, via any medium is strictly prohibited.
   * */

package com.android.systemui;

import android.app.Application;
import android.content.Context;

import com.android.baoliyota.network.BaoliYotaHttpManager;

import static android.R.attr.x;

/**
 * des:
 *
 * @author liuwenrong@coolpad.com
 * @version 1.0, 2017/3/21
 */
public class SystemUIApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        BaoliYoaHttpManager.getInstance().init(this);
    }
}
