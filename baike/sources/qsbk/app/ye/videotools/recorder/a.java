package qsbk.app.ye.videotools.recorder;

class a implements Runnable {
    final /* synthetic */ NativeAudioTrack a;

    a(NativeAudioTrack nativeAudioTrack) {
        this.a = nativeAudioTrack;
    }

    public void run() {
        while (this.a.mThreadFlag) {
            this.a.mLock.lock();
            if (!this.a.mStartFlag || this.a.mAudioTrack == null || this.a.mAudioTrack.getPlayState() == 2) {
                this.a.mLock.unlock();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                this.a.mAudioTrack.write(this.a.mAudioBuffer, 0, this.a._FillUpCallBack(this.a.mAudioBuffer, this.a.mMinBufferSize));
                this.a.mLock.unlock();
            }
        }
    }
}
