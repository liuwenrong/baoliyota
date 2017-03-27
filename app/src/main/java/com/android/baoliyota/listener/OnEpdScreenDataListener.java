/* *
   * Copyright (C) 2017 BaoliYota Tech. Co., Ltd, LLC - All Rights Reserved.
   *
   * Confidential and Proprietary.
   * Unauthorized copying of this file, via any medium is strictly prohibited.
   * */

package com.android.baoliyota.listener;

import com.android.baoliyota.model.bean.EpdScreenBean;

/**
 * 背屏网络数据监听,并回调
 * @author liuwenrong@coolpad.com
 * @version 1.0, 2017/3/20
 */

public interface OnEpdScreenDataListener {
    void onSuccess(EpdScreenBean epdScreenBean);
    void onError();
}
