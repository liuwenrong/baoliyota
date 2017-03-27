package com.android.baoliyota.network.request;


import com.android.baoliyota.network.BaoliYotaHttpUtils;

import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * 描    述：Get请求的实现类，注意需要传入本类的泛型
 * Created by liuwenrong on 2017/1/20.
 */
public class GetRequest extends BaseRequest<GetRequest> {

    public GetRequest(String url) {
        super(url);
        method = "GET";
    }

    @Override
    public RequestBody generateRequestBody() {
        return null;
    }

    @Override
    public Request generateRequest(RequestBody requestBody) {
        Request.Builder requestBuilder = BaoliYotaHttpUtils.appendHeaders(headers);
        url = BaoliYotaHttpUtils.createUrlFromParams(baseUrl, params.urlParamsMap);
        return requestBuilder.get().url(url).tag(tag).build();
    }
}