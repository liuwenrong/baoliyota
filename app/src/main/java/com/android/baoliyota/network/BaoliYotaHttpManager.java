/* *
   * Copyright (C) 2017 BaoliYota Tech. Co., Ltd, LLC - All Rights Reserved.
   *
   * Confidential and Proprietary.
   * Unauthorized copying of this file, via any medium is strictly prohibited.
   * */

package com.android.baoliyota.network;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.android.baoliyota.network.model.HttpHeaders;
import com.android.baoliyota.network.model.HttpParams;
import com.android.baoliyota.network.request.GetRequest;
import com.android.baoliyota.network.request.PostRequest;

import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.OkHttpClient;

/**
 * 这个类用来辅助网络请求Http的
 *  @author liuwenrong@coolpad.com,2017/1/20.
 */
public class BaoliYotaHttpManager implements IHttpManager {

    public static final int DEFAULT_MILLISECONDS = 60000;       //默认的超时时间
    public static int REFRESH_TIME = 100;                       //回调刷新时间（单位ms）
    private HttpParams mCommonParams;                           //全局公共请求参数
    private HttpHeaders mCommonHeaders;                         //全局公共请求头
    private int mRetryCount = 3;                                //全局超时重试次数

    /**
     * 采用单例模式
     */
    private static BaoliYotaHttpManager httpManager;
    private static OkHttpClient okHttpClient;
    private OkHttpClient.Builder okHttpClientBuilder;           //ok请求的客户端
    private Handler mHandler;                                   //用于在主线程执行的调度器
    private static Application context;                         //全局上下文

    /**
     * 单例模式,私有的构造方法
     */
    private BaoliYotaHttpManager(){
        okHttpClient = new OkHttpClient();
        okHttpClientBuilder = okHttpClient.newBuilder();

        okHttpClientBuilder.connectTimeout(DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS)
                .readTimeout(DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS)
                .writeTimeout(DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);

        mHandler = new Handler(Looper.getMainLooper());

    }

    public static BaoliYotaHttpManager getInstance(){
        if(httpManager == null){

            synchronized(BaoliYotaHttpManager.class){
                if(httpManager == null){
                    httpManager = new BaoliYotaHttpManager();
                }
            }

        }
        return httpManager;
    }

    /** 必须在全局Application先调用，获取context上下文，否则缓存无法使用 */
    public static void init(Application app) {
        context = app;
    }

    /** 获取全局上下文 */
    public static Context getContext() {
        if (context == null) throw new IllegalStateException("请先在全局Application中调用 BaoliYotaHttpManager.init() 初始化！");
        return context;
    }

    /** get请求 */
    public GetRequest get(String url) {
        return new GetRequest(url);
    }

    /** post请求 */
    public PostRequest post(String url) {
        return new PostRequest(url);
    }

    /** get请求 */
    public GetRequest download(String url) {
        return new GetRequest(url);
    }

    public OkHttpClient getOkHttpClient() {
        if (okHttpClient == null) okHttpClient = okHttpClientBuilder.build();
        return okHttpClient;
    }

    public Handler getDelivery() {
        return mHandler;
    }

    /** 超时重试次数 */
    public int getRetryCount() {
        return mRetryCount;
    }

    /** 获取全局公共请求参数 */
    public HttpParams getCommonParams() {
        return mCommonParams;
    }

    /** 添加全局公共请求参数 */
    public BaoliYotaHttpManager addCommonParams(HttpParams commonParams) {
        if (mCommonParams == null) mCommonParams = new HttpParams();
        mCommonParams.put(commonParams);
        return this;
    }

    /** 获取全局公共请求头 */
    public HttpHeaders getCommonHeaders() {
        return mCommonHeaders;
    }

    /** 添加全局公共请求参数 */
    public BaoliYotaHttpManager addCommonHeaders(HttpHeaders commonHeaders) {
        if (mCommonHeaders == null) mCommonHeaders = new HttpHeaders();
        mCommonHeaders.put(commonHeaders);
        return this;
    }

    /** 根据Tag取消请求 */
    public void cancelTag(Object tag) {
        for (Call call : getOkHttpClient().dispatcher().queuedCalls()) {
            if (tag.equals(call.request().tag())) {
                call.cancel();
            }
        }
        for (Call call : getOkHttpClient().dispatcher().runningCalls()) {
            if (tag.equals(call.request().tag())) {
                call.cancel();
            }
        }
    }

    /** 取消所有请求请求 */
    public void cancelAll() {
        for (Call call : getOkHttpClient().dispatcher().queuedCalls()) {
            call.cancel();
        }
        for (Call call : getOkHttpClient().dispatcher().runningCalls()) {
            call.cancel();
        }
    }

}
