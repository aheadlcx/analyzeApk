package com.ak.android.engine.download;

public interface ApkListener {
    void onApkDownloadCanceled(String str);

    void onApkDownloadCompleted(String str);

    void onApkDownloadContinued(String str);

    void onApkDownloadFailed(String str);

    void onApkDownloadPaused(String str);

    void onApkDownloadProgress(String str, int i);

    void onApkDownloadStart(String str);

    void onApkInstallCompleted(String str, String str2);
}
