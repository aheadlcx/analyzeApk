package cn.xiaochuan.media.av;

import java.nio.Buffer;

public class AVFileWriter {
    public static final int STREAM_INDEX_AUDIO = 1;
    public static final int STREAM_INDEX_VIDEO = 2;
    private long mPeer;

    public native int closeFile();

    public native int createFile(String str);

    public native String getFileName();

    public native int initialize();

    public native int release();

    public native void setAudioBitrate(int i);

    public native void setInputAudioMediaType(AudioMediaType audioMediaType);

    public native void setInputVideoMediaType(VideoMediaType videoMediaType);

    public native void setOutputAudioMediaType(AudioMediaType audioMediaType);

    public native void setOutputVideoMediaType(VideoMediaType videoMediaType);

    public native int setVFilter(String str);

    public native void setVideoBitrate(int i);

    public native void setVideoRotate(int i);

    public native void setVolume(float f);

    public native long writeAudio(byte[] bArr, long j);

    public native int writeCompressSample(int i, long j, long j2, byte[] bArr, long j3, int i2);

    public native int writeSampleArray(int i, long j, long j2, byte[] bArr, long j3);

    public native int writeSampleBuffer(int i, long j, long j2, Buffer buffer, long j3);

    public native int writeVideo(long j, long j2, int[] iArr, long j3);

    static {
        System.loadLibrary("avmedia");
    }
}
