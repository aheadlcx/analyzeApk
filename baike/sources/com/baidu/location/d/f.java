package com.baidu.location.d;

import android.util.Log;
import cz.msebera.android.httpclient.HttpHeaders;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

class f extends Thread {
    final /* synthetic */ e a;

    f(e eVar) {
        this.a = eVar;
    }

    public void run() {
        Throwable th;
        this.a.h = j.c();
        this.a.b();
        this.a.a();
        HttpURLConnection httpURLConnection = null;
        int i = this.a.i;
        while (i > 0) {
            HttpURLConnection httpURLConnection2;
            try {
                httpURLConnection2 = (HttpURLConnection) new URL(this.a.h).openConnection();
                try {
                    httpURLConnection2.setRequestMethod("GET");
                    httpURLConnection2.setDoInput(true);
                    httpURLConnection2.setDoOutput(true);
                    httpURLConnection2.setUseCaches(false);
                    httpURLConnection2.setConnectTimeout(a.b);
                    httpURLConnection2.setReadTimeout(a.b);
                    httpURLConnection2.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");
                    httpURLConnection2.setRequestProperty(HttpHeaders.ACCEPT_CHARSET, "UTF-8");
                    if (httpURLConnection2.getResponseCode() == 200) {
                        InputStream inputStream = httpURLConnection2.getInputStream();
                        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                        byte[] bArr = new byte[1024];
                        while (true) {
                            int read = inputStream.read(bArr);
                            if (read == -1) {
                                break;
                            }
                            byteArrayOutputStream.write(bArr, 0, read);
                        }
                        inputStream.close();
                        byteArrayOutputStream.close();
                        this.a.j = new String(byteArrayOutputStream.toByteArray(), "utf-8");
                        this.a.a(true);
                        httpURLConnection2.disconnect();
                        if (httpURLConnection2 != null) {
                            httpURLConnection2.disconnect();
                        }
                        if (i > 0) {
                            e.o++;
                            this.a.j = null;
                            this.a.a(false);
                            return;
                        }
                        e.o = 0;
                        return;
                    }
                    httpURLConnection2.disconnect();
                    if (httpURLConnection2 != null) {
                        httpURLConnection2.disconnect();
                    }
                    i--;
                    httpURLConnection = httpURLConnection2;
                } catch (Exception e) {
                }
            } catch (Exception e2) {
                httpURLConnection2 = httpURLConnection;
                try {
                    Log.d(a.a, "NetworkCommunicationException!");
                    if (httpURLConnection2 != null) {
                        httpURLConnection2.disconnect();
                    }
                    i--;
                    httpURLConnection = httpURLConnection2;
                } catch (Throwable th2) {
                    Throwable th3 = th2;
                    httpURLConnection = httpURLConnection2;
                    th = th3;
                }
            } catch (Throwable th4) {
                th = th4;
            }
        }
        if (i > 0) {
            e.o = 0;
            return;
        }
        e.o++;
        this.a.j = null;
        this.a.a(false);
        return;
        if (httpURLConnection != null) {
            httpURLConnection.disconnect();
        }
        throw th;
    }
}
