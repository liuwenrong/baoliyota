package com.android.systemui;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.demo.service.UploadJobService;
import com.android.systemuib.R;
import com.coolyota.analysis.CYAnalysis;

import java.util.Timer;
import java.util.TimerTask;

/**
 * des:
 *
 * @author liuwenrong
 * @version 1.0, 2017/5/25
 */
public class MainActivity2 extends AppCompatActivity {

    public static final String TAG = "Roger--------";
    private static final String EXCEPTION_TYPE_KEY = "exception.type";
    private static final String EXCEPTION_DATA_KEY = "exception.data";
    String mPageName = "HomePage";
    EditText mEt;
    private float density;
    private int dpi;
    private DisplayMetrics displayMetrics;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Log.d(TAG, "onCreate: ");
        displayMetrics = getResources().getDisplayMetrics();
        density = displayMetrics.density;
        dpi = displayMetrics.densityDpi;
//        CYAnalysis.init(this, "www", "key");
        mEt = (EditText) findViewById(R.id.et);
        mEt.setText("3");

        Timer mTimer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getBaseContext(), "TimerTask", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        };
        mTimer.schedule(timerTask, 3000, 5000);

        JobScheduler scheduler = (JobScheduler) getContext().getSystemService(Context.JOB_SCHEDULER_SERVICE);
        int jobId = 1;
        JobInfo.Builder builder = new JobInfo.Builder(jobId, new ComponentName(getContext(), UploadJobService.class));

//        builder.setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED);//非流量(计费)的时候使用,即使用wifi
        //设置设备重启是执行此任务。前提是需要拥有RECEIVE_BOOT_COMPLETED  权限
//        builder.se
         //这个设置并不能设置成为前台进程。通知还需要应用自己发。此外该设置会忽略该任务的网络限制。
//        builder.
//        builder.setRequiresCharging(true);//充电的时候执行
//        builder.setRequiresDeviceIdle(true);//空闲的时候执行
//        builder.setPeriodic(3000);//每3s循环一次
        builder.setOverrideDeadline(5 * 1000); //约定的时间内条件都没触发后5秒后接着开始触发
        JobInfo job = builder.build();

        if (scheduler.schedule(job) <= 0) {

            Log.e(TAG, "onCreate: job No Start");
        }



        //创建Intent对象，action为ELITOR_CLOCK，附加信息为字符串“你该打酱油了”
        Intent intent = new Intent("Data.Upload.Clock");
        intent.putExtra("msg", "你该打酱油了");

//定义一个PendingIntent对象，PendingIntent.getBroadcast包含了sendBroadcast的动作。
//也就是发送了action 为""的intent
        PendingIntent pi = PendingIntent.getBroadcast(this, 0, intent, 0);

//AlarmManager对象,注意这里并不是new一个对象，AlarmManager为系统级服务
        AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);

//设置闹钟从当前时间开始，每隔3s执行一次PendingIntent对象pi，注意第一个参数与第二个参数的关系
// 5秒后通过PendingIntent pi对象发送广播
        am.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), 70 * 1000, pi);

    }

    private Activity getActivity() {
        return this;
    }

    private Context getContext() {
        return this;
    }

    @Override
    protected void onResume() {
        super.onResume();
        CYAnalysis.onResume(this, mPageName);
    }

    @Override
    protected void onPause() {
        super.onPause();
        CYAnalysis.onPause(this);
    }

    public void onClick(View v) {

        TextView tv = (TextView) v;

        Toast.makeText(this, tv.getText() + "的size = " + tv.getTextSize()
                + ", width = " + tv.getMeasuredWidth() + ",height = "
                + tv.getMeasuredHeight() + ",density = " + density +
                ",xdpi = " + displayMetrics.xdpi + ",ydpi = " + displayMetrics.ydpi +
                ", dpi = " + dpi, Toast.LENGTH_SHORT).show();

        Log.v(TAG, "onClick: tv = " + tv.getText().toString());
        Log.d(TAG, "onClick: tv = " + tv.getText().toString());
        Log.i(TAG, "onClick: tv = " + tv.getText().toString());
        Log.w(TAG, "onClick: tv = " + tv.getText().toString());
        Log.e(TAG, "onClick: tv = " + tv.getText().toString());

        switch (v.getId()) {
            case R.id.tv2:

//                startActivity(new Intent(this, MainActivity.class));
//                new NullPointerException("xx");

                String s = mEt.getText().toString();
                s = null;
                s.toString();
                Intent int_ = new Intent("baoliyota.logreport.main");
                int_.putExtra(EXCEPTION_TYPE_KEY, Integer.parseInt(s));
                startActivity(int_);

                break;
            case R.id.tv3:

//                try {
//                    Thread.sleep(18000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
                String str = mEt.getText().toString();

                Intent intent = new Intent("baoliyota.logreport");
//                intent.setFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);

                intent.putExtra(EXCEPTION_TYPE_KEY, Integer.parseInt(str));
                startActivity(intent);

                Toast.makeText(getApplicationContext(), "hello", Toast.LENGTH_SHORT).show();

                break;

        }
    }


}
