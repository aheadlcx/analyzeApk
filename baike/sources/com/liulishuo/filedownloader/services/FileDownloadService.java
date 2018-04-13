package com.liulishuo.filedownloader.services;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import com.liulishuo.filedownloader.FileDownloadServiceProxy;
import com.liulishuo.filedownloader.util.FileDownloadProperties;
import com.liulishuo.filedownloader.util.FileDownloadUtils;
import java.lang.ref.WeakReference;

@SuppressLint({"Registered"})
public class FileDownloadService extends Service {
    private e a;

    public static class SeparateProcessService extends FileDownloadService {
    }

    public static class SharedMainProcessService extends FileDownloadService {
    }

    public void onCreate() {
        super.onCreate();
        try {
            FileDownloadUtils.setMinProgressStep(FileDownloadProperties.getImpl().DOWNLOAD_MIN_PROGRESS_STEP);
            FileDownloadUtils.setMinProgressTime(FileDownloadProperties.getImpl().DOWNLOAD_MIN_PROGRESS_TIME);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        c cVar = new c(FileDownloadServiceProxy.getImpl().getDownloadMgrInitialParams());
        if (FileDownloadProperties.getImpl().PROCESS_NON_SEPARATE) {
            this.a = new FDServiceSharedHandler(new WeakReference(this), cVar);
        } else {
            this.a = new FDServiceSeparateHandler(new WeakReference(this), cVar);
        }
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        this.a.onStartCommand(intent, i, i2);
        return 1;
    }

    public void onDestroy() {
        this.a.onDestroy();
        super.onDestroy();
    }

    public IBinder onBind(Intent intent) {
        return this.a.onBind(intent);
    }
}
