package com.media.ffmpeg;

import android.content.Context;
import android.media.MediaScannerConnection;
import android.media.MediaScannerConnection.MediaScannerConnectionClient;
import android.net.Uri;

public class FFMpegMediaScannerNotifier implements MediaScannerConnectionClient {
    private MediaScannerConnection mConnection;
    private String mPath;

    private FFMpegMediaScannerNotifier(Context context, String str) {
        this.mPath = str;
        this.mConnection = new MediaScannerConnection(context, this);
        this.mConnection.connect();
    }

    public static void scan(Context context, String str) {
        FFMpegMediaScannerNotifier fFMpegMediaScannerNotifier = new FFMpegMediaScannerNotifier(context, str);
    }

    public void onMediaScannerConnected() {
        this.mConnection.scanFile(this.mPath, null);
    }

    public void onScanCompleted(String str, Uri uri) {
        this.mConnection.disconnect();
    }
}
