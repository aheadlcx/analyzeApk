package com.budejie.www.util;

import android.util.Log;
import com.budejie.www.util.g.a;
import java.util.ArrayList;

protected class g$b extends Thread {
    final /* synthetic */ g a;
    private boolean b = false;

    protected g$b(g gVar) {
        this.a = gVar;
    }

    public boolean a() {
        return this.b;
    }

    public void b() {
        this.b = true;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean a(java.io.File r14, java.lang.String r15) {
        /*
        r13 = this;
        r3 = 0;
        r1 = 0;
        r6 = new java.io.File;
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r2 = r14.toString();
        r0 = r0.append(r2);
        r2 = ".tmp";
        r0 = r0.append(r2);
        r0 = r0.toString();
        r6.<init>(r0);
        r0 = r6.exists();
        if (r0 == 0) goto L_0x0027;
    L_0x0024:
        r6.delete();
    L_0x0027:
        r2 = 0;
        r4 = 0;
        r0 = new java.net.URL;	 Catch:{ Exception -> 0x014b, all -> 0x0100 }
        r0.<init>(r15);	 Catch:{ Exception -> 0x014b, all -> 0x0100 }
        r0 = r0.openConnection();	 Catch:{ Exception -> 0x014b, all -> 0x0100 }
        r0 = (java.net.HttpURLConnection) r0;	 Catch:{ Exception -> 0x014b, all -> 0x0100 }
        r0.connect();	 Catch:{ Exception -> 0x0150, all -> 0x0135 }
        r5 = r0.getResponseCode();	 Catch:{ Exception -> 0x0150, all -> 0x0135 }
        r7 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        if (r5 == r7) goto L_0x007a;
    L_0x003f:
        r5 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0150, all -> 0x0135 }
        r5.<init>();	 Catch:{ Exception -> 0x0150, all -> 0x0135 }
        r6 = "Server returned HTTP ";
        r5 = r5.append(r6);	 Catch:{ Exception -> 0x0150, all -> 0x0135 }
        r6 = r0.getResponseCode();	 Catch:{ Exception -> 0x0150, all -> 0x0135 }
        r5 = r5.append(r6);	 Catch:{ Exception -> 0x0150, all -> 0x0135 }
        r6 = " ";
        r5 = r5.append(r6);	 Catch:{ Exception -> 0x0150, all -> 0x0135 }
        r6 = r0.getResponseMessage();	 Catch:{ Exception -> 0x0150, all -> 0x0135 }
        r5 = r5.append(r6);	 Catch:{ Exception -> 0x0150, all -> 0x0135 }
        r5 = r5.toString();	 Catch:{ Exception -> 0x0150, all -> 0x0135 }
        r6 = "BgMusicManager";
        android.util.Log.i(r6, r5);	 Catch:{ Exception -> 0x0150, all -> 0x0135 }
        if (r3 == 0) goto L_0x006e;
    L_0x006b:
        r4.close();	 Catch:{ IOException -> 0x0163 }
    L_0x006e:
        if (r3 == 0) goto L_0x0073;
    L_0x0070:
        r2.close();	 Catch:{ IOException -> 0x0163 }
    L_0x0073:
        if (r0 == 0) goto L_0x0078;
    L_0x0075:
        r0.disconnect();	 Catch:{ Exception -> 0x0113 }
    L_0x0078:
        r0 = r1;
    L_0x0079:
        return r0;
    L_0x007a:
        r5 = r0.getInputStream();	 Catch:{ Exception -> 0x0150, all -> 0x0135 }
        r4 = new java.io.FileOutputStream;	 Catch:{ Exception -> 0x0157, all -> 0x013b }
        r4.<init>(r6);	 Catch:{ Exception -> 0x0157, all -> 0x013b }
        r2 = 4096; // 0x1000 float:5.74E-42 double:2.0237E-320;
        r7 = new byte[r2];	 Catch:{ Exception -> 0x00b5, all -> 0x0140 }
        r2 = 0;
    L_0x0089:
        r8 = r5.read(r7);	 Catch:{ Exception -> 0x00b5, all -> 0x0140 }
        r9 = -1;
        if (r8 == r9) goto L_0x00e8;
    L_0x0090:
        r9 = r13.a();	 Catch:{ Exception -> 0x00b5, all -> 0x0140 }
        if (r9 == 0) goto L_0x00ae;
    L_0x0096:
        r2 = "BgMusicManager";
        r3 = "download cancelled isCancelled is true";
        android.util.Log.i(r2, r3);	 Catch:{ Exception -> 0x00b5, all -> 0x0140 }
        if (r4 == 0) goto L_0x00a2;
    L_0x009f:
        r4.close();	 Catch:{ IOException -> 0x0160 }
    L_0x00a2:
        if (r5 == 0) goto L_0x00a7;
    L_0x00a4:
        r5.close();	 Catch:{ IOException -> 0x0160 }
    L_0x00a7:
        if (r0 == 0) goto L_0x00ac;
    L_0x00a9:
        r0.disconnect();	 Catch:{ Exception -> 0x0113 }
    L_0x00ac:
        r0 = r1;
        goto L_0x0079;
    L_0x00ae:
        r10 = (long) r8;
        r2 = r2 + r10;
        r9 = 0;
        r4.write(r7, r9, r8);	 Catch:{ Exception -> 0x00b5, all -> 0x0140 }
        goto L_0x0089;
    L_0x00b5:
        r2 = move-exception;
        r3 = r4;
        r4 = r5;
        r12 = r2;
        r2 = r0;
        r0 = r12;
    L_0x00bb:
        r5 = "BgMusicManager";
        r6 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0146 }
        r6.<init>();	 Catch:{ all -> 0x0146 }
        r7 = "download error";
        r6 = r6.append(r7);	 Catch:{ all -> 0x0146 }
        r0 = r0.getMessage();	 Catch:{ all -> 0x0146 }
        r0 = r6.append(r0);	 Catch:{ all -> 0x0146 }
        r0 = r0.toString();	 Catch:{ all -> 0x0146 }
        android.util.Log.i(r5, r0);	 Catch:{ all -> 0x0146 }
        if (r3 == 0) goto L_0x00dc;
    L_0x00d9:
        r3.close();	 Catch:{ IOException -> 0x0149 }
    L_0x00dc:
        if (r4 == 0) goto L_0x00e1;
    L_0x00de:
        r4.close();	 Catch:{ IOException -> 0x0149 }
    L_0x00e1:
        if (r2 == 0) goto L_0x00e6;
    L_0x00e3:
        r2.disconnect();	 Catch:{ Exception -> 0x0113 }
    L_0x00e6:
        r0 = r1;
        goto L_0x0079;
    L_0x00e8:
        if (r4 == 0) goto L_0x00ed;
    L_0x00ea:
        r4.close();	 Catch:{ IOException -> 0x015e }
    L_0x00ed:
        if (r5 == 0) goto L_0x00f2;
    L_0x00ef:
        r5.close();	 Catch:{ IOException -> 0x015e }
    L_0x00f2:
        if (r0 == 0) goto L_0x00f7;
    L_0x00f4:
        r0.disconnect();	 Catch:{ Exception -> 0x0113 }
    L_0x00f7:
        r6.renameTo(r14);
        r0 = r14.exists();
        goto L_0x0079;
    L_0x0100:
        r0 = move-exception;
        r2 = r3;
        r5 = r3;
    L_0x0103:
        if (r3 == 0) goto L_0x0108;
    L_0x0105:
        r3.close();	 Catch:{ IOException -> 0x0133 }
    L_0x0108:
        if (r5 == 0) goto L_0x010d;
    L_0x010a:
        r5.close();	 Catch:{ IOException -> 0x0133 }
    L_0x010d:
        if (r2 == 0) goto L_0x0112;
    L_0x010f:
        r2.disconnect();	 Catch:{ Exception -> 0x0113 }
    L_0x0112:
        throw r0;	 Catch:{ Exception -> 0x0113 }
    L_0x0113:
        r0 = move-exception;
        r2 = "BgMusicManager";
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r4 = "download error";
        r3 = r3.append(r4);
        r0 = r0.getMessage();
        r0 = r3.append(r0);
        r0 = r0.toString();
        android.util.Log.i(r2, r0);
        r0 = r1;
        goto L_0x0079;
    L_0x0133:
        r3 = move-exception;
        goto L_0x010d;
    L_0x0135:
        r2 = move-exception;
        r5 = r3;
        r12 = r0;
        r0 = r2;
        r2 = r12;
        goto L_0x0103;
    L_0x013b:
        r2 = move-exception;
        r12 = r2;
        r2 = r0;
        r0 = r12;
        goto L_0x0103;
    L_0x0140:
        r2 = move-exception;
        r3 = r4;
        r12 = r0;
        r0 = r2;
        r2 = r12;
        goto L_0x0103;
    L_0x0146:
        r0 = move-exception;
        r5 = r4;
        goto L_0x0103;
    L_0x0149:
        r0 = move-exception;
        goto L_0x00e1;
    L_0x014b:
        r0 = move-exception;
        r2 = r3;
        r4 = r3;
        goto L_0x00bb;
    L_0x0150:
        r2 = move-exception;
        r4 = r3;
        r12 = r0;
        r0 = r2;
        r2 = r12;
        goto L_0x00bb;
    L_0x0157:
        r2 = move-exception;
        r4 = r5;
        r12 = r0;
        r0 = r2;
        r2 = r12;
        goto L_0x00bb;
    L_0x015e:
        r2 = move-exception;
        goto L_0x00f2;
    L_0x0160:
        r2 = move-exception;
        goto L_0x00a7;
    L_0x0163:
        r2 = move-exception;
        goto L_0x0073;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.budejie.www.util.g$b.a(java.io.File, java.lang.String):boolean");
    }

    public void run() {
        if (g.a(this.a) == null) {
            g.a(this.a, false);
            return;
        }
        int i;
        this.a.a = new ArrayList();
        for (i = 0; i < g.a(this.a).size() && !this.b; i++) {
            a aVar = (a) g.a(this.a).get(i);
            if (aVar.d.exists()) {
                Log.e("wuzhenlin", "local -> " + aVar.toString());
                this.a.a.add(aVar);
            }
        }
        for (i = 0; i < g.a(this.a).size() && !this.b; i++) {
            aVar = (a) g.a(this.a).get(i);
            if (!aVar.d.exists() && a(aVar.d, aVar.c)) {
                Log.e("wuzhenlin", "download -> " + aVar.toString());
                this.a.a.add(aVar);
            }
        }
        this.a.g();
        g.a(this.a, false);
    }
}
