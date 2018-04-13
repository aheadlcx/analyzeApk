package cz.msebera.android.httpclient.impl.client.cache;

import com.alipay.sdk.sys.a;
import com.alipay.sdk.util.h;
import cz.msebera.android.httpclient.Consts;
import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HeaderElement;
import cz.msebera.android.httpclient.HttpHost;
import cz.msebera.android.httpclient.HttpRequest;
import cz.msebera.android.httpclient.annotation.Immutable;
import cz.msebera.android.httpclient.client.cache.HttpCacheEntry;
import cz.msebera.android.httpclient.client.utils.URIUtils;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.eclipse.paho.client.mqttv3.MqttTopic;

@Immutable
class i {
    private static final URI a = URI.create("http://example.com/");

    i() {
    }

    public String getURI(HttpHost httpHost, HttpRequest httpRequest) {
        if (!a(httpRequest)) {
            return canonicalizeUri(httpRequest.getRequestLine().getUri());
        }
        return canonicalizeUri(String.format("%s%s", new Object[]{httpHost.toString(), httpRequest.getRequestLine().getUri()}));
    }

    public String canonicalizeUri(String str) {
        try {
            URL url = new URL(URIUtils.resolve(a, str).toASCIIString());
            String protocol = url.getProtocol();
            String host = url.getHost();
            int a = a(url.getPort(), protocol);
            String path = url.getPath();
            String query = url.getQuery();
            if (query != null) {
                path = path + "?" + query;
            }
            str = new URL(protocol, host, a, path).toString();
        } catch (IllegalArgumentException e) {
        } catch (MalformedURLException e2) {
        }
        return str;
    }

    private int a(int i, String str) {
        if (i == -1 && "http".equalsIgnoreCase(str)) {
            return 80;
        }
        if (i == -1 && "https".equalsIgnoreCase(str)) {
            return 443;
        }
        return i;
    }

    private boolean a(HttpRequest httpRequest) {
        String uri = httpRequest.getRequestLine().getUri();
        return "*".equals(uri) || uri.startsWith(MqttTopic.TOPIC_LEVEL_SEPARATOR);
    }

    protected String a(Header[] headerArr) {
        if (headerArr == null) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder("");
        int length = headerArr.length;
        Object obj = 1;
        int i = 0;
        while (i < length) {
            Header header = headerArr[i];
            if (obj == null) {
                stringBuilder.append(", ");
            }
            stringBuilder.append(header.getValue().trim());
            i++;
            obj = null;
        }
        return stringBuilder.toString();
    }

    public String getVariantURI(HttpHost httpHost, HttpRequest httpRequest, HttpCacheEntry httpCacheEntry) {
        if (httpCacheEntry.hasVariants()) {
            return getVariantKey(httpRequest, httpCacheEntry) + getURI(httpHost, httpRequest);
        }
        return getURI(httpHost, httpRequest);
    }

    public String getVariantKey(HttpRequest httpRequest, HttpCacheEntry httpCacheEntry) {
        List<String> arrayList = new ArrayList();
        for (Header elements : httpCacheEntry.getHeaders("Vary")) {
            for (HeaderElement name : elements.getElements()) {
                arrayList.add(name.getName());
            }
        }
        Collections.sort(arrayList);
        try {
            StringBuilder stringBuilder = new StringBuilder("{");
            Object obj = 1;
            for (String str : arrayList) {
                if (obj == null) {
                    stringBuilder.append(a.b);
                }
                stringBuilder.append(URLEncoder.encode(str, Consts.UTF_8.name()));
                stringBuilder.append("=");
                stringBuilder.append(URLEncoder.encode(a(httpRequest.getHeaders(str)), Consts.UTF_8.name()));
                obj = null;
            }
            stringBuilder.append(h.d);
            return stringBuilder.toString();
        } catch (Throwable e) {
            throw new RuntimeException("couldn't encode to UTF-8", e);
        }
    }
}
