/* *
   * Copyright (C) 2017 BaoliYota Tech. Co., Ltd, LLC - All Rights Reserved.
   *
   * Confidential and Proprietary.
   * Unauthorized copying of this file, via any medium is strictly prohibited.
   * */

package com.android.baoliyota.model;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.android.baoliyota.imageloader.IBaoliYotaImageLoader;
import com.android.baoliyota.listener.OnEpdScreenDataListener;
import com.android.baoliyota.model.bean.EpdScreenBean;

import java.util.List;

/**
 * 背屏锁屏壁纸和图书推荐的数据来源
 * @author liuwenrong@coolpad.com
 * @version 1.0, 2017/3/20
 */

public interface IEpdScreenModel {

    void loadData(OnEpdScreenDataListener listener);
    boolean isLockOpenFromSettings(Context context);
    /**
     * 获取将要显示的壁纸或图书推荐的索引Index
     * @param size    服务器数据的集合大小,即有多少壁纸或图书推荐
     * @param context 获取共享参数需要用到
     * @return
     */
    int getCurIndex(int size, Context context);
    int getTotalSize();
    void setTotalSize(int totalSize);

    /**
     * 设置上次更新数据的时间到SharedPreferences
     * @param context
     */
    void setLastUpdateTimeToSP(Context context);

    /**
     * 今天是否更新了数据
     * @param context
     * @return true 今天已经更新过了
     */
    boolean isTodayUpdate(Context context);

    /**
     * 预下载所有图片,以便显示壁纸时快速从内存和磁盘中读取
     * @param datas 服务器返回的接口数据集合
     * @param context
     */
    void preLoadAllImage(List<EpdScreenBean.DataBean> datas, Context context);

    /**
     * 加载网络图片
     * @param context
     * @param url 网络图片url
     * @param listener 图片数据返回的监听
     */
    void loadImage(Context context, String url, IBaoliYotaImageLoader.LoaderListener<Drawable> listener);
}
