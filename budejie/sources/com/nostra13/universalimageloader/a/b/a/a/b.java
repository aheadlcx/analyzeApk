package com.nostra13.universalimageloader.a.b.a.a;

import android.graphics.Bitmap;
import com.nostra13.universalimageloader.a.b.a.c;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashMap;

public class b implements c {
    public static long a;
    private final LinkedHashMap<String, Bitmap> b;
    private final int c;

    public b(int i) {
        if (i <= 0) {
            throw new IllegalArgumentException("maxSize <= 0");
        }
        this.c = i;
        this.b = new LinkedHashMap(0, 0.75f, true);
    }

    public final Bitmap a(String str) {
        if (str == null) {
            throw new NullPointerException("key == null");
        }
        Bitmap bitmap;
        synchronized (this) {
            bitmap = (Bitmap) this.b.get(str);
        }
        return bitmap;
    }

    public final boolean a(String str, Bitmap bitmap) {
        if (str == null || bitmap == null) {
            throw new NullPointerException("key == null || value == null");
        }
        synchronized (this) {
            a += (long) b(str, bitmap);
            Bitmap bitmap2 = (Bitmap) this.b.put(str, bitmap);
            if (bitmap2 != null) {
                a -= (long) b(str, bitmap2);
            }
        }
        a(this.c);
        return true;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a(int r7) {
        /*
        r6 = this;
        r4 = 0;
    L_0x0002:
        monitor-enter(r6);
        r0 = a;	 Catch:{ all -> 0x0056 }
        r0 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1));
        if (r0 < 0) goto L_0x0017;
    L_0x0009:
        r0 = r6.b;	 Catch:{ all -> 0x0056 }
        r0 = r0.isEmpty();	 Catch:{ all -> 0x0056 }
        if (r0 == 0) goto L_0x0045;
    L_0x0011:
        r0 = a;	 Catch:{ all -> 0x0056 }
        r0 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1));
        if (r0 == 0) goto L_0x0045;
    L_0x0017:
        r0 = r6.getClass();	 Catch:{ all -> 0x0056 }
        r0 = r0.getName();	 Catch:{ all -> 0x0056 }
        r1 = 1;
        r1 = new java.lang.Object[r1];	 Catch:{ all -> 0x0056 }
        r2 = 0;
        r3 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0056 }
        r3.<init>();	 Catch:{ all -> 0x0056 }
        r4 = r6.getClass();	 Catch:{ all -> 0x0056 }
        r4 = r4.getName();	 Catch:{ all -> 0x0056 }
        r3 = r3.append(r4);	 Catch:{ all -> 0x0056 }
        r4 = ".sizeOf() is reporting inconsistent results!";
        r3 = r3.append(r4);	 Catch:{ all -> 0x0056 }
        r3 = r3.toString();	 Catch:{ all -> 0x0056 }
        r1[r2] = r3;	 Catch:{ all -> 0x0056 }
        com.nostra13.universalimageloader.b.e.d(r0, r1);	 Catch:{ all -> 0x0056 }
        monitor-exit(r6);	 Catch:{ all -> 0x0056 }
    L_0x0044:
        return;
    L_0x0045:
        r0 = a;	 Catch:{ all -> 0x0056 }
        r2 = (long) r7;	 Catch:{ all -> 0x0056 }
        r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));
        if (r0 <= 0) goto L_0x0054;
    L_0x004c:
        r0 = r6.b;	 Catch:{ all -> 0x0056 }
        r0 = r0.isEmpty();	 Catch:{ all -> 0x0056 }
        if (r0 == 0) goto L_0x0059;
    L_0x0054:
        monitor-exit(r6);	 Catch:{ all -> 0x0056 }
        goto L_0x0044;
    L_0x0056:
        r0 = move-exception;
        monitor-exit(r6);	 Catch:{ all -> 0x0056 }
        throw r0;
    L_0x0059:
        r0 = r6.b;	 Catch:{ all -> 0x0056 }
        r0 = r0.entrySet();	 Catch:{ all -> 0x0056 }
        r0 = r0.iterator();	 Catch:{ all -> 0x0056 }
        r0 = r0.next();	 Catch:{ all -> 0x0056 }
        r0 = (java.util.Map.Entry) r0;	 Catch:{ all -> 0x0056 }
        if (r0 != 0) goto L_0x006d;
    L_0x006b:
        monitor-exit(r6);	 Catch:{ all -> 0x0056 }
        goto L_0x0044;
    L_0x006d:
        r1 = r0.getKey();	 Catch:{ all -> 0x0056 }
        r1 = (java.lang.String) r1;	 Catch:{ all -> 0x0056 }
        r0 = r0.getValue();	 Catch:{ all -> 0x0056 }
        r0 = (android.graphics.Bitmap) r0;	 Catch:{ all -> 0x0056 }
        r2 = r6.b;	 Catch:{ all -> 0x0056 }
        r2.remove(r1);	 Catch:{ all -> 0x0056 }
        r2 = a;	 Catch:{ all -> 0x0056 }
        r0 = r6.b(r1, r0);	 Catch:{ all -> 0x0056 }
        r0 = (long) r0;	 Catch:{ all -> 0x0056 }
        r0 = r2 - r0;
        a = r0;	 Catch:{ all -> 0x0056 }
        monitor-exit(r6);	 Catch:{ all -> 0x0056 }
        goto L_0x0002;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.nostra13.universalimageloader.a.b.a.a.b.a(int):void");
    }

    public final Bitmap b(String str) {
        if (str == null) {
            throw new NullPointerException("key == null");
        }
        Bitmap bitmap;
        synchronized (this) {
            bitmap = (Bitmap) this.b.remove(str);
            if (bitmap != null) {
                a -= (long) b(str, bitmap);
            }
        }
        return bitmap;
    }

    public Collection<String> a() {
        Collection hashSet;
        synchronized (this) {
            hashSet = new HashSet(this.b.keySet());
        }
        return hashSet;
    }

    public void b() {
        a(-1);
    }

    private int b(String str, Bitmap bitmap) {
        return bitmap.getRowBytes() * bitmap.getHeight();
    }

    public final synchronized String toString() {
        return String.format("LruCache[maxSize=%d]", new Object[]{Integer.valueOf(this.c)});
    }
}
