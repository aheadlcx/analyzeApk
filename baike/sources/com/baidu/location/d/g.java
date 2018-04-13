package com.baidu.location.d;

import android.util.Log;
import com.alipay.sdk.sys.a;
import cz.msebera.android.httpclient.HttpHeaders;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map.Entry;
import java.util.zip.GZIPInputStream;

class g extends Thread {
    final /* synthetic */ boolean a;
    final /* synthetic */ e b;

    g(e eVar, boolean z) {
        this.b = eVar;
        this.a = z;
    }

    public void run() {
        HttpURLConnection httpURLConnection;
        Throwable th;
        this.b.h = j.c();
        this.b.b();
        this.b.a();
        HttpURLConnection httpURLConnection2 = null;
        int i = this.b.i;
        while (i > 0) {
            try {
                URL url = new URL(this.b.h);
                StringBuffer stringBuffer = new StringBuffer();
                for (Entry entry : this.b.k.entrySet()) {
                    stringBuffer.append((String) entry.getKey());
                    stringBuffer.append("=");
                    stringBuffer.append(entry.getValue());
                    stringBuffer.append(a.b);
                }
                if (stringBuffer.length() > 0) {
                    stringBuffer.deleteCharAt(stringBuffer.length() - 1);
                }
                httpURLConnection = (HttpURLConnection) url.openConnection();
                try {
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoInput(true);
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setUseCaches(false);
                    httpURLConnection.setConnectTimeout(a.b);
                    httpURLConnection.setReadTimeout(a.b);
                    httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");
                    httpURLConnection.setRequestProperty(HttpHeaders.ACCEPT_CHARSET, "UTF-8");
                    httpURLConnection.setRequestProperty(HttpHeaders.ACCEPT_ENCODING, "gzip");
                    httpURLConnection.setRequestProperty("Host", "loc.map.baidu.com");
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    outputStream.write(stringBuffer.toString().getBytes());
                    outputStream.flush();
                    outputStream.close();
                    if (httpURLConnection.getResponseCode() == 200) {
                        InputStream inputStream = httpURLConnection.getInputStream();
                        String contentEncoding = httpURLConnection.getContentEncoding();
                        InputStream gZIPInputStream = (contentEncoding == null || !contentEncoding.contains("gzip")) ? inputStream : new GZIPInputStream(new BufferedInputStream(inputStream));
                        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                        byte[] bArr = new byte[1024];
                        while (true) {
                            int read = gZIPInputStream.read(bArr);
                            if (read == -1) {
                                break;
                            }
                            byteArrayOutputStream.write(bArr, 0, read);
                        }
                        gZIPInputStream.close();
                        byteArrayOutputStream.close();
                        this.b.j = new String(byteArrayOutputStream.toByteArray(), "utf-8");
                        if (this.a) {
                            this.b.m = byteArrayOutputStream.toByteArray();
                        }
                        this.b.a(true);
                        httpURLConnection.disconnect();
                        if (httpURLConnection != null) {
                            httpURLConnection.disconnect();
                        }
                        if (i > 0) {
                            e.o++;
                            this.b.j = null;
                            this.b.a(false);
                            return;
                        }
                        e.o = 0;
                        return;
                    }
                    httpURLConnection.disconnect();
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                    httpURLConnection2 = httpURLConnection;
                    i--;
                } catch (Exception e) {
                    Log.d(a.a, "NetworkCommunicationException!");
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                    httpURLConnection2 = httpURLConnection;
                    i--;
                } catch (Error e2) {
                    try {
                        Log.d(a.a, "NetworkCommunicationError!");
                        if (httpURLConnection != null) {
                            httpURLConnection.disconnect();
                        }
                        httpURLConnection2 = httpURLConnection;
                        i--;
                    } catch (Throwable th2) {
                        httpURLConnection2 = httpURLConnection;
                        th = th2;
                    }
                }
            } catch (Exception e3) {
                httpURLConnection = httpURLConnection2;
                Log.d(a.a, "NetworkCommunicationException!");
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
                httpURLConnection2 = httpURLConnection;
                i--;
            } catch (Error e4) {
                httpURLConnection = httpURLConnection2;
                Log.d(a.a, "NetworkCommunicationError!");
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
                httpURLConnection2 = httpURLConnection;
                i--;
            } catch (Throwable th3) {
                th = th3;
            }
        }
        if (i > 0) {
            e.o = 0;
            return;
        }
        e.o++;
        this.b.j = null;
        this.b.a(false);
        return;
        if (httpURLConnection2 != null) {
            httpURLConnection2.disconnect();
        }
        throw th;
    }
}
