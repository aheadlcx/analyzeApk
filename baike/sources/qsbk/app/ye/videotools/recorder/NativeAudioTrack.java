package qsbk.app.ye.videotools.recorder;

import android.media.AudioTrack;
import android.util.Log;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class NativeAudioTrack {
    private byte[] mAudioBuffer = null;
    private AudioTrack mAudioTrack = null;
    private Lock mLock = null;
    private int mMinBufferSize = 0;
    private long mNativeContext;
    private boolean mStartFlag = false;
    private Thread mThread = null;
    private boolean mThreadFlag = false;

    private native int _FillUpCallBack(byte[] bArr, int i);

    public NativeAudioTrack(int i, int i2) {
        int i3 = 4;
        if (i2 > 1) {
            i3 = 12;
        }
        this.mMinBufferSize = AudioTrack.getMinBufferSize(i, i3, 2);
        this.mAudioTrack = new AudioTrack(3, i, i3, 2, this.mMinBufferSize, 1);
        this.mLock = new ReentrantLock();
        if (4096 > this.mMinBufferSize) {
            this.mMinBufferSize = (((4096 + this.mMinBufferSize) - 1) / this.mMinBufferSize) * this.mMinBufferSize;
        }
        Log.i("QSBK", "audio track buffer size:" + this.mMinBufferSize);
    }

    private void prepare() {
        this.mThreadFlag = true;
        this.mAudioBuffer = new byte[this.mMinBufferSize];
        this.mThread = new Thread(new a(this));
        this.mThread.start();
    }

    private void start() {
        this.mStartFlag = true;
        if (this.mAudioTrack != null) {
            try {
                this.mAudioTrack.play();
            } catch (IllegalStateException e) {
            }
        }
    }

    private void pause() {
        if (this.mAudioTrack != null) {
            try {
                this.mAudioTrack.pause();
            } catch (IllegalStateException e) {
            }
        }
    }

    private void resume() {
        if (this.mAudioTrack != null) {
            try {
                this.mAudioTrack.play();
            } catch (IllegalStateException e) {
            }
        }
    }

    private void stop() {
        if (this.mAudioTrack != null) {
            try {
                this.mAudioTrack.stop();
            } catch (IllegalStateException e) {
            }
            this.mLock.lock();
            this.mAudioTrack.release();
            this.mAudioTrack = null;
            this.mLock.unlock();
        }
        this.mStartFlag = false;
        this.mThreadFlag = false;
    }
}
