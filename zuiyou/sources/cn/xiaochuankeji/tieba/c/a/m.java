package cn.xiaochuankeji.tieba.c.a;

import cn.xiaochuankeji.tieba.c.c;
import java.nio.FloatBuffer;

public class m extends a {
    protected a a;
    protected a b;

    public m(a aVar, a aVar2) {
        this.a = aVar;
        this.b = aVar2;
    }

    protected void b() {
        super.b();
        this.a.a();
        this.b.a();
    }

    protected void b(int i, int i2) {
        super.b(i, i2);
        this.a.a(i, i2);
        this.b.a(i, i2);
    }

    protected int c(int i, float[] fArr, FloatBuffer floatBuffer, float[] fArr2, FloatBuffer floatBuffer2) {
        super.c(i, fArr, floatBuffer, fArr2, floatBuffer2);
        return this.b.a(this.a.a(i, c.a, floatBuffer, c.a, floatBuffer2), c.a, floatBuffer, c.a, floatBuffer2);
    }

    protected void h() {
        super.h();
        this.a.g();
        this.b.g();
    }
}
