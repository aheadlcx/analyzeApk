package com.bumptech.glide.load.a;

import android.text.TextUtils;
import android.util.Log;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.b.d;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Map;
import java.util.Map.Entry;

public class f implements c<InputStream> {
    private static final b a = new a();
    private final d b;
    private final b c;
    private HttpURLConnection d;
    private InputStream e;
    private volatile boolean f;

    interface b {
        HttpURLConnection a(URL url) throws IOException;
    }

    private static class a implements b {
        private a() {
        }

        public HttpURLConnection a(URL url) throws IOException {
            return (HttpURLConnection) url.openConnection();
        }
    }

    public /* synthetic */ Object a(Priority priority) throws Exception {
        return b(priority);
    }

    public f(d dVar) {
        this(dVar, a);
    }

    f(d dVar, b bVar) {
        this.b = dVar;
        this.c = bVar;
    }

    public InputStream b(Priority priority) throws Exception {
        return a(this.b.a(), 0, null, this.b.b());
    }

    private InputStream a(URL url, int i, URL url2, Map<String, String> map) throws IOException {
        if (i >= 5) {
            throw new IOException("Too many (> 5) redirects!");
        }
        if (url2 != null) {
            try {
                if (url.toURI().equals(url2.toURI())) {
                    throw new IOException("In re-direct loop");
                }
            } catch (URISyntaxException e) {
            }
        }
        this.d = this.c.a(url);
        for (Entry entry : map.entrySet()) {
            this.d.addRequestProperty((String) entry.getKey(), (String) entry.getValue());
        }
        this.d.setConnectTimeout(2500);
        this.d.setReadTimeout(2500);
        this.d.setUseCaches(false);
        this.d.setDoInput(true);
        this.d.connect();
        if (this.f) {
            return null;
        }
        int responseCode = this.d.getResponseCode();
        if (responseCode / 100 == 2) {
            return a(this.d);
        }
        if (responseCode / 100 == 3) {
            Object headerField = this.d.getHeaderField("Location");
            if (!TextUtils.isEmpty(headerField)) {
                return a(new URL(url, headerField), i + 1, url, map);
            }
            throw new IOException("Received empty or null redirect url");
        } else if (responseCode == -1) {
            throw new IOException("Unable to retrieve response code from HttpUrlConnection.");
        } else {
            throw new IOException("Request failed " + responseCode + ": " + this.d.getResponseMessage());
        }
    }

    private InputStream a(HttpURLConnection httpURLConnection) throws IOException {
        if (TextUtils.isEmpty(httpURLConnection.getContentEncoding())) {
            this.e = com.bumptech.glide.i.b.a(httpURLConnection.getInputStream(), (long) httpURLConnection.getContentLength());
        } else {
            if (Log.isLoggable("HttpUrlFetcher", 3)) {
                Log.d("HttpUrlFetcher", "Got non empty content encoding: " + httpURLConnection.getContentEncoding());
            }
            this.e = httpURLConnection.getInputStream();
        }
        return this.e;
    }

    public void a() {
        if (this.e != null) {
            try {
                this.e.close();
            } catch (IOException e) {
            }
        }
        if (this.d != null) {
            this.d.disconnect();
        }
    }

    public String b() {
        return this.b.c();
    }

    public void c() {
        this.f = true;
    }
}
