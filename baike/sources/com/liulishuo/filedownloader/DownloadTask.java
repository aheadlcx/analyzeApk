package com.liulishuo.filedownloader;

import android.text.TextUtils;
import android.util.SparseArray;
import com.liulishuo.filedownloader.BaseDownloadTask.FinishListener;
import com.liulishuo.filedownloader.BaseDownloadTask.IRunningTask;
import com.liulishuo.filedownloader.BaseDownloadTask.InQueueTask;
import com.liulishuo.filedownloader.ITaskHunter.IMessageHandler;
import com.liulishuo.filedownloader.model.FileDownloadHeader;
import com.liulishuo.filedownloader.model.FileDownloadStatus;
import com.liulishuo.filedownloader.util.FileDownloadLog;
import com.liulishuo.filedownloader.util.FileDownloadUtils;
import java.io.File;
import java.util.ArrayList;

public class DownloadTask implements BaseDownloadTask, IRunningTask, a {
    public static final int DEFAULT_CALLBACK_PROGRESS_MIN_INTERVAL_MILLIS = 10;
    volatile int a = 0;
    private final ITaskHunter b;
    private final IMessageHandler c;
    private int d;
    private ArrayList<FinishListener> e;
    private final String f;
    private String g;
    private String h;
    private boolean i;
    private FileDownloadHeader j;
    private FileDownloadListener k;
    private SparseArray<Object> l;
    private Object m;
    private int n = 0;
    private boolean o = false;
    private boolean p = false;
    private int q = 100;
    private int r = 10;
    private boolean s = false;
    private boolean t = false;
    private final Object u;
    private final Object v = new Object();
    private volatile boolean w = false;

    private static final class a implements InQueueTask {
        private final DownloadTask a;

        private a(DownloadTask downloadTask) {
            this.a = downloadTask;
            this.a.t = true;
        }

        public int enqueue() {
            int id = this.a.getId();
            if (FileDownloadLog.NEED_LOG) {
                FileDownloadLog.d(this, "add the task[%d] to the queue", new Object[]{Integer.valueOf(id)});
            }
            FileDownloadList.getImpl().c(this.a);
            return id;
        }
    }

    DownloadTask(String str) {
        this.f = str;
        this.u = new Object();
        Object downloadTaskHunter = new DownloadTaskHunter(this, this.u);
        this.b = downloadTaskHunter;
        this.c = downloadTaskHunter;
    }

    public BaseDownloadTask setMinIntervalUpdateSpeed(int i) {
        this.b.setMinIntervalUpdateSpeed(i);
        return this;
    }

    public BaseDownloadTask setPath(String str) {
        return setPath(str, false);
    }

    public BaseDownloadTask setPath(String str, boolean z) {
        this.g = str;
        if (FileDownloadLog.NEED_LOG) {
            FileDownloadLog.d(this, "setPath %s", new Object[]{str});
        }
        this.i = z;
        if (z) {
            this.h = null;
        } else {
            this.h = new File(str).getName();
        }
        return this;
    }

    public BaseDownloadTask setListener(FileDownloadListener fileDownloadListener) {
        this.k = fileDownloadListener;
        if (FileDownloadLog.NEED_LOG) {
            FileDownloadLog.d(this, "setListener %s", new Object[]{fileDownloadListener});
        }
        return this;
    }

    public BaseDownloadTask setCallbackProgressTimes(int i) {
        this.q = i;
        return this;
    }

    public BaseDownloadTask setCallbackProgressMinInterval(int i) {
        this.r = i;
        return this;
    }

    public BaseDownloadTask setCallbackProgressIgnored() {
        return setCallbackProgressTimes(-1);
    }

    public BaseDownloadTask setTag(Object obj) {
        this.m = obj;
        if (FileDownloadLog.NEED_LOG) {
            FileDownloadLog.d(this, "setTag %s", new Object[]{obj});
        }
        return this;
    }

    public BaseDownloadTask setTag(int i, Object obj) {
        if (this.l == null) {
            this.l = new SparseArray(2);
        }
        this.l.put(i, obj);
        return this;
    }

    public BaseDownloadTask setForceReDownload(boolean z) {
        this.s = z;
        return this;
    }

