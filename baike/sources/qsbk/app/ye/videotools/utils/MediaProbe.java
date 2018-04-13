package qsbk.app.ye.videotools.utils;

import qsbk.app.ye.videotools.recorder.MediaRecorder;

public class MediaProbe {
    private byte[] _lyrics = null;
    public int mChannels = 0;
    public long mDuration = 0;
    public int mHeight = 0;
    public String mLyrics = null;
    public int mRotate = 0;
    public int mSampleRate = 0;
    public boolean mSuccessed = false;
    public int mVideoBitrate = 0;
    public int mWidth = 0;

    private native boolean probe(String str);

    public MediaProbe(String str) {
        if (MediaRecorder.loadLibrary()) {
            this.mSuccessed = probe(str);
            if (this.mSuccessed && this._lyrics != null) {
                this.mLyrics = new String(this._lyrics);
            }
        }
    }
}
