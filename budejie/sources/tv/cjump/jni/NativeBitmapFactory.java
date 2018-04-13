package tv.cjump.jni;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.os.Build.VERSION;
import android.util.Log;
import java.lang.reflect.Field;

public class NativeBitmapFactory {
    static Field a = null;
    static boolean b = false;

    private static native Bitmap createBitmap(int i, int i2, int i3, boolean z);

    private static native Bitmap createBitmap19(int i, int i2, int i3, boolean z);

    private static native boolean init();

    private static native boolean release();

    public static boolean a() {
        return VERSION.SDK_INT < 11 || (b && a != null);
    }

    public static void b() {
        if (!DeviceUtils.g() && !DeviceUtils.h()) {
            b = false;
        } else if (!b) {
            try {
                if (VERSION.SDK_INT >= 11) {
                    System.loadLibrary("ndkbitmap");
                    b = true;
                } else {
                    b = false;
                }
            } catch (Exception e) {
                e.printStackTrace();
                b = false;
            } catch (Error e2) {
                e2.printStackTrace();
                b = false;
            }
            if (b) {
                if (init()) {
                    d();
                    if (!e()) {
                        release();
                        b = false;
                    }
                } else {
                    release();
                    b = false;
                }
            }
            Log.e("NativeBitmapFactory", "loaded" + b);
        }
    }

    public static void c() {
        if (b) {
            release();
        }
        a = null;
        b = false;
    }

