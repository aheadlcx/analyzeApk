package com.elves.update;

class d$2 extends Thread {
    final /* synthetic */ String a;
    final /* synthetic */ String b;
    final /* synthetic */ boolean c;
    final /* synthetic */ String d;
    final /* synthetic */ d e;

    d$2(d dVar, String str, String str2, boolean z, String str3) {
        this.e = dVar;
        this.a = str;
        this.b = str2;
        this.c = z;
        this.d = str3;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void run() {
        /*
        r14 = this;
        r0 = java.lang.System.currentTimeMillis();
        r0 = (int) r0;
        r7 = java.lang.Math.abs(r0);
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r1 = r14.a;
        r0 = r0.append(r1);
        r1 = "/";
        r0 = r0.append(r1);
        r3 = r0.toString();
        r0 = "";
        r0 = new java.io.File;
        r1 = r14.a;
        r0.<init>(r1);
        r1 = r0.exists();
        if (r1 != 0) goto L_0x0030;
    L_0x002d:
        r0.mkdirs();
    L_0x0030:
        r1 = 0;
        r4 = 0;
        r2 = 0;
        r0 = r14.e;	 Catch:{ MalformedURLException -> 0x0347, IOException -> 0x01c8, Exception -> 0x023b }
        r5 = r14.b;	 Catch:{ MalformedURLException -> 0x0347, IOException -> 0x01c8, Exception -> 0x023b }
        r0.c = r5;	 Catch:{ MalformedURLException -> 0x0347, IOException -> 0x01c8, Exception -> 0x023b }
        r0 = r14.e;	 Catch:{ MalformedURLException -> 0x0347, IOException -> 0x01c8, Exception -> 0x023b }
        r5 = r14.c;	 Catch:{ MalformedURLException -> 0x0347, IOException -> 0x01c8, Exception -> 0x023b }
        r0.d = r5;	 Catch:{ MalformedURLException -> 0x0347, IOException -> 0x01c8, Exception -> 0x023b }
        r0 = new java.net.URL;	 Catch:{ MalformedURLException -> 0x0347, IOException -> 0x01c8, Exception -> 0x023b }
        r5 = r14.d;	 Catch:{ MalformedURLException -> 0x0347, IOException -> 0x01c8, Exception -> 0x023b }
        r0.<init>(r5);	 Catch:{ MalformedURLException -> 0x0347, IOException -> 0x01c8, Exception -> 0x023b }
        r0 = r0.openConnection();	 Catch:{ MalformedURLException -> 0x0347, IOException -> 0x01c8, Exception -> 0x023b }
        r0 = (java.net.HttpURLConnection) r0;	 Catch:{ MalformedURLException -> 0x0347, IOException -> 0x01c8, Exception -> 0x023b }
        r1 = 30000; // 0x7530 float:4.2039E-41 double:1.4822E-319;
        r0.setConnectTimeout(r1);	 Catch:{ MalformedURLException -> 0x034f, IOException -> 0x0332, Exception -> 0x031d, all -> 0x0304 }
        r0.connect();	 Catch:{ MalformedURLException -> 0x034f, IOException -> 0x0332, Exception -> 0x031d, all -> 0x0304 }
        r2 = r0.getInputStream();	 Catch:{ MalformedURLException -> 0x034f, IOException -> 0x0332, Exception -> 0x031d, all -> 0x0304 }
        r8 = r0.getContentLength();	 Catch:{ MalformedURLException -> 0x0358, IOException -> 0x0332, Exception -> 0x031d, all -> 0x0304 }
        r6 = 0;
        r1 = new java.lang.StringBuilder;	 Catch:{ MalformedURLException -> 0x0358, IOException -> 0x0332, Exception -> 0x031d, all -> 0x0304 }
        r1.<init>();	 Catch:{ MalformedURLException -> 0x0358, IOException -> 0x0332, Exception -> 0x031d, all -> 0x0304 }
        r5 = "不得姐";
        r1 = r1.append(r5);	 Catch:{ MalformedURLException -> 0x0358, IOException -> 0x0332, Exception -> 0x031d, all -> 0x0304 }
        r5 = new java.util.Date;	 Catch:{ MalformedURLException -> 0x0358, IOException -> 0x0332, Exception -> 0x031d, all -> 0x0304 }
        r5.<init>();	 Catch:{ MalformedURLException -> 0x0358, IOException -> 0x0332, Exception -> 0x031d, all -> 0x0304 }
        r10 = r5.getTime();	 Catch:{ MalformedURLException -> 0x0358, IOException -> 0x0332, Exception -> 0x031d, all -> 0x0304 }
        r1 = r1.append(r10);	 Catch:{ MalformedURLException -> 0x0358, IOException -> 0x0332, Exception -> 0x031d, all -> 0x0304 }
        r5 = ".mp4";
        r1 = r1.append(r5);	 Catch:{ MalformedURLException -> 0x0358, IOException -> 0x0332, Exception -> 0x031d, all -> 0x0304 }
        r9 = r1.toString();	 Catch:{ MalformedURLException -> 0x0358, IOException -> 0x0332, Exception -> 0x031d, all -> 0x0304 }
        r1 = new java.lang.StringBuilder;	 Catch:{ MalformedURLException -> 0x0358, IOException -> 0x0332, Exception -> 0x031d, all -> 0x0304 }
        r1.<init>();	 Catch:{ MalformedURLException -> 0x0358, IOException -> 0x0332, Exception -> 0x031d, all -> 0x0304 }
        r1 = r1.append(r3);	 Catch:{ MalformedURLException -> 0x0358, IOException -> 0x0332, Exception -> 0x031d, all -> 0x0304 }
        r1 = r1.append(r9);	 Catch:{ MalformedURLException -> 0x0358, IOException -> 0x0332, Exception -> 0x031d, all -> 0x0304 }
        r5 = r1.toString();	 Catch:{ MalformedURLException -> 0x0358, IOException -> 0x0332, Exception -> 0x031d, all -> 0x0304 }
        r3 = new java.io.FileOutputStream;	 Catch:{ MalformedURLException -> 0x0361, IOException -> 0x0338, Exception -> 0x0323, all -> 0x0309 }
        r1 = new java.io.File;	 Catch:{ MalformedURLException -> 0x0361, IOException -> 0x0338, Exception -> 0x0323, all -> 0x0309 }
        r1.<init>(r5);	 Catch:{ MalformedURLException -> 0x0361, IOException -> 0x0338, Exception -> 0x0323, all -> 0x0309 }
        r3.<init>(r1);	 Catch:{ MalformedURLException -> 0x0361, IOException -> 0x0338, Exception -> 0x0323, all -> 0x0309 }
        r1 = 1024; // 0x400 float:1.435E-42 double:5.06E-321;
        r10 = new byte[r1];	 Catch:{ MalformedURLException -> 0x00ff, IOException -> 0x033f, Exception -> 0x032a, all -> 0x030f }
        r1 = 0;
        r4 = r6;
    L_0x00a0:
        r6 = r2.read(r10);	 Catch:{ MalformedURLException -> 0x00ff, IOException -> 0x033f, Exception -> 0x032a, all -> 0x030f }
        r11 = -1;
        if (r6 == r11) goto L_0x016d;
    L_0x00a7:
        r4 = r4 + r6;
        r11 = 0;
        r3.write(r10, r11, r6);	 Catch:{ MalformedURLException -> 0x00ff, IOException -> 0x033f, Exception -> 0x032a, all -> 0x030f }
        r3.flush();	 Catch:{ MalformedURLException -> 0x00ff, IOException -> 0x033f, Exception -> 0x032a, all -> 0x030f }
        r1 = r1 + 1;
        r6 = 50;
        if (r1 <= r6) goto L_0x00a0;
    L_0x00b5:
        r1 = r14.e;	 Catch:{ MalformedURLException -> 0x00ff, IOException -> 0x033f, Exception -> 0x032a, all -> 0x030f }
        r1 = r1.e;	 Catch:{ MalformedURLException -> 0x00ff, IOException -> 0x033f, Exception -> 0x032a, all -> 0x030f }
        r6 = 777777; // 0xbde31 float:1.089898E-39 double:3.84273E-318;
        r11 = new java.lang.StringBuilder;	 Catch:{ MalformedURLException -> 0x00ff, IOException -> 0x033f, Exception -> 0x032a, all -> 0x030f }
        r11.<init>();	 Catch:{ MalformedURLException -> 0x00ff, IOException -> 0x033f, Exception -> 0x032a, all -> 0x030f }
        r11 = r11.append(r4);	 Catch:{ MalformedURLException -> 0x00ff, IOException -> 0x033f, Exception -> 0x032a, all -> 0x030f }
        r12 = "&";
        r11 = r11.append(r12);	 Catch:{ MalformedURLException -> 0x00ff, IOException -> 0x033f, Exception -> 0x032a, all -> 0x030f }
        r11 = r11.append(r8);	 Catch:{ MalformedURLException -> 0x00ff, IOException -> 0x033f, Exception -> 0x032a, all -> 0x030f }
        r12 = "&";
        r11 = r11.append(r12);	 Catch:{ MalformedURLException -> 0x00ff, IOException -> 0x033f, Exception -> 0x032a, all -> 0x030f }
        r11 = r11.append(r9);	 Catch:{ MalformedURLException -> 0x00ff, IOException -> 0x033f, Exception -> 0x032a, all -> 0x030f }
        r12 = "&";
        r11 = r11.append(r12);	 Catch:{ MalformedURLException -> 0x00ff, IOException -> 0x033f, Exception -> 0x032a, all -> 0x030f }
        r11 = r11.append(r7);	 Catch:{ MalformedURLException -> 0x00ff, IOException -> 0x033f, Exception -> 0x032a, all -> 0x030f }
        r11 = r11.toString();	 Catch:{ MalformedURLException -> 0x00ff, IOException -> 0x033f, Exception -> 0x032a, all -> 0x030f }
        r1 = r1.obtainMessage(r6, r11);	 Catch:{ MalformedURLException -> 0x00ff, IOException -> 0x033f, Exception -> 0x032a, all -> 0x030f }
        r6 = r14.c;	 Catch:{ MalformedURLException -> 0x00ff, IOException -> 0x033f, Exception -> 0x032a, all -> 0x030f }
        if (r6 == 0) goto L_0x00fb;
    L_0x00ef:
        r6 = 1;
        r1.arg1 = r6;	 Catch:{ MalformedURLException -> 0x00ff, IOException -> 0x033f, Exception -> 0x032a, all -> 0x030f }
    L_0x00f2:
        r6 = r14.e;	 Catch:{ MalformedURLException -> 0x00ff, IOException -> 0x033f, Exception -> 0x032a, all -> 0x030f }
        r6 = r6.e;	 Catch:{ MalformedURLException -> 0x00ff, IOException -> 0x033f, Exception -> 0x032a, all -> 0x030f }
        r6.sendMessage(r1);	 Catch:{ MalformedURLException -> 0x00ff, IOException -> 0x033f, Exception -> 0x032a, all -> 0x030f }
        r1 = 0;
        goto L_0x00a0;
    L_0x00fb:
        r6 = 0;
        r1.arg1 = r6;	 Catch:{ MalformedURLException -> 0x00ff, IOException -> 0x033f, Exception -> 0x032a, all -> 0x030f }
        goto L_0x00f2;
    L_0x00ff:
        r1 = move-exception;
        r4 = r5;
        r13 = r2;
        r2 = r3;
        r3 = r0;
        r0 = r1;
        r1 = r13;
    L_0x0106:
        r5 = "download";
        r6 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0316 }
        r6.<init>();	 Catch:{ all -> 0x0316 }
        r8 = "MalformedURLException :";
        r6 = r6.append(r8);	 Catch:{ all -> 0x0316 }
        r0 = r0.getMessage();	 Catch:{ all -> 0x0316 }
        r0 = r6.append(r0);	 Catch:{ all -> 0x0316 }
        r0 = r0.toString();	 Catch:{ all -> 0x0316 }
        com.budejie.www.util.aa.e(r5, r0);	 Catch:{ all -> 0x0316 }
        r0 = r14.e;
        r0 = r0.e;
        r5 = 888888; // 0xd9038 float:1.245597E-39 double:4.39169E-318;
        r6 = new java.lang.StringBuilder;
        r6.<init>();
        r4 = r6.append(r4);
        r6 = "&";
        r4 = r4.append(r6);
        r4 = r4.append(r7);
        r6 = "&";
        r4 = r4.append(r6);
        r6 = r14.d;
        r4 = r4.append(r6);
        r4 = r4.toString();
        r0 = r0.obtainMessage(r5, r4);
        r4 = r14.e;
        r4 = r4.e;
        r4.sendMessage(r0);
        r3.disconnect();	 Catch:{ Exception -> 0x01be }
    L_0x015a:
        if (r1 == 0) goto L_0x015f;
    L_0x015c:
        r1.close();	 Catch:{ IOException -> 0x01c3 }
    L_0x015f:
        if (r2 == 0) goto L_0x0164;
    L_0x0161:
        r2.close();	 Catch:{ IOException -> 0x01c3 }
    L_0x0164:
        r0 = r14.e;
        r0 = r0.e;
        r1 = 0;
        r0.sendEmptyMessage(r1);
    L_0x016c:
        return;
    L_0x016d:
        r1 = r14.c;
        if (r1 == 0) goto L_0x01b0;
    L_0x0171:
        r1 = 666666; // 0xa2c2a float:9.34198E-40 double:3.29377E-318;
    L_0x0174:
        r4 = r14.e;
        r4 = r4.e;
        r6 = new java.lang.StringBuilder;
        r6.<init>();
        r5 = r6.append(r5);
        r6 = "&";
        r5 = r5.append(r6);
        r5 = r5.append(r7);
        r5 = r5.toString();
        r1 = r4.obtainMessage(r1, r5);
        r4 = r14.e;
        r4 = r4.e;
        r4.sendMessage(r1);
        r0.disconnect();	 Catch:{ Exception -> 0x01b4 }
    L_0x019d:
        if (r2 == 0) goto L_0x01a2;
    L_0x019f:
        r2.close();	 Catch:{ IOException -> 0x01b9 }
    L_0x01a2:
        if (r3 == 0) goto L_0x01a7;
    L_0x01a4:
        r3.close();	 Catch:{ IOException -> 0x01b9 }
    L_0x01a7:
        r0 = r14.e;
        r0 = r0.e;
        r1 = 0;
        r0.sendEmptyMessage(r1);
        goto L_0x016c;
    L_0x01b0:
        r1 = 999999; // 0xf423f float:1.401297E-39 double:4.94065E-318;
        goto L_0x0174;
    L_0x01b4:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x019d;
    L_0x01b9:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x01a7;
    L_0x01be:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x015a;
    L_0x01c3:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x0164;
    L_0x01c8:
        r0 = move-exception;
    L_0x01c9:
        r5 = "download";
        r6 = new java.lang.StringBuilder;	 Catch:{ all -> 0x02ae }
        r6.<init>();	 Catch:{ all -> 0x02ae }
        r8 = "IoException : ";
        r6 = r6.append(r8);	 Catch:{ all -> 0x02ae }
        r0 = r0.getMessage();	 Catch:{ all -> 0x02ae }
        r0 = r6.append(r0);	 Catch:{ all -> 0x02ae }
        r0 = r0.toString();	 Catch:{ all -> 0x02ae }
        com.budejie.www.util.aa.e(r5, r0);	 Catch:{ all -> 0x02ae }
        r0 = r14.e;
        r0 = r0.e;
        r5 = 888888; // 0xd9038 float:1.245597E-39 double:4.39169E-318;
        r6 = new java.lang.StringBuilder;
        r6.<init>();
        r3 = r6.append(r3);
        r6 = "&";
        r3 = r3.append(r6);
        r3 = r3.append(r7);
        r6 = "&";
        r3 = r3.append(r6);
        r6 = r14.d;
        r3 = r3.append(r6);
        r3 = r3.toString();
        r0 = r0.obtainMessage(r5, r3);
        r3 = r14.e;
        r3 = r3.e;
        r3.sendMessage(r0);
        r1.disconnect();	 Catch:{ Exception -> 0x0231 }
    L_0x021d:
        if (r2 == 0) goto L_0x0222;
    L_0x021f:
        r2.close();	 Catch:{ IOException -> 0x0236 }
    L_0x0222:
        if (r4 == 0) goto L_0x0227;
    L_0x0224:
        r4.close();	 Catch:{ IOException -> 0x0236 }
    L_0x0227:
        r0 = r14.e;
        r0 = r0.e;
        r1 = 0;
        r0.sendEmptyMessage(r1);
        goto L_0x016c;
    L_0x0231:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x021d;
    L_0x0236:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x0227;
    L_0x023b:
        r0 = move-exception;
    L_0x023c:
        r5 = "download";
        r6 = new java.lang.StringBuilder;	 Catch:{ all -> 0x02ae }
        r6.<init>();	 Catch:{ all -> 0x02ae }
        r8 = "UnknownHostException : ";
        r6 = r6.append(r8);	 Catch:{ all -> 0x02ae }
        r0 = r0.getMessage();	 Catch:{ all -> 0x02ae }
        r0 = r6.append(r0);	 Catch:{ all -> 0x02ae }
        r0 = r0.toString();	 Catch:{ all -> 0x02ae }
        com.budejie.www.util.aa.e(r5, r0);	 Catch:{ all -> 0x02ae }
        r0 = r14.e;
        r0 = r0.e;
        r5 = 888888; // 0xd9038 float:1.245597E-39 double:4.39169E-318;
        r6 = new java.lang.StringBuilder;
        r6.<init>();
        r3 = r6.append(r3);
        r6 = "&";
        r3 = r3.append(r6);
        r3 = r3.append(r7);
        r6 = "&";
        r3 = r3.append(r6);
        r6 = r14.d;
        r3 = r3.append(r6);
        r3 = r3.toString();
        r0 = r0.obtainMessage(r5, r3);
        r3 = r14.e;
        r3 = r3.e;
        r3.sendMessage(r0);
        r1.disconnect();	 Catch:{ Exception -> 0x02a4 }
    L_0x0290:
        if (r2 == 0) goto L_0x0295;
    L_0x0292:
        r2.close();	 Catch:{ IOException -> 0x02a9 }
    L_0x0295:
        if (r4 == 0) goto L_0x029a;
    L_0x0297:
        r4.close();	 Catch:{ IOException -> 0x02a9 }
    L_0x029a:
        r0 = r14.e;
        r0 = r0.e;
        r1 = 0;
        r0.sendEmptyMessage(r1);
        goto L_0x016c;
    L_0x02a4:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x0290;
    L_0x02a9:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x029a;
    L_0x02ae:
        r0 = move-exception;
    L_0x02af:
        r5 = r14.e;
        r5 = r5.e;
        r6 = 888888; // 0xd9038 float:1.245597E-39 double:4.39169E-318;
        r8 = new java.lang.StringBuilder;
        r8.<init>();
        r3 = r8.append(r3);
        r8 = "&";
        r3 = r3.append(r8);
        r3 = r3.append(r7);
        r7 = "&";
        r3 = r3.append(r7);
        r7 = r14.d;
        r3 = r3.append(r7);
        r3 = r3.toString();
        r3 = r5.obtainMessage(r6, r3);
        r5 = r14.e;
        r5 = r5.e;
        r5.sendMessage(r3);
        r1.disconnect();	 Catch:{ Exception -> 0x02fa }
    L_0x02e7:
        if (r2 == 0) goto L_0x02ec;
    L_0x02e9:
        r2.close();	 Catch:{ IOException -> 0x02ff }
    L_0x02ec:
        if (r4 == 0) goto L_0x02f1;
    L_0x02ee:
        r4.close();	 Catch:{ IOException -> 0x02ff }
    L_0x02f1:
        r1 = r14.e;
        r1 = r1.e;
        r2 = 0;
        r1.sendEmptyMessage(r2);
        throw r0;
    L_0x02fa:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x02e7;
    L_0x02ff:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x02f1;
    L_0x0304:
        r1 = move-exception;
        r13 = r1;
        r1 = r0;
        r0 = r13;
        goto L_0x02af;
    L_0x0309:
        r1 = move-exception;
        r3 = r5;
        r13 = r0;
        r0 = r1;
        r1 = r13;
        goto L_0x02af;
    L_0x030f:
        r1 = move-exception;
        r4 = r3;
        r3 = r5;
        r13 = r0;
        r0 = r1;
        r1 = r13;
        goto L_0x02af;
    L_0x0316:
        r0 = move-exception;
        r13 = r1;
        r1 = r3;
        r3 = r4;
        r4 = r2;
        r2 = r13;
        goto L_0x02af;
    L_0x031d:
        r1 = move-exception;
        r13 = r1;
        r1 = r0;
        r0 = r13;
        goto L_0x023c;
    L_0x0323:
        r1 = move-exception;
        r3 = r5;
        r13 = r0;
        r0 = r1;
        r1 = r13;
        goto L_0x023c;
    L_0x032a:
        r1 = move-exception;
        r4 = r3;
        r3 = r5;
        r13 = r0;
        r0 = r1;
        r1 = r13;
        goto L_0x023c;
    L_0x0332:
        r1 = move-exception;
        r13 = r1;
        r1 = r0;
        r0 = r13;
        goto L_0x01c9;
    L_0x0338:
        r1 = move-exception;
        r3 = r5;
        r13 = r0;
        r0 = r1;
        r1 = r13;
        goto L_0x01c9;
    L_0x033f:
        r1 = move-exception;
        r4 = r3;
        r3 = r5;
        r13 = r0;
        r0 = r1;
        r1 = r13;
        goto L_0x01c9;
    L_0x0347:
        r0 = move-exception;
        r13 = r2;
        r2 = r4;
        r4 = r3;
        r3 = r1;
        r1 = r13;
        goto L_0x0106;
    L_0x034f:
        r1 = move-exception;
        r13 = r1;
        r1 = r2;
        r2 = r4;
        r4 = r3;
        r3 = r0;
        r0 = r13;
        goto L_0x0106;
    L_0x0358:
        r1 = move-exception;
        r13 = r1;
        r1 = r2;
        r2 = r4;
        r4 = r3;
        r3 = r0;
        r0 = r13;
        goto L_0x0106;
    L_0x0361:
        r1 = move-exception;
        r3 = r0;
        r0 = r1;
        r1 = r2;
        r2 = r4;
        r4 = r5;
        goto L_0x0106;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.elves.update.d$2.run():void");
    }
}
