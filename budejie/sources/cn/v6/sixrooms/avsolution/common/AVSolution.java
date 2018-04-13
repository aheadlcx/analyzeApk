package cn.v6.sixrooms.avsolution.common;

import android.util.Log;

public class AVSolution {
    private static final String TAG = "AVSolution";
    private static long mJNI = 0;
    private IAVSolution mCallback;

    private native long initialize(int i);

    private native void release(long j);

    public AVSolution(IAVSolution iAVSolution) {
        this.mCallback = iAVSolution;
    }

    public void openAVSolution() throws SolutionException {
        if (this.mCallback == null) {
            throw new SolutionException(SolutionException.InvalidParameter);
        }
        long initialize = initialize(4);
        mJNI = initialize;
        if (initialize <= 0) {
            throw new SolutionException(SolutionException.JNIError);
        }
    }

    public void closeAVSolution() {
        release(mJNI);
        mJNI = 0;
    }

    private void reportException(int i) {
        this.mCallback.reportException(i);
    }

    static {
        try {
            System.loadLibrary(TAG);
            System.loadLibrary("PlayerManager");
        } catch (Exception e) {
            Log.w(TAG, "Can't load library libAVSolution.so");
        }
    }
}
