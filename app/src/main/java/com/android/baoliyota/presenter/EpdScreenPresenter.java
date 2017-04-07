/* *
   * Copyright (C) 2017 BaoliYota Tech. Co., Ltd, LLC - All Rights Reserved.
   *
   * Confidential and Proprietary.
   * Unauthorized copying of this file, via any medium is strictly prohibited.
   * */

package com.android.baoliyota.presenter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.support.annotation.NonNull;
import android.util.Log;

import com.android.baoliyota.contract.IEpdScreenContract;
import com.android.baoliyota.contract.IEpdScreenContract.IPresenter;
import com.android.baoliyota.imageloader.IBaoliYotaImageLoader;
import com.android.baoliyota.listener.OnEpdScreenDataListener;
import com.android.baoliyota.model.ApiConstants;
import com.android.baoliyota.model.EpdScreenModel;
import com.android.baoliyota.model.IEpdScreenModel;
import com.android.baoliyota.model.bean.EpdScreenBean;

import java.util.List;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;
import static com.android.systemui.Utils.DEBUG;

/**
 * Listens to user action from the UI ({@link CPYPhoneStatusBar}), retrievers the data and updates the
 * UI as required
 * @author liuwenrong@coolpad.com
 * @version 1.0, 2017/3/20
 */
public class EpdScreenPresenter implements IPresenter, OnEpdScreenDataListener {

    private static final String TAG = "EpdPresenter";
    private IEpdScreenContract.IView mView;
    private EpdScreenBean mEpdScreenBean;
    private IEpdScreenModel mEpdScreenModel;
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
        if (mEpdScreenBean != null && mEpdScreenModel.isTodayUpdate(mView.getContext())) {
                List<EpdScreenBean.DataBean> datas = mEpdScreenBean.getData();
                int size = datas.size(); // 数据从内存中读取,只要Bean不为空,datas数据也不为空
                setEpdWallpaperOrBookRecommend(datas, size);
        } else {
            int netType = com.android.baoliyota.network.Utils.getNetType(mView.getContext());
            if (netType == ConnectivityManager.TYPE_WIFI) {
                mEpdScreenModel.loadData(this);
            } else {
                mView.setEpdWallpaperFromLocal();
            }
        }
    }

    /**
     * 设置锁屏壁纸,首先去读取设置中是否打开
     *
     * @param context
     */
    @Override
    public void setEpdWallpaper(@NonNull Context context) {
        boolean isOpen = mEpdScreenModel.isLockOpenFromSettings(context);
        //设置壁纸
        mView.setEpdWallpaper(isOpen);
    }

    /**
     * 在Model中解析服务器数据成功后的回调
     * @param epdScreenBean 网络数据封装到Bean中
     */
    @Override
    public void onSuccess(EpdScreenBean epdScreenBean) {

        if (epdScreenBean == null) {
            mView.setEpdWallpaperFromLocal();//网络数据为0时,去设置本地的默认壁纸
            return;
        }

        List<EpdScreenBean.DataBean> datas = epdScreenBean.getData();
        if (datas == null){ //有接口数据,但壁纸和图书推荐集合为空,或者请求参数错误
            mView.setEpdWallpaperFromLocal();//网络数据为0时,去设置本地的默认壁纸
            return;
        }
        int size = datas.size();
        if (size == 0) {
            mView.setEpdWallpaperFromLocal();//网络数据为0时,去设置本地的默认壁纸
            return;
        }
        //预加载网络图片
        mEpdScreenModel.preLoadAllImage(datas, mView.getContext());
        //设置集合大小
        mEpdScreenModel.setTotalSize(size);
        //最近更新时间存到共享参数中
        mEpdScreenModel.setLastUpdateTimeToSP(mView.getContext());
        mEpdScreenBean = epdScreenBean; //将网络数据存在内存中,当天调用时不用再从网络获取,直到次日去更新最新数据
        setEpdWallpaperOrBookRecommend(datas, size);
    }

    private void setEpdWallpaperOrBookRecommend(List<EpdScreenBean.DataBean> datas, int size) {
        int index = mEpdScreenModel.getCurIndex(size, mView.getContext());
        EpdScreenBean.DataBean data = datas.get(index);
        int resType = data.getResourceType();
        switch (resType) {
            case ApiConstants.RES_TYPE_WALLPAPER:
                mView.setEpdWallpaperFromNet(data);
                break;
            case ApiConstants.RES_TYPE_BACK_READER:
            case ApiConstants.RES_TYPE_IREADER:
                mView.setEpdBookRecommend(data, resType);
                break;

        }
    }

    /**
     * 服务器数据解析失败的回调,
     */
    @Override
    public void onError() {
        mView.setEpdWallpaperFromLocal();//网络获取数据失败时,去设置本地的默认壁纸
    }

    /**
     * 加载网络图片
     * @param url 图片资源url
     * @param listener 获取图片成功或失败后的数据监听回调
     */
    @Override
    public void loadImage(String url, IBaoliYotaImageLoader.LoaderListener<Drawable> listener) {
        mEpdScreenModel.loadImage(mView.getContext(), url, listener);
    }

}
