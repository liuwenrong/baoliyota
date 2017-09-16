/* *
   * Copyright (C) 2017 BaoliYota Tech. Co., Ltd, LLC - All Rights Reserved.
   *
   * Confidential and Proprietary.
   * Unauthorized copying of this file, via any medium is strictly prohibited.
   * */

package com.android.baoliyota.model;

import com.android.systemui.SystemUIApplication;
import com.android.systemui.Utils;

/**
 * des: 接口相关URL
 * @author liuwenrong@coolpad.com
 * @version 1.0, 2017/3/20
 */
public class ApiConstants {

    public static final boolean DEBUG = Utils.DEBUG;
    /**
     * BR阅读器的包名和类名,用于锁屏图书推荐跳转,传入类型,背屏锁屏为7
     */
    public static final String BR_PACKAGE_NAME = "com.baoliyota.reader";
    public static final String BR_CLASS_NAME = "com.baoliyota.reader.ui.LoadActivity";
    public static final String INTENT_FLAG_STR = "INTENT_FLAG_STR"; // 传入图书推荐的数据,值为json对象
    public static final String INTENT_FLAG_TYPE = "INTENT_FLAG_TYPE"; //值为7
    public static final int FLAG_TYPE_EPD_SCREEN = 7;
    public static final String INTENT_BOOK_ID = "bookId";

    /**
     * 掌阅IR阅读器的包名和类名
     */
    public static final String IR_PACKAGE_NAME = "com.ireader";
    public static final String IR_CLASS_NAME = "com.ireader.LoadActivity";
    /** what(reason)
     * 资源类型: 1壁纸, 2BR阅读, 3掌阅IReader
     * liuwenrong@coolpad.com,1.0, 2017/3/24*/
    public static final int RES_TYPE_WALLPAPER = 1;
    public static final int RES_TYPE_BACK_READER = 2;
    public static final int RES_TYPE_IREADER = 3;

    /**
     * 接口的一些参数,token,时间戳,版本,app类型
     */
    public static final String PARAM_APP_TOKEN = "appToken";
    public static final String PARAM_TIME_STAMP = "timestamp";
    public static final String PARAM_VERSION = "version";
    public static final String PARAM_APP_ID = "appId";
    public static final String PARAM_APP_TYPE = "appType";
    public static final String APP_ID = "4001";

    /**
     * @return 服务器域名,控制test和正式服务器
     */
    public static String getBaseURL() {
        String baseUrl;
        if (DEBUG) {
            baseUrl = "http://120.77.80.97";//appID 4001
        } else {
            baseUrl = "";
        }
        return baseUrl;
    }

    public static final String URL_EPD_LOCK_SCREEN_RES = getBaseURL() + "/app/operation/push/getUserResourceList";

    public static String getVersion(){
        try {
            String pkName = SystemUIApplication.getContext().getPackageName();
//            String versionName = context.getPackageManager().getPackageInfo(pkName, 0).versionName;
            int versionCode = SystemUIApplication.getContext().getPackageManager().getPackageInfo(pkName, 0).versionCode;
            return "" + versionCode;
        } catch (Exception e) {
            return "1";
        }
    }

    public static class ResponseCode {
        public static final int PARAMS_NOT_TRUE = 102; //请求参数不正确
        public static final int SUCCESS = 0; //请求成功

    }

}
