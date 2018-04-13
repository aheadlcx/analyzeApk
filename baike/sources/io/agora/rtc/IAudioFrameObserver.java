package io.agora.rtc;

public interface IAudioFrameObserver {
    boolean onPlaybackFrame(byte[] bArr, int i, int i2, int i3, int i4);

    boolean onRecordFrame(byte[] bArr, int i, int i2, int i3, int i4);
}
