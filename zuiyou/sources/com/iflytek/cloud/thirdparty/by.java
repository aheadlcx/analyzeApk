package com.iflytek.cloud.thirdparty;

import android.text.TextUtils;
import com.iflytek.cloud.SpeechError;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import org.apache.http.util.ByteArrayBuffer;

public class by extends Thread {
    private int a = 20000;
    private a b = null;
    private volatile boolean c = false;
    private URL d = null;
    private ArrayList<byte[]> e = new ArrayList();
    private String f;
    private Object g = null;
    private int h = 0;

    public interface a {
        void a(SpeechError speechError);

        void a(by byVar, byte[] bArr);
    }

    public static URL a(String str, String str2) throws MalformedURLException {
        if (!(TextUtils.isEmpty(str) || TextUtils.isEmpty(str2))) {
            if (!str.endsWith("?")) {
                str = str + "?";
            }
            str = str + str2;
        }
        return new URL(str);
    }

    private void a(SpeechError speechError) {
        if (this.b != null && !this.c) {
            this.b.a(speechError);
        }
    }

    private byte[] a(InputStream inputStream) throws IOException {
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
        ByteArrayBuffer byteArrayBuffer = new ByteArrayBuffer(1024);
        byte[] bArr = new byte[1024];
        while (!this.c) {
            int read = bufferedInputStream.read(bArr, 0, 1024);
            if (read == -1) {
                break;
            }
            byteArrayBuffer.append(bArr, 0, read);
        }
        return byteArrayBuffer.toByteArray();
    }

