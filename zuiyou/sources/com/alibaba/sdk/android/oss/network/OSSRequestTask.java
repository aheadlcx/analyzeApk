package com.alibaba.sdk.android.oss.network;

import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.ServiceException;
import com.alibaba.sdk.android.oss.callback.OSSProgressCallback;
import com.alibaba.sdk.android.oss.common.OSSLog;
import com.alibaba.sdk.android.oss.common.utils.DateUtil;
import com.alibaba.sdk.android.oss.common.utils.OSSUtils;
import com.alibaba.sdk.android.oss.internal.OSSRetryHandler;
import com.alibaba.sdk.android.oss.internal.OSSRetryType;
import com.alibaba.sdk.android.oss.internal.RequestMessage;
import com.alibaba.sdk.android.oss.internal.ResponseParser;
import com.alibaba.sdk.android.oss.internal.ResponseParsers;
import com.alibaba.sdk.android.oss.model.OSSResult;
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

public class OSSRequestTask<T extends OSSResult> implements Callable<T> {
    private w client;
    private ExecutionContext context;
    private int currentRetryCount = 0;
    private RequestMessage message;
    private ResponseParser<T> responseParser;
    private OSSRetryHandler retryHandler;

    class ProgressTouchableRequestBody extends z {
        private static final int SEGMENT_SIZE = 2048;
        private BufferedSink bufferedSink;
        private OSSProgressCallback callback;
        private long contentLength;
        private String contentType;
        private byte[] data;
        private File file;
        private InputStream inputStream;

        public ProgressTouchableRequestBody(File file, String str, OSSProgressCallback oSSProgressCallback) {
            this.file = file;
            this.contentType = str;
            this.contentLength = file.length();
            this.callback = oSSProgressCallback;
        }

        public ProgressTouchableRequestBody(byte[] bArr, String str, OSSProgressCallback oSSProgressCallback) {
            this.data = bArr;
            this.contentType = str;
            this.contentLength = (long) bArr.length;
            this.callback = oSSProgressCallback;
        }

