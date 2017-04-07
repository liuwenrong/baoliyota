/* *
   * Copyright (C) 2017 BaoliYota Tech. Co., Ltd, LLC - All Rights Reserved.
   *
   * Confidential and Proprietary.
   * Unauthorized copying of this file, via any medium is strictly prohibited.
   * */

package com.android.baoliyota.contract;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.android.baoliyota.imageloader.IBaoliYotaImageLoader;
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

        /**
         * 设置背屏壁纸
         * @param isLockOpen 背屏锁是否显示/打开
         */
        void setEpdWallpaper(boolean isLockOpen);

        /** 从网络 设置壁纸
         * @param data
         */
        void setEpdWallpaperFromNet(EpdScreenBean.DataBean data);

        /**
         * 从本地默认图片设置壁纸
         */
        void setEpdWallpaperFromLocal();
        Context getContext();
        void setEpdBookRecommend(EpdScreenBean.DataBean data, int type);

    }

    interface IPresenter extends IBasePresenter{

        void setEpdWallpaper(Context context);

        /**
         * 加载网络图片
         * @param url 图片资源url
         * @param listener 获取图片成功或失败后的数据监听回调
         */
        void loadImage(String url, IBaoliYotaImageLoader.LoaderListener<Drawable> listener);
    }

}