    public BaseDownloadTask setFinishListener(FinishListener finishListener) {
        addFinishListener(finishListener);
        return this;
    }

    public BaseDownloadTask addFinishListener(FinishListener finishListener) {
        if (this.e == null) {
            this.e = new ArrayList();
        }
        if (!this.e.contains(finishListener)) {
            this.e.add(finishListener);
        }
        return this;
    }

    public boolean removeFinishListener(FinishListener finishListener) {
        return this.e != null && this.e.remove(finishListener);
    }

    public BaseDownloadTask setAutoRetryTimes(int i) {
        this.n = i;
        return this;
    }

    public BaseDownloadTask addHeader(String str, String str2) {
        b();
        this.j.add(str, str2);
        return this;
    }

    public BaseDownloadTask addHeader(String str) {
        b();
        this.j.add(str);
        return this;
    }

    public BaseDownloadTask removeAllHeaders(String str) {
        if (this.j == null) {
            synchronized (this.v) {
                if (this.j == null) {
                }
            }
            return this;
        }
        this.j.removeAll(str);
        return this;
    }

    public BaseDownloadTask setSyncCallback(boolean z) {
        this.o = z;
        return this;
    }

    public BaseDownloadTask setWifiRequired(boolean z) {
        this.p = z;
        return this;
    }

    public int ready() {
        return asInQueueTask().enqueue();
    }

    public InQueueTask asInQueueTask() {
        return new a();
    }

    public boolean reuse() {
        if (isRunning()) {
            FileDownloadLog.w(this, "This task[%d] is running, if you want start the same task, please create a new one by FileDownloader#create", new Object[]{Integer.valueOf(getId())});
            return false;
        }
        this.a = 0;
        this.t = false;
        this.w = false;
        this.b.reset();
        return true;
    }

    public boolean isUsing() {
        return this.b.getStatus() != (byte) 0;
    }

    public boolean isRunning() {
        if (FileDownloader.getImpl().b().isInWaitingList(this)) {
            return true;
        }
        return FileDownloadStatus.isIng(getStatus());
    }

    public boolean isAttached() {
        return this.a != 0;
    }

    public int start() {
        if (!this.t) {
            return a();
        }
        throw new IllegalStateException("If you start the task manually, it means this task doesn't belong to a queue, so you must not invoke BaseDownloadTask#ready() or InQueueTask#enqueue() before you start() this method. For detail: If this task doesn't belong to a queue, what is just an isolated task, you just need to invoke BaseDownloadTask#start() to start this task, that's all. In other words, If this task doesn't belong to a queue, you must not invoke BaseDownloadTask#ready() method or InQueueTask#enqueue() method before invoke BaseDownloadTask#start(), If you do that and if there is the same listener object to start a queue in another thread, this task may be assembled by the queue, in that case, when you invoke BaseDownloadTask#start() manually to start this task or this task is started by the queue, there is an exception buried in there, because this task object is started two times without declare BaseDownloadTask#reuse() : 1. you invoke BaseDownloadTask#start() manually; 2. the queue start this task automatically.");
    }

    private int a() {
        if (!isUsing()) {
            if (!isAttached()) {
                setAttachKeyDefault();
            }
            this.b.intoLaunchPool();
            return getId();
        } else if (isRunning()) {
            throw new IllegalStateException(FileDownloadUtils.formatString("This task is running %d, if you want to start the same task, please create a new one by FileDownloader.create", new Object[]{Integer.valueOf(getId())}));
        } else {
            throw new IllegalStateException("This task is dirty to restart, If you want to reuse this task, please invoke #reuse method manually and retry to restart again." + this.b.toString());
        }
    }

    public boolean pause() {
        boolean pause;
        synchronized (this.u) {
            pause = this.b.pause();
        }
        return pause;
    }

    public boolean cancel() {
        return pause();
    }

    public int getId() {
        if (this.d != 0) {
            return this.d;
        }
        if (TextUtils.isEmpty(this.g) || TextUtils.isEmpty(this.f)) {
            return 0;
        }
        int generateId = FileDownloadUtils.generateId(this.f, this.g, this.i);
        this.d = generateId;
        return generateId;
    }

    public int getDownloadId() {
        return getId();
    }

    public String getUrl() {
        return this.f;
    }

