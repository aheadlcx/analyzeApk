package cn.xiaochuan.media.av;

public class AudioMixFilter {
    private long mPeer;

    public native int checkInputType();

    public native int doMixAudio(byte[] bArr, int i, byte[] bArr2, int i2, byte[] bArr3, int i3);

    public native long getMixDuration();

    public native AudioMediaType getPrimaryAudioType();

    public native AudioMediaType getSecondAudioType();

    public native int initialize();

    public native int release();

    public native void setMixDuration(long j);

    public native void setPrimaryAudioType(AudioMediaType audioMediaType);

    public native void setPrimaryPercent(float f);

    public native void setSecondAudioType(AudioMediaType audioMediaType);

    static {
        System.loadLibrary("avmedia");
    }
}
