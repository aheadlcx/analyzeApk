package cn.xiaochuankeji.tieba.ui.videomaker;

import android.content.Context;
import android.opengl.GLES20;
import android.text.TextUtils;
import cn.xiaochuankeji.tieba.c.a.c;
import cn.xiaochuankeji.tieba.c.a.d;
import cn.xiaochuankeji.tieba.c.a.e;
import cn.xiaochuankeji.tieba.c.a.h;
import cn.xiaochuankeji.tieba.c.a.j;
import cn.xiaochuankeji.tieba.c.a.l;
import cn.xiaochuankeji.tieba.ui.videomaker.CameraSurfaceView.b;
import com.sensetime.stmobile.STBeautifyNative;
import com.sensetime.stmobile.STHumanAction;
import com.sensetime.stmobile.STMobileStickerNative;
import com.sensetime.stmobile.model.STMobile106;
import java.nio.FloatBuffer;

public class a extends cn.xiaochuankeji.tieba.c.a.a {
    private h a = new h();
    private d b = new d(20);
    private c c = new c();
    private j d;
    private STHumanAction e;
    private STBeautifyNative f;
    private int g;
    private STMobileStickerNative h;
    private int i;
    private int j = -1;
    private boolean k;
    private boolean l;
    private boolean m;
    private Context n;
    private a o;
    private a p;

    private static class a {
        public int a;
        public e b;

        private a() {
            this.a = -1;
        }
    }

    public a(Context context) {
        this.c.a(0.0f, 0.0f, 0.0f, 0.3f);
        this.d = new j();
        this.f = new STBeautifyNative();
        this.h = new STMobileStickerNative();
        this.n = context;
        this.o = new a();
        this.p = new a();
    }

    protected void b() {
        super.b();
        this.a.a();
        this.b.a();
        this.c.a();
        this.d.a();
        this.f.createInstance();
        if (this.f.createInstance() == 0) {
            this.f.setParam(1, 0.4f);
            this.f.setParam(3, 0.4f);
            this.f.setParam(4, 0.4f);
            this.f.setParam(5, 0.12f);
            this.f.setParam(6, 0.08f);
            this.f.setParam(7, 0.05f);
        }
        this.g = cn.xiaochuankeji.tieba.c.c.a(3553);
        this.h.createInstance(null, null);
        this.i = cn.xiaochuankeji.tieba.c.c.a(3553);
    }

    protected void b(int i, int i2) {
        super.b(i, i2);
        this.a.a(i, i2);
        this.b.a(i, i2);
        this.c.a(i, i2);
        this.d.a(i, i2);
        cn.xiaochuankeji.tieba.c.c.a(this.g, i, i2, 3553);
        cn.xiaochuankeji.tieba.c.c.a(this.i, i, i2, 3553);
    }

    protected int c(int i, float[] fArr, FloatBuffer floatBuffer, float[] fArr2, FloatBuffer floatBuffer2) {
        STMobile106[] sTMobile106Arr = null;
        super.c(i, fArr, floatBuffer, fArr2, floatBuffer2);
        if (!this.m || this.j == -1) {
            cn.xiaochuankeji.tieba.c.a.a aVar;
            cn.xiaochuankeji.tieba.c.a.a aVar2;
            int a = this.a.a(i, fArr, floatBuffer, cn.xiaochuankeji.tieba.c.c.a(fArr2, cn.xiaochuankeji.tieba.c.c.a()), floatBuffer2);
            if (this.k) {
                STMobile106[] mobileFaces;
                if (this.e != null) {
                    mobileFaces = this.e.getMobileFaces();
                    if (mobileFaces != null && mobileFaces.length > 0) {
                        sTMobile106Arr = new STMobile106[mobileFaces.length];
                    }
                } else {
                    mobileFaces = null;
                }
                if (this.f.processTexture(a, d(), e(), mobileFaces, this.g, sTMobile106Arr) == 0) {
                    a = this.g;
                    if (!(this.e == null || sTMobile106Arr == null || sTMobile106Arr.length == 0)) {
                        this.e.replaceMobile106(sTMobile106Arr);
                    }
                }
            }
            cn.xiaochuankeji.tieba.c.a.a aVar3;
            if (this.o.a < this.p.a) {
                aVar3 = this.o.b;
                aVar = this.p.b;
                aVar2 = aVar3;
            } else {
                aVar3 = this.p.b;
                aVar = this.o.b;
                aVar2 = aVar3;
            }
            if (aVar2 != null) {
                a = aVar2.a(a, cn.xiaochuankeji.tieba.c.c.a, floatBuffer, cn.xiaochuankeji.tieba.c.c.a, floatBuffer2);
            }
            if (aVar != null) {
                a = aVar.a(a, cn.xiaochuankeji.tieba.c.c.a, floatBuffer, cn.xiaochuankeji.tieba.c.c.a, floatBuffer2);
            }
            if (this.l) {
                if (this.h.processTexture(a, this.e, 0, d(), e(), false, this.i) == 0) {
                    a = this.i;
                }
            }
            int a2 = this.d.a(a, cn.xiaochuankeji.tieba.c.c.a, floatBuffer, cn.xiaochuankeji.tieba.c.c.b, floatBuffer2);
            this.j = a2;
            return a2;
        }
        return this.c.a(this.b.a(this.j, cn.xiaochuankeji.tieba.c.c.a, floatBuffer, cn.xiaochuankeji.tieba.c.c.a, floatBuffer2), cn.xiaochuankeji.tieba.c.c.a, floatBuffer, cn.xiaochuankeji.tieba.c.c.a, floatBuffer2);
    }

