package okio;

import javax.annotation.Nullable;

final class n {
    final byte[] a;
    int b;
    int c;
    boolean d;
    boolean e;
    n f;
    n g;

    n() {
        this.a = new byte[8192];
        this.e = true;
        this.d = false;
    }

    n(n nVar) {
        this(nVar.a, nVar.b, nVar.c);
        nVar.d = true;
    }

    n(byte[] bArr, int i, int i2) {
        this.a = bArr;
        this.b = i;
        this.c = i2;
        this.e = false;
        this.d = true;
    }

    @Nullable
    public n pop() {
        n nVar = this.f != this ? this.f : null;
        this.g.f = this.f;
        this.f.g = this.g;
        this.f = null;
        this.g = null;
        return nVar;
    }

    public n push(n nVar) {
        nVar.g = this;
        nVar.f = this.f;
        this.f.g = nVar;
        this.f = nVar;
        return nVar;
    }

    public n split(int i) {
        if (i <= 0 || i > this.c - this.b) {
            throw new IllegalArgumentException();
        }
        n nVar;
        if (i >= 1024) {
            nVar = new n(this);
        } else {
            nVar = o.a();
            System.arraycopy(this.a, this.b, nVar.a, 0, i);
        }
        nVar.c = nVar.b + i;
        this.b += i;
        this.g.push(nVar);
        return nVar;
    }

    public void compact() {
        if (this.g == this) {
            throw new IllegalStateException();
        } else if (this.g.e) {
            int i = this.c - this.b;
            if (i <= (this.g.d ? 0 : this.g.b) + (8192 - this.g.c)) {
                writeTo(this.g, i);
                pop();
                o.a(this);
            }
        }
    }

    public void writeTo(n nVar, int i) {
        if (nVar.e) {
            if (nVar.c + i > 8192) {
                if (nVar.d) {
                    throw new IllegalArgumentException();
                } else if ((nVar.c + i) - nVar.b > 8192) {
                    throw new IllegalArgumentException();
                } else {
                    System.arraycopy(nVar.a, nVar.b, nVar.a, 0, nVar.c - nVar.b);
                    nVar.c -= nVar.b;
                    nVar.b = 0;
                }
            }
            System.arraycopy(this.a, this.b, nVar.a, nVar.c, i);
            nVar.c += i;
            this.b += i;
            return;
        }
        throw new IllegalArgumentException();
    }
}
