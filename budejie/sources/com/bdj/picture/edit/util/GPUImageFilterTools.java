package com.bdj.picture.edit.util;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.graphics.BitmapFactory;
import android.graphics.PointF;
import com.bdj.picture.edit.a.g;
import java.util.LinkedList;
import java.util.List;
import jp.co.cyberagent.android.gpuimage.aa;
import jp.co.cyberagent.android.gpuimage.ab;
import jp.co.cyberagent.android.gpuimage.ac;
import jp.co.cyberagent.android.gpuimage.ad;
import jp.co.cyberagent.android.gpuimage.ae;
import jp.co.cyberagent.android.gpuimage.af;
import jp.co.cyberagent.android.gpuimage.ag;
import jp.co.cyberagent.android.gpuimage.ah;
import jp.co.cyberagent.android.gpuimage.ai;
import jp.co.cyberagent.android.gpuimage.aj;
import jp.co.cyberagent.android.gpuimage.ak;
import jp.co.cyberagent.android.gpuimage.al;
import jp.co.cyberagent.android.gpuimage.am;
import jp.co.cyberagent.android.gpuimage.an;
import jp.co.cyberagent.android.gpuimage.ao;
import jp.co.cyberagent.android.gpuimage.ap;
import jp.co.cyberagent.android.gpuimage.aq;
import jp.co.cyberagent.android.gpuimage.ar;
import jp.co.cyberagent.android.gpuimage.as;
import jp.co.cyberagent.android.gpuimage.au;
import jp.co.cyberagent.android.gpuimage.av;
import jp.co.cyberagent.android.gpuimage.aw;
import jp.co.cyberagent.android.gpuimage.ax;
import jp.co.cyberagent.android.gpuimage.ay;
import jp.co.cyberagent.android.gpuimage.az;
import jp.co.cyberagent.android.gpuimage.ba;
import jp.co.cyberagent.android.gpuimage.bb;
import jp.co.cyberagent.android.gpuimage.bc;
import jp.co.cyberagent.android.gpuimage.bd;
import jp.co.cyberagent.android.gpuimage.bf;
import jp.co.cyberagent.android.gpuimage.bg;
import jp.co.cyberagent.android.gpuimage.bh;
import jp.co.cyberagent.android.gpuimage.bi;
import jp.co.cyberagent.android.gpuimage.bj;
import jp.co.cyberagent.android.gpuimage.bk;
import jp.co.cyberagent.android.gpuimage.bl;
import jp.co.cyberagent.android.gpuimage.bm;
import jp.co.cyberagent.android.gpuimage.bn;
import jp.co.cyberagent.android.gpuimage.bo;
import jp.co.cyberagent.android.gpuimage.bp;
import jp.co.cyberagent.android.gpuimage.bq;
import jp.co.cyberagent.android.gpuimage.br;
import jp.co.cyberagent.android.gpuimage.bs;
import jp.co.cyberagent.android.gpuimage.bt;
import jp.co.cyberagent.android.gpuimage.bu;
import jp.co.cyberagent.android.gpuimage.bx;
import jp.co.cyberagent.android.gpuimage.by;
import jp.co.cyberagent.android.gpuimage.bz;
import jp.co.cyberagent.android.gpuimage.d;
import jp.co.cyberagent.android.gpuimage.e;
import jp.co.cyberagent.android.gpuimage.f;
import jp.co.cyberagent.android.gpuimage.h;
import jp.co.cyberagent.android.gpuimage.i;
import jp.co.cyberagent.android.gpuimage.j;
import jp.co.cyberagent.android.gpuimage.k;
import jp.co.cyberagent.android.gpuimage.l;
import jp.co.cyberagent.android.gpuimage.m;
import jp.co.cyberagent.android.gpuimage.n;
import jp.co.cyberagent.android.gpuimage.p;
import jp.co.cyberagent.android.gpuimage.q;
import jp.co.cyberagent.android.gpuimage.r;
import jp.co.cyberagent.android.gpuimage.s;
import jp.co.cyberagent.android.gpuimage.t;
import jp.co.cyberagent.android.gpuimage.u;
import jp.co.cyberagent.android.gpuimage.v;
import jp.co.cyberagent.android.gpuimage.w;
import jp.co.cyberagent.android.gpuimage.x;
import jp.co.cyberagent.android.gpuimage.y;
import jp.co.cyberagent.android.gpuimage.z;

