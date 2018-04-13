package qsbk.app.ticker;

import android.graphics.Canvas;
import android.graphics.Paint;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import java.util.Map;

class a {
    private static a a = a.UNKNOWN;
    private final char[] b;
    private final Map<Character, Integer> c;
    private final c d;
    private char e = '\u0000';
    private char f = '\u0000';
    private char g = '\u0000';
    private int h = -1;
    private int i;
    private int j;
    private int k;
    private float l;
    private float m;
    private int n = -1;
    private float o;
    private float p;
    private float q;
    private float r;
    private float s;
    private float t;
    private int u;

    private enum a {
        UP,
        DOWN,
        UNKNOWN
    }

    a(char[] cArr, Map<Character, Integer> map, c cVar) {
        this.b = cArr;
        this.c = map;
        this.d = cVar;
    }

    boolean a(char c) {
        int i;
        boolean z = false;
        this.f = c;
        this.o = this.p;
        this.q = this.d.a(c);
        this.r = Math.max(this.o, this.q);
        if (this.e > this.f && this.f >= '0' && this.f < '9') {
            this.g = this.e;
            this.e = (char) (this.f + 1);
        } else if (this.e < this.f && this.f > '0' && this.f <= '9') {
            this.g = this.e;
            this.e = (char) (this.f - 1);
        }
        a = a.UNKNOWN;
        for (i = 0; i < this.b.length; i++) {
            if (c == this.b[i]) {
                this.n = i;
                break;
            }
        }
        for (i = 0; i < this.b.length; i++) {
            if (this.g == this.b[i]) {
                this.h = i;
                break;
            }
        }
        f();
        if (this.j >= this.i) {
            z = true;
        }
        this.u = z ? 1 : -1;
        this.t = this.s;
        this.s = 0.0f;
        return z;
    }

    char a() {
        return this.e;
    }

    char b() {
        return this.f;
    }

    float c() {
        return this.p;
    }

    float d() {
        return this.r;
    }

    private void f() {
        if (this.c.containsKey(Character.valueOf(this.e)) && this.c.containsKey(Character.valueOf(this.f))) {
            this.i = ((Integer) this.c.get(Character.valueOf(this.e))).intValue();
            this.j = ((Integer) this.c.get(Character.valueOf(this.f))).intValue();
            return;
        }
        throw new IllegalStateException("No indices found for chars: " + this.e + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + this.f);
    }

    void e() {
        this.r = this.p;
    }

    void a(float f) {
        if (f == 1.0f) {
            this.e = this.f;
            this.s = 0.0f;
            this.t = 0.0f;
            a = a.UNKNOWN;
        }
        float b = this.d.b();
        float abs = ((((float) Math.abs(this.j - this.i)) * b) * f) / b;
        this.l = (((abs - ((float) ((int) abs))) * b) * ((float) this.u)) + (this.t * (1.0f - f));
        if (a.equals(a.UNKNOWN)) {
            if (this.l < 0.0f) {
                a = a.UP;
            } else if (this.l > 0.0f) {
                a = a.DOWN;
            }
        }
        if (a.equals(a.UP)) {
            this.l = this.l > 0.0f ? -this.l : this.l;
        } else if (a.equals(a.DOWN)) {
            this.l = this.l > 0.0f ? this.l : -this.l;
        }
        this.k = this.i + (((int) abs) * this.u);
        this.m = b;
        this.p = this.o + ((this.q - this.o) * f);
    }

    void a(Canvas canvas, Paint paint) {
        if (this.n != -1) {
            char[] cArr = this.b;
            int i = (this.k == this.n || this.h == -1) ? this.n : this.h;
            if (a(canvas, paint, cArr, i, this.l)) {
                char[] cArr2 = this.b;
                int i2 = (this.k == this.n || this.h == -1) ? this.n : this.h;
                this.e = cArr2[i2];
                this.s = this.l;
            }
            a(canvas, paint, this.b, this.n, this.l - this.m);
            a(canvas, paint, this.b, this.n, this.l + this.m);
            return;
        }
        if (a(canvas, paint, this.b, this.k, this.l)) {
            this.e = this.b[this.k];
            this.s = this.l;
        }
        a(canvas, paint, this.b, this.k + 1, this.l - this.m);
        a(canvas, paint, this.b, this.k - 1, this.l + this.m);
    }

    private boolean a(Canvas canvas, Paint paint, char[] cArr, int i, float f) {
        if (i < 0 || i >= cArr.length) {
            return false;
        }
        canvas.drawText(cArr, i, 1, 0.0f, f, paint);
        return true;
    }
}
