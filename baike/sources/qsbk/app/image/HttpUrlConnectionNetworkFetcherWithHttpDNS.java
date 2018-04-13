package qsbk.app.image;

import android.net.Uri;
import android.text.TextUtils;
import android.util.Pair;
import com.facebook.common.internal.VisibleForTesting;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.producers.BaseNetworkFetcher;
import com.facebook.imagepipeline.producers.Consumer;
import com.facebook.imagepipeline.producers.FetchState;
import com.facebook.imagepipeline.producers.NetworkFetcher.Callback;
import com.facebook.imagepipeline.producers.ProducerContext;
import cz.msebera.android.httpclient.HttpHeaders;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import qsbk.app.utils.DomainRecord;
import qsbk.app.utils.HttpDNSManager;
import qsbk.app.utils.image.issue.IOExceptionWrapper;

public class HttpUrlConnectionNetworkFetcherWithHttpDNS extends BaseNetworkFetcher<FetchState> {
    public static final int HTTP_PERMANENT_REDIRECT = 308;
    public static final int HTTP_TEMPORARY_REDIRECT = 307;
    private final ExecutorService a;

    public HttpUrlConnectionNetworkFetcherWithHttpDNS() {
        this(Executors.newFixedThreadPool(3));
    }

    @VisibleForTesting
    public HttpUrlConnectionNetworkFetcherWithHttpDNS(ExecutorService executorService) {
        this.a = executorService;
    }

    private static Uri a(Uri uri, String str) {
        return Uri.parse(uri.toString().replaceFirst(uri.getHost(), str));
    }

    @VisibleForTesting
    static Pair<HttpURLConnection, Pair<Uri, DomainRecord>> a(Uri uri) throws IOException {
        Pair b = b(uri);
        if (b != null) {
            uri = a(uri, ((DomainRecord) b.second).ip);
        }
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(uri.toString()).openConnection();
        if (b != null) {
            httpURLConnection.setRequestProperty("Host", ((DomainRecord) b.second).domain);
        }
        return new Pair(httpURLConnection, b);
    }

    static boolean a(int i) {
        return i >= 200 && i < 300;
    }

