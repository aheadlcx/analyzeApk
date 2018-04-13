package qsbk.app.ye.videotools.recorder;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import java.lang.ref.WeakReference;
import qsbk.app.ye.videotools.camera.VideoFrameSink;
import qsbk.app.ye.videotools.recorder.AudioRecorder.AudioBufferCallBack;

public class MediaRecorder implements VideoFrameSink {
    private static final String TAG = MediaRecorder.class.getSimpleName();
    private static boolean mLibraryLoadSuccess = false;
    private boolean mAudioRecordStarted = false;
    private AudioRecorder mAudioRecorder = null;
    private boolean mBitmapFlip = false;
    private int mBitmapHeight = 0;
    private int mBitmapRotate = 0;
    private int mBitmapWidth = 0;
    private b mEventHandler = null;
    private byte[] mLastData = null;
    private Object mLastFrameLock = new Object();
    private boolean mMusicFinished = false;
    private long mNativeContext;
    private OnCompletionListener mOnCompletionListener;
    private OnMusicCompletionListener mOnMusicCompletionListener;
    private boolean mPauseFlag = true;
    private boolean mPauseRequest = false;
    private Object mPauseRequestLock = new Object();
    private boolean mSegmentFlag = false;
    private int mSegmentIndex = 0;
    private boolean mStartFlag = false;

    public interface OnCompletionListener {
        void onCompletion(MediaRecorder mediaRecorder);
    }

    public interface OnMusicCompletionListener {
        void onCompletion(MediaRecorder mediaRecorder);
    }

    private class a implements AudioBufferCallBack {
        final /* synthetic */ MediaRecorder a;
        private MediaRecorder b = null;

        public a(MediaRecorder mediaRecorder, MediaRecorder mediaRecorder2) {
            this.a = mediaRecorder;
            this.b = mediaRecorder2;
        }

        public void hasData(byte[] bArr, int i) {
            if (this.b != null) {
                if (!this.b.mAudioRecordStarted) {
                    this.b.setaudiostart();
                }
                if (this.b.mStartFlag) {
                    this.b.encodeAudio(bArr, i);
                }
            }
        }
    }

    private class b extends Handler {
        final /* synthetic */ MediaRecorder a;
        private MediaRecorder b;

        public b(MediaRecorder mediaRecorder, MediaRecorder mediaRecorder2, Looper looper) {
            this.a = mediaRecorder;
            super(looper);
            this.b = mediaRecorder2;
        }

