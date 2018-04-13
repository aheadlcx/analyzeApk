package qsbk.app.ye.videotools.live;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.xiaomi.mipush.sdk.Constants;
import java.lang.ref.WeakReference;
import qsbk.app.ye.videotools.camera.VideoFrameSink;
import qsbk.app.ye.videotools.recorder.AudioRecorder;
import qsbk.app.ye.videotools.recorder.AudioRecorder.AudioBufferCallBack;
import qsbk.app.ye.videotools.recorder.MediaRecorder;

public class MediaPublisher implements VideoFrameSink {
    public static final int KPUBLISHER_ERROR_CONNECT_FAILED = 1;
    public static final int KPUBLISHER_ERROR_NONE = 0;
    public static final int KPUBLISHER_ERROR_NOSTREAM = 2;
    public static final int KPUBLISHER_ERROR_PUSH = 3;
    public static final int KPUBLISHER_FASTMODE_NONE = 0;
    public static final int KPUBLISHER_FASTMODE_WW = 1;
    public static final int KPUBLISHER_INFO_BADNETWORK = 2;
    public static final int KPUBLISHER_INFO_NONE = 0;
    public static final int KPUBLISHER_INFO_QUALITY = 1;
    private static final String TAG = MediaPublisher.class.getSimpleName();
    private static boolean mLibraryLoadSuccess = false;
    private boolean mAudioRecordStarted = false;
    private AudioRecorder mAudioRecorder = null;
    private EventHandler mEventHandler = null;
    private int mFastMode = 0;
    private long mNativeContext;
    private OnConnectedListener mOnConnectedListener;
    private OnErrorListener mOnErrorListener;
    private OnInfoListener mOnInfoListener;
    private boolean mStartFlag = false;

    private class AudioDataCallback implements AudioBufferCallBack {
        private MediaPublisher mMediaPublisher = null;

        public AudioDataCallback(MediaPublisher mediaPublisher) {
            this.mMediaPublisher = mediaPublisher;
        }

        public void hasData(byte[] bArr, int i) {
            if (this.mMediaPublisher != null) {
                if (!this.mMediaPublisher.mAudioRecordStarted) {
                    this.mMediaPublisher.mAudioRecordStarted = true;
                }
                this.mMediaPublisher.encodeAudio(bArr, i);
            }
        }
    }

    private class EventHandler extends Handler {
        private static final int KPUBLISHER_CONNECTED = 1;
        private static final int KPUBLISHER_ERROR = 2;
        private static final int KPUBLISHER_INFO = 3;
        private MediaPublisher mMediaPublisher;

        public EventHandler(MediaPublisher mediaPublisher, Looper looper) {
            super(looper);
            this.mMediaPublisher = mediaPublisher;
        }

