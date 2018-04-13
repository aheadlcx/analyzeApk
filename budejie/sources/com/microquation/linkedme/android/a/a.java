package com.microquation.linkedme.android.a;

public class a {
    private int a;
    private int b;
    private int c;
    private int d;
    private int e = 0;
    private int f = -1;
    private int g = -1;
    private int h = Integer.MAX_VALUE;

    public void a(int i) {
        this.f = i;
    }

    public void b(int i) {
        this.g = i;
    }

    public void c(int i) {
        this.h = i;
    }

    public void d(int i) {
        this.c = i;
    }

    public void e(int i) {
        this.d = i;
    }

    public void f(int i) {
        this.b = i;
    }

    public void g(int i) {
        this.e = i;
    }

    public void h(int i) {
        this.a = i;
    }

    public String toString() {
        return this.g + "," + this.f + "," + this.a + "," + this.b + "," + this.c + "," + this.d + "," + this.e + "," + this.h;
    }
}
