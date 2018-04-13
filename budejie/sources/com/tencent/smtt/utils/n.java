package com.tencent.smtt.utils;

import android.os.Build.VERSION;
import cn.v6.sixrooms.room.gift.BoxingVoteBean;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import java.util.zip.Inflater;
import java.util.zip.InflaterInputStream;
import org.apache.commons.httpclient.HttpState;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.http.entity.mime.MIME;

public class n {

    public interface a {
        void a(int i);
    }

    public static String a(String str, Map<String, String> map, byte[] bArr, a aVar, boolean z) {
        if (bArr == null) {
            return null;
        }
        HttpURLConnection a = a(str, (Map) map);
        if (a == null) {
            return null;
        }
        if (z) {
            a(a, bArr);
        } else {
            b(a, bArr);
        }
        return a(a, aVar, false);
    }

    public static String a(String str, byte[] bArr, a aVar, boolean z) {
        String c;
        byte[] a;
        if (z) {
            try {
                c = q.a().c();
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        c = p.a().b();
        String str2 = str + c;
        if (z) {
            try {
                a = q.a().a(bArr);
            } catch (Exception e2) {
                e2.printStackTrace();
                a = bArr;
            }
        } else {
            a = p.a().a(bArr);
        }
        if (a == null) {
            return null;
        }
        Map hashMap = new HashMap();
        hashMap.put(MIME.CONTENT_TYPE, PostMethod.FORM_URL_ENCODED_CONTENT_TYPE);
        hashMap.put("Content-Length", String.valueOf(a.length));
        HttpURLConnection a2 = a(str2, hashMap);
        if (a2 != null) {
            b(a2, a);
            c = a(a2, aVar, z);
        } else {
            c = null;
        }
        return c;
    }

    private static String a(HttpURLConnection httpURLConnection, a aVar, boolean z) {
        Closeable inflaterInputStream;
        Throwable th;
        Closeable closeable;
        Throwable th2;
        Closeable closeable2 = null;
        try {
            String contentEncoding;
            Closeable byteArrayOutputStream;
            int responseCode = httpURLConnection.getResponseCode();
            if (aVar != null) {
                aVar.a(responseCode);
            }
            if (responseCode == 200) {
                InputStream inputStream = httpURLConnection.getInputStream();
                contentEncoding = httpURLConnection.getContentEncoding();
                if (contentEncoding == null || !contentEncoding.equalsIgnoreCase("gzip")) {
                    if (contentEncoding != null) {
                        if (contentEncoding.equalsIgnoreCase("deflate")) {
                            inflaterInputStream = new InflaterInputStream(inputStream, new Inflater(true));
                        }
                    }
                    Object obj = inputStream;
                } else {
                    inflaterInputStream = new GZIPInputStream(inputStream);
                }
                try {
                    byteArrayOutputStream = new ByteArrayOutputStream();
                } catch (Throwable th3) {
                    th = th3;
                    a(inflaterInputStream);
                    a(closeable2);
                    throw th;
                }
                try {
                    byte[] bArr = new byte[128];
                    while (true) {
                        int read = inflaterInputStream.read(bArr);
                        if (read == -1) {
                            break;
                        }
                        byteArrayOutputStream.write(bArr, 0, read);
                    }
                    if (z) {
                        closeable2 = inflaterInputStream;
                        contentEncoding = new String(byteArrayOutputStream.toByteArray(), "utf-8");
                    } else {
                        closeable2 = inflaterInputStream;
                        contentEncoding = new String(p.a().c(byteArrayOutputStream.toByteArray()));
                    }
                } catch (Throwable th4) {
                    th2 = th4;
                    closeable2 = byteArrayOutputStream;
                    th = th2;
                    a(inflaterInputStream);
                    a(closeable2);
                    throw th;
                }
            }
            byteArrayOutputStream = null;
            contentEncoding = null;
            a(closeable2);
            a(byteArrayOutputStream);
            return contentEncoding;
        } catch (Throwable th5) {
            th = th5;
            inflaterInputStream = null;
            a(inflaterInputStream);
            a(closeable2);
            throw th;
        }
    }

    private static HttpURLConnection a(String str, Map<String, String> map) {
        HttpURLConnection httpURLConnection;
        Exception e;
        try {
            httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            try {
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                httpURLConnection.setUseCaches(false);
                httpURLConnection.setConnectTimeout(20000);
                if (VERSION.SDK_INT > 13) {
                    httpURLConnection.setRequestProperty("Connection", BoxingVoteBean.BOXING_VOTE_STATE_CLOSE);
                } else {
                    httpURLConnection.setRequestProperty("http.keepAlive", HttpState.PREEMPTIVE_DEFAULT);
                }
                for (Entry entry : map.entrySet()) {
                    httpURLConnection.setRequestProperty((String) entry.getKey(), (String) entry.getValue());
                }
            } catch (Exception e2) {
                e = e2;
                e.printStackTrace();
                return httpURLConnection;
            }
        } catch (Exception e3) {
            Exception exception = e3;
            httpURLConnection = null;
            e = exception;
            e.printStackTrace();
            return httpURLConnection;
        }
        return httpURLConnection;
    }

    private static void a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Exception e) {
            }
        }
    }

    private static void a(HttpURLConnection httpURLConnection, byte[] bArr) {
        Closeable gZIPOutputStream;
        Exception e;
        Throwable th;
        try {
            gZIPOutputStream = new GZIPOutputStream(new BufferedOutputStream(httpURLConnection.getOutputStream(), 204800));
            try {
                gZIPOutputStream.write(bArr);
                gZIPOutputStream.flush();
                a(null);
                a(gZIPOutputStream);
            } catch (Exception e2) {
                e = e2;
                try {
                    e.printStackTrace();
                    a(null);
                    a(gZIPOutputStream);
                } catch (Throwable th2) {
                    th = th2;
                    a(null);
                    a(gZIPOutputStream);
                    throw th;
                }
            }
        } catch (Exception e3) {
            e = e3;
            gZIPOutputStream = null;
            e.printStackTrace();
            a(null);
            a(gZIPOutputStream);
        } catch (Throwable th3) {
            th = th3;
            gZIPOutputStream = null;
            a(null);
            a(gZIPOutputStream);
            throw th;
        }
    }

    private static void b(HttpURLConnection httpURLConnection, byte[] bArr) {
        try {
            OutputStream outputStream = httpURLConnection.getOutputStream();
            outputStream.write(bArr);
            outputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
