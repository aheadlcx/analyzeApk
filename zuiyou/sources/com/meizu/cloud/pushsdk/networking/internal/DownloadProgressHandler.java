package com.meizu.cloud.pushsdk.networking.internal;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.meizu.cloud.pushsdk.networking.interfaces.DownloadProgressListener;
import com.meizu.cloud.pushsdk.networking.model.Progress;
import java.lang.ref.WeakReference;

public class DownloadProgressHandler extends Handler {
    private final WeakReference<DownloadProgressListener> mDownloadProgressListenerWeakRef;

    public DownloadProgressHandler(DownloadProgressListener downloadProgressListener) {
        super(Looper.getMainLooper());
        this.mDownloadProgressListenerWeakRef = new WeakReference(downloadProgressListener);
    }

    public void handleMessage(Message message) {
        DownloadProgressListener downloadProgressListener = (DownloadProgressListener) this.mDownloadProgressListenerWeakRef.get();
        switch (message.what) {
            case 1:
                if (downloadProgressListener != null) {
                    Progress progress = (Progress) message.obj;
                    downloadProgressListener.onProgress(progress.currentBytes, progress.totalBytes);
                    return;
                }
                return;
            default:
                super.handleMessage(message);
                return;
        }
    }
}
