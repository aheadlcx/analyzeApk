package com.bumptech.glide.b;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.os.Build.VERSION;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;

public class a {
    private static final String a = a.class.getSimpleName();
    private static final Config b = Config.ARGB_8888;
    private int[] c;
    private ByteBuffer d;
    private final byte[] e = new byte[256];
    private short[] f;
    private byte[] g;
    private byte[] h;
    private byte[] i;
    private int[] j;
    private int k;
    private byte[] l;
    private c m;
    private a n;
    private Bitmap o;
    private boolean p;
    private int q;

    public interface a {
        Bitmap a(int i, int i2, Config config);

        void a(Bitmap bitmap);
    }

    public a(a aVar) {
        this.n = aVar;
        this.m = new c();
    }

    public int a() {
        return this.m.f;
    }

    public int b() {
        return this.m.g;
    }

    public void c() {
        this.k = (this.k + 1) % this.m.c;
    }

    public int a(int i) {
        if (i < 0 || i >= this.m.c) {
            return -1;
        }
        return ((b) this.m.e.get(i)).i;
    }

    public int d() {
        if (this.m.c <= 0 || this.k < 0) {
            return -1;
        }
        return a(this.k);
    }

    public int e() {
        return this.m.c;
    }

    public int f() {
        return this.k;
    }

    public int g() {
        return this.m.m;
    }

    public synchronized Bitmap h() {
        Bitmap bitmap;
        int i = 0;
        synchronized (this) {
            if (this.m.c <= 0 || this.k < 0) {
                if (Log.isLoggable(a, 3)) {
                    Log.d(a, "unable to decode frame, frameCount=" + this.m.c + " framePointer=" + this.k);
                }
                this.q = 1;
            }
            if (this.q == 1 || this.q == 2) {
                if (Log.isLoggable(a, 3)) {
                    Log.d(a, "Unable to decode frame, status=" + this.q);
                }
                bitmap = null;
            } else {
                b bVar;
                this.q = 0;
                b bVar2 = (b) this.m.e.get(this.k);
                int i2 = this.k - 1;
                if (i2 >= 0) {
                    bVar = (b) this.m.e.get(i2);
                } else {
                    bVar = null;
                }
                if (bVar2.k == null) {
                    this.c = this.m.a;
                } else {
                    this.c = bVar2.k;
                    if (this.m.j == bVar2.h) {
                        this.m.l = 0;
                    }
                }
                if (bVar2.f) {
                    i2 = this.c[bVar2.h];
                    this.c[bVar2.h] = 0;
                    i = i2;
                }
                if (this.c == null) {
                    if (Log.isLoggable(a, 3)) {
                        Log.d(a, "No Valid Color Table");
                    }
                    this.q = 1;
                    bitmap = null;
                } else {
                    Bitmap a = a(bVar2, bVar);
                    if (bVar2.f) {
                        this.c[bVar2.h] = i;
                    }
                    bitmap = a;
                }
            }
        }
        return bitmap;
    }

    public void i() {
        this.m = null;
        this.l = null;
        this.i = null;
        this.j = null;
        if (this.o != null) {
            this.n.a(this.o);
        }
        this.o = null;
        this.d = null;
    }

    public void a(c cVar, byte[] bArr) {
        this.m = cVar;
        this.l = bArr;
        this.q = 0;
        this.k = -1;
        this.d = ByteBuffer.wrap(bArr);
        this.d.rewind();
        this.d.order(ByteOrder.LITTLE_ENDIAN);
        this.p = false;
        for (b bVar : cVar.e) {
            if (bVar.g == 3) {
                this.p = true;
                break;
            }
        }
        this.i = new byte[(cVar.f * cVar.g)];
        this.j = new int[(cVar.f * cVar.g)];
    }

    private Bitmap a(b bVar, b bVar2) {
        int i;
        int i2 = this.m.f;
        int i3 = this.m.g;
        int[] iArr = this.j;
        if (bVar2 != null && bVar2.g > 0) {
            if (bVar2.g == 2) {
                i = 0;
                if (!bVar.f) {
                    i = this.m.l;
                }
                Arrays.fill(iArr, i);
            } else if (bVar2.g == 3 && this.o != null) {
                this.o.getPixels(iArr, 0, i2, 0, 0, i2, i3);
            }
        }
        a(bVar);
        int i4 = 1;
        int i5 = 8;
        int i6 = 0;
        for (i = 0; i < bVar.d; i++) {
            int i7;
            if (bVar.e) {
                if (i6 >= bVar.d) {
                    i4++;
                    switch (i4) {
                        case 2:
                            i6 = 4;
                            break;
                        case 3:
                            i6 = 2;
                            i5 = 4;
                            break;
                        case 4:
                            i6 = 1;
                            i5 = 2;
                            break;
                    }
                }
                int i8 = i6;
                i6 += i5;
                i7 = i8;
            } else {
                i7 = i;
            }
            i7 += bVar.b;
            if (i7 < this.m.g) {
                int i9 = this.m.f * i7;
                int i10 = i9 + bVar.a;
                i7 = bVar.c + i10;
                if (this.m.f + i9 < i7) {
                    i7 = this.m.f + i9;
                }
                i9 = bVar.c * i;
                int i11 = i10;
                while (i11 < i7) {
                    i10 = i9 + 1;
                    i9 = this.c[this.i[i9] & 255];
                    if (i9 != 0) {
                        iArr[i11] = i9;
                    }
                    i11++;
                    i9 = i10;
                }
            }
        }
        if (this.p && (bVar.g == 0 || bVar.g == 1)) {
            if (this.o == null) {
                this.o = l();
            }
            this.o.setPixels(iArr, 0, i2, 0, 0, i2, i3);
        }
        Bitmap l = l();
        l.setPixels(iArr, 0, i2, 0, 0, i2, i3);
        return l;
    }

