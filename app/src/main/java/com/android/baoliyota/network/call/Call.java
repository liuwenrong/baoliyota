package com.android.baoliyota.network.call;


import com.android.baoliyota.network.callback.AbsCallback;
import com.android.baoliyota.network.model.Response;
import com.android.baoliyota.network.request.BaseRequest;

/**
 * 描    述：请求的包装类
 * Created by liuwenrong on 2017/1/22.
 */
public interface Call<T> {
    /** 同步执行 */
    Response<T> execute() throws Exception;

    /** 异步回调执行 */
    void execute(AbsCallback<T> callback);

    /** 是否已经执行 */
    boolean isExecuted();

    /** 取消 */
    void cancel();

    /** 是否取消 */
    boolean isCanceled();

    Call<T> clone();

    BaseRequest getBaseRequest();
}