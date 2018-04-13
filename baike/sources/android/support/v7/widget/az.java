package android.support.v7.widget;

class az {
    static final /* synthetic */ boolean e = (!GridLayout.class.desiredAssertionStatus());
    a[] a = new a[this.f.length];
    int b = (this.a.length - 1);
    a[][] c = this.g.a(this.f);
    int[] d = new int[(this.g.getCount() + 1)];
    final /* synthetic */ a[] f;
    final /* synthetic */ c g;

    az(c cVar, a[] aVarArr) {
        this.g = cVar;
        this.f = aVarArr;
    }

    void a(int i) {
        switch (this.d[i]) {
            case 0:
                this.d[i] = 1;
                for (a aVar : this.c[i]) {
                    a(aVar.span.max);
                    a[] aVarArr = this.a;
                    int i2 = this.b;
                    this.b = i2 - 1;
                    aVarArr[i2] = aVar;
                }
                this.d[i] = 2;
                return;
            case 1:
                if (!e) {
                    throw new AssertionError();
                }
                return;
            default:
                return;
        }
    }

    a[] a() {
        int length = this.c.length;
        for (int i = 0; i < length; i++) {
            a(i);
        }
        if (e || this.b == -1) {
            return this.a;
        }
        throw new AssertionError();
    }
}
