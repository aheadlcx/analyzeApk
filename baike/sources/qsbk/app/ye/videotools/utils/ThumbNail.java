package qsbk.app.ye.videotools.utils;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.util.Log;
import qsbk.app.ye.videotools.filter.VideoFilter;
import qsbk.app.ye.videotools.recorder.MediaRecorder;

public class ThumbNail {
    private static final int MAX_COUNT = 10;
    private static final String TAG = ThumbNail.class.getSimpleName();
    private int mCount = 0;
    private int mHeight = 0;
    private boolean mLibraryLoadSuccess = false;
    private long mNativeContext;
    private int mWidth = 0;

    private native boolean _getFrame(Bitmap bitmap, int i);

    private native boolean _getFrameAt(Bitmap bitmap, long j);

    private native void _release();

    private native int getHeight();

    private native int getWidth();

    private native void native_setup(String str, int i, int i2, int i3);

    private native void native_setup(String[] strArr, int[] iArr, int i, int i2);

    private native void setFilter(int i, boolean z, int[] iArr, int i2, int i3, int[] iArr2, int i4, int i5, int[] iArr3, int i6, int i7, int[] iArr4, int i8, int i9, int[] iArr5, int i10, int i11);

    public native int getFrameRate();

    public static ThumbNail create(String str, int i, int i2) {
        return create(str, i, i2, 1);
    }

    public static ThumbNail create(String str, int i, int i2, int i3) {
        if (i3 > 10) {
            i3 = 10;
        }
        if (MediaRecorder.loadLibrary()) {
            ThumbNail thumbNail = new ThumbNail(str, i, i2, i3);
            thumbNail.mLibraryLoadSuccess = true;
            thumbNail.mCount = i3;
            return thumbNail;
        }
        Log.e(TAG, "load library failed!!!");
        return null;
    }

    public static ThumbNail create(String[] strArr, int[] iArr, int i, int i2) {
        if (MediaRecorder.loadLibrary()) {
            ThumbNail thumbNail = new ThumbNail(strArr, iArr, i, i2);
            thumbNail.mLibraryLoadSuccess = true;
            return thumbNail;
        }
        Log.e(TAG, "load library failed!!!");
        return null;
    }

    public Bitmap getFrame() {
        return getFrame(0);
    }

    public Bitmap getFrame(int i) {
        if (i < 0 || i >= this.mCount) {
            return null;
        }
        Bitmap createBitmap = Bitmap.createBitmap(this.mWidth, this.mHeight, Config.ARGB_8888);
        if (createBitmap == null || !_getFrame(createBitmap, i)) {
            return null;
        }
        return createBitmap;
    }

    public Bitmap getFrameAt(long j) {
        Bitmap createBitmap = Bitmap.createBitmap(this.mWidth, this.mHeight, Config.ARGB_8888);
        return (createBitmap == null || !_getFrameAt(createBitmap, j)) ? null : createBitmap;
    }

    public void setFilter(VideoFilter videoFilter, boolean z) {
        setFilter(videoFilter.type, z, videoFilter.mTexture1, videoFilter.mWidth1, videoFilter.mHeight1, videoFilter.mTexture2, videoFilter.mWidth2, videoFilter.mHeight2, videoFilter.mTexture3, videoFilter.mWidth3, videoFilter.mHeight3, videoFilter.mTexture4, videoFilter.mWidth4, videoFilter.mHeight4, videoFilter.mTexture5, videoFilter.mWidth5, videoFilter.mHeight5);
    }

    public void setFilter(VideoFilter videoFilter) {
        setFilter(videoFilter, false);
    }

    public void release() {
        _release();
    }

    private ThumbNail(String str, int i, int i2, int i3) {
        this.mWidth = i;
        this.mHeight = i2;
        native_setup(str, i, i2, i3);
    }

    private ThumbNail(String[] strArr, int[] iArr, int i, int i2) {
        this.mWidth = i;
        this.mHeight = i2;
        native_setup(strArr, iArr, i, i2);
    }
}