        public void handleMessage(Message message) {
            if (this.mMediaPublisher.mNativeContext == 0) {
                Log.w(MediaPublisher.TAG, "player went away with unhandled events");
                return;
            }
            switch (message.what) {
                case 1:
                    if (MediaPublisher.this.mOnConnectedListener != null) {
                        MediaPublisher.this.mOnConnectedListener.onConnected(this.mMediaPublisher);
                        return;
                    }
                    return;
                case 2:
                    Log.e(MediaPublisher.TAG, "Error (" + message.arg1 + Constants.ACCEPT_TIME_SEPARATOR_SP + message.arg2 + ")");
                    if (MediaPublisher.this.mOnErrorListener != null) {
                        MediaPublisher.this.mOnErrorListener.onError(this.mMediaPublisher, message.arg1, message.arg2);
                        return;
                    }
                    return;
                case 3:
                    Log.d(MediaPublisher.TAG, "Info (" + message.arg1 + Constants.ACCEPT_TIME_SEPARATOR_SP + message.arg2 + ")");
                    if (MediaPublisher.this.mOnInfoListener != null) {
                        MediaPublisher.this.mOnInfoListener.onInfo(this.mMediaPublisher, message.arg1, message.arg2);
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    }

    public interface OnConnectedListener {
        void onConnected(MediaPublisher mediaPublisher);
    }

    public interface OnErrorListener {
        void onError(MediaPublisher mediaPublisher, int i, int i2);
    }

    public interface OnInfoListener {
        void onInfo(MediaPublisher mediaPublisher, int i, int i2);
    }

    private native boolean _encodeVideo(byte[] bArr, long j, int i, int i2, int i3, int i4, boolean z);

    private native boolean _encodeVideoAddress(long j, long j2, int i, int i2, int i3, int i4, boolean z);

    private native void _prepare();

    private native void _start();

    private native void _stop();

    static native void native_init();

    private native void native_setup(Object obj, int i);

    public native void encodeAudio(byte[] bArr, int i);

    public native void getBitrateInfo(StatisticsInfo statisticsInfo);

    public native void mute(boolean z);

    public native void setAudioInfo(int i, int i2);

    public native void setDimension(int i, int i2);

    public native void setURL(String str);

    public static MediaPublisher create() {
        return create(0);
    }

    public static MediaPublisher create(int i) {
        if (MediaRecorder.loadLibrary()) {
            return new MediaPublisher(i);
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
            int i = 32000;
            if (this.mFastMode == 1) {
                i = 16000;
            }
            this.mAudioRecorder = AudioRecorder.createAudioRecorder(new AudioDataCallback(this), i);
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
        this.mStartFlag = true;
    }

    public void stop() {
        this.mStartFlag = false;
        if (this.mAudioRecorder != null) {
            this.mAudioRecorder.stop();
        }
        _stop();
    }

    public boolean encodeVideo(byte[] bArr, long j, int i, int i2, int i3, int i4, boolean z) {
        if (!this.mStartFlag || !this.mAudioRecordStarted) {
            return false;
        }
        if (_encodeVideo(bArr, j, i, i2, i3, i4, z)) {
            this.mStartFlag = false;
        }
        return true;
    }

    public boolean encodeVideo(long j, long j2, int i, int i2, int i3, int i4, boolean z) {
        if (!this.mStartFlag || !this.mAudioRecordStarted) {
            return false;
        }
        if (_encodeVideoAddress(j, j2, i, i2, i3, i4, z)) {
            this.mStartFlag = false;
        }
        return true;
    }

    public void setBitrateLevels(int[] iArr) {
    }

    private MediaPublisher(int i) {
        Looper myLooper = Looper.myLooper();
        if (myLooper != null) {
            this.mEventHandler = new EventHandler(this, myLooper);
        } else {
            myLooper = Looper.getMainLooper();
            if (myLooper != null) {
                this.mEventHandler = new EventHandler(this, myLooper);
            } else {
                this.mEventHandler = null;
            }
        }
        this.mFastMode = i;
        native_setup(new WeakReference(this), this.mFastMode);
    }

    private static void postEventFromNative(Object obj, int i, int i2, int i3) {
        MediaPublisher mediaPublisher = (MediaPublisher) ((WeakReference) obj).get();
        if (mediaPublisher != null && mediaPublisher.mEventHandler != null) {
            mediaPublisher.mEventHandler.sendMessage(mediaPublisher.mEventHandler.obtainMessage(i, i2, i3));
        }
    }

    public void setOnConnectListener(OnConnectedListener onConnectedListener) {
        this.mOnConnectedListener = onConnectedListener;
    }

    public void setOnErrorListener(OnErrorListener onErrorListener) {
        this.mOnErrorListener = onErrorListener;
    }

    public void setOnInfoListener(OnInfoListener onInfoListener) {
        this.mOnInfoListener = onInfoListener;
    }

    public boolean isRecordEnable() {
        return this.mAudioRecorder != null ? this.mAudioRecorder.isRecordEnable() : false;
    }
}
