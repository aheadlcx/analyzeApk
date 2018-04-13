package com.alibaba.sdk.android.mns.network;

import com.alibaba.sdk.android.common.ClientException;
import com.alibaba.sdk.android.common.ServiceException;
import com.alibaba.sdk.android.mns.callback.MNSProgressCallback;
import com.alibaba.sdk.android.mns.common.MNSLog;
import com.alibaba.sdk.android.mns.common.MNSUtils;
import com.alibaba.sdk.android.mns.internal.RequestMessage;
import com.alibaba.sdk.android.mns.internal.ResponseParser;
import com.alibaba.sdk.android.mns.internal.ResponseParsers;
import com.alibaba.sdk.android.mns.model.MNSResult;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import okhttp3.aa;
import okhttp3.e;
import okhttp3.u;
import okhttp3.w;
import okhttp3.y;
import okhttp3.y$a;
import okhttp3.z;
import okio.BufferedSink;
import okio.Okio;
import okio.Source;

public class MNSRequestTask<T extends MNSResult> implements Callable<T> {
    private w client;
    private ExecutionContext context;
    private RequestMessage message;
    private ResponseParser<T> responseParser;

    class ProgressTouchableRequestBody extends z {
        private static final int SEGMENT_SIZE = 2048;
        private MNSProgressCallback callback;
        private long contentLength;
        private String contentType;
        private byte[] data;
        private File file;
        private InputStream inputStream;

        public ProgressTouchableRequestBody(File file, String str, MNSProgressCallback mNSProgressCallback) {
            this.file = file;
            this.contentType = str;
            this.contentLength = file.length();
            this.callback = mNSProgressCallback;
        }

        public ProgressTouchableRequestBody(byte[] bArr, String str, MNSProgressCallback mNSProgressCallback) {
            this.data = bArr;
            this.contentType = str;
            this.contentLength = (long) bArr.length;
            this.callback = mNSProgressCallback;
        }

        public ProgressTouchableRequestBody(InputStream inputStream, long j, String str, MNSProgressCallback mNSProgressCallback) {
            this.inputStream = inputStream;
            this.contentType = str;
            this.contentLength = j;
            this.callback = mNSProgressCallback;
        }

        public u contentType() {
            return u.a(this.contentType);
        }

        public long contentLength() throws IOException {
            return this.contentLength;
        }

        public void writeTo(BufferedSink bufferedSink) throws IOException {
            Source source;
            if (this.file != null) {
                source = Okio.source(this.file);
            } else if (this.data != null) {
                source = Okio.source(new ByteArrayInputStream(this.data));
            } else if (this.inputStream != null) {
                source = Okio.source(this.inputStream);
            } else {
                source = null;
            }
            long j = 0;
            while (j < this.contentLength) {
                long read = source.read(bufferedSink.buffer(), Math.min(this.contentLength - j, 2048));
                if (read == -1) {
                    break;
                }
                j += read;
                bufferedSink.flush();
                if (this.callback != null) {
                    this.callback.onProgress(MNSRequestTask.this.context.getRequest(), j, this.contentLength);
                }
            }
            if (source != null) {
                source.close();
            }
        }
    }

    public MNSRequestTask(RequestMessage requestMessage, ResponseParser responseParser, ExecutionContext executionContext) {
        this.responseParser = responseParser;
        this.message = requestMessage;
        this.context = executionContext;
        this.client = executionContext.getClient();
    }

