/* *
   * Copyright (C) 2017 BaoliYota Tech. Co., Ltd, LLC - All Rights Reserved.
   *
   * Confidential and Proprietary.
   * Unauthorized copying of this file, via any medium is strictly prohibited.
   * */

package com.android.baoliyota.model;

import com.android.systemui.Utils;

/**
 * des: 接口URL
 * @author liuwenrong@coolpad.com
 * @version 1.0, 2017/3/20
 */
public class ApiConstants {

    public static final boolean DEBUG = Utils.DEBUG;

    public static String getBaseURL(){
        String baseUrl;
        if(DEBUG){
            baseUrl = "http://120.77.80.97:22";//appID 4001
        }else{
            baseUrl = "";
        }
        return baseUrl;
    }

    public static final String URL_EPD_LOCK_SCREEN_RES = getBaseURL() + "/api/operation/push/getUserResource";
}