public class GPUImageFilterTools {

    /* renamed from: com.bdj.picture.edit.util.GPUImageFilterTools$1 */
    static class AnonymousClass1 implements OnClickListener {
        final /* synthetic */ c a;
        final /* synthetic */ Context b;
        final /* synthetic */ b c;

        public void onClick(DialogInterface dialogInterface, int i) {
            this.a.a(GPUImageFilterTools.a(this.b, (FilterType) this.c.a.get(i)));
        }
    }

    public enum FilterType {
        NORMAL,
        CONTRAST,
        GRAYSCALE,
        SHARPEN,
        SEPIA,
        SOBEL_EDGE_DETECTION,
        THREE_X_THREE_CONVOLUTION,
        FILTER_GROUP,
        EMBOSS,
        POSTERIZE,
        GAMMA,
        BRIGHTNESS,
        INVERT,
        HUE,
        PIXELATION,
        SATURATION,
        EXPOSURE,
        HIGHLIGHT_SHADOW,
        MONOCHROME,
        OPACITY,
        RGB,
        WHITE_BALANCE,
        VIGNETTE,
        TONE_CURVE,
        BLEND_COLOR_BURN,
        BLEND_COLOR_DODGE,
        BLEND_DARKEN,
        BLEND_DIFFERENCE,
        BLEND_DISSOLVE,
        BLEND_EXCLUSION,
        BLEND_SOURCE_OVER,
        BLEND_HARD_LIGHT,
        BLEND_LIGHTEN,
        BLEND_ADD,
        BLEND_DIVIDE,
        BLEND_MULTIPLY,
        BLEND_OVERLAY,
        BLEND_SCREEN,
        BLEND_ALPHA,
        BLEND_COLOR,
        BLEND_HUE,
        BLEND_SATURATION,
        BLEND_LUMINOSITY,
        BLEND_LINEAR_BURN,
        BLEND_SOFT_LIGHT,
        BLEND_SUBTRACT,
        BLEND_CHROMA_KEY,
        BLEND_NORMAL,
        LOOKUP_AMATORKA,
        GAUSSIAN_BLUR,
        CROSSHATCH,
        BOX_BLUR,
        CGA_COLORSPACE,
        DILATION,
        KUWAHARA,
        RGB_DILATION,
        SKETCH,
        TOON,
        SMOOTH_TOON,
        BULGE_DISTORTION,
        GLASS_SPHERE,
        HAZE,
        LAPLACIAN,
        NON_MAXIMUM_SUPPRESSION,
        SPHERE_REFRACTION,
        SWIRL,
        WEAK_PIXEL_INCLUSION,
        FALSE_COLOR,
        COLOR_BALANCE,
        LEVELS_FILTER_MIN
    }

    public static class a {
        private final a<? extends jp.co.cyberagent.android.gpuimage.ab> a;

        private abstract class a<T extends jp.co.cyberagent.android.gpuimage.ab> {
            final /* synthetic */ a a;
            private T b;

            public abstract void a(int i);

            private a(a aVar) {
                this.a = aVar;
            }

            public a<T> a(jp.co.cyberagent.android.gpuimage.ab abVar) {
                this.b = abVar;
                return this;
            }

            public T a() {
                return this.b;
            }

            protected float a(int i, float f, float f2) {
                return (((f2 - f) * ((float) i)) / 100.0f) + f;
            }

            protected int a(int i, int i2, int i3) {
                return (((i3 - i2) * i) / 100) + i2;
            }
        }

        private class aa extends a<bp> {
            final /* synthetic */ a b;

            private aa(a aVar) {
                this.b = aVar;
                super();
            }

            public void a(int i) {
                ((bp) a()).b(a(i, 0.0f, 1.0f));
            }
        }

        private class ab extends a<br> {
            final /* synthetic */ a b;

            private ab(a aVar) {
                this.b = aVar;
                super();
            }

            public void a(int i) {
                ((br) a()).b(a(i, 0.0f, 2.0f));
            }
        }

        private class ac extends a<bx> {
            final /* synthetic */ a b;

            private ac(a aVar) {
                this.b = aVar;
                super();
            }

            public void a(int i) {
                ((bx) a()).a(a(i, 0.0f, 1.0f));
            }
        }

        private class ad extends a<bz> {
            final /* synthetic */ a b;

            private ad(a aVar) {
                this.b = aVar;
                super();
            }

