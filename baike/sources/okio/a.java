package okio;

import android.support.v4.media.session.PlaybackStateCompat;
import java.io.IOException;

class a implements Sink {
    final /* synthetic */ Sink a;
    final /* synthetic */ AsyncTimeout b;

    a(AsyncTimeout asyncTimeout, Sink sink) {
        this.b = asyncTimeout;
        this.a = sink;
    }

    public void write(Buffer buffer, long j) throws IOException {
        r.checkOffsetAndCount(buffer.b, 0, j);
        long j2 = j;
        while (j2 > 0) {
            n nVar = buffer.a;
            long j3 = 0;
            while (j3 < PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH) {
                long j4 = ((long) (buffer.a.c - buffer.a.b)) + j3;
                if (j4 >= j2) {
                    j3 = j2;
                    break;
                } else {
                    nVar = nVar.f;
                    j3 = j4;
                }
            }
            this.b.enter();
            try {
                this.a.write(buffer, j3);
                j2 -= j3;
                this.b.a(true);
            } catch (IOException e) {
                throw this.b.b(e);
            } catch (Throwable th) {
                this.b.a(false);
            }
        }
    }

    public void flush() throws IOException {
        this.b.enter();
        try {
            this.a.flush();
            this.b.a(true);
        } catch (IOException e) {
            throw this.b.b(e);
        } catch (Throwable th) {
            this.b.a(false);
        }
    }

    public void close() throws IOException {
        this.b.enter();
        try {
            this.a.close();
            this.b.a(true);
        } catch (IOException e) {
            throw this.b.b(e);
        } catch (Throwable th) {
            this.b.a(false);
        }
    }

    public Timeout timeout() {
        return this.b;
    }

    public String toString() {
        return "AsyncTimeout.sink(" + this.a + ")";
    }
}
