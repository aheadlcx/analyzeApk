package qsbk.app.ye.videotools.camera;

import android.view.Surface;
import java.lang.ref.WeakReference;
import qsbk.app.ye.videotools.filter.VideoFilter;
import qsbk.app.ye.videotools.recorder.MediaRecorder;

public class CameraFilter {
    private long mNativeContext;
    private CameraFilterTextureListener mTextureListener = null;

    public interface CameraFilterTextureListener {
        int onProcessTexture(int i);

        void onSurfaceTextureCreate(boolean z);

        void onSurfaceTextureFrame(long j);

        int onSurfaceTextureUpdate();
    }

    private native void _init(Object obj, Object obj2, int i, int i2, int i3, int i4, int i5, int i6);

    private native void setFilter(int i, boolean z, int[] iArr, int i2, int i3, int[] iArr2, int i4, int i5, int[] iArr3, int i6, int i7, int[] iArr4, int i8, int i9, int[] iArr5, int i10, int i11);

    public native void release();

    public native void resetSize(int i, int i2);

    public native void setImageSize(int i, int i2);

    public native void setMatrix(float[] fArr);

    public native void updateTexture();

    public CameraFilter(Surface surface, CameraFilterTextureListener cameraFilterTextureListener, int i, int i2, int i3, int i4, int i5, int i6) {
        if (MediaRecorder.loadLibrary()) {
            this.mTextureListener = cameraFilterTextureListener;
            _init(new WeakReference(this), surface, i, i2, i3, i4, i5, i6);
        }
    }

    public void setFilter(VideoFilter videoFilter, boolean z) {
        setFilter(videoFilter.type, z, videoFilter.mTexture1, videoFilter.mWidth1, videoFilter.mHeight1, videoFilter.mTexture2, videoFilter.mWidth2, videoFilter.mHeight2, videoFilter.mTexture3, videoFilter.mWidth3, videoFilter.mHeight3, videoFilter.mTexture4, videoFilter.mWidth4, videoFilter.mHeight4, videoFilter.mTexture5, videoFilter.mWidth5, videoFilter.mHeight5);
    }

    private static void nativeSurfaceTextureCreate(Object obj, boolean z) {
        CameraFilter cameraFilter = (CameraFilter) ((WeakReference) obj).get();
        if (cameraFilter != null && cameraFilter.mTextureListener != null) {
            cameraFilter.mTextureListener.onSurfaceTextureCreate(z);
        }
    }

    private static int nativeSurfaceTextureUpdate(Object obj) {
        CameraFilter cameraFilter = (CameraFilter) ((WeakReference) obj).get();
        if (cameraFilter == null) {
            return 0;
        }
        return cameraFilter.mTextureListener != null ? cameraFilter.mTextureListener.onSurfaceTextureUpdate() : 0;
    }

    private static int nativeProcessTexture(Object obj, int i) {
        CameraFilter cameraFilter = (CameraFilter) ((WeakReference) obj).get();
        if (cameraFilter == null) {
            return 0;
        }
        return cameraFilter.mTextureListener != null ? cameraFilter.mTextureListener.onProcessTexture(i) : 0;
    }

    private static void nativeSurfaceTextureFrame(Object obj, long j) {
        CameraFilter cameraFilter = (CameraFilter) ((WeakReference) obj).get();
        if (cameraFilter != null && cameraFilter.mTextureListener != null) {
            cameraFilter.mTextureListener.onSurfaceTextureFrame(j);
        }
    }
}
