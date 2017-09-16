package com.android.demo.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import static com.android.systemui.MainActivity2.TAG;

/**
 * des: 闹钟接收器
 *
 * @author liuwenrong@yotamobile.com
 * @version 1.0, 2017/9/15
 */
public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        Toast.makeText(context, "接收到上传闹钟", Toast.LENGTH_SHORT).show();
        Log.e(TAG, "onReceive: AlarmReceiver");

    }
}
