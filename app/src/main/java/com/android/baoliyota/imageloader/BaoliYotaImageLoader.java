/* *
   * Copyright (C) 2017 BaoliYota Tech. Co., Ltd, LLC - All Rights Reserved.
   *
   * Confidential and Proprietary.
   * Unauthorized copying of this file, via any medium is strictly prohibited.
   * */

package com.android.baoliyota.imageloader;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import static com.bumptech.glide.Glide.with;

/**
 * des:
 *
 * @author liuwenrong
 * @version 1.0, 2017/4/6
 */
public class BaoliYotaImageLoader implements IBaoliYotaImageLoader {

    private static BaoliYotaImageLoader instance=null;
    public static synchronized BaoliYotaImageLoader getInstance(){
        if(instance==null) {
            instance = new BaoliYotaImageLoader();
        }
        return instance;
    }
    private BaoliYotaImageLoader(){
    }

    @Override
    public void load(Context context, String url, final LoaderListener<Drawable> listener) {

        with(context)
                .load(url)
                .priority(Priority.HIGH)
                .diskCacheStrategy(DiskCacheStrategy.ALL)//采用所有磁盘缓存,ALL只下载一遍,之后同一张图片裁剪不同大小不用再重新下载
                .skipMemoryCache(false) //不跳过内存缓存
                .into(new SimpleTarget<GlideDrawable>() {

                    @Override
                    public void onResourceReady(GlideDrawable drawable, GlideAnimation<? super GlideDrawable> glideAnimation) {

                        listener.onResourceReady(drawable);
                    }

                    @Override
                    public void onLoadFailed(Exception e, Drawable errorDrawable) {
                        super.onLoadFailed(e, errorDrawable);

                        listener.onLoadFailed(e, errorDrawable);
                    }

                });

    }

    @Override
    public void preLoad(Context context, String url){

        Glide.with(context)
                .load(url)
                .priority(Priority.HIGH)
                .diskCacheStrategy(DiskCacheStrategy.ALL)//采用所有磁盘缓存,ALL只下载一遍,之后同一张图片裁剪不同大小不用再重新下载
                .skipMemoryCache(false) //不跳过内存缓存
                .preload();

    }

}
