package com.loc;

import android.os.Build.VERSION;
import com.loc.bi.a;
import com.xiaomi.mipush.sdk.Constants;
import cz.msebera.android.httpclient.HttpStatus;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;

public final class bl {
    private static bm a;
    private int b;
    private int c;
    private boolean d;
    private SSLContext e;
    private Proxy f;
    private volatile boolean g;
    private long h;
    private long i;
    private String j;
    private a k;
    private HostnameVerifier l;

    bl(int i, int i2, Proxy proxy) {
        this(i, i2, proxy, false);
    }

    bl(int i, int i2, Proxy proxy, boolean z) {
        this(i, i2, proxy, z, (byte) 0);
    }

    private bl(int i, int i2, Proxy proxy, boolean z, byte b) {
        this.g = false;
        this.h = -1;
        this.i = 0;
        this.l = new do(this);
        this.b = i;
        this.c = i2;
        this.f = proxy;
        this.d = z;
        this.k = null;
        try {
            this.j = UUID.randomUUID().toString().replaceAll(Constants.ACCEPT_TIME_SEPARATOR_SERVER, "").toLowerCase();
        } catch (Throwable th) {
            w.a(th, "HttpUrlUtil", "initCSID");
        }
        if (z) {
            try {
                SSLContext instance = SSLContext.getInstance("TLS");
                instance.init(null, null, null);
                this.e = instance;
            } catch (Throwable th2) {
                w.a(th2, "HttpUtil", "HttpUtil");
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.loc.bo a(java.net.HttpURLConnection r11) throws com.loc.j, java.io.IOException {
        /*
        r10 = this;
        r2 = 0;
        r1 = "";
        r11.connect();	 Catch:{ IOException -> 0x015b, all -> 0x0146 }
        r6 = r11.getHeaderFields();	 Catch:{ IOException -> 0x015b, all -> 0x0146 }
        r3 = r11.getResponseCode();	 Catch:{ IOException -> 0x015b, all -> 0x0146 }
        if (r6 == 0) goto L_0x016b;
    L_0x0010:
        r0 = "gsid";
        r0 = r6.get(r0);	 Catch:{ IOException -> 0x015b, all -> 0x0146 }
        r0 = (java.util.List) r0;	 Catch:{ IOException -> 0x015b, all -> 0x0146 }
        if (r0 == 0) goto L_0x016b;
    L_0x001a:
        r4 = r0.size();	 Catch:{ IOException -> 0x015b, all -> 0x0146 }
        if (r4 <= 0) goto L_0x016b;
    L_0x0020:
        r4 = 0;
        r0 = r0.get(r4);	 Catch:{ IOException -> 0x015b, all -> 0x0146 }
        r0 = (java.lang.String) r0;	 Catch:{ IOException -> 0x015b, all -> 0x0146 }
    L_0x0027:
        r1 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        if (r3 == r1) goto L_0x0089;
    L_0x002b:
        r1 = new com.loc.j;	 Catch:{ IOException -> 0x0064, all -> 0x0146 }
        r4 = new java.lang.StringBuilder;	 Catch:{ IOException -> 0x0064, all -> 0x0146 }
        r5 = "网络异常原因：";
        r4.<init>(r5);	 Catch:{ IOException -> 0x0064, all -> 0x0146 }
        r5 = r11.getResponseMessage();	 Catch:{ IOException -> 0x0064, all -> 0x0146 }
        r4 = r4.append(r5);	 Catch:{ IOException -> 0x0064, all -> 0x0146 }
        r5 = " 网络异常状态码：";
        r4 = r4.append(r5);	 Catch:{ IOException -> 0x0064, all -> 0x0146 }
        r3 = r4.append(r3);	 Catch:{ IOException -> 0x0064, all -> 0x0146 }
        r4 = "  ";
        r3 = r3.append(r4);	 Catch:{ IOException -> 0x0064, all -> 0x0146 }
        r3 = r3.append(r0);	 Catch:{ IOException -> 0x0064, all -> 0x0146 }
        r4 = " ";
        r3 = r3.append(r4);	 Catch:{ IOException -> 0x0064, all -> 0x0146 }
        r4 = r10.j;	 Catch:{ IOException -> 0x0064, all -> 0x0146 }
        r3 = r3.append(r4);	 Catch:{ IOException -> 0x0064, all -> 0x0146 }
        r3 = r3.toString();	 Catch:{ IOException -> 0x0064, all -> 0x0146 }
        r1.<init>(r3, r0);	 Catch:{ IOException -> 0x0064, all -> 0x0146 }
        throw r1;	 Catch:{ IOException -> 0x0064, all -> 0x0146 }
    L_0x0064:
        r1 = move-exception;
        r1 = r2;
        r3 = r2;
        r4 = r2;
    L_0x0068:
        r5 = new com.loc.j;	 Catch:{ all -> 0x0070 }
        r6 = "IO 操作异常 - IOException";
        r5.<init>(r6, r0);	 Catch:{ all -> 0x0070 }
        throw r5;	 Catch:{ all -> 0x0070 }
    L_0x0070:
        r0 = move-exception;
        r9 = r1;
        r1 = r2;
        r2 = r9;
    L_0x0074:
        if (r4 == 0) goto L_0x0079;
    L_0x0076:
        r4.close();	 Catch:{ Throwable -> 0x00fa }
    L_0x0079:
        if (r3 == 0) goto L_0x007e;
    L_0x007b:
        r3.close();	 Catch:{ Throwable -> 0x0104 }
    L_0x007e:
        if (r2 == 0) goto L_0x0083;
    L_0x0080:
        r2.close();	 Catch:{ Throwable -> 0x010e }
    L_0x0083:
        if (r1 == 0) goto L_0x0088;
    L_0x0085:
        r1.close();	 Catch:{ Throwable -> 0x0118 }
    L_0x0088:
        throw r0;
    L_0x0089:
        r4 = new java.io.ByteArrayOutputStream;	 Catch:{ IOException -> 0x0064, all -> 0x0146 }
        r4.<init>();	 Catch:{ IOException -> 0x0064, all -> 0x0146 }
        r3 = r11.getInputStream();	 Catch:{ IOException -> 0x0162, all -> 0x014c }
        r1 = new java.io.PushbackInputStream;	 Catch:{ IOException -> 0x0167, all -> 0x0151 }
        r5 = 2;
        r1.<init>(r3, r5);	 Catch:{ IOException -> 0x0167, all -> 0x0151 }
        r5 = 2;
        r5 = new byte[r5];	 Catch:{ all -> 0x0155 }
        r1.read(r5);	 Catch:{ all -> 0x0155 }
        r1.unread(r5);	 Catch:{ all -> 0x0155 }
        r7 = 0;
        r7 = r5[r7];	 Catch:{ all -> 0x0155 }
        r8 = 31;
        if (r7 != r8) goto L_0x00c7;
    L_0x00a8:
        r7 = 1;
        r5 = r5[r7];	 Catch:{ all -> 0x0155 }
        r7 = -117; // 0xffffffffffffff8b float:NaN double:NaN;
        if (r5 != r7) goto L_0x00c7;
    L_0x00af:
        r5 = new java.util.zip.GZIPInputStream;	 Catch:{ all -> 0x0155 }
        r5.<init>(r1);	 Catch:{ all -> 0x0155 }
        r2 = r5;
    L_0x00b5:
        r5 = 1024; // 0x400 float:1.435E-42 double:5.06E-321;
        r5 = new byte[r5];	 Catch:{ IOException -> 0x00c5 }
    L_0x00b9:
        r7 = r2.read(r5);	 Catch:{ IOException -> 0x00c5 }
        r8 = -1;
        if (r7 == r8) goto L_0x00c9;
    L_0x00c0:
        r8 = 0;
        r4.write(r5, r8, r7);	 Catch:{ IOException -> 0x00c5 }
        goto L_0x00b9;
    L_0x00c5:
        r5 = move-exception;
        goto L_0x0068;
    L_0x00c7:
        r2 = r1;
        goto L_0x00b5;
    L_0x00c9:
        r5 = a;	 Catch:{ IOException -> 0x00c5 }
        if (r5 == 0) goto L_0x00d2;
    L_0x00cd:
        r5 = a;	 Catch:{ IOException -> 0x00c5 }
        r5.a();	 Catch:{ IOException -> 0x00c5 }
    L_0x00d2:
        r5 = new com.loc.bo;	 Catch:{ IOException -> 0x00c5 }
        r5.<init>();	 Catch:{ IOException -> 0x00c5 }
        r7 = r4.toByteArray();	 Catch:{ IOException -> 0x00c5 }
        r5.a = r7;	 Catch:{ IOException -> 0x00c5 }
        r5.b = r6;	 Catch:{ IOException -> 0x00c5 }
        r6 = r10.j;	 Catch:{ IOException -> 0x00c5 }
        r5.c = r6;	 Catch:{ IOException -> 0x00c5 }
        r5.d = r0;	 Catch:{ IOException -> 0x00c5 }
        if (r4 == 0) goto L_0x00ea;
    L_0x00e7:
        r4.close();	 Catch:{ Throwable -> 0x0122 }
    L_0x00ea:
        if (r3 == 0) goto L_0x00ef;
    L_0x00ec:
        r3.close();	 Catch:{ Throwable -> 0x012b }
    L_0x00ef:
        if (r1 == 0) goto L_0x00f4;
    L_0x00f1:
        r1.close();	 Catch:{ Throwable -> 0x0134 }
    L_0x00f4:
        if (r2 == 0) goto L_0x00f9;
    L_0x00f6:
        r2.close();	 Catch:{ Throwable -> 0x013d }
    L_0x00f9:
        return r5;
    L_0x00fa:
        r4 = move-exception;
        r5 = "HttpUrlUtil";
        r6 = "parseResult";
        com.loc.w.a(r4, r5, r6);
        goto L_0x0079;
    L_0x0104:
        r3 = move-exception;
        r4 = "HttpUrlUtil";
        r5 = "parseResult";
        com.loc.w.a(r3, r4, r5);
        goto L_0x007e;
    L_0x010e:
        r2 = move-exception;
        r3 = "HttpUrlUtil";
        r4 = "parseResult";
        com.loc.w.a(r2, r3, r4);
        goto L_0x0083;
    L_0x0118:
        r1 = move-exception;
        r2 = "HttpUrlUtil";
        r3 = "parseResult";
        com.loc.w.a(r1, r2, r3);
        goto L_0x0088;
    L_0x0122:
        r0 = move-exception;
        r4 = "HttpUrlUtil";
        r6 = "parseResult";
        com.loc.w.a(r0, r4, r6);
        goto L_0x00ea;
    L_0x012b:
        r0 = move-exception;
        r3 = "HttpUrlUtil";
        r4 = "parseResult";
        com.loc.w.a(r0, r3, r4);
        goto L_0x00ef;
    L_0x0134:
        r0 = move-exception;
        r1 = "HttpUrlUtil";
        r3 = "parseResult";
        com.loc.w.a(r0, r1, r3);
        goto L_0x00f4;
    L_0x013d:
        r0 = move-exception;
        r1 = "HttpUrlUtil";
        r2 = "parseResult";
        com.loc.w.a(r0, r1, r2);
        goto L_0x00f9;
    L_0x0146:
        r0 = move-exception;
        r1 = r2;
        r3 = r2;
        r4 = r2;
        goto L_0x0074;
    L_0x014c:
        r0 = move-exception;
        r1 = r2;
        r3 = r2;
        goto L_0x0074;
    L_0x0151:
        r0 = move-exception;
        r1 = r2;
        goto L_0x0074;
    L_0x0155:
        r0 = move-exception;
        r9 = r1;
        r1 = r2;
        r2 = r9;
        goto L_0x0074;
    L_0x015b:
        r0 = move-exception;
        r0 = r1;
        r3 = r2;
        r4 = r2;
        r1 = r2;
        goto L_0x0068;
    L_0x0162:
        r1 = move-exception;
        r1 = r2;
        r3 = r2;
        goto L_0x0068;
    L_0x0167:
        r1 = move-exception;
        r1 = r2;
        goto L_0x0068;
    L_0x016b:
        r0 = r1;
        goto L_0x0027;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.bl.a(java.net.HttpURLConnection):com.loc.bo");
    }

    static String a(Map<String, String> map) {
        if (map == null) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (Entry entry : map.entrySet()) {
            String str = (String) entry.getKey();
            String str2 = (String) entry.getValue();
            if (str2 == null) {
                str2 = "";
            }
            if (stringBuilder.length() > 0) {
                stringBuilder.append(com.alipay.sdk.sys.a.b);
            }
            stringBuilder.append(URLEncoder.encode(str));
            stringBuilder.append("=");
            stringBuilder.append(URLEncoder.encode(str2));
        }
        return stringBuilder.toString();
    }

    private HttpURLConnection a(String str, Map<String, String> map, boolean z) throws IOException {
        HttpURLConnection httpURLConnection;
        n.b();
        URLConnection uRLConnection = null;
        URL url = new URL(str);
        if (this.k != null) {
            a aVar = this.k;
            Proxy proxy = this.f;
            uRLConnection = aVar.a();
        }
        if (uRLConnection == null) {
            uRLConnection = this.f != null ? url.openConnection(this.f) : url.openConnection();
        }
        if (this.d) {
            httpURLConnection = (HttpsURLConnection) uRLConnection;
            ((HttpsURLConnection) httpURLConnection).setSSLSocketFactory(this.e.getSocketFactory());
            ((HttpsURLConnection) httpURLConnection).setHostnameVerifier(this.l);
        } else {
            httpURLConnection = (HttpURLConnection) uRLConnection;
        }
        if (VERSION.SDK != null && VERSION.SDK_INT > 13) {
            httpURLConnection.setRequestProperty("Connection", "close");
        }
        a(map, httpURLConnection);
        if (z) {
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setUseCaches(false);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
        } else {
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setDoInput(true);
        }
        return httpURLConnection;
    }

    public static void a(bm bmVar) {
        a = bmVar;
    }

    private void a(Map<String, String> map, HttpURLConnection httpURLConnection) {
        if (map != null) {
            for (String str : map.keySet()) {
                httpURLConnection.addRequestProperty(str, (String) map.get(str));
            }
        }
        try {
            httpURLConnection.addRequestProperty("csid", this.j);
        } catch (Throwable th) {
            w.a(th, "HttpUrlUtil", "addHeaders");
        }
        httpURLConnection.setConnectTimeout(this.b);
        httpURLConnection.setReadTimeout(this.c);
    }

    final bo a(String str, Map<String, String> map, byte[] bArr) throws j {
        HttpURLConnection httpURLConnection = null;
        try {
            httpURLConnection = a(str, (Map) map, true);
            if (bArr != null && bArr.length > 0) {
                DataOutputStream dataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());
                dataOutputStream.write(bArr);
                dataOutputStream.close();
            }
            bo a = a(httpURLConnection);
            if (httpURLConnection != null) {
                try {
                    httpURLConnection.disconnect();
                } catch (Throwable th) {
                    w.a(th, "HttpUrlUtil", "makePostReqeust");
                }
            }
            return a;
        } catch (ConnectException e) {
            e.printStackTrace();
            throw new j("http连接失败 - ConnectionException");
        } catch (MalformedURLException e2) {
            e2.printStackTrace();
            throw new j("url异常 - MalformedURLException");
        } catch (UnknownHostException e3) {
            e3.printStackTrace();
            throw new j("未知主机 - UnKnowHostException");
        } catch (SocketException e4) {
            e4.printStackTrace();
            throw new j("socket 连接异常 - SocketException");
        } catch (SocketTimeoutException e5) {
            e5.printStackTrace();
            throw new j("socket 连接超时 - SocketTimeoutException");
        } catch (InterruptedIOException e6) {
            throw new j("未知的错误");
        } catch (IOException e7) {
            e7.printStackTrace();
            throw new j("IO 操作异常 - IOException");
        } catch (Throwable e8) {
            w.a(e8, "HttpUrlUtil", "makePostReqeust");
            throw e8;
        } catch (Throwable th2) {
            if (httpURLConnection != null) {
                try {
                    httpURLConnection.disconnect();
                } catch (Throwable th3) {
                    w.a(th3, "HttpUrlUtil", "makePostReqeust");
                }
            }
        }
    }

    final void a() {
        this.i = 0;
    }

    final void a(String str, Map<String, String> map, Map<String, String> map2, bk.a aVar) {
        HttpURLConnection a;
        Throwable e;
        HttpURLConnection httpURLConnection;
        InputStream inputStream;
        Throwable th;
        InputStream inputStream2;
        HttpURLConnection httpURLConnection2 = null;
        int i = 1;
        if (aVar != null) {
            try {
                String a2 = a((Map) map2);
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append(str);
                if (a2 != null) {
                    stringBuffer.append("?").append(a2);
                }
                a = a(stringBuffer.toString(), (Map) map, false);
                try {
                    a.setRequestProperty("RANGE", "bytes=" + this.i + Constants.ACCEPT_TIME_SEPARATOR_SERVER);
                    a.connect();
                    int responseCode = a.getResponseCode();
                    int i2 = responseCode != 200 ? 1 : 0;
                    if (responseCode == HttpStatus.SC_PARTIAL_CONTENT) {
                        i = 0;
                    }
                    if ((i & i2) != 0) {
                        j jVar = new j("网络异常原因：" + a.getResponseMessage() + " 网络异常状态码：" + responseCode);
                        aVar.d();
                    }
                    InputStream inputStream3 = a.getInputStream();
                    try {
                        Object obj = new byte[1024];
                        while (!Thread.interrupted() && !this.g) {
                            int read = inputStream3.read(obj, 0, 1024);
                            if (read > 0 && (this.h == -1 || this.i < this.h)) {
                                if (read == 1024) {
                                    aVar.a(obj, this.i);
                                } else {
                                    Object obj2 = new byte[read];
                                    System.arraycopy(obj, 0, obj2, 0, read);
                                    aVar.a(obj2, this.i);
                                }
                                this.i += (long) read;
                            }
                        }
                        if (this.g) {
                            aVar.b();
                        } else {
                            aVar.c();
                        }
                        if (inputStream3 != null) {
                            try {
                                inputStream3.close();
                            } catch (Throwable e2) {
                                w.a(e2, "HttpUrlUtil", "makeDownloadGetRequest");
                            } catch (Throwable e22) {
                                w.a(e22, "HttpUrlUtil", "makeDownloadGetRequest");
                            }
                        }
                        if (a != null) {
                            try {
                                a.disconnect();
                            } catch (Throwable e222) {
                                w.a(e222, "HttpUrlUtil", "makeDownloadGetRequest");
                            }
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        inputStream2 = inputStream3;
                        e222 = th;
                    }
                } catch (Throwable th22) {
                    th = th22;
                    inputStream2 = null;
                    e222 = th;
                    if (inputStream2 != null) {
                        inputStream2.close();
                    }
                    if (a != null) {
                        a.disconnect();
                    }
                    throw e222;
                }
            } catch (Throwable e3) {
                inputStream2 = null;
                e222 = e3;
                a = null;
                if (inputStream2 != null) {
                    inputStream2.close();
                }
                if (a != null) {
                    a.disconnect();
                }
                throw e222;
            }
        }
    }

    final void b() {
        this.h = -1;
    }
}
