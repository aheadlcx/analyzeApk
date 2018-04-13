package com.liulishuo.filedownloader;

import com.liulishuo.filedownloader.BaseDownloadTask.FinishListener;
import java.util.ArrayList;
import java.util.List;

public class FileDownloadQueueSet {
    private FileDownloadListener a;
    private boolean b;
    private List<FinishListener> c;
    private Integer d;
    private Boolean e;
    private Boolean f;
    private Boolean g;
    private Integer h;
    private Integer i;
    private Object j;
    private String k;
    private BaseDownloadTask[] l;

    public FileDownloadQueueSet(FileDownloadListener fileDownloadListener) {
        if (fileDownloadListener == null) {
            throw new IllegalArgumentException("create FileDownloadQueueSet must with valid target!");
        }
        this.a = fileDownloadListener;
    }

    public FileDownloadQueueSet downloadTogether(BaseDownloadTask... baseDownloadTaskArr) {
        this.b = false;
        this.l = baseDownloadTaskArr;
        return this;
    }

    public FileDownloadQueueSet downloadTogether(List<BaseDownloadTask> list) {
        this.b = false;
        this.l = new BaseDownloadTask[list.size()];
        list.toArray(this.l);
        return this;
    }

    public FileDownloadQueueSet downloadSequentially(BaseDownloadTask... baseDownloadTaskArr) {
        this.b = true;
        this.l = baseDownloadTaskArr;
        return this;
    }

    public FileDownloadQueueSet downloadSequentially(List<BaseDownloadTask> list) {
        this.b = true;
        this.l = new BaseDownloadTask[list.size()];
        list.toArray(this.l);
        return this;
    }

    public void reuseAndStart() {
        for (BaseDownloadTask reuse : this.l) {
            reuse.reuse();
        }
        start();
    }

    public void start() {
        for (BaseDownloadTask baseDownloadTask : this.l) {
            baseDownloadTask.setListener(this.a);
            if (this.d != null) {
                baseDownloadTask.setAutoRetryTimes(this.d.intValue());
            }
            if (this.e != null) {
                baseDownloadTask.setSyncCallback(this.e.booleanValue());
            }
            if (this.f != null) {
                baseDownloadTask.setForceReDownload(this.f.booleanValue());
            }
            if (this.h != null) {
                baseDownloadTask.setCallbackProgressTimes(this.h.intValue());
            }
            if (this.i != null) {
                baseDownloadTask.setCallbackProgressMinInterval(this.i.intValue());
            }
            if (this.j != null) {
                baseDownloadTask.setTag(this.j);
            }
            if (this.c != null) {
                for (FinishListener addFinishListener : this.c) {
                    baseDownloadTask.addFinishListener(addFinishListener);
                }
            }
            if (this.k != null) {
                baseDownloadTask.setPath(this.k, true);
            }
            if (this.g != null) {
                baseDownloadTask.setWifiRequired(true);
            }
            baseDownloadTask.asInQueueTask().enqueue();
        }
        FileDownloader.getImpl().start(this.a, this.b);
    }

    public FileDownloadQueueSet setDirectory(String str) {
        this.k = str;
        return this;
    }

    public FileDownloadQueueSet setAutoRetryTimes(int i) {
        this.d = Integer.valueOf(i);
        return this;
    }

    public FileDownloadQueueSet setSyncCallback(boolean z) {
        this.e = Boolean.valueOf(z);
        return this;
    }

    public FileDownloadQueueSet setForceReDownload(boolean z) {
        this.f = Boolean.valueOf(z);
        return this;
    }

    public FileDownloadQueueSet setCallbackProgressTimes(int i) {
        this.h = Integer.valueOf(i);
        return this;
    }

    public FileDownloadQueueSet setCallbackProgressMinInterval(int i) {
        this.i = Integer.valueOf(i);
        return this;
    }

    public FileDownloadQueueSet ignoreEachTaskInternalProgress() {
        setCallbackProgressTimes(-1);
        return this;
    }

    public FileDownloadQueueSet disableCallbackProgressTimes() {
        return setCallbackProgressTimes(0);
    }

    public FileDownloadQueueSet setTag(Object obj) {
        this.j = obj;
        return this;
    }

    public FileDownloadQueueSet addTaskFinishListener(FinishListener finishListener) {
        if (this.c == null) {
            this.c = new ArrayList();
        }
        this.c.add(finishListener);
        return this;
    }

    public FileDownloadQueueSet setWifiRequired(boolean z) {
        this.g = Boolean.valueOf(z);
        return this;
    }
}
