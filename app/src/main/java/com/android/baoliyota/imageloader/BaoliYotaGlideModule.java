/* *
   * Copyright (C) 2017 BaoliYota Tech. Co., Ltd, LLC - All Rights Reserved.
   *
   * Confidential and Proprietary.
   * Unauthorized copying of this file, via any medium is strictly prohibited.
   * */

package com.android.baoliyota.imageloader;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool;
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.module.GlideModule;

/**
 * des: Glide的一些配置,需要在AndroidManifest.xml注册 <meta-data>/>
 * @author liuwenrong
 * @version 1.0, 2017/4/6
 */
public class BaoliYotaGlideModule implements GlideModule {

    private static final int SIZE_IN_BYTE = 1024 * 1024 * 10;// 10M缓存
    private static final int SIZE_IN_BYTE_DISK = 1024 * 1024 * 20;// 20M磁盘缓存


    @Override
    public void applyOptions(Context context, GlideBuilder glideBuilder) {
        //GlideBuilder 可以配置内存,磁盘缓存等
        glideBuilder.setBitmapPool(new LruBitmapPool(SIZE_IN_BYTE)); //Lru最近最少使用算法
        glideBuilder.setMemoryCache(new LruResourceCache(SIZE_IN_BYTE));
        //设置磁盘缓存目录(默认磁盘,data/data/packageName/)和大小(20M,Glide默认250M)
        glideBuilder.setDiskCache(new InternalCacheDiskCacheFactory(context, InternalCacheDiskCacheFactory.DEFAULT_DISK_CACHE_DIR, SIZE_IN_BYTE_DISK));
        // 设置解码器,默认565,比ARGB_8888节省一半内存,画质相差不大,不支持透明度
        glideBuilder.setDecodeFormat(DecodeFormat.PREFER_RGB_565);
    }

    @Override
    public void registerComponents(Context context, Glide glide) {
        // 该方法每次实现只会被调用一次。通常在该方法中注册ModelLoader

    }
}
