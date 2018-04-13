package com.bumptech.glide.c;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.support.v4.view.ViewCompat;
import android.util.Log;
import com.budejie.www.R$styleable;
import java.io.IOException;
import java.io.OutputStream;

public class a {
    private int a;
    private int b;
    private Integer c = null;
    private int d;
    private int e = -1;
    private int f = 0;
    private boolean g = false;
    private OutputStream h;
    private Bitmap i;
    private byte[] j;
    private byte[] k;
    private int l;
    private byte[] m;
    private boolean[] n = new boolean[256];
    private int o = 7;
    private int p = -1;
    private boolean q = false;
    private boolean r = true;
    private boolean s = false;
    private int t = 10;
    private boolean u;

    public void a(int i) {
        this.f = Math.round(((float) i) / 10.0f);
    }

    public boolean a(Bitmap bitmap) {
        if (bitmap == null || !this.g) {
            return false;
        }
        try {
            if (!this.s) {
                a(bitmap.getWidth(), bitmap.getHeight());
            }
            this.i = bitmap;
            c();
            b();
            if (this.r) {
                f();
                h();
                if (this.e >= 0) {
                    g();
                }
            }
            d();
            e();
            if (!this.r) {
                h();
            }
            i();
            this.r = false;
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public boolean a() {
        if (!this.g) {
            return false;
        }
        boolean z;
        this.g = false;
        try {
            this.h.write(59);
            this.h.flush();
            if (this.q) {
                this.h.close();
            }
            z = true;
        } catch (IOException e) {
            z = false;
        }
        this.d = 0;
        this.h = null;
        this.i = null;
        this.j = null;
        this.k = null;
        this.m = null;
        this.q = false;
        this.r = true;
        return z;
    }

    public void a(int i, int i2) {
        if (!this.g || this.r) {
            this.a = i;
            this.b = i2;
            if (this.a < 1) {
                this.a = R$styleable.Theme_Custom_last_refresh_item_text_theme;
            }
            if (this.b < 1) {
                this.b = R$styleable.Theme_Custom_shape_cmt_like4_bg;
            }
            this.s = true;
        }
    }

    public boolean a(OutputStream outputStream) {
        if (outputStream == null) {
            return false;
        }
        boolean z = true;
        this.q = false;
        this.h = outputStream;
        try {
            a("GIF89a");
        } catch (IOException e) {
            z = false;
        }
        this.g = z;
        return z;
    }

    private void b() {
        int length = this.j.length;
        int i = length / 3;
        this.k = new byte[i];
        c cVar = new c(this.j, length, this.t);
        this.m = cVar.d();
        for (length = 0; length < this.m.length; length += 3) {
            byte b = this.m[length];
            this.m[length] = this.m[length + 2];
            this.m[length + 2] = b;
            this.n[length / 3] = false;
        }
        int i2 = 0;
        for (length = 0; length < i; length++) {
            int i3 = i2 + 1;
            int i4 = i3 + 1;
            i2 = i4 + 1;
            int a = cVar.a(this.j[i2] & 255, this.j[i3] & 255, this.j[i4] & 255);
            this.n[a] = true;
            this.k[length] = (byte) a;
        }
        this.j = null;
        this.l = 8;
        this.o = 7;
        if (this.c != null) {
            this.d = b(this.c.intValue());
        } else if (this.u) {
            this.d = b(0);
        }
    }

    private int b(int i) {
        int i2 = 0;
        if (this.m == null) {
            return -1;
        }
        int red = Color.red(i);
        int green = Color.green(i);
        int blue = Color.blue(i);
        int i3 = ViewCompat.MEASURED_STATE_TOO_SMALL;
        int length = this.m.length;
        int i4 = 0;
        while (i2 < length) {
            int i5 = i2 + 1;
            i2 = red - (this.m[i2] & 255);
            int i6 = i5 + 1;
            int i7 = green - (this.m[i5] & 255);
            i5 = blue - (this.m[i6] & 255);
            i2 = ((i2 * i2) + (i7 * i7)) + (i5 * i5);
            i7 = i6 / 3;
            if (!this.n[i7] || i2 >= i3) {
                i2 = i3;
                i3 = i4;
            } else {
                i3 = i7;
            }
            i4 = i3;
            i3 = i2;
            i2 = i6 + 1;
        }
        return i4;
    }

    private void c() {
        boolean z = false;
        int width = this.i.getWidth();
        int height = this.i.getHeight();
        if (!(width == this.a && height == this.b)) {
            Bitmap createBitmap = Bitmap.createBitmap(this.a, this.b, Config.ARGB_8888);
            new Canvas(createBitmap).drawBitmap(createBitmap, 0.0f, 0.0f, null);
            this.i = createBitmap;
        }
        int[] iArr = new int[(width * height)];
        this.i.getPixels(iArr, 0, width, 0, 0, width, height);
        this.j = new byte[(iArr.length * 3)];
        this.u = false;
        int i = 0;
        int i2 = 0;
        for (int i3 : iArr) {
            if (i3 == 0) {
                i++;
            }
            int i4 = i2 + 1;
            this.j[i2] = (byte) (i3 & 255);
            height = i4 + 1;
            this.j[i4] = (byte) ((i3 >> 8) & 255);
            i2 = height + 1;
            this.j[height] = (byte) ((i3 >> 16) & 255);
        }
        double length = ((double) (i * 100)) / ((double) iArr.length);
        if (length > 4.0d) {
            z = true;
        }
        this.u = z;
        if (Log.isLoggable("AnimatedGifEncoder", 3)) {
            Log.d("AnimatedGifEncoder", "got pixels for frame with " + length + "% transparent pixels");
        }
    }

    private void d() throws IOException {
        int i;
        int i2;
        this.h.write(33);
        this.h.write(R$styleable.Theme_Custom_gray_click_state);
        this.h.write(4);
        if (this.c != null || this.u) {
            i = 1;
            i2 = 2;
        } else {
            i2 = 0;
            i = 0;
        }
        if (this.p >= 0) {
            i2 = this.p & 7;
        }
        this.h.write((((i2 << 2) | 0) | 0) | i);
        c(this.f);
        this.h.write(this.d);
        this.h.write(0);
    }

    private void e() throws IOException {
        this.h.write(44);
        c(0);
        c(0);
        c(this.a);
        c(this.b);
        if (this.r) {
            this.h.write(0);
        } else {
            this.h.write(this.o | 128);
        }
    }

    private void f() throws IOException {
        c(this.a);
        c(this.b);
        this.h.write(this.o | R$styleable.Theme_Custom_shape_cmt_like4_bg);
        this.h.write(0);
        this.h.write(0);
    }

    private void g() throws IOException {
        this.h.write(33);
        this.h.write(255);
        this.h.write(11);
        a("NETSCAPE2.0");
        this.h.write(3);
        this.h.write(1);
        c(this.e);
        this.h.write(0);
    }

    private void h() throws IOException {
        this.h.write(this.m, 0, this.m.length);
        int length = 768 - this.m.length;
        for (int i = 0; i < length; i++) {
            this.h.write(0);
        }
    }

    private void i() throws IOException {
        new b(this.a, this.b, this.k, this.l).b(this.h);
    }

    private void c(int i) throws IOException {
        this.h.write(i & 255);
        this.h.write((i >> 8) & 255);
    }

    private void a(String str) throws IOException {
        for (int i = 0; i < str.length(); i++) {
            this.h.write((byte) str.charAt(i));
        }
    }
}
