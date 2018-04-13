package qsbk.app.ye.videotools.player;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.Surface;
import com.xiaomi.mipush.sdk.Constants;
import java.lang.ref.WeakReference;
import qsbk.app.ye.videotools.filter.VideoFilter;
import qsbk.app.ye.videotools.recorder.MediaRecorder;

public class VideoPlayer {
    public static final int KPLAYER_ERROR_BROKEN_FILE = 2;
    public static final int KPLAYER_ERROR_CONNECT_FAILED = 4;
    public static final int KPLAYER_ERROR_DATASOURCE = 3;
    public static final int KPLAYER_ERROR_NONE = 0;
    public static final int KPLAYER_ERROR_NOSTREAM = 5;
    public static final int KPLAYER_ERROR_NO_SUCH_FILE = 1;
    public static final int KPLAYER_ERROR_PUSHFINISHED = 6;
    public static final int KPLAYER_ERROR_UNKNOWN = 7;
    public static final int KPLAYER_INFO_BUFFERING_END = 3;
    public static final int KPLAYER_INFO_BUFFERING_START = 2;
    public static final int KPLAYER_INFO_CACHEPERCENT = 1;
    public static final int KPLAYER_INFO_NONE = 0;
    private static final String TAG = VideoPlayer.class.getSimpleName();
    private a mEventHandler = null;
    private boolean mLibraryLoadSuccess = false;
    private long mNativeContext;
    private OnCompletionListener mOnCompletionListener;
    private OnErrorListener mOnErrorListener;
    private OnFirstFrameListener mOnFirstFrameListener;
    private OnInfoListener mOnInfoListener;
    private OnPreparedListener mOnPreparedListener;
    private OnSeekCompleteListener mOnSeekCompleteListener;

    public interface OnCompletionListener {
        void onCompletion(VideoPlayer videoPlayer);
    }

    public interface OnErrorListener {
        void onError(VideoPlayer videoPlayer, int i, int i2);
    }

    public interface OnFirstFrameListener {
        void onFirstFrame(VideoPlayer videoPlayer);
    }

    public interface OnInfoListener {
        void onInfo(VideoPlayer videoPlayer, int i, int i2);
    }

    public interface OnPreparedListener {
        void onPrepared(VideoPlayer videoPlayer);
    }

    public interface OnSeekCompleteListener {
        void onSeekComplete(VideoPlayer videoPlayer);
    }

    private class a extends Handler {
        final /* synthetic */ VideoPlayer a;
        private VideoPlayer b;

        public a(VideoPlayer videoPlayer, VideoPlayer videoPlayer2, Looper looper) {
            this.a = videoPlayer;
            super(looper);
            this.b = videoPlayer2;
        }

        public void handleMessage(Message message) {
            if (this.b.mNativeContext == 0) {
                Log.w(VideoPlayer.TAG, "player went away with unhandled events");
                return;
            }
            switch (message.what) {
                case 0:
                    return;
                case 1:
                    if (this.a.mOnPreparedListener != null) {
                        this.a.mOnPreparedListener.onPrepared(this.b);
                        return;
                    } else {
                        this.a.start();
                        return;
                    }
                case 2:
                    if (this.a.mOnCompletionListener != null) {
                        this.a.mOnCompletionListener.onCompletion(this.b);
                        return;
                    } else {
                        this.a.stop();
                        return;
                    }
                case 3:
                    if (this.a.mOnSeekCompleteListener != null) {
                        this.a.mOnSeekCompleteListener.onSeekComplete(this.b);
                        return;
                    }
                    return;
                case 4:
                    Log.e(VideoPlayer.TAG, "Error (" + message.arg1 + Constants.ACCEPT_TIME_SEPARATOR_SP + Integer.toHexString(message.arg2) + ")");
                    this.a._stop();
                    if (this.a.mOnErrorListener != null) {
                        this.a.mOnErrorListener.onError(this.b, message.arg1, message.arg2);
                        return;
                    }
                    return;
                case 5:
                    if (this.a.mOnInfoListener != null) {
                        this.a.mOnInfoListener.onInfo(this.b, message.arg1, message.arg2);
                        return;
                    }
                    return;
                case 7:
                    if (this.a.mOnFirstFrameListener != null) {
                        this.a.mOnFirstFrameListener.onFirstFrame(this.b);
                        return;
                    }
                    return;
                default:
                    Log.e(VideoPlayer.TAG, "Unknown message type " + message.what);
                    return;
            }
        }
    }

    private native byte[] _getLyrics();

    private native void _pause();

    private native void _prepareAsync();

    private native void _release();

    private native void _seekTo(long j);

    private native void _setDataSource(String str, String str2, long j, long j2, String str3);

    private native void _setDataSource(String[] strArr, String str, int[] iArr);

    private native void _start();

    private native void _stop();

    private native void native_setup(Object obj, boolean z);

    private native void setDisplay(Object obj, int i, int i2);

    private native void setFilter(int i, boolean z, int[] iArr, int i2, int i3, int[] iArr2, int i4, int i5, int[] iArr3, int i6, int i7, int[] iArr4, int i8, int i9, int[] iArr5, int i10, int i11, int i12);

    public native String getCopyright();

    public native long getCurrentPosition();

    public native long getDuration();

    public native int getVideoHeight();

    public native int getVideoWidth();

    public native void resetSize(int i, int i2);

    public native void setLoop(int i);

