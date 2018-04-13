package com.linkedin.urls.detection;

public class b {
    private final char[] a;
    private int b = 0;
    private int c = 0;

    public b(String str) {
        this.a = str.toCharArray();
    }

    public char a() {
        char[] cArr = this.a;
        int i = this.b;
        this.b = i + 1;
        char c = cArr[i];
        return a.g(c) ? ' ' : c;
    }

    public String a(int i) {
        return new String(this.a, this.b, i);
    }

    public char b(int i) {
        if (c(i)) {
            return this.a[this.b + i];
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    public boolean c(int i) {
        return this.a.length >= this.b + i;
    }

    public boolean b() {
        return this.a.length <= this.b;
    }

    public int c() {
        return this.b;
    }

    public void d(int i) {
        int max = Math.max(this.b - i, 0);
        this.c += max;
        this.b = i;
        e(max);
    }

    public void d() {
        this.c++;
        this.b--;
        e(1);
    }

    private void e(int i) {
        if (this.c > this.a.length * 10) {
            if (i < 20) {
                i = 20;
            }
            int max = Math.max(this.b, 0);
            if (max + i > this.a.length) {
                i = this.a.length - max;
            }
            throw new NegativeArraySizeException("Backtracked max amount of characters. Endless loop detected. Bad Text: '" + new String(this.a, max, i) + "'");
        }
    }
}
