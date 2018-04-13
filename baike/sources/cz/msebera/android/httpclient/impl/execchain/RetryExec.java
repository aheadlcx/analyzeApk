package cz.msebera.android.httpclient.impl.execchain;

import cz.msebera.android.httpclient.annotation.Immutable;
import cz.msebera.android.httpclient.client.HttpRequestRetryHandler;
import cz.msebera.android.httpclient.extras.HttpClientAndroidLog;
import cz.msebera.android.httpclient.util.Args;

@Immutable
public class RetryExec implements ClientExecChain {
    private final ClientExecChain a;
    private final HttpRequestRetryHandler b;
    public HttpClientAndroidLog log = new HttpClientAndroidLog(getClass());

    public RetryExec(ClientExecChain clientExecChain, HttpRequestRetryHandler httpRequestRetryHandler) {
        Args.notNull(clientExecChain, "HTTP request executor");
        Args.notNull(httpRequestRetryHandler, "HTTP request retry handler");
        this.a = clientExecChain;
        this.b = httpRequestRetryHandler;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public cz.msebera.android.httpclient.client.methods.CloseableHttpResponse execute(cz.msebera.android.httpclient.conn.routing.HttpRoute r7, cz.msebera.android.httpclient.client.methods.HttpRequestWrapper r8, cz.msebera.android.httpclient.client.protocol.HttpClientContext r9, cz.msebera.android.httpclient.client.methods.HttpExecutionAware r10) throws java.io.IOException, cz.msebera.android.httpclient.HttpException {
        /*
        r6 = this;
        r0 = "HTTP route";
        cz.msebera.android.httpclient.util.Args.notNull(r7, r0);
        r0 = "HTTP request";
        cz.msebera.android.httpclient.util.Args.notNull(r8, r0);
        r0 = "HTTP context";
        cz.msebera.android.httpclient.util.Args.notNull(r9, r0);
        r2 = r8.getAllHeaders();
        r0 = 1;
        r1 = r0;
    L_0x0015:
        r0 = r6.a;	 Catch:{ IOException -> 0x001c }
        r0 = r0.execute(r7, r8, r9, r10);	 Catch:{ IOException -> 0x001c }
        return r0;
    L_0x001c:
        r0 = move-exception;
        if (r10 == 0) goto L_0x002d;
    L_0x001f:
        r3 = r10.isAborted();
        if (r3 == 0) goto L_0x002d;
    L_0x0025:
        r1 = r6.log;
        r2 = "Request has been aborted";
        r1.debug(r2);
        throw r0;
    L_0x002d:
        r3 = r6.b;
        r3 = r3.retryRequest(r0, r1, r9);
        if (r3 == 0) goto L_0x00c3;
    L_0x0035:
        r3 = r6.log;
        r3 = r3.isInfoEnabled();
        if (r3 == 0) goto L_0x0075;
    L_0x003d:
        r3 = r6.log;
        r4 = new java.lang.StringBuilder;
        r4.<init>();
        r5 = "I/O exception (";
        r4 = r4.append(r5);
        r5 = r0.getClass();
        r5 = r5.getName();
        r4 = r4.append(r5);
        r5 = ") caught when processing request to ";
        r4 = r4.append(r5);
        r4 = r4.append(r7);
        r5 = ": ";
        r4 = r4.append(r5);
        r5 = r0.getMessage();
        r4 = r4.append(r5);
        r4 = r4.toString();
        r3.info(r4);
    L_0x0075:
        r3 = r6.log;
        r3 = r3.isDebugEnabled();
        if (r3 == 0) goto L_0x0086;
    L_0x007d:
        r3 = r6.log;
        r4 = r0.getMessage();
        r3.debug(r4, r0);
    L_0x0086:
        r3 = cz.msebera.android.httpclient.impl.execchain.c.a(r8);
        if (r3 != 0) goto L_0x009b;
    L_0x008c:
        r1 = r6.log;
        r2 = "Cannot retry non-repeatable request";
        r1.debug(r2);
        r1 = new cz.msebera.android.httpclient.client.NonRepeatableRequestException;
        r2 = "Cannot retry request with a non-repeatable request entity";
        r1.<init>(r2, r0);
        throw r1;
    L_0x009b:
        r8.setHeaders(r2);
        r0 = r6.log;
        r0 = r0.isInfoEnabled();
        if (r0 == 0) goto L_0x00be;
    L_0x00a6:
        r0 = r6.log;
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r4 = "Retrying request to ";
        r3 = r3.append(r4);
        r3 = r3.append(r7);
        r3 = r3.toString();
        r0.info(r3);
    L_0x00be:
        r0 = r1 + 1;
        r1 = r0;
        goto L_0x0015;
    L_0x00c3:
        r1 = r0 instanceof cz.msebera.android.httpclient.NoHttpResponseException;
        if (r1 == 0) goto L_0x00ef;
    L_0x00c7:
        r1 = new cz.msebera.android.httpclient.NoHttpResponseException;
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r3 = r7.getTargetHost();
        r3 = r3.toHostString();
        r2 = r2.append(r3);
        r3 = " failed to respond";
        r2 = r2.append(r3);
        r2 = r2.toString();
        r1.<init>(r2);
        r0 = r0.getStackTrace();
        r1.setStackTrace(r0);
        throw r1;
    L_0x00ef:
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: cz.msebera.android.httpclient.impl.execchain.RetryExec.execute(cz.msebera.android.httpclient.conn.routing.HttpRoute, cz.msebera.android.httpclient.client.methods.HttpRequestWrapper, cz.msebera.android.httpclient.client.protocol.HttpClientContext, cz.msebera.android.httpclient.client.methods.HttpExecutionAware):cz.msebera.android.httpclient.client.methods.CloseableHttpResponse");
    }
}
