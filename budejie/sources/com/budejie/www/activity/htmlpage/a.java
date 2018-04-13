package com.budejie.www.activity.htmlpage;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.text.TextUtils;
import android.util.Log;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

public class a {
    public static File a(String str, String str2) {
        File file = null;
        Bitmap b = b(str);
        if (b != null) {
            Bitmap a = a(str, b);
            OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            a.compress(CompressFormat.PNG, 100, byteArrayOutputStream);
            try {
                file = a(byteArrayOutputStream.toByteArray(), str2);
                if (!(a == null || a.isRecycled())) {
                    a.recycle();
                }
                if (!(b == null || b.isRecycled())) {
                    b.recycle();
                }
            } catch (Exception e) {
                e.printStackTrace();
                if (!(a == null || a.isRecycled())) {
                    a.recycle();
                }
                if (!(b == null || b.isRecycled())) {
                    b.recycle();
                }
            } catch (Throwable th) {
                if (!(a == null || a.isRecycled())) {
                    a.recycle();
                }
                if (!(b == null || b.isRecycled())) {
                    b.recycle();
                }
            }
        }
        return file;
    }

    private static Bitmap a(String str, Bitmap bitmap) {
        return a(a(str), bitmap);
    }

    public static Bitmap a(int i, Bitmap bitmap) {
        Matrix matrix = new Matrix();
        matrix.postRotate((float) i);
        System.out.println("angle2=" + i);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    public static int a(String str) {
        try {
            switch (new ExifInterface(str).getAttributeInt("Orientation", 1)) {
                case 3:
                    return 180;
                case 6:
                    return 90;
                case 8:
                    return 270;
                default:
                    return 0;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.io.File a(byte[] r4, java.lang.String r5) {
        /*
        r3 = 0;
        r0 = new java.io.File;	 Catch:{ Exception -> 0x001e, all -> 0x0030 }
        r0.<init>(r5);	 Catch:{ Exception -> 0x001e, all -> 0x0030 }
        r1 = new java.io.FileOutputStream;	 Catch:{ Exception -> 0x003f, all -> 0x0030 }
        r1.<init>(r0);	 Catch:{ Exception -> 0x003f, all -> 0x0030 }
        r2 = new java.io.BufferedOutputStream;	 Catch:{ Exception -> 0x003f, all -> 0x0030 }
        r2.<init>(r1);	 Catch:{ Exception -> 0x003f, all -> 0x0030 }
        r2.write(r4);	 Catch:{ Exception -> 0x0042 }
        if (r2 == 0) goto L_0x0018;
    L_0x0015:
        r2.close();	 Catch:{ IOException -> 0x0019 }
    L_0x0018:
        return r0;
    L_0x0019:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x0018;
    L_0x001e:
        r0 = move-exception;
        r1 = r0;
        r2 = r3;
        r0 = r3;
    L_0x0022:
        r1.printStackTrace();	 Catch:{ all -> 0x003c }
        if (r2 == 0) goto L_0x0018;
    L_0x0027:
        r2.close();	 Catch:{ IOException -> 0x002b }
        goto L_0x0018;
    L_0x002b:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x0018;
    L_0x0030:
        r0 = move-exception;
    L_0x0031:
        if (r3 == 0) goto L_0x0036;
    L_0x0033:
        r3.close();	 Catch:{ IOException -> 0x0037 }
    L_0x0036:
        throw r0;
    L_0x0037:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x0036;
    L_0x003c:
        r0 = move-exception;
        r3 = r2;
        goto L_0x0031;
    L_0x003f:
        r1 = move-exception;
        r2 = r3;
        goto L_0x0022;
    L_0x0042:
        r1 = move-exception;
        goto L_0x0022;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.budejie.www.activity.htmlpage.a.a(byte[], java.lang.String):java.io.File");
    }

    public static Bitmap b(String str) {
        int i = 1;
        Options options = new Options();
        options.inJustDecodeBounds = true;
        options.inDither = false;
        options.inPurgeable = true;
        options.inInputShareable = true;
        BitmapFactory.decodeFile(str, options);
        if (options.outHeight > 200 || options.outWidth > 200) {
            i = Math.round(((float) options.outHeight) / 200.0f);
            int round = Math.round(((float) options.outWidth) / 200.0f);
            if (i >= round) {
                i = round;
            }
        }
        Log.i("scale", "scal =" + i);
        options.inJustDecodeBounds = false;
        options.inSampleSize = i;
        Bitmap decodeFile = BitmapFactory.decodeFile(str, options);
        if (decodeFile == null) {
            return null;
        }
        return decodeFile.copy(Config.ARGB_8888, false);
    }

    public static void c(String str) {
        if (!TextUtils.isEmpty(str)) {
            File file = new File(str);
            if (file.exists()) {
                file.delete();
            }
        }
    }
}
