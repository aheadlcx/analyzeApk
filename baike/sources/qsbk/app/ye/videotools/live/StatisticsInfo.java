package qsbk.app.ye.videotools.live;

public class StatisticsInfo {
    public long mAudioSendDataSize;
    public int mDelayMS;
    public long mVideoSendDataSize;

    public String toString() {
        return "mAudioSendDataSize:" + this.mAudioSendDataSize + ", mVideoSendDataSize:" + this.mVideoSendDataSize + ", mDelayMS:" + this.mDelayMS;
    }
}