    private void a(b bVar) {
        int i;
        int i2;
        if (bVar != null) {
            this.d.position(bVar.j);
        }
        if (bVar == null) {
            i = this.m.f * this.m.g;
        } else {
            i = bVar.c * bVar.d;
        }
        if (this.i == null || this.i.length < i) {
            this.i = new byte[i];
        }
        if (this.f == null) {
            this.f = new short[4096];
        }
        if (this.g == null) {
            this.g = new byte[4096];
        }
        if (this.h == null) {
            this.h = new byte[FragmentTransaction.TRANSIT_FRAGMENT_OPEN];
        }
        int j = j();
        int i3 = 1 << j;
        int i4 = i3 + 1;
        int i5 = i3 + 2;
        int i6 = -1;
        int i7 = j + 1;
        int i8 = (1 << i7) - 1;
        for (i2 = 0; i2 < i3; i2++) {
            this.f[i2] = (short) 0;
            this.g[i2] = (byte) i2;
        }
        i2 = 0;
        int i9 = 0;
        int i10 = 0;
        int i11 = 0;
        int i12 = 0;
        int i13 = i7;
        int i14 = i8;
        int i15 = i5;
        i7 = 0;
        i8 = 0;
        i5 = 0;
        while (i9 < i) {
            if (i8 == 0) {
                i8 = k();
                if (i8 <= 0) {
                    this.q = 3;
                    break;
                }
                i7 = 0;
            }
            i2 += (this.e[i7] & 255) << i12;
            int i16 = i7 + 1;
            int i17 = i8 - 1;
            i7 = i13;
            i8 = i14;
            i13 = i11;
            int i18 = i12 + 8;
            i12 = i2;
            i2 = i5;
            i5 = i15;
            i15 = i18;
            while (i15 >= i7) {
                i11 = i12 & i8;
                i14 = i12 >> i7;
                i15 -= i7;
                if (i11 == i3) {
                    i7 = j + 1;
                    i8 = (1 << i7) - 1;
                    i5 = i3 + 2;
                    i12 = i14;
                    i6 = -1;
                } else if (i11 > i5) {
                    this.q = 3;
                    i11 = i13;
                    i12 = i15;
                    i13 = i7;
                    i15 = i5;
                    i7 = i16;
                    i5 = i2;
                    i2 = i14;
                    i14 = i8;
                    i8 = i17;
                    break;
                } else if (i11 == i4) {
                    i11 = i13;
                    i12 = i15;
                    i13 = i7;
                    i15 = i5;
                    i7 = i16;
                    i5 = i2;
                    i2 = i14;
                    i14 = i8;
                    i8 = i17;
                    break;
                } else if (i6 == -1) {
                    i12 = i10 + 1;
                    this.h[i10] = this.g[i11];
                    i10 = i12;
                    i13 = i11;
                    i6 = i11;
                    i12 = i14;
                } else {
                    if (i11 >= i5) {
                        i12 = i10 + 1;
                        this.h[i10] = (byte) i13;
                        i10 = i12;
                        i13 = i6;
                    } else {
                        i13 = i11;
                    }
                    while (i13 >= i3) {
                        i12 = i10 + 1;
                        this.h[i10] = this.g[i13];
                        short s = this.f[i13];
                        i10 = i12;
                    }
                    i13 = this.g[i13] & 255;
                    i12 = i10 + 1;
                    this.h[i10] = (byte) i13;
                    if (i5 < 4096) {
                        this.f[i5] = (short) i6;
                        this.g[i5] = (byte) i13;
                        i5++;
                        if ((i5 & i8) == 0 && i5 < 4096) {
                            i7++;
                            i8 += i5;
                        }
                    }
                    i10 = i9;
                    while (i12 > 0) {
                        i9 = i12 - 1;
                        i12 = i2 + 1;
                        this.i[i2] = this.h[i9];
                        i10++;
                        i2 = i12;
                        i12 = i9;
                    }
                    i9 = i10;
                    i6 = i11;
                    i10 = i12;
                    i12 = i14;
                }
            }
            i11 = i13;
            i14 = i8;
            i8 = i17;
            i13 = i7;
            i7 = i16;
            i18 = i15;
            i15 = i5;
            i5 = i2;
            i2 = i12;
            i12 = i18;
        }
        for (i7 = i5; i7 < i; i7++) {
            this.i[i7] = (byte) 0;
        }
    }

    private int j() {
        int i = 0;
        try {
            return this.d.get() & 255;
        } catch (Exception e) {
            this.q = 1;
            return i;
        }
    }

    private int k() {
        int j = j();
        int i = 0;
        if (j > 0) {
            while (i < j) {
                int i2 = j - i;
                try {
                    this.d.get(this.e, i, i2);
                    i += i2;
                } catch (Throwable e) {
                    Log.w(a, "Error Reading Block", e);
                    this.q = 1;
                }
            }
        }
        return i;
    }

    private Bitmap l() {
        Bitmap a = this.n.a(this.m.f, this.m.g, b);
        if (a == null) {
            a = Bitmap.createBitmap(this.m.f, this.m.g, b);
        }
        a(a);
        return a;
    }

    @TargetApi(12)
    private static void a(Bitmap bitmap) {
        if (VERSION.SDK_INT >= 12) {
            bitmap.setHasAlpha(true);
        }
    }
}
