package com.android.demo.service;


import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

/**
 * des:
 *
 * @author liuwenrong@yotamobile.com
 * @version 1.0, 2017/9/15
 */
public class UploadJobService extends JobService {
    @Override
    public boolean onStartJob(JobParameters params) {

        int id = params.getJobId();

        Log.e("xxx-Roger", "onStartJob: UploadJob--JobId = " + id);

        return false;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public boolean onStopJob(JobParameters params) {
        int id = params.getJobId();

        Log.e("xxx-Roger", "onStoJob: UploadJob--JobId = " + id);

        JobScheduler scheduler = (JobScheduler) getBaseContext().getSystemService(Context.JOB_SCHEDULER_SERVICE);
        scheduler.schedule(scheduler.getPendingJob(params.getJobId()));
        return false;
    }

}
