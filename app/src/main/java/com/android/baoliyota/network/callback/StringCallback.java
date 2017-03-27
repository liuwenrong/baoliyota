package com.android.baoliyota.network.callback;


import com.android.baoliyota.network.convert.StringConvert;

import okhttp3.Response;

/**
 * des：返回字符串类型的数据
 * @author liuwenrong@coolpad.com
 * @version 1.0, 2017/3/20
 */
public abstract class StringCallback extends AbsCallback<String> {

    @Override
    public String convertSuccess(Response response) throws Exception {
        String s = StringConvert.create().convertSuccess(response);
        response.close();
        return s;
    }
}