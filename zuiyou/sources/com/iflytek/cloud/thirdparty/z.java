package com.iflytek.cloud.thirdparty;

import java.util.concurrent.ConcurrentLinkedQueue;

public class z {
    private int a;
    private a b;
    private ConcurrentLinkedQueue<byte[]> c;
    private long d;

    class a {
        final /* synthetic */ z a;
        private byte[] b;
        private int c;
        private int d;

        public a(z zVar, int i) {
            this.a = zVar;
            this.d = i;
            this.b = new byte[i];
        }

        public int a(byte[] bArr, int i) {
            if (bArr == null || i < 0 || i >= bArr.length) {
                return -1;
            }
            int length = bArr.length - i;
            int d = d();
            if (length <= d) {
                d = length;
            }
            System.arraycopy(bArr, i, this.b, this.c, d);
            this.c += d;
            return d + i;
        }

        public void a() {
            this.b = new byte[this.d];
            this.c = 0;
        }

        public byte[] b() {
            return this.b;
        }

        public boolean c() {
            return this.c == this.d;
        }

        public int d() {
            return this.d - this.c;
        }
    }

    public z() {
        this.a = 320;
        this.d = 0;
        this.c = new ConcurrentLinkedQueue();
        this.b = new a(this, this.a);
    }

    public z(int i) {
        this.a = 320;
        this.d = 0;
        this.c = new ConcurrentLinkedQueue();
        this.a = i;
        this.b = new a(this, this.a);
    }

    public void a() {
        this.c.clear();
        this.d = 0;
    }

    public void a(byte[] bArr, int i) {
        int i2 = 0;
        while (i2 != i) {
            i2 = this.b.a(bArr, i2);
            if (this.b.c()) {
                this.c.add(this.b.b());
                this.b.a();
            }
        }
        this.d += (long) i;
    }

    public byte[] a(int i) {
        if (i > this.c.size()) {
            i = this.c.size();
        }
        if (i == 0) {
            return null;
        }
        a aVar = new a(this, this.a * i);
        for (int i2 = 0; i2 < i; i2++) {
            aVar.a((byte[]) this.c.poll(), 0);
        }
        return aVar.b();
    }
}
