package okio;

import java.io.IOException;

class AsyncTimeout$1 implements Sink {
    final /* synthetic */ AsyncTimeout this$0;
    final /* synthetic */ Sink val$sink;

    AsyncTimeout$1(AsyncTimeout asyncTimeout, Sink sink) {
        this.this$0 = asyncTimeout;
        this.val$sink = sink;
    }

    public void write(Buffer buffer, long j) throws IOException {
        Util.checkOffsetAndCount(buffer.size, 0, j);
        long j2 = j;
        while (j2 > 0) {
            Segment segment = buffer.head;
            long j3 = 0;
            while (j3 < 65536) {
                long j4 = ((long) (buffer.head.limit - buffer.head.pos)) + j3;
                if (j4 >= j2) {
                    j3 = j2;
                    break;
                } else {
                    segment = segment.next;
                    j3 = j4;
                }
            }
            this.this$0.enter();
            try {
                this.val$sink.write(buffer, j3);
                j2 -= j3;
                this.this$0.exit(true);
            } catch (IOException e) {
                throw this.this$0.exit(e);
            } catch (Throwable th) {
                this.this$0.exit(false);
            }
        }
    }

    public void flush() throws IOException {
        this.this$0.enter();
        try {
            this.val$sink.flush();
            this.this$0.exit(true);
        } catch (IOException e) {
            throw this.this$0.exit(e);
        } catch (Throwable th) {
            this.this$0.exit(false);
        }
    }

    public void close() throws IOException {
        this.this$0.enter();
        try {
            this.val$sink.close();
            this.this$0.exit(true);
        } catch (IOException e) {
            throw this.this$0.exit(e);
        } catch (Throwable th) {
            this.this$0.exit(false);
        }
    }

    public Timeout timeout() {
        return this.this$0;
    }

    public String toString() {
        return "AsyncTimeout.sink(" + this.val$sink + ")";
    }
}