            public void a(int i) {
                ((bz) a()).a(a(i, 2000.0f, 8000.0f));
            }
        }

        private class b extends a<jp.co.cyberagent.android.gpuimage.f> {
            final /* synthetic */ a b;

            private b(a aVar) {
                this.b = aVar;
                super();
            }

            public void a(int i) {
                ((jp.co.cyberagent.android.gpuimage.f) a()).a(a(i, -1.0f, 1.0f));
            }
        }

        private class c extends a<jp.co.cyberagent.android.gpuimage.g> {
            final /* synthetic */ a b;

            private c(a aVar) {
                this.b = aVar;
                super();
            }

            public void a(int i) {
                ((jp.co.cyberagent.android.gpuimage.g) a()).a(a(i, 0.0f, 1.0f));
                ((jp.co.cyberagent.android.gpuimage.g) a()).b(a(i, -1.0f, 1.0f));
            }
        }

        private class d extends a<jp.co.cyberagent.android.gpuimage.j> {
            final /* synthetic */ a b;

            private d(a aVar) {
                this.b = aVar;
                super();
            }

            public void a(int i) {
                ((jp.co.cyberagent.android.gpuimage.j) a()).b(new float[]{a(i, 0.0f, 1.0f), a(i / 2, 0.0f, 1.0f), a(i / 3, 0.0f, 1.0f)});
            }
        }

        private class e extends a<jp.co.cyberagent.android.gpuimage.p> {
            final /* synthetic */ a b;

            private e(a aVar) {
                this.b = aVar;
                super();
            }

            public void a(int i) {
                ((jp.co.cyberagent.android.gpuimage.p) a()).a(a(i, 0.0f, 2.0f));
            }
        }

        private class f extends a<jp.co.cyberagent.android.gpuimage.q> {
            final /* synthetic */ a b;

            private f(a aVar) {
                this.b = aVar;
                super();
            }

            public void a(int i) {
                ((jp.co.cyberagent.android.gpuimage.q) a()).a(a(i, 0.0f, 0.06f));
                ((jp.co.cyberagent.android.gpuimage.q) a()).b(a(i, 0.0f, 0.006f));
            }
        }

        private class g extends a<jp.co.cyberagent.android.gpuimage.v> {
            final /* synthetic */ a b;

            private g(a aVar) {
                this.b = aVar;
                super();
            }

            public void a(int i) {
                ((jp.co.cyberagent.android.gpuimage.v) a()).a(a(i, 0.0f, 1.0f));
            }
        }

        private class h extends a<jp.co.cyberagent.android.gpuimage.x> {
            final /* synthetic */ a b;

            private h(a aVar) {
                this.b = aVar;
                super();
            }

            public void a(int i) {
                ((jp.co.cyberagent.android.gpuimage.x) a()).b(a(i, 0.0f, 4.0f));
            }
        }

        private class i extends a<jp.co.cyberagent.android.gpuimage.z> {
            final /* synthetic */ a b;

            private i(a aVar) {
                this.b = aVar;
                super();
            }

            public void a(int i) {
                ((jp.co.cyberagent.android.gpuimage.z) a()).a(a(i, -10.0f, 10.0f));
            }
        }

        private class j extends a<jp.co.cyberagent.android.gpuimage.b> {
            final /* synthetic */ a b;

            private j(a aVar) {
                this.b = aVar;
                super();
            }

            public void a(int i) {
                ((jp.co.cyberagent.android.gpuimage.b) a()).a(a(i, 0.0f, 5.0f));
            }
        }

        private class k extends a<jp.co.cyberagent.android.gpuimage.ad> {
            final /* synthetic */ a b;

            private k(a aVar) {
                this.b = aVar;
                super();
            }

            public void a(int i) {
                ((jp.co.cyberagent.android.gpuimage.ad) a()).a(a(i, 0.0f, 3.0f));
            }
        }

        private class l extends a<ae> {
            final /* synthetic */ a b;

            private l(a aVar) {
                this.b = aVar;
                super();
            }

            public void a(int i) {
                ((ae) a()).a(a(i, 0.0f, 1.0f));
            }
        }

        private class m extends a<af> {
            final /* synthetic */ a b;

            private m(a aVar) {
                this.b = aVar;
                super();
            }

            public void a(int i) {
                ((af) a()).b(a(i, 0.0f, 1.0f));
            }
        }

