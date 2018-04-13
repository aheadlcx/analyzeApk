package mtopsdk.a;

import java.io.Closeable;
import java.net.HttpURLConnection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import mtopsdk.a.a.a;
import mtopsdk.a.b.b;
import mtopsdk.a.b.d;
import mtopsdk.common.util.l;
import mtopsdk.common.util.m;
import org.apache.http.entity.mime.MIME;

public final class c implements a {
    volatile boolean a;
    private b b;
    private ExecutorService c;
    private Future d;

    public c(b bVar, ExecutorService executorService) {
        this.b = bVar;
        this.c = executorService;
    }

    private static void a(String str, Map map) {
        if (str != null && map != null) {
            try {
                for (Entry entry : map.entrySet()) {
                    String str2 = (String) entry.getKey();
                    if (str2 != null && (str2.equalsIgnoreCase("Set-Cookie") || str2.equalsIgnoreCase("Set-Cookie2"))) {
                        for (String a : (List) entry.getValue()) {
                            a.a(str, a);
                        }
                    }
                }
            } catch (Exception e) {
            }
        }
    }

    private static void a(HttpURLConnection httpURLConnection, b bVar) {
        httpURLConnection.setRequestMethod(bVar.b());
        for (Entry entry : bVar.c().entrySet()) {
            if (!((String) entry.getKey()).equalsIgnoreCase("Cookie")) {
                httpURLConnection.addRequestProperty((String) entry.getKey(), (String) entry.getValue());
            }
        }
        if (!l.b(a.a(bVar.a()))) {
            httpURLConnection.addRequestProperty("Cookie", a.a(bVar.a()));
        }
        if ("POST".equalsIgnoreCase(bVar.b())) {
            httpURLConnection.setDoOutput(true);
        }
        d d = bVar.d();
        if (d != null) {
            httpURLConnection.setDoOutput(true);
            httpURLConnection.addRequestProperty(MIME.CONTENT_TYPE, d.a());
            long b = d.b();
            if (b != -1) {
                httpURLConnection.setFixedLengthStreamingMode((int) b);
                httpURLConnection.addRequestProperty("Content-Length", String.valueOf(b));
            }
            Closeable outputStream = httpURLConnection.getOutputStream();
            try {
                d.a(outputStream);
            } catch (Throwable e) {
                m.b("mtopsdk.DefaultCallImpl", "write outputstream error.", e);
            } finally {
                com.taobao.tao.remotebusiness.listener.c.a(outputStream);
            }
        }
    }

    public final b a() {
        return this.b;
    }

