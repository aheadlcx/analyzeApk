package com.google.analytics.tracking.android;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Build.VERSION;
import android.text.TextUtils;
import com.google.android.gms.common.util.VisibleForTesting;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Locale;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.client.HttpClient;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicHttpEntityEnclosingRequest;

class SimpleNetworkDispatcher implements Dispatcher {
    private static final String USER_AGENT_TEMPLATE = "%s/%s (Linux; U; Android %s; %s; %s Build/%s)";
    private final Context ctx;
    private GoogleAnalytics gaInstance;
    private final HttpClient httpClient;
    private URL mOverrideHostUrl;
    private final String userAgent;

    @VisibleForTesting
    SimpleNetworkDispatcher(HttpClient httpClient, GoogleAnalytics googleAnalytics, Context context) {
        this.ctx = context.getApplicationContext();
        this.userAgent = createUserAgentString("GoogleAnalytics", "3.0", VERSION.RELEASE, Utils.getLanguage(Locale.getDefault()), Build.MODEL, Build.ID);
        this.httpClient = httpClient;
        this.gaInstance = googleAnalytics;
    }

    SimpleNetworkDispatcher(HttpClient httpClient, Context context) {
        this(httpClient, GoogleAnalytics.getInstance(context), context);
    }

    public boolean okToDispatch() {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) this.ctx.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
            return true;
        }
        Log.v("...no network connectivity");
        return false;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int dispatchHits(java.util.List<com.google.analytics.tracking.android.Hit> r13) {
        /*
        r12 = this;
        r3 = 0;
        r0 = r13.size();
        r1 = 40;
        r5 = java.lang.Math.min(r0, r1);
        r1 = 1;
        r4 = r3;
        r2 = r3;
    L_0x000e:
        if (r4 >= r5) goto L_0x0122;
    L_0x0010:
        r0 = r13.get(r4);
        r0 = (com.google.analytics.tracking.android.Hit) r0;
        r6 = r12.getUrl(r0);
        if (r6 != 0) goto L_0x0049;
    L_0x001c:
        r6 = com.google.analytics.tracking.android.Log.isVerbose();
        if (r6 == 0) goto L_0x0043;
    L_0x0022:
        r6 = new java.lang.StringBuilder;
        r6.<init>();
        r7 = "No destination: discarding hit: ";
        r6 = r6.append(r7);
        r0 = r0.getHitParams();
        r0 = r6.append(r0);
        r0 = r0.toString();
        com.google.analytics.tracking.android.Log.w(r0);
    L_0x003c:
        r0 = r2 + 1;
    L_0x003e:
        r2 = r4 + 1;
        r4 = r2;
        r2 = r0;
        goto L_0x000e;
    L_0x0043:
        r0 = "No destination: discarding hit.";
        com.google.analytics.tracking.android.Log.w(r0);
        goto L_0x003c;
    L_0x0049:
        r7 = new org.apache.http.HttpHost;
        r8 = r6.getHost();
        r9 = r6.getPort();
        r10 = r6.getProtocol();
        r7.<init>(r8, r9, r10);
        r6 = r6.getPath();
        r8 = r0.getHitParams();
        r8 = android.text.TextUtils.isEmpty(r8);
        if (r8 == 0) goto L_0x0073;
    L_0x0068:
        r0 = "";
    L_0x006a:
        r6 = r12.buildRequest(r0, r6);
        if (r6 != 0) goto L_0x007c;
    L_0x0070:
        r0 = r2 + 1;
        goto L_0x003e;
    L_0x0073:
        r8 = java.lang.System.currentTimeMillis();
        r0 = com.google.analytics.tracking.android.HitBuilder.postProcessHit(r0, r8);
        goto L_0x006a;
    L_0x007c:
        r8 = "Host";
        r9 = r7.toHostString();
        r6.addHeader(r8, r9);
        r8 = com.google.analytics.tracking.android.Log.isVerbose();
        if (r8 == 0) goto L_0x008e;
    L_0x008b:
        r12.logDebugInformation(r6);
    L_0x008e:
        r0 = r0.length();
        r8 = 8192; // 0x2000 float:1.14794E-41 double:4.0474E-320;
        if (r0 <= r8) goto L_0x00a2;
    L_0x0096:
        r0 = "Hit too long (> 8192 bytes)--not sent";
        com.google.analytics.tracking.android.Log.w(r0);
        r0 = r1;
    L_0x009c:
        r1 = r2 + 1;
        r11 = r0;
        r0 = r1;
        r1 = r11;
        goto L_0x003e;
    L_0x00a2:
        r0 = r12.gaInstance;
        r0 = r0.isDryRunEnabled();
        if (r0 == 0) goto L_0x00b1;
    L_0x00aa:
        r0 = "Dry run enabled. Hit not actually sent.";
        com.google.analytics.tracking.android.Log.i(r0);
        r0 = r1;
        goto L_0x009c;
    L_0x00b1:
        if (r1 == 0) goto L_0x0127;
    L_0x00b3:
        r0 = r12.ctx;	 Catch:{ ClientProtocolException -> 0x0124, IOException -> 0x00fa }
        com.google.analytics.tracking.android.GANetworkReceiver.sendRadioPoweredBroadcast(r0);	 Catch:{ ClientProtocolException -> 0x0124, IOException -> 0x00fa }
        r0 = r3;
    L_0x00b9:
        r1 = r12.httpClient;	 Catch:{ ClientProtocolException -> 0x00f3, IOException -> 0x00fa }
        r1 = r1.execute(r7, r6);	 Catch:{ ClientProtocolException -> 0x00f3, IOException -> 0x00fa }
        r6 = r1.getStatusLine();	 Catch:{ ClientProtocolException -> 0x00f3, IOException -> 0x00fa }
        r6 = r6.getStatusCode();	 Catch:{ ClientProtocolException -> 0x00f3, IOException -> 0x00fa }
        r7 = r1.getEntity();	 Catch:{ ClientProtocolException -> 0x00f3, IOException -> 0x00fa }
        if (r7 == 0) goto L_0x00d0;
    L_0x00cd:
        r7.consumeContent();	 Catch:{ ClientProtocolException -> 0x00f3, IOException -> 0x00fa }
    L_0x00d0:
        r7 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        if (r6 == r7) goto L_0x009c;
    L_0x00d4:
        r6 = new java.lang.StringBuilder;	 Catch:{ ClientProtocolException -> 0x00f3, IOException -> 0x00fa }
        r6.<init>();	 Catch:{ ClientProtocolException -> 0x00f3, IOException -> 0x00fa }
        r7 = "Bad response: ";
        r6 = r6.append(r7);	 Catch:{ ClientProtocolException -> 0x00f3, IOException -> 0x00fa }
        r1 = r1.getStatusLine();	 Catch:{ ClientProtocolException -> 0x00f3, IOException -> 0x00fa }
        r1 = r1.getStatusCode();	 Catch:{ ClientProtocolException -> 0x00f3, IOException -> 0x00fa }
        r1 = r6.append(r1);	 Catch:{ ClientProtocolException -> 0x00f3, IOException -> 0x00fa }
        r1 = r1.toString();	 Catch:{ ClientProtocolException -> 0x00f3, IOException -> 0x00fa }
        com.google.analytics.tracking.android.Log.w(r1);	 Catch:{ ClientProtocolException -> 0x00f3, IOException -> 0x00fa }
        goto L_0x009c;
    L_0x00f3:
        r1 = move-exception;
    L_0x00f4:
        r1 = "ClientProtocolException sending hit; discarding hit...";
        com.google.analytics.tracking.android.Log.w(r1);
        goto L_0x009c;
    L_0x00fa:
        r0 = move-exception;
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r3 = "Exception sending hit: ";
        r1 = r1.append(r3);
        r3 = r0.getClass();
        r3 = r3.getSimpleName();
        r1 = r1.append(r3);
        r1 = r1.toString();
        com.google.analytics.tracking.android.Log.w(r1);
        r0 = r0.getMessage();
        com.google.analytics.tracking.android.Log.w(r0);
        r0 = r2;
    L_0x0121:
        return r0;
    L_0x0122:
        r0 = r2;
        goto L_0x0121;
    L_0x0124:
        r0 = move-exception;
        r0 = r1;
        goto L_0x00f4;
    L_0x0127:
        r0 = r1;
        goto L_0x00b9;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.analytics.tracking.android.SimpleNetworkDispatcher.dispatchHits(java.util.List):int");
    }

    public void close() {
        this.httpClient.getConnectionManager().shutdown();
    }

    private HttpEntityEnclosingRequest buildRequest(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            Log.w("Empty hit, discarding.");
            return null;
        }
        HttpEntityEnclosingRequest basicHttpEntityEnclosingRequest;
        String str3 = str2 + "?" + str;
        if (str3.length() < 2036) {
            basicHttpEntityEnclosingRequest = new BasicHttpEntityEnclosingRequest("GET", str3);
        } else {
            basicHttpEntityEnclosingRequest = new BasicHttpEntityEnclosingRequest("POST", str2);
            try {
                basicHttpEntityEnclosingRequest.setEntity(new StringEntity(str));
            } catch (UnsupportedEncodingException e) {
                Log.w("Encoding error, discarding hit");
                return null;
            }
        }
        basicHttpEntityEnclosingRequest.addHeader("User-Agent", this.userAgent);
        return basicHttpEntityEnclosingRequest;
    }

    private void logDebugInformation(HttpEntityEnclosingRequest httpEntityEnclosingRequest) {
        StringBuffer stringBuffer = new StringBuffer();
        for (Object obj : httpEntityEnclosingRequest.getAllHeaders()) {
            stringBuffer.append(obj.toString()).append("\n");
        }
        stringBuffer.append(httpEntityEnclosingRequest.getRequestLine().toString()).append("\n");
        if (httpEntityEnclosingRequest.getEntity() != null) {
            try {
                InputStream content = httpEntityEnclosingRequest.getEntity().getContent();
                if (content != null) {
                    int available = content.available();
                    if (available > 0) {
                        byte[] bArr = new byte[available];
                        content.read(bArr);
                        stringBuffer.append("POST:\n");
                        stringBuffer.append(new String(bArr)).append("\n");
                    }
                }
            } catch (IOException e) {
                Log.v("Error Writing hit to log...");
            }
        }
        Log.v(stringBuffer.toString());
    }

    String createUserAgentString(String str, String str2, String str3, String str4, String str5, String str6) {
        return String.format(USER_AGENT_TEMPLATE, new Object[]{str, str2, str3, str4, str5, str6});
    }

    @VisibleForTesting
    URL getUrl(Hit hit) {
        if (this.mOverrideHostUrl != null) {
            return this.mOverrideHostUrl;
        }
        try {
            return new URL("http:".equals(hit.getHitUrlScheme()) ? "http://www.google-analytics.com/collect" : "https://ssl.google-analytics.com/collect");
        } catch (MalformedURLException e) {
            Log.e("Error trying to parse the hardcoded host url. This really shouldn't happen.");
            return null;
        }
    }

    @VisibleForTesting
    public void overrideHostUrl(String str) {
        try {
            this.mOverrideHostUrl = new URL(str);
        } catch (MalformedURLException e) {
            this.mOverrideHostUrl = null;
        }
    }
}
