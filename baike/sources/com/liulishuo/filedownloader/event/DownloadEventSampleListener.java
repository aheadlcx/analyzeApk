package com.liulishuo.filedownloader.event;

public class DownloadEventSampleListener extends IDownloadListener {
    private final IEventListener a;

    public interface IEventListener {
        boolean callback(IDownloadEvent iDownloadEvent);
    }

    public DownloadEventSampleListener(IEventListener iEventListener) {
        this.a = iEventListener;
    }

    public boolean callback(IDownloadEvent iDownloadEvent) {
        return this.a != null && this.a.callback(iDownloadEvent);
    }
}
