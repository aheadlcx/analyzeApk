package com.tencent.bugly.proguard;

import android.text.TextUtils;
import com.tencent.bugly.beta.download.BetaReceiver;
import com.tencent.bugly.beta.download.DownloadTask;
import com.tencent.bugly.beta.global.d;
import com.tencent.bugly.beta.ui.c;
import com.tencent.bugly.beta.utils.e;
import com.tencent.tinker.loader.shareutil.ShareConstants;
import java.io.File;
import java.net.HttpURLConnection;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class t extends DownloadTask implements Runnable {
    public long k = 0;
    private File l;
    private long m = 0;

    public t(String str, String str2, long j, long j2, String str3) {
        super(str, "", "", str3);
        this.l = new File(str2);
        this.b = this.l.getParent();
        this.c = this.l.getName();
        this.e = j;
        this.f = j2;
        getStatus();
    }

    public t(String str, String str2, String str3, String str4) {
        super(str, str2, str3, str4);
        getStatus();
    }

    public File getSaveFile() {
        return this.l;
    }

    public void download() {
        if (getStatus() == 1) {
            b();
        } else if (getStatus() != 2) {
            if (getSaveFile() == null || !getSaveFile().exists()) {
                this.e = 0;
                this.f = 0;
                this.k = 0;
            } else {
                this.e = getSaveFile().length();
            }
            if (this.g) {
                c.a.a(this);
            }
            this.m = System.currentTimeMillis();
            this.i = 2;
            s.a.b.put(getDownloadUrl(), this);
            s.a.a(this);
        }
    }

    public void delete(boolean z) {
        stop();
        if (z) {
            if (!(getSaveFile() == null || !getSaveFile().exists() || getSaveFile().isDirectory())) {
                getSaveFile().delete();
            }
            p.a.b(this);
        }
        BetaReceiver.netListeners.remove(getDownloadUrl());
        this.c = null;
        this.e = 0;
        this.f = 0;
        this.i = 4;
    }

    public void stop() {
        if (this.i != 5) {
            this.i = 3;
        }
    }

    public int getStatus() {
        if (getSaveFile() != null && getSaveFile().exists() && getSaveFile().length() == this.f && !s.a.b.contains(this)) {
            this.e = this.f;
            this.i = 1;
        }
        if (getSaveFile() != null && getSaveFile().exists() && getSaveFile().length() > 0 && getSaveFile().length() < this.f && !s.a.b.contains(this)) {
            this.e = getSaveFile().length();
            this.i = 3;
        }
        if ((getSaveFile() == null || !getSaveFile().exists()) && !s.a.b.contains(this)) {
            this.i = 0;
        }
        return this.i;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void run() {
        /*
        r12 = this;
        r5 = new java.net.URL;	 Catch:{ MalformedURLException -> 0x00ad }
        r0 = r12.getDownloadUrl();	 Catch:{ MalformedURLException -> 0x00ad }
        r5.<init>(r0);	 Catch:{ MalformedURLException -> 0x00ad }
        r2 = 0;
        r1 = 0;
        r0 = 0;
    L_0x000c:
        r3 = 3;
        if (r0 >= r3) goto L_0x0165;
    L_0x000f:
        r4 = r0 + 1;
        r0 = r5.openConnection();	 Catch:{ IOException -> 0x01e4 }
        r0 = (javax.net.ssl.HttpsURLConnection) r0;	 Catch:{ IOException -> 0x01e4 }
        r3 = 5000; // 0x1388 float:7.006E-42 double:2.4703E-320;
        r0.setConnectTimeout(r3);	 Catch:{ IOException -> 0x01e4 }
        r3 = "GET";
        r0.setRequestMethod(r3);	 Catch:{ IOException -> 0x01e4 }
        r3 = "Referer";
        r6 = r5.toString();	 Catch:{ IOException -> 0x01e4 }
        r0.setRequestProperty(r3, r6);	 Catch:{ IOException -> 0x01e4 }
        r3 = "Charset";
        r6 = "UTF-8";
        r0.setRequestProperty(r3, r6);	 Catch:{ IOException -> 0x01e4 }
        r3 = "Range";
        r6 = new java.lang.StringBuilder;	 Catch:{ IOException -> 0x01e4 }
        r6.<init>();	 Catch:{ IOException -> 0x01e4 }
        r7 = "bytes=";
        r6 = r6.append(r7);	 Catch:{ IOException -> 0x01e4 }
        r8 = r12.e;	 Catch:{ IOException -> 0x01e4 }
        r6 = r6.append(r8);	 Catch:{ IOException -> 0x01e4 }
        r7 = "-";
        r6 = r6.append(r7);	 Catch:{ IOException -> 0x01e4 }
        r6 = r6.toString();	 Catch:{ IOException -> 0x01e4 }
        r0.setRequestProperty(r3, r6);	 Catch:{ IOException -> 0x01e4 }
        r3 = "Connection";
        r6 = "Keep-Alive";
        r0.setRequestProperty(r3, r6);	 Catch:{ IOException -> 0x01e4 }
        r0.connect();	 Catch:{ IOException -> 0x01e4 }
        r3 = r12.a(r0);	 Catch:{ IOException -> 0x01e4 }
        r12.c = r3;	 Catch:{ IOException -> 0x01e4 }
        r3 = new java.io.File;	 Catch:{ IOException -> 0x01e4 }
        r6 = r12.b;	 Catch:{ IOException -> 0x01e4 }
        r3.<init>(r6);	 Catch:{ IOException -> 0x01e4 }
        r6 = r3.exists();	 Catch:{ IOException -> 0x01e4 }
        if (r6 != 0) goto L_0x007a;
    L_0x0077:
        r3.mkdirs();	 Catch:{ IOException -> 0x01e4 }
    L_0x007a:
        r6 = new java.io.File;	 Catch:{ IOException -> 0x01e4 }
        r7 = r12.c;	 Catch:{ IOException -> 0x01e4 }
        r6.<init>(r3, r7);	 Catch:{ IOException -> 0x01e4 }
        r12.l = r6;	 Catch:{ IOException -> 0x01e4 }
        r6 = r12.f;	 Catch:{ IOException -> 0x01e4 }
        r8 = 0;
        r3 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1));
        if (r3 != 0) goto L_0x00c7;
    L_0x008b:
        r3 = r0.getContentLength();	 Catch:{ IOException -> 0x01e4 }
        r6 = (long) r3;	 Catch:{ IOException -> 0x01e4 }
        r12.f = r6;	 Catch:{ IOException -> 0x01e4 }
        r6 = r12.f;	 Catch:{ IOException -> 0x01e4 }
        r8 = 0;
        r3 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1));
        if (r3 > 0) goto L_0x00c7;
    L_0x009a:
        r0 = 2000; // 0x7d0 float:2.803E-42 double:9.88E-321;
        r3 = "tLen <= 0 ";
        r12.a(r0, r3);	 Catch:{ IOException -> 0x01e4 }
        if (r1 == 0) goto L_0x00a7;
    L_0x00a4:
        r1.close();	 Catch:{ Exception -> 0x01b3 }
    L_0x00a7:
        if (r2 == 0) goto L_0x00ac;
    L_0x00a9:
        r2.close();	 Catch:{ Exception -> 0x00c2 }
    L_0x00ac:
        return;
    L_0x00ad:
        r0 = move-exception;
        r1 = r0.getMessage();
        r2 = 0;
        r2 = new java.lang.Object[r2];
        com.tencent.bugly.proguard.an.a(r1, r2);
        r1 = 2010; // 0x7da float:2.817E-42 double:9.93E-321;
        r0 = r0.getMessage();
        r12.a(r1, r0);
        goto L_0x00ac;
    L_0x00c2:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x00ac;
    L_0x00c7:
        r3 = com.tencent.bugly.proguard.p.a;	 Catch:{ IOException -> 0x01e4 }
        r3.a(r12);	 Catch:{ IOException -> 0x01e4 }
        r3 = r0.getInputStream();	 Catch:{ IOException -> 0x01e4 }
        r0 = 307200; // 0x4b000 float:4.30479E-40 double:1.51777E-318;
        r6 = new byte[r0];	 Catch:{ IOException -> 0x01e6, all -> 0x01e1 }
        r2 = new java.io.RandomAccessFile;	 Catch:{ IOException -> 0x01e6, all -> 0x01e1 }
        r0 = r12.l;	 Catch:{ IOException -> 0x01e6, all -> 0x01e1 }
        r7 = "rwd";
        r2.<init>(r0, r7);	 Catch:{ IOException -> 0x01e6, all -> 0x01e1 }
        r0 = r12.e;	 Catch:{ IOException -> 0x0188, all -> 0x01aa }
        r2.seek(r0);	 Catch:{ IOException -> 0x0188, all -> 0x01aa }
        r1 = 0;
    L_0x00e5:
        r7 = r3.read(r6);	 Catch:{ IOException -> 0x0188, all -> 0x01aa }
        r0 = -1;
        if (r7 == r0) goto L_0x015b;
    L_0x00ec:
        r8 = r12.e;	 Catch:{ IOException -> 0x0188, all -> 0x01aa }
        r10 = (long) r7;	 Catch:{ IOException -> 0x0188, all -> 0x01aa }
        r8 = r8 + r10;
        r12.e = r8;	 Catch:{ IOException -> 0x0188, all -> 0x01aa }
        r8 = r12.e;	 Catch:{ IOException -> 0x0188, all -> 0x01aa }
        r10 = r12.f;	 Catch:{ IOException -> 0x0188, all -> 0x01aa }
        r0 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1));
        if (r0 <= 0) goto L_0x0116;
    L_0x00fa:
        r12.b();	 Catch:{ IOException -> 0x0188, all -> 0x01aa }
        r0 = "mSavedLength > mTotalLength,重新下载!";
        r1 = 0;
        r1 = new java.lang.Object[r1];	 Catch:{ IOException -> 0x0188, all -> 0x01aa }
        com.tencent.bugly.proguard.an.e(r0, r1);	 Catch:{ IOException -> 0x0188, all -> 0x01aa }
        if (r2 == 0) goto L_0x010b;
    L_0x0108:
        r2.close();	 Catch:{ Exception -> 0x01dc, all -> 0x01cd }
    L_0x010b:
        if (r3 == 0) goto L_0x00ac;
    L_0x010d:
        r3.close();	 Catch:{ Exception -> 0x0111 }
        goto L_0x00ac;
    L_0x0111:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x00ac;
    L_0x0116:
        r0 = 1120403456; // 0x42c80000 float:100.0 double:5.53552857E-315;
        r8 = r12.e;	 Catch:{ IOException -> 0x0188, all -> 0x01aa }
        r8 = (float) r8;	 Catch:{ IOException -> 0x0188, all -> 0x01aa }
        r10 = r12.f;	 Catch:{ IOException -> 0x0188, all -> 0x01aa }
        r9 = (float) r10;	 Catch:{ IOException -> 0x0188, all -> 0x01aa }
        r8 = r8 / r9;
        r0 = r0 * r8;
        r8 = r0 - r1;
        r8 = (double) r8;	 Catch:{ IOException -> 0x0188, all -> 0x01aa }
        r10 = 4607182418800017408; // 0x3ff0000000000000 float:0.0 double:1.0;
        r8 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1));
        if (r8 < 0) goto L_0x01ed;
    L_0x0129:
        r12.a();	 Catch:{ IOException -> 0x0188, all -> 0x01aa }
    L_0x012c:
        r1 = 0;
        r2.write(r6, r1, r7);	 Catch:{ IOException -> 0x0188, all -> 0x01aa }
        r1 = r12.getSaveFile();	 Catch:{ IOException -> 0x0188, all -> 0x01aa }
        if (r1 == 0) goto L_0x0147;
    L_0x0136:
        r1 = r12.getSaveFile();	 Catch:{ IOException -> 0x0188, all -> 0x01aa }
        r1 = r1.exists();	 Catch:{ IOException -> 0x0188, all -> 0x01aa }
        if (r1 == 0) goto L_0x0147;
    L_0x0140:
        r1 = r12.getStatus();	 Catch:{ IOException -> 0x0188, all -> 0x01aa }
        r7 = 3;
        if (r1 != r7) goto L_0x0159;
    L_0x0147:
        if (r2 == 0) goto L_0x014c;
    L_0x0149:
        r2.close();	 Catch:{ Exception -> 0x01dc, all -> 0x01cd }
    L_0x014c:
        if (r3 == 0) goto L_0x00ac;
    L_0x014e:
        r3.close();	 Catch:{ Exception -> 0x0153 }
        goto L_0x00ac;
    L_0x0153:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x00ac;
    L_0x0159:
        r1 = r0;
        goto L_0x00e5;
    L_0x015b:
        r12.b();	 Catch:{ IOException -> 0x0188, all -> 0x01aa }
        if (r2 == 0) goto L_0x01e9;
    L_0x0160:
        r2.close();	 Catch:{ Exception -> 0x01dc, all -> 0x01cd }
        r0 = r4;
        r2 = r3;
    L_0x0165:
        r1 = 3;
        if (r0 < r1) goto L_0x017b;
    L_0x0168:
        r0 = com.tencent.bugly.proguard.t.class;
        r1 = "have retry %d times";
        r3 = 1;
        r3 = new java.lang.Object[r3];	 Catch:{ Exception -> 0x01b3 }
        r4 = 0;
        r5 = 3;
        r5 = java.lang.Integer.valueOf(r5);	 Catch:{ Exception -> 0x01b3 }
        r3[r4] = r5;	 Catch:{ Exception -> 0x01b3 }
        com.tencent.bugly.proguard.an.b(r0, r1, r3);	 Catch:{ Exception -> 0x01b3 }
    L_0x017b:
        if (r2 == 0) goto L_0x00ac;
    L_0x017d:
        r2.close();	 Catch:{ Exception -> 0x0182 }
        goto L_0x00ac;
    L_0x0182:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x00ac;
    L_0x0188:
        r0 = move-exception;
        r1 = r2;
        r2 = r3;
    L_0x018b:
        r0.printStackTrace();	 Catch:{ all -> 0x01df }
        r3 = 2020; // 0x7e4 float:2.83E-42 double:9.98E-321;
        r0 = r0.getMessage();	 Catch:{ all -> 0x01df }
        r12.a(r3, r0);	 Catch:{ all -> 0x01df }
        r0 = com.tencent.bugly.proguard.t.class;
        r3 = "IOException,stop download!";
        r6 = 0;
        r6 = new java.lang.Object[r6];	 Catch:{ all -> 0x01df }
        com.tencent.bugly.proguard.an.b(r0, r3, r6);	 Catch:{ all -> 0x01df }
        if (r1 == 0) goto L_0x01a7;
    L_0x01a4:
        r1.close();	 Catch:{ Exception -> 0x01b3 }
    L_0x01a7:
        r0 = r4;
        goto L_0x000c;
    L_0x01aa:
        r0 = move-exception;
        r1 = r2;
        r2 = r3;
    L_0x01ad:
        if (r1 == 0) goto L_0x01b2;
    L_0x01af:
        r1.close();	 Catch:{ Exception -> 0x01b3 }
    L_0x01b2:
        throw r0;	 Catch:{ Exception -> 0x01b3 }
    L_0x01b3:
        r0 = move-exception;
    L_0x01b4:
        r1 = 2000; // 0x7d0 float:2.803E-42 double:9.88E-321;
        r3 = r0.getMessage();	 Catch:{ all -> 0x01da }
        r12.a(r1, r3);	 Catch:{ all -> 0x01da }
        r0.printStackTrace();	 Catch:{ all -> 0x01da }
        if (r2 == 0) goto L_0x00ac;
    L_0x01c2:
        r2.close();	 Catch:{ Exception -> 0x01c7 }
        goto L_0x00ac;
    L_0x01c7:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x00ac;
    L_0x01cd:
        r0 = move-exception;
        r2 = r3;
    L_0x01cf:
        if (r2 == 0) goto L_0x01d4;
    L_0x01d1:
        r2.close();	 Catch:{ Exception -> 0x01d5 }
    L_0x01d4:
        throw r0;
    L_0x01d5:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x01d4;
    L_0x01da:
        r0 = move-exception;
        goto L_0x01cf;
    L_0x01dc:
        r0 = move-exception;
        r2 = r3;
        goto L_0x01b4;
    L_0x01df:
        r0 = move-exception;
        goto L_0x01ad;
    L_0x01e1:
        r0 = move-exception;
        r2 = r3;
        goto L_0x01ad;
    L_0x01e4:
        r0 = move-exception;
        goto L_0x018b;
    L_0x01e6:
        r0 = move-exception;
        r2 = r3;
        goto L_0x018b;
    L_0x01e9:
        r0 = r4;
        r2 = r3;
        goto L_0x0165;
    L_0x01ed:
        r0 = r1;
        goto L_0x012c;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.t.run():void");
    }

    private String a(HttpURLConnection httpURLConnection) {
        try {
            if (!TextUtils.isEmpty(this.c)) {
                return this.c;
            }
            Map headerFields = httpURLConnection.getHeaderFields();
            if (headerFields != null) {
                for (String str : headerFields.keySet()) {
                    if (str != null) {
                        List<String> list = (List) headerFields.get(str);
                        if (list != null) {
                            for (String str2 : list) {
                                if (str2 != null && "content-disposition".equals(str.toLowerCase())) {
                                    Matcher matcher = Pattern.compile(".*filename=(.*)").matcher(str2.toLowerCase());
                                    if (matcher.find()) {
                                        return matcher.group(1);
                                    }
                                }
                            }
                            continue;
                        } else {
                            continue;
                        }
                    }
                }
            }
            String str3 = getDownloadUrl().substring(getDownloadUrl().lastIndexOf(47) + 1);
            if (!TextUtils.isEmpty(str3)) {
                return str3;
            }
            return UUID.randomUUID() + ShareConstants.PATCH_SUFFIX;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void a(int i, String str) {
        this.i = 5;
        c.a.a();
        s.a.b.remove(getDownloadUrl());
        e.a(new d(10, new Object[]{this.d, this, Integer.valueOf(i), str}));
    }

    protected void a() {
        this.k += System.currentTimeMillis() - this.m;
        p.a.a(this);
        this.m = System.currentTimeMillis();
        c.a.a();
        e.a(new d(9, new Object[]{this.d, this}));
    }

    protected void b() {
        this.i = 1;
        a();
        s.a.b.remove(getDownloadUrl());
        BetaReceiver.netListeners.remove(getDownloadUrl());
        e.a(new d(8, new Object[]{this.d, this}));
    }

    public long getCostTime() {
        return this.k;
    }
}
