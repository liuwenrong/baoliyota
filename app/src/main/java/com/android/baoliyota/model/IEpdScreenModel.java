/* *
   * Copyright (C) 2017 BaoliYota Tech. Co., Ltd, LLC - All Rights Reserved.
   *
   * Confidential and Proprietary.
   * Unauthorized copying of this file, via any medium is strictly prohibited.
   * */

package com.android.baoliyota.model;

import com.android.baoliyota.listener.OnEpdScreenDataListener;

/**
 * 背屏锁屏壁纸和图书推荐的数据来源
 * @author liuwenrong@coolpad.com
 * @version 1.0, 2017/3/20
 */

public interface IEpdScreenModel {

    void loadData(OnEpdScreenDataListener listener);

}
