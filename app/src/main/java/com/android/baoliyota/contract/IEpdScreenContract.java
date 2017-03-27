/* *
   * Copyright (C) 2017 BaoliYota Tech. Co., Ltd, LLC - All Rights Reserved.
   *
   * Confidential and Proprietary.
   * Unauthorized copying of this file, via any medium is strictly prohibited.
   * */

package com.android.baoliyota.contract;

import com.android.baoliyota.model.bean.EpdScreenBean;
import com.android.baoliyota.presenter.IBasePresenter;
import com.android.baoliyota.view.IBaseView;

/**
 * This specifies the contract between the view and the presenter
 * @author liuwenrong@coolpad.com
 * @version 1.0, 2017/3/20
 */
public interface IEpdScreenContract {

    /**
     * 因为设置背屏壁纸的View在PhoneStatusBar中,所有PhoneStatusBar去实现IView即可
     */
    interface IView extends IBaseView<IPresenter> {

        void setEpdWallpaperFromNet(EpdScreenBean epdScreenBean);
    }

    interface IPresenter extends IBasePresenter{

    }

}
