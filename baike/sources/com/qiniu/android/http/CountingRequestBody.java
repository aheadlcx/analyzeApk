package com.qiniu.android.http;

import com.qiniu.android.http.CancellationHandler.CancellationException;
import com.qiniu.android.utils.AsyncRun;
import java.io.IOException;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.Buffer;
import okio.BufferedSink;
import okio.ForwardingSink;
import okio.Okio;
import okio.Sink;

public final class CountingRequestBody extends RequestBody {
    private final RequestBody a;
    private final ProgressHandler b;
    private final CancellationHandler c;

    protected final class CountingSink extends ForwardingSink {
        final /* synthetic */ CountingRequestBody a;
        private int b = 0;

        public CountingSink(CountingRequestBody countingRequestBody, Sink sink) {
            this.a = countingRequestBody;
            super(sink);
        }

        public void write(Buffer buffer, long j) throws IOException {
            if (this.a.c == null && this.a.b == null) {
                super.write(buffer, j);
            } else if (this.a.c == null || !this.a.c.isCancelled()) {
                super.write(buffer, j);
                this.b = (int) (((long) this.b) + j);
                if (this.a.b != null) {
                    AsyncRun.runInMain(new j(this));
                }
            } else {
                throw new CancellationException();
            }
        }
    }

    public CountingRequestBody(RequestBody requestBody, ProgressHandler progressHandler, CancellationHandler cancellationHandler) {
        this.a = requestBody;
        this.b = progressHandler;
        this.c = cancellationHandler;
    }

    public long contentLength() throws IOException {
        return this.a.contentLength();
    }

    public MediaType contentType() {
        return this.a.contentType();
    }

    public void writeTo(BufferedSink bufferedSink) throws IOException {
        BufferedSink buffer = Okio.buffer(new CountingSink(this, bufferedSink));
        this.a.writeTo(buffer);
        buffer.flush();
    }
}
