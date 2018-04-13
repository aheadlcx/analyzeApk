package cn.xiaochuankeji.tieba.c.a;

import cn.xiaochuankeji.tieba.c.c;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class e extends a {
    private List<a> a;
    private List<a> b;

    public e(List<a> list) {
        this.a = list;
        if (this.a == null) {
            this.a = list;
        } else {
            i();
        }
    }

    public void i() {
        if (this.a != null) {
            if (this.b == null) {
                this.b = new ArrayList();
            } else {
                this.b.clear();
            }
            for (a aVar : this.a) {
                if (aVar instanceof e) {
                    ((e) aVar).i();
                    Collection k = ((e) aVar).k();
                    if (!(k == null || k.isEmpty())) {
                        this.b.addAll(k);
                    }
                } else {
                    this.b.add(aVar);
                }
            }
        }
    }

    public List<a> j() {
        return this.a;
    }

    public List<a> k() {
        return this.b;
    }

    protected void b() {
        super.b();
        for (a aVar : this.b) {
            if (aVar != null) {
                aVar.a();
            }
        }
    }

    protected void b(int i, int i2) {
        super.b(i, i2);
        for (a aVar : this.b) {
            if (aVar != null) {
                aVar.a(i, i2);
            }
        }
    }

    protected int c(int i, float[] fArr, FloatBuffer floatBuffer, float[] fArr2, FloatBuffer floatBuffer2) {
        super.c(i, fArr, floatBuffer, fArr2, floatBuffer2);
        int i2 = -1;
        for (a aVar : this.b) {
            if (aVar != null && aVar.c()) {
                if (i2 == -1) {
                    i2 = aVar.a(i, c.a, floatBuffer, c.a, floatBuffer2);
                } else {
                    i2 = aVar.a(i2, c.a, floatBuffer, c.a, floatBuffer2);
                }
            }
        }
        return i2;
    }

    protected void h() {
        super.h();
        for (a aVar : this.b) {
            if (aVar != null) {
                aVar.g();
            }
        }
        this.b.clear();
        this.a.clear();
    }
}
