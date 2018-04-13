package com.liulishuo.filedownloader.services;

import android.util.SparseArray;
import com.liulishuo.filedownloader.util.FileDownloadExecutors;
import com.liulishuo.filedownloader.util.FileDownloadLog;
import com.liulishuo.filedownloader.util.FileDownloadProperties;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

class d {
    private SparseArray<FileDownloadRunnable> a = new SparseArray();
    private ThreadPoolExecutor b;
    private final String c = "Network";
    private int d;
    private int e = 0;

    d(int i) {
        this.b = FileDownloadExecutors.newDefaultThreadPool(i, "Network");
        this.d = i;
    }

    public synchronized boolean setMaxNetworkThreadCount(int i) {
        boolean z = false;
        synchronized (this) {
            if (exactSize() > 0) {
                FileDownloadLog.w(this, "Can't change the max network thread count, because the  network thread pool isn't in IDLE, please try again after all running tasks are completed or invoking FileDownloader#pauseAll directly.", new Object[0]);
            } else {
                int validNetworkThreadCount = FileDownloadProperties.getValidNetworkThreadCount(i);
                if (FileDownloadLog.NEED_LOG) {
                    FileDownloadLog.d(this, "change the max network thread count, from %d to %d", Integer.valueOf(this.d), Integer.valueOf(validNetworkThreadCount));
                }
                List shutdownNow = this.b.shutdownNow();
                this.b = FileDownloadExecutors.newDefaultThreadPool(validNetworkThreadCount, "Network");
                if (shutdownNow.size() > 0) {
                    FileDownloadLog.w(this, "recreate the network thread pool and discard %d tasks", Integer.valueOf(shutdownNow.size()));
                }
                this.d = validNetworkThreadCount;
                z = true;
            }
        }
        return z;
    }

    public void execute(FileDownloadRunnable fileDownloadRunnable) {
        fileDownloadRunnable.onPending();
        synchronized (this) {
            this.a.put(fileDownloadRunnable.getId(), fileDownloadRunnable);
        }
        this.b.execute(fileDownloadRunnable);
        if (this.e >= 600) {
            a();
            this.e = 0;
            return;
        }
        this.e++;
    }

    public void cancel(int i) {
        a();
        synchronized (this) {
            FileDownloadRunnable fileDownloadRunnable = (FileDownloadRunnable) this.a.get(i);
            if (fileDownloadRunnable != null) {
                fileDownloadRunnable.cancelRunnable();
                boolean remove = this.b.remove(fileDownloadRunnable);
                if (FileDownloadLog.NEED_LOG) {
                    FileDownloadLog.d(this, "successful cancel %d %B", Integer.valueOf(i), Boolean.valueOf(remove));
                }
            }
            this.a.remove(i);
        }
    }

    private synchronized void a() {
        SparseArray sparseArray = new SparseArray();
        for (int i = 0; i < this.a.size(); i++) {
            int keyAt = this.a.keyAt(i);
            FileDownloadRunnable fileDownloadRunnable = (FileDownloadRunnable) this.a.get(keyAt);
            if (fileDownloadRunnable.isExist()) {
                sparseArray.put(keyAt, fileDownloadRunnable);
            }
        }
        this.a = sparseArray;
    }

    public boolean isInThreadPool(int i) {
        FileDownloadRunnable fileDownloadRunnable = (FileDownloadRunnable) this.a.get(i);
        return fileDownloadRunnable != null && fileDownloadRunnable.isExist();
    }

    public int findRunningTaskIdBySameTempPath(String str, int i) {
        if (str == null) {
            return 0;
        }
        int size = this.a.size();
        for (int i2 = 0; i2 < size; i2++) {
            FileDownloadRunnable fileDownloadRunnable = (FileDownloadRunnable) this.a.valueAt(i2);
            if (fileDownloadRunnable.isExist() && fileDownloadRunnable.getId() != i && str.equals(fileDownloadRunnable.getTempFilePath())) {
                return fileDownloadRunnable.getId();
            }
        }
        return 0;
    }

    public synchronized int exactSize() {
        a();
        return this.a.size();
    }

    public synchronized List<Integer> getAllExactRunningDownloadIds() {
        List<Integer> arrayList;
        a();
        arrayList = new ArrayList();
        for (int i = 0; i < this.a.size(); i++) {
            arrayList.add(Integer.valueOf(((FileDownloadRunnable) this.a.get(this.a.keyAt(i))).getId()));
        }
        return arrayList;
    }
}
