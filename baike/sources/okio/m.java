package okio;

import android.support.v4.media.session.PlaybackStateCompat;
import java.io.IOException;
import java.io.InputStream;

class m extends InputStream {
    final /* synthetic */ l a;

    m(l lVar) {
        this.a = lVar;
    }

    public int read() throws IOException {
        if (this.a.a) {
            throw new IOException("closed");
        } else if (this.a.buffer.b == 0 && this.a.source.read(this.a.buffer, PlaybackStateCompat.ACTION_PLAY_FROM_URI) == -1) {
            return -1;
        } else {
            return this.a.buffer.readByte() & 255;
        }
    }

    public int read(byte[] bArr, int i, int i2) throws IOException {
        if (this.a.a) {
            throw new IOException("closed");
        }
        r.checkOffsetAndCount((long) bArr.length, (long) i, (long) i2);
        if (this.a.buffer.b == 0 && this.a.source.read(this.a.buffer, PlaybackStateCompat.ACTION_PLAY_FROM_URI) == -1) {
            return -1;
        }
        return this.a.buffer.read(bArr, i, i2);
    }

    public int available() throws IOException {
        if (!this.a.a) {
            return (int) Math.min(this.a.buffer.b, 2147483647L);
        }
        throw new IOException("closed");
    }

    public void close() throws IOException {
        this.a.close();
    }

    public String toString() {
        return this.a + ".inputStream()";
    }
}
