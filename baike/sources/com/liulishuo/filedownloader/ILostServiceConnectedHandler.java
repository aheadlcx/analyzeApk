package com.liulishuo.filedownloader;

import com.liulishuo.filedownloader.BaseDownloadTask.IRunningTask;

public interface ILostServiceConnectedHandler {
    boolean dispatchTaskStart(IRunningTask iRunningTask);

    boolean isInWaitingList(IRunningTask iRunningTask);

    void taskWorkFine(IRunningTask iRunningTask);
}
