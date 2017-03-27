/* *
   * Copyright (C) 2017 BaoliYota Tech. Co., Ltd, LLC - All Rights Reserved.
   *
   * Confidential and Proprietary.
   * Unauthorized copying of this file, via any medium is strictly prohibited.
   * */

package com.android.baoliyota.view;

/**
 * MVP中的V,提供一个设置Presenter的方法
 * @author liuwenrong@coolpad.com
 * @version 1.0, 2017/3/20
 */
public interface IBaseView<T> {
    void setPresenter(T presenter);
}
