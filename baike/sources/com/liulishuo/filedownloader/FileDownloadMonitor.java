package com.liulishuo.filedownloader;

public class FileDownloadMonitor {
    private static IMonitor a;

    public interface IMonitor {
        void onRequestStart(int i, boolean z, FileDownloadListener fileDownloadListener);

        void onRequestStart(BaseDownloadTask baseDownloadTask);

        void onTaskBegin(BaseDownloadTask baseDownloadTask);

        void onTaskOver(BaseDownloadTask baseDownloadTask);

        void onTaskStarted(BaseDownloadTask baseDownloadTask);
    }

    public static void setGlobalMonitor(IMonitor iMonitor) {
        a = iMonitor;
    }

    public static void releaseGlobalMonitor() {
        a = null;
    }

    public static IMonitor getMonitor() {
        return a;
    }

    public static boolean isValid() {
        return getMonitor() != null;
    }
}
