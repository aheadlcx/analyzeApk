package com.google.tagmanager;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Build.VERSION;
import com.google.android.gms.common.util.VisibleForTesting;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.Locale;
import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.message.BasicHttpEntityEnclosingRequest;

class SimpleNetworkDispatcher implements Dispatcher {
    private static final String USER_AGENT_TEMPLATE = "%s/%s (Linux; U; Android %s; %s; %s Build/%s)";
    private final Context ctx;
    private DispatchListener dispatchListener;
    private final HttpClient httpClient;
    private final String userAgent = createUserAgentString("GoogleTagManager", "3.02", VERSION.RELEASE, getUserAgentLanguage(Locale.getDefault()), Build.MODEL, Build.ID);

    public interface DispatchListener {
        void onHitDispatched(Hit hit);

        void onHitPermanentDispatchFailure(Hit hit);

        void onHitTransientDispatchFailure(Hit hit);
    }

    @VisibleForTesting
    SimpleNetworkDispatcher(HttpClient httpClient, Context context, DispatchListener dispatchListener) {
        this.ctx = context.getApplicationContext();
        this.httpClient = httpClient;
        this.dispatchListener = dispatchListener;
    }

    public boolean okToDispatch() {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) this.ctx.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
            return true;
        }
        Log.v("...no network connectivity");
        return false;
    }

    public void dispatchHits(List<Hit> list) {
        int min = Math.min(list.size(), 40);
        Object obj = 1;
        int i = 0;
        while (i < min) {
            Object obj2;
            Hit hit = (Hit) list.get(i);
            URL url = getUrl(hit);
            if (url == null) {
                Log.w("No destination: discarding hit.");
                this.dispatchListener.onHitPermanentDispatchFailure(hit);
                obj2 = obj;
            } else {
                HttpRequest constructGtmRequest = constructGtmRequest(url);
                if (constructGtmRequest == null) {
                    this.dispatchListener.onHitPermanentDispatchFailure(hit);
                    obj2 = obj;
                } else {
                    HttpHost httpHost = new HttpHost(url.getHost(), url.getPort(), url.getProtocol());
                    constructGtmRequest.addHeader("Host", httpHost.toHostString());
                    logDebugInformation(constructGtmRequest);
                    if (obj != null) {
                        try {
                            NetworkReceiver.sendRadioPoweredBroadcast(this.ctx);
                            obj = null;
                        } catch (ClientProtocolException e) {
                            Log.w("ClientProtocolException sending hit; discarding hit...");
                            this.dispatchListener.onHitPermanentDispatchFailure(hit);
                            obj2 = obj;
                        } catch (IOException e2) {
                            Log.w("Exception sending hit: " + e2.getClass().getSimpleName());
                            Log.w(e2.getMessage());
                            this.dispatchListener.onHitTransientDispatchFailure(hit);
                            obj2 = obj;
                        }
                    }
                    HttpResponse execute = this.httpClient.execute(httpHost, constructGtmRequest);
                    int statusCode = execute.getStatusLine().getStatusCode();
                    HttpEntity entity = execute.getEntity();
                    if (entity != null) {
                        entity.consumeContent();
                    }
                    if (statusCode != 200) {
                        Log.w("Bad response: " + execute.getStatusLine().getStatusCode());
                        this.dispatchListener.onHitTransientDispatchFailure(hit);
                    } else {
                        this.dispatchListener.onHitDispatched(hit);
                    }
                    obj2 = obj;
                }
            }
            i++;
            obj = obj2;
        }
    }

    public void close() {
        this.httpClient.getConnectionManager().shutdown();
    }

    private HttpEntityEnclosingRequest constructGtmRequest(URL url) {
        HttpEntityEnclosingRequest basicHttpEntityEnclosingRequest;
        URISyntaxException e;
        try {
            basicHttpEntityEnclosingRequest = new BasicHttpEntityEnclosingRequest("GET", url.toURI().toString());
            try {
                basicHttpEntityEnclosingRequest.addHeader("User-Agent", this.userAgent);
            } catch (URISyntaxException e2) {
                e = e2;
                Log.w("Exception sending hit: " + e.getClass().getSimpleName());
                Log.w(e.getMessage());
                return basicHttpEntityEnclosingRequest;
            }
        } catch (URISyntaxException e3) {
            URISyntaxException uRISyntaxException = e3;
            basicHttpEntityEnclosingRequest = null;
            e = uRISyntaxException;
            Log.w("Exception sending hit: " + e.getClass().getSimpleName());
            Log.w(e.getMessage());
            return basicHttpEntityEnclosingRequest;
        }
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

    static String getUserAgentLanguage(Locale locale) {
        if (locale == null || locale.getLanguage() == null || locale.getLanguage().length() == 0) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(locale.getLanguage().toLowerCase());
        if (!(locale.getCountry() == null || locale.getCountry().length() == 0)) {
            stringBuilder.append("-").append(locale.getCountry().toLowerCase());
        }
        return stringBuilder.toString();
    }

    @VisibleForTesting
    URL getUrl(Hit hit) {
        try {
            return new URL(hit.getHitUrl());
        } catch (MalformedURLException e) {
            Log.e("Error trying to parse the GTM url.");
            return null;
        }
    }
}
