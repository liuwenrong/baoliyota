/* *
   * Copyright (C) 2017 BaoliYota Tech. Co., Ltd, LLC - All Rights Reserved.
   *
   * Confidential and Proprietary.
   * Unauthorized copying of this file, via any medium is strictly prohibited.
   * */

package com.android.baoliyota.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;

import com.android.baoliyota.imageloader.BaoliYotaImageLoader;
import com.android.baoliyota.imageloader.IBaoliYotaImageLoader;
import com.android.baoliyota.listener.OnEpdScreenDataListener;
import com.android.baoliyota.model.bean.EpdScreenBean;
import com.android.baoliyota.network.BaoliYotaHttpManager;
import com.android.baoliyota.network.callback.AbsCallback;
import com.android.baoliyota.tools.DateUtils;
import com.android.baoliyota.tools.UrlBuilder;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Response;

/**
 * des: MVP中的M,主要用于处理数据相关,网络获取数据
 *
 * @author liuwenrong@coolpad.com
 * @version 1.0, 2017/3/20
 */
public class EpdScreenModel implements IEpdScreenModel {

    private static final String TAG = "EpdScreenModel";
    private static final String LOCK_CONTENT_URL = "content://com.android.jv.ink.launcherink/jv_ink_setting_data/2";
    private Uri mLockUri;

