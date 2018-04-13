package com.danikula.videocache;

import android.net.Uri;
import android.text.TextUtils;
import com.danikula.videocache.b.a;
import com.danikula.videocache.b.b;
import com.danikula.videocache.d.c;
import com.danikula.videocache.d.d;
import java.io.BufferedInputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map.Entry;

public class h implements n {
    private final c a;
    private final b b;
    private o c;
    private HttpURLConnection d;
    private InputStream e;
    private b f;
    private String g;
    private String h;
    private String i;
    private int j;
    private boolean k;

    public h(String str) {
        this(str, d.a());
    }

    public h(String str, c cVar) {
        this(str, cVar, new a());
    }

    public h(String str, c cVar, b bVar) {
        boolean z = false;
        this.i = "";
        this.k = false;
        this.a = (c) k.a((Object) cVar);
        this.b = (b) k.a((Object) bVar);
        try {
            o a = cVar.a(str);
            if (a != null) {
                z = true;
            }
            this.k = z;
            if (a == null) {
                a = new o(str, -2147483648L, m.a(str));
            }
            this.c = a;
        } catch (Exception e) {
            this.c = new o(str, -2147483648L, m.a(str));
        }
    }

    public h(h hVar) {
        this.i = "";
        this.k = false;
        this.c = hVar.c;
        this.a = hVar.a;
        this.b = hVar.b;
    }

    public synchronized long a() throws ProxyCacheException {
        if (this.c.b == -2147483648L) {
            e();
        }
        return this.c.b;
    }

    public void a(long j) throws ProxyCacheException {
        if (!b(j)) {
            List a = q.a().a(this.h);
            if (a == null || a.size() <= 0) {
                throw new ProxyCacheException("can't download data");
            }
            String str = (String) a.get(0);
            this.i = str;
            if (!a(j, this.h, str)) {
                a.remove(0);
                a.add(str);
            }
        }
    }

    private boolean b(long j) {
        try {
            long currentTimeMillis = System.currentTimeMillis();
            this.d = a(j, -1);
            String contentType = this.d.getContentType();
            this.e = new BufferedInputStream(this.d.getInputStream(), 5120);
            this.c = new o(this.c.a, a(this.d, j, this.d.getResponseCode()), contentType);
            this.a.a(this.c.a, this.c);
            long currentTimeMillis2 = System.currentTimeMillis() - currentTimeMillis;
            if (this.f == null) {
                return true;
            }
            this.f.a(this.c.a, this.d.getURL().toString(), currentTimeMillis2, this.d.getResponseCode(), null, this.k);
            return true;
        } catch (Exception e) {
            Exception exception = e;
            if (this.f != null) {
                String str = this.g;
                if (TextUtils.isEmpty(str)) {
                    str = this.c.a;
                }
                this.f.a(this.c.a, str, -1, 0, exception.getClass().getSimpleName(), this.k);
            }
            com.izuiyou.a.a.b.d("Error opening connection for " + this.c.a + " with offset " + j, new Object[]{exception});
            return false;
        }
    }

    private boolean a(long j, String str, String str2) throws ProxyCacheException {
        try {
            long currentTimeMillis = System.currentTimeMillis();
            this.d = a(j, -1, str, str2);
            String contentType = this.d.getContentType();
            this.e = new BufferedInputStream(this.d.getInputStream(), 5120);
            this.c = new o(this.c.a, a(this.d, j, this.d.getResponseCode()), contentType);
            this.a.a(this.c.a, this.c);
            long currentTimeMillis2 = System.currentTimeMillis() - currentTimeMillis;
            if (this.f != null) {
                this.f.a(this.c.a, this.g, currentTimeMillis2, 200, null, this.k);
            }
            return true;
        } catch (Throwable e) {
            if (this.f != null) {
                String str3 = this.g;
                if (TextUtils.isEmpty(str3)) {
                    str3 = this.c.a;
                }
                this.f.a(this.c.a, str3, -1, this.j, e.getClass().getSimpleName(), this.k);
            }
            throw new ProxyCacheException("Error opening connection for " + this.c.a + " with offset " + j, e);
        }
    }

    private long a(HttpURLConnection httpURLConnection, long j, int i) throws IOException {
        long a = a(httpURLConnection);
        if (i == 200) {
            return a;
        }
        return i == 206 ? a + j : this.c.b;
    }

    private long a(HttpURLConnection httpURLConnection) {
        String headerField = httpURLConnection.getHeaderField("Content-Length");
        return headerField == null ? -1 : Long.parseLong(headerField);
    }

