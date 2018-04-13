package com.baidu.mobads.g;

import android.os.Handler;
import android.os.Looper;

class i extends Handler {
    final /* synthetic */ g a;

    i(g gVar, Looper looper) {
        this.a = gVar;
        super(looper);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void handleMessage(android.os.Message r8) {
        /*
        r7 = this;
        r3 = 2;
        r6 = 1;
        r5 = 0;
        r0 = r8.getData();
        r1 = "CODE";
        r1 = r0.getString(r1);
        r0 = r8.getData();
        r2 = "APK_INFO";
        r0 = r0.getParcelable(r2);
        r0 = (com.baidu.mobads.g.e) r0;
        r2 = "OK";
        r2 = r2.equals(r1);
        if (r2 == 0) goto L_0x00c2;
    L_0x0021:
        r1 = new com.baidu.mobads.g.b;
        r2 = r0.e();
        r3 = r7.a;
        r3 = com.baidu.mobads.g.g.a(r3);
        r1.<init>(r2, r3, r0);
        r2 = r7.a;	 Catch:{ g$a -> 0x0087 }
        r2 = r2.g;	 Catch:{ g$a -> 0x0087 }
        r3 = com.baidu.mobads.g.g.f;	 Catch:{ g$a -> 0x0087 }
        if (r2 != r3) goto L_0x0072;
    L_0x0038:
        r1.a();	 Catch:{ g$a -> 0x0087 }
        r2 = com.baidu.mobads.g.g.f();	 Catch:{ g$a -> 0x0087 }
        r1.a(r2);	 Catch:{ g$a -> 0x0087 }
        r2 = com.baidu.mobads.g.g.b;	 Catch:{ g$a -> 0x0087 }
        if (r2 == 0) goto L_0x004e;
    L_0x0046:
        r2 = com.baidu.mobads.g.g.b;	 Catch:{ g$a -> 0x0087 }
        r4 = r0.b();	 Catch:{ g$a -> 0x0087 }
        r2.a = r4;	 Catch:{ g$a -> 0x0087 }
    L_0x004e:
        r0 = r7.a;	 Catch:{ g$a -> 0x0087 }
        com.baidu.mobads.g.g.b(r0);	 Catch:{ g$a -> 0x0087 }
        r0 = r7.a;	 Catch:{ g$a -> 0x0087 }
        r0 = com.baidu.mobads.g.g.c(r0);	 Catch:{ g$a -> 0x0087 }
        if (r0 == 0) goto L_0x006e;
    L_0x005b:
        r0 = r7.a;	 Catch:{ g$a -> 0x0087 }
        r2 = 0;
        com.baidu.mobads.g.g.a(r0, r2);	 Catch:{ g$a -> 0x0087 }
        r0 = r7.a;	 Catch:{ g$a -> 0x0087 }
        r2 = r7.a;	 Catch:{ g$a -> 0x0087 }
        r2 = com.baidu.mobads.g.g.d(r2);	 Catch:{ g$a -> 0x0087 }
        r3 = "load remote file just downloaded";
        com.baidu.mobads.g.g.a(r0, r2, r3);	 Catch:{ g$a -> 0x0087 }
    L_0x006e:
        r1.delete();
    L_0x0071:
        return;
    L_0x0072:
        r0 = r7.a;	 Catch:{ g$a -> 0x0087 }
        com.baidu.mobads.g.g.a(r0, r1);	 Catch:{ g$a -> 0x0087 }
        r0 = r7.a;	 Catch:{ g$a -> 0x0087 }
        r0 = com.baidu.mobads.g.g.f();	 Catch:{ g$a -> 0x0087 }
        r1.a(r0);	 Catch:{ g$a -> 0x0087 }
        r0 = r7.a;	 Catch:{ g$a -> 0x0087 }
        r2 = 1;
        com.baidu.mobads.g.g.b(r0, r2);	 Catch:{ g$a -> 0x0087 }
        goto L_0x006e;
    L_0x0087:
        r0 = move-exception;
        r2 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00bd }
        r2.<init>();	 Catch:{ all -> 0x00bd }
        r3 = "download apk file failed: ";
        r2 = r2.append(r3);	 Catch:{ all -> 0x00bd }
        r0 = r0.toString();	 Catch:{ all -> 0x00bd }
        r0 = r2.append(r0);	 Catch:{ all -> 0x00bd }
        r0 = r0.toString();	 Catch:{ all -> 0x00bd }
        r2 = r7.a;	 Catch:{ all -> 0x00bd }
        r3 = 0;
        com.baidu.mobads.g.g.b(r2, r3);	 Catch:{ all -> 0x00bd }
        r2 = r7.a;	 Catch:{ all -> 0x00bd }
        r2 = com.baidu.mobads.g.g.e(r2);	 Catch:{ all -> 0x00bd }
        r3 = 2;
        r3 = new java.lang.Object[r3];	 Catch:{ all -> 0x00bd }
        r4 = 0;
        r5 = "XAdApkLoader";
        r3[r4] = r5;	 Catch:{ all -> 0x00bd }
        r4 = 1;
        r3[r4] = r0;	 Catch:{ all -> 0x00bd }
        r2.e(r3);	 Catch:{ all -> 0x00bd }
        r1.delete();
        goto L_0x0071;
    L_0x00bd:
        r0 = move-exception;
        r1.delete();
        throw r0;
    L_0x00c2:
        r0 = r7.a;
        r0 = com.baidu.mobads.g.g.e(r0);
        r2 = new java.lang.Object[r3];
        r3 = "XAdApkLoader";
        r2[r5] = r3;
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r4 = "mOnApkDownloadCompleted: download failed, code: ";
        r3 = r3.append(r4);
        r1 = r3.append(r1);
        r1 = r1.toString();
        r2[r6] = r1;
        r0.e(r2);
        r0 = r7.a;
        com.baidu.mobads.g.g.b(r0, r5);
        r0 = r7.a;
        r0 = com.baidu.mobads.g.g.c(r0);
        if (r0 == 0) goto L_0x0071;
    L_0x00f3:
        r0 = r7.a;
        com.baidu.mobads.g.g.a(r0, r5);
        r0 = r7.a;
        r1 = "Refused to download remote for version...";
        com.baidu.mobads.g.g.a(r0, r5, r1);
        goto L_0x0071;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.mobads.g.i.handleMessage(android.os.Message):void");
    }
}