    private void b(byte[] bArr) {
        if (this.b != null && !this.c) {
            this.b.a(this, bArr);
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void c() {
        /*
        r6 = this;
        r2 = 0;
        r0 = "Start connect server";
        com.iflytek.cloud.thirdparty.cb.a(r0);	 Catch:{ Exception -> 0x00cd, all -> 0x00b4 }
        r0 = r6.d;	 Catch:{ Exception -> 0x00cd, all -> 0x00b4 }
        r0 = r0.openConnection();	 Catch:{ Exception -> 0x00cd, all -> 0x00b4 }
        r0 = (java.net.HttpURLConnection) r0;	 Catch:{ Exception -> 0x00cd, all -> 0x00b4 }
        r1 = r6.a;	 Catch:{ Exception -> 0x007a, all -> 0x00c3 }
        r0.setConnectTimeout(r1);	 Catch:{ Exception -> 0x007a, all -> 0x00c3 }
        r1 = r6.a;	 Catch:{ Exception -> 0x007a, all -> 0x00c3 }
        r0.setReadTimeout(r1);	 Catch:{ Exception -> 0x007a, all -> 0x00c3 }
        r1 = "GET";
        r0.setRequestMethod(r1);	 Catch:{ Exception -> 0x007a, all -> 0x00c3 }
        r1 = r0.getResponseCode();	 Catch:{ Exception -> 0x007a, all -> 0x00c3 }
        r3 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x007a, all -> 0x00c3 }
        r3.<init>();	 Catch:{ Exception -> 0x007a, all -> 0x00c3 }
        r4 = "responseCode = ";
        r3 = r3.append(r4);	 Catch:{ Exception -> 0x007a, all -> 0x00c3 }
        r3 = r3.append(r1);	 Catch:{ Exception -> 0x007a, all -> 0x00c3 }
        r3 = r3.toString();	 Catch:{ Exception -> 0x007a, all -> 0x00c3 }
        com.iflytek.cloud.thirdparty.cb.a(r3);	 Catch:{ Exception -> 0x007a, all -> 0x00c3 }
        r3 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        if (r3 != r1) goto L_0x0054;
    L_0x003e:
        r2 = r0.getInputStream();	 Catch:{ Exception -> 0x007a, all -> 0x00c3 }
        r1 = r6.a(r2);	 Catch:{ Exception -> 0x00d0, all -> 0x00c3 }
        r6.b(r1);	 Catch:{ Exception -> 0x00d0, all -> 0x00c3 }
    L_0x0049:
        if (r2 == 0) goto L_0x004e;
    L_0x004b:
        r2.close();	 Catch:{ Exception -> 0x00d6 }
    L_0x004e:
        if (r0 == 0) goto L_0x0053;
    L_0x0050:
        r0.disconnect();	 Catch:{ Exception -> 0x00d6 }
    L_0x0053:
        return;
    L_0x0054:
        r3 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x007a, all -> 0x00c3 }
        r3.<init>();	 Catch:{ Exception -> 0x007a, all -> 0x00c3 }
        r4 = "MscHttpRequest connect error:";
        r3 = r3.append(r4);	 Catch:{ Exception -> 0x007a, all -> 0x00c3 }
        r3 = r3.append(r1);	 Catch:{ Exception -> 0x007a, all -> 0x00c3 }
        r3 = r3.toString();	 Catch:{ Exception -> 0x007a, all -> 0x00c3 }
        com.iflytek.cloud.thirdparty.cb.a(r3);	 Catch:{ Exception -> 0x007a, all -> 0x00c3 }
        r3 = new com.iflytek.cloud.SpeechError;	 Catch:{ Exception -> 0x007a, all -> 0x00c3 }
        r1 = java.lang.Math.abs(r1);	 Catch:{ Exception -> 0x007a, all -> 0x00c3 }
        r1 = r1 + 12000;
        r3.<init>(r1);	 Catch:{ Exception -> 0x007a, all -> 0x00c3 }
        r6.a(r3);	 Catch:{ Exception -> 0x007a, all -> 0x00c3 }
        goto L_0x0049;
    L_0x007a:
        r1 = move-exception;
        r5 = r1;
        r1 = r2;
        r2 = r0;
        r0 = r5;
    L_0x007f:
        com.iflytek.cloud.thirdparty.cb.a(r0);	 Catch:{ all -> 0x00c8 }
        r3 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00c8 }
        r3.<init>();	 Catch:{ all -> 0x00c8 }
        r4 = "MscHttpRequest error:";
        r3 = r3.append(r4);	 Catch:{ all -> 0x00c8 }
        r0 = r0.toString();	 Catch:{ all -> 0x00c8 }
        r0 = r3.append(r0);	 Catch:{ all -> 0x00c8 }
        r0 = r0.toString();	 Catch:{ all -> 0x00c8 }
        com.iflytek.cloud.thirdparty.cb.a(r0);	 Catch:{ all -> 0x00c8 }
        r0 = new com.iflytek.cloud.SpeechError;	 Catch:{ all -> 0x00c8 }
        r3 = 20003; // 0x4e23 float:2.803E-41 double:9.883E-320;
        r0.<init>(r3);	 Catch:{ all -> 0x00c8 }
        r6.a(r0);	 Catch:{ all -> 0x00c8 }
        if (r1 == 0) goto L_0x00ac;
    L_0x00a9:
        r1.close();	 Catch:{ Exception -> 0x00b2 }
    L_0x00ac:
        if (r2 == 0) goto L_0x0053;
    L_0x00ae:
        r2.disconnect();	 Catch:{ Exception -> 0x00b2 }
        goto L_0x0053;
    L_0x00b2:
        r0 = move-exception;
        goto L_0x0053;
    L_0x00b4:
        r0 = move-exception;
        r1 = r2;
    L_0x00b6:
        if (r2 == 0) goto L_0x00bb;
    L_0x00b8:
        r2.close();	 Catch:{ Exception -> 0x00c1 }
    L_0x00bb:
        if (r1 == 0) goto L_0x00c0;
    L_0x00bd:
        r1.disconnect();	 Catch:{ Exception -> 0x00c1 }
    L_0x00c0:
        throw r0;
    L_0x00c1:
        r1 = move-exception;
        goto L_0x00c0;
    L_0x00c3:
        r1 = move-exception;
        r5 = r1;
        r1 = r0;
        r0 = r5;
        goto L_0x00b6;
    L_0x00c8:
        r0 = move-exception;
        r5 = r1;
        r1 = r2;
        r2 = r5;
        goto L_0x00b6;
    L_0x00cd:
        r0 = move-exception;
        r1 = r2;
        goto L_0x007f;
    L_0x00d0:
        r1 = move-exception;
        r5 = r1;
        r1 = r2;
        r2 = r0;
        r0 = r5;
        goto L_0x007f;
    L_0x00d6:
        r0 = move-exception;
        goto L_0x0053;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.iflytek.cloud.thirdparty.by.c():void");
    }

    private int d() {
        int i = 0;
        for (int i2 = 0; i2 < this.e.size(); i2++) {
            i += ((byte[]) this.e.get(i2)).length;
        }
        return i;
    }

    public void a() {
        this.c = true;
    }

    public void a(int i) {
        this.h = i;
    }

    public void a(a aVar) {
        this.b = aVar;
        start();
    }

    public void a(String str, String str2, byte[] bArr) {
        this.e.clear();
        a(bArr);
        try {
            this.d = a(str, str2);
        } catch (Throwable e) {
            cb.a(e);
        }
    }

    public void a(String str, String str2, byte[] bArr, String str3) {
        this.f = str3;
        a(str, str2, bArr);
    }

    public void a(byte[] bArr) {
        if (bArr != null) {
            this.e.add(bArr);
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void b() {
        /*
        r6 = this;
        r2 = 0;
        r0 = "MscHttpRequest start Post";
        com.iflytek.cloud.thirdparty.cb.a(r0);	 Catch:{ Exception -> 0x012f, all -> 0x0125 }
        r0 = r6.d;	 Catch:{ Exception -> 0x012f, all -> 0x0125 }
        r0 = r0.openConnection();	 Catch:{ Exception -> 0x012f, all -> 0x0125 }
        r0 = (java.net.HttpURLConnection) r0;	 Catch:{ Exception -> 0x012f, all -> 0x0125 }
        r1 = 1;
        r0.setDoOutput(r1);	 Catch:{ Exception -> 0x0097, all -> 0x0114 }
        r1 = 1;
        r0.setDoInput(r1);	 Catch:{ Exception -> 0x0097, all -> 0x0114 }
        r1 = 0;
        r0.setUseCaches(r1);	 Catch:{ Exception -> 0x0097, all -> 0x0114 }
        r1 = "POST";
        r0.setRequestMethod(r1);	 Catch:{ Exception -> 0x0097, all -> 0x0114 }
        r1 = r6.a;	 Catch:{ Exception -> 0x0097, all -> 0x0114 }
        r0.setConnectTimeout(r1);	 Catch:{ Exception -> 0x0097, all -> 0x0114 }
        r1 = r6.a;	 Catch:{ Exception -> 0x0097, all -> 0x0114 }
        r0.setReadTimeout(r1);	 Catch:{ Exception -> 0x0097, all -> 0x0114 }
        r1 = "Content-length";
        r3 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0097, all -> 0x0114 }
        r3.<init>();	 Catch:{ Exception -> 0x0097, all -> 0x0114 }
        r4 = "";
        r3 = r3.append(r4);	 Catch:{ Exception -> 0x0097, all -> 0x0114 }
        r4 = r6.d();	 Catch:{ Exception -> 0x0097, all -> 0x0114 }
        r3 = r3.append(r4);	 Catch:{ Exception -> 0x0097, all -> 0x0114 }
        r3 = r3.toString();	 Catch:{ Exception -> 0x0097, all -> 0x0114 }
        r0.setRequestProperty(r1, r3);	 Catch:{ Exception -> 0x0097, all -> 0x0114 }
        r1 = "Connection";
        r3 = "Keep-Alive";
        r0.setRequestProperty(r1, r3);	 Catch:{ Exception -> 0x0097, all -> 0x0114 }
        r1 = "Charset";
        r3 = "utf-8";
        r0.setRequestProperty(r1, r3);	 Catch:{ Exception -> 0x0097, all -> 0x0114 }
        r1 = "Content-Type";
        r3 = "application/x-gzip";
        r0.setRequestProperty(r1, r3);	 Catch:{ Exception -> 0x0097, all -> 0x0114 }
        r1 = r6.f;	 Catch:{ Exception -> 0x0097, all -> 0x0114 }
        r1 = android.text.TextUtils.isEmpty(r1);	 Catch:{ Exception -> 0x0097, all -> 0x0114 }
        if (r1 != 0) goto L_0x007d;
    L_0x006c:
        r1 = r6.f;	 Catch:{ Exception -> 0x0097, all -> 0x0114 }
        r1 = r1.getBytes();	 Catch:{ Exception -> 0x0097, all -> 0x0114 }
        r3 = 2;
        r1 = android.util.Base64.encodeToString(r1, r3);	 Catch:{ Exception -> 0x0097, all -> 0x0114 }
        r3 = "X-Par";
        r0.setRequestProperty(r3, r1);	 Catch:{ Exception -> 0x0097, all -> 0x0114 }
    L_0x007d:
        r3 = r0.getOutputStream();	 Catch:{ Exception -> 0x0097, all -> 0x0114 }
        r1 = r6.e;	 Catch:{ Exception -> 0x0097, all -> 0x0114 }
        r4 = r1.iterator();	 Catch:{ Exception -> 0x0097, all -> 0x0114 }
    L_0x0087:
        r1 = r4.hasNext();	 Catch:{ Exception -> 0x0097, all -> 0x0114 }
        if (r1 == 0) goto L_0x00cc;
    L_0x008d:
        r1 = r4.next();	 Catch:{ Exception -> 0x0097, all -> 0x0114 }
        r1 = (byte[]) r1;	 Catch:{ Exception -> 0x0097, all -> 0x0114 }
        r3.write(r1);	 Catch:{ Exception -> 0x0097, all -> 0x0114 }
        goto L_0x0087;
    L_0x0097:
        r1 = move-exception;
        r5 = r1;
        r1 = r2;
        r2 = r0;
        r0 = r5;
    L_0x009c:
        r3 = new com.iflytek.cloud.SpeechError;	 Catch:{ all -> 0x0128 }
        r4 = 20003; // 0x4e23 float:2.803E-41 double:9.883E-320;
        r3.<init>(r4);	 Catch:{ all -> 0x0128 }
        r6.a(r3);	 Catch:{ all -> 0x0128 }
        r3 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0128 }
        r3.<init>();	 Catch:{ all -> 0x0128 }
        r4 = "MscHttpRequest error:";
        r3 = r3.append(r4);	 Catch:{ all -> 0x0128 }
        r0 = r0.toString();	 Catch:{ all -> 0x0128 }
        r0 = r3.append(r0);	 Catch:{ all -> 0x0128 }
        r0 = r0.toString();	 Catch:{ all -> 0x0128 }
        com.iflytek.cloud.thirdparty.cb.a(r0);	 Catch:{ all -> 0x0128 }
        if (r1 == 0) goto L_0x00c6;
    L_0x00c3:
        r1.close();	 Catch:{ Exception -> 0x012d }
    L_0x00c6:
        if (r2 == 0) goto L_0x00cb;
    L_0x00c8:
        r2.disconnect();	 Catch:{ Exception -> 0x012d }
    L_0x00cb:
        return;
    L_0x00cc:
        r3.flush();	 Catch:{ Exception -> 0x0097, all -> 0x0114 }
        r3.close();	 Catch:{ Exception -> 0x0097, all -> 0x0114 }
        r1 = r0.getResponseCode();	 Catch:{ Exception -> 0x0097, all -> 0x0114 }
        r3 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        if (r3 != r1) goto L_0x00f2;
    L_0x00da:
        r2 = r0.getInputStream();	 Catch:{ Exception -> 0x0097, all -> 0x0114 }
        r1 = r6.a(r2);	 Catch:{ Exception -> 0x0133, all -> 0x0114 }
        r6.b(r1);	 Catch:{ Exception -> 0x0133, all -> 0x0114 }
    L_0x00e5:
        if (r2 == 0) goto L_0x00ea;
    L_0x00e7:
        r2.close();	 Catch:{ Exception -> 0x00f0 }
    L_0x00ea:
        if (r0 == 0) goto L_0x00cb;
    L_0x00ec:
        r0.disconnect();	 Catch:{ Exception -> 0x00f0 }
        goto L_0x00cb;
    L_0x00f0:
        r0 = move-exception;
        goto L_0x00cb;
    L_0x00f2:
        r3 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0097, all -> 0x0114 }
        r3.<init>();	 Catch:{ Exception -> 0x0097, all -> 0x0114 }
        r4 = "Http Request Failed.";
        r3 = r3.append(r4);	 Catch:{ Exception -> 0x0097, all -> 0x0114 }
        r3 = r3.append(r1);	 Catch:{ Exception -> 0x0097, all -> 0x0114 }
        r3 = r3.toString();	 Catch:{ Exception -> 0x0097, all -> 0x0114 }
        com.iflytek.cloud.thirdparty.cb.a(r3);	 Catch:{ Exception -> 0x0097, all -> 0x0114 }
        r3 = new com.iflytek.cloud.SpeechError;	 Catch:{ Exception -> 0x0097, all -> 0x0114 }
        r1 = r1 + 12000;
        r3.<init>(r1);	 Catch:{ Exception -> 0x0097, all -> 0x0114 }
        r6.a(r3);	 Catch:{ Exception -> 0x0097, all -> 0x0114 }
        goto L_0x00e5;
    L_0x0114:
        r1 = move-exception;
        r5 = r1;
        r1 = r0;
        r0 = r5;
    L_0x0118:
        if (r2 == 0) goto L_0x011d;
    L_0x011a:
        r2.close();	 Catch:{ Exception -> 0x0123 }
    L_0x011d:
        if (r1 == 0) goto L_0x0122;
    L_0x011f:
        r1.disconnect();	 Catch:{ Exception -> 0x0123 }
    L_0x0122:
        throw r0;
    L_0x0123:
        r1 = move-exception;
        goto L_0x0122;
    L_0x0125:
        r0 = move-exception;
        r1 = r2;
        goto L_0x0118;
    L_0x0128:
        r0 = move-exception;
        r5 = r1;
        r1 = r2;
        r2 = r5;
        goto L_0x0118;
    L_0x012d:
        r0 = move-exception;
        goto L_0x00cb;
    L_0x012f:
        r0 = move-exception;
        r1 = r2;
        goto L_0x009c;
    L_0x0133:
        r1 = move-exception;
        r5 = r1;
        r1 = r2;
        r2 = r0;
        r0 = r5;
        goto L_0x009c;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.iflytek.cloud.thirdparty.by.b():void");
    }

    public void b(int i) {
        this.a = i;
    }

    public void run() {
        if (this.h == 1) {
            b();
        } else {
            c();
        }
    }
}
