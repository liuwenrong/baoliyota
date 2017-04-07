/* *
   * Copyright (C) 2017 BaoliYota Tech. Co., Ltd, LLC - All Rights Reserved.
   *
   * Confidential and Proprietary.
   * Unauthorized copying of this file, via any medium is strictly prohibited.
   * */

package com.android.baoliyota.imageloader;

import android.content.Context;
import android.graphics.drawable.Drawable;

/**
 * des: ImageLoader的接口类
 *
 * @author liuwenrong
 * @version 1.0, 2017/4/6
 */
public interface IBaoliYotaImageLoader {

    interface LoaderListener<T> {
        void onResourceReady(T t);
        void onLoadFailed(Exception e, Drawable errorDrawable);
    }

    /**
     * 加载图片资源
     * @param context
     * @param url 图片url路径
     * @param listener 加载完成或失败 图片数据后的监听回调
     */
    void load(Context context, String url, final LoaderListener<Drawable> listener);

    /**
     * 预下载图片资源
     * @param context
     * @param url 图片资源url
     */
    void preLoad(Context context, String url);

}