    static void d() {
        try {
            a = Config.class.getDeclaredField("nativeInt");
            a.setAccessible(true);
        } catch (NoSuchFieldException e) {
            a = null;
            e.printStackTrace();
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @android.annotation.SuppressLint({"NewApi"})
    private static boolean e() {
        /*
        r9 = 17;
        r6 = 1;
        r5 = 2;
        r7 = 0;
        r0 = a;
        if (r0 != 0) goto L_0x000b;
    L_0x0009:
        r0 = r7;
    L_0x000a:
        return r0;
    L_0x000b:
        r1 = 0;
        r0 = 2;
        r2 = 2;
        r3 = android.graphics.Bitmap.Config.ARGB_8888;	 Catch:{ Exception -> 0x006d, Error -> 0x0092, all -> 0x009c }
        r4 = 1;
        r8 = b(r0, r2, r3, r4);	 Catch:{ Exception -> 0x006d, Error -> 0x0092, all -> 0x009c }
        if (r8 == 0) goto L_0x006b;
    L_0x0017:
        r0 = r8.getWidth();	 Catch:{ Exception -> 0x00ab, Error -> 0x00a9, all -> 0x00a4 }
        if (r0 != r5) goto L_0x006b;
    L_0x001d:
        r0 = r8.getHeight();	 Catch:{ Exception -> 0x00ab, Error -> 0x00a9, all -> 0x00a4 }
        if (r0 != r5) goto L_0x006b;
    L_0x0023:
        if (r6 == 0) goto L_0x00ae;
    L_0x0025:
        r0 = android.os.Build.VERSION.SDK_INT;	 Catch:{ Exception -> 0x00ab, Error -> 0x00a9, all -> 0x00a4 }
        if (r0 < r9) goto L_0x0033;
    L_0x0029:
        r0 = r8.isPremultiplied();	 Catch:{ Exception -> 0x00ab, Error -> 0x00a9, all -> 0x00a4 }
        if (r0 != 0) goto L_0x0033;
    L_0x002f:
        r0 = 1;
        r8.setPremultiplied(r0);	 Catch:{ Exception -> 0x00ab, Error -> 0x00a9, all -> 0x00a4 }
    L_0x0033:
        r0 = new android.graphics.Canvas;	 Catch:{ Exception -> 0x00ab, Error -> 0x00a9, all -> 0x00a4 }
        r0.<init>(r8);	 Catch:{ Exception -> 0x00ab, Error -> 0x00a9, all -> 0x00a4 }
        r5 = new android.graphics.Paint;	 Catch:{ Exception -> 0x00ab, Error -> 0x00a9, all -> 0x00a4 }
        r5.<init>();	 Catch:{ Exception -> 0x00ab, Error -> 0x00a9, all -> 0x00a4 }
        r1 = -65536; // 0xffffffffffff0000 float:NaN double:NaN;
        r5.setColor(r1);	 Catch:{ Exception -> 0x00ab, Error -> 0x00a9, all -> 0x00a4 }
        r1 = 1101004800; // 0x41a00000 float:20.0 double:5.439686476E-315;
        r5.setTextSize(r1);	 Catch:{ Exception -> 0x00ab, Error -> 0x00a9, all -> 0x00a4 }
        r1 = 0;
        r2 = 0;
        r3 = r8.getWidth();	 Catch:{ Exception -> 0x00ab, Error -> 0x00a9, all -> 0x00a4 }
        r3 = (float) r3;	 Catch:{ Exception -> 0x00ab, Error -> 0x00a9, all -> 0x00a4 }
        r4 = r8.getHeight();	 Catch:{ Exception -> 0x00ab, Error -> 0x00a9, all -> 0x00a4 }
        r4 = (float) r4;	 Catch:{ Exception -> 0x00ab, Error -> 0x00a9, all -> 0x00a4 }
        r0.drawRect(r1, r2, r3, r4, r5);	 Catch:{ Exception -> 0x00ab, Error -> 0x00a9, all -> 0x00a4 }
        r1 = "TestLib";
        r2 = 0;
        r3 = 0;
        r0.drawText(r1, r2, r3, r5);	 Catch:{ Exception -> 0x00ab, Error -> 0x00a9, all -> 0x00a4 }
        r0 = android.os.Build.VERSION.SDK_INT;	 Catch:{ Exception -> 0x00ab, Error -> 0x00a9, all -> 0x00a4 }
        if (r0 < r9) goto L_0x00ae;
    L_0x0061:
        r0 = r8.isPremultiplied();	 Catch:{ Exception -> 0x00ab, Error -> 0x00a9, all -> 0x00a4 }
    L_0x0065:
        if (r8 == 0) goto L_0x000a;
    L_0x0067:
        r8.recycle();
        goto L_0x000a;
    L_0x006b:
        r6 = r7;
        goto L_0x0023;
    L_0x006d:
        r0 = move-exception;
    L_0x006e:
        r2 = "NativeBitmapFactory";
        r3 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00a6 }
        r3.<init>();	 Catch:{ all -> 0x00a6 }
        r4 = "exception:";
        r3 = r3.append(r4);	 Catch:{ all -> 0x00a6 }
        r0 = r0.toString();	 Catch:{ all -> 0x00a6 }
        r0 = r3.append(r0);	 Catch:{ all -> 0x00a6 }
        r0 = r0.toString();	 Catch:{ all -> 0x00a6 }
        android.util.Log.e(r2, r0);	 Catch:{ all -> 0x00a6 }
        if (r1 == 0) goto L_0x008f;
    L_0x008c:
        r1.recycle();
    L_0x008f:
        r0 = r7;
        goto L_0x000a;
    L_0x0092:
        r0 = move-exception;
        r8 = r1;
    L_0x0094:
        if (r8 == 0) goto L_0x0099;
    L_0x0096:
        r8.recycle();
    L_0x0099:
        r0 = r7;
        goto L_0x000a;
    L_0x009c:
        r0 = move-exception;
        r8 = r1;
    L_0x009e:
        if (r8 == 0) goto L_0x00a3;
    L_0x00a0:
        r8.recycle();
    L_0x00a3:
        throw r0;
    L_0x00a4:
        r0 = move-exception;
        goto L_0x009e;
    L_0x00a6:
        r0 = move-exception;
        r8 = r1;
        goto L_0x009e;
    L_0x00a9:
        r0 = move-exception;
        goto L_0x0094;
    L_0x00ab:
        r0 = move-exception;
        r1 = r8;
        goto L_0x006e;
    L_0x00ae:
        r0 = r6;
        goto L_0x0065;
        */
        throw new UnsupportedOperationException("Method not decompiled: tv.cjump.jni.NativeBitmapFactory.e():boolean");
    }

    public static int a(Config config) {
        int i = 0;
        try {
            if (a != null) {
                i = a.getInt(config);
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e2) {
            e2.printStackTrace();
        }
        return i;
    }

    public static Bitmap a(int i, int i2, Config config) {
        return a(i, i2, config, config.equals(Config.ARGB_8888));
    }

    public static Bitmap a(int i, int i2, Config config, boolean z) {
        if (!b || a == null) {
            return Bitmap.createBitmap(i, i2, config);
        }
        return b(i, i2, config, z);
    }

    private static Bitmap b(int i, int i2, Config config, boolean z) {
        int a = a(config);
        if (VERSION.SDK_INT == 19) {
            return createBitmap19(i, i2, a, z);
        }
        return createBitmap(i, i2, a, z);
    }
}
