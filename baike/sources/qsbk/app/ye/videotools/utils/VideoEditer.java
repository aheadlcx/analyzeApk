package qsbk.app.ye.videotools.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.xiaomi.mipush.sdk.Constants;
import java.lang.ref.WeakReference;
import qsbk.app.ye.videotools.R;
import qsbk.app.ye.videotools.filter.VideoFilter;
import qsbk.app.ye.videotools.recorder.MediaRecorder;

public class VideoEditer {
    public static final int FRAMERATE_AUDO = -1;
    public static final int FRAMERATE_CAMERA = 15;
    private static final String TAG = VideoEditer.class.getSimpleName();
    private EventHandler mEventHandler = null;
    private boolean mLibraryLoadSuccess = false;
    private long mNativeContext;
    private OnCompletionListener mOnCompletionListener;
    private OnErrorListener mOnErrorListener;
    private OnProgressListener mOnProgressListener;

    private class EventHandler extends Handler {
        private static final int EDITCONTROLLER_COMPLETION = 1;
        private static final int EDITCONTROLLER_ERROR = 2;
        private static final int EDITCONTROLLER_NOP = 0;
        private static final int EDITCONTROLLER_PROGRESS = 3;
        private VideoEditer mVideoEditer;

        public EventHandler(VideoEditer videoEditer, Looper looper) {
            super(looper);
            this.mVideoEditer = videoEditer;
        }

        public void handleMessage(Message message) {
            if (this.mVideoEditer.mNativeContext == 0) {
                Log.w(VideoEditer.TAG, "player went away with unhandled events");
                return;
            }
            switch (message.what) {
                case 0:
                    return;
                case 1:
                    if (VideoEditer.this.mOnCompletionListener != null) {
                        VideoEditer.this.mOnCompletionListener.onCompletion(this.mVideoEditer);
                        return;
                    } else {
                        VideoEditer.this.stop();
                        return;
                    }
                case 2:
                    Log.e(VideoEditer.TAG, "Error (" + message.arg1 + Constants.ACCEPT_TIME_SEPARATOR_SP + Integer.toHexString(message.arg2) + ")");
                    VideoEditer.this.stop();
                    if (VideoEditer.this.mOnErrorListener != null) {
                        VideoEditer.this.mOnErrorListener.onError(this.mVideoEditer, message.arg1, message.arg2);
                        return;
                    }
                    return;
                case 3:
                    if (VideoEditer.this.mOnProgressListener != null) {
                        VideoEditer.this.mOnProgressListener.onProgress(this.mVideoEditer, message.arg1, message.arg2);
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    }

    public interface OnCompletionListener {
        void onCompletion(VideoEditer videoEditer);
    }

    public interface OnErrorListener {
        void onError(VideoEditer videoEditer, int i, int i2);
    }

    public interface OnProgressListener {
        void onProgress(VideoEditer videoEditer, int i, int i2);
    }

    private native void addVideoEndingMark(int[] iArr, int i, int i2, int i3);

    private native void addWaterMark(int[] iArr, int i, int i2, int i3, int i4, int i5);

    private native void native_setup(Object obj, String str);

    private native void setFilter(int i, boolean z, int[] iArr, int i2, int i3, int[] iArr2, int i4, int i5, int[] iArr3, int i6, int i7, int[] iArr4, int i8, int i9, int[] iArr5, int i10, int i11, int i12);

    public native void addAudioDataSource(String str);

    public native void addDataSource(String str);

    public native void addDataSource(String[] strArr, int[] iArr);

    public native void prepare();

    public native void release();

    public native void setBitrate(int i);

    public native void setCropArea(int i, int i2, int i3, int i4);

    public native void setFrameRate(int i);

    public native void setSegment(int i, int i2);

    public native void setTargetPath(String str);

    public native void setTargetSize(int i, int i2);

    public native void setVolumeRate(double d);

    public native void start();

    public native void stop();

    public static VideoEditer create() {
        return create(null);
    }

    public static VideoEditer create(String str) {
        if (MediaRecorder.loadLibrary()) {
            VideoEditer videoEditer = new VideoEditer(str);
            videoEditer.mLibraryLoadSuccess = true;
            return videoEditer;
        }
        Log.e(TAG, "load library failed!!!");
        return null;
    }

    public void setFilter(VideoFilter videoFilter, boolean z) {
        setFilter(videoFilter.type, z, videoFilter.mTexture1, videoFilter.mWidth1, videoFilter.mHeight1, videoFilter.mTexture2, videoFilter.mWidth2, videoFilter.mHeight2, videoFilter.mTexture3, videoFilter.mWidth3, videoFilter.mHeight3, videoFilter.mTexture4, videoFilter.mWidth4, videoFilter.mHeight4, videoFilter.mTexture5, videoFilter.mWidth5, videoFilter.mHeight5, 0);
    }

    public void setFilter(VideoFilter videoFilter, boolean z, int i) {
        setFilter(videoFilter.type, z, videoFilter.mTexture1, videoFilter.mWidth1, videoFilter.mHeight1, videoFilter.mTexture2, videoFilter.mWidth2, videoFilter.mHeight2, videoFilter.mTexture3, videoFilter.mWidth3, videoFilter.mHeight3, videoFilter.mTexture4, videoFilter.mWidth4, videoFilter.mHeight4, videoFilter.mTexture5, videoFilter.mWidth5, videoFilter.mHeight5, i);
    }

    public void addVideoEndingMark(Bitmap bitmap, int i) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int[] iArr = new int[(width * height)];
        bitmap.getPixels(iArr, 0, width, 0, 0, width, height);
        bitmap.recycle();
        addVideoEndingMark(iArr, width, height, i);
    }

    public void addWaterMark(Bitmap bitmap, int i, int i2) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int[] iArr = new int[(width * height)];
        bitmap.getPixels(iArr, 0, width, 0, 0, width, height);
        bitmap.recycle();
        addWaterMark(iArr, width, height, i, -1, i2);
    }