        private class n extends a<ai> {
            final /* synthetic */ a b;

            private n(a aVar) {
                this.b = aVar;
                super();
            }

            public void a(int i) {
                ((ai) a()).a(a(i, -0.3f, 0.3f));
                ((ai) a()).b(a(i, -0.3f, 0.3f));
            }
        }

        private class o extends a<aj> {
            final /* synthetic */ a b;

            private o(a aVar) {
                this.b = aVar;
                super();
            }

            public void a(int i) {
                ((aj) a()).b(a(i, 0.0f, 1.0f));
                ((aj) a()).a(a(i, 0.0f, 1.0f));
            }
        }

        private class p extends a<al> {
            final /* synthetic */ a b;

            private p(a aVar) {
                this.b = aVar;
                super();
            }

            public void a(int i) {
                ((al) a()).a(a(i, 0.0f, 360.0f));
            }
        }

        private class q extends a<ao> {
            final /* synthetic */ a b;

            private q(a aVar) {
                this.b = aVar;
                super();
            }

            public void a(int i) {
                ((ao) a()).a(0.0f, a(i, 0.0f, 1.0f), 1.0f);
            }
        }

        private class r extends a<au> {
            final /* synthetic */ a b;

            private r(a aVar) {
                this.b = aVar;
                super();
            }

            public void a(int i) {
                ((au) a()).a(a(i, 0.0f, 1.0f));
            }
        }

        private class s extends a<ay> {
            final /* synthetic */ a b;

            private s(a aVar) {
                this.b = aVar;
                super();
            }

            public void a(int i) {
                ((ay) a()).a(a(i, 0.0f, 1.0f));
            }
        }

        private class t extends a<ba> {
            final /* synthetic */ a b;

            private t(a aVar) {
                this.b = aVar;
                super();
            }

            public void a(int i) {
                ((ba) a()).a(a(i, 1.0f, 100.0f));
            }
        }

        private class u extends a<bb> {
            final /* synthetic */ a b;

            private u(a aVar) {
                this.b = aVar;
                super();
            }

            public void a(int i) {
                ((bb) a()).a(a(i, 1, 50));
            }
        }

        private class v extends a<bd> {
            final /* synthetic */ a b;

            private v(a aVar) {
                this.b = aVar;
                super();
            }

            public void a(int i) {
                ((bd) a()).a(a(i, 0.0f, 1.0f));
            }
        }

        private class w extends a<bg> {
            final /* synthetic */ a b;

            private w(a aVar) {
                this.b = aVar;
                super();
            }

            public void a(int i) {
                ((bg) a()).a(a(i, 0.0f, 2.0f));
            }
        }

        private class x extends a<bi> {
            final /* synthetic */ a b;

            private x(a aVar) {
                this.b = aVar;
                super();
            }

            public void a(int i) {
                ((bi) a()).a(a(i, 0.0f, 2.0f));
            }
        }

        private class y extends a<bj> {
            final /* synthetic */ a b;

            private y(a aVar) {
                this.b = aVar;
                super();
            }

            public void a(int i) {
                ((bj) a()).a(a(i, -4.0f, 4.0f));
            }
        }

        private class z extends a<bm> {
            final /* synthetic */ a b;

            private z(a aVar) {
                this.b = aVar;
                super();
            }

            public void a(int i) {
                ((bm) a()).a(a(i, 0.0f, 5.0f));
            }
        }