    public native void setSegmentInfo(int[] iArr);

    public native void setVolumeRate(double d);

    public static VideoPlayer create() {
        return create(false);
    }

    public static VideoPlayer create(boolean z) {
        if (MediaRecorder.loadLibrary()) {
            VideoPlayer videoPlayer = new VideoPlayer(z);
            videoPlayer.mLibraryLoadSuccess = true;
            return videoPlayer;
        }
        Log.e(TAG, "load library failed!!!");
        return null;
    }

    private VideoPlayer(boolean z) {
        Looper myLooper = Looper.myLooper();
        if (myLooper != null) {
            this.mEventHandler = new a(this, this, myLooper);
        } else {
            myLooper = Looper.getMainLooper();
            if (myLooper != null) {
                this.mEventHandler = new a(this, this, myLooper);
            } else {
                this.mEventHandler = null;
            }
        }
        native_setup(new WeakReference(this), z);
    }

    public void setDataSource(String str) {
        setDataSource(str, null, 0, 0, null);
    }

    public void setDataSource(String str, String str2) {
        setDataSource(str, null, 0, 0, str2);
    }

    public void setDataSource(String str, long j) {
        setDataSource(str, null, j, 0, null);
    }

    public void setDataSource(String str, String str2, long j) {
        setDataSource(str, str2, j, 0, null);
    }

    public void setDataSource(String str, long j, String str2) {
        setDataSource(str, null, j, 0, str2);
    }

    public void setDataSource(String str, long j, long j2) {
        setDataSource(str, null, j, j2, null);
    }

    public void setDataSource(String str, String str2, long j, long j2) {
        setDataSource(str, str2, j, j2, null);
    }

    public void setDataSource(String str, String str2, long j, long j2, String str3) {
        if (this.mLibraryLoadSuccess) {
            _setDataSource(str, str2, j, j2, str3);
        }
    }

    public void setDataSource(String[] strArr, String str, int[] iArr) {
        if (this.mLibraryLoadSuccess) {
            _setDataSource(strArr, str, iArr);
        }
    }

    public void prepareAsync() {
        if (this.mLibraryLoadSuccess) {
            _prepareAsync();
        }
    }

    public void start() {
        if (this.mLibraryLoadSuccess) {
            _start();
        }
    }

    public void stop() {
        if (this.mLibraryLoadSuccess) {
            _stop();
        }
    }

    public void pause() {
        if (this.mLibraryLoadSuccess) {
            _pause();
        }
    }

    public void seekTo(long j) {
        if (this.mLibraryLoadSuccess) {
            _seekTo(j);
        }
    }

    public String getLyrics() {
        byte[] _getLyrics = _getLyrics();
        if (_getLyrics != null) {
            return new String(_getLyrics);
        }
        return null;
    }

    public void setSurface(Surface surface, int i, int i2) {
        setDisplay(surface, i, i2);
    }

    public void setFilter(VideoFilter videoFilter) {
        setFilter(videoFilter, false);
    }

    public void setFilter(VideoFilter videoFilter, boolean z) {
        setFilter(videoFilter.type, z, videoFilter.mTexture1, videoFilter.mWidth1, videoFilter.mHeight1, videoFilter.mTexture2, videoFilter.mWidth2, videoFilter.mHeight2, videoFilter.mTexture3, videoFilter.mWidth3, videoFilter.mHeight3, videoFilter.mTexture4, videoFilter.mWidth4, videoFilter.mHeight4, videoFilter.mTexture5, videoFilter.mWidth5, videoFilter.mHeight5, 0);
    }

    public void setFilter(VideoFilter videoFilter, boolean z, int i) {
        setFilter(videoFilter.type, z, videoFilter.mTexture1, videoFilter.mWidth1, videoFilter.mHeight1, videoFilter.mTexture2, videoFilter.mWidth2, videoFilter.mHeight2, videoFilter.mTexture3, videoFilter.mWidth3, videoFilter.mHeight3, videoFilter.mTexture4, videoFilter.mWidth4, videoFilter.mHeight4, videoFilter.mTexture5, videoFilter.mWidth5, videoFilter.mHeight5, i);
    }

    public void release() {
        _release();
    }

    private static void postEventFromNative(Object obj, int i, int i2, int i3) {
        VideoPlayer videoPlayer = (VideoPlayer) ((WeakReference) obj).get();
        if (videoPlayer != null && videoPlayer.mEventHandler != null) {
            videoPlayer.mEventHandler.sendMessage(videoPlayer.mEventHandler.obtainMessage(i, i2, i3));
        }
    }

    public void setOnPreparedListener(OnPreparedListener onPreparedListener) {
        this.mOnPreparedListener = onPreparedListener;
    }

    public void setOnCompletionListener(OnCompletionListener onCompletionListener) {
        this.mOnCompletionListener = onCompletionListener;
    }

    public void setOnSeekCompleteListener(OnSeekCompleteListener onSeekCompleteListener) {
        this.mOnSeekCompleteListener = onSeekCompleteListener;
    }

    public void setOnErrorListener(OnErrorListener onErrorListener) {
        this.mOnErrorListener = onErrorListener;
    }

    public void setOnInfoListener(OnInfoListener onInfoListener) {
        this.mOnInfoListener = onInfoListener;
    }

    public void setOnFirstFrameListener(OnFirstFrameListener onFirstFrameListener) {
        this.mOnFirstFrameListener = onFirstFrameListener;
    }
}
