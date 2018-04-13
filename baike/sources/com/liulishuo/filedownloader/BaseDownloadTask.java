package com.liulishuo.filedownloader;

import com.liulishuo.filedownloader.ITaskHunter.IMessageHandler;

public interface BaseDownloadTask {
    public static final int DEFAULT_CALLBACK_PROGRESS_MIN_INTERVAL_MILLIS = 10;

    public interface FinishListener {
        void over(BaseDownloadTask baseDownloadTask);
    }

    public interface IRunningTask {
        void free();

        int getAttachKey();

        IMessageHandler getMessageHandler();

        BaseDownloadTask getOrigin();

        Object getPauseLock();

        boolean is(int i);

        boolean is(FileDownloadListener fileDownloadListener);

        boolean isMarkedAdded2List();

        boolean isOver();

        void markAdded2List();

        void setAttachKeyByQueue(int i);

        void setAttachKeyDefault();

        void startTaskByQueue();

        void startTaskByRescue();
    }

    public interface InQueueTask {
        int enqueue();
    }

    public interface LifeCycleCallback {
        void onBegin();

        void onIng();

        void onOver();
    }

    BaseDownloadTask addFinishListener(FinishListener finishListener);

    BaseDownloadTask addHeader(String str);

    BaseDownloadTask addHeader(String str, String str2);

    InQueueTask asInQueueTask();

    boolean cancel();

    int getAutoRetryTimes();

    int getCallbackProgressMinInterval();

    int getCallbackProgressTimes();

    int getDownloadId();

    Throwable getErrorCause();

    String getEtag();

    Throwable getEx();

    String getFilename();

    int getId();

    long getLargeFileSoFarBytes();

    long getLargeFileTotalBytes();

    FileDownloadListener getListener();

    String getPath();

    int getRetryingTimes();

    int getSmallFileSoFarBytes();

    int getSmallFileTotalBytes();

    int getSoFarBytes();

    int getSpeed();

    byte getStatus();

    Object getTag();

    Object getTag(int i);

    String getTargetFilePath();

    int getTotalBytes();

    String getUrl();

    boolean isAttached();

    boolean isContinue();

    boolean isForceReDownload();

    boolean isLargeFile();

    boolean isPathAsDirectory();

    boolean isResuming();

    boolean isReusedOldFile();

    boolean isRunning();

    boolean isSyncCallback();

    boolean isUsing();

    boolean isWifiRequired();

    boolean pause();

    int ready();

    BaseDownloadTask removeAllHeaders(String str);

    boolean removeFinishListener(FinishListener finishListener);

    boolean reuse();

    BaseDownloadTask setAutoRetryTimes(int i);

    BaseDownloadTask setCallbackProgressIgnored();

    BaseDownloadTask setCallbackProgressMinInterval(int i);

    BaseDownloadTask setCallbackProgressTimes(int i);

    BaseDownloadTask setFinishListener(FinishListener finishListener);

    BaseDownloadTask setForceReDownload(boolean z);

    BaseDownloadTask setListener(FileDownloadListener fileDownloadListener);

    BaseDownloadTask setMinIntervalUpdateSpeed(int i);

    BaseDownloadTask setPath(String str);

    BaseDownloadTask setPath(String str, boolean z);

    BaseDownloadTask setSyncCallback(boolean z);

    BaseDownloadTask setTag(int i, Object obj);

    BaseDownloadTask setTag(Object obj);

    BaseDownloadTask setWifiRequired(boolean z);

    int start();
}