        public a(jp.co.cyberagent.android.gpuimage.ab abVar) {
            if (abVar instanceof bj) {
                this.a = new y().a(abVar);
            } else if (abVar instanceof bi) {
                this.a = new x().a(abVar);
            } else if (abVar instanceof jp.co.cyberagent.android.gpuimage.p) {
                this.a = new e().a(abVar);
            } else if (abVar instanceof jp.co.cyberagent.android.gpuimage.ad) {
                this.a = new k().a(abVar);
            } else if (abVar instanceof jp.co.cyberagent.android.gpuimage.f) {
                this.a = new b().a(abVar);
            } else if (abVar instanceof bm) {
                this.a = new z().a(abVar);
            } else if (abVar instanceof jp.co.cyberagent.android.gpuimage.x) {
                this.a = new h().a(abVar);
            } else if (abVar instanceof jp.co.cyberagent.android.gpuimage.b) {
                this.a = new j().a(abVar);
            } else if (abVar instanceof al) {
                this.a = new p().a(abVar);
            } else if (abVar instanceof bb) {
                this.a = new u().a(abVar);
            } else if (abVar instanceof ba) {
                this.a = new t().a(abVar);
            } else if (abVar instanceof bg) {
                this.a = new w().a(abVar);
            } else if (abVar instanceof jp.co.cyberagent.android.gpuimage.z) {
                this.a = new i().a(abVar);
            } else if (abVar instanceof aj) {
                this.a = new o().a(abVar);
            } else if (abVar instanceof au) {
                this.a = new r().a(abVar);
            } else if (abVar instanceof ay) {
                this.a = new s().a(abVar);
            } else if (abVar instanceof bd) {
                this.a = new v().a(abVar);
            } else if (abVar instanceof bz) {
                this.a = new ad().a(abVar);
            } else if (abVar instanceof bx) {
                this.a = new ac().a(abVar);
            } else if (abVar instanceof jp.co.cyberagent.android.gpuimage.v) {
                this.a = new g().a(abVar);
            } else if (abVar instanceof ae) {
                this.a = new l().a(abVar);
            } else if (abVar instanceof jp.co.cyberagent.android.gpuimage.q) {
                this.a = new f().a(abVar);
            } else if (abVar instanceof jp.co.cyberagent.android.gpuimage.g) {
                this.a = new c().a(abVar);
            } else if (abVar instanceof af) {
                this.a = new m().a(abVar);
            } else if (abVar instanceof ai) {
                this.a = new n().a(abVar);
            } else if (abVar instanceof bp) {
                this.a = new aa().a(abVar);
            } else if (abVar instanceof br) {
                this.a = new ab().a(abVar);
            } else if (abVar instanceof jp.co.cyberagent.android.gpuimage.j) {
                this.a = new d().a(abVar);
            } else if (abVar instanceof ao) {
                this.a = new q().a(abVar);
            } else {
                this.a = null;
            }
        }

        public void a(int i) {
            if (this.a != null) {
                this.a.a(i);
            }
        }
    }

    private static class b {
        public List<FilterType> a;
    }

    public interface c {
        void a(ab abVar);
    }