    public void b() throws ProxyCacheException {
        Throwable e;
        if (this.d != null) {
            try {
                this.d.disconnect();
                return;
            } catch (NullPointerException e2) {
                e = e2;
            } catch (IllegalArgumentException e3) {
                e = e3;
            } catch (ArrayIndexOutOfBoundsException e4) {
                com.izuiyou.a.a.b.d("Error closing connection correctly. Should happen only on Android L. If anybody know how to fix it, please visit https://github.com/danikula/AndroidVideoCache/issues/88. Until good solution is not know, just ignore this issue :(", new Object[]{e4});
                return;
            }
        }
        return;
        throw new RuntimeException("Wait... but why? WTF!? Really shouldn't happen any more after fixing https://github.com/danikula/AndroidVideoCache/issues/43. If you read it on your device log, please, notify me danikula@gmail.com or create issue here https://github.com/danikula/AndroidVideoCache/issues.", e);
    }

    public int a(byte[] bArr) throws ProxyCacheException {
        Throwable th;
        if (this.e == null) {
            if (this.f != null) {
                this.f.a(this.c.a, this.h, this.i, 2, 0, "Download stream is null");
            }
            throw new ProxyCacheException("Error reading data from " + this.c.a + ": connection is absent!");
        }
        try {
            if (this.f != null) {
                this.f.a(this.c.a, this.h, this.i, 1, 0, "");
            }
            return this.e.read(bArr, 0, bArr.length);
        } catch (Throwable e) {
            th = e;
            if (this.f != null) {
                this.f.a(this.c.a, this.h, this.i, 6, 0, th.getClass().getSimpleName());
            }
            throw new InterruptedProxyCacheException("Reading source " + this.c.a + " is interrupted", th);
        } catch (Throwable e2) {
            th = e2;
            if (this.f != null) {
                this.f.a(this.c.a, this.h, this.i, 6, 0, th.getClass().getSimpleName());
            }
            throw new ProxyCacheException("Error reading data from " + this.c.a, th);
        } catch (Throwable e22) {
            th = e22;
            if (this.f != null) {
                this.f.a(this.c.a, this.h, this.i, 6, 0, th.getClass().getSimpleName());
            }
            throw new ProxyCacheException("Error reading data from " + this.c.a, th);
        }
    }

    private void e() throws ProxyCacheException {
        com.izuiyou.a.a.b.d("Read content info from " + this.c.a);
        if (!f()) {
            String host;
            if (!(this.f == null || this.h.contains("127.0.0.1"))) {
                com.izuiyou.a.a.b.d("onDownloadStateChange ip:" + this.i + ", host:" + this.h);
                this.f.a(this.c.a, this.h, this.i, 2, 0, "");
            }
            if (TextUtils.isEmpty(this.g) && !TextUtils.isEmpty(this.c.a)) {
                host = Uri.parse(this.c.a).getHost();
                if (a(host)) {
                    this.h = host;
                }
            }
            List a = q.a().a(this.h);
            com.izuiyou.a.a.b.d("try reconnect host:" + this.h + ", ip:" + q.a().a(this.h));
            if (a != null && a.size() > 0) {
                host = (String) a.get(0);
                this.i = host;
                if (!a(this.h, this.i)) {
                    a.remove(0);
                    a.add(host);
                    if (this.f != null && !this.h.contains("127.0")) {
                        com.izuiyou.a.a.b.d("onDownloadStateChange ip:" + this.i + ", host:" + this.h);
                        this.f.a(this.c.a, this.h, this.i, 2, 0, "");
                    }
                } else if (this.f != null && !this.h.contains("127.0")) {
                    com.izuiyou.a.a.b.d("onDownloadStateChange ip:" + this.i + ", host:" + this.h);
                    this.f.a(this.c.a, this.h, this.i, 1, 0, "");
                }
            }
        } else if (this.f != null && !this.h.contains("127.0")) {
            com.izuiyou.a.a.b.d("onDownloadStateChange ip:" + this.i + ", host:" + this.h);
            this.f.a(this.c.a, this.h, "", 1, 0, "");
        }
    }

