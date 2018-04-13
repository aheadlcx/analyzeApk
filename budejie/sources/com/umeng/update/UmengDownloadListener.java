package com.umeng.update;

public interface UmengDownloadListener {
    void OnDownloadEnd(int i, String str);

    void OnDownloadStart();

    void OnDownloadUpdate(int i);
}