    public final void a(f fVar) {
        if (this.c != null) {
            try {
                this.d = this.c.submit(new e(this, this.b, fVar));
                return;
            } catch (Exception e) {
                fVar.a((a) this, e);
                return;
            }
        }
        fVar.a((a) this, new Exception("miss executorService in CallImpl "));
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final mtopsdk.a.b.e b() {
        /*
        r13 = this;
        r3 = 0;
        r0 = 0;
        r7 = r13.b;
        r2 = r3;
        r4 = r0;
    L_0x0006:
        r1 = r0 + 1;
        r5 = r7.g();
        if (r0 >= r5) goto L_0x012a;
    L_0x000e:
        r0 = new java.net.URL;	 Catch:{ UnknownHostException -> 0x0047, SocketTimeoutException -> 0x009b, ConnectTimeoutException -> 0x00f0, SSLHandshakeException -> 0x00fb, SSLException -> 0x0106, ConnectException -> 0x0111, Exception -> 0x011c }
        r5 = r7.a();	 Catch:{ UnknownHostException -> 0x0047, SocketTimeoutException -> 0x009b, ConnectTimeoutException -> 0x00f0, SSLHandshakeException -> 0x00fb, SSLException -> 0x0106, ConnectException -> 0x0111, Exception -> 0x011c }
        r0.<init>(r5);	 Catch:{ UnknownHostException -> 0x0047, SocketTimeoutException -> 0x009b, ConnectTimeoutException -> 0x00f0, SSLHandshakeException -> 0x00fb, SSLException -> 0x0106, ConnectException -> 0x0111, Exception -> 0x011c }
        r0 = r0.openConnection();	 Catch:{ UnknownHostException -> 0x0047, SocketTimeoutException -> 0x009b, ConnectTimeoutException -> 0x00f0, SSLHandshakeException -> 0x00fb, SSLException -> 0x0106, ConnectException -> 0x0111, Exception -> 0x011c }
        r0 = (java.net.HttpURLConnection) r0;	 Catch:{ UnknownHostException -> 0x0047, SocketTimeoutException -> 0x009b, ConnectTimeoutException -> 0x00f0, SSLHandshakeException -> 0x00fb, SSLException -> 0x0106, ConnectException -> 0x0111, Exception -> 0x011c }
        r5 = r7.e();	 Catch:{ UnknownHostException -> 0x0047, SocketTimeoutException -> 0x009b, ConnectTimeoutException -> 0x00f0, SSLHandshakeException -> 0x00fb, SSLException -> 0x0106, ConnectException -> 0x0111, Exception -> 0x011c }
        r0.setConnectTimeout(r5);	 Catch:{ UnknownHostException -> 0x0047, SocketTimeoutException -> 0x009b, ConnectTimeoutException -> 0x00f0, SSLHandshakeException -> 0x00fb, SSLException -> 0x0106, ConnectException -> 0x0111, Exception -> 0x011c }
        r5 = r7.f();	 Catch:{ UnknownHostException -> 0x0047, SocketTimeoutException -> 0x009b, ConnectTimeoutException -> 0x00f0, SSLHandshakeException -> 0x00fb, SSLException -> 0x0106, ConnectException -> 0x0111, Exception -> 0x011c }
        r0.setReadTimeout(r5);	 Catch:{ UnknownHostException -> 0x0047, SocketTimeoutException -> 0x009b, ConnectTimeoutException -> 0x00f0, SSLHandshakeException -> 0x00fb, SSLException -> 0x0106, ConnectException -> 0x0111, Exception -> 0x011c }
        a(r0, r7);	 Catch:{ UnknownHostException -> 0x0047, SocketTimeoutException -> 0x009b, ConnectTimeoutException -> 0x00f0, SSLHandshakeException -> 0x00fb, SSLException -> 0x0106, ConnectException -> 0x0111, Exception -> 0x011c }
        r5 = java.lang.Thread.currentThread();	 Catch:{ UnknownHostException -> 0x0047, SocketTimeoutException -> 0x009b, ConnectTimeoutException -> 0x00f0, SSLHandshakeException -> 0x00fb, SSLException -> 0x0106, ConnectException -> 0x0111, Exception -> 0x011c }
        r5 = r5.isInterrupted();	 Catch:{ UnknownHostException -> 0x0047, SocketTimeoutException -> 0x009b, ConnectTimeoutException -> 0x00f0, SSLHandshakeException -> 0x00fb, SSLException -> 0x0106, ConnectException -> 0x0111, Exception -> 0x011c }
        if (r5 == 0) goto L_0x0051;
    L_0x0038:
        r0 = "mtopsdk.DefaultCallImpl";
        r2 = "[readResponse]call task is canceled.";
        mtopsdk.common.util.m.a(r0, r2);	 Catch:{ UnknownHostException -> 0x0047, SocketTimeoutException -> 0x009b, ConnectTimeoutException -> 0x00f0, SSLHandshakeException -> 0x00fb, SSLException -> 0x0106, ConnectException -> 0x0111, Exception -> 0x011c }
        r0 = new java.util.concurrent.CancellationException;	 Catch:{ UnknownHostException -> 0x0047, SocketTimeoutException -> 0x009b, ConnectTimeoutException -> 0x00f0, SSLHandshakeException -> 0x00fb, SSLException -> 0x0106, ConnectException -> 0x0111, Exception -> 0x011c }
        r2 = "call canceled";
        r0.<init>(r2);	 Catch:{ UnknownHostException -> 0x0047, SocketTimeoutException -> 0x009b, ConnectTimeoutException -> 0x00f0, SSLHandshakeException -> 0x00fb, SSLException -> 0x0106, ConnectException -> 0x0111, Exception -> 0x011c }
        throw r0;	 Catch:{ UnknownHostException -> 0x0047, SocketTimeoutException -> 0x009b, ConnectTimeoutException -> 0x00f0, SSLHandshakeException -> 0x00fb, SSLException -> 0x0106, ConnectException -> 0x0111, Exception -> 0x011c }
    L_0x0047:
        r0 = move-exception;
        r2 = -1;
        r0 = r0.getMessage();
        r4 = r2;
        r2 = r0;
        r0 = r1;
        goto L_0x0006;
    L_0x0051:
        r8 = r0.getResponseCode();	 Catch:{ UnknownHostException -> 0x0047, SocketTimeoutException -> 0x009b, ConnectTimeoutException -> 0x00f0, SSLHandshakeException -> 0x00fb, SSLException -> 0x0106, ConnectException -> 0x0111, Exception -> 0x011c }
        r5 = r0.getResponseMessage();	 Catch:{ UnknownHostException -> 0x0047, SocketTimeoutException -> 0x009b, ConnectTimeoutException -> 0x00f0, SSLHandshakeException -> 0x00fb, SSLException -> 0x0106, ConnectException -> 0x0111, Exception -> 0x011c }
        if (r5 != 0) goto L_0x0127;
    L_0x005b:
        r5 = "";
        r6 = r5;
    L_0x005e:
        r9 = r0.getHeaderFields();	 Catch:{ UnknownHostException -> 0x0047, SocketTimeoutException -> 0x009b, ConnectTimeoutException -> 0x00f0, SSLHandshakeException -> 0x00fb, SSLException -> 0x0106, ConnectException -> 0x0111, Exception -> 0x011c }
        r5 = r7.a();	 Catch:{ UnknownHostException -> 0x0047, SocketTimeoutException -> 0x009b, ConnectTimeoutException -> 0x00f0, SSLHandshakeException -> 0x00fb, SSLException -> 0x0106, ConnectException -> 0x0111, Exception -> 0x011c }
        a(r5, r9);	 Catch:{ UnknownHostException -> 0x0047, SocketTimeoutException -> 0x009b, ConnectTimeoutException -> 0x00f0, SSLHandshakeException -> 0x00fb, SSLException -> 0x0106, ConnectException -> 0x0111, Exception -> 0x011c }
        r10 = r0.getContentType();	 Catch:{ UnknownHostException -> 0x0047, SocketTimeoutException -> 0x009b, ConnectTimeoutException -> 0x00f0, SSLHandshakeException -> 0x00fb, SSLException -> 0x0106, ConnectException -> 0x0111, Exception -> 0x011c }
        r11 = r0.getContentLength();	 Catch:{ UnknownHostException -> 0x0047, SocketTimeoutException -> 0x009b, ConnectTimeoutException -> 0x00f0, SSLHandshakeException -> 0x00fb, SSLException -> 0x0106, ConnectException -> 0x0111, Exception -> 0x011c }
        r5 = com.taobao.tao.remotebusiness.listener.c.a(r9);	 Catch:{ UnknownHostException -> 0x0047, SocketTimeoutException -> 0x009b, ConnectTimeoutException -> 0x00f0, SSLHandshakeException -> 0x00fb, SSLException -> 0x0106, ConnectException -> 0x0111, Exception -> 0x011c }
        r12 = 400; // 0x190 float:5.6E-43 double:1.976E-321;
        if (r8 < r12) goto L_0x00a6;
    L_0x0079:
        r0 = r0.getErrorStream();	 Catch:{ UnknownHostException -> 0x0047, SocketTimeoutException -> 0x009b, ConnectTimeoutException -> 0x00f0, SSLHandshakeException -> 0x00fb, SSLException -> 0x0106, ConnectException -> 0x0111, Exception -> 0x011c }
    L_0x007d:
        r5 = new mtopsdk.a.d;	 Catch:{ UnknownHostException -> 0x0047, SocketTimeoutException -> 0x009b, ConnectTimeoutException -> 0x00f0, SSLHandshakeException -> 0x00fb, SSLException -> 0x0106, ConnectException -> 0x0111, Exception -> 0x011c }
        r5.<init>(r13, r10, r11, r0);	 Catch:{ UnknownHostException -> 0x0047, SocketTimeoutException -> 0x009b, ConnectTimeoutException -> 0x00f0, SSLHandshakeException -> 0x00fb, SSLException -> 0x0106, ConnectException -> 0x0111, Exception -> 0x011c }
        r0 = java.lang.Thread.currentThread();	 Catch:{ UnknownHostException -> 0x0047, SocketTimeoutException -> 0x009b, ConnectTimeoutException -> 0x00f0, SSLHandshakeException -> 0x00fb, SSLException -> 0x0106, ConnectException -> 0x0111, Exception -> 0x011c }
        r0 = r0.isInterrupted();	 Catch:{ UnknownHostException -> 0x0047, SocketTimeoutException -> 0x009b, ConnectTimeoutException -> 0x00f0, SSLHandshakeException -> 0x00fb, SSLException -> 0x0106, ConnectException -> 0x0111, Exception -> 0x011c }
        if (r0 == 0) goto L_0x00b8;
    L_0x008c:
        r0 = "mtopsdk.DefaultCallImpl";
        r2 = "[readResponse]call task is canceled.";
        mtopsdk.common.util.m.a(r0, r2);	 Catch:{ UnknownHostException -> 0x0047, SocketTimeoutException -> 0x009b, ConnectTimeoutException -> 0x00f0, SSLHandshakeException -> 0x00fb, SSLException -> 0x0106, ConnectException -> 0x0111, Exception -> 0x011c }
        r0 = new java.util.concurrent.CancellationException;	 Catch:{ UnknownHostException -> 0x0047, SocketTimeoutException -> 0x009b, ConnectTimeoutException -> 0x00f0, SSLHandshakeException -> 0x00fb, SSLException -> 0x0106, ConnectException -> 0x0111, Exception -> 0x011c }
        r2 = "call canceled";
        r0.<init>(r2);	 Catch:{ UnknownHostException -> 0x0047, SocketTimeoutException -> 0x009b, ConnectTimeoutException -> 0x00f0, SSLHandshakeException -> 0x00fb, SSLException -> 0x0106, ConnectException -> 0x0111, Exception -> 0x011c }
        throw r0;	 Catch:{ UnknownHostException -> 0x0047, SocketTimeoutException -> 0x009b, ConnectTimeoutException -> 0x00f0, SSLHandshakeException -> 0x00fb, SSLException -> 0x0106, ConnectException -> 0x0111, Exception -> 0x011c }
    L_0x009b:
        r0 = move-exception;
        r2 = -2;
        r0 = r0.getMessage();
        r4 = r2;
        r2 = r0;
        r0 = r1;
        goto L_0x0006;
    L_0x00a6:
        if (r5 == 0) goto L_0x00b3;
    L_0x00a8:
        r5 = new java.util.zip.GZIPInputStream;	 Catch:{ UnknownHostException -> 0x0047, SocketTimeoutException -> 0x009b, ConnectTimeoutException -> 0x00f0, SSLHandshakeException -> 0x00fb, SSLException -> 0x0106, ConnectException -> 0x0111, Exception -> 0x011c }
        r0 = r0.getInputStream();	 Catch:{ UnknownHostException -> 0x0047, SocketTimeoutException -> 0x009b, ConnectTimeoutException -> 0x00f0, SSLHandshakeException -> 0x00fb, SSLException -> 0x0106, ConnectException -> 0x0111, Exception -> 0x011c }
        r5.<init>(r0);	 Catch:{ UnknownHostException -> 0x0047, SocketTimeoutException -> 0x009b, ConnectTimeoutException -> 0x00f0, SSLHandshakeException -> 0x00fb, SSLException -> 0x0106, ConnectException -> 0x0111, Exception -> 0x011c }
        r0 = r5;
        goto L_0x007d;
    L_0x00b3:
        r0 = r0.getInputStream();	 Catch:{ UnknownHostException -> 0x0047, SocketTimeoutException -> 0x009b, ConnectTimeoutException -> 0x00f0, SSLHandshakeException -> 0x00fb, SSLException -> 0x0106, ConnectException -> 0x0111, Exception -> 0x011c }
        goto L_0x007d;
    L_0x00b8:
        r5.c();	 Catch:{ UnknownHostException -> 0x0047, SocketTimeoutException -> 0x009b, ConnectTimeoutException -> 0x00f0, SSLHandshakeException -> 0x00fb, SSLException -> 0x0106, ConnectException -> 0x0111, Exception -> 0x011c }
        r0 = new mtopsdk.a.b.f;	 Catch:{ UnknownHostException -> 0x0047, SocketTimeoutException -> 0x009b, ConnectTimeoutException -> 0x00f0, SSLHandshakeException -> 0x00fb, SSLException -> 0x0106, ConnectException -> 0x0111, Exception -> 0x011c }
        r0.<init>();	 Catch:{ UnknownHostException -> 0x0047, SocketTimeoutException -> 0x009b, ConnectTimeoutException -> 0x00f0, SSLHandshakeException -> 0x00fb, SSLException -> 0x0106, ConnectException -> 0x0111, Exception -> 0x011c }
        r0 = r0.a(r7);	 Catch:{ UnknownHostException -> 0x0047, SocketTimeoutException -> 0x009b, ConnectTimeoutException -> 0x00f0, SSLHandshakeException -> 0x00fb, SSLException -> 0x0106, ConnectException -> 0x0111, Exception -> 0x011c }
        r0 = r0.a(r8);	 Catch:{ UnknownHostException -> 0x0047, SocketTimeoutException -> 0x009b, ConnectTimeoutException -> 0x00f0, SSLHandshakeException -> 0x00fb, SSLException -> 0x0106, ConnectException -> 0x0111, Exception -> 0x011c }
        r0 = r0.a(r6);	 Catch:{ UnknownHostException -> 0x0047, SocketTimeoutException -> 0x009b, ConnectTimeoutException -> 0x00f0, SSLHandshakeException -> 0x00fb, SSLException -> 0x0106, ConnectException -> 0x0111, Exception -> 0x011c }
        r0 = r0.a(r9);	 Catch:{ UnknownHostException -> 0x0047, SocketTimeoutException -> 0x009b, ConnectTimeoutException -> 0x00f0, SSLHandshakeException -> 0x00fb, SSLException -> 0x0106, ConnectException -> 0x0111, Exception -> 0x011c }
        r0 = r0.a(r5);	 Catch:{ UnknownHostException -> 0x0047, SocketTimeoutException -> 0x009b, ConnectTimeoutException -> 0x00f0, SSLHandshakeException -> 0x00fb, SSLException -> 0x0106, ConnectException -> 0x0111, Exception -> 0x011c }
        r0 = r0.a();	 Catch:{ UnknownHostException -> 0x0047, SocketTimeoutException -> 0x009b, ConnectTimeoutException -> 0x00f0, SSLHandshakeException -> 0x00fb, SSLException -> 0x0106, ConnectException -> 0x0111, Exception -> 0x011c }
    L_0x00d8:
        if (r0 != 0) goto L_0x00ef;
    L_0x00da:
        r0 = new mtopsdk.a.b.f;
        r0.<init>();
        r0 = r0.a(r7);
        r0 = r0.a(r4);
        r0 = r0.a(r2);
        r0 = r0.a();
    L_0x00ef:
        return r0;
    L_0x00f0:
        r0 = move-exception;
        r2 = -3;
        r0 = r0.getMessage();
        r4 = r2;
        r2 = r0;
        r0 = r1;
        goto L_0x0006;
    L_0x00fb:
        r0 = move-exception;
        r2 = -4;
        r0 = r0.getMessage();
        r4 = r2;
        r2 = r0;
        r0 = r1;
        goto L_0x0006;
    L_0x0106:
        r0 = move-exception;
        r2 = -5;
        r0 = r0.getMessage();
        r4 = r2;
        r2 = r0;
        r0 = r1;
        goto L_0x0006;
    L_0x0111:
        r0 = move-exception;
        r2 = -6;
        r0 = r0.getMessage();
        r4 = r2;
        r2 = r0;
        r0 = r1;
        goto L_0x0006;
    L_0x011c:
        r0 = move-exception;
        r2 = -7;
        r0 = r0.getMessage();
        r4 = r2;
        r2 = r0;
        r0 = r1;
        goto L_0x0006;
    L_0x0127:
        r6 = r5;
        goto L_0x005e;
    L_0x012a:
        r0 = r3;
        goto L_0x00d8;
        */
        throw new UnsupportedOperationException("Method not decompiled: mtopsdk.a.c.b():mtopsdk.a.b.e");
    }

    public final void c() {
        m.a("mtopsdk.DefaultCallImpl", "try to cancel call");
        this.a = true;
        if (this.d != null) {
            this.d.cancel(true);
        }
    }
}
