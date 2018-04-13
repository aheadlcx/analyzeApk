package org.ahocorasick.interval;

public class a implements c {
    private int a;
    private int b;

    public a(int i, int i2) {
        this.a = i;
        this.b = i2;
    }

    public int a() {
        return this.a;
    }

    public int b() {
        return this.b;
    }

    public int c() {
        return (this.b - this.a) + 1;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof c)) {
            return false;
        }
        c cVar = (c) obj;
        if (this.a == cVar.a() && this.b == cVar.b()) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (this.a % 100) + (this.b % 100);
    }

    public int compareTo(Object obj) {
        if (!(obj instanceof c)) {
            return -1;
        }
        c cVar = (c) obj;
        int a = this.a - cVar.a();
        return a == 0 ? this.b - cVar.b() : a;
    }

    public String toString() {
        return this.a + ":" + this.b;
    }
}
