package com.budejie.www.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.text.TextUtils;
import java.io.File;
import java.lang.ref.SoftReference;
import java.util.WeakHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class f {
    private WeakHashMap<String, SoftReference<Drawable>> a;
    private ExecutorService b = Executors.newFixedThreadPool(8);

    public interface b {
        void a(int i, String str);

        void a(Drawable drawable, String str);

        void b(int i, String str);
    }

    public interface a {
        void a(Drawable drawable, String str);
    }

    public f() {
        aa.c("AsyncImageLoader", "create asyncImageLoader ! this = " + this);
        this.a = new WeakHashMap();
    }

    public Drawable a(Context context, String str, a aVar) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (this.a.containsKey(str)) {
            Drawable drawable = (Drawable) ((SoftReference) this.a.get(str)).get();
            if (drawable != null) {
                aVar.a(drawable, str);
                return drawable;
            }
        }
        this.b.submit(new f$2(this, str, context, new f$1(this, aVar, str)));
        return null;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a(java.io.File r7, java.lang.String r8, android.os.Handler r9) {
        /*
        r6 = this;
        r0 = 0;
        r1 = new java.io.FileOutputStream;	 Catch:{ OutOfMemoryError -> 0x010b, IOException -> 0x0097, all -> 0x00cf }
        r1.<init>(r7);	 Catch:{ OutOfMemoryError -> 0x010b, IOException -> 0x0097, all -> 0x00cf }
        r2 = new org.apache.http.impl.client.DefaultHttpClient;	 Catch:{ OutOfMemoryError -> 0x003a, IOException -> 0x0109, all -> 0x00fd }
        r2.<init>();	 Catch:{ OutOfMemoryError -> 0x003a, IOException -> 0x0109, all -> 0x00fd }
        r3 = new org.apache.http.client.methods.HttpGet;	 Catch:{ OutOfMemoryError -> 0x003a, IOException -> 0x0109, all -> 0x00fd }
        r3.<init>(r8);	 Catch:{ OutOfMemoryError -> 0x003a, IOException -> 0x0109, all -> 0x00fd }
        r2 = r2.execute(r3);	 Catch:{ OutOfMemoryError -> 0x003a, IOException -> 0x0109, all -> 0x00fd }
        if (r2 == 0) goto L_0x0025;
    L_0x0016:
        r2 = r2.getEntity();	 Catch:{ OutOfMemoryError -> 0x003a, IOException -> 0x0109, all -> 0x00fd }
        if (r2 == 0) goto L_0x0025;
    L_0x001c:
        r3 = new org.apache.http.entity.BufferedHttpEntity;	 Catch:{ OutOfMemoryError -> 0x003a, IOException -> 0x0109, all -> 0x00fd }
        r3.<init>(r2);	 Catch:{ OutOfMemoryError -> 0x003a, IOException -> 0x0109, all -> 0x00fd }
        r0 = r3.getContent();	 Catch:{ OutOfMemoryError -> 0x003a, IOException -> 0x0109, all -> 0x00fd }
    L_0x0025:
        if (r0 == 0) goto L_0x0050;
    L_0x0027:
        r2 = 1024; // 0x400 float:1.435E-42 double:5.06E-321;
        r2 = new byte[r2];	 Catch:{ OutOfMemoryError -> 0x003a, IOException -> 0x0109 }
    L_0x002b:
        r3 = r0.read(r2);	 Catch:{ OutOfMemoryError -> 0x003a, IOException -> 0x0109 }
        r4 = -1;
        if (r3 == r4) goto L_0x0050;
    L_0x0032:
        r4 = 0;
        r1.write(r2, r4, r3);	 Catch:{ OutOfMemoryError -> 0x003a, IOException -> 0x0109 }
        r1.flush();	 Catch:{ OutOfMemoryError -> 0x003a, IOException -> 0x0109 }
        goto L_0x002b;
    L_0x003a:
        r2 = move-exception;
    L_0x003b:
        r7.delete();	 Catch:{ all -> 0x0103 }
        r2 = "AsyncImageLoader";
        r3 = "writeIconPngToLocal, OutOfMemoryError";
        com.budejie.www.util.aa.e(r2, r3);	 Catch:{ all -> 0x0103 }
        if (r1 == 0) goto L_0x004a;
    L_0x0047:
        r1.close();	 Catch:{ IOException -> 0x0079 }
    L_0x004a:
        if (r0 == 0) goto L_0x004f;
    L_0x004c:
        r0.close();	 Catch:{ IOException -> 0x0079 }
    L_0x004f:
        return;
    L_0x0050:
        if (r1 == 0) goto L_0x0055;
    L_0x0052:
        r1.close();	 Catch:{ IOException -> 0x005b }
    L_0x0055:
        if (r0 == 0) goto L_0x004f;
    L_0x0057:
        r0.close();	 Catch:{ IOException -> 0x005b }
        goto L_0x004f;
    L_0x005b:
        r0 = move-exception;
        r1 = "AsyncImageLoader";
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r0 = r0.toString();
        r0 = r2.append(r0);
        r2 = "流关闭异常";
        r0 = r0.append(r2);
        r0 = r0.toString();
        com.budejie.www.util.aa.e(r1, r0);
        goto L_0x004f;
    L_0x0079:
        r0 = move-exception;
        r1 = "AsyncImageLoader";
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r0 = r0.toString();
        r0 = r2.append(r0);
        r2 = "流关闭异常";
        r0 = r0.append(r2);
        r0 = r0.toString();
        com.budejie.www.util.aa.e(r1, r0);
        goto L_0x004f;
    L_0x0097:
        r1 = move-exception;
        r1 = r0;
    L_0x0099:
        r2 = r7.exists();	 Catch:{ all -> 0x0103 }
        if (r2 == 0) goto L_0x00a2;
    L_0x009f:
        r7.delete();	 Catch:{ all -> 0x0103 }
    L_0x00a2:
        r2 = 2;
        r9.sendEmptyMessage(r2);	 Catch:{ all -> 0x0103 }
        if (r1 == 0) goto L_0x00ab;
    L_0x00a8:
        r1.close();	 Catch:{ IOException -> 0x00b1 }
    L_0x00ab:
        if (r0 == 0) goto L_0x004f;
    L_0x00ad:
        r0.close();	 Catch:{ IOException -> 0x00b1 }
        goto L_0x004f;
    L_0x00b1:
        r0 = move-exception;
        r1 = "AsyncImageLoader";
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r0 = r0.toString();
        r0 = r2.append(r0);
        r2 = "流关闭异常";
        r0 = r0.append(r2);
        r0 = r0.toString();
        com.budejie.www.util.aa.e(r1, r0);
        goto L_0x004f;
    L_0x00cf:
        r1 = move-exception;
        r2 = r0;
        r5 = r0;
        r0 = r1;
        r1 = r5;
    L_0x00d4:
        if (r2 == 0) goto L_0x00d9;
    L_0x00d6:
        r2.close();	 Catch:{ IOException -> 0x00df }
    L_0x00d9:
        if (r1 == 0) goto L_0x00de;
    L_0x00db:
        r1.close();	 Catch:{ IOException -> 0x00df }
    L_0x00de:
        throw r0;
    L_0x00df:
        r1 = move-exception;
        r2 = "AsyncImageLoader";
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r1 = r1.toString();
        r1 = r3.append(r1);
        r3 = "流关闭异常";
        r1 = r1.append(r3);
        r1 = r1.toString();
        com.budejie.www.util.aa.e(r2, r1);
        goto L_0x00de;
    L_0x00fd:
        r2 = move-exception;
        r5 = r2;
        r2 = r1;
        r1 = r0;
        r0 = r5;
        goto L_0x00d4;
    L_0x0103:
        r2 = move-exception;
        r5 = r2;
        r2 = r1;
        r1 = r0;
        r0 = r5;
        goto L_0x00d4;
    L_0x0109:
        r2 = move-exception;
        goto L_0x0099;
    L_0x010b:
        r1 = move-exception;
        r1 = r0;
        goto L_0x003b;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.budejie.www.util.f.a(java.io.File, java.lang.String, android.os.Handler):void");
    }

    private void b(File file, String str, Handler handler) {
        String file2 = file.toString();
        try {
            Options options = new Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(file2, options);
            if (options.outWidth <= 0 || options.outHeight <= 0) {
                handler.sendEmptyMessage(2);
                return;
            }
            options.inJustDecodeBounds = false;
            options.inPreferredConfig = Config.RGB_565;
            options.inPurgeable = true;
            options.inInputShareable = true;
            Bitmap decodeFile = BitmapFactory.decodeFile(file2, options);
            BitmapDrawable bitmapDrawable = new BitmapDrawable(an.a(decodeFile));
            if (decodeFile == null || bitmapDrawable == null) {
                file.delete();
                an.a.remove(str);
                handler.sendEmptyMessage(2);
                return;
            }
            this.a.put(str, new SoftReference(bitmapDrawable));
            handler.sendMessage(handler.obtainMessage(1, bitmapDrawable));
        } catch (OutOfMemoryError e) {
        }
    }

    public synchronized Drawable a(Context context, String str, b bVar) {
        Drawable drawable;
        aa.c("AsyncImageLoader", "----->loadDrawable2, imageUrl = " + str);
        if (TextUtils.isEmpty(str)) {
            drawable = null;
        } else {
            if (this.a.containsKey(str)) {
                aa.c("AsyncImageLoader", "imageUrl has stored in Cache(WeakHashMap), imageUrl = " + str);
                drawable = (Drawable) ((SoftReference) this.a.get(str)).get();
                if (drawable != null) {
                    bVar.a(drawable, str);
                } else {
                    aa.c("AsyncImageLoader", "imageUrl has stored in Cache(WeakHashMap), but has been Gc");
                }
            }
            Handler f_3 = new f$3(this, bVar, str);
            String str2 = "";
            if (!TextUtils.isEmpty(str)) {
                str2 = str.substring(7).replace("/", "-");
            }
            if (TextUtils.isEmpty(str2)) {
                str2 = "test.jpg";
            }
            File file = new File(context.getCacheDir(), str2);
            if (!file.exists() || file.length() <= 10) {
                this.b.submit(new f$5(this, str, file, f_3));
            } else {
                aa.c("AsyncImageLoader", "image file is exits, try to load it!");
                new f$4(this, file, f_3, str).start();
            }
            drawable = null;
        }
        return drawable;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void c(java.io.File r11, java.lang.String r12, android.os.Handler r13) {
        /*
        r10 = this;
        r0 = 0;
        r1 = "AsyncImageLoader";
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r3 = "writePngToLocal(download from net), file = ";
        r2 = r2.append(r3);
        r2 = r2.append(r11);
        r2 = r2.toString();
        com.budejie.www.util.aa.c(r1, r2);
        r2 = 0;
        r1 = new java.io.FileOutputStream;	 Catch:{ OutOfMemoryError -> 0x01e2, IOException -> 0x01de, all -> 0x01a4 }
        r1.<init>(r11);	 Catch:{ OutOfMemoryError -> 0x01e2, IOException -> 0x01de, all -> 0x01a4 }
        r4 = new org.apache.http.impl.client.DefaultHttpClient;	 Catch:{ OutOfMemoryError -> 0x00c6, IOException -> 0x00fe, all -> 0x01d2 }
        r4.<init>();	 Catch:{ OutOfMemoryError -> 0x00c6, IOException -> 0x00fe, all -> 0x01d2 }
        r5 = new org.apache.http.client.methods.HttpGet;	 Catch:{ OutOfMemoryError -> 0x00c6, IOException -> 0x00fe, all -> 0x01d2 }
        r5.<init>(r12);	 Catch:{ OutOfMemoryError -> 0x00c6, IOException -> 0x00fe, all -> 0x01d2 }
        r6 = new android.os.Bundle;	 Catch:{ OutOfMemoryError -> 0x00c6, IOException -> 0x00fe, all -> 0x01d2 }
        r6.<init>();	 Catch:{ OutOfMemoryError -> 0x00c6, IOException -> 0x00fe, all -> 0x01d2 }
        r7 = "imgUrl";
        r6.putString(r7, r12);	 Catch:{ OutOfMemoryError -> 0x00c6, IOException -> 0x00fe, all -> 0x01d2 }
        r7 = 5;
        r6 = r13.obtainMessage(r7, r6);	 Catch:{ OutOfMemoryError -> 0x00c6, IOException -> 0x00fe, all -> 0x01d2 }
        r13.sendMessage(r6);	 Catch:{ OutOfMemoryError -> 0x00c6, IOException -> 0x00fe, all -> 0x01d2 }
        r4 = r4.execute(r5);	 Catch:{ OutOfMemoryError -> 0x00c6, IOException -> 0x00fe, all -> 0x01d2 }
        if (r4 == 0) goto L_0x008e;
    L_0x0042:
        r4 = r4.getEntity();	 Catch:{ OutOfMemoryError -> 0x00c6, IOException -> 0x00fe, all -> 0x01d2 }
        if (r4 == 0) goto L_0x00f6;
    L_0x0048:
        r5 = new org.apache.http.entity.BufferedHttpEntity;	 Catch:{ OutOfMemoryError -> 0x00c6, IOException -> 0x00fe, all -> 0x01d2 }
        r5.<init>(r4);	 Catch:{ OutOfMemoryError -> 0x00c6, IOException -> 0x00fe, all -> 0x01d2 }
        r0 = r5.getContent();	 Catch:{ OutOfMemoryError -> 0x00c6, IOException -> 0x00fe, all -> 0x01d2 }
        r4 = r5.getContentLength();	 Catch:{ OutOfMemoryError -> 0x00c6, IOException -> 0x00fe }
        r6 = "AsyncImageLoader";
        r7 = new java.lang.StringBuilder;	 Catch:{ OutOfMemoryError -> 0x00c6, IOException -> 0x00fe }
        r7.<init>();	 Catch:{ OutOfMemoryError -> 0x00c6, IOException -> 0x00fe }
        r8 = "file size = ";
        r7 = r7.append(r8);	 Catch:{ OutOfMemoryError -> 0x00c6, IOException -> 0x00fe }
        r7 = r7.append(r4);	 Catch:{ OutOfMemoryError -> 0x00c6, IOException -> 0x00fe }
        r8 = ", file = ";
        r7 = r7.append(r8);	 Catch:{ OutOfMemoryError -> 0x00c6, IOException -> 0x00fe }
        r7 = r7.append(r11);	 Catch:{ OutOfMemoryError -> 0x00c6, IOException -> 0x00fe }
        r7 = r7.toString();	 Catch:{ OutOfMemoryError -> 0x00c6, IOException -> 0x00fe }
        com.budejie.www.util.aa.c(r6, r7);	 Catch:{ OutOfMemoryError -> 0x00c6, IOException -> 0x00fe }
        r6 = new android.os.Bundle;	 Catch:{ OutOfMemoryError -> 0x00c6, IOException -> 0x00fe }
        r6.<init>();	 Catch:{ OutOfMemoryError -> 0x00c6, IOException -> 0x00fe }
        r7 = "totalLength";
        r6.putLong(r7, r4);	 Catch:{ OutOfMemoryError -> 0x00c6, IOException -> 0x00fe }
        r4 = "imgUrl";
        r6.putString(r4, r12);	 Catch:{ OutOfMemoryError -> 0x00c6, IOException -> 0x00fe }
        r4 = 4;
        r4 = r13.obtainMessage(r4, r6);	 Catch:{ OutOfMemoryError -> 0x00c6, IOException -> 0x00fe }
        r13.sendMessage(r4);	 Catch:{ OutOfMemoryError -> 0x00c6, IOException -> 0x00fe }
    L_0x008e:
        if (r0 == 0) goto L_0x0152;
    L_0x0090:
        r4 = 1024; // 0x400 float:1.435E-42 double:5.06E-321;
        r4 = new byte[r4];	 Catch:{ OutOfMemoryError -> 0x00c6, IOException -> 0x00fe }
    L_0x0094:
        r5 = r0.read(r4);	 Catch:{ OutOfMemoryError -> 0x00c6, IOException -> 0x00fe }
        r6 = -1;
        if (r5 == r6) goto L_0x015b;
    L_0x009b:
        r6 = (long) r5;	 Catch:{ OutOfMemoryError -> 0x00c6, IOException -> 0x00fe }
        r2 = r2 + r6;
        r6 = 0;
        r1.write(r4, r6, r5);	 Catch:{ OutOfMemoryError -> 0x00c6, IOException -> 0x00fe }
        r1.flush();	 Catch:{ OutOfMemoryError -> 0x00c6, IOException -> 0x00fe }
        r5 = new android.os.Bundle;	 Catch:{ OutOfMemoryError -> 0x00c6, IOException -> 0x00fe }
        r5.<init>();	 Catch:{ OutOfMemoryError -> 0x00c6, IOException -> 0x00fe }
        r6 = "currentLength";
        r5.putLong(r6, r2);	 Catch:{ OutOfMemoryError -> 0x00c6, IOException -> 0x00fe }
        r6 = "imgUrl";
        r5.putString(r6, r12);	 Catch:{ OutOfMemoryError -> 0x00c6, IOException -> 0x00fe }
        r6 = 3;
        r5 = r13.obtainMessage(r6, r5);	 Catch:{ OutOfMemoryError -> 0x00c6, IOException -> 0x00fe }
        r13.sendMessage(r5);	 Catch:{ OutOfMemoryError -> 0x00c6, IOException -> 0x00fe }
        r6 = 3;
        java.lang.Thread.sleep(r6);	 Catch:{ InterruptedException -> 0x00c1 }
        goto L_0x0094;
    L_0x00c1:
        r5 = move-exception;
        r5.printStackTrace();	 Catch:{ OutOfMemoryError -> 0x00c6, IOException -> 0x00fe }
        goto L_0x0094;
    L_0x00c6:
        r2 = move-exception;
    L_0x00c7:
        r11.delete();	 Catch:{ all -> 0x01d8 }
        r2 = com.budejie.www.util.an.a;	 Catch:{ all -> 0x01d8 }
        r2.remove(r12);	 Catch:{ all -> 0x01d8 }
        r2 = 2;
        r13.sendEmptyMessage(r2);	 Catch:{ all -> 0x01d8 }
        r2 = "AsyncImageLoader";
        r3 = new java.lang.StringBuilder;	 Catch:{ all -> 0x01d8 }
        r3.<init>();	 Catch:{ all -> 0x01d8 }
        r4 = "writePngToLocal, OutOfMemoryError : ";
        r3 = r3.append(r4);	 Catch:{ all -> 0x01d8 }
        r3 = r3.append(r12);	 Catch:{ all -> 0x01d8 }
        r3 = r3.toString();	 Catch:{ all -> 0x01d8 }
        com.budejie.www.util.aa.e(r2, r3);	 Catch:{ all -> 0x01d8 }
        if (r1 == 0) goto L_0x00f0;
    L_0x00ed:
        r1.close();	 Catch:{ IOException -> 0x0185 }
    L_0x00f0:
        if (r0 == 0) goto L_0x00f5;
    L_0x00f2:
        r0.close();	 Catch:{ IOException -> 0x0185 }
    L_0x00f5:
        return;
    L_0x00f6:
        r4 = "AsyncImageLoader";
        r5 = "response.getEntity() = null";
        com.budejie.www.util.aa.e(r4, r5);	 Catch:{ OutOfMemoryError -> 0x00c6, IOException -> 0x00fe, all -> 0x01d2 }
        goto L_0x008e;
    L_0x00fe:
        r2 = move-exception;
    L_0x00ff:
        r2 = r11.exists();	 Catch:{ all -> 0x01d8 }
        if (r2 == 0) goto L_0x0108;
    L_0x0105:
        r11.delete();	 Catch:{ all -> 0x01d8 }
    L_0x0108:
        r2 = com.budejie.www.util.an.a;	 Catch:{ all -> 0x01d8 }
        r2.remove(r12);	 Catch:{ all -> 0x01d8 }
        r2 = 2;
        r13.sendEmptyMessage(r2);	 Catch:{ all -> 0x01d8 }
        r2 = "AsyncImageLoader";
        r3 = new java.lang.StringBuilder;	 Catch:{ all -> 0x01d8 }
        r3.<init>();	 Catch:{ all -> 0x01d8 }
        r4 = "ioexception : ";
        r3 = r3.append(r4);	 Catch:{ all -> 0x01d8 }
        r3 = r3.append(r12);	 Catch:{ all -> 0x01d8 }
        r3 = r3.toString();	 Catch:{ all -> 0x01d8 }
        com.budejie.www.util.aa.e(r2, r3);	 Catch:{ all -> 0x01d8 }
        if (r1 == 0) goto L_0x012e;
    L_0x012b:
        r1.close();	 Catch:{ IOException -> 0x0134 }
    L_0x012e:
        if (r0 == 0) goto L_0x00f5;
    L_0x0130:
        r0.close();	 Catch:{ IOException -> 0x0134 }
        goto L_0x00f5;
    L_0x0134:
        r0 = move-exception;
        r1 = "AsyncImageLoader";
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r0 = r0.toString();
        r0 = r2.append(r0);
        r2 = "流关闭异常";
        r0 = r0.append(r2);
        r0 = r0.toString();
        com.budejie.www.util.aa.e(r1, r0);
        goto L_0x00f5;
    L_0x0152:
        r2 = 2;
        r13.sendEmptyMessage(r2);	 Catch:{ OutOfMemoryError -> 0x00c6, IOException -> 0x00fe }
        r2 = com.budejie.www.util.an.a;	 Catch:{ OutOfMemoryError -> 0x00c6, IOException -> 0x00fe }
        r2.remove(r12);	 Catch:{ OutOfMemoryError -> 0x00c6, IOException -> 0x00fe }
    L_0x015b:
        if (r1 == 0) goto L_0x0160;
    L_0x015d:
        r1.close();	 Catch:{ IOException -> 0x0166 }
    L_0x0160:
        if (r0 == 0) goto L_0x00f5;
    L_0x0162:
        r0.close();	 Catch:{ IOException -> 0x0166 }
        goto L_0x00f5;
    L_0x0166:
        r0 = move-exception;
        r1 = "AsyncImageLoader";
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r0 = r0.toString();
        r0 = r2.append(r0);
        r2 = "流关闭异常";
        r0 = r0.append(r2);
        r0 = r0.toString();
        com.budejie.www.util.aa.e(r1, r0);
        goto L_0x00f5;
    L_0x0185:
        r0 = move-exception;
        r1 = "AsyncImageLoader";
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r0 = r0.toString();
        r0 = r2.append(r0);
        r2 = "流关闭异常";
        r0 = r0.append(r2);
        r0 = r0.toString();
        com.budejie.www.util.aa.e(r1, r0);
        goto L_0x00f5;
    L_0x01a4:
        r1 = move-exception;
        r2 = r0;
        r9 = r0;
        r0 = r1;
        r1 = r9;
    L_0x01a9:
        if (r2 == 0) goto L_0x01ae;
    L_0x01ab:
        r2.close();	 Catch:{ IOException -> 0x01b4 }
    L_0x01ae:
        if (r1 == 0) goto L_0x01b3;
    L_0x01b0:
        r1.close();	 Catch:{ IOException -> 0x01b4 }
    L_0x01b3:
        throw r0;
    L_0x01b4:
        r1 = move-exception;
        r2 = "AsyncImageLoader";
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r1 = r1.toString();
        r1 = r3.append(r1);
        r3 = "流关闭异常";
        r1 = r1.append(r3);
        r1 = r1.toString();
        com.budejie.www.util.aa.e(r2, r1);
        goto L_0x01b3;
    L_0x01d2:
        r2 = move-exception;
        r9 = r2;
        r2 = r1;
        r1 = r0;
        r0 = r9;
        goto L_0x01a9;
    L_0x01d8:
        r2 = move-exception;
        r9 = r2;
        r2 = r1;
        r1 = r0;
        r0 = r9;
        goto L_0x01a9;
    L_0x01de:
        r1 = move-exception;
        r1 = r0;
        goto L_0x00ff;
    L_0x01e2:
        r1 = move-exception;
        r1 = r0;
        goto L_0x00c7;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.budejie.www.util.f.c(java.io.File, java.lang.String, android.os.Handler):void");
    }

    private synchronized void a(File file, Handler handler, String str) {
        aa.c("AsyncImageLoader", "createPng(from file), file = " + file);
        String file2 = file.toString();
        try {
            Options options = new Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(file2, options);
            if (options.outWidth <= 0 || options.outHeight <= 0) {
                aa.e("AsyncImageLoader", "*****createPng(from file), error!  --->opt.outWidth = " + options.outWidth + ", opt.outHeight = " + options.outHeight + ", file = " + file);
                file.delete();
                an.a.remove(str);
                handler.sendEmptyMessage(2);
            } else {
                options.inJustDecodeBounds = false;
                options.inPreferredConfig = Config.RGB_565;
                options.inPurgeable = true;
                options.inInputShareable = true;
                Bitmap decodeFile = BitmapFactory.decodeFile(file2, options);
                BitmapDrawable bitmapDrawable = new BitmapDrawable(decodeFile);
                if (decodeFile == null || bitmapDrawable == null) {
                    aa.e("AsyncImageLoader", "*****createPng(from file), error!  --->bitmap = " + decodeFile + ", drawable = " + bitmapDrawable + ", file = " + file);
                    handler.sendEmptyMessage(2);
                } else {
                    this.a.put(str, new SoftReference(bitmapDrawable));
                    handler.sendMessage(handler.obtainMessage(1, bitmapDrawable));
                }
            }
        } catch (OutOfMemoryError e) {
            aa.e("AsyncImageLoader", "*****createPng(from file), OutOfMemoryError! , file = " + file);
        } catch (Exception e2) {
            aa.e("AsyncImageLoader", "*****createPng(from file), Exception = " + e2.toString() + ", file = " + file);
            e2.printStackTrace();
        }
    }
}
