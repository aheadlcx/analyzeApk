package com.meizu.cloud.pushsdk.networking.internal;

import com.meizu.cloud.pushsdk.networking.http.MediaType;
import com.meizu.cloud.pushsdk.networking.http.ResponseBody;
import com.meizu.cloud.pushsdk.networking.interfaces.DownloadProgressListener;
import com.meizu.cloud.pushsdk.networking.model.Progress;
import com.meizu.cloud.pushsdk.networking.okio.Buffer;
import com.meizu.cloud.pushsdk.networking.okio.BufferedSource;
import com.meizu.cloud.pushsdk.networking.okio.ForwardingSource;
import com.meizu.cloud.pushsdk.networking.okio.Okio;
import com.meizu.cloud.pushsdk.networking.okio.Source;
import java.io.IOException;

public class ResponseProgressBody extends ResponseBody {
    private BufferedSource bufferedSource;
    private DownloadProgressHandler downloadProgressHandler;
    private final ResponseBody mResponseBody;

    public ResponseProgressBody(ResponseBody responseBody, DownloadProgressListener downloadProgressListener) {
        this.mResponseBody = responseBody;
        if (downloadProgressListener != null) {
            this.downloadProgressHandler = new DownloadProgressHandler(downloadProgressListener);
        }
    }

    public MediaType contentType() {
        return this.mResponseBody.contentType();
    }

    public long contentLength() {
        return this.mResponseBody.contentLength();
    }

    public BufferedSource source() {
        if (this.bufferedSource == null) {
            this.bufferedSource = Okio.buffer(source(this.mResponseBody.source()));
        }
        return this.bufferedSource;
    }

    private Source source(Source source) {
        return new ForwardingSource(source) {
            long totalBytesRead;

            public long read(Buffer buffer, long j) throws IOException {
                long read = super.read(buffer, j);
                this.totalBytesRead = (read != -1 ? read : 0) + this.totalBytesRead;
                if (ResponseProgressBody.this.downloadProgressHandler != null) {
                    ResponseProgressBody.this.downloadProgressHandler.obtainMessage(1, new Progress(this.totalBytesRead, ResponseProgressBody.this.mResponseBody.contentLength())).sendToTarget();
                }
                return read;
            }
        };
    }
}