    /**
     * @return 背屏锁屏是否显示开关
     */
    @Override
    public boolean isLockOpenFromSettings(@NonNull Context context) {
        if (mLockUri == null) {
            mLockUri = Uri.parse(LOCK_CONTENT_URL);
        }
        int a = 0;
        Cursor cursor = null;
        try {
            cursor = context.getContentResolver().query(mLockUri, null, null, null, null);
            //游标移到第一条记录准备获取数据
            if (cursor == null) {
                return true;
            }

            if (cursor.moveToFirst()) {
                // 获取数据中的LONG类型数据
                a = (int) cursor.getInt(cursor.getColumnIndex("today_lock"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {//关闭cursor
                cursor.close();
            }
        }
        boolean isOpen = false;
        if (a == 1) {
            isOpen = false;//将锁屏置为透明,直接看到后面的today屏
        }
        if (a == 0) {
            isOpen = true;//正常显示锁屏界面
        }
        Log.i("--1703231504", isOpen + "222222222222222222222222");
        return isOpen;

    }


    /**
     * 加载网络锁屏推荐数据
     * @param listener 成功失败后的监听回调
     */
    @Override
    public void loadData(final OnEpdScreenDataListener listener) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("appType", 0);
//        jsonObject.put("userID", 0); //锁屏可以不传
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String url = decorateCommonParams(ApiConstants.URL_EPD_LOCK_SCREEN_RES, null);

        BaoliYotaHttpManager.getInstance().post(url)
                .tag(this)
                .upJson(jsonObject)
                .execute(new AbsCallback<EpdScreenBean>() {

                    @Override
                    public void onSuccess(EpdScreenBean epdScreenBean, Call call, Response response) {
                        listener.onSuccess(epdScreenBean);
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        listener.onError();
                        Log.e(TAG, "onError: call = " + call.toString(), e);
                    }

                    /**
                     * @param response 需要转换的对象,json转换为Bean对象,即解析数据
                     * @return 背屏服务器数据的Bean类
                     * @throws Exception
                     */
                    @Override
                    public EpdScreenBean convertSuccess(Response response) throws Exception {


//                        String json = response.body().string();
//                        Log.i(TAG + "--1703", "convertSuccess: json = " + json);
                        Gson gson = new GsonBuilder().create();
//                        EpdScreenBean epdScreenBean;
//                        epdScreenBean = gson.fromJson(json, EpdScreenBean.class);

//
                        String jsonLocal = "{ \"code\": 1, \"data\": [ { \"bookId\": 1, \"picUrl\": \"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1490864224&di=e16630f6fd5f0587f9a1bff2fd1392c2&imgtype=jpg&er=1&src=http%3A%2F%2Fpic21.nipic.com%2F20120511%2F8133282_145011003398_2.jpg\", \"reourceId\": 1, \"resourceType\": 1, \"resourceUrl\": \"www.baidu.com\" }, { \"bookId\": 1, \"picUrl\": \"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1490269455272&di=7ef929d847836d298dad597acda5994a&imgtype=0&src=http%3A%2F%2Fimg3.redocn.com%2Ftupian%2F20140704%2Fheibaiyutu_2694809.jpg\", \"reourceId\": 1, \"resourceType\": 1, \"resourceUrl\": \"www\" } ], \"msg\": \"success\" } ";
                        EpdScreenBean epdScreenBean = gson.fromJson(jsonLocal, EpdScreenBean.class);
                        Log.i(TAG + "--1703", "onCreate: json = " + jsonLocal);
                        Log.i(TAG, "convertSuccess: " + epdScreenBean.toString());

                        return epdScreenBean;
                    }
                });
    }

    SharedPreferences mSharedPreferences;
    public static final String SHARED_PREFERENCES_NAME = "baoliyota_spf";

    @Override
    public boolean isTodayUpdate(Context context){

        if (mSharedPreferences == null) {
            mSharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
        }
        long lastUpdateTime = mSharedPreferences.getLong("lastUpdateTime", new Date().getTime());
        return DateUtils.isToday(lastUpdateTime, null);

    }

    @Override
    public void setLastUpdateTimeToSP(Context context){
        if (mSharedPreferences == null) {
            mSharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
        }
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putLong("lastUpdateTime", new Date().getTime());
        editor.commit();

    }

    /**
     * 获取将要显示的壁纸或图书推荐的索引Index
     * @param size 服务器数据的集合大小,即有多少壁纸或图书推荐
     * @param context 获取共享参数需要用到
     * @return
     */
    public int getCurIndex(int size, Context context) {
        if (mSharedPreferences == null) {
            mSharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
        }
        int curIndex = mSharedPreferences.getInt("wallpaperIndex", 0);
        if (curIndex >= size) {//如果将要显示的壁纸Index大于服务器数据集合大小, 将其置为0
            curIndex = 0;
        }
        //设置下一张要显示的壁纸
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putInt("wallpaperIndex", curIndex + 1);
        editor.commit();
        return curIndex;

    }

    IBaoliYotaImageLoader mBaoliYotaImageLoader = BaoliYotaImageLoader.getInstance();

    /**
     * 预下载所有图片,以便显示壁纸时快速从内存和磁盘中读取
     * @param datas 服务器返回的接口数据集合
     * @param context
     */
    @Override
    public void preLoadAllImage(List<EpdScreenBean.DataBean> datas, Context context) {

        for (int i = 0; i < datas.size(); i++) {
            EpdScreenBean.DataBean data = datas.get(i);
            if (data.getPicUrl() != null && !"".equals(data.getPicUrl())){
                mBaoliYotaImageLoader.preLoad(context, data.getPicUrl());
            }
        }

    }

    /**
     * 加载网络图片
     * @param context
     * @param url 网络图片url
     * @param listener 图片数据返回的监听
     */
    public void loadImage(Context context, String url, IBaoliYotaImageLoader.LoaderListener<Drawable> listener) {
        mBaoliYotaImageLoader.load(context, url, listener);
    }

    private int mTotalSize;//服务器数据的集合大小

    @Override
    public int getTotalSize() {
        return mTotalSize;
    }

    public void setTotalSize(int totalSize) {
        this.mTotalSize = totalSize;
    }

    /**
     * 为目标url添加通用get参数
     *
     * @param urlString 目标url
     * @param token     token不需要可以是null
     * @return 装饰过公共get参数以后的url
     */
    private String decorateCommonParams(String urlString, @Nullable String token) {
        Map<String, String> map = new HashMap<String, String>();
        map.put(ApiConstants.PARAM_APP_ID, ApiConstants.APP_ID);
        map.put(ApiConstants.PARAM_VERSION, "1");
        map.put(ApiConstants.PARAM_TIME_STAMP, String.valueOf(System.currentTimeMillis()));
        map.put(ApiConstants.PARAM_APP_TOKEN, UrlBuilder.buildSign(map, token));
        if (!TextUtils.isEmpty(token)) {
//            map.put(YotaUserConstants.PARAM_TOKEN, token);
        }

        StringBuilder sb = new StringBuilder();
        sb.append(urlString).append("?");
        for (Map.Entry<String, String> entry : map.entrySet()) {
            sb.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
        }
        return sb.substring(0, sb.length() - 1);
    }
}
