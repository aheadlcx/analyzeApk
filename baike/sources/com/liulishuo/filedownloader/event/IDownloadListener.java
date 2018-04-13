package com.liulishuo.filedownloader.event;

public abstract class IDownloadListener {
    public abstract boolean callback(IDownloadEvent iDownloadEvent);
}
