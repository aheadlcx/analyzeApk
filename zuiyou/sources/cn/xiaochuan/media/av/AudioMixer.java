package cn.xiaochuan.media.av;

public class AudioMixer {
    private long mPeer;

    public native int getBufferFreeSize(int i);

    public native int initialize();

    public native int prepareToMix();

    public native int readMixData(byte[] bArr, int i);

    public native int release();

    public native void setMixedStreamDuration(long j);

    public native void setPrimaryAudioType(AudioMediaType audioMediaType);

    public native void setPrimaryPercent(float f);

    public native void setSecondAudioType(AudioMediaType audioMediaType);

    public native int writeAudioData(int i, byte[] bArr, int i2);

    static {
        System.loadLibrary("avmedia");
    }
}
