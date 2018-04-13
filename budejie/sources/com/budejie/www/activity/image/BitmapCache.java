package com.budejie.www.activity.image;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.media.ThumbnailUtils;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;
import java.lang.ref.SoftReference;
import java.util.HashMap;

public class BitmapCache extends Activity {
    public Handler a = new Handler();
    public final String b = getClass().getSimpleName();
    private HashMap<String, SoftReference<Bitmap>> c = new HashMap();

    public interface a {
        void a(ImageView imageView, Bitmap bitmap, Object... objArr);
    }

    public void a(String str, Bitmap bitmap) {
        if (!TextUtils.isEmpty(str) && bitmap != null) {
            this.c.put(str, new SoftReference(bitmap));
        }
    }

    public void a(ImageView imageView, String str, String str2, a aVar) {
        if (TextUtils.isEmpty(str) && TextUtils.isEmpty(str2)) {
            Log.e(this.b, "no paths pass in");
            return;
        }
        boolean z;
        String str3;
        if (!TextUtils.isEmpty(str)) {
            z = true;
            str3 = str;
        } else if (!TextUtils.isEmpty(str2)) {
            z = false;
            str3 = str2;
        } else {
            return;
        }
        if (this.c.containsKey(str3)) {
            Bitmap bitmap = (Bitmap) ((SoftReference) this.c.get(str3)).get();
            if (bitmap != null) {
                if (aVar != null) {
                    aVar.a(imageView, bitmap, str2);
                }
                imageView.setImageBitmap(bitmap);
                Log.d(this.b, "hit cache");
                return;
            }
        }
        imageView.setImageBitmap(null);
        final String str4 = str;
        final String str5 = str2;
        final a aVar2 = aVar;
        final ImageView imageView2 = imageView;
        new Thread(this) {
            Bitmap a;
            final /* synthetic */ BitmapCache h;

            public void run() {
                if (z) {
                    try {
                        this.a = BitmapFactory.decodeFile(str4);
                    } catch (Exception e) {
                        Options options = new Options();
                        options.inPreferredConfig = Config.RGB_565;
                        options.inPurgeable = true;
                        options.inInputShareable = true;
                        options.inSampleSize = 2;
                        try {
                            this.a = BitmapFactory.decodeFile(str4, options);
                        } catch (OutOfMemoryError e2) {
                            Toast.makeText(this.h.getApplicationContext(), "手机内存不足啦!", 0).show();
                        }
                    }
                    if (this.a == null) {
                        this.a = this.h.a(str5);
                    }
                } else {
                    this.a = this.h.a(str5);
                }
                if (this.a == null) {
                    this.a = SelectImageActivity.a;
                }
                this.h.a(str3, this.a);
                if (aVar2 != null) {
                    this.h.a.post(new BitmapCache$1$1(this));
                }
            }
        }.start();
    }