    private boolean f() {
        HttpURLConnection a;
        long currentTimeMillis;
        Closeable inputStream;
        Object e;
        Throwable th;
        Closeable closeable = null;
        long currentTimeMillis2 = System.currentTimeMillis();
        try {
            long a2;
            String contentType;
            if (!(this.f == null || TextUtils.isEmpty(this.c.a) || !this.c.a.contains("api.izuiyou.com"))) {
                this.f.a(this.c.a, this.c.a, 0, 0, null, this.k);
            }
            a = a(0, -1);
            try {
                currentTimeMillis = System.currentTimeMillis() - currentTimeMillis2;
                if (this.f != null) {
                    this.f.a(this.c.a, this.g, currentTimeMillis, this.j, null, this.k);
                }
                a2 = a(a);
                contentType = a.getContentType();
                inputStream = a.getInputStream();
            } catch (Exception e2) {
                e = e2;
                try {
                    com.izuiyou.a.a.b.d("Error fetching info from " + this.g, new Object[]{e});
                    currentTimeMillis = System.currentTimeMillis() - currentTimeMillis2;
                    if (this.f != null) {
                        this.f.a(this.c.a, this.g, currentTimeMillis, 0, e.getClass().getSimpleName(), this.k);
                    }
                    m.a(closeable);
                    if (a != null) {
                        return false;
                    }
                    a.disconnect();
                    return false;
                } catch (Throwable th2) {
                    th = th2;
                    m.a(closeable);
                    if (a != null) {
                        a.disconnect();
                    }
                    throw th;
                }
            }
            try {
                this.c = new o(this.c.a, a2, contentType);
                this.a.a(this.c.a, this.c);
                com.izuiyou.a.a.b.d("Source info fetched: " + this.c);
                m.a(inputStream);
                if (a == null) {
                    return true;
                }
                a.disconnect();
                return true;
            } catch (Exception e3) {
                e = e3;
                closeable = inputStream;
                com.izuiyou.a.a.b.d("Error fetching info from " + this.g, new Object[]{e});
                currentTimeMillis = System.currentTimeMillis() - currentTimeMillis2;
                if (this.f != null) {
                    this.f.a(this.c.a, this.g, currentTimeMillis, 0, e.getClass().getSimpleName(), this.k);
                }
                m.a(closeable);
                if (a != null) {
                    return false;
                }
                a.disconnect();
                return false;
            } catch (Throwable th3) {
                th = th3;
                closeable = inputStream;
                m.a(closeable);
                if (a != null) {
                    a.disconnect();
                }
                throw th;
            }
        } catch (Exception e4) {
            e = e4;
            a = null;
            com.izuiyou.a.a.b.d("Error fetching info from " + this.g, new Object[]{e});
            currentTimeMillis = System.currentTimeMillis() - currentTimeMillis2;
            if (this.f != null) {
                this.f.a(this.c.a, this.g, currentTimeMillis, 0, e.getClass().getSimpleName(), this.k);
            }
            m.a(closeable);
            if (a != null) {
                return false;
            }
            a.disconnect();
            return false;
        } catch (Throwable th4) {
            th = th4;
            a = null;
            m.a(closeable);
            if (a != null) {
                a.disconnect();
            }
            throw th;
        }
    }

