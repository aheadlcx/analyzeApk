package com.liulishuo.filedownloader;

import com.liulishuo.filedownloader.event.DownloadEventPoolImpl;

public class FileDownloadEventPool extends DownloadEventPoolImpl {

    private static class a {
        private static final FileDownloadEventPool a = new FileDownloadEventPool();
    }

    private FileDownloadEventPool() {
    }

    public static FileDownloadEventPool getImpl() {
        return a.a;
    }
}