    public T call() throws Exception {
        aa aaVar;
        Exception exception;
        Throwable e;
        Exception clientException;
        MNSResult mNSResult;
        e a;
        aa a2;
        e eVar;
        try {
            MNSLog.logD("[call] - ");
            if (this.context.getCancellationHandler().isCancelled()) {
                throw new InterruptedIOException("This task is cancelled!");
            }
            y$a y_a = new y$a();
            String buildCanonicalURL = this.message.buildCanonicalURL();
            MNSUtils.signRequest(this.message);
            y$a a3 = y_a.a(buildCanonicalURL);
            for (String str : this.message.getHeaders().keySet()) {
                a3 = a3.b(str, (String) this.message.getHeaders().get(str));
            }
            String str2 = (String) this.message.getHeaders().get("Content-Type");
            this.message.getContent();
            if (this.message.getContent() == null) {
                switch (this.message.getMethod()) {
                    case PUT:
                        y_a = a3.a(this.message.getMethod().toString(), z.create(null, new byte[0]));
                        break;
                    case GET:
                        y_a = a3.a();
                        break;
                    case HEAD:
                        y_a = a3.b();
                        break;
                    case DELETE:
                        y_a = a3.c();
                        break;
                    default:
                        y_a = a3;
                        break;
                }
            }
            MNSUtils.assertTrue(str2 != null, "Content type can't be null when send data!");
            y_a = a3.a(this.message.getMethod().toString(), new ProgressTouchableRequestBody(this.message.getContent().getBytes(), str2, this.context.getProgressCallback()));
            y d = y_a.d();
            if (MNSLog.isEnableLog()) {
                MNSLog.logD("request url: " + d.a());
                Map c = d.c().c();
                for (String str22 : c.keySet()) {
                    MNSLog.logD("requestHeader " + str22 + ": " + ((String) ((List) c.get(str22)).get(0)));
                }
            }
            a = this.client.a(d);
            try {
                this.context.getCancellationHandler().setCall(a);
                a2 = a.a();
                try {
                    if (MNSLog.isEnableLog()) {
                        MNSLog.logD("response code: " + a2.b() + " for url: " + d.a());
                        Map c2 = a2.f().c();
                        for (String str222 : c2.keySet()) {
                            MNSLog.logD("responseHeader " + str222 + ": " + ((String) ((List) c2.get(str222)).get(0)));
                        }
                    }
                    aaVar = a2;
                    eVar = a;
                    exception = null;
                } catch (Exception e2) {
                    e = e2;
                    MNSLog.logE("Encounter local execpiton: " + e.toString());
                    if (MNSLog.isEnableLog()) {
                        e.printStackTrace();
                    }
                    clientException = new ClientException(e.getMessage(), e);
                    aaVar = a2;
                    eVar = a;
                    exception = clientException;
                    if (exception != null) {
                    }
                    if (exception == null) {
                        try {
                            mNSResult = (MNSResult) this.responseParser.parse(aaVar);
                            if (this.context.getCompletedCallback() != null) {
                                this.context.getCompletedCallback().onSuccess(this.context.getRequest(), mNSResult);
                            }
                            return mNSResult;
                        } catch (Throwable e3) {
                            exception = new ClientException(e3.getMessage(), e3);
                        }
                    }
                    exception = new ClientException("Task is cancelled!", exception.getCause(), Boolean.valueOf(true));
                    if (exception instanceof ClientException) {
                        if (this.context.getCompletedCallback() != null) {
                            this.context.getCompletedCallback().onFailure(this.context.getRequest(), (ClientException) exception, null);
                        }
                    } else if (this.context.getCompletedCallback() != null) {
                        this.context.getCompletedCallback().onFailure(this.context.getRequest(), null, (ServiceException) exception);
                    }
                    throw exception;
                }
            } catch (Exception e4) {
                e3 = e4;
                a2 = null;
                MNSLog.logE("Encounter local execpiton: " + e3.toString());
                if (MNSLog.isEnableLog()) {
                    e3.printStackTrace();
                }
                clientException = new ClientException(e3.getMessage(), e3);
                aaVar = a2;
                eVar = a;
                exception = clientException;
                if (exception != null) {
                }
                if (exception == null) {
                    mNSResult = (MNSResult) this.responseParser.parse(aaVar);
                    if (this.context.getCompletedCallback() != null) {
                        this.context.getCompletedCallback().onSuccess(this.context.getRequest(), mNSResult);
                    }
                    return mNSResult;
                }
                exception = new ClientException("Task is cancelled!", exception.getCause(), Boolean.valueOf(true));
                if (exception instanceof ClientException) {
                    if (this.context.getCompletedCallback() != null) {
                        this.context.getCompletedCallback().onFailure(this.context.getRequest(), (ClientException) exception, null);
                    }
                } else if (this.context.getCompletedCallback() != null) {
                    this.context.getCompletedCallback().onFailure(this.context.getRequest(), null, (ServiceException) exception);
                }
                throw exception;
            }
            if (exception != null && (aaVar.b() == 203 || aaVar.b() >= 300)) {
                try {
                    exception = ResponseParsers.parseResponseErrorXML(aaVar);
                } catch (Throwable e32) {
                    exception = new ClientException(e32.getMessage(), e32);
                }
            } else if (exception == null) {
                mNSResult = (MNSResult) this.responseParser.parse(aaVar);
                if (this.context.getCompletedCallback() != null) {
                    this.context.getCompletedCallback().onSuccess(this.context.getRequest(), mNSResult);
                }
                return mNSResult;
            }
            if ((eVar != null && eVar.c()) || this.context.getCancellationHandler().isCancelled()) {
                exception = new ClientException("Task is cancelled!", exception.getCause(), Boolean.valueOf(true));
            }
            if (exception instanceof ClientException) {
                if (this.context.getCompletedCallback() != null) {
                    this.context.getCompletedCallback().onFailure(this.context.getRequest(), (ClientException) exception, null);
                }
            } else if (this.context.getCompletedCallback() != null) {
                this.context.getCompletedCallback().onFailure(this.context.getRequest(), null, (ServiceException) exception);
            }
            throw exception;
        } catch (Exception e5) {
            e32 = e5;
            a = null;
            a2 = null;
            MNSLog.logE("Encounter local execpiton: " + e32.toString());
            if (MNSLog.isEnableLog()) {
                e32.printStackTrace();
            }
            clientException = new ClientException(e32.getMessage(), e32);
            aaVar = a2;
            eVar = a;
            exception = clientException;
            if (exception != null) {
            }
            if (exception == null) {
                mNSResult = (MNSResult) this.responseParser.parse(aaVar);
                if (this.context.getCompletedCallback() != null) {
                    this.context.getCompletedCallback().onSuccess(this.context.getRequest(), mNSResult);
                }
                return mNSResult;
            }
            exception = new ClientException("Task is cancelled!", exception.getCause(), Boolean.valueOf(true));
            if (exception instanceof ClientException) {
                if (this.context.getCompletedCallback() != null) {
                    this.context.getCompletedCallback().onFailure(this.context.getRequest(), null, (ServiceException) exception);
                }
            } else if (this.context.getCompletedCallback() != null) {
                this.context.getCompletedCallback().onFailure(this.context.getRequest(), (ClientException) exception, null);
            }
            throw exception;
        }
    }
}
