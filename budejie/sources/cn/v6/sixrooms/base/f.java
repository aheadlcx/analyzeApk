package cn.v6.sixrooms.base;

import cn.v6.sixrooms.base.VLHttpClient.VLHttpFileDownloadListener;
import java.io.InputStream;
import java.io.OutputStream;

final class f implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ VLAsyncHandler b;
    final /* synthetic */ a c;
    final /* synthetic */ String d;
    final /* synthetic */ boolean e;
    final /* synthetic */ int f;
    final /* synthetic */ VLHttpFileDownloadListener g;
    final /* synthetic */ VLHttpClient h;

    f(VLHttpClient vLHttpClient, String str, VLAsyncHandler vLAsyncHandler, a aVar, String str2, boolean z, int i, VLHttpFileDownloadListener vLHttpFileDownloadListener) {
        this.h = vLHttpClient;
        this.a = str;
        this.b = vLAsyncHandler;
        this.c = aVar;
        this.d = str2;
        this.e = z;
        this.f = i;
        this.g = vLHttpFileDownloadListener;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void run() {
        /*
        r9 = this;
        r2 = 0;
        r8 = 0;
        r1 = r9.a;	 Catch:{ Exception -> 0x01d8, all -> 0x01c1 }
        r4 = cn.v6.sixrooms.base.SixRoomsUtils.fileOpenToWrite(r1);	 Catch:{ Exception -> 0x01d8, all -> 0x01c1 }
        if (r4 != 0) goto L_0x0031;
    L_0x000a:
        r2 = 3;
        r1 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x01dd, all -> 0x01c1 }
        r3 = "open output file failed : ";
        r1.<init>(r3);	 Catch:{ Exception -> 0x01dd, all -> 0x01c1 }
        r3 = r9.a;	 Catch:{ Exception -> 0x01dd, all -> 0x01c1 }
        r1 = r1.append(r3);	 Catch:{ Exception -> 0x01dd, all -> 0x01c1 }
        r3 = r1.toString();	 Catch:{ Exception -> 0x01dd, all -> 0x01c1 }
        r5 = 0;
        r6 = r9.b;	 Catch:{ Exception -> 0x01dd, all -> 0x01c1 }
        r1 = r9;
        r1.a(r2, r3, r4, r5, r6);	 Catch:{ Exception -> 0x01dd, all -> 0x01c1 }
        r1 = r9.h;
        r1 = r1.b;
        r2 = r9.c;
        r2 = r2.a;
        r1.remove(r2);
    L_0x0030:
        return;
    L_0x0031:
        r1 = r9.c;	 Catch:{ Exception -> 0x01dd, all -> 0x01c1 }
        r1 = r1.c;	 Catch:{ Exception -> 0x01dd, all -> 0x01c1 }
        if (r1 == 0) goto L_0x004f;
    L_0x0037:
        r2 = 1;
        r3 = "user canceled";
        r5 = 0;
        r6 = r9.b;	 Catch:{ Exception -> 0x01dd, all -> 0x01c1 }
        r1 = r9;
        r1.a(r2, r3, r4, r5, r6);	 Catch:{ Exception -> 0x01dd, all -> 0x01c1 }
        r1 = r9.h;
        r1 = r1.b;
        r2 = r9.c;
        r2 = r2.a;
        r1.remove(r2);
        goto L_0x0030;
    L_0x004f:
        r1 = r9.c;	 Catch:{ Exception -> 0x01dd, all -> 0x01c1 }
        r3 = 1;
        r1.b = r3;	 Catch:{ Exception -> 0x01dd, all -> 0x01c1 }
        r1 = new java.net.URL;	 Catch:{ Exception -> 0x01dd, all -> 0x01c1 }
        r3 = r9.d;	 Catch:{ Exception -> 0x01dd, all -> 0x01c1 }
        r1.<init>(r3);	 Catch:{ Exception -> 0x01dd, all -> 0x01c1 }
        r1 = r1.openConnection();	 Catch:{ Exception -> 0x01dd, all -> 0x01c1 }
        r0 = r1;
        r0 = (java.net.HttpURLConnection) r0;	 Catch:{ Exception -> 0x01dd, all -> 0x01c1 }
        r7 = r0;
        r1 = 0;
        r7.setDoOutput(r1);	 Catch:{ Exception -> 0x01e1 }
        r1 = 1;
        r7.setDoInput(r1);	 Catch:{ Exception -> 0x01e1 }
        r1 = r9.e;	 Catch:{ Exception -> 0x01e1 }
        r7.setInstanceFollowRedirects(r1);	 Catch:{ Exception -> 0x01e1 }
        r1 = "User-Agent";
        r3 = r9.h;	 Catch:{ Exception -> 0x01e1 }
        r3 = r3.c;	 Catch:{ Exception -> 0x01e1 }
        r7.setRequestProperty(r1, r3);	 Catch:{ Exception -> 0x01e1 }
        r1 = "Accept";
        r3 = r9.h;	 Catch:{ Exception -> 0x01e1 }
        r3 = r3.d;	 Catch:{ Exception -> 0x01e1 }
        r7.setRequestProperty(r1, r3);	 Catch:{ Exception -> 0x01e1 }
        r1 = "Accept-Encoding";
        r3 = r9.h;	 Catch:{ Exception -> 0x01e1 }
        r3 = r3.e;	 Catch:{ Exception -> 0x01e1 }
        r7.setRequestProperty(r1, r3);	 Catch:{ Exception -> 0x01e1 }
        r1 = r9.c;	 Catch:{ Exception -> 0x01e1 }
        r1 = r1.c;	 Catch:{ Exception -> 0x01e1 }
        if (r1 == 0) goto L_0x00b5;
    L_0x0097:
        r2 = 1;
        r3 = "user canceled";
        r5 = 0;
        r6 = r9.b;	 Catch:{ Exception -> 0x01e1 }
        r1 = r9;
        r1.a(r2, r3, r4, r5, r6);	 Catch:{ Exception -> 0x01e1 }
        if (r7 == 0) goto L_0x00a6;
    L_0x00a3:
        r7.disconnect();
    L_0x00a6:
        r1 = r9.h;
        r1 = r1.b;
        r2 = r9.c;
        r2 = r2.a;
        r1.remove(r2);
        goto L_0x0030;
    L_0x00b5:
        r1 = r9.c;	 Catch:{ Exception -> 0x01e1 }
        r3 = 2;
        r1.b = r3;	 Catch:{ Exception -> 0x01e1 }
        r1 = r7.getResponseCode();	 Catch:{ Exception -> 0x01e1 }
        r3 = r7.getContentLength();	 Catch:{ Exception -> 0x01e1 }
        r5 = r7.getContentEncoding();	 Catch:{ Exception -> 0x01e1 }
        r5 = cn.v6.sixrooms.base.SixRoomsUtils.V(r5);	 Catch:{ Exception -> 0x01e1 }
        r5 = r5.trim();	 Catch:{ Exception -> 0x01e1 }
        r6 = java.util.Locale.getDefault();	 Catch:{ Exception -> 0x01e1 }
        r6 = r5.toLowerCase(r6);	 Catch:{ Exception -> 0x01e1 }
        r5 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        if (r1 == r5) goto L_0x0105;
    L_0x00da:
        r2 = 4;
        r3 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x01e1 }
        r5 = "server error : ";
        r3.<init>(r5);	 Catch:{ Exception -> 0x01e1 }
        r1 = r3.append(r1);	 Catch:{ Exception -> 0x01e1 }
        r3 = r1.toString();	 Catch:{ Exception -> 0x01e1 }
        r5 = 0;
        r6 = r9.b;	 Catch:{ Exception -> 0x01e1 }
        r1 = r9;
        r1.a(r2, r3, r4, r5, r6);	 Catch:{ Exception -> 0x01e1 }
        if (r7 == 0) goto L_0x00f6;
    L_0x00f3:
        r7.disconnect();
    L_0x00f6:
        r1 = r9.h;
        r1 = r1.b;
        r2 = r9.c;
        r2 = r2.a;
        r1.remove(r2);
        goto L_0x0030;
    L_0x0105:
        r1 = r9.c;	 Catch:{ Exception -> 0x01e1 }
        r1 = r1.c;	 Catch:{ Exception -> 0x01e1 }
        if (r1 == 0) goto L_0x0129;
    L_0x010b:
        r2 = 1;
        r3 = "user canceled";
        r5 = 0;
        r6 = r9.b;	 Catch:{ Exception -> 0x01e1 }
        r1 = r9;
        r1.a(r2, r3, r4, r5, r6);	 Catch:{ Exception -> 0x01e1 }
        if (r7 == 0) goto L_0x011a;
    L_0x0117:
        r7.disconnect();
    L_0x011a:
        r1 = r9.h;
        r1 = r1.b;
        r2 = r9.c;
        r2 = r2.a;
        r1.remove(r2);
        goto L_0x0030;
    L_0x0129:
        r1 = r9.c;	 Catch:{ Exception -> 0x01e1 }
        r5 = 3;
        r1.b = r5;	 Catch:{ Exception -> 0x01e1 }
        r5 = r7.getInputStream();	 Catch:{ Exception -> 0x01e1 }
        r1 = "gzip";
        r1 = r6.equals(r1);	 Catch:{ Exception -> 0x0161 }
        if (r1 == 0) goto L_0x0140;
    L_0x013a:
        r1 = new java.util.zip.GZIPInputStream;	 Catch:{ Exception -> 0x0161 }
        r1.<init>(r5);	 Catch:{ Exception -> 0x0161 }
        r5 = r1;
    L_0x0140:
        r1 = r9.f;	 Catch:{ Exception -> 0x0161 }
        r6 = new byte[r1];	 Catch:{ Exception -> 0x0161 }
        r1 = r2;
    L_0x0145:
        r2 = r9.c;	 Catch:{ Exception -> 0x0161 }
        r2 = r2.c;	 Catch:{ Exception -> 0x0161 }
        if (r2 != 0) goto L_0x0181;
    L_0x014b:
        r2 = r5.read(r6);	 Catch:{ Exception -> 0x0161 }
        r8 = -1;
        if (r2 == r8) goto L_0x0181;
    L_0x0152:
        r1 = r1 + r2;
        r8 = 0;
        r4.write(r6, r8, r2);	 Catch:{ Exception -> 0x0161 }
        r2 = r9.g;	 Catch:{ Exception -> 0x0161 }
        if (r2 == 0) goto L_0x0145;
    L_0x015b:
        r2 = r9.g;	 Catch:{ Exception -> 0x0161 }
        r2.onResProgress(r1, r3);	 Catch:{ Exception -> 0x0161 }
        goto L_0x0145;
    L_0x0161:
        r1 = move-exception;
    L_0x0162:
        r2 = 2;
        r3 = r1.getMessage();	 Catch:{ all -> 0x01d6 }
        r6 = r9.b;	 Catch:{ all -> 0x01d6 }
        r1 = r9;
        r1.a(r2, r3, r4, r5, r6);	 Catch:{ all -> 0x01d6 }
        if (r7 == 0) goto L_0x0172;
    L_0x016f:
        r7.disconnect();
    L_0x0172:
        r1 = r9.h;
        r1 = r1.b;
        r2 = r9.c;
        r2 = r2.a;
        r1.remove(r2);
        goto L_0x0030;
    L_0x0181:
        r1 = r9.c;	 Catch:{ Exception -> 0x0161 }
        r1 = r1.c;	 Catch:{ Exception -> 0x0161 }
        if (r1 == 0) goto L_0x01a4;
    L_0x0187:
        r2 = 1;
        r3 = "user canceled";
        r6 = r9.b;	 Catch:{ Exception -> 0x0161 }
        r1 = r9;
        r1.a(r2, r3, r4, r5, r6);	 Catch:{ Exception -> 0x0161 }
        if (r7 == 0) goto L_0x0195;
    L_0x0192:
        r7.disconnect();
    L_0x0195:
        r1 = r9.h;
        r1 = r1.b;
        r2 = r9.c;
        r2 = r2.a;
        r1.remove(r2);
        goto L_0x0030;
    L_0x01a4:
        r2 = 0;
        r3 = "succeed";
        r6 = r9.b;	 Catch:{ Exception -> 0x0161 }
        r1 = r9;
        r1.a(r2, r3, r4, r5, r6);	 Catch:{ Exception -> 0x0161 }
        if (r7 == 0) goto L_0x01b2;
    L_0x01af:
        r7.disconnect();
    L_0x01b2:
        r1 = r9.h;
        r1 = r1.b;
        r2 = r9.c;
        r2 = r2.a;
        r1.remove(r2);
        goto L_0x0030;
    L_0x01c1:
        r1 = move-exception;
        r7 = r8;
    L_0x01c3:
        if (r7 == 0) goto L_0x01c8;
    L_0x01c5:
        r7.disconnect();
    L_0x01c8:
        r2 = r9.h;
        r2 = r2.b;
        r3 = r9.c;
        r3 = r3.a;
        r2.remove(r3);
        throw r1;
    L_0x01d6:
        r1 = move-exception;
        goto L_0x01c3;
    L_0x01d8:
        r1 = move-exception;
        r5 = r8;
        r4 = r8;
        r7 = r8;
        goto L_0x0162;
    L_0x01dd:
        r1 = move-exception;
        r5 = r8;
        r7 = r8;
        goto L_0x0162;
    L_0x01e1:
        r1 = move-exception;
        r5 = r8;
        goto L_0x0162;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.v6.sixrooms.base.f.run():void");
    }

    private void a(int i, String str, OutputStream outputStream, InputStream inputStream, VLAsyncHandler<Object> vLAsyncHandler) {
        if (outputStream != null) {
            try {
                outputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (inputStream != null) {
            inputStream.close();
        }
        this.c.b = 4;
        if (i == 0) {
            if (vLAsyncHandler != null) {
                vLAsyncHandler.handlerSuccess();
            }
        } else if (i == 1) {
            if (vLAsyncHandler != null) {
                vLAsyncHandler.handlerError(VLAsyncHandler$VLAsyncRes.VLAsyncResCanceled, "user canceled");
            }
            if (this.c.d != null) {
                for (VLResHandler vLResHandler : this.c.d) {
                    if (vLResHandler != null) {
                        vLResHandler.handlerSuccess();
                    }
                }
            }
        } else if (vLAsyncHandler != null) {
            vLAsyncHandler.handlerError(VLAsyncHandler$VLAsyncRes.VLAsyncResFailed, str);
        }
    }
}
