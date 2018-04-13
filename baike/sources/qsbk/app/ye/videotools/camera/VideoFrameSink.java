package qsbk.app.ye.videotools.camera;

public interface VideoFrameSink {
    boolean encodeVideo(long j, long j2, int i, int i2, int i3, int i4, boolean z);

    boolean encodeVideo(byte[] bArr, long j, int i, int i2, int i3, int i4, boolean z);
}
