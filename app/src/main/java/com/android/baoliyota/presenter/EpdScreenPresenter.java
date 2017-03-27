/* *
   * Copyright (C) 2017 BaoliYota Tech. Co., Ltd, LLC - All Rights Reserved.
   *
   * Confidential and Proprietary.
   * Unauthorized copying of this file, via any medium is strictly prohibited.
   * */

package com.android.baoliyota.presenter;

import android.net.ConnectivityManager;
import android.support.annotation.NonNull;
import android.util.Log;

import com.android.baoliyota.contract.IEpdScreenContract;
import com.android.baoliyota.contract.IEpdScreenContract.IPresenter;
import com.android.baoliyota.listener.OnEpdScreenDataListener;
import com.android.baoliyota.model.EpdScreenModel;
import com.android.baoliyota.model.IEpdScreenModel;
import com.android.baoliyota.model.bean.EpdScreenBean;

import static com.android.systemui.Utils.DEBUG;

/**
 * Listens to user action from the UI ({@link CPYPhoneStatusBar}), retrievers the data and updates the
 * UI as required
 * @author liuwenrong@coolpad.com
 * @version 1.0, 2017/3/20
 */
public class EpdScreenPresenter implements IPresenter, OnEpdScreenDataListener {

    public static final String TAG = "EpdScreenPresenter";
    public IEpdScreenContract.IView mView;
    public IEpdScreenModel mEpdScreenModel;
    public EpdScreenPresenter(@NonNull IEpdScreenContract.IView mView){
        this.mView = mView;
        mEpdScreenModel = new EpdScreenModel();
        mView.setPresenter(this);
    }

    /**
     * des: 业务的入口,去加载网络数据
     */
    @Override
    public void start() {

//        Context
        mEpdScreenModel.loadData(this);
        if (DEBUG){
            Log.i(TAG, "start: -----end" + "--1703211453");
        }
    }

    @Override
    public void onSuccess(EpdScreenBean epdScreenBean) {

        mView.setEpdWallpaperFromNet(epdScreenBean);
    }

    @Override
    public void onError() {

        ConnectivityManager m;

        EpdScreenBean epdScreenBean;

    }
}
