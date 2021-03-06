package com.liulishuo.filedownloader.services;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import com.liulishuo.filedownloader.g.c;
import com.liulishuo.filedownloader.g.e;
import com.liulishuo.filedownloader.g.f;
import java.lang.ref.WeakReference;

@SuppressLint({"Registered"})
public class FileDownloadService extends Service {
    private i a;

    public static class SeparateProcessService extends FileDownloadService {
    }

    public static class SharedMainProcessService extends FileDownloadService {
    }

    public void onCreate() {
        super.onCreate();
        c.a(this);
        try {
            f.a(e.a().a);
            f.a(e.a().b);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        g gVar = new g();
        if (e.a().d) {
            this.a = new e(new WeakReference(this), gVar);
        } else {
            this.a = new d(new WeakReference(this), gVar);
        }
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        this.a.a(intent, i, i2);
        return 1;
    }

    public void onDestroy() {
        this.a.d();
        super.onDestroy();
    }

    public IBinder onBind(Intent intent) {
        return this.a.a(intent);
    }
}
