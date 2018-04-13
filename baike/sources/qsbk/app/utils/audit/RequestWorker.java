package qsbk.app.utils.audit;

import android.net.Uri;
import android.text.TextUtils;
import android.util.Pair;
import cz.msebera.android.httpclient.HttpHeaders;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.BlockingQueue;
import qsbk.app.Constants;
import qsbk.app.utils.DomainRecord;
import qsbk.app.utils.HttpDNSManager;
import qsbk.app.utils.image.issue.IOExceptionWrapper;

public class RequestWorker extends Thread {
    public static final int HTTP_PERMANENT_REDIRECT = 308;
    public static final int HTTP_TEMPORARY_REDIRECT = 307;
    private final BlockingQueue<Request> a;
    private boolean b = false;

    public RequestWorker(BlockingQueue<Request> blockingQueue) {
        this.a = blockingQueue;
    }

    private static Uri a(Uri uri, String str) {
        return Uri.parse(uri.toString().replaceFirst(uri.getHost(), str));
    }

    static Pair<Uri, DomainRecord> a(Uri uri) {
        if (uri == null) {
            return null;
        }
        try {
            String host = uri.getHost();
            if (TextUtils.isEmpty(host) || host.matches("[\\d.]+") || "localhost".equalsIgnoreCase(host)) {
                return null;
            }
            Object httpDnsIp = HttpDNSManager.instance().getHttpDnsIp(host);
            if (TextUtils.isEmpty(httpDnsIp)) {
                return null;
            }
            return new Pair(uri, new DomainRecord(host, httpDnsIp));
        } catch (Exception e) {
            return null;
        }
    }

    static Pair<HttpURLConnection, Pair<Uri, DomainRecord>> b(Uri uri) throws IOException {
        Pair a = a(uri);
        if (a != null) {
            uri = a(uri, ((DomainRecord) a.second).ip);
        }
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(uri.toString()).openConnection();
        httpURLConnection.setConnectTimeout(10000);
        httpURLConnection.setReadTimeout(45000);
        httpURLConnection.setRequestProperty("Source", "android_" + Constants.localVersionName);
        if (a != null) {
            httpURLConnection.setRequestProperty("Host", ((DomainRecord) a.second).domain);
        }
        return new Pair(httpURLConnection, a);
    }

    private static boolean a(int i) {
        return i >= 200 && i < 300;
    }

    private static boolean b(int i) {
        switch (i) {
            case 300:
            case 301:
            case 302:
            case 303:
            case 307:
            case 308:
                return true;
            default:
                return false;
        }
    }