    public static ab a(Context context, FilterType filterType) {
        ab pVar;
        switch (filterType) {
            case NORMAL:
                return new ab();
            case CONTRAST:
                pVar = new p(2.0f);
                new a(pVar).a(60);
                return pVar;
            case GAMMA:
                pVar = new ad(2.0f);
                new a(pVar).a(50);
                return pVar;
            case INVERT:
                return new n();
            case PIXELATION:
                pVar = new ba();
                new a(pVar).a(20);
                return pVar;
            case HUE:
                return new al(90.0f);
            case BRIGHTNESS:
                return new f(1.5f);
            case GRAYSCALE:
                pVar = new ag();
                new a(pVar).a(45);
                return pVar;
            case SEPIA:
                pVar = new bi();
                new a(pVar).a(45);
                return pVar;
            case SHARPEN:
                pVar = new bj();
                pVar.a(2.0f);
                return pVar;
            case SOBEL_EDGE_DETECTION:
                return new bm();
            case THREE_X_THREE_CONVOLUTION:
                pVar = new jp.co.cyberagent.android.gpuimage.a();
                pVar.a(new float[]{-1.0f, 0.0f, 1.0f, -2.0f, 0.0f, 2.0f, -1.0f, 0.0f, 1.0f});
                return pVar;
            case EMBOSS:
                pVar = new x();
                new a(pVar).a(25);
                return pVar;
            case POSTERIZE:
                return new bb();
            case FILTER_GROUP:
                List linkedList = new LinkedList();
                linkedList.add(new p());
                linkedList.add(new u());
                linkedList.add(new ag());
                return new ac(linkedList);
            case SATURATION:
                pVar = new bg(1.0f);
                new a(pVar).a(65);
                return pVar;
            case EXPOSURE:
                return new z(0.0f);
            case HIGHLIGHT_SHADOW:
                return new aj(0.0f, 1.0f);
            case MONOCHROME:
                pVar = new au(1.0f, new float[]{0.6f, 0.45f, 0.3f, 1.0f});
                new a(pVar).a(100);
                return pVar;
            case OPACITY:
                pVar = new ay(1.0f);
                new a(pVar).a(100);
                return pVar;
            case RGB:
                return new bd(1.0f, 1.0f, 1.0f);
            case WHITE_BALANCE:
                return new bz(5000.0f, 0.0f);
            case VIGNETTE:
                PointF pointF = new PointF();
                pointF.x = 0.5f;
                pointF.y = 0.5f;
                pVar = new bx(pointF, new float[]{0.0f, 0.0f, 0.0f}, 0.3f, 0.75f);
                new a(pVar).a(30);
                return pVar;
            case TONE_CURVE:
                pVar = new bs();
                pVar.a(context.getResources().openRawResource(g.tone_cuver_sample));
                return pVar;
            case BLEND_DIFFERENCE:
                return a(context, s.class);
            case BLEND_SOURCE_OVER:
                return a(context, bo.class);
            case BLEND_COLOR_BURN:
                return a(context, l.class);
            case BLEND_COLOR_DODGE:
                return a(context, m.class);
            case BLEND_DARKEN:
                return a(context, r.class);
            case BLEND_DISSOLVE:
                return a(context, v.class);
            case BLEND_EXCLUSION:
                return a(context, y.class);
            case BLEND_HARD_LIGHT:
                return a(context, ah.class);
            case BLEND_LIGHTEN:
                return a(context, ap.class);
            case BLEND_ADD:
                return a(context, jp.co.cyberagent.android.gpuimage.c.class);
            case BLEND_DIVIDE:
                return a(context, w.class);
            case BLEND_MULTIPLY:
                return a(context, av.class);
            case BLEND_OVERLAY:
                return a(context, az.class);
            case BLEND_SCREEN:
                return a(context, bh.class);
            case BLEND_ALPHA:
                return a(context, d.class);
            case BLEND_COLOR:
                return a(context, k.class);
            case BLEND_HUE:
                return a(context, ak.class);
            case BLEND_SATURATION:
                return a(context, bf.class);
            case BLEND_LUMINOSITY:
                return a(context, as.class);
            case BLEND_LINEAR_BURN:
                return a(context, aq.class);
            case BLEND_SOFT_LIGHT:
                return a(context, bn.class);
            case BLEND_SUBTRACT:
                return a(context, bq.class);
            case BLEND_CHROMA_KEY:
                return a(context, i.class);
            case BLEND_NORMAL:
                return a(context, ax.class);
            case LOOKUP_AMATORKA:
                pVar = new ar();
                pVar.a(BitmapFactory.decodeResource(context.getResources(), com.bdj.picture.edit.a.c.lookup_amatorka));
                return pVar;
            case GAUSSIAN_BLUR:
                pVar = new ae();
                new a(pVar).a(100);
                return pVar;
            case CROSSHATCH:
                return new q();
            case BOX_BLUR:
                return new e();
            case CGA_COLORSPACE:
                return new h();
            case DILATION:
                return new t();
            case KUWAHARA:
                return new am();
            case RGB_DILATION:
                return new bc();
            case SKETCH:
                return new bk();
            case TOON:
                return new bt();
            case SMOOTH_TOON:
                return new bl();
            case BULGE_DISTORTION:
                pVar = new jp.co.cyberagent.android.gpuimage.g();
                new a(pVar).a(80);
                return pVar;
            case GLASS_SPHERE:
                pVar = new af();
                new a(pVar).a(45);
                return pVar;
            case HAZE:
                return new ai();
            case LAPLACIAN:
                return new an();
            case NON_MAXIMUM_SUPPRESSION:
                return new aw();
            case SPHERE_REFRACTION:
                return new bp();
            case SWIRL:
                pVar = new br();
                new a(pVar).a(40);
                return pVar;
            case WEAK_PIXEL_INCLUSION:
                return new by();
            case FALSE_COLOR:
                return new aa();
            case COLOR_BALANCE:
                pVar = new j();
                new a(pVar).a(10);
                return pVar;
            case LEVELS_FILTER_MIN:
                pVar = new ao();
                pVar.a(0.0f, 3.0f, 1.0f);
                new a(pVar).a(60);
                return pVar;
            default:
                throw new IllegalStateException("No filter of that type!");
        }
    }

    private static ab a(Context context, Class<? extends bu> cls) {
        try {
            bu buVar = (bu) cls.newInstance();
            buVar.a(BitmapFactory.decodeResource(context.getResources(), com.bdj.picture.edit.a.c.ic_launcher));
            return buVar;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
