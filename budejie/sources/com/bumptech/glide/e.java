package com.bumptech.glide;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import com.bumptech.glide.d.g;
import com.bumptech.glide.d.m;
import com.bumptech.glide.f.a;
import com.bumptech.glide.g.a.d;
import com.bumptech.glide.g.b.j;
import com.bumptech.glide.g.f;
import com.bumptech.glide.i.h;
import com.bumptech.glide.load.b;
import com.bumptech.glide.load.c;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

public class e<ModelType, DataType, ResourceType, TranscodeType> implements Cloneable {
    private boolean A;
    private Drawable B;
    private int C;
    protected final Class<ModelType> a;
    protected final Context b;
    protected final i c;
    protected final Class<TranscodeType> d;
    protected final m e;
    protected final g f;
    private a<ModelType, DataType, ResourceType, TranscodeType> g;
    private ModelType h;
    private b i;
    private boolean j;
    private int k;
    private int l;
    private f<? super ModelType, TranscodeType> m;
    private Float n;
    private e<?, ?, ?, TranscodeType> o;
    private Float p;
    private Drawable q;
    private Drawable r;
    private Priority s;
    private boolean t;
    private d<TranscodeType> u;
    private int v;
    private int w;
    private DiskCacheStrategy x;
    private com.bumptech.glide.load.f<ResourceType> y;
    private boolean z;

    public /* synthetic */ Object clone() throws CloneNotSupportedException {
        return g();
    }

    e(com.bumptech.glide.f.f<ModelType, DataType, ResourceType, TranscodeType> fVar, Class<TranscodeType> cls, e<ModelType, ?, ?, ?> eVar) {
        this(eVar.b, eVar.a, fVar, cls, eVar.c, eVar.e, eVar.f);
        this.h = eVar.h;
        this.j = eVar.j;
        this.i = eVar.i;
        this.x = eVar.x;
        this.t = eVar.t;
    }

    e(Context context, Class<ModelType> cls, com.bumptech.glide.f.f<ModelType, DataType, ResourceType, TranscodeType> fVar, Class<TranscodeType> cls2, i iVar, m mVar, g gVar) {
        a aVar = null;
        this.i = com.bumptech.glide.h.a.a();
        this.p = Float.valueOf(1.0f);
        this.s = null;
        this.t = true;
        this.u = com.bumptech.glide.g.a.e.a();
        this.v = -1;
        this.w = -1;
        this.x = DiskCacheStrategy.RESULT;
        this.y = com.bumptech.glide.load.resource.d.b();
        this.b = context;
        this.a = cls;
        this.d = cls2;
        this.c = iVar;
        this.e = mVar;
        this.f = gVar;
        if (fVar != null) {
            aVar = new a(fVar);
        }
        this.g = aVar;
        if (context == null) {
            throw new NullPointerException("Context can't be null");
        } else if (cls != null && fVar == null) {
            throw new NullPointerException("LoadProvider must not be null");
        }
    }

    public e<ModelType, DataType, ResourceType, TranscodeType> b(com.bumptech.glide.load.d<DataType, ResourceType> dVar) {
        if (this.g != null) {
            this.g.a(dVar);
        }
        return this;
    }

    public e<ModelType, DataType, ResourceType, TranscodeType> b(com.bumptech.glide.load.a<DataType> aVar) {
        if (this.g != null) {
            this.g.a(aVar);
        }
        return this;
    }

    public e<ModelType, DataType, ResourceType, TranscodeType> b(DiskCacheStrategy diskCacheStrategy) {
        this.x = diskCacheStrategy;
        return this;
    }

    public e<ModelType, DataType, ResourceType, TranscodeType> b(com.bumptech.glide.load.f<ResourceType>... fVarArr) {
        this.z = true;
        if (fVarArr.length == 1) {
            this.y = fVarArr[0];
        } else {
            this.y = new c(fVarArr);
        }
        return this;
    }

    public e<ModelType, DataType, ResourceType, TranscodeType> h() {
        return a(com.bumptech.glide.g.a.e.a());
    }

    e<ModelType, DataType, ResourceType, TranscodeType> a(d<TranscodeType> dVar) {
        if (dVar == null) {
            throw new NullPointerException("Animation factory must not be null!");
        }
        this.u = dVar;
        return this;
    }

    public e<ModelType, DataType, ResourceType, TranscodeType> d(int i) {
        this.k = i;
        return this;
    }

    public e<ModelType, DataType, ResourceType, TranscodeType> d(Drawable drawable) {
        this.q = drawable;
        return this;
    }

    public e<ModelType, DataType, ResourceType, TranscodeType> c(int i) {
        this.l = i;
        return this;
    }

    public e<ModelType, DataType, ResourceType, TranscodeType> c(Drawable drawable) {
        this.r = drawable;
        return this;
    }