    public void addWaterMark(Context context, int i, int i2, long j) {
        Bitmap waterBitmap = getWaterBitmap(context, j);
        int width = waterBitmap.getWidth();
        int height = waterBitmap.getHeight();
        int[] iArr = new int[(width * height)];
        waterBitmap.getPixels(iArr, 0, width, 0, 0, width, height);
        waterBitmap.recycle();
        addWaterMark(iArr, width, height, -1, i, i2);
    }

    private Bitmap getWaterBitmap(Context context, long j) {
        int length = String.valueOf(j).length();
        int i = length > 5 ? (length - 6) * 10 : -6;
        Bitmap createBitmap = Bitmap.createBitmap(i + 118, 47, Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        Paint paint = new Paint(5);
        canvas.drawBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.watermark), null, new Rect(i + 6, 0, i + 118, 27), paint);
        if (j > 0) {
            String str = "热猫号:" + j;
            paint.setTextSize(16.0f);
            paint.setColor(-1);
            paint.setTypeface(Typeface.MONOSPACE);
            paint.setShadowLayer(0.4f, 0.0f, 0.4f, -16777216);
            canvas.drawText(str, 0, str.length(), 0.0f, 45.0f, paint);
        }
        return createBitmap;
    }

    public void setFilter(VideoFilter videoFilter) {
        setFilter(videoFilter, false);
    }

    private VideoEditer(String str) {
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
        native_setup(new WeakReference(this), str);
    }

    private static void postEventFromNative(Object obj, int i, int i2, int i3) {
        VideoEditer videoEditer = (VideoEditer) ((WeakReference) obj).get();
        if (videoEditer != null && videoEditer.mEventHandler != null) {
            videoEditer.mEventHandler.sendMessage(videoEditer.mEventHandler.obtainMessage(i, i2, i3));
        }
    }

    public void setOnCompletionListener(OnCompletionListener onCompletionListener) {
        this.mOnCompletionListener = onCompletionListener;
    }

    public void setOnErrorListener(OnErrorListener onErrorListener) {
        this.mOnErrorListener = onErrorListener;
    }

    public void setOnProgressListener(OnProgressListener onProgressListener) {
        this.mOnProgressListener = onProgressListener;
    }
}