        public void handleMessage(Message message) {
            if (this.b.mNativeContext == 0) {
                Log.w(MediaRecorder.TAG, "player went away with unhandled events");
                return;
            }
            switch (message.what) {
                case 2:
                    if (this.a.mOnCompletionListener != null) {
                        this.a.mOnCompletionListener.onCompletion(this.b);
                        return;
                    } else {
                        this.a.stop();
                        return;
                    }
                case 6:
                    this.a.mMusicFinished = true;
                    if (this.a.mOnMusicCompletionListener != null) {
                        this.a.mOnMusicCompletionListener.onCompletion(this.b);
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    }

    private native void _FillBitmap(Bitmap bitmap, byte[] bArr, int i, int i2, int i3, boolean z);

    private native void _addSegment(int i);

    private native boolean _encodeVideo(byte[] bArr, long j, int i, int i2, int i3, int i4, boolean z, int i5);

    private native boolean _getSegmentInfo(SegmentInfo segmentInfo);

    private native void _pause();

    private native void _prepare();

    private native void _removeSegment(int i);

    private native void _resume();

    private native void _setSegmentInfo(SegmentInfo segmentInfo);

    private native void _start();

    private native void _stop(boolean z);

    static native void native_init();

    private native void native_setup(Object obj);

    public static native void showMemoryInfo();

    public native void _setOutputFilePrefix(String str);

    public native void _setRate(int i, int i2);

    public native void encodeAudio(byte[] bArr, int i);

    public native long getCurrentPosition();

    public native long getDuration();

    public native double getVolumeRate();

    public native void maxRecordTime(int i);

    public native void release();

    public native void setAudioInfo(int i, int i2);

    public native void setBackgroundAudio(String str);

    public native void setDimension(int i, int i2);

    public native void setFramesPerSecond(int i);

    public native void setOutputFile(String str);

    public static MediaRecorder create() {
        if (loadLibrary()) {
            return new MediaRecorder();
        }
        Log.e(TAG, "load library failed!!!");
        return null;
    }

    public void prepare() {
        prepare(false);
    }

    public void prepare(boolean z) {
        if (z) {
            setAudioInfo(0, 0);
        } else {
            this.mAudioRecorder = AudioRecorder.createAudioRecorder(new a(this, this));
            if (this.mAudioRecorder != null) {
                setAudioInfo(this.mAudioRecorder.getSampleRate(), this.mAudioRecorder.getChannels());
            } else {
                setAudioInfo(0, 0);
            }
        }
        _prepare();
    }

    public void start() {
        if (this.mAudioRecorder != null) {
            this.mAudioRecordStarted = false;
            this.mAudioRecorder.start();
        } else {
            this.mAudioRecordStarted = true;
        }
        _start();
        this.mPauseFlag = false;
        this.mStartFlag = true;
        this.mMusicFinished = false;
    }

    private void setaudiostart() {
        this.mAudioRecordStarted = true;
    }

    public void pause() {
        if (!this.mMusicFinished) {
            synchronized (this.mPauseRequestLock) {
                this.mPauseRequest = true;
            }
            _pause();
        }
    }

    public void resume() {
        _resume();
        this.mPauseFlag = false;
    }

    public void stop() {
        stop(false);
    }

    public void stop(boolean z) {
        this.mStartFlag = false;
        if (this.mAudioRecorder != null) {
            this.mAudioRecorder.stop();
        }
        _stop(z);
    }

    public boolean encodeVideo(byte[] bArr, long j, int i, int i2, int i3, int i4, boolean z) {
        if (!this.mStartFlag || this.mPauseFlag || !this.mAudioRecordStarted) {
            return false;
        }
        if (_encodeVideo(bArr, j, i, i2, i3, i4, z, this.mSegmentIndex)) {
            this.mStartFlag = false;
        }
        synchronized (this.mLastFrameLock) {
            this.mLastData = bArr;
            this.mBitmapWidth = i2;
            this.mBitmapHeight = i3;
            this.mBitmapRotate = i4;
            this.mBitmapFlip = z;
        }
        synchronized (this.mPauseRequestLock) {
            if (this.mPauseRequest) {
                this.mPauseFlag = true;
                this.mPauseRequest = false;
                if (this.mSegmentFlag) {
                    this.mSegmentIndex++;
                    _addSegment(this.mSegmentIndex);
                }
            }
        }
        return true;
    }

    public boolean encodeVideo(long j, long j2, int i, int i2, int i3, int i4, boolean z) {
        return false;
    }

    public void setOutputFilePrefix(String str) {
        this.mSegmentFlag = true;
        _setOutputFilePrefix(str);
    }

    public Bitmap getLastFrame() {
        if (this.mLastData == null) {
            return null;
        }
        int i = this.mBitmapWidth;
        int i2 = this.mBitmapHeight;
        if (this.mBitmapRotate % 180 != 0) {
            i = this.mBitmapHeight;
            i2 = this.mBitmapWidth;
        }
        Bitmap createBitmap = Bitmap.createBitmap(i, i2, Config.ARGB_8888);
        if (createBitmap == null) {
            return createBitmap;
        }
        synchronized (this.mLastFrameLock) {
            _FillBitmap(createBitmap, this.mLastData, this.mBitmapWidth, this.mBitmapHeight, this.mBitmapRotate, this.mBitmapFlip);
        }
        return createBitmap;
    }

    public boolean removeLastSegment() {
        if (!this.mSegmentFlag) {
            Log.e("QSBK", "++++++is not segment mode++++++");
            return false;
        } else if (this.mPauseRequest || !this.mPauseFlag) {
            Log.e("QSBK", "++++++has not paused sucessful++++++");
            return false;
        } else {
            this.mSegmentIndex--;
            _removeSegment(this.mSegmentIndex);
            return true;
        }
    }

    public SegmentInfo getSegmentInfo() {
        if (!this.mSegmentFlag) {
            return null;
        }
        SegmentInfo segmentInfo = new SegmentInfo();
        _getSegmentInfo(segmentInfo);
        Log.d(TAG, segmentInfo.toString());
        return segmentInfo;
    }

    public void setSegmentInfo(SegmentInfo segmentInfo) {
        if (this.mSegmentFlag) {
            if (segmentInfo.a > 0) {
                this.mSegmentIndex = segmentInfo.a - 1;
            }
            Log.d(TAG, segmentInfo.toString());
            _setSegmentInfo(segmentInfo);
        }
    }

    public void setRate(int i, int i2) {
        _setRate(i, i2);
    }

    public static final synchronized boolean loadLibrary() {
        boolean z;
        synchronized (MediaRecorder.class) {
            if (mLibraryLoadSuccess) {
                z = mLibraryLoadSuccess;
            } else {
                try {
                    System.loadLibrary("fdk-aac");
                    System.loadLibrary("x264");
                    System.loadLibrary("ffmpeg");
                    System.loadLibrary("qsbkvideotools");
                    native_init();
                    mLibraryLoadSuccess = true;
                } catch (UnsatisfiedLinkError e) {
                    e.printStackTrace();
                    mLibraryLoadSuccess = false;
                } catch (Exception e2) {
                    e2.printStackTrace();
                    mLibraryLoadSuccess = false;
                }
                z = mLibraryLoadSuccess;
            }
        }
        return z;
    }

    private MediaRecorder() {
        Looper myLooper = Looper.myLooper();
        if (myLooper != null) {
            this.mEventHandler = new b(this, this, myLooper);
        } else {
            myLooper = Looper.getMainLooper();
            if (myLooper != null) {
                this.mEventHandler = new b(this, this, myLooper);
            } else {
                this.mEventHandler = null;
            }
        }
        native_setup(new WeakReference(this));
    }

    private static void postEventFromNative(Object obj, int i, int i2, int i3) {
        MediaRecorder mediaRecorder = (MediaRecorder) ((WeakReference) obj).get();
        if (mediaRecorder != null && mediaRecorder.mEventHandler != null) {
            mediaRecorder.mEventHandler.sendMessage(mediaRecorder.mEventHandler.obtainMessage(i, i2, i3));
        }
    }

    public void setOnCompletionListener(OnCompletionListener onCompletionListener) {
        this.mOnCompletionListener = onCompletionListener;
    }

    public void setOnMusicCompletionListener(OnMusicCompletionListener onMusicCompletionListener) {
        this.mOnMusicCompletionListener = onMusicCompletionListener;
    }

    public boolean isStart() {
        return this.mStartFlag;
    }

    public boolean isPause() {
        return this.mPauseFlag || this.mPauseRequest;
    }

    public boolean isRecordEnable() {
        return this.mAudioRecorder != null ? this.mAudioRecorder.isRecordEnable() : false;
    }
}