    public e<ModelType, DataType, ResourceType, TranscodeType> b(boolean z) {
        this.t = !z;
        return this;
    }

    public e<ModelType, DataType, ResourceType, TranscodeType> b(int i, int i2) {
        if (h.a(i, i2)) {
            this.w = i;
            this.v = i2;
            return this;
        }
        throw new IllegalArgumentException("Width and height must be Target#SIZE_ORIGINAL or > 0");
    }

    public e<ModelType, DataType, ResourceType, TranscodeType> b(b bVar) {
        if (bVar == null) {
            throw new NullPointerException("Signature must not be null");
        }
        this.i = bVar;
        return this;
    }

    public e<ModelType, DataType, ResourceType, TranscodeType> b(ModelType modelType) {
        this.h = modelType;
        this.j = true;
        return this;
    }

    public e<ModelType, DataType, ResourceType, TranscodeType> g() {
        try {
            e<ModelType, DataType, ResourceType, TranscodeType> eVar = (e) super.clone();
            eVar.g = this.g != null ? this.g.g() : null;
            return eVar;
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    public <Y extends j<TranscodeType>> Y a(Y y) {
        h.a();
        if (y == null) {
            throw new IllegalArgumentException("You must pass in a non null Target");
        } else if (this.j) {
            com.bumptech.glide.g.c request = y.getRequest();
            if (request != null) {
                request.d();
                this.e.b(request);
                request.a();
            }
            request = b((j) y);
            y.setRequest(request);
            this.f.a(y);
            this.e.a(request);
            return y;
        } else {
            throw new IllegalArgumentException("You must first set a model (try #load())");
        }
    }

    public j<TranscodeType> a(ImageView imageView) {
        h.a();
        if (imageView == null) {
            throw new IllegalArgumentException("You must pass in a non null View");
        }
        if (!(this.z || imageView.getScaleType() == null)) {
            switch (e$2.a[imageView.getScaleType().ordinal()]) {
                case 1:
                    f();
                    break;
                case 2:
                case 3:
                case 4:
                    e();
                    break;
            }
        }
        return a(this.c.a(imageView, this.d));
    }

    public com.bumptech.glide.g.a<TranscodeType> c(int i, int i2) {
        com.bumptech.glide.g.a eVar = new com.bumptech.glide.g.e(this.c.g(), i, i2);
        this.c.g().post(new e$1(this, eVar));
        return eVar;
    }

    void f() {
    }

    void e() {
    }

    private Priority a() {
        if (this.s == Priority.LOW) {
            return Priority.NORMAL;
        }
        if (this.s == Priority.NORMAL) {
            return Priority.HIGH;
        }
        return Priority.IMMEDIATE;
    }

    private com.bumptech.glide.g.c b(j<TranscodeType> jVar) {
        if (this.s == null) {
            this.s = Priority.NORMAL;
        }
        return a(jVar, null);
    }

    private com.bumptech.glide.g.c a(j<TranscodeType> jVar, com.bumptech.glide.g.h hVar) {
        com.bumptech.glide.g.c hVar2;
        if (this.o != null) {
            if (this.A) {
                throw new IllegalStateException("You cannot use a request as both the main request and a thumbnail, consider using clone() on the request(s) passed to thumbnail()");
            }
            if (this.o.u.equals(com.bumptech.glide.g.a.e.a())) {
                this.o.u = this.u;
            }
            if (this.o.s == null) {
                this.o.s = a();
            }
            if (h.a(this.w, this.v) && !h.a(this.o.w, this.o.v)) {
                this.o.b(this.w, this.v);
            }
            hVar2 = new com.bumptech.glide.g.h(hVar);
            com.bumptech.glide.g.c a = a(jVar, this.p.floatValue(), this.s, hVar2);
            this.A = true;
            com.bumptech.glide.g.c a2 = this.o.a(jVar, hVar2);
            this.A = false;
            hVar2.a(a, a2);
            return hVar2;
        } else if (this.n == null) {
            return a(jVar, this.p.floatValue(), this.s, hVar);
        } else {
            hVar2 = new com.bumptech.glide.g.h(hVar);
            hVar2.a(a(jVar, this.p.floatValue(), this.s, hVar2), a(jVar, this.n.floatValue(), a(), hVar2));
            return hVar2;
        }
    }

    private com.bumptech.glide.g.c a(j<TranscodeType> jVar, float f, Priority priority, com.bumptech.glide.g.d dVar) {
        return com.bumptech.glide.g.b.a(this.g, this.h, this.i, this.b, priority, jVar, f, this.q, this.k, this.r, this.l, this.B, this.C, this.m, dVar, this.c.b(), this.y, this.d, this.t, this.u, this.w, this.v, this.x);
    }
}
