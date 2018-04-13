package a.a.a.d;

public final class b extends c {
    private byte[] a;
    private int b;
    private int c;

    public void a(byte[] bArr) {
        c(bArr, 0, bArr.length);
    }

    public void c(byte[] bArr, int i, int i2) {
        this.a = bArr;
        this.b = i;
        this.c = i + i2;
    }

    public void a() {
        this.a = null;
    }

    public int a(byte[] bArr, int i, int i2) throws d {
        int d = d();
        if (i2 > d) {
            i2 = d;
        }
        if (i2 > 0) {
            System.arraycopy(this.a, this.b, bArr, i, i2);
            a(i2);
        }
        return i2;
    }

    public void b(byte[] bArr, int i, int i2) throws d {
        throw new UnsupportedOperationException("No writing allowed!");
    }

    public byte[] b() {
        return this.a;
    }

    public int c() {
        return this.b;
    }

    public int d() {
        return this.c - this.b;
    }

    public void a(int i) {
        this.b += i;
    }
}
