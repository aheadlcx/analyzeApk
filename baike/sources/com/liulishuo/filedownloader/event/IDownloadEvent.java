package com.liulishuo.filedownloader.event;

import com.liulishuo.filedownloader.util.FileDownloadLog;

public abstract class IDownloadEvent {
    protected final String a;
    public Runnable callback = null;

    public IDownloadEvent(String str) {
        this.a = str;
    }

    public IDownloadEvent(String str, boolean z) {
        this.a = str;
        if (z) {
            FileDownloadLog.w(this, "do not handle ORDER any more, %s", new Object[]{str});
        }
    }

    public final String getId() {
        return this.a;
    }
}