    private Pair<HttpURLConnection, Pair<Uri, DomainRecord>> a(Uri uri, int i) throws IOException {
        Pair<HttpURLConnection, Pair<Uri, DomainRecord>> b = b(uri);
        HttpURLConnection httpURLConnection = (HttpURLConnection) b.first;
        int responseCode = httpURLConnection.getResponseCode();
        if (a(responseCode)) {
            return b;
        }
        if (b(responseCode)) {
            String headerField = httpURLConnection.getHeaderField(HttpHeaders.LOCATION);
            httpURLConnection.disconnect();
            if (i > 0) {
                return a(Uri.parse(headerField), i - 1);
            }
            throw new IOException(String.format("URL %s follows too many redirects", new Object[]{uri.toString()}));
        }
        httpURLConnection.disconnect();
        throw new IOExceptionWrapper(responseCode, new IOException(String.format("Image URL %s returned HTTP code %d", new Object[]{uri.toString(), Integer.valueOf(responseCode)})));
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void run() {
        /*
        r15 = this;
        r8 = 0;
        r4 = 0;
        r1 = 10;
        android.os.Process.setThreadPriority(r1);
    L_0x0007:
        r1 = qsbk.app.utils.DebugUtil.DEBUG;	 Catch:{ InterruptedException -> 0x003b }
        if (r1 == 0) goto L_0x0014;
    L_0x000b:
        r1 = r15.getName();	 Catch:{ InterruptedException -> 0x003b }
        r2 = "request worker start and get one request.";
        android.util.Log.e(r1, r2);	 Catch:{ InterruptedException -> 0x003b }
    L_0x0014:
        r1 = r15.a;	 Catch:{ InterruptedException -> 0x003b }
        r1 = r1.take();	 Catch:{ InterruptedException -> 0x003b }
        r1 = (qsbk.app.utils.audit.Request) r1;	 Catch:{ InterruptedException -> 0x003b }
        r7 = r1.getRequestListener();	 Catch:{ LoaderException -> 0x018d, Exception -> 0x0189, OutOfMemoryError -> 0x00ad }
        r6 = 0;
        r10 = 0;
        r2 = r1.isCanceled();	 Catch:{ IOException -> 0x0198, all -> 0x0191 }
        if (r2 == 0) goto L_0x0041;
    L_0x0028:
        if (r4 == 0) goto L_0x002d;
    L_0x002a:
        r6.close();	 Catch:{ LoaderException -> 0x0033, Exception -> 0x007b, OutOfMemoryError -> 0x00ad }
    L_0x002d:
        if (r4 == 0) goto L_0x0007;
    L_0x002f:
        r10.close();	 Catch:{ LoaderException -> 0x0033, Exception -> 0x007b, OutOfMemoryError -> 0x00ad }
        goto L_0x0007;
    L_0x0033:
        r2 = move-exception;
        r3 = r7;
    L_0x0035:
        if (r3 == 0) goto L_0x0007;
    L_0x0037:
        r3.onError(r1, r2);
        goto L_0x0007;
    L_0x003b:
        r1 = move-exception;
        r1 = r15.b;
        if (r1 == 0) goto L_0x0007;
    L_0x0040:
        return;
    L_0x0041:
        r2 = r1.getUrl();	 Catch:{ IOException -> 0x0198, all -> 0x0191 }
        r2 = android.net.Uri.parse(r2);	 Catch:{ IOException -> 0x0198, all -> 0x0191 }
        r3 = 3;
        r3 = r15.a(r2, r3);	 Catch:{ IOException -> 0x0198, all -> 0x0191 }
        r2 = r3.first;	 Catch:{ IOException -> 0x0198, all -> 0x0191 }
        r2 = (java.net.HttpURLConnection) r2;	 Catch:{ IOException -> 0x0198, all -> 0x0191 }
        r3 = r3.second;	 Catch:{ IOException -> 0x0198, all -> 0x0191 }
        r3 = (android.util.Pair) r3;	 Catch:{ IOException -> 0x0198, all -> 0x0191 }
        r5 = "Content-Length";
        r5 = r2.getHeaderField(r5);	 Catch:{ IOException -> 0x019e, all -> 0x0191 }
        r9 = android.text.TextUtils.isEmpty(r5);	 Catch:{ IOException -> 0x019e, all -> 0x0191 }
        if (r9 != 0) goto L_0x01b0;
    L_0x0062:
        r5 = java.lang.Integer.parseInt(r5);	 Catch:{ Exception -> 0x008a }
        r9 = r5;
    L_0x0067:
        r5 = r1.isCanceled();	 Catch:{ IOException -> 0x019e, all -> 0x0191 }
        if (r5 == 0) goto L_0x008d;
    L_0x006d:
        r2.disconnect();	 Catch:{ IOException -> 0x019e, all -> 0x0191 }
        if (r4 == 0) goto L_0x0075;
    L_0x0072:
        r6.close();	 Catch:{ LoaderException -> 0x0033, Exception -> 0x007b, OutOfMemoryError -> 0x00ad }
    L_0x0075:
        if (r4 == 0) goto L_0x0007;
    L_0x0077:
        r10.close();	 Catch:{ LoaderException -> 0x0033, Exception -> 0x007b, OutOfMemoryError -> 0x00ad }
        goto L_0x0007;
    L_0x007b:
        r2 = move-exception;
    L_0x007c:
        if (r7 == 0) goto L_0x0007;
    L_0x007e:
        r3 = new qsbk.app.utils.audit.LoaderException;
        r5 = "Unhandler Exception";
        r3.<init>(r5, r2);
        r7.onError(r1, r3);
        goto L_0x0007;
    L_0x008a:
        r5 = move-exception;
        r9 = r8;
        goto L_0x0067;
    L_0x008d:
        if (r7 == 0) goto L_0x0092;
    L_0x008f:
        r7.onPrepare(r1, r9);	 Catch:{ IOException -> 0x019e, all -> 0x0191 }
    L_0x0092:
        r5 = r1.isCanceled();	 Catch:{ IOException -> 0x019e, all -> 0x0191 }
        if (r5 != 0) goto L_0x009e;
    L_0x0098:
        r5 = r1.isFinished();	 Catch:{ IOException -> 0x019e, all -> 0x0191 }
        if (r5 == 0) goto L_0x00b3;
    L_0x009e:
        r2.disconnect();	 Catch:{ IOException -> 0x019e, all -> 0x0191 }
        if (r4 == 0) goto L_0x00a6;
    L_0x00a3:
        r6.close();	 Catch:{ LoaderException -> 0x0033, Exception -> 0x007b, OutOfMemoryError -> 0x00ad }
    L_0x00a6:
        if (r4 == 0) goto L_0x0007;
    L_0x00a8:
        r10.close();	 Catch:{ LoaderException -> 0x0033, Exception -> 0x007b, OutOfMemoryError -> 0x00ad }
        goto L_0x0007;
    L_0x00ad:
        r1 = move-exception;
        java.lang.System.gc();
        goto L_0x0007;
    L_0x00b3:
        r2 = r2.getInputStream();	 Catch:{ IOException -> 0x019e, all -> 0x0191 }
        r6 = new java.io.BufferedInputStream;	 Catch:{ IOException -> 0x019e, all -> 0x0191 }
        r6.<init>(r2);	 Catch:{ IOException -> 0x019e, all -> 0x0191 }
        r5 = new java.io.ByteArrayOutputStream;	 Catch:{ IOException -> 0x01a6, all -> 0x0195 }
        r5.<init>();	 Catch:{ IOException -> 0x01a6, all -> 0x0195 }
        r2 = 1024; // 0x400 float:1.435E-42 double:5.06E-321;
        r10 = new byte[r2];	 Catch:{ IOException -> 0x00f0 }
        r2 = r8;
    L_0x00c6:
        r11 = r6.read(r10);	 Catch:{ IOException -> 0x00f0 }
        r12 = -1;
        if (r11 == r12) goto L_0x00d3;
    L_0x00cd:
        r12 = r1.isCanceled();	 Catch:{ IOException -> 0x00f0 }
        if (r12 == 0) goto L_0x00e5;
    L_0x00d3:
        r2 = r1.isCanceled();	 Catch:{ IOException -> 0x00f0 }
        if (r2 == 0) goto L_0x013e;
    L_0x00d9:
        if (r6 == 0) goto L_0x00de;
    L_0x00db:
        r6.close();	 Catch:{ LoaderException -> 0x0033, Exception -> 0x007b, OutOfMemoryError -> 0x00ad }
    L_0x00de:
        if (r5 == 0) goto L_0x0007;
    L_0x00e0:
        r5.close();	 Catch:{ LoaderException -> 0x0033, Exception -> 0x007b, OutOfMemoryError -> 0x00ad }
        goto L_0x0007;
    L_0x00e5:
        r12 = 0;
        r5.write(r10, r12, r11);	 Catch:{ IOException -> 0x00f0 }
        r2 = r2 + r11;
        if (r7 == 0) goto L_0x00c6;
    L_0x00ec:
        r7.onDownloading(r1, r9, r2);	 Catch:{ IOException -> 0x00f0 }
        goto L_0x00c6;
    L_0x00f0:
        r2 = move-exception;
        r14 = r2;
        r2 = r3;
        r3 = r14;
    L_0x00f4:
        if (r2 == 0) goto L_0x01ad;
    L_0x00f6:
        r9 = r2.second;	 Catch:{ all -> 0x0132 }
        if (r9 == 0) goto L_0x01ad;
    L_0x00fa:
        r2 = r2.second;	 Catch:{ all -> 0x0132 }
        r2 = (qsbk.app.utils.DomainRecord) r2;	 Catch:{ all -> 0x0132 }
        r2 = r2.ip;	 Catch:{ all -> 0x0132 }
    L_0x0100:
        r9 = qsbk.app.utils.image.issue.DisplayIssueManager.getInstance();	 Catch:{ all -> 0x0132 }
        r10 = qsbk.app.QsbkApp.mContext;	 Catch:{ all -> 0x0132 }
        r11 = r1.getUrl();	 Catch:{ all -> 0x0132 }
        r9.reportNewIssue(r10, r11, r3, r2);	 Catch:{ all -> 0x0132 }
        r2 = r3 instanceof qsbk.app.utils.image.issue.IOExceptionWrapper;	 Catch:{ all -> 0x0132 }
        if (r2 == 0) goto L_0x017f;
    L_0x0111:
        r0 = r3;
        r0 = (qsbk.app.utils.image.issue.IOExceptionWrapper) r0;	 Catch:{ all -> 0x0132 }
        r2 = r0;
        r9 = new qsbk.app.utils.audit.LoaderException;	 Catch:{ all -> 0x0132 }
        r10 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0132 }
        r10.<init>();	 Catch:{ all -> 0x0132 }
        r11 = "IOException, Sever response code ";
        r10 = r10.append(r11);	 Catch:{ all -> 0x0132 }
        r2 = r2.getResponseCode();	 Catch:{ all -> 0x0132 }
        r2 = r10.append(r2);	 Catch:{ all -> 0x0132 }
        r2 = r2.toString();	 Catch:{ all -> 0x0132 }
        r9.<init>(r2, r3);	 Catch:{ all -> 0x0132 }
        throw r9;	 Catch:{ all -> 0x0132 }
    L_0x0132:
        r2 = move-exception;
    L_0x0133:
        if (r6 == 0) goto L_0x0138;
    L_0x0135:
        r6.close();	 Catch:{ LoaderException -> 0x0033, Exception -> 0x007b, OutOfMemoryError -> 0x00ad }
    L_0x0138:
        if (r5 == 0) goto L_0x013d;
    L_0x013a:
        r5.close();	 Catch:{ LoaderException -> 0x0033, Exception -> 0x007b, OutOfMemoryError -> 0x00ad }
    L_0x013d:
        throw r2;	 Catch:{ LoaderException -> 0x0033, Exception -> 0x007b, OutOfMemoryError -> 0x00ad }
    L_0x013e:
        r2 = r5.toByteArray();	 Catch:{ IOException -> 0x00f0 }
        r10 = r2.length;	 Catch:{ IOException -> 0x00f0 }
        if (r10 != r9) goto L_0x0156;
    L_0x0145:
        if (r7 == 0) goto L_0x0156;
    L_0x0147:
        r7.onSuccess(r1, r9, r2);	 Catch:{ IOException -> 0x00f0 }
    L_0x014a:
        if (r6 == 0) goto L_0x014f;
    L_0x014c:
        r6.close();	 Catch:{ LoaderException -> 0x0033, Exception -> 0x007b, OutOfMemoryError -> 0x00ad }
    L_0x014f:
        if (r5 == 0) goto L_0x0007;
    L_0x0151:
        r5.close();	 Catch:{ LoaderException -> 0x0033, Exception -> 0x007b, OutOfMemoryError -> 0x00ad }
        goto L_0x0007;
    L_0x0156:
        if (r7 == 0) goto L_0x014a;
    L_0x0158:
        r10 = r1.isCanceled();	 Catch:{ IOException -> 0x00f0 }
        if (r10 != 0) goto L_0x014a;
    L_0x015e:
        r10 = new qsbk.app.utils.audit.LoaderException;	 Catch:{ IOException -> 0x00f0 }
        r11 = "Not enough data. Loaded %1d of %2d";
        r12 = 2;
        r12 = new java.lang.Object[r12];	 Catch:{ IOException -> 0x00f0 }
        r13 = 0;
        r2 = r2.length;	 Catch:{ IOException -> 0x00f0 }
        r2 = java.lang.Integer.valueOf(r2);	 Catch:{ IOException -> 0x00f0 }
        r12[r13] = r2;	 Catch:{ IOException -> 0x00f0 }
        r2 = 1;
        r9 = java.lang.Integer.valueOf(r9);	 Catch:{ IOException -> 0x00f0 }
        r12[r2] = r9;	 Catch:{ IOException -> 0x00f0 }
        r2 = java.lang.String.format(r11, r12);	 Catch:{ IOException -> 0x00f0 }
        r10.<init>(r2);	 Catch:{ IOException -> 0x00f0 }
        r7.onError(r1, r10);	 Catch:{ IOException -> 0x00f0 }
        goto L_0x014a;
    L_0x017f:
        r2 = new qsbk.app.utils.audit.LoaderException;	 Catch:{ all -> 0x0132 }
        r9 = r3.toString();	 Catch:{ all -> 0x0132 }
        r2.<init>(r9, r3);	 Catch:{ all -> 0x0132 }
        throw r2;	 Catch:{ all -> 0x0132 }
    L_0x0189:
        r2 = move-exception;
        r7 = r4;
        goto L_0x007c;
    L_0x018d:
        r2 = move-exception;
        r3 = r4;
        goto L_0x0035;
    L_0x0191:
        r2 = move-exception;
        r5 = r4;
        r6 = r4;
        goto L_0x0133;
    L_0x0195:
        r2 = move-exception;
        r5 = r4;
        goto L_0x0133;
    L_0x0198:
        r3 = move-exception;
        r2 = r4;
        r5 = r4;
        r6 = r4;
        goto L_0x00f4;
    L_0x019e:
        r2 = move-exception;
        r5 = r4;
        r6 = r4;
        r14 = r2;
        r2 = r3;
        r3 = r14;
        goto L_0x00f4;
    L_0x01a6:
        r2 = move-exception;
        r5 = r4;
        r14 = r3;
        r3 = r2;
        r2 = r14;
        goto L_0x00f4;
    L_0x01ad:
        r2 = r4;
        goto L_0x0100;
    L_0x01b0:
        r9 = r8;
        goto L_0x0067;
        */
        throw new UnsupportedOperationException("Method not decompiled: qsbk.app.utils.audit.RequestWorker.run():void");
    }

    public void quit() {
        this.b = true;
        interrupt();
    }
}
