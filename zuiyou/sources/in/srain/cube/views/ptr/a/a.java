package in.srain.cube.views.ptr.a;

import android.graphics.PointF;

public class a {
    protected int a = 0;
    private PointF b = new PointF();
    private float c;
    private float d;
    private int e = 0;
    private int f = 0;
    private int g;
    private int h = 0;
    private float i = 1.2f;
    private float j = 1.7f;
    private boolean k = false;
    private int l = -1;
    private int m = 0;

    public boolean a() {
        return this.k;
    }

    public float b() {
        return this.j;
    }

    public void a(float f) {
        this.j = f;
    }

    public void c() {
        this.k = false;
    }

    public void d() {
        this.m = this.e;
    }

    public boolean e() {
        return this.e >= this.m;
    }

    protected void a(float f, float f2, float f3, float f4) {
        c(f3, f4 / this.j);
    }

    public void b(float f) {
        this.i = f;
        this.a = (int) (((float) this.g) * f);
    }

    public float f() {
        return this.i;
    }

    public int g() {
        return this.a;
    }

    public void a(int i) {
        this.i = (((float) this.g) * 1.0f) / ((float) i);
        this.a = i;
    }

    public void a(float f, float f2) {
        this.k = true;
        this.h = this.e;
        this.b.set(f, f2);
    }

    public final void b(float f, float f2) {
        a(f, f2, f - this.b.x, f2 - this.b.y);
        this.b.set(f, f2);
    }

    protected void c(float f, float f2) {
        this.c = f;
        this.d = f2;
    }

    public float h() {
        return this.c;
    }

    public float i() {
        return this.d;
    }

    public int j() {
        return this.f;
    }

    public int k() {
        return this.e;
    }

    public final void b(int i) {
        this.f = this.e;
        this.e = i;
        a(i, this.f);
    }

    protected void a(int i, int i2) {
    }

    public void c(int i) {
        this.g = i;
        l();
    }

    protected void l() {
        this.a = (int) (this.i * ((float) this.g));
    }

    public void a(a aVar) {
        this.e = aVar.e;
        this.f = aVar.f;
        this.g = aVar.g;
    }

    public boolean m() {
        return this.e > 0;
    }

    public boolean n() {
        return this.f == 0 && m();
    }

    public boolean o() {
        return this.f != 0 && r();
    }

    public boolean p() {
        return this.e >= g();
    }

    public boolean q() {
        return this.e != this.h;
    }

    public boolean r() {
        return this.e == 0;
    }

    public boolean s() {
        return this.f < g() && this.e >= g();
    }

    public boolean t() {
        return this.f < this.g && this.e >= this.g;
    }

    public boolean u() {
        return this.e > v();
    }

    public void d(int i) {
        this.l = i;
    }

    public int v() {
        return this.l >= 0 ? this.l : this.g;
    }

    public boolean e(int i) {
        return this.e == i;
    }

    public boolean f(int i) {
        return i < 0;
    }
}