    public static Bitmap a(String str, int i) {
        try {
            return ThumbnailUtils.createVideoThumbnail(str, i);
        } catch (OutOfMemoryError e) {
            return null;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.graphics.Bitmap a(java.lang.String r13) {
        /*
        r12 = this;
        r10 = 4607182418800017408; // 0x3ff0000000000000 float:0.0 double:1.0;
        r1 = 0;
        r8 = 1;
        r0 = 0;
        r2 = "mp4";
        r2 = r13.endsWith(r2);
        if (r2 == 0) goto L_0x0012;
    L_0x000d:
        r0 = a(r13, r8);
    L_0x0011:
        return r0;
    L_0x0012:
        r3 = new java.io.BufferedInputStream;	 Catch:{ OutOfMemoryError -> 0x0089, IOException -> 0x00ca }
        r2 = new java.io.FileInputStream;	 Catch:{ OutOfMemoryError -> 0x0089, IOException -> 0x00ca }
        r4 = new java.io.File;	 Catch:{ OutOfMemoryError -> 0x0089, IOException -> 0x00ca }
        r4.<init>(r13);	 Catch:{ OutOfMemoryError -> 0x0089, IOException -> 0x00ca }
        r2.<init>(r4);	 Catch:{ OutOfMemoryError -> 0x0089, IOException -> 0x00ca }
        r3.<init>(r2);	 Catch:{ OutOfMemoryError -> 0x0089, IOException -> 0x00ca }
        r2 = new android.graphics.BitmapFactory$Options;	 Catch:{ OutOfMemoryError -> 0x0089, IOException -> 0x00ca }
        r2.<init>();	 Catch:{ OutOfMemoryError -> 0x0089, IOException -> 0x00ca }
        r4 = 1;
        r2.inJustDecodeBounds = r4;	 Catch:{ OutOfMemoryError -> 0x00cd, IOException -> 0x00ca }
        r4 = 0;
        android.graphics.BitmapFactory.decodeStream(r3, r4, r2);	 Catch:{ OutOfMemoryError -> 0x00cd, IOException -> 0x00ca }
        r3.close();	 Catch:{ OutOfMemoryError -> 0x00cd, IOException -> 0x00ca }
        r3 = r2.outHeight;	 Catch:{ OutOfMemoryError -> 0x00cd, IOException -> 0x00ca }
        r4 = (double) r3;	 Catch:{ OutOfMemoryError -> 0x00cd, IOException -> 0x00ca }
        r4 = r4 * r10;
        r3 = r2.outWidth;	 Catch:{ OutOfMemoryError -> 0x00cd, IOException -> 0x00ca }
        r6 = (double) r3;	 Catch:{ OutOfMemoryError -> 0x00cd, IOException -> 0x00ca }
        r4 = r4 / r6;
        r6 = 4625196817309499392; // 0x4030000000000000 float:0.0 double:16.0;
        r3 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1));
        if (r3 <= 0) goto L_0x006e;
    L_0x003e:
        r4 = 64;
        r3 = 64;
    L_0x0042:
        r5 = r2.outWidth;	 Catch:{ OutOfMemoryError -> 0x00cd, IOException -> 0x00ca }
        r5 = r5 >> r1;
        if (r5 <= r4) goto L_0x004c;
    L_0x0047:
        r5 = r2.outHeight;	 Catch:{ OutOfMemoryError -> 0x00cd, IOException -> 0x00ca }
        r5 = r5 >> r1;
        if (r5 > r3) goto L_0x0086;
    L_0x004c:
        r3 = new java.io.BufferedInputStream;	 Catch:{ OutOfMemoryError -> 0x00cd, IOException -> 0x00ca }
        r4 = new java.io.FileInputStream;	 Catch:{ OutOfMemoryError -> 0x00cd, IOException -> 0x00ca }
        r5 = new java.io.File;	 Catch:{ OutOfMemoryError -> 0x00cd, IOException -> 0x00ca }
        r5.<init>(r13);	 Catch:{ OutOfMemoryError -> 0x00cd, IOException -> 0x00ca }
        r4.<init>(r5);	 Catch:{ OutOfMemoryError -> 0x00cd, IOException -> 0x00ca }
        r3.<init>(r4);	 Catch:{ OutOfMemoryError -> 0x00cd, IOException -> 0x00ca }
        r4 = 4611686018427387904; // 0x4000000000000000 float:0.0 double:2.0;
        r6 = (double) r1;	 Catch:{ OutOfMemoryError -> 0x00cd, IOException -> 0x00ca }
        r4 = java.lang.Math.pow(r4, r6);	 Catch:{ OutOfMemoryError -> 0x00cd, IOException -> 0x00ca }
        r4 = (int) r4;	 Catch:{ OutOfMemoryError -> 0x00cd, IOException -> 0x00ca }
        r2.inSampleSize = r4;	 Catch:{ OutOfMemoryError -> 0x00cd, IOException -> 0x00ca }
        r4 = 0;
        r2.inJustDecodeBounds = r4;	 Catch:{ OutOfMemoryError -> 0x00cd, IOException -> 0x00ca }
        r4 = 0;
        r0 = android.graphics.BitmapFactory.decodeStream(r3, r4, r2);	 Catch:{ OutOfMemoryError -> 0x00cd, IOException -> 0x00ca }
        goto L_0x0011;
    L_0x006e:
        r3 = r2.outHeight;	 Catch:{ OutOfMemoryError -> 0x00cd, IOException -> 0x00ca }
        r4 = (double) r3;	 Catch:{ OutOfMemoryError -> 0x00cd, IOException -> 0x00ca }
        r4 = r4 * r10;
        r3 = r2.outWidth;	 Catch:{ OutOfMemoryError -> 0x00cd, IOException -> 0x00ca }
        r6 = (double) r3;
        r4 = r4 / r6;
        r6 = 4620693217682128896; // 0x4020000000000000 float:0.0 double:8.0;
        r3 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1));
        if (r3 <= 0) goto L_0x0081;
    L_0x007c:
        r4 = 128; // 0x80 float:1.794E-43 double:6.32E-322;
        r3 = 128; // 0x80 float:1.794E-43 double:6.32E-322;
        goto L_0x0042;
    L_0x0081:
        r4 = 256; // 0x100 float:3.59E-43 double:1.265E-321;
        r3 = 256; // 0x100 float:3.59E-43 double:1.265E-321;
        goto L_0x0042;
    L_0x0086:
        r1 = r1 + 1;
        goto L_0x0042;
    L_0x0089:
        r2 = move-exception;
        r2 = r0;
    L_0x008b:
        r3 = android.graphics.Bitmap.Config.RGB_565;
        r2.inPreferredConfig = r3;
        r2.inPurgeable = r8;
        r2.inInputShareable = r8;
        r3 = new java.io.BufferedInputStream;	 Catch:{ Exception -> 0x00b8 }
        r4 = new java.io.FileInputStream;	 Catch:{ Exception -> 0x00b8 }
        r5 = new java.io.File;	 Catch:{ Exception -> 0x00b8 }
        r5.<init>(r13);	 Catch:{ Exception -> 0x00b8 }
        r4.<init>(r5);	 Catch:{ Exception -> 0x00b8 }
        r3.<init>(r4);	 Catch:{ Exception -> 0x00b8 }
        r4 = 4611686018427387904; // 0x4000000000000000 float:0.0 double:2.0;
        r6 = (double) r1;	 Catch:{ Exception -> 0x00b8 }
        r4 = java.lang.Math.pow(r4, r6);	 Catch:{ Exception -> 0x00b8 }
        r1 = (int) r4;	 Catch:{ Exception -> 0x00b8 }
        r1 = r1 + 1;
        r2.inSampleSize = r1;	 Catch:{ Exception -> 0x00b8 }
        r1 = 0;
        r2.inJustDecodeBounds = r1;	 Catch:{ Exception -> 0x00b8 }
        r1 = 0;
        r0 = android.graphics.BitmapFactory.decodeStream(r3, r1, r2);	 Catch:{ Exception -> 0x00b8 }
        goto L_0x0011;
    L_0x00b8:
        r1 = move-exception;
        r1 = 2131232069; // 0x7f080545 float:1.8080237E38 double:1.0529685486E-314;
        r1 = r12.getString(r1);
        r2 = -1;
        r1 = com.budejie.www.util.an.a(r12, r1, r2);
        r1.show();
        goto L_0x0011;
    L_0x00ca:
        r1 = move-exception;
        goto L_0x0011;
    L_0x00cd:
        r3 = move-exception;
        goto L_0x008b;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.budejie.www.activity.image.BitmapCache.a(java.lang.String):android.graphics.Bitmap");
    }
}