        public ProgressTouchableRequestBody(InputStream inputStream, long j, String str, OSSProgressCallback oSSProgressCallback) {
            this.inputStream = inputStream;
            this.contentType = str;
            this.contentLength = j;
            this.callback = oSSProgressCallback;
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
                    this.callback.onProgress(OSSRequestTask.this.context.getRequest(), j, this.contentLength);
                }
            }
            if (source != null) {
                source.close();
            }
        }
    }

    public OSSRequestTask(RequestMessage requestMessage, ResponseParser responseParser, ExecutionContext executionContext, int i) {
        this.responseParser = responseParser;
        this.message = requestMessage;
        this.context = executionContext;
        this.client = executionContext.getClient();
        this.retryHandler = new OSSRetryHandler(i);
    }

    public T call() throws Exception {
        aa a;
        Throwable e;
        Exception clientException;
        aa aaVar;
        Exception exception;
        OSSResult oSSResult;
        OSSRetryType shouldRetry;
        y d;
        e a2;
        y yVar;
        e eVar;
        try {
            OSSLog.logD("[call] - ");
            OSSUtils.ensureRequestValid(this.context.getRequest(), this.message);
            OSSUtils.signRequest(this.message);
            if (this.context.getCancellationHandler().isCancelled()) {
                throw new InterruptedIOException("This task is cancelled!");
            }
            y$a a3;
            y$a a4 = new y$a().a(this.message.buildCanonicalURL());
            for (String str : this.message.getHeaders().keySet()) {
                a4 = a4.b(str, (String) this.message.getHeaders().get(str));
            }
            String str2 = (String) this.message.getHeaders().get("Content-Type");
            switch (this.message.getMethod()) {
                case POST:
                case PUT:
                    boolean z;
                    if (str2 != null) {
                        z = true;
                    } else {
                        z = false;
                    }
                    OSSUtils.assertTrue(z, "Content type can't be null when upload!");
                    if (this.message.getUploadData() == null) {
                        if (this.message.getUploadFilePath() == null) {
                            if (this.message.getUploadInputStream() == null) {
                                a3 = a4.a(this.message.getMethod().toString(), z.create(null, new byte[0]));
                                break;
                            }
                            a3 = a4.a(this.message.getMethod().toString(), new ProgressTouchableRequestBody(this.message.getUploadInputStream(), this.message.getReadStreamLength(), str2, this.context.getProgressCallback()));
                            break;
                        }
                        a3 = a4.a(this.message.getMethod().toString(), new ProgressTouchableRequestBody(new File(this.message.getUploadFilePath()), str2, this.context.getProgressCallback()));
                        break;
                    }
                    a3 = a4.a(this.message.getMethod().toString(), new ProgressTouchableRequestBody(this.message.getUploadData(), str2, this.context.getProgressCallback()));
                    break;
                case GET:
                    a3 = a4.a();
                    break;
                case HEAD:
                    a3 = a4.b();
                    break;
                case DELETE:
                    a3 = a4.c();
                    break;
                default:
                    a3 = a4;
                    break;
            }
            d = a3.d();
            try {
                if (OSSLog.isEnableLog()) {
                    OSSLog.logD("request url: " + d.a());
                    Map c = d.c().c();
                    for (String str3 : c.keySet()) {
                        OSSLog.logD("requestHeader " + str3 + ": " + ((String) ((List) c.get(str3)).get(0)));
                    }
                }
                a2 = this.client.a(d);
                try {
                    this.context.getCancellationHandler().setCall(a2);
                    a = a2.a();
                } catch (Exception e2) {
                    e = e2;
                    a = null;
                    OSSLog.logE("Encounter local execpiton: " + e.toString());
                    if (OSSLog.isEnableLog()) {
                        e.printStackTrace();
                    }
                    clientException = new ClientException(e.getMessage(), e);
                    yVar = d;
                    aaVar = a;
                    eVar = a2;
                    exception = clientException;
                    if (aaVar != null) {
                        try {
                            DateUtil.setCurrentServerTime(DateUtil.parseRfc822Date(aaVar.a("Date")).getTime());
                        } catch (Exception e3) {
                        }
                    }
                    if (exception != null) {
                    }
                    if (exception == null) {
                        try {
                            oSSResult = (OSSResult) this.responseParser.parse(aaVar);
                            if (this.context.getCompletedCallback() == null) {
                                return oSSResult;
                            }
                            try {
                                this.context.getCompletedCallback().onSuccess(this.context.getRequest(), oSSResult);
                                return oSSResult;
                            } catch (Exception e4) {
                                return oSSResult;
                            }
                        } catch (Throwable e5) {
                            exception = new ClientException(e5.getMessage(), e5);
                        }
                    }
                    exception = new ClientException("Task is cancelled!", exception.getCause(), Boolean.valueOf(true));
                    shouldRetry = this.retryHandler.shouldRetry(exception, this.currentRetryCount);
                    OSSLog.logE("[run] - retry, retry type: " + shouldRetry);
                    if (shouldRetry == OSSRetryType.OSSRetryTypeShouldRetry) {
                        this.currentRetryCount++;
                        return call();
                    } else if (shouldRetry == OSSRetryType.OSSRetryTypeShouldFixedTimeSkewedAndRetry) {
                        if (exception instanceof ClientException) {
                            if (this.context.getCompletedCallback() != null) {
                                this.context.getCompletedCallback().onFailure(this.context.getRequest(), null, (ServiceException) exception);
                            }
                        } else if (this.context.getCompletedCallback() != null) {
                            this.context.getCompletedCallback().onFailure(this.context.getRequest(), (ClientException) exception, null);
                        }
                        throw exception;
                    } else {
                        if (aaVar != null) {
                            this.message.getHeaders().put("Date", aaVar.a("Date"));
                        }
                        this.currentRetryCount++;
                        return call();
                    }
                }
                try {
                    if (OSSLog.isEnableLog()) {
                        OSSLog.logD("response code: " + a.b() + " for url: " + d.a());
                        Map c2 = a.f().c();
                        for (String str32 : c2.keySet()) {
                            OSSLog.logD("responseHeader " + str32 + ": " + ((String) ((List) c2.get(str32)).get(0)));
                        }
                    }
                    yVar = d;
                    aaVar = a;
                    eVar = a2;
                    exception = null;
                } catch (Exception e6) {
                    e5 = e6;
                    OSSLog.logE("Encounter local execpiton: " + e5.toString());
                    if (OSSLog.isEnableLog()) {
                        e5.printStackTrace();
                    }
                    clientException = new ClientException(e5.getMessage(), e5);
                    yVar = d;
                    aaVar = a;
                    eVar = a2;
                    exception = clientException;
                    if (aaVar != null) {
                        DateUtil.setCurrentServerTime(DateUtil.parseRfc822Date(aaVar.a("Date")).getTime());
                    }
                    if (exception != null) {
                    }
                    if (exception == null) {
                        oSSResult = (OSSResult) this.responseParser.parse(aaVar);
                        if (this.context.getCompletedCallback() == null) {
                            return oSSResult;
                        }
                        this.context.getCompletedCallback().onSuccess(this.context.getRequest(), oSSResult);
                        return oSSResult;
                    }
                    exception = new ClientException("Task is cancelled!", exception.getCause(), Boolean.valueOf(true));
                    shouldRetry = this.retryHandler.shouldRetry(exception, this.currentRetryCount);
                    OSSLog.logE("[run] - retry, retry type: " + shouldRetry);
                    if (shouldRetry == OSSRetryType.OSSRetryTypeShouldRetry) {
                        this.currentRetryCount++;
                        return call();
                    } else if (shouldRetry == OSSRetryType.OSSRetryTypeShouldFixedTimeSkewedAndRetry) {
                        if (aaVar != null) {
                            this.message.getHeaders().put("Date", aaVar.a("Date"));
                        }
                        this.currentRetryCount++;
                        return call();
                    } else {
                        if (exception instanceof ClientException) {
                            if (this.context.getCompletedCallback() != null) {
                                this.context.getCompletedCallback().onFailure(this.context.getRequest(), (ClientException) exception, null);
                            }
                        } else if (this.context.getCompletedCallback() != null) {
                            this.context.getCompletedCallback().onFailure(this.context.getRequest(), null, (ServiceException) exception);
                        }
                        throw exception;
                    }
                }
            } catch (Exception e7) {
                e5 = e7;
                a2 = null;
                a = null;
                OSSLog.logE("Encounter local execpiton: " + e5.toString());
                if (OSSLog.isEnableLog()) {
                    e5.printStackTrace();
                }
                clientException = new ClientException(e5.getMessage(), e5);
                yVar = d;
                aaVar = a;
                eVar = a2;
                exception = clientException;
                if (aaVar != null) {
                    DateUtil.setCurrentServerTime(DateUtil.parseRfc822Date(aaVar.a("Date")).getTime());
                }
                if (exception != null) {
                }
                if (exception == null) {
                    oSSResult = (OSSResult) this.responseParser.parse(aaVar);
                    if (this.context.getCompletedCallback() == null) {
                        return oSSResult;
                    }
                    this.context.getCompletedCallback().onSuccess(this.context.getRequest(), oSSResult);
                    return oSSResult;
                }
                exception = new ClientException("Task is cancelled!", exception.getCause(), Boolean.valueOf(true));
                shouldRetry = this.retryHandler.shouldRetry(exception, this.currentRetryCount);
                OSSLog.logE("[run] - retry, retry type: " + shouldRetry);
                if (shouldRetry == OSSRetryType.OSSRetryTypeShouldRetry) {
                    this.currentRetryCount++;
                    return call();
                } else if (shouldRetry == OSSRetryType.OSSRetryTypeShouldFixedTimeSkewedAndRetry) {
                    if (exception instanceof ClientException) {
                        if (this.context.getCompletedCallback() != null) {
                            this.context.getCompletedCallback().onFailure(this.context.getRequest(), null, (ServiceException) exception);
                        }
                    } else if (this.context.getCompletedCallback() != null) {
                        this.context.getCompletedCallback().onFailure(this.context.getRequest(), (ClientException) exception, null);
                    }
                    throw exception;
                } else {
                    if (aaVar != null) {
                        this.message.getHeaders().put("Date", aaVar.a("Date"));
                    }
                    this.currentRetryCount++;
                    return call();
                }
            }
            if (aaVar != null) {
                DateUtil.setCurrentServerTime(DateUtil.parseRfc822Date(aaVar.a("Date")).getTime());
            }
            if (exception != null && (aaVar.b() == 203 || aaVar.b() >= 300)) {
                try {
                    exception = ResponseParsers.parseResponseErrorXML(aaVar, yVar.b().equals("HEAD"));
                } catch (Throwable e52) {
                    exception = new ClientException(e52.getMessage(), e52);
                }
            } else if (exception == null) {
                oSSResult = (OSSResult) this.responseParser.parse(aaVar);
                if (this.context.getCompletedCallback() == null) {
                    return oSSResult;
                }
                this.context.getCompletedCallback().onSuccess(this.context.getRequest(), oSSResult);
                return oSSResult;
            }
            if ((eVar != null && eVar.c()) || this.context.getCancellationHandler().isCancelled()) {
                exception = new ClientException("Task is cancelled!", exception.getCause(), Boolean.valueOf(true));
            }
            shouldRetry = this.retryHandler.shouldRetry(exception, this.currentRetryCount);
            OSSLog.logE("[run] - retry, retry type: " + shouldRetry);
            if (shouldRetry == OSSRetryType.OSSRetryTypeShouldRetry) {
                this.currentRetryCount++;
                return call();
            } else if (shouldRetry == OSSRetryType.OSSRetryTypeShouldFixedTimeSkewedAndRetry) {
                if (aaVar != null) {
                    this.message.getHeaders().put("Date", aaVar.a("Date"));
                }
                this.currentRetryCount++;
                return call();
            } else {
                if (exception instanceof ClientException) {
                    if (this.context.getCompletedCallback() != null) {
                        this.context.getCompletedCallback().onFailure(this.context.getRequest(), (ClientException) exception, null);
                    }
                } else if (this.context.getCompletedCallback() != null) {
                    this.context.getCompletedCallback().onFailure(this.context.getRequest(), null, (ServiceException) exception);
                }
                throw exception;
            }
        } catch (Exception e8) {
            e52 = e8;
            a2 = null;
            a = null;
            d = null;
            OSSLog.logE("Encounter local execpiton: " + e52.toString());
            if (OSSLog.isEnableLog()) {
                e52.printStackTrace();
            }
            clientException = new ClientException(e52.getMessage(), e52);
            yVar = d;
            aaVar = a;
            eVar = a2;
            exception = clientException;
            if (aaVar != null) {
                DateUtil.setCurrentServerTime(DateUtil.parseRfc822Date(aaVar.a("Date")).getTime());
            }
            if (exception != null) {
            }
            if (exception == null) {
                oSSResult = (OSSResult) this.responseParser.parse(aaVar);
                if (this.context.getCompletedCallback() == null) {
                    return oSSResult;
                }
                this.context.getCompletedCallback().onSuccess(this.context.getRequest(), oSSResult);
                return oSSResult;
            }
            exception = new ClientException("Task is cancelled!", exception.getCause(), Boolean.valueOf(true));
            shouldRetry = this.retryHandler.shouldRetry(exception, this.currentRetryCount);
            OSSLog.logE("[run] - retry, retry type: " + shouldRetry);
            if (shouldRetry == OSSRetryType.OSSRetryTypeShouldRetry) {
                this.currentRetryCount++;
                return call();
            } else if (shouldRetry == OSSRetryType.OSSRetryTypeShouldFixedTimeSkewedAndRetry) {
                if (aaVar != null) {
                    this.message.getHeaders().put("Date", aaVar.a("Date"));
                }
                this.currentRetryCount++;
                return call();
            } else {
                if (exception instanceof ClientException) {
                    if (this.context.getCompletedCallback() != null) {
                        this.context.getCompletedCallback().onFailure(this.context.getRequest(), (ClientException) exception, null);
                    }
                } else if (this.context.getCompletedCallback() != null) {
                    this.context.getCompletedCallback().onFailure(this.context.getRequest(), null, (ServiceException) exception);
                }
                throw exception;
            }
        }
    }
}
