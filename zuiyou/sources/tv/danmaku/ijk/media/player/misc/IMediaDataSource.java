package tv.danmaku.ijk.media.player.misc;

import java.io.IOException;

public interface IMediaDataSource {
    void close() throws IOException;

    long getSize() throws IOException;

    int readAt(long j, byte[] bArr, int i, int i2) throws IOException;
}
