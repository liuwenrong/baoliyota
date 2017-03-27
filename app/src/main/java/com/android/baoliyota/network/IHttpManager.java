package com.android.baoliyota.network;


import com.android.baoliyota.network.request.BaseRequest;

/**
 * Created by liuwenrong on 2017/1/20.
 */

public interface IHttpManager {

    BaseRequest get(String url);
    BaseRequest post(String url);
    BaseRequest download(String url);

}
