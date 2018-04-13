package cn.xiaochuan.daemon;

import android.annotation.TargetApi;
import android.app.job.JobParameters;
import android.app.job.JobService;

@TargetApi(21)
public class JobSchedulerService extends JobService {
    public boolean onStartJob(JobParameters jobParameters) {
        if (a.c) {
            a.a(a.b);
        }
        return false;
    }

    public boolean onStopJob(JobParameters jobParameters) {
        return false;
    }
}
