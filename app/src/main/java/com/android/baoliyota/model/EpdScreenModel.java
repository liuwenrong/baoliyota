package com.android.baoliyota.model;

import android.util.Log;

import com.android.baoliyota.listener.OnEpdScreenDataListener;
import com.android.baoliyota.model.bean.EpdScreenBean;
import com.android.baoliyota.network.BaoliYotaHttpManager;
import com.android.baoliyota.network.callback.AbsCallback;
import com.android.baoliyota.network.model.HttpParams;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.Call;
import okhttp3.Response;

import static com.android.systemui.Utils.DEBUG;

/**
 * des: MVP中的M,主要用于处理数据相关,网络获取数据
 * @author liuwenrong@coolpad.com
 * @version 1.0, 2017/3/20
 */
public class EpdScreenModel implements IEpdScreenModel {

    private static final String TAG = "EpdScreenModel";

    @Override
    public void loadData(final OnEpdScreenDataListener listener) {
//        HttpParams params = new HttpParams();
//        params.put("appType", 0);


        String url = "http://suggest.taobao.com/sug";
        Log.d(TAG, "get: url = " + url);
        HttpParams params = new HttpParams();
        params.put("code", "utf-8");
        params.put("q", "衣服");
        params.put("callback", "cb");

//        params.put("userID", 0); //锁屏可以不传
        if (DEBUG){
            Log.i(TAG + "--170321", "loadData: --------");
        }
//        BaoliYotaHttpManager.getInstance().post(ApiConstants.URL_EPD_LOCK_SCREEN_RES)
        BaoliYotaHttpManager.getInstance().get(url)
                .tag(this)
                .params(params)
                .execute(new AbsCallback<EpdScreenBean>() {

                    @Override
                    public void onSuccess(EpdScreenBean epdScreenBean, Call call, Response response) {
                        listener.onSuccess(epdScreenBean);
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        Log.e(TAG, "onError: call = " + call.toString(), e);
                    }

                    /**
                     * @param response 需要转换的对象,json转换为Bean对象,即解析数据
                     * @return
                     * @throws Exception
                     */
                    @Override
                    public EpdScreenBean convertSuccess(Response response) throws Exception {
                        Gson gson = new GsonBuilder().create();

                        String json = "{ \"code\": 1, \"data\": [ { \"bookId\": 1, \"picUrl\": \"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1490864224&di=e16630f6fd5f0587f9a1bff2fd1392c2&imgtype=jpg&er=1&src=http%3A%2F%2Fpic21.nipic.com%2F20120511%2F8133282_145011003398_2.jpg\", \"reourceId\": 1, \"resourceType\": 1, \"resourceUrl\": \"www.baidu.com\" }, { \"bookId\": 1, \"picUrl\": \"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1490269455272&di=7ef929d847836d298dad597acda5994a&imgtype=0&src=http%3A%2F%2Fimg3.redocn.com%2Ftupian%2F20140704%2Fheibaiyutu_2694809.jpg\", \"reourceId\": 1, \"resourceType\": 1, \"resourceUrl\": \"www\" } ], \"msg\": \"success\" } ";
                        Log.i(TAG, "onCreate: json = " + json);
//                        String json = response.body().toString();
//                        Log.i(TAG, "convertSuccess: json = " + json);
//                        EpdScreenBean epdScreenBean;
//                        epdScreenBean = gson.fromJson(json, EpdScreenBean.class);
                        EpdScreenBean epdScreenBean = gson.fromJson(json, EpdScreenBean.class);
                        Log.i(TAG, "convertSuccess: " + epdScreenBean.toString());

                        return epdScreenBean;
                    }
                });
    }

}
