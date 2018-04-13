package cn.v6.sixrooms.avsolution.player;

import android.util.Log;
import android.view.Surface;
import android.view.SurfaceView;
import cn.v6.sixrooms.avsolution.common.Event;
import cn.v6.sixrooms.avsolution.common.SolutionException;

public class PlayerManager {
    private static final String TAG = "PlayerManager";
    private static long mJNI = 0;
    private IPlayerManager mCallback;
    private GLESYUVRenderer mRenderer;
    private int mState;
    private SurfaceView mSurface;
    private boolean mute = false;

    private native long initialize(String[] strArr);

    private native int mute(long j);

    private native int pause(long j);

    private native void release(long j);

    private native int resume(long j);

    private native int seek(long j, long j2);

    public PlayerManager(IPlayerManager iPlayerManager) {
        this.mCallback = iPlayerManager;
        this.mState = 0;
    }

    public PlayerManager(IPlayerManager iPlayerManager, GLESYUVRenderer gLESYUVRenderer, String str) throws SolutionException {
        if (iPlayerManager == null || gLESYUVRenderer == null || str == null) {
            throw new SolutionException(SolutionException.InvalidParameter);
        }
        this.mCallback = iPlayerManager;
        this.mRenderer = gLESYUVRenderer;
        long initialize = initialize(new String[]{str});
        mJNI = initialize;
        if (initialize == 0) {
            throw new SolutionException(SolutionException.JNIError);
        }
    }

    public void openPlayer(SurfaceView surfaceView, String str) throws SolutionException {
        if (this.mCallback == null || surfaceView == null || str == null) {
            throw new SolutionException(SolutionException.InvalidParameter);
        }
        this.mSurface = surfaceView;
        if (mJNI != 0) {
            release(mJNI);
        }
        long initialize = initialize(new String[]{str});
        mJNI = initialize;
        if (initialize == 0) {
            throw new SolutionException(SolutionException.JNIError);
        }
    }

    public void closePlayer() {
        release(mJNI);
        mJNI = 0;
    }

    public boolean isPlayerMute() {
        return this.mute;
    }

    public void pausePlayer() {
    }

    public void resumePlayer() {
    }

    public void mutePlayer() {
        this.mute = !this.mute;
        mute(mJNI);
    }

    public void seekPlayer(long j) {
    }

    static {
        try {
            System.loadLibrary(TAG);
        } catch (Exception e) {
            Log.w(TAG, "Can't load library libPlayerManager.so");
        }
    }

    public Surface getSurface() {
        if (this.mRenderer != null) {
            return this.mRenderer.getHolder().getSurface();
        }
        return null;
    }

    public long getRenderer() {
        if (this.mRenderer != null) {
            return this.mRenderer.getJNI();
        }
        return 0;
    }

    public void reportEvent(Event event) {
        if (this.mCallback == null) {
            return;
        }
        if (event.type == 14) {
            this.mCallback.reportError(event);
            return;
        }
        this.mState = event.type;
        this.mCallback.reportEvent(event);
    }

    public void reportException(int i) {
        if (this.mCallback != null) {
            this.mCallback.reportException(i);
        }
    }
}