    static boolean b(int i) {
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

    static final String a(String str, Object... objArr) {
        return String.format(Locale.getDefault(), str, objArr);
    }

    static Pair<Uri, DomainRecord> b(Uri uri) {
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

    public FetchState createFetchState(Consumer<EncodedImage> consumer, ProducerContext producerContext) {
        return new FetchState(consumer, producerContext);
    }

    public void fetch(FetchState fetchState, Callback callback) {
        fetchState.getContext().addCallbacks(new c(this, this.a.submit(new b(this, fetchState, callback)), callback));
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @com.facebook.common.internal.VisibleForTesting
    protected void a(com.facebook.imagepipeline.producers.FetchState r9, com.facebook.imagepipeline.producers.NetworkFetcher.Callback r10) {
        /*
        r8 = this;
        r4 = 0;
        r0 = r9.getUri();	 Catch:{ IOException -> 0x003d, all -> 0x007c }
        r1 = 5;
        r5 = r8.a(r0, r1);	 Catch:{ IOException -> 0x003d, all -> 0x007c }
        r0 = r5.first;	 Catch:{ IOException -> 0x00a0, all -> 0x007c }
        r0 = (java.net.HttpURLConnection) r0;	 Catch:{ IOException -> 0x00a0, all -> 0x007c }
        if (r0 == 0) goto L_0x00ba;
    L_0x0010:
        r2 = r0.getInputStream();	 Catch:{ IOException -> 0x00a6, all -> 0x0090 }
        r1 = -1;
        r10.onResponse(r2, r1);	 Catch:{ IOException -> 0x00ad, all -> 0x0095 }
        r3 = r2;
    L_0x0019:
        r1 = r5.second;	 Catch:{ IOException -> 0x00b4, all -> 0x009a }
        r1 = (android.util.Pair) r1;	 Catch:{ IOException -> 0x00b4, all -> 0x009a }
        if (r1 == 0) goto L_0x0032;
    L_0x001f:
        r6 = qsbk.app.utils.HttpDNSManager.instance();	 Catch:{ IOException -> 0x00b4, all -> 0x009a }
        r2 = r1.second;	 Catch:{ IOException -> 0x00b4, all -> 0x009a }
        r2 = (qsbk.app.utils.DomainRecord) r2;	 Catch:{ IOException -> 0x00b4, all -> 0x009a }
        r2 = r2.domain;	 Catch:{ IOException -> 0x00b4, all -> 0x009a }
        r1 = r1.second;	 Catch:{ IOException -> 0x00b4, all -> 0x009a }
        r1 = (qsbk.app.utils.DomainRecord) r1;	 Catch:{ IOException -> 0x00b4, all -> 0x009a }
        r1 = r1.ip;	 Catch:{ IOException -> 0x00b4, all -> 0x009a }
        r6.reportOK(r2, r1);	 Catch:{ IOException -> 0x00b4, all -> 0x009a }
    L_0x0032:
        if (r3 == 0) goto L_0x0037;
    L_0x0034:
        r3.close();	 Catch:{ IOException -> 0x008a }
    L_0x0037:
        if (r0 == 0) goto L_0x003c;
    L_0x0039:
        r0.disconnect();
    L_0x003c:
        return;
    L_0x003d:
        r0 = move-exception;
        r2 = r0;
        r3 = r4;
        r5 = r4;
        r0 = r4;
    L_0x0042:
        r10.onFailure(r2);	 Catch:{ all -> 0x009e }
        if (r0 == 0) goto L_0x0060;
    L_0x0047:
        r0 = r0.second;	 Catch:{ all -> 0x009e }
        r0 = (android.util.Pair) r0;	 Catch:{ all -> 0x009e }
        if (r0 == 0) goto L_0x0060;
    L_0x004d:
        r1 = r0.second;	 Catch:{ all -> 0x009e }
        r1 = (qsbk.app.utils.DomainRecord) r1;	 Catch:{ all -> 0x009e }
        r4 = r1.ip;	 Catch:{ all -> 0x009e }
        r1 = qsbk.app.utils.HttpDNSManager.instance();	 Catch:{ all -> 0x009e }
        r0 = r0.second;	 Catch:{ all -> 0x009e }
        r0 = (qsbk.app.utils.DomainRecord) r0;	 Catch:{ all -> 0x009e }
        r0 = r0.domain;	 Catch:{ all -> 0x009e }
        r1.reportError(r0, r4);	 Catch:{ all -> 0x009e }
    L_0x0060:
        r0 = qsbk.app.utils.image.issue.DisplayIssueManager.getInstance();	 Catch:{ all -> 0x009e }
        r1 = qsbk.app.QsbkApp.mContext;	 Catch:{ all -> 0x009e }
        r6 = r9.getUri();	 Catch:{ all -> 0x009e }
        r6 = r6.toString();	 Catch:{ all -> 0x009e }
        r0.reportNewIssue(r1, r6, r2, r4);	 Catch:{ all -> 0x009e }
        if (r3 == 0) goto L_0x0076;
    L_0x0073:
        r3.close();	 Catch:{ IOException -> 0x008c }
    L_0x0076:
        if (r5 == 0) goto L_0x003c;
    L_0x0078:
        r5.disconnect();
        goto L_0x003c;
    L_0x007c:
        r0 = move-exception;
        r3 = r4;
        r5 = r4;
    L_0x007f:
        if (r3 == 0) goto L_0x0084;
    L_0x0081:
        r3.close();	 Catch:{ IOException -> 0x008e }
    L_0x0084:
        if (r5 == 0) goto L_0x0089;
    L_0x0086:
        r5.disconnect();
    L_0x0089:
        throw r0;
    L_0x008a:
        r1 = move-exception;
        goto L_0x0037;
    L_0x008c:
        r0 = move-exception;
        goto L_0x0076;
    L_0x008e:
        r1 = move-exception;
        goto L_0x0084;
    L_0x0090:
        r1 = move-exception;
        r3 = r4;
        r5 = r0;
        r0 = r1;
        goto L_0x007f;
    L_0x0095:
        r1 = move-exception;
        r3 = r2;
        r5 = r0;
        r0 = r1;
        goto L_0x007f;
    L_0x009a:
        r1 = move-exception;
        r5 = r0;
        r0 = r1;
        goto L_0x007f;
    L_0x009e:
        r0 = move-exception;
        goto L_0x007f;
    L_0x00a0:
        r0 = move-exception;
        r2 = r0;
        r3 = r4;
        r0 = r5;
        r5 = r4;
        goto L_0x0042;
    L_0x00a6:
        r1 = move-exception;
        r2 = r1;
        r3 = r4;
        r7 = r0;
        r0 = r5;
        r5 = r7;
        goto L_0x0042;
    L_0x00ad:
        r1 = move-exception;
        r3 = r2;
        r2 = r1;
        r7 = r0;
        r0 = r5;
        r5 = r7;
        goto L_0x0042;
    L_0x00b4:
        r1 = move-exception;
        r2 = r1;
        r7 = r0;
        r0 = r5;
        r5 = r7;
        goto L_0x0042;
    L_0x00ba:
        r3 = r4;
        goto L_0x0019;
        */
        throw new UnsupportedOperationException("Method not decompiled: qsbk.app.image.HttpUrlConnectionNetworkFetcherWithHttpDNS.a(com.facebook.imagepipeline.producers.FetchState, com.facebook.imagepipeline.producers.NetworkFetcher$Callback):void");
    }

    protected Pair<HttpURLConnection, Pair<Uri, DomainRecord>> a(Uri uri, int i) throws IOException {
        Pair<HttpURLConnection, Pair<Uri, DomainRecord>> a = a(uri);
        HttpURLConnection httpURLConnection = (HttpURLConnection) a.first;
        int responseCode = httpURLConnection.getResponseCode();
        if (a(responseCode)) {
            return a;
        }
        if (b(responseCode)) {
            String headerField = httpURLConnection.getHeaderField(HttpHeaders.LOCATION);
            httpURLConnection.disconnect();
            Uri parse = headerField == null ? null : Uri.parse(headerField);
            headerField = uri.getScheme();
            if (i > 0 && parse != null && !parse.getScheme().equals(headerField)) {
                return a(parse, i - 1);
            }
            String a2;
            if (i == 0) {
                a2 = a("URL %s follows too many redirects", uri.toString());
            } else {
                a2 = a("URL %s returned %d without a valid redirect", uri.toString(), Integer.valueOf(responseCode));
            }
            throw new IOExceptionWrapper(responseCode, new IOException(a2));
        }
        httpURLConnection.disconnect();
        throw new IOExceptionWrapper(responseCode, new IOException(String.format("Image URL %s returned HTTP code %d", new Object[]{uri.toString(), Integer.valueOf(responseCode)})));
    }
}
