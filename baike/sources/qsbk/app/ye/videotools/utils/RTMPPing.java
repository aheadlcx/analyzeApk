package qsbk.app.ye.videotools.utils;

import qsbk.app.ye.videotools.recorder.MediaRecorder;

public class RTMPPing {
    private static native int _ping(String str);

    public static int ping(String str) {
        if (MediaRecorder.loadLibrary()) {
            return _ping(str);
        }
        return -100;
    }
}
