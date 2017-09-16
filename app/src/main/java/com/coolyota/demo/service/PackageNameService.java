package com.coolyota.demo.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import com.coolyota.demo.aidl.IPackageName;

/**
 * des:
 *
 * @author liuwenrong
 * @version 1.0, 2017/8/2
 */
public class PackageNameService extends Service {
    private int mPageId;
    private final IPackageName.Stub mBinder = new IPackageName.Stub() {
        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }

        @Override
        public int getPageId() throws RemoteException {

            return ++mPageId;
        }
    };

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }
}
