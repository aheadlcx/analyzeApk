package cn.xiaochuan.media.av;

public class AVFileReader {
    public static final int STREAM_INDEX_AUDIO = 1;
    public static final int STREAM_INDEX_VIDEO = 2;
    private long mPeer;

    public native int IsEndofStream();

    public native void closeFile();

    public native double getAudioDuration();

    public native AudioMediaType getAudioType();

    public native double getDuration();

    public native String getFileName();

    public native AudioMediaType getRawAudioType();

    public native VideoMediaType getRawVideoType();

    public native int getVideoRotate();

    public native int haveAudio();

    public native int haveVideo();

    public native int initialize();

    public native int loadFile(String str);

    public native int readNextRawSample(Integer num, Long l, Long l2, byte[] bArr, Integer num2, Integer num3);

    public native int readNextSample(Integer num, Long l, Long l2, byte[] bArr, Integer num2);

    public native int readNextSample2(Integer num, Long l, Long l2, int[] iArr, Integer num2, byte[] bArr, Integer num3);

    public native int release();

    public native int seek(long j);

    public native void setAutoRotate(int i);

    public native int setOutputSize(int i, int i2);

    public native int setOutputStream(int i);

    public native int setOutputVideoType(int i);

    static {
        System.loadLibrary("avmedia");
    }
}
