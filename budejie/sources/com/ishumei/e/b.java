package com.ishumei.e;

import com.ishumei.f.e;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.security.KeyStore;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Map;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class b {
    private static String a = "POST";
    private static b j = null;
    private int b;
    private ArrayList<String> c = new ArrayList();
    private int d;
    private int e = 3;
    private int f = 2;
    private int g;
    private long h;
    private SSLContext i = null;
    private TrustManager[] k = null;
    private KeyStore l = null;

    public static abstract class b<T> extends com.ishumei.c.b<T> {
        public c b = null;

        public b(boolean z, int i) {
            super(z, i);
        }

        public abstract void a(String str);

        public boolean a(String str, int i) {
            if (!this.b.g || this.b.d + 1 >= this.b.h) {
                return true;
            }
            c cVar = this.b;
            cVar.d++;
            e.a(this.b.a);
            this.b.a = null;
            this.b.f.a();
            return false;
        }

        public void run() {
            Closeable bufferedReader;
            Object e;
            Throwable th;
            Closeable closeable = null;
            if (this.b.i != null) {
                a(this.b.i, 1);
                this.b.i = null;
            } else if (this.b.a == null) {
                a("HttpUrlConnection is null", 0);
            } else {
                try {
                    int responseCode = this.b.a.getResponseCode();
                    if (responseCode != 200) {
                        com.ishumei.f.c.a("HttpTransport", "HttpTransport responseCode ( " + responseCode + ")");
                        a("responseCode: " + responseCode, 2);
                        return;
                    }
                    try {
                        Closeable inputStream = this.b.a.getInputStream();
                        try {
                            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                        } catch (Exception e2) {
                            e = e2;
                            bufferedReader = null;
                            closeable = inputStream;
                            try {
                                com.ishumei.f.c.a("HttpTransport", "HttpTransport response content err: " + e);
                                a("response content err: " + e, 3);
                                e.a(closeable);
                                e.a(bufferedReader);
                                e.a(this.b.a);
                            } catch (Throwable th2) {
                                th = th2;
                                e.a(closeable);
                                e.a(bufferedReader);
                                e.a(this.b.a);
                                throw th;
                            }
                        } catch (Throwable th3) {
                            th = th3;
                            bufferedReader = null;
                            closeable = inputStream;
                            e.a(closeable);
                            e.a(bufferedReader);
                            e.a(this.b.a);
                            throw th;
                        }
                        try {
                            StringBuilder stringBuilder = new StringBuilder();
                            while (true) {
                                String readLine = bufferedReader.readLine();
                                if (readLine != null) {
                                    stringBuilder.append(readLine);
                                } else {
                                    a(stringBuilder.toString());
                                    e.a(inputStream);
                                    e.a(bufferedReader);
                                    e.a(this.b.a);
                                    return;
                                }
                            }
                        } catch (Exception e3) {
                            e = e3;
                            closeable = inputStream;
                            com.ishumei.f.c.a("HttpTransport", "HttpTransport response content err: " + e);
                            a("response content err: " + e, 3);
                            e.a(closeable);
                            e.a(bufferedReader);
                            e.a(this.b.a);
                        } catch (Throwable th4) {
                            th = th4;
                            closeable = inputStream;
                            e.a(closeable);
                            e.a(bufferedReader);
                            e.a(this.b.a);
                            throw th;
                        }
                    } catch (Exception e4) {
                        e = e4;
                        bufferedReader = null;
                        com.ishumei.f.c.a("HttpTransport", "HttpTransport response content err: " + e);
                        a("response content err: " + e, 3);
                        e.a(closeable);
                        e.a(bufferedReader);
                        e.a(this.b.a);
                    } catch (Throwable th5) {
                        th = th5;
                        bufferedReader = null;
                        e.a(closeable);
                        e.a(bufferedReader);
                        e.a(this.b.a);
                        throw th;
                    }
                } catch (Exception e5) {
                    e.a(this.b.a);
                    com.ishumei.f.c.a("HttpTransport", "HttpTransport getResponseCode failed: " + e5);
                    a(e5.getMessage(), 2);
                }
            }
        }
    }

    public static class a implements HostnameVerifier {
        public boolean verify(String str, SSLSession sSLSession) {
            return true;
        }
    }

    public static class c {
        public HttpURLConnection a = null;
        public byte[] b = null;
        public Map<String, String> c = null;
        public int d = -1;
        public b e = null;
        public com.ishumei.c.b<c> f = null;
        public boolean g = false;
        public int h = 0;
        public String i = null;
    }

    public b a(a aVar) {
        if (aVar == null) {
            return null;
        }
        int i;
        this.b = aVar.d();
        for (i = 0; i < this.e; i++) {
            this.c.add(aVar.e());
        }
        for (Object add : aVar.f()) {
            for (i = 0; i < this.f; i++) {
                this.c.add(add);
            }
        }
        this.d = aVar.b() * 1000;
        this.g = aVar.c() * 1000;
        this.h = (long) (aVar.g() * 1000);
        if (1 == this.b) {
            return this;
        }
        this.k = new TrustManager[]{new X509TrustManager(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) {
            }

            public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) {
            }

            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }
        }};
        try {
            this.i = SSLContext.getInstance("SSL");
            this.i.init(null, this.k, null);
            return this;
        } catch (Exception e) {
            return null;
        }
    }

    public void a(byte[] bArr, Map<String, String> map, b bVar) {
        if (bVar != null) {
            try {
                if (bVar.b == null) {
                    bVar.b = new c();
                }
                bVar.b.d = 0;
                bVar.b.b = bArr;
                bVar.b.c = map;
                bVar.b.g = true;
                bVar.b.e = bVar;
                bVar.b.h = this.c.size();
                bVar.b.f = new com.ishumei.c.b<c>(this, true, com.ishumei.c.a.b().a(), true, this.h, false) {
                    final /* synthetic */ b a;

                    public void run() {
                        c cVar = (c) this.h;
                        if (cVar == null) {
                            try {
                                throw new Exception("sessionCache is null");
                            } catch (Exception e) {
                                com.ishumei.f.c.d("HttpTransport", "transportWithRetry asyn failed: url: " + ((String) this.a.c.get(cVar.d)) + " " + e);
                            }
                        } else if (cVar.d < this.a.c.size()) {
                            this.a.a(cVar.b, cVar.c, (String) this.a.c.get(cVar.d), cVar.e);
                        }
                    }
                };
                bVar.b.f.a(bVar.b);
            } catch (Exception e) {
                com.ishumei.f.c.d("HttpTransport", "transportWithRetry asyn failed: url: + " + ((String) this.c.get(bVar.b.d)) + " " + e);
                return;
            }
        }
        a(bArr, map, (String) this.c.get(0), bVar);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(byte[] r8, java.util.Map<java.lang.String, java.lang.String> r9, java.lang.String r10, com.ishumei.e.b.b<?> r11) {
        /*
        r7 = this;
        r3 = 0;
        r0 = "HttpTransport";
        r1 = new java.lang.StringBuilder;
        r2 = "HttpTransport transport: ";
        r1.<init>(r2);
        r1 = r1.append(r8);
        r2 = " ";
        r1 = r1.append(r2);
        r1 = r1.append(r10);
        r2 = " ";
        r1 = r1.append(r2);
        r1 = r1.append(r11);
        r1 = r1.toString();
        com.ishumei.f.c.a(r0, r1);
        if (r8 == 0) goto L_0x002e;
    L_0x002b:
        r0 = r8.length;
        if (r0 != 0) goto L_0x0036;
    L_0x002e:
        r0 = new java.io.IOException;
        r1 = "data is null";
        r0.<init>(r1);
        throw r0;
    L_0x0036:
        if (r11 == 0) goto L_0x0043;
    L_0x0038:
        r0 = r11.b;
        if (r0 != 0) goto L_0x0043;
    L_0x003c:
        r0 = new com.ishumei.e.b$c;
        r0.<init>();
        r11.b = r0;
    L_0x0043:
        r0 = new java.net.URL;	 Catch:{ Exception -> 0x0133, all -> 0x0128 }
        r0.<init>(r10);	 Catch:{ Exception -> 0x0133, all -> 0x0128 }
        r1 = r7.b;	 Catch:{ Exception -> 0x0133, all -> 0x0128 }
        if (r1 != 0) goto L_0x0061;
    L_0x004c:
        r1 = r7.k;	 Catch:{ Exception -> 0x0133, all -> 0x0128 }
        if (r1 == 0) goto L_0x0061;
    L_0x0050:
        r1 = new com.ishumei.e.b$a;	 Catch:{ Exception -> 0x0133, all -> 0x0128 }
        r1.<init>();	 Catch:{ Exception -> 0x0133, all -> 0x0128 }
        javax.net.ssl.HttpsURLConnection.setDefaultHostnameVerifier(r1);	 Catch:{ Exception -> 0x0133, all -> 0x0128 }
        r1 = r7.i;	 Catch:{ Exception -> 0x0133, all -> 0x0128 }
        r1 = r1.getSocketFactory();	 Catch:{ Exception -> 0x0133, all -> 0x0128 }
        javax.net.ssl.HttpsURLConnection.setDefaultSSLSocketFactory(r1);	 Catch:{ Exception -> 0x0133, all -> 0x0128 }
    L_0x0061:
        r0 = r0.openConnection();	 Catch:{ Exception -> 0x0133, all -> 0x0128 }
        r0 = (java.net.HttpURLConnection) r0;	 Catch:{ Exception -> 0x0133, all -> 0x0128 }
        r1 = 1;
        r0.setDoInput(r1);	 Catch:{ Exception -> 0x00be, all -> 0x0128 }
        r1 = 1;
        r0.setDoOutput(r1);	 Catch:{ Exception -> 0x00be, all -> 0x0128 }
        r1 = 0;
        r0.setUseCaches(r1);	 Catch:{ Exception -> 0x00be, all -> 0x0128 }
        r1 = 1;
        r0.setInstanceFollowRedirects(r1);	 Catch:{ Exception -> 0x00be, all -> 0x0128 }
        r1 = a;	 Catch:{ Exception -> 0x00be, all -> 0x0128 }
        r0.setRequestMethod(r1);	 Catch:{ Exception -> 0x00be, all -> 0x0128 }
        r1 = "Content-Type";
        r2 = "application/octet-stream";
        r0.setRequestProperty(r1, r2);	 Catch:{ Exception -> 0x00be, all -> 0x0128 }
        r1 = "Connection";
        r2 = "Close";
        r0.setRequestProperty(r1, r2);	 Catch:{ Exception -> 0x00be, all -> 0x0128 }
        r1 = r7.d;	 Catch:{ Exception -> 0x00be, all -> 0x0128 }
        r0.setConnectTimeout(r1);	 Catch:{ Exception -> 0x00be, all -> 0x0128 }
        r1 = r7.g;	 Catch:{ Exception -> 0x00be, all -> 0x0128 }
        r0.setReadTimeout(r1);	 Catch:{ Exception -> 0x00be, all -> 0x0128 }
        r1 = r8.length;	 Catch:{ Exception -> 0x00be, all -> 0x0128 }
        r0.setFixedLengthStreamingMode(r1);	 Catch:{ Exception -> 0x00be, all -> 0x0128 }
        if (r9 == 0) goto L_0x00f9;
    L_0x009a:
        r1 = r9.entrySet();	 Catch:{ Exception -> 0x00be, all -> 0x0128 }
        r4 = r1.iterator();	 Catch:{ Exception -> 0x00be, all -> 0x0128 }
    L_0x00a2:
        r1 = r4.hasNext();	 Catch:{ Exception -> 0x00be, all -> 0x0128 }
        if (r1 == 0) goto L_0x00f9;
    L_0x00a8:
        r1 = r4.next();	 Catch:{ Exception -> 0x00be, all -> 0x0128 }
        r1 = (java.util.Map.Entry) r1;	 Catch:{ Exception -> 0x00be, all -> 0x0128 }
        r2 = r1.getKey();	 Catch:{ Exception -> 0x00be, all -> 0x0128 }
        r2 = (java.lang.String) r2;	 Catch:{ Exception -> 0x00be, all -> 0x0128 }
        r1 = r1.getValue();	 Catch:{ Exception -> 0x00be, all -> 0x0128 }
        r1 = (java.lang.String) r1;	 Catch:{ Exception -> 0x00be, all -> 0x0128 }
        r0.setRequestProperty(r2, r1);	 Catch:{ Exception -> 0x00be, all -> 0x0128 }
        goto L_0x00a2;
    L_0x00be:
        r1 = move-exception;
        r2 = r0;
        r0 = r1;
        r1 = r3;
    L_0x00c2:
        com.ishumei.f.e.a(r2);	 Catch:{ all -> 0x0130 }
        r2 = "HttpTransport";
        r4 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0130 }
        r5 = "HttpTransport request failed: ";
        r4.<init>(r5);	 Catch:{ all -> 0x0130 }
        r4 = r4.append(r0);	 Catch:{ all -> 0x0130 }
        r4 = r4.toString();	 Catch:{ all -> 0x0130 }
        com.ishumei.f.c.d(r2, r4);	 Catch:{ all -> 0x0130 }
        if (r11 == 0) goto L_0x00e3;
    L_0x00db:
        r2 = r11.b;	 Catch:{ all -> 0x0130 }
        r0 = r0.getMessage();	 Catch:{ all -> 0x0130 }
        r2.i = r0;	 Catch:{ all -> 0x0130 }
    L_0x00e3:
        com.ishumei.f.e.a(r1);
        r0 = r3;
    L_0x00e7:
        if (r11 == 0) goto L_0x00f8;
    L_0x00e9:
        r1 = r11.b;
        r1.a = r0;
        r0 = r11.b;
        r0.b = r8;
        r0 = r11.b;
        r0.c = r9;
        r11.a();
    L_0x00f8:
        return;
    L_0x00f9:
        r0.connect();	 Catch:{ Exception -> 0x00be, all -> 0x0128 }
        r2 = r0.getOutputStream();	 Catch:{ Exception -> 0x00be, all -> 0x0128 }
        r2.write(r8);	 Catch:{ Exception -> 0x0137, all -> 0x012d }
        r2.flush();	 Catch:{ Exception -> 0x0137, all -> 0x012d }
        r1 = "HttpTransport";
        r4 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0137, all -> 0x012d }
        r5 = "HttpTransport finish: ";
        r4.<init>(r5);	 Catch:{ Exception -> 0x0137, all -> 0x012d }
        r4 = r4.append(r10);	 Catch:{ Exception -> 0x0137, all -> 0x012d }
        r5 = " ";
        r4 = r4.append(r5);	 Catch:{ Exception -> 0x0137, all -> 0x012d }
        r4 = r4.append(r8);	 Catch:{ Exception -> 0x0137, all -> 0x012d }
        r4 = r4.toString();	 Catch:{ Exception -> 0x0137, all -> 0x012d }
        com.ishumei.f.c.a(r1, r4);	 Catch:{ Exception -> 0x0137, all -> 0x012d }
        com.ishumei.f.e.a(r2);
        goto L_0x00e7;
    L_0x0128:
        r0 = move-exception;
    L_0x0129:
        com.ishumei.f.e.a(r3);
        throw r0;
    L_0x012d:
        r0 = move-exception;
        r3 = r2;
        goto L_0x0129;
    L_0x0130:
        r0 = move-exception;
        r3 = r1;
        goto L_0x0129;
    L_0x0133:
        r0 = move-exception;
        r1 = r3;
        r2 = r3;
        goto L_0x00c2;
    L_0x0137:
        r1 = move-exception;
        r6 = r1;
        r1 = r2;
        r2 = r0;
        r0 = r6;
        goto L_0x00c2;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ishumei.e.b.a(byte[], java.util.Map, java.lang.String, com.ishumei.e.b$b):void");
    }
}