    private boolean a(String str, String str2) throws ProxyCacheException {
        boolean z;
        Object e;
        Throwable th;
        HttpURLConnection httpURLConnection = null;
        Closeable closeable = null;
        long currentTimeMillis = System.currentTimeMillis();
        long currentTimeMillis2;
        try {
            if (this.f != null) {
                this.f.a(this.c.a, this.c.a, 0, 0, null, this.k);
            }
            httpURLConnection = a(0, -1, str, str2);
            currentTimeMillis2 = System.currentTimeMillis() - currentTimeMillis;
            if (this.f != null) {
                this.f.a(this.c.a, this.g, currentTimeMillis2, this.j, null, this.k);
            }
            long a = a(httpURLConnection);
            String contentType = httpURLConnection.getContentType();
            Closeable inputStream = httpURLConnection.getInputStream();
            try {
                this.c = new o(this.c.a, a, contentType);
                this.a.a(this.c.a, this.c);
                com.izuiyou.a.a.b.d("Source info fetched: " + this.c);
                z = true;
                m.a(inputStream);
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
            } catch (Exception e2) {
                e = e2;
                closeable = inputStream;
                try {
                    com.izuiyou.a.a.b.d("Error fetching info from " + this.c.a, new Object[]{e});
                    currentTimeMillis2 = System.currentTimeMillis() - currentTimeMillis;
                    if (this.f != null) {
                        this.f.a(this.c.a, this.g, currentTimeMillis2, 0, e.getClass().getSimpleName(), this.k);
                    }
                    z = false;
                    m.a(closeable);
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                    return z;
                } catch (Throwable th2) {
                    th = th2;
                    m.a(closeable);
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                closeable = inputStream;
                m.a(closeable);
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
                throw th;
            }
        } catch (Exception e3) {
            e = e3;
            com.izuiyou.a.a.b.d("Error fetching info from " + this.c.a, new Object[]{e});
            currentTimeMillis2 = System.currentTimeMillis() - currentTimeMillis;
            if (this.f != null) {
                this.f.a(this.c.a, this.g, currentTimeMillis2, 0, e.getClass().getSimpleName(), this.k);
            }
            z = false;
            m.a(closeable);
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
            return z;
        }
        return z;
    }

    private HttpURLConnection a(long j, int i) throws IOException, ProxyCacheException {
        HttpURLConnection httpURLConnection;
        String str = TextUtils.isEmpty(this.g) ? this.c.a : this.g;
        int i2 = 0;
        boolean z;
        do {
            com.izuiyou.a.a.b.d("Open connection " + (j > 0 ? " with offset " + j : "") + " to " + str);
            String host = Uri.parse(str).getHost();
            if (a(host)) {
                this.h = host;
            }
            httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            httpURLConnection.setInstanceFollowRedirects(false);
            a(httpURLConnection, str);
            com.izuiyou.a.a.b.d("target url:" + str);
            if (j > 0) {
                httpURLConnection.setRequestProperty("Range", "bytes=" + j + "-");
            }
            int c = q.a().c();
            int d = q.a().d();
            if (c <= 5000) {
                c = 0;
            }
            httpURLConnection.setConnectTimeout(c);
            if (d > 5000) {
                c = d;
            } else {
                c = 0;
            }
            httpURLConnection.setReadTimeout(c);
            this.j = httpURLConnection.getResponseCode();
            this.g = httpURLConnection.getURL().toString();
            if (this.j == 301 || this.j == 302 || this.j == 303) {
                z = true;
            } else {
                z = false;
            }
            if (z) {
                str = httpURLConnection.getHeaderField("Location");
                this.g = str;
                String host2 = Uri.parse(this.g).getHost();
                if (a(host2)) {
                    this.h = host2;
                }
                i2++;
                httpURLConnection.disconnect();
            }
            if (i2 > 5) {
                throw new ProxyCacheException("Too many redirects: " + i2);
            }
        } while (z);
        return httpURLConnection;
    }

    private HttpURLConnection a(long j, int i, String str, String str2) throws IOException, ProxyCacheException {
        HttpURLConnection httpURLConnection;
        String str3 = TextUtils.isEmpty(this.g) ? this.c.a : this.g;
        int i2 = 0;
        boolean z;
        do {
            String str4;
            com.izuiyou.a.a.b.d("Open connection " + (j > 0 ? " with offset " + j : "") + " to " + str3);
            if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || !str3.contains(str)) {
                str4 = str3;
            } else {
                String replace = str3.replace(str, str2);
                this.i = str2;
                str4 = replace;
            }
            httpURLConnection = (HttpURLConnection) new URL(str4).openConnection();
            httpURLConnection.setInstanceFollowRedirects(false);
            a(httpURLConnection, str3);
            if (!(TextUtils.isEmpty(str) || !str3.contains(str) || str4.contains(str))) {
                httpURLConnection.setRequestProperty("Host", str);
            }
            if (j > 0) {
                httpURLConnection.setRequestProperty("Range", "bytes=" + j + "-");
            }
            int c = q.a().c();
            int d = q.a().d();
            if (c <= 3000) {
                c = 0;
            }
            httpURLConnection.setConnectTimeout(c);
            if (d > 5000) {
                c = d;
            } else {
                c = 0;
            }
            httpURLConnection.setReadTimeout(c);
            this.j = httpURLConnection.getResponseCode();
            if (this.j == 301 || this.j == 302 || this.j == 303) {
                z = true;
            } else {
                z = false;
            }
            if (z) {
                str3 = httpURLConnection.getHeaderField("Location");
                this.g = str3;
                i2++;
                httpURLConnection.disconnect();
            }
            if (i2 > 5) {
                throw new ProxyCacheException("Too many redirects: " + i2);
            }
        } while (z);
        return httpURLConnection;
    }

    private void a(HttpURLConnection httpURLConnection, String str) {
        for (Entry entry : this.b.a(str).entrySet()) {
            httpURLConnection.setRequestProperty((String) entry.getKey(), (String) entry.getValue());
        }
    }

    public synchronized String c() throws ProxyCacheException {
        if (TextUtils.isEmpty(this.c.c)) {
            e();
        }
        return this.c.c;
    }

    public String d() {
        return this.c.a;
    }

    public void a(b bVar) {
        this.f = bVar;
    }

    private boolean a(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        String[] split = str.split("\\.");
        if ((split == null || split.length > 1) && !TextUtils.isDigitsOnly(split[0])) {
            return true;
        }
        return false;
    }

    public String toString() {
        return "HttpUrlSource{sourceInfo='" + this.c + "}";
    }
}