    public int getCallbackProgressTimes() {
        return this.q;
    }

    public int getCallbackProgressMinInterval() {
        return this.r;
    }

    public String getPath() {
        return this.g;
    }

    public boolean isPathAsDirectory() {
        return this.i;
    }

    public String getFilename() {
        return this.h;
    }

    public String getTargetFilePath() {
        return FileDownloadUtils.getTargetFilePath(getPath(), isPathAsDirectory(), getFilename());
    }

    public FileDownloadListener getListener() {
        return this.k;
    }

    public int getSoFarBytes() {
        return getSmallFileSoFarBytes();
    }

    public int getSmallFileSoFarBytes() {
        if (this.b.getSofarBytes() > 2147483647L) {
            return Integer.MAX_VALUE;
        }
        return (int) this.b.getSofarBytes();
    }

    public long getLargeFileSoFarBytes() {
        return this.b.getSofarBytes();
    }

    public int getTotalBytes() {
        return getSmallFileTotalBytes();
    }

    public int getSmallFileTotalBytes() {
        if (this.b.getTotalBytes() > 2147483647L) {
            return Integer.MAX_VALUE;
        }
        return (int) this.b.getTotalBytes();
    }

    public long getLargeFileTotalBytes() {
        return this.b.getTotalBytes();
    }

    public int getSpeed() {
        return this.b.getSpeed();
    }

    public byte getStatus() {
        return this.b.getStatus();
    }

    public boolean isForceReDownload() {
        return this.s;
    }

    public Throwable getEx() {
        return getErrorCause();
    }

    public Throwable getErrorCause() {
        return this.b.getErrorCause();
    }

    public boolean isReusedOldFile() {
        return this.b.isReusedOldFile();
    }

    public Object getTag() {
        return this.m;
    }

    public Object getTag(int i) {
        return this.l == null ? null : this.l.get(i);
    }

    public boolean isContinue() {
        return isResuming();
    }

    public boolean isResuming() {
        return this.b.isResuming();
    }

    public String getEtag() {
        return this.b.getEtag();
    }

    public int getAutoRetryTimes() {
        return this.n;
    }

    public int getRetryingTimes() {
        return this.b.getRetryingTimes();
    }

    public boolean isSyncCallback() {
        return this.o;
    }

    public boolean isLargeFile() {
        return this.b.isLargeFile();
    }

    public boolean isWifiRequired() {
        return this.p;
    }

    private void b() {
        if (this.j == null) {
            synchronized (this.v) {
                if (this.j == null) {
                    this.j = new FileDownloadHeader();
                }
            }
        }
    }

    public FileDownloadHeader getHeader() {
        return this.j;
    }

    public void markAdded2List() {
        this.w = true;
    }

    public void free() {
        this.b.free();
        if (FileDownloadList.getImpl().a((IRunningTask) this)) {
            this.w = false;
        }
    }

    public void startTaskByQueue() {
        a();
    }

    public void startTaskByRescue() {
        a();
    }

    public Object getPauseLock() {
        return this.u;
    }

    public boolean isMarkedAdded2List() {
        return this.w;
    }

    public IRunningTask getRunningTask() {
        return this;
    }

    public void setFileName(String str) {
        this.h = str;
    }

    public ArrayList<FinishListener> getFinishListenerList() {
        return this.e;
    }

    public BaseDownloadTask getOrigin() {
        return this;
    }

    public IMessageHandler getMessageHandler() {
        return this.c;
    }

    public boolean is(int i) {
        return getId() == i;
    }

    public boolean is(FileDownloadListener fileDownloadListener) {
        return getListener() == fileDownloadListener;
    }

    public boolean isOver() {
        return FileDownloadStatus.isOver(getStatus());
    }

    public int getAttachKey() {
        return this.a;
    }

    public void setAttachKeyByQueue(int i) {
        this.a = i;
    }

    public void setAttachKeyDefault() {
        int hashCode;
        if (getListener() != null) {
            hashCode = getListener().hashCode();
        } else {
            hashCode = hashCode();
        }
        this.a = hashCode;
    }

    public String toString() {
        return FileDownloadUtils.formatString("%d@%s", new Object[]{Integer.valueOf(getId()), super.toString()});
    }
}
