package com.liulishuo.filedownloader.util;

import android.os.Handler;
import android.os.Handler.Callback;
import android.os.HandlerThread;
import android.os.Message;
import com.liulishuo.filedownloader.BaseDownloadTask;
import com.liulishuo.filedownloader.BaseDownloadTask.FinishListener;
import com.liulishuo.filedownloader.FileDownloader;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class FileDownloadSerialQueue {
    private final BlockingQueue<BaseDownloadTask> a = new LinkedBlockingQueue();
    private final HandlerThread b = new HandlerThread("SerialDownloadManager");
    private final Handler c;
    private int d = 0;

    private static class a implements FinishListener {
        private final WeakReference<FileDownloadSerialQueue> a;

        a(WeakReference<FileDownloadSerialQueue> weakReference) {
            this.a = weakReference;
        }

        public void over(BaseDownloadTask baseDownloadTask) {
            baseDownloadTask.removeFinishListener(this);
            if (this.a != null) {
                FileDownloadSerialQueue fileDownloadSerialQueue = (FileDownloadSerialQueue) this.a.get();
                if (fileDownloadSerialQueue != null) {
                    fileDownloadSerialQueue.d = 0;
                    fileDownloadSerialQueue.a();
                }
            }
        }
    }

    private class b implements Callback {
        final /* synthetic */ FileDownloadSerialQueue a;

        private b(FileDownloadSerialQueue fileDownloadSerialQueue) {
            this.a = fileDownloadSerialQueue;
        }

        public boolean handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    try {
                        this.a.d = ((BaseDownloadTask) this.a.a.take()).addFinishListener(new a(new WeakReference(this.a))).start();
                        break;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        break;
                    }
            }
            return false;
        }
    }

    public FileDownloadSerialQueue() {
        this.b.start();
        this.c = new Handler(this.b.getLooper(), new b());
        a();
    }

    public void enqueue(BaseDownloadTask baseDownloadTask) {
        try {
            this.a.put(baseDownloadTask);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public int getWorkingTaskId() {
        return this.d;
    }

    public int getWaitingTaskCount() {
        return this.a.size();
    }

    public List<BaseDownloadTask> shutdown() {
        if (this.d != 0) {
            FileDownloader.getImpl().pause(this.d);
        }
        Object arrayList = new ArrayList();
        this.a.drainTo(arrayList);
        this.c.removeMessages(1);
        this.b.interrupt();
        this.b.quit();
        return arrayList;
    }

    private void a() {
        this.c.sendEmptyMessage(1);
    }
}
