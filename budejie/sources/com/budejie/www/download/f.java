package com.budejie.www.download;

import java.util.Map;

public class f extends Thread {
    f$b a = f$b.a;
    private f$a b;
    private boolean c = false;
    private Map<String, f> d;
    private String e;
    private String f;
    private String g;

    public f(String str, String str2, String str3) {
        this.e = str;
        this.g = str2;
        this.f = str3;
    }

    public String getUrl() {
        return this.e;
    }

    public void a(Map<String, f> map) {
        this.d = map;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void run() {
        /*
        r14 = this;
        r4 = 0;
        r2 = 0;
        r0 = com.budejie.www.download.f$b.a;
        r14.a = r0;
        r0 = r14.b;
        if (r0 == 0) goto L_0x0011;
    L_0x000a:
        r0 = r14.b;
        r1 = r14.e;
        r0.a(r1);
    L_0x0011:
        r0 = 10;
        android.os.Process.setThreadPriority(r0);
        r0 = 0;
        r1 = 0;
        r6 = 0;
        r3 = new java.net.URL;	 Catch:{ Exception -> 0x02be, all -> 0x025f }
        r5 = r14.e;	 Catch:{ Exception -> 0x02be, all -> 0x025f }
        r5 = com.budejie.www.download.i.b(r5);	 Catch:{ Exception -> 0x02be, all -> 0x025f }
        r3.<init>(r5);	 Catch:{ Exception -> 0x02be, all -> 0x025f }
        r5 = "";
        r5 = r5.equals(r3);	 Catch:{ Exception -> 0x02be, all -> 0x025f }
        if (r5 == 0) goto L_0x0078;
    L_0x002d:
        r2 = com.budejie.www.download.f$1.a;
        r3 = r14.a;
        r3 = r3.ordinal();
        r2 = r2[r3];
        switch(r2) {
            case 1: goto L_0x004a;
            case 2: goto L_0x003a;
            case 3: goto L_0x0064;
            default: goto L_0x003a;
        };
    L_0x003a:
        if (r4 == 0) goto L_0x003f;
    L_0x003c:
        r4.disconnect();
    L_0x003f:
        if (r4 == 0) goto L_0x0044;
    L_0x0041:
        r1.close();	 Catch:{ IOException -> 0x0073 }
    L_0x0044:
        if (r4 == 0) goto L_0x0049;
    L_0x0046:
        r0.close();	 Catch:{ IOException -> 0x0073 }
    L_0x0049:
        return;
    L_0x004a:
        r2 = r14.b;
        if (r2 == 0) goto L_0x0055;
    L_0x004e:
        r2 = r14.b;
        r3 = r14.e;
        r2.b(r3);
    L_0x0055:
        r2 = r14.d;
        r3 = r14.e;
        r2.remove(r3);
        r2 = r14.d;
        r3 = r14.f;
        r2.remove(r3);
        goto L_0x003a;
    L_0x0064:
        r2 = r14.d;
        r3 = r14.e;
        r2.remove(r3);
        r2 = r14.d;
        r3 = r14.f;
        r2.remove(r3);
        goto L_0x003a;
    L_0x0073:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x0049;
    L_0x0078:
        r5 = r14.f;	 Catch:{ Exception -> 0x02be, all -> 0x025f }
        r8 = r3.toString();	 Catch:{ Exception -> 0x02be, all -> 0x025f }
        r5 = com.budejie.www.download.d.a(r5, r8);	 Catch:{ Exception -> 0x02be, all -> 0x025f }
        r14.f = r5;	 Catch:{ Exception -> 0x02be, all -> 0x025f }
        r5 = r14.d;	 Catch:{ Exception -> 0x02be, all -> 0x025f }
        r8 = r14.f;	 Catch:{ Exception -> 0x02be, all -> 0x025f }
        r5 = r5.containsKey(r8);	 Catch:{ Exception -> 0x02be, all -> 0x025f }
        if (r5 == 0) goto L_0x00d9;
    L_0x008e:
        r2 = com.budejie.www.download.f$1.a;
        r3 = r14.a;
        r3 = r3.ordinal();
        r2 = r2[r3];
        switch(r2) {
            case 1: goto L_0x00b0;
            case 2: goto L_0x009b;
            case 3: goto L_0x00ca;
            default: goto L_0x009b;
        };
    L_0x009b:
        if (r4 == 0) goto L_0x00a0;
    L_0x009d:
        r4.disconnect();
    L_0x00a0:
        if (r4 == 0) goto L_0x00a5;
    L_0x00a2:
        r1.close();	 Catch:{ IOException -> 0x00ab }
    L_0x00a5:
        if (r4 == 0) goto L_0x0049;
    L_0x00a7:
        r0.close();	 Catch:{ IOException -> 0x00ab }
        goto L_0x0049;
    L_0x00ab:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x0049;
    L_0x00b0:
        r2 = r14.b;
        if (r2 == 0) goto L_0x00bb;
    L_0x00b4:
        r2 = r14.b;
        r3 = r14.e;
        r2.b(r3);
    L_0x00bb:
        r2 = r14.d;
        r3 = r14.e;
        r2.remove(r3);
        r2 = r14.d;
        r3 = r14.f;
        r2.remove(r3);
        goto L_0x009b;
    L_0x00ca:
        r2 = r14.d;
        r3 = r14.e;
        r2.remove(r3);
        r2 = r14.d;
        r3 = r14.f;
        r2.remove(r3);
        goto L_0x009b;
    L_0x00d9:
        r0 = r14.d;	 Catch:{ Exception -> 0x02be, all -> 0x025f }
        r1 = r14.f;	 Catch:{ Exception -> 0x02be, all -> 0x025f }
        r0.put(r1, r14);	 Catch:{ Exception -> 0x02be, all -> 0x025f }
        r0 = r14.d;	 Catch:{ Exception -> 0x02be, all -> 0x025f }
        r1 = r14.e;	 Catch:{ Exception -> 0x02be, all -> 0x025f }
        r0.put(r1, r14);	 Catch:{ Exception -> 0x02be, all -> 0x025f }
        r0 = r3.openConnection();	 Catch:{ Exception -> 0x02be, all -> 0x025f }
        r0 = (java.net.HttpURLConnection) r0;	 Catch:{ Exception -> 0x02be, all -> 0x025f }
        r1 = 30000; // 0x7530 float:4.2039E-41 double:1.4822E-319;
        r0.setConnectTimeout(r1);	 Catch:{ Exception -> 0x02c3, all -> 0x02ad }
        r0.connect();	 Catch:{ Exception -> 0x02c3, all -> 0x02ad }
        r3 = r0.getInputStream();	 Catch:{ Exception -> 0x02c3, all -> 0x02ad }
        r1 = r0.getContentLength();	 Catch:{ Exception -> 0x02c9, all -> 0x02b2 }
        r8 = new java.io.File;	 Catch:{ Exception -> 0x02c9, all -> 0x02b2 }
        r5 = r14.g;	 Catch:{ Exception -> 0x02c9, all -> 0x02b2 }
        r8.<init>(r5);	 Catch:{ Exception -> 0x02c9, all -> 0x02b2 }
        r5 = r8.exists();	 Catch:{ Exception -> 0x02c9, all -> 0x02b2 }
        if (r5 != 0) goto L_0x010d;
    L_0x010a:
        r8.mkdirs();	 Catch:{ Exception -> 0x02c9, all -> 0x02b2 }
    L_0x010d:
        r9 = new java.io.File;	 Catch:{ Exception -> 0x02c9, all -> 0x02b2 }
        r5 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x02c9, all -> 0x02b2 }
        r5.<init>();	 Catch:{ Exception -> 0x02c9, all -> 0x02b2 }
        r10 = r14.f;	 Catch:{ Exception -> 0x02c9, all -> 0x02b2 }
        r5 = r5.append(r10);	 Catch:{ Exception -> 0x02c9, all -> 0x02b2 }
        r10 = ".tmp";
        r5 = r5.append(r10);	 Catch:{ Exception -> 0x02c9, all -> 0x02b2 }
        r5 = r5.toString();	 Catch:{ Exception -> 0x02c9, all -> 0x02b2 }
        r9.<init>(r8, r5);	 Catch:{ Exception -> 0x02c9, all -> 0x02b2 }
        r5 = new java.io.FileOutputStream;	 Catch:{ Exception -> 0x02c9, all -> 0x02b2 }
        r5.<init>(r9);	 Catch:{ Exception -> 0x02c9, all -> 0x02b2 }
        r1 = r1 / 1024;
        r1 = r1 / 1024;
        r4 = 5;
        if (r1 >= r4) goto L_0x0173;
    L_0x0133:
        r1 = 2048; // 0x800 float:2.87E-42 double:1.0118E-320;
    L_0x0135:
        r4 = new byte[r1];	 Catch:{ Exception -> 0x01db, all -> 0x02b6 }
        r1 = r2;
    L_0x0138:
        r10 = r3.read(r4);	 Catch:{ Exception -> 0x01db, all -> 0x02b6 }
        r11 = -1;
        if (r10 == r11) goto L_0x0147;
    L_0x013f:
        r11 = r14.c;	 Catch:{ Exception -> 0x01db, all -> 0x02b6 }
        if (r11 == 0) goto L_0x0193;
    L_0x0143:
        r1 = com.budejie.www.download.f$b.c;	 Catch:{ Exception -> 0x01db, all -> 0x02b6 }
        r14.a = r1;	 Catch:{ Exception -> 0x01db, all -> 0x02b6 }
    L_0x0147:
        r1 = r14.c;	 Catch:{ Exception -> 0x01db, all -> 0x02b6 }
        if (r1 == 0) goto L_0x01cb;
    L_0x014b:
        r1 = com.budejie.www.download.f$b.c;	 Catch:{ Exception -> 0x01db, all -> 0x02b6 }
        r14.a = r1;	 Catch:{ Exception -> 0x01db, all -> 0x02b6 }
    L_0x014f:
        r1 = com.budejie.www.download.f$1.a;
        r2 = r14.a;
        r2 = r2.ordinal();
        r1 = r1[r2];
        switch(r1) {
            case 1: goto L_0x020b;
            case 2: goto L_0x015c;
            case 3: goto L_0x0226;
            default: goto L_0x015c;
        };
    L_0x015c:
        if (r0 == 0) goto L_0x0161;
    L_0x015e:
        r0.disconnect();
    L_0x0161:
        if (r3 == 0) goto L_0x0166;
    L_0x0163:
        r3.close();	 Catch:{ IOException -> 0x016d }
    L_0x0166:
        if (r5 == 0) goto L_0x0049;
    L_0x0168:
        r5.close();	 Catch:{ IOException -> 0x016d }
        goto L_0x0049;
    L_0x016d:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x0049;
    L_0x0173:
        r4 = 15;
        if (r1 >= r4) goto L_0x017a;
    L_0x0177:
        r1 = 6144; // 0x1800 float:8.61E-42 double:3.0355E-320;
        goto L_0x0135;
    L_0x017a:
        r4 = 35;
        if (r1 >= r4) goto L_0x0181;
    L_0x017e:
        r1 = 14336; // 0x3800 float:2.0089E-41 double:7.083E-320;
        goto L_0x0135;
    L_0x0181:
        r4 = 55;
        if (r1 >= r4) goto L_0x0188;
    L_0x0185:
        r1 = 22528; // 0x5800 float:3.1568E-41 double:1.11303E-319;
        goto L_0x0135;
    L_0x0188:
        r4 = 75;
        if (r1 >= r4) goto L_0x018f;
    L_0x018c:
        r1 = 30720; // 0x7800 float:4.3048E-41 double:1.51777E-319;
        goto L_0x0135;
    L_0x018f:
        r1 = 40960; // 0xa000 float:5.7397E-41 double:2.0237E-319;
        goto L_0x0135;
    L_0x0193:
        r12 = (long) r10;
        r6 = r6 + r12;
        r11 = 0;
        r5.write(r4, r11, r10);	 Catch:{ Exception -> 0x01db, all -> 0x02b6 }
        r5.flush();	 Catch:{ Exception -> 0x01db, all -> 0x02b6 }
        r10 = 150; // 0x96 float:2.1E-43 double:7.4E-322;
        if (r1 > r10) goto L_0x01a2;
    L_0x01a0:
        if (r1 != 0) goto L_0x01c7;
    L_0x01a2:
        r1 = "FileDownTask";
        r10 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x01db, all -> 0x02b6 }
        r10.<init>();	 Catch:{ Exception -> 0x01db, all -> 0x02b6 }
        r11 = "正在下载:";
        r10 = r10.append(r11);	 Catch:{ Exception -> 0x01db, all -> 0x02b6 }
        r11 = r14.f;	 Catch:{ Exception -> 0x01db, all -> 0x02b6 }
        r10 = r10.append(r11);	 Catch:{ Exception -> 0x01db, all -> 0x02b6 }
        r11 = "   currentSize:";
        r10 = r10.append(r11);	 Catch:{ Exception -> 0x01db, all -> 0x02b6 }
        r10 = r10.append(r6);	 Catch:{ Exception -> 0x01db, all -> 0x02b6 }
        r10 = r10.toString();	 Catch:{ Exception -> 0x01db, all -> 0x02b6 }
        com.budejie.www.util.aa.b(r1, r10);	 Catch:{ Exception -> 0x01db, all -> 0x02b6 }
        r1 = r2;
    L_0x01c7:
        r1 = r1 + 1;
        goto L_0x0138;
    L_0x01cb:
        r1 = com.budejie.www.download.f$b.b;	 Catch:{ Exception -> 0x01db, all -> 0x02b6 }
        r14.a = r1;	 Catch:{ Exception -> 0x01db, all -> 0x02b6 }
        r1 = new java.io.File;	 Catch:{ Exception -> 0x01db, all -> 0x02b6 }
        r2 = r14.f;	 Catch:{ Exception -> 0x01db, all -> 0x02b6 }
        r1.<init>(r8, r2);	 Catch:{ Exception -> 0x01db, all -> 0x02b6 }
        r9.renameTo(r1);	 Catch:{ Exception -> 0x01db, all -> 0x02b6 }
        goto L_0x014f;
    L_0x01db:
        r1 = move-exception;
        r4 = r5;
        r2 = r0;
        r0 = r1;
        r1 = r3;
    L_0x01e0:
        r0.printStackTrace();	 Catch:{ all -> 0x02bb }
        r0 = com.budejie.www.download.f$b.d;	 Catch:{ all -> 0x02bb }
        r14.a = r0;	 Catch:{ all -> 0x02bb }
        r0 = com.budejie.www.download.f$1.a;
        r3 = r14.a;
        r3 = r3.ordinal();
        r0 = r0[r3];
        switch(r0) {
            case 1: goto L_0x0236;
            case 2: goto L_0x01f4;
            case 3: goto L_0x0250;
            default: goto L_0x01f4;
        };
    L_0x01f4:
        if (r2 == 0) goto L_0x01f9;
    L_0x01f6:
        r2.disconnect();
    L_0x01f9:
        if (r1 == 0) goto L_0x01fe;
    L_0x01fb:
        r1.close();	 Catch:{ IOException -> 0x0205 }
    L_0x01fe:
        if (r4 == 0) goto L_0x0049;
    L_0x0200:
        r4.close();	 Catch:{ IOException -> 0x0205 }
        goto L_0x0049;
    L_0x0205:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x0049;
    L_0x020b:
        r1 = r14.b;
        if (r1 == 0) goto L_0x0216;
    L_0x020f:
        r1 = r14.b;
        r2 = r14.e;
        r1.b(r2);
    L_0x0216:
        r1 = r14.d;
        r2 = r14.e;
        r1.remove(r2);
        r1 = r14.d;
        r2 = r14.f;
        r1.remove(r2);
        goto L_0x015c;
    L_0x0226:
        r1 = r14.d;
        r2 = r14.e;
        r1.remove(r2);
        r1 = r14.d;
        r2 = r14.f;
        r1.remove(r2);
        goto L_0x015c;
    L_0x0236:
        r0 = r14.b;
        if (r0 == 0) goto L_0x0241;
    L_0x023a:
        r0 = r14.b;
        r3 = r14.e;
        r0.b(r3);
    L_0x0241:
        r0 = r14.d;
        r3 = r14.e;
        r0.remove(r3);
        r0 = r14.d;
        r3 = r14.f;
        r0.remove(r3);
        goto L_0x01f4;
    L_0x0250:
        r0 = r14.d;
        r3 = r14.e;
        r0.remove(r3);
        r0 = r14.d;
        r3 = r14.f;
        r0.remove(r3);
        goto L_0x01f4;
    L_0x025f:
        r0 = move-exception;
        r3 = r4;
        r2 = r4;
    L_0x0262:
        r1 = com.budejie.www.download.f$1.a;
        r5 = r14.a;
        r5 = r5.ordinal();
        r1 = r1[r5];
        switch(r1) {
            case 1: goto L_0x027f;
            case 2: goto L_0x026f;
            case 3: goto L_0x0299;
            default: goto L_0x026f;
        };
    L_0x026f:
        if (r2 == 0) goto L_0x0274;
    L_0x0271:
        r2.disconnect();
    L_0x0274:
        if (r3 == 0) goto L_0x0279;
    L_0x0276:
        r3.close();	 Catch:{ IOException -> 0x02a8 }
    L_0x0279:
        if (r4 == 0) goto L_0x027e;
    L_0x027b:
        r4.close();	 Catch:{ IOException -> 0x02a8 }
    L_0x027e:
        throw r0;
    L_0x027f:
        r1 = r14.b;
        if (r1 == 0) goto L_0x028a;
    L_0x0283:
        r1 = r14.b;
        r5 = r14.e;
        r1.b(r5);
    L_0x028a:
        r1 = r14.d;
        r5 = r14.e;
        r1.remove(r5);
        r1 = r14.d;
        r5 = r14.f;
        r1.remove(r5);
        goto L_0x026f;
    L_0x0299:
        r1 = r14.d;
        r5 = r14.e;
        r1.remove(r5);
        r1 = r14.d;
        r5 = r14.f;
        r1.remove(r5);
        goto L_0x026f;
    L_0x02a8:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x027e;
    L_0x02ad:
        r1 = move-exception;
        r3 = r4;
        r2 = r0;
        r0 = r1;
        goto L_0x0262;
    L_0x02b2:
        r1 = move-exception;
        r2 = r0;
        r0 = r1;
        goto L_0x0262;
    L_0x02b6:
        r1 = move-exception;
        r4 = r5;
        r2 = r0;
        r0 = r1;
        goto L_0x0262;
    L_0x02bb:
        r0 = move-exception;
        r3 = r1;
        goto L_0x0262;
    L_0x02be:
        r0 = move-exception;
        r1 = r4;
        r2 = r4;
        goto L_0x01e0;
    L_0x02c3:
        r1 = move-exception;
        r2 = r0;
        r0 = r1;
        r1 = r4;
        goto L_0x01e0;
    L_0x02c9:
        r1 = move-exception;
        r2 = r0;
        r0 = r1;
        r1 = r3;
        goto L_0x01e0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.budejie.www.download.f.run():void");
    }
}