    protected void h() {
        super.h();
        this.a.g();
        this.b.g();
        this.c.g();
        this.d.g();
        this.f.destroyBeautify();
        GLES20.glDeleteTextures(1, new int[]{this.g}, 0);
        this.h.destroyInstance();
        GLES20.glDeleteTextures(1, new int[]{this.i}, 0);
        a(this.o);
        a(this.p);
        this.j = -1;
    }

    public void a(final boolean z) {
        a(new Runnable(this) {
            final /* synthetic */ a b;

            public void run() {
                this.b.k = z;
            }
        });
    }

    public void a(String str, b bVar) {
        this.l = !TextUtils.isEmpty(str);
        this.h.changeSticker(str);
        if (bVar != null) {
            bVar.a(str, this.l ? this.h.getTriggerAction() : 0);
        }
    }

    public void a(STHumanAction sTHumanAction) {
        this.e = sTHumanAction;
    }

    public void b(final boolean z) {
        a(new Runnable(this) {
            final /* synthetic */ a b;

            public void run() {
                this.b.m = z;
            }
        });
    }

    public void b(final int i, final float f) {
        a(new Runnable(this) {
            final /* synthetic */ a c;

            public void run() {
                if (f == 1.0f) {
                    this.c.b(i);
                } else {
                    this.c.c(i, f);
                }
            }
        });
    }

    private void c(int i, float f) {
        a(c(i).b, 0.0f, f);
        a(c(i + 1).b, f, 1.0f);
    }

    public void a(final int i) {
        a(new Runnable(this) {
            final /* synthetic */ a b;

            public void run() {
                this.b.b(i);
            }
        });
    }

    private void b(int i) {
        int size = i % g.a.size();
        a(c(size).b, 0.0f, 1.0f);
        if (this.o.a == size) {
            a(this.p);
        } else if (this.p.a == size) {
            a(this.o);
        }
    }

    private a c(int i) {
        int size = i % g.a.size();
        if (this.o.a == size) {
            return this.o;
        }
        if (this.p.a == size) {
            return this.p;
        }
        a aVar = null;
        if (this.o.a == -1) {
            aVar = this.o;
        } else if (this.p.a == -1) {
            aVar = this.p;
        }
        a aVar2 = aVar == null ? this.o.a != size + 1 ? this.o : this.p : aVar;
        a(aVar2);
        g.c cVar = (g.c) g.a.get(size);
        aVar2.a = size;
        aVar2.b = cVar.a(this.n);
        aVar2.b.a();
        aVar2.b.a(d(), e());
        return aVar2;
    }

    private void a(e eVar, float f, float f2) {
        for (cn.xiaochuankeji.tieba.c.a.a aVar : eVar.k()) {
            if (aVar instanceof l) {
                ((l) aVar).a(f, f2);
            }
        }
    }

    private void a(a aVar) {
        aVar.a = -1;
        if (aVar.b != null) {
            aVar.b.g();
            aVar.b = null;
        }
    }
}
