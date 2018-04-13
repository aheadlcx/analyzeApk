package com.airbnb.lottie;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Path.FillType;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.drawable.Drawable.Callback;
import android.support.annotation.FloatRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.LongSparseArray;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class ah extends d {
    private ak c;
    private ah d;
    private final RectF e = new RectF();
    private final List<ah> f = new ArrayList();
    private final Paint g = new Paint(1);
    private final Paint h = new Paint(1);
    private final Paint i = new Paint(1);
    private final Paint j = new Paint(3);
    private final Layer k;
    private final ai l;
    @Nullable
    private ah m;
    private int n;
    private int o;

    ah(Layer layer, ai aiVar, Callback callback) {
        super(callback);
        this.k = layer;
        this.l = aiVar;
        setBounds(aiVar.a());
        if (layer.j() == MatteType.Invert) {
            this.h.setXfermode(new PorterDuffXfermode(Mode.DST_OUT));
        } else {
            this.h.setXfermode(new PorterDuffXfermode(Mode.DST_IN));
        }
        this.i.setXfermode(new PorterDuffXfermode(Mode.DST_IN));
        h();
    }

    private void h() {
        a(this.k.n());
        setBounds(0, 0, this.k.p(), this.k.o());
        a(this.k.m().f());
        k();
        switch (this.k.i()) {
            case Shape:
                i();
                break;
            case PreComp:
                j();
                break;
        }
        if (!(this.k.h() == null || this.k.h().isEmpty())) {
            a(new ak(this.k.h()));
        }
        LongSparseArray longSparseArray = new LongSparseArray();
        for (d dVar : this.b) {
            ah ahVar;
            if (dVar instanceof ah) {
                longSparseArray.put(((ah) dVar).g(), (ah) dVar);
                ahVar = ((ah) dVar).d;
                if (ahVar != null) {
                    longSparseArray.put(ahVar.g(), ahVar);
                }
            }
        }
        for (d dVar2 : this.b) {
            if (dVar2 instanceof ah) {
                ah ahVar2 = (ah) longSparseArray.get(((ah) dVar2).d().k());
                if (ahVar2 != null) {
                    ((ah) dVar2).a(ahVar2);
                }
                ahVar2 = ((ah) dVar2).d;
                if (ahVar2 != null) {
                    ahVar = (ah) longSparseArray.get(ahVar2.d().k());
                    if (ahVar != null) {
                        ahVar2.a(ahVar);
                    }
                }
            }
        }
    }

    private void i() {
        ShapeStroke shapeStroke = null;
        List arrayList = new ArrayList(this.k.l());
        Collections.reverse(arrayList);
        ay ayVar = null;
        be beVar = null;
        j jVar = null;
        for (int i = 0; i < arrayList.size(); i++) {
            Object obj = arrayList.get(i);
            if (obj instanceof az) {
                a(new y((az) obj, ayVar, shapeStroke, beVar, jVar, getCallback()));
            } else if (obj instanceof j) {
                jVar = (j) obj;
            } else if (obj instanceof ay) {
                ayVar = (ay) obj;
            } else if (obj instanceof be) {
                beVar = (be) obj;
            } else if (obj instanceof ShapeStroke) {
                shapeStroke = (ShapeStroke) obj;
            } else if (obj instanceof bd) {
                a(new bc((bd) obj, ayVar, shapeStroke, beVar, a.a(this.l), getCallback()));
            } else if (obj instanceof au) {
                a(new at((au) obj, ayVar, shapeStroke, beVar, a.a(this.l), getCallback()));
            } else if (obj instanceof p) {
                a(new u((p) obj, ayVar, shapeStroke, beVar, a.a(this.l), getCallback()));
            } else if (obj instanceof PolystarShape) {
                a(new ar((PolystarShape) obj, ayVar, shapeStroke, beVar, a.a(this.l), getCallback()));
            }
        }
    }

    private void j() {
        List a = this.l.a(this.k.e());
        if (a != null) {
            int size = a.size() - 1;
            ah ahVar = null;
            while (size >= 0) {
                ah ahVar2;
                Layer layer = (Layer) a.get(size);
                ah ahVar3 = new ah(layer, this.l, getCallback());
                ahVar3.a(this.k.f(), this.k.g());
                if (ahVar != null) {
                    ahVar.b(ahVar3);
                    ahVar2 = null;
                } else {
                    a((d) ahVar3);
                    if (layer.j() == MatteType.Add) {
                        ahVar2 = ahVar3;
                    } else if (layer.j() == MatteType.Invert) {
                        ahVar2 = ahVar3;
                    } else {
                        ahVar2 = ahVar;
                    }
                }
                size--;
                ahVar = ahVar2;
            }
        }
    }

    private void k() {
        if (this.k.b().isEmpty()) {
            setVisible(true, false);
            return;
        }
        n wVar = new w(this.k.b());
        wVar.a();
        wVar.a(new a<Float>(this) {
            final /* synthetic */ ah a;

            {
                this.a = r1;
            }

            public void a(Float f) {
                boolean z;
                ah ahVar = this.a;
                if (f.floatValue() == 1.0f) {
                    z = true;
                } else {
                    z = false;
                }
                ahVar.setVisible(z, false);
            }
        });
        setVisible(((Float) wVar.b()).floatValue() == 1.0f, false);
        a(wVar);
    }

    Layer d() {
        return this.k;
    }

    void a(@Nullable ah ahVar) {
        this.m = ahVar;
    }

    @Nullable
    private ah l() {
        return this.m;
    }

    private void a(int i, int i2) {
        this.n = i;
        this.o = i2;
    }

    private void a(ak akVar) {
        this.c = akVar;
        for (n nVar : akVar.b()) {
            a(nVar);
            nVar.a(this.a);
        }
    }

    void b(ah ahVar) {
        this.d = ahVar;
    }

    public void draw(@NonNull Canvas canvas) {
        if (isVisible() && this.g.getAlpha() != 0) {
            this.f.clear();
            for (ah ahVar = this.m; ahVar != null; ahVar = ahVar.l()) {
                this.f.add(ahVar);
            }
            if (this.n == 0 && this.o == 0) {
                canvas.clipRect(0, 0, this.l.a().width(), this.l.a().height());
            } else {
                canvas.clipRect(0, 0, this.n, this.o);
            }
            int size;
            if (f() || e()) {
                this.e.set(canvas.getClipBounds());
                canvas.saveLayer(this.e, this.g, 31);
                c(canvas);
                for (size = this.f.size() - 1; size >= 0; size--) {
                    a(canvas, (ah) this.f.get(size));
                }
                super.draw(canvas);
                if (f()) {
                    b(canvas);
                }
                if (e()) {
                    canvas.saveLayer(this.e, this.h, 19);
                    this.d.draw(canvas);
                    canvas.restore();
                }
                canvas.restore();
                return;
            }
            int a = a(canvas);
            for (size = this.f.size() - 1; size >= 0; size--) {
                a(canvas, (ah) this.f.get(size));
            }
            c(canvas);
            super.draw(canvas);
            canvas.restoreToCount(a);
        }
    }

    private void b(Canvas canvas) {
        canvas.saveLayer(this.e, this.i, 19);
        for (int size = this.f.size() - 1; size >= 0; size--) {
            a(canvas, (ah) this.f.get(size));
        }
        a(canvas, this);
        int size2 = this.c.a().size();
        for (int i = 0; i < size2; i++) {
            n nVar = (n) this.c.b().get(i);
            Path path = (Path) nVar.b();
            switch (((Mask) this.c.a().get(i)).a()) {
                case MaskModeSubtract:
                    path.setFillType(FillType.INVERSE_WINDING);
                    break;
                default:
                    path.setFillType(FillType.WINDING);
                    break;
            }
            canvas.drawPath((Path) nVar.b(), this.g);
        }
        canvas.restore();
    }

    private void c(Canvas canvas) {
        if (this.l.e()) {
            Bitmap b = c().b(this.k.e());
            if (b != null) {
                canvas.save();
                a(canvas, this);
                canvas.drawBitmap(b, 0.0f, 0.0f, this.j);
                canvas.restore();
            }
        }
    }

    boolean e() {
        return this.d != null;
    }

    boolean f() {
        return (this.c == null || this.c.b().isEmpty()) ? false : true;
    }

    public void a(@FloatRange(from = 0.0d, to = 1.0d) float f) {
        float a = this.k.a() * f;
        super.a(a);
        if (this.d != null) {
            this.d.a(a);
        }
    }

    long g() {
        return this.k.c();
    }

    public String toString() {
        return this.k.toString();
    }
}
