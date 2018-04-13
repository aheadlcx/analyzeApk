package com.meizu.cloud.pushsdk.networking.interfaces;

import com.meizu.cloud.pushsdk.networking.error.ANError;

public interface DownloadListener {
    void onDownloadComplete();

    void onError(ANError aNError);
}
