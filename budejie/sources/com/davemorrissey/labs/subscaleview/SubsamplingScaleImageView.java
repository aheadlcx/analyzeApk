package com.davemorrissey.labs.subscaleview;

import android.content.Context;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnLongClickListener;
import com.budejie.www.R$styleable;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.Executor;

public class SubsamplingScaleImageView extends View {
    public static int a = Integer.MAX_VALUE;
    private static final String b = SubsamplingScaleImageView.class.getSimpleName();
    private static final List<Integer> c = Arrays.asList(new Integer[]{Integer.valueOf(0), Integer.valueOf(90), Integer.valueOf(180), Integer.valueOf(270), Integer.valueOf(-1)});
    private static final List<Integer> d = Arrays.asList(new Integer[]{Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(3)});
    private static final List<Integer> e = Arrays.asList(new Integer[]{Integer.valueOf(2), Integer.valueOf(1)});
    private static final List<Integer> f = Arrays.asList(new Integer[]{Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(3)});
    private static final List<Integer> g = Arrays.asList(new Integer[]{Integer.valueOf(2), Integer.valueOf(1), Integer.valueOf(3)});
    private float A;
    private int B;
    private int C;
    private float D;
    private float E;
    private PointF F;
    private PointF G;
    private Float H;
    private PointF I;
    private PointF J;
    private int K;
    private int L;
    private int M;
    private Rect N;
    private Rect O;
    private boolean P;
    private boolean Q;
    private boolean R;
    private int S;
    private GestureDetector T;
    private com.davemorrissey.labs.subscaleview.a.d U;
    private final Object V;
    private com.davemorrissey.labs.subscaleview.a.b<? extends com.davemorrissey.labs.subscaleview.a.c> W;
    private com.davemorrissey.labs.subscaleview.a.b<? extends com.davemorrissey.labs.subscaleview.a.d> aa;
    private PointF ab;
    private float ac;
    private final float ad;
    private PointF ae;
    private float af;
    private PointF ag;
    private boolean ah;
    private a ai;
    private boolean aj;
    private boolean ak;
    private f al;
    private OnLongClickListener am;
    private Handler an;
    private Paint ao;
    private Paint ap;
    private Paint aq;
    private g ar;
    private Matrix as;
    private RectF at;
    private float[] au;
    private float[] av;
    private float aw;
    private Bitmap h;
    private boolean i;
    private boolean j;
    private Uri k;
    private int l;
    private Map<Integer, List<h>> m;
    private boolean n;
    private int o;
    private float p;
    private float q;
    private int r;
    private int s;
    private int t;
    private int u;
    private int v;
    private boolean w;
    private boolean x;
    private boolean y;
    private boolean z;

    public interface f {
        void a();

        void a(Exception exception);

        void b();

        void b(Exception exception);

        void c();

        void c(Exception exception);
    }

    public static class d implements f {
        public void b() {
        }

        public void a() {
        }

        public void a(Exception exception) {
        }

        public void b(Exception exception) {
        }

        public void c(Exception exception) {
        }

        public void c() {
        }
    }

    private static class a {
        private float a;
        private float b;
        private PointF c;
        private PointF d;
        private PointF e;
        private PointF f;
        private PointF g;
        private long h;
        private boolean i;
        private int j;
        private long k;
        private e l;

        private a() {
            this.h = 500;
            this.i = true;
            this.j = 2;
            this.k = System.currentTimeMillis();
        }
    }

    public final class b {
        final /* synthetic */ SubsamplingScaleImageView a;
        private final float b;
        private final PointF c;
        private final PointF d;
        private long e;
        private int f;
        private boolean g;
        private boolean h;
        private e i;

        private b(SubsamplingScaleImageView subsamplingScaleImageView, PointF pointF) {
            this.a = subsamplingScaleImageView;
            this.e = 500;
            this.f = 2;
            this.g = true;
            this.h = true;
            this.b = subsamplingScaleImageView.D;
            this.c = pointF;
            this.d = null;
        }

        private b(SubsamplingScaleImageView subsamplingScaleImageView, float f, PointF pointF) {
            this.a = subsamplingScaleImageView;
            this.e = 500;
            this.f = 2;
            this.g = true;
            this.h = true;
            this.b = f;
            this.c = pointF;
            this.d = null;
        }

        private b(SubsamplingScaleImageView subsamplingScaleImageView, float f, PointF pointF, PointF pointF2) {
            this.a = subsamplingScaleImageView;
            this.e = 500;
            this.f = 2;
            this.g = true;
            this.h = true;
            this.b = f;
            this.c = pointF;
            this.d = pointF2;
        }

        public b a(long j) {
            this.e = j;
            return this;
        }

        public b a(boolean z) {
            this.g = z;
            return this;
        }

        public b a(int i) {
            if (SubsamplingScaleImageView.e.contains(Integer.valueOf(i))) {
                this.f = i;
                return this;
            }
            throw new IllegalArgumentException("Unknown easing type: " + i);
        }

        private b b(boolean z) {
            this.h = z;
            return this;
        }

        public void a() {
            if (!(this.a.ai == null || this.a.ai.l == null)) {
                try {
                    this.a.ai.l.c();
                } catch (Throwable e) {
                    Log.w(SubsamplingScaleImageView.b, "Error thrown by animation listener", e);
                }
            }
            int width = (((this.a.getWidth() - this.a.getPaddingRight()) - this.a.getPaddingLeft()) / 2) + this.a.getPaddingLeft();
            int height = (((this.a.getHeight() - this.a.getPaddingBottom()) - this.a.getPaddingTop()) / 2) + this.a.getPaddingTop();
            float c = this.a.f(this.b);
            PointF a = this.h ? this.a.a(this.c.x, this.c.y, c, new PointF()) : this.c;
            this.a.ai = new a();
            this.a.ai.a = this.a.D;
            this.a.ai.b = c;
            this.a.ai.k = System.currentTimeMillis();
            this.a.ai.e = a;
            this.a.ai.c = this.a.getCenter();
            this.a.ai.d = a;
            this.a.ai.f = this.a.b(a);
            this.a.ai.g = new PointF((float) width, (float) height);
            this.a.ai.h = this.e;
            this.a.ai.i = this.g;
            this.a.ai.j = this.f;
            this.a.ai.k = System.currentTimeMillis();
            this.a.ai.l = this.i;
            if (this.d != null) {
                float f = this.d.x - (this.a.ai.c.x * c);
                float f2 = this.d.y - (this.a.ai.c.y * c);
                g gVar = new g(c, new PointF(f, f2));
                this.a.a(true, gVar);
                this.a.ai.g = new PointF((gVar.b.x - f) + this.d.x, (gVar.b.y - f2) + this.d.y);
            }
            this.a.invalidate();
        }
    }

    private static class c extends AsyncTask<Void, Void, Integer> {
        private final WeakReference<SubsamplingScaleImageView> a;
        private final WeakReference<Context> b;
        private final WeakReference<com.davemorrissey.labs.subscaleview.a.b<? extends com.davemorrissey.labs.subscaleview.a.c>> c;
        private final Uri d;
        private final boolean e;
        private Bitmap f;
        private Exception g;

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return a((Void[]) objArr);
        }

        protected /* synthetic */ void onPostExecute(Object obj) {
            a((Integer) obj);
        }

        public c(SubsamplingScaleImageView subsamplingScaleImageView, Context context, com.davemorrissey.labs.subscaleview.a.b<? extends com.davemorrissey.labs.subscaleview.a.c> bVar, Uri uri, boolean z) {
            this.a = new WeakReference(subsamplingScaleImageView);
            this.b = new WeakReference(context);
            this.c = new WeakReference(bVar);
            this.d = uri;
            this.e = z;
        }

        protected Integer a(Void... voidArr) {
            try {
                String uri = this.d.toString();
                Context context = (Context) this.b.get();
                com.davemorrissey.labs.subscaleview.a.b bVar = (com.davemorrissey.labs.subscaleview.a.b) this.c.get();
                SubsamplingScaleImageView subsamplingScaleImageView = (SubsamplingScaleImageView) this.a.get();
                if (!(context == null || bVar == null || subsamplingScaleImageView == null)) {
                    this.f = ((com.davemorrissey.labs.subscaleview.a.c) bVar.a()).a(context, this.d);
                    return Integer.valueOf(subsamplingScaleImageView.a(uri));
                }
            } catch (Throwable e) {
                Log.e(SubsamplingScaleImageView.b, "Failed to load bitmap", e);
                this.g = e;
            } catch (Throwable e2) {
                Log.e(SubsamplingScaleImageView.b, "Failed to load bitmap - OutOfMemoryError", e2);
                this.g = new RuntimeException(e2);
            }
            return null;
        }

        protected void a(Integer num) {
            SubsamplingScaleImageView subsamplingScaleImageView = (SubsamplingScaleImageView) this.a.get();
            if (subsamplingScaleImageView == null) {
                return;
            }
            if (this.f == null || num == null) {
                if (this.g != null && subsamplingScaleImageView.al != null) {
                    if (this.e) {
                        subsamplingScaleImageView.al.a(this.g);
                    } else {
                        subsamplingScaleImageView.al.b(this.g);
                    }
                }
            } else if (this.e) {
                subsamplingScaleImageView.a(this.f);
            } else {
                subsamplingScaleImageView.a(this.f, num.intValue(), false);
            }
        }
    }

    public interface e {
        void a();

        void b();

        void c();
    }

    private static class g {
        private float a;
        private PointF b;

        private g(float f, PointF pointF) {
            this.a = f;
            this.b = pointF;
        }
    }

    private static class h {
        private Rect a;
        private int b;
        private Bitmap c;
        private boolean d;
        private boolean e;
        private Rect f;
        private Rect g;

        private h() {
        }
    }

    private static class i extends AsyncTask<Void, Void, Bitmap> {
        private final WeakReference<SubsamplingScaleImageView> a;
        private final WeakReference<com.davemorrissey.labs.subscaleview.a.d> b;
        private final WeakReference<h> c;
        private Exception d;

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return a((Void[]) objArr);
        }

        protected /* synthetic */ void onPostExecute(Object obj) {
            a((Bitmap) obj);
        }

        public i(SubsamplingScaleImageView subsamplingScaleImageView, com.davemorrissey.labs.subscaleview.a.d dVar, h hVar) {
            this.a = new WeakReference(subsamplingScaleImageView);
            this.b = new WeakReference(dVar);
            this.c = new WeakReference(hVar);
            hVar.d = true;
        }

        protected Bitmap a(Void... voidArr) {
            try {
                SubsamplingScaleImageView subsamplingScaleImageView = (SubsamplingScaleImageView) this.a.get();
                com.davemorrissey.labs.subscaleview.a.d dVar = (com.davemorrissey.labs.subscaleview.a.d) this.b.get();
                h hVar = (h) this.c.get();
                if (dVar == null || hVar == null || subsamplingScaleImageView == null || !dVar.a() || !hVar.e) {
                    if (hVar != null) {
                        hVar.d = false;
                    }
                    return null;
                }
                Bitmap a;
                synchronized (subsamplingScaleImageView.V) {
                    subsamplingScaleImageView.a(hVar.a, hVar.g);
                    if (subsamplingScaleImageView.N != null) {
                        hVar.g.offset(subsamplingScaleImageView.N.left, subsamplingScaleImageView.N.top);
                    }
                    a = dVar.a(hVar.g, hVar.b);
                }
                return a;
            } catch (Throwable e) {
                Log.e(SubsamplingScaleImageView.b, "Failed to decode tile", e);
                this.d = e;
            } catch (Throwable e2) {
                Log.e(SubsamplingScaleImageView.b, "Failed to decode tile - OutOfMemoryError", e2);
                this.d = new RuntimeException(e2);
            }
        }

        protected void a(Bitmap bitmap) {
            SubsamplingScaleImageView subsamplingScaleImageView = (SubsamplingScaleImageView) this.a.get();
            h hVar = (h) this.c.get();
            if (subsamplingScaleImageView != null && hVar != null) {
                if (bitmap != null) {
                    hVar.c = bitmap;
                    hVar.d = false;
                    subsamplingScaleImageView.l();
                } else if (this.d != null && subsamplingScaleImageView.al != null) {
                    subsamplingScaleImageView.al.c(this.d);
                }
            }
        }
    }

    private static class j extends AsyncTask<Void, Void, int[]> {
        private final WeakReference<SubsamplingScaleImageView> a;
        private final WeakReference<Context> b;
        private final WeakReference<com.davemorrissey.labs.subscaleview.a.b<? extends com.davemorrissey.labs.subscaleview.a.d>> c;
        private final Uri d;
        private com.davemorrissey.labs.subscaleview.a.d e;
        private Exception f;

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return a((Void[]) objArr);
        }

        protected /* synthetic */ void onPostExecute(Object obj) {
            a((int[]) obj);
        }

        public j(SubsamplingScaleImageView subsamplingScaleImageView, Context context, com.davemorrissey.labs.subscaleview.a.b<? extends com.davemorrissey.labs.subscaleview.a.d> bVar, Uri uri) {
            this.a = new WeakReference(subsamplingScaleImageView);
            this.b = new WeakReference(context);
            this.c = new WeakReference(bVar);
            this.d = uri;
        }

        protected int[] a(Void... voidArr) {
            try {
                String uri = this.d.toString();
                Context context = (Context) this.b.get();
                com.davemorrissey.labs.subscaleview.a.b bVar = (com.davemorrissey.labs.subscaleview.a.b) this.c.get();
                SubsamplingScaleImageView subsamplingScaleImageView = (SubsamplingScaleImageView) this.a.get();
                if (!(context == null || bVar == null || subsamplingScaleImageView == null)) {
                    int width;
                    this.e = (com.davemorrissey.labs.subscaleview.a.d) bVar.a();
                    Point a = this.e.a(context, this.d);
                    int i = a.x;
                    int i2 = a.y;
                    int a2 = subsamplingScaleImageView.a(uri);
                    if (subsamplingScaleImageView.N != null) {
                        width = subsamplingScaleImageView.N.width();
                        i = subsamplingScaleImageView.N.height();
                    } else {
                        width = i;
                        i = i2;
                    }
                    return new int[]{width, i, a2};
                }
            } catch (Throwable e) {
                Log.e(SubsamplingScaleImageView.b, "Failed to initialise bitmap decoder", e);
                this.f = e;
            }
            return null;
        }

        protected void a(int[] iArr) {
            SubsamplingScaleImageView subsamplingScaleImageView = (SubsamplingScaleImageView) this.a.get();
            if (subsamplingScaleImageView == null) {
                return;
            }
            if (this.e != null && iArr != null && iArr.length == 3) {
                subsamplingScaleImageView.a(this.e, iArr[0], iArr[1], iArr[2]);
            } else if (this.f != null && subsamplingScaleImageView.al != null) {
                subsamplingScaleImageView.al.b(this.f);
            }
        }
    }

    public SubsamplingScaleImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.o = 0;
        this.p = 2.0f;
        this.q = o();
        this.r = -1;
        this.s = 1;
        this.t = 1;
        this.u = a;
        this.v = a;
        this.x = true;
        this.y = true;
        this.z = true;
        this.A = 1.0f;
        this.B = 1;
        this.C = 300;
        this.V = new Object();
        this.W = new com.davemorrissey.labs.subscaleview.a.a(com.davemorrissey.labs.subscaleview.a.e.class);
        this.aa = new com.davemorrissey.labs.subscaleview.a.a(com.davemorrissey.labs.subscaleview.a.f.class);
        this.au = new float[8];
        this.av = new float[8];
        this.aw = getResources().getDisplayMetrics().density;
        setMinimumDpi(160);
        setDoubleTapZoomDpi(160);
        setGestureDetector(context);
        this.an = new Handler(new Callback(this) {
            final /* synthetic */ SubsamplingScaleImageView a;

            {
                this.a = r1;
            }

            public boolean handleMessage(Message message) {
                if (message.what == 1 && this.a.am != null) {
                    this.a.S = 0;
                    super.setOnLongClickListener(this.a.am);
                    this.a.performLongClick();
                    super.setOnLongClickListener(null);
                }
                return true;
            }
        });
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, com.davemorrissey.labs.subscaleview.b.a.SubsamplingScaleImageView);
            if (obtainStyledAttributes.hasValue(com.davemorrissey.labs.subscaleview.b.a.SubsamplingScaleImageView_assetName)) {
                String string = obtainStyledAttributes.getString(com.davemorrissey.labs.subscaleview.b.a.SubsamplingScaleImageView_assetName);
                if (string != null && string.length() > 0) {
                    setImage(a.a(string).a());
                }
            }
            if (obtainStyledAttributes.hasValue(com.davemorrissey.labs.subscaleview.b.a.SubsamplingScaleImageView_src)) {
                int resourceId = obtainStyledAttributes.getResourceId(com.davemorrissey.labs.subscaleview.b.a.SubsamplingScaleImageView_src, 0);
                if (resourceId > 0) {
                    setImage(a.a(resourceId).a());
                }
            }
            if (obtainStyledAttributes.hasValue(com.davemorrissey.labs.subscaleview.b.a.SubsamplingScaleImageView_panEnabled)) {
                setPanEnabled(obtainStyledAttributes.getBoolean(com.davemorrissey.labs.subscaleview.b.a.SubsamplingScaleImageView_panEnabled, true));
            }
            if (obtainStyledAttributes.hasValue(com.davemorrissey.labs.subscaleview.b.a.SubsamplingScaleImageView_zoomEnabled)) {
                setZoomEnabled(obtainStyledAttributes.getBoolean(com.davemorrissey.labs.subscaleview.b.a.SubsamplingScaleImageView_zoomEnabled, true));
            }
            if (obtainStyledAttributes.hasValue(com.davemorrissey.labs.subscaleview.b.a.SubsamplingScaleImageView_quickScaleEnabled)) {
                setQuickScaleEnabled(obtainStyledAttributes.getBoolean(com.davemorrissey.labs.subscaleview.b.a.SubsamplingScaleImageView_quickScaleEnabled, true));
            }
            if (obtainStyledAttributes.hasValue(com.davemorrissey.labs.subscaleview.b.a.SubsamplingScaleImageView_tileBackgroundColor)) {
                setTileBackgroundColor(obtainStyledAttributes.getColor(com.davemorrissey.labs.subscaleview.b.a.SubsamplingScaleImageView_tileBackgroundColor, Color.argb(0, 0, 0, 0)));
            }
            obtainStyledAttributes.recycle();
        }
        this.ad = TypedValue.applyDimension(1, 20.0f, context.getResources().getDisplayMetrics());
    }

    public SubsamplingScaleImageView(Context context) {
        this(context, null);
    }

    public final void setOrientation(int i) {
        if (c.contains(Integer.valueOf(i))) {
            this.o = i;
            a(false);
            invalidate();
            requestLayout();
            return;
        }
        throw new IllegalArgumentException("Invalid orientation: " + i);
    }

    public final void setImage(a aVar) {
        a(aVar, null, null);
    }

    public final void a(a aVar, a aVar2, ImageViewState imageViewState) {
        if (aVar == null) {
            throw new NullPointerException("imageSource must not be null");
        }
        a(true);
        if (imageViewState != null) {
            a(imageViewState);
        }
        if (aVar2 != null) {
            if (aVar.c() != null) {
                throw new IllegalArgumentException("Preview image cannot be used when a bitmap is provided for the main image");
            } else if (aVar.f() <= 0 || aVar.g() <= 0) {
                throw new IllegalArgumentException("Preview image cannot be used unless dimensions are provided for the main image");
            } else {
                this.K = aVar.f();
                this.L = aVar.g();
                this.O = aVar2.h();
                if (aVar2.c() != null) {
                    this.j = aVar2.i();
                    a(aVar2.c());
                } else {
                    Uri b = aVar2.b();
                    if (b == null && aVar2.d() != null) {
                        b = Uri.parse("android.resource://" + getContext().getPackageName() + "/" + aVar2.d());
                    }
                    a(new c(this, getContext(), this.W, b, true));
                }
            }
        }
        if (aVar.c() != null && aVar.h() != null) {
            a(Bitmap.createBitmap(aVar.c(), aVar.h().left, aVar.h().top, aVar.h().width(), aVar.h().height()), 0, false);
        } else if (aVar.c() != null) {
            a(aVar.c(), 0, aVar.i());
        } else {
            this.N = aVar.h();
            this.k = aVar.b();
            if (this.k == null && aVar.d() != null) {
                this.k = Uri.parse("android.resource://" + getContext().getPackageName() + "/" + aVar.d());
            }
            if (aVar.e() || this.N != null) {
                a(new j(this, getContext(), this.aa, this.k));
            } else {
                a(new c(this, getContext(), this.W, this.k, false));
            }
        }
    }

    private void a(boolean z) {
        this.D = 0.0f;
        this.E = 0.0f;
        this.F = null;
        this.G = null;
        this.H = Float.valueOf(0.0f);
        this.I = null;
        this.J = null;
        this.P = false;
        this.Q = false;
        this.R = false;
        this.S = 0;
        this.l = 0;
        this.ab = null;
        this.ac = 0.0f;
        this.ae = null;
        this.af = 0.0f;
        this.ag = null;
        this.ah = false;
        this.ai = null;
        this.ar = null;
        this.as = null;
        this.at = null;
        if (z) {
            this.k = null;
            if (this.U != null) {
                synchronized (this.V) {
                    this.U.b();
                    this.U = null;
                }
            }
            if (!(this.h == null || this.j)) {
                this.h.recycle();
            }
            if (!(this.h == null || !this.j || this.al == null)) {
                this.al.c();
            }
            this.K = 0;
            this.L = 0;
            this.M = 0;
            this.N = null;
            this.O = null;
            this.aj = false;
            this.ak = false;
            this.h = null;
            this.i = false;
            this.j = false;
        }
        if (this.m != null) {
            for (Entry value : this.m.entrySet()) {
                for (h hVar : (List) value.getValue()) {
                    hVar.e = false;
                    if (hVar.c != null) {
                        hVar.c.recycle();
                        hVar.c = null;
                    }
                }
            }
            this.m = null;
        }
        setGestureDetector(getContext());
    }

    private void setGestureDetector(final Context context) {
        this.T = new GestureDetector(context, new SimpleOnGestureListener(this) {
            final /* synthetic */ SubsamplingScaleImageView b;

            public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
                if (!this.b.x || !this.b.aj || this.b.F == null || motionEvent == null || motionEvent2 == null || ((Math.abs(motionEvent.getX() - motionEvent2.getX()) <= 50.0f && Math.abs(motionEvent.getY() - motionEvent2.getY()) <= 50.0f) || ((Math.abs(f) <= 500.0f && Math.abs(f2) <= 500.0f) || this.b.P))) {
                    return super.onFling(motionEvent, motionEvent2, f, f2);
                }
                PointF pointF = new PointF(this.b.F.x + (f * 0.25f), this.b.F.y + (0.25f * f2));
                new b(new PointF((((float) (this.b.getWidth() / 2)) - pointF.x) / this.b.D, (((float) (this.b.getHeight() / 2)) - pointF.y) / this.b.D)).a(1).b(false).a();
                return true;
            }

            public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
                this.b.performClick();
                return true;
            }

            public boolean onDoubleTap(MotionEvent motionEvent) {
                if (!this.b.y || !this.b.aj || this.b.F == null) {
                    return super.onDoubleTapEvent(motionEvent);
                }
                this.b.setGestureDetector(context);
                if (this.b.z) {
                    this.b.ab = new PointF(motionEvent.getX(), motionEvent.getY());
                    this.b.G = new PointF(this.b.F.x, this.b.F.y);
                    this.b.E = this.b.D;
                    this.b.R = true;
                    this.b.P = true;
                    this.b.ae = this.b.a(this.b.ab);
                    this.b.af = -1.0f;
                    this.b.ag = new PointF(this.b.ae.x, this.b.ae.y);
                    this.b.ah = false;
                    return false;
                }
                this.b.a(this.b.a(new PointF(motionEvent.getX(), motionEvent.getY())), new PointF(motionEvent.getX(), motionEvent.getY()));
                return true;
            }
        });
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        PointF center = getCenter();
        if (this.aj && center != null) {
            this.ai = null;
            this.H = Float.valueOf(this.D);
            this.I = center;
        }
    }

    protected void onMeasure(int i, int i2) {
        int n;
        int i3;
        Object obj = 1;
        int mode = MeasureSpec.getMode(i);
        int mode2 = MeasureSpec.getMode(i2);
        int size = MeasureSpec.getSize(i);
        int size2 = MeasureSpec.getSize(i2);
        Object obj2 = mode != 1073741824 ? 1 : null;
        if (mode2 == 1073741824) {
            obj = null;
        }
        if (this.K > 0 && this.L > 0) {
            if (obj2 == null || obj == null) {
                if (obj != null) {
                    n = (int) ((((double) n()) / ((double) m())) * ((double) size));
                    i3 = size;
                } else if (obj2 != null) {
                    i3 = (int) ((((double) m()) / ((double) n())) * ((double) size2));
                    n = size2;
                }
                setMeasuredDimension(Math.max(i3, getSuggestedMinimumWidth()), Math.max(n, getSuggestedMinimumHeight()));
            }
            i3 = m();
            n = n();
            setMeasuredDimension(Math.max(i3, getSuggestedMinimumWidth()), Math.max(n, getSuggestedMinimumHeight()));
        }
        n = size2;
        i3 = size;
        setMeasuredDimension(Math.max(i3, getSuggestedMinimumWidth()), Math.max(n, getSuggestedMinimumHeight()));
    }

    public boolean onTouchEvent(@NonNull MotionEvent motionEvent) {
        boolean z = false;
        Log.d(b, "onTouchEvent event=" + motionEvent.getAction());
        if (this.ai == null || this.ai.i) {
            if (!(this.ai == null || this.ai.l == null)) {
                try {
                    this.ai.l.b();
                } catch (Throwable e) {
                    Log.w(b, "Error thrown by animation listener", e);
                }
            }
            this.ai = null;
            if (this.F == null) {
                Log.d(b, "onTouchEvent vTranslate == null");
                return true;
            } else if (this.R || !(this.T == null || this.T.onTouchEvent(motionEvent))) {
                if (this.G == null) {
                    this.G = new PointF(0.0f, 0.0f);
                }
                if (this.ab == null) {
                    this.ab = new PointF(0.0f, 0.0f);
                }
                int pointerCount = motionEvent.getPointerCount();
                float a;
                switch (motionEvent.getAction()) {
                    case 0:
                    case 5:
                    case R$styleable.Theme_Custom_posts_detail_forward_icon /*261*/:
                        this.ai = null;
                        getParent().requestDisallowInterceptTouchEvent(true);
                        this.S = Math.max(this.S, pointerCount);
                        if (pointerCount >= 2) {
                            if (this.y) {
                                a = a(motionEvent.getX(0), motionEvent.getX(1), motionEvent.getY(0), motionEvent.getY(1));
                                this.E = this.D;
                                this.ac = a;
                                this.G.set(this.F.x, this.F.y);
                                this.ab.set((motionEvent.getX(0) + motionEvent.getX(1)) / 2.0f, (motionEvent.getY(0) + motionEvent.getY(1)) / 2.0f);
                            } else {
                                this.S = 0;
                            }
                            this.an.removeMessages(1);
                        } else if (!this.R) {
                            this.G.set(this.F.x, this.F.y);
                            this.ab.set(motionEvent.getX(), motionEvent.getY());
                            this.an.sendEmptyMessageDelayed(1, 600);
                        }
                        Log.d(b, "onTouchEvent ACTION_DOWN");
                        return true;
                    case 1:
                    case 6:
                    case R$styleable.Theme_Custom_commend_icon /*262*/:
                        this.an.removeMessages(1);
                        if (this.R) {
                            this.R = false;
                            if (!this.ah) {
                                a(this.ae, this.ab);
                            }
                        }
                        if (this.S > 0 && (this.P || this.Q)) {
                            if (this.P && pointerCount == 2) {
                                this.Q = true;
                                this.G.set(this.F.x, this.F.y);
                                if (motionEvent.getActionIndex() == 1) {
                                    this.ab.set(motionEvent.getX(0), motionEvent.getY(0));
                                } else {
                                    this.ab.set(motionEvent.getX(1), motionEvent.getY(1));
                                }
                            }
                            if (pointerCount < 3) {
                                this.P = false;
                            }
                            if (pointerCount < 2) {
                                this.Q = false;
                                this.S = 0;
                            }
                            b(true);
                            return true;
                        } else if (pointerCount != 1) {
                            return true;
                        } else {
                            this.P = false;
                            this.Q = false;
                            this.S = 0;
                            return true;
                        }
                    case 2:
                        if (this.S > 0) {
                            float x;
                            float y;
                            float f;
                            if (pointerCount >= 2) {
                                a = a(motionEvent.getX(0), motionEvent.getX(1), motionEvent.getY(0), motionEvent.getY(1));
                                x = (motionEvent.getX(0) + motionEvent.getX(1)) / 2.0f;
                                y = (motionEvent.getY(0) + motionEvent.getY(1)) / 2.0f;
                                if (this.y && (a(this.ab.x, x, this.ab.y, y) > 5.0f || Math.abs(a - this.ac) > 5.0f || this.Q)) {
                                    this.P = true;
                                    this.Q = true;
                                    this.D = Math.min(this.p, (a / this.ac) * this.E);
                                    if (this.D <= o()) {
                                        this.ac = a;
                                        this.E = o();
                                        this.ab.set(x, y);
                                        this.G.set(this.F);
                                    } else if (this.x) {
                                        f = (this.ab.y - this.G.y) * (this.D / this.E);
                                        this.F.x = x - ((this.ab.x - this.G.x) * (this.D / this.E));
                                        this.F.y = y - f;
                                    } else if (this.J != null) {
                                        this.F.x = ((float) (getWidth() / 2)) - (this.D * this.J.x);
                                        this.F.y = ((float) (getHeight() / 2)) - (this.D * this.J.y);
                                    } else {
                                        this.F.x = ((float) (getWidth() / 2)) - (this.D * ((float) (m() / 2)));
                                        this.F.y = ((float) (getHeight() / 2)) - (this.D * ((float) (n() / 2)));
                                    }
                                    c(true);
                                    b(false);
                                }
                            } else if (this.R) {
                                y = this.ad + (Math.abs(this.ab.y - motionEvent.getY()) * 2.0f);
                                if (this.af == -1.0f) {
                                    this.af = y;
                                }
                                r2 = motionEvent.getY() > this.ag.y;
                                this.ag.set(0.0f, motionEvent.getY());
                                f = Math.abs(1.0f - (y / this.af)) * 0.5f;
                                if (f > 0.03f || this.ah) {
                                    this.ah = true;
                                    a = this.af > 0.0f ? r2 ? 1.0f + f : 1.0f - f : 1.0f;
                                    this.D = Math.max(o(), Math.min(this.p, a * this.D));
                                    if (this.x) {
                                        x = (this.ab.y - this.G.y) * (this.D / this.E);
                                        this.F.x = this.ab.x - ((this.ab.x - this.G.x) * (this.D / this.E));
                                        this.F.y = this.ab.y - x;
                                    } else if (this.J != null) {
                                        this.F.x = ((float) (getWidth() / 2)) - (this.D * this.J.x);
                                        this.F.y = ((float) (getHeight() / 2)) - (this.D * this.J.y);
                                    } else {
                                        this.F.x = ((float) (getWidth() / 2)) - (this.D * ((float) (m() / 2)));
                                        this.F.y = ((float) (getHeight() / 2)) - (this.D * ((float) (n() / 2)));
                                    }
                                }
                                this.af = y;
                                c(true);
                                b(false);
                                z = true;
                            } else if (!this.P) {
                                f = Math.abs(motionEvent.getX() - this.ab.x);
                                x = Math.abs(motionEvent.getY() - this.ab.y);
                                float f2 = this.aw * 5.0f;
                                if (f > f2 || x > f2 || this.Q) {
                                    this.F.x = this.G.x + (motionEvent.getX() - this.ab.x);
                                    this.F.y = this.G.y + (motionEvent.getY() - this.ab.y);
                                    a = this.F.x;
                                    float f3 = this.F.y;
                                    c(true);
                                    boolean z2 = a != this.F.x;
                                    if (!z2 || f <= x || this.Q) {
                                        r2 = false;
                                    } else {
                                        r2 = true;
                                    }
                                    boolean z3;
                                    if (f3 != this.F.y || x <= 3.0f * f2) {
                                        z3 = false;
                                    } else {
                                        z3 = true;
                                    }
                                    if (!r2 && (!z2 || r3 || this.Q)) {
                                        this.Q = true;
                                    } else if (f > f2) {
                                    }
                                    if (!(f3 == this.F.y || this.F.y == 0.0f)) {
                                        Log.e(b, "lastY != vTranslate.y");
                                        Log.e(b, "lastY  ＝" + f3);
                                        Log.e(b, "vTranslate.y ＝" + this.F.y);
                                        this.S = 0;
                                        this.an.removeMessages(1);
                                        getParent().requestDisallowInterceptTouchEvent(false);
                                    }
                                    if (!this.x) {
                                        this.F.x = this.G.x;
                                        this.F.y = this.G.y;
                                        getParent().requestDisallowInterceptTouchEvent(false);
                                    }
                                    b(false);
                                }
                            }
                            z = true;
                        }
                        if (z) {
                            this.an.removeMessages(1);
                            invalidate();
                            return true;
                        }
                        break;
                }
                Log.d(b, "onTouchEvent super.onTouchEvent(event)");
                return super.onTouchEvent(motionEvent);
            } else {
                this.P = false;
                this.Q = false;
                this.S = 0;
                Log.d(b, "onTouchEvent !isQuickScaling && (detector == null || detector.onTouchEvent(event))");
                return true;
            }
        }
        getParent().requestDisallowInterceptTouchEvent(true);
        Log.d(b, "onTouchEvent requestDisallowInterceptTouchEvent");
        return true;
    }

    private void a(PointF pointF, PointF pointF2) {
        if (!this.x) {
            if (this.J != null) {
                pointF.x = this.J.x;
                pointF.y = this.J.y;
            } else {
                pointF.x = (float) (m() / 2);
                pointF.y = (float) (n() / 2);
            }
        }
        float min = Math.min(this.p, this.A);
        Object obj = ((double) this.D) <= ((double) min) * 0.9d ? 1 : null;
        if (obj == null) {
            min = o();
        }
        if (this.B == 3) {
            a(min, pointF);
        } else if (this.B == 2 || obj == null || !this.x) {
            new b(min, pointF).a(false).a((long) this.C).a();
        } else if (this.B == 1) {
            new b(min, pointF, pointF2).a(false).a((long) this.C).a();
        }
        invalidate();
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        j();
        if (this.K != 0 && this.L != 0 && getWidth() != 0 && getHeight() != 0) {
            if (this.m == null && this.U != null) {
                a(a(canvas));
            }
            if (h()) {
                float a;
                PointF pointF;
                k();
                if (this.ai != null) {
                    boolean z;
                    long currentTimeMillis = System.currentTimeMillis() - this.ai.k;
                    if (currentTimeMillis > this.ai.h) {
                        z = true;
                    } else {
                        z = false;
                    }
                    currentTimeMillis = Math.min(currentTimeMillis, this.ai.h);
                    this.D = a(this.ai.j, currentTimeMillis, this.ai.a, this.ai.b - this.ai.a, this.ai.h);
                    float a2 = a(this.ai.j, currentTimeMillis, this.ai.f.x, this.ai.g.x - this.ai.f.x, this.ai.h);
                    a = a(this.ai.j, currentTimeMillis, this.ai.f.y, this.ai.g.y - this.ai.f.y, this.ai.h);
                    pointF = this.F;
                    pointF.x -= d(this.ai.d.x) - a2;
                    pointF = this.F;
                    pointF.y -= e(this.ai.d.y) - a;
                    boolean z2 = z || this.ai.a == this.ai.b;
                    c(z2);
                    b(z);
                    if (z) {
                        if (this.ai.l != null) {
                            try {
                                this.ai.l.a();
                            } catch (Throwable e) {
                                Log.w(b, "Error thrown by animation listener", e);
                            }
                        }
                        this.ai = null;
                    }
                    invalidate();
                }
                if (this.m != null && g()) {
                    int min = Math.min(this.l, a(this.D));
                    Object obj = null;
                    for (Entry entry : this.m.entrySet()) {
                        if (((Integer) entry.getKey()).intValue() == min) {
                            for (h hVar : (List) entry.getValue()) {
                                if (hVar.e && (hVar.d || hVar.c == null)) {
                                    obj = 1;
                                }
                            }
                        }
                        obj = obj;
                    }
                    for (Entry entry2 : this.m.entrySet()) {
                        if (((Integer) entry2.getKey()).intValue() == min || r13 != null) {
                            for (h hVar2 : (List) entry2.getValue()) {
                                b(hVar2.a, hVar2.f);
                                if (!hVar2.d && hVar2.c != null) {
                                    if (this.aq != null) {
                                        canvas.drawRect(hVar2.f, this.aq);
                                    }
                                    if (this.as == null) {
                                        this.as = new Matrix();
                                    }
                                    this.as.reset();
                                    a(this.au, 0.0f, 0.0f, (float) hVar2.c.getWidth(), 0.0f, (float) hVar2.c.getWidth(), (float) hVar2.c.getHeight(), 0.0f, (float) hVar2.c.getHeight());
                                    if (getRequiredRotation() == 0) {
                                        a(this.av, (float) hVar2.f.left, (float) hVar2.f.top, (float) hVar2.f.right, (float) hVar2.f.top, (float) hVar2.f.right, (float) hVar2.f.bottom, (float) hVar2.f.left, (float) hVar2.f.bottom);
                                    } else if (getRequiredRotation() == 90) {
                                        a(this.av, (float) hVar2.f.right, (float) hVar2.f.top, (float) hVar2.f.right, (float) hVar2.f.bottom, (float) hVar2.f.left, (float) hVar2.f.bottom, (float) hVar2.f.left, (float) hVar2.f.top);
                                    } else if (getRequiredRotation() == 180) {
                                        a(this.av, (float) hVar2.f.right, (float) hVar2.f.bottom, (float) hVar2.f.left, (float) hVar2.f.bottom, (float) hVar2.f.left, (float) hVar2.f.top, (float) hVar2.f.right, (float) hVar2.f.top);
                                    } else if (getRequiredRotation() == 270) {
                                        a(this.av, (float) hVar2.f.left, (float) hVar2.f.bottom, (float) hVar2.f.left, (float) hVar2.f.top, (float) hVar2.f.right, (float) hVar2.f.top, (float) hVar2.f.right, (float) hVar2.f.bottom);
                                    }
                                    this.as.setPolyToPoly(this.au, 0, this.av, 0, 4);
                                    canvas.drawBitmap(hVar2.c, this.as, this.ao);
                                    if (this.n) {
                                        canvas.drawRect(hVar2.f, this.ap);
                                    }
                                } else if (hVar2.d && this.n) {
                                    canvas.drawText("LOADING", (float) (hVar2.f.left + 5), (float) (hVar2.f.top + 35), this.ap);
                                }
                                if (hVar2.e && this.n) {
                                    canvas.drawText("ISS " + hVar2.b + " RECT " + hVar2.a.top + "," + hVar2.a.left + "," + hVar2.a.bottom + "," + hVar2.a.right, (float) (hVar2.f.left + 5), (float) (hVar2.f.top + 15), this.ap);
                                }
                            }
                        }
                    }
                    if (this.n) {
                        canvas.drawText("Scale: " + String.format(Locale.ENGLISH, "%.2f", new Object[]{Float.valueOf(this.D)}), 5.0f, 15.0f, this.ap);
                        canvas.drawText("Translate: " + String.format(Locale.ENGLISH, "%.2f", new Object[]{Float.valueOf(this.F.x)}) + ":" + String.format(Locale.ENGLISH, "%.2f", new Object[]{Float.valueOf(this.F.y)}), 5.0f, 35.0f, this.ap);
                        PointF center = getCenter();
                        canvas.drawText("Source center: " + String.format(Locale.ENGLISH, "%.2f", new Object[]{Float.valueOf(center.x)}) + ":" + String.format(Locale.ENGLISH, "%.2f", new Object[]{Float.valueOf(center.y)}), 5.0f, 55.0f, this.ap);
                        if (this.ai != null) {
                            center = b(this.ai.c);
                            pointF = b(this.ai.e);
                            PointF b = b(this.ai.d);
                            canvas.drawCircle(center.x, center.y, 10.0f, this.ap);
                            canvas.drawCircle(pointF.x, pointF.y, 20.0f, this.ap);
                            canvas.drawCircle(b.x, b.y, 25.0f, this.ap);
                            canvas.drawCircle((float) (getWidth() / 2), (float) (getHeight() / 2), 30.0f, this.ap);
                        }
                    }
                } else if (this.h != null) {
                    float f = this.D;
                    a = this.D;
                    if (this.i) {
                        f = (((float) this.K) / ((float) this.h.getWidth())) * this.D;
                        a = this.D * (((float) this.L) / ((float) this.h.getHeight()));
                    }
                    if (this.as == null) {
                        this.as = new Matrix();
                    }
                    this.as.reset();
                    this.as.postScale(f, a);
                    this.as.postRotate((float) getRequiredRotation());
                    this.as.postTranslate(this.F.x, this.F.y);
                    if (getRequiredRotation() == 180) {
                        this.as.postTranslate(this.D * ((float) this.K), this.D * ((float) this.L));
                    } else if (getRequiredRotation() == 90) {
                        this.as.postTranslate(this.D * ((float) this.L), 0.0f);
                    } else if (getRequiredRotation() == 270) {
                        this.as.postTranslate(0.0f, this.D * ((float) this.K));
                    }
                    if (this.aq != null) {
                        if (this.at == null) {
                            this.at = new RectF();
                        }
                        this.at.set(0.0f, 0.0f, (float) this.K, (float) this.L);
                        this.as.mapRect(this.at);
                        canvas.drawRect(this.at, this.aq);
                    }
                    canvas.drawBitmap(this.h, this.as, this.ao);
                }
            }
        }
    }

    private void a(float[] fArr, float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8) {
        fArr[0] = f;
        fArr[1] = f2;
        fArr[2] = f3;
        fArr[3] = f4;
        fArr[4] = f5;
        fArr[5] = f6;
        fArr[6] = f7;
        fArr[7] = f8;
    }

    private boolean g() {
        if (this.h != null && !this.i) {
            return true;
        }
        if (this.m == null) {
            return false;
        }
        boolean z = true;
        for (Entry entry : this.m.entrySet()) {
            if (((Integer) entry.getKey()).intValue() == this.l) {
                for (h hVar : (List) entry.getValue()) {
                    if (hVar.d || hVar.c == null) {
                        z = false;
                    }
                }
            }
            z = z;
        }
        return z;
    }

    private boolean h() {
        boolean z = getWidth() > 0 && getHeight() > 0 && this.K > 0 && this.L > 0 && (this.h != null || g());
        if (!this.aj && z) {
            k();
            this.aj = true;
            c();
            if (this.al != null) {
                this.al.b();
            }
        }
        return z;
    }

    private boolean i() {
        boolean g = g();
        if (!this.ak && g) {
            k();
            this.ak = true;
            d();
            if (this.al != null) {
                this.al.a();
            }
        }
        return g;
    }

    private void j() {
        if (this.ao == null) {
            this.ao = new Paint();
            this.ao.setAntiAlias(true);
            this.ao.setFilterBitmap(true);
            this.ao.setDither(true);
        }
        if (this.ap == null && this.n) {
            this.ap = new Paint();
            this.ap.setTextSize(18.0f);
            this.ap.setColor(-65281);
            this.ap.setStyle(Style.STROKE);
        }
    }

    private synchronized void a(Point point) {
        this.ar = new g(0.0f, new PointF(0.0f, 0.0f));
        a(true, this.ar);
        this.l = a(this.ar.a);
        if (this.l > 1) {
            this.l /= 2;
        }
        if (this.l != 1 || this.N != null || m() >= point.x || n() >= point.y) {
            b(point);
            for (h iVar : (List) this.m.get(Integer.valueOf(this.l))) {
                a(new i(this, this.U, iVar));
            }
            b(true);
        } else {
            this.U.b();
            this.U = null;
            a(new c(this, getContext(), this.W, this.k, false));
        }
    }

    private void b(boolean z) {
        Log.d(b, "onTouchEvent refreshRequiredTiles load=" + z);
        if (this.U != null && this.m != null) {
            int min = Math.min(this.l, a(this.D));
            for (Entry value : this.m.entrySet()) {
                for (h hVar : (List) value.getValue()) {
                    if (hVar.b < min || (hVar.b > min && hVar.b != this.l)) {
                        hVar.e = false;
                        if (hVar.c != null) {
                            hVar.c.recycle();
                            hVar.c = null;
                        }
                    }
                    if (hVar.b == min) {
                        if (a(hVar)) {
                            hVar.e = true;
                            if (!hVar.d && hVar.c == null && z) {
                                a(new i(this, this.U, hVar));
                            }
                        } else if (hVar.b != this.l) {
                            hVar.e = false;
                            if (hVar.c != null) {
                                hVar.c.recycle();
                                hVar.c = null;
                            }
                        }
                    } else if (hVar.b == this.l) {
                        hVar.e = true;
                    }
                }
            }
        }
    }

    private boolean a(h hVar) {
        return b(0.0f) <= ((float) hVar.a.right) && ((float) hVar.a.left) <= b((float) getWidth()) && c(0.0f) <= ((float) hVar.a.bottom) && ((float) hVar.a.top) <= c((float) getHeight());
    }

    private void k() {
        if (getWidth() != 0 && getHeight() != 0 && this.K > 0 && this.L > 0) {
            if (!(this.I == null || this.H == null)) {
                this.D = this.H.floatValue();
                if (this.F == null) {
                    this.F = new PointF();
                }
                this.F.x = ((float) (getWidth() / 2)) - (this.D * this.I.x);
                this.F.y = ((float) (getHeight() / 2)) - (this.D * this.I.y);
                this.I = null;
                this.H = null;
                c(true);
                b(true);
            }
            c(false);
        }
    }

    private int a(float f) {
        if (this.r > 0) {
            DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
            f *= ((float) this.r) / ((displayMetrics.ydpi + displayMetrics.xdpi) / 2.0f);
        }
        int m = (int) (((float) m()) * f);
        int n = (int) (((float) n()) * f);
        if (m == 0 || n == 0) {
            return 32;
        }
        if (n() > n || m() > m) {
            n = Math.round(((float) n()) / ((float) n));
            m = Math.round(((float) m()) / ((float) m));
            if (n >= m) {
                n = m;
            }
        } else {
            n = 1;
        }
        m = 1;
        while (m * 2 < n) {
            m *= 2;
        }
        return m;
    }

    private void a(boolean z, g gVar) {
        float paddingLeft;
        float max;
        float f = 0.5f;
        if (this.s == 2 && b()) {
            z = false;
        }
        PointF b = gVar.b;
        float f2 = f(gVar.a);
        float m = f2 * ((float) m());
        float n = f2 * ((float) n());
        if (this.s == 3 && b()) {
            b.x = Math.max(b.x, ((float) (getWidth() / 2)) - m);
            b.y = Math.max(b.y, ((float) (getHeight() / 2)) - n);
        } else if (z) {
            b.x = Math.max(b.x, ((float) getWidth()) - m);
            b.y = Math.max(b.y, ((float) getHeight()) - n);
        } else {
            b.x = Math.max(b.x, -m);
            b.y = Math.max(b.y, -n);
        }
        if (getPaddingLeft() > 0 || getPaddingRight() > 0) {
            paddingLeft = ((float) getPaddingLeft()) / ((float) (getPaddingLeft() + getPaddingRight()));
        } else {
            paddingLeft = 0.5f;
        }
        if (getPaddingTop() > 0 || getPaddingBottom() > 0) {
            f = ((float) getPaddingTop()) / ((float) (getPaddingTop() + getPaddingBottom()));
        }
        if (this.s == 3 && b()) {
            paddingLeft = (float) Math.max(0, getWidth() / 2);
            max = (float) Math.max(0, getHeight() / 2);
        } else if (z) {
            paddingLeft = Math.max(0.0f, (((float) getWidth()) - m) * paddingLeft);
            max = Math.max(0.0f, (((float) getHeight()) - n) * f);
        } else {
            paddingLeft = (float) Math.max(0, getWidth());
            max = (float) Math.max(0, getHeight());
        }
        b.x = Math.min(b.x, paddingLeft);
        b.y = Math.min(b.y, max);
        gVar.a = f2;
    }

    private void c(boolean z) {
        Log.d(b, "onTouchEvent fitToBounds center=" + z);
        Object obj = null;
        if (this.F == null) {
            obj = 1;
            this.F = new PointF(0.0f, 0.0f);
        }
        if (this.ar == null) {
            this.ar = new g(0.0f, new PointF(0.0f, 0.0f));
        }
        this.ar.a = this.D;
        this.ar.b.set(this.F);
        a(z, this.ar);
        this.D = this.ar.a;
        this.F.set(this.ar.b);
        if (obj != null) {
            this.F.set(a((float) (m() / 2), (float) (n() / 2), this.D));
        }
    }

    private void b(Point point) {
        this.m = new LinkedHashMap();
        int i = this.l;
        int i2 = 1;
        int i3 = 1;
        while (true) {
            int m = m() / i2;
            int n = n() / i3;
            int i4 = m / i;
            int i5 = n / i;
            while (true) {
                if ((i4 + i2) + 1 > point.x || (((double) i4) > ((double) getWidth()) * 1.25d && i < this.l)) {
                    m = i2 + 1;
                    i4 = m() / m;
                    i2 = m;
                    m = i4;
                    i4 /= i;
                }
            }
            i4 = i5;
            i5 = n;
            while (true) {
                if ((i4 + i3) + 1 > point.y || (((double) i4) > ((double) getHeight()) * 1.25d && i < this.l)) {
                    i5 = i3 + 1;
                    i4 = n() / i5;
                    i3 = i5;
                    i5 = i4;
                    i4 /= i;
                }
            }
            List arrayList = new ArrayList(i2 * i3);
            int i6 = 0;
            while (i6 < i2) {
                for (int i7 = 0; i7 < i3; i7++) {
                    h hVar = new h();
                    hVar.b = i;
                    hVar.e = i == this.l;
                    int i8 = i6 * m;
                    int i9 = i7 * i5;
                    n = i6 == i2 + -1 ? m() : (i6 + 1) * m;
                    if (i7 == i3 - 1) {
                        i4 = n();
                    } else {
                        i4 = (i7 + 1) * i5;
                    }
                    hVar.a = new Rect(i8, i9, n, i4);
                    hVar.f = new Rect(0, 0, 0, 0);
                    hVar.g = new Rect(hVar.a);
                    arrayList.add(hVar);
                }
                i6++;
            }
            this.m.put(Integer.valueOf(i), arrayList);
            if (i != 1) {
                i /= 2;
            } else {
                return;
            }
        }
    }

    private synchronized void a(com.davemorrissey.labs.subscaleview.a.d dVar, int i, int i2, int i3) {
        if (this.K > 0 && this.L > 0 && !(this.K == i && this.L == i2)) {
            a(false);
            if (this.h != null) {
                if (!this.j) {
                    this.h.recycle();
                }
                this.h = null;
                if (this.al != null && this.j) {
                    this.al.c();
                }
                this.i = false;
                this.j = false;
            }
        }
        this.U = dVar;
        this.K = i;
        this.L = i2;
        this.M = i3;
        h();
        i();
        invalidate();
        requestLayout();
    }

    private synchronized void l() {
        h();
        i();
        if (g() && this.h != null) {
            if (!this.j) {
                this.h.recycle();
            }
            this.h = null;
            if (this.al != null && this.j) {
                this.al.c();
            }
            this.i = false;
            this.j = false;
        }
        invalidate();
    }

    private synchronized void a(Bitmap bitmap) {
        if (this.h != null || this.ak) {
            bitmap.recycle();
        } else {
            if (this.O != null) {
                this.h = Bitmap.createBitmap(bitmap, this.O.left, this.O.top, this.O.width(), this.O.height());
            } else {
                this.h = bitmap;
            }
            this.i = true;
            if (h()) {
                invalidate();
                requestLayout();
            }
        }
    }

    private synchronized void a(Bitmap bitmap, int i, boolean z) {
        if (this.K > 0 && this.L > 0 && !(this.K == bitmap.getWidth() && this.L == bitmap.getHeight())) {
            a(false);
        }
        if (!(this.h == null || this.j)) {
            this.h.recycle();
        }
        if (!(this.h == null || !this.j || this.al == null)) {
            this.al.c();
        }
        this.i = false;
        this.j = z;
        this.h = bitmap;
        this.K = bitmap.getWidth();
        this.L = bitmap.getHeight();
        this.M = i;
        boolean h = h();
        boolean i2 = i();
        if (h || i2) {
            invalidate();
            requestLayout();
        }
    }

    private int a(String str) {
        Cursor query;
        Cursor cursor;
        Throwable th;
        int i;
        if (str.startsWith("content")) {
            try {
                query = getContext().getContentResolver().query(Uri.parse(str), new String[]{"orientation"}, null, null, null);
                if (query != null) {
                    try {
                        if (query.moveToFirst()) {
                            i = query.getInt(0);
                            if (!c.contains(Integer.valueOf(i)) || i == -1) {
                                Log.w(b, "Unsupported orientation: " + i);
                            }
                            if (query != null) {
                                query.close();
                            }
                            return i;
                        }
                    } catch (Exception e) {
                        cursor = query;
                        try {
                            Log.w(b, "Could not get orientation of image from media store");
                            if (cursor == null) {
                                cursor.close();
                                i = 0;
                            } else {
                                i = 0;
                            }
                            return i;
                        } catch (Throwable th2) {
                            Throwable th3 = th2;
                            query = cursor;
                            th = th3;
                            if (query != null) {
                                query.close();
                            }
                            throw th;
                        }
                    } catch (Throwable th4) {
                        th = th4;
                        if (query != null) {
                            query.close();
                        }
                        throw th;
                    }
                }
                i = 0;
                if (query != null) {
                    query.close();
                }
            } catch (Exception e2) {
                cursor = null;
                Log.w(b, "Could not get orientation of image from media store");
                if (cursor == null) {
                    i = 0;
                } else {
                    cursor.close();
                    i = 0;
                }
                return i;
            } catch (Throwable th5) {
                th = th5;
                query = null;
                if (query != null) {
                    query.close();
                }
                throw th;
            }
            return i;
        } else if (!str.startsWith("file:///") || str.startsWith("file:///android_asset/")) {
            return 0;
        } else {
            try {
                i = new ExifInterface(str.substring("file:///".length() - 1)).getAttributeInt("Orientation", 1);
                if (i == 1 || i == 0) {
                    return 0;
                }
                if (i == 6) {
                    return 90;
                }
                if (i == 3) {
                    return 180;
                }
                if (i == 8) {
                    return 270;
                }
                Log.w(b, "Unsupported EXIF orientation: " + i);
                return 0;
            } catch (Exception e3) {
                Log.w(b, "Could not get EXIF orientation of image");
                return 0;
            }
        }
    }

    private void a(AsyncTask<Void, Void, ?> asyncTask) {
        if (this.w && VERSION.SDK_INT >= 11) {
            try {
                Executor executor = (Executor) AsyncTask.class.getField("THREAD_POOL_EXECUTOR").get(null);
                AsyncTask.class.getMethod("executeOnExecutor", new Class[]{Executor.class, Object[].class}).invoke(asyncTask, new Object[]{executor, null});
                return;
            } catch (Throwable e) {
                Log.i(b, "Failed to execute AsyncTask on thread pool executor, falling back to single threaded executor", e);
            }
        }
        asyncTask.execute(new Void[0]);
    }

    private void a(ImageViewState imageViewState) {
        if (imageViewState != null && imageViewState.getCenter() != null && c.contains(Integer.valueOf(imageViewState.getOrientation()))) {
            this.o = imageViewState.getOrientation();
            this.H = Float.valueOf(imageViewState.getScale());
            this.I = imageViewState.getCenter();
            invalidate();
        }
    }

    public void setMaxTileSize(int i) {
        this.u = i;
        this.v = i;
    }

    private Point a(Canvas canvas) {
        int intValue;
        int i = 2048;
        if (VERSION.SDK_INT >= 14) {
            try {
                int intValue2 = ((Integer) Canvas.class.getMethod("getMaximumBitmapWidth", new Class[0]).invoke(canvas, new Object[0])).intValue();
                try {
                    intValue = ((Integer) Canvas.class.getMethod("getMaximumBitmapHeight", new Class[0]).invoke(canvas, new Object[0])).intValue();
                    i = intValue2;
                } catch (Exception e) {
                    intValue = intValue2;
                    i = intValue;
                    intValue = 2048;
                    return new Point(Math.min(i, this.u), Math.min(intValue, this.v));
                }
            } catch (Exception e2) {
                intValue = 2048;
                i = intValue;
                intValue = 2048;
                return new Point(Math.min(i, this.u), Math.min(intValue, this.v));
            }
        }
        intValue = 2048;
        return new Point(Math.min(i, this.u), Math.min(intValue, this.v));
    }

    private int m() {
        int requiredRotation = getRequiredRotation();
        if (requiredRotation == 90 || requiredRotation == 270) {
            return this.L;
        }
        return this.K;
    }

    private int n() {
        int requiredRotation = getRequiredRotation();
        if (requiredRotation == 90 || requiredRotation == 270) {
            return this.K;
        }
        return this.L;
    }

    private void a(Rect rect, Rect rect2) {
        if (getRequiredRotation() == 0) {
            rect2.set(rect);
        } else if (getRequiredRotation() == 90) {
            rect2.set(rect.top, this.L - rect.right, rect.bottom, this.L - rect.left);
        } else if (getRequiredRotation() == 180) {
            rect2.set(this.K - rect.right, this.L - rect.bottom, this.K - rect.left, this.L - rect.top);
        } else {
            rect2.set(this.K - rect.bottom, rect.left, this.K - rect.top, rect.right);
        }
    }

    private int getRequiredRotation() {
        if (this.o == -1) {
            return this.M;
        }
        return this.o;
    }

    private float a(float f, float f2, float f3, float f4) {
        float f5 = f - f2;
        float f6 = f3 - f4;
        return (float) Math.sqrt((double) ((f5 * f5) + (f6 * f6)));
    }

    public void a() {
        a(true);
        this.ao = null;
        this.ap = null;
        this.aq = null;
    }

    private float b(float f) {
        if (this.F == null) {
            return Float.NaN;
        }
        return (f - this.F.x) / this.D;
    }

    private float c(float f) {
        if (this.F == null) {
            return Float.NaN;
        }
        return (f - this.F.y) / this.D;
    }

    public final PointF a(PointF pointF) {
        return a(pointF.x, pointF.y, new PointF());
    }

    public final PointF a(float f, float f2) {
        return a(f, f2, new PointF());
    }

    public final PointF a(float f, float f2, PointF pointF) {
        if (this.F == null) {
            return null;
        }
        pointF.set(b(f), c(f2));
        return pointF;
    }

    private float d(float f) {
        if (this.F == null) {
            return Float.NaN;
        }
        return (this.D * f) + this.F.x;
    }

    private float e(float f) {
        if (this.F == null) {
            return Float.NaN;
        }
        return (this.D * f) + this.F.y;
    }

    public final PointF b(PointF pointF) {
        return b(pointF.x, pointF.y, new PointF());
    }

    public final PointF b(float f, float f2, PointF pointF) {
        if (this.F == null) {
            return null;
        }
        pointF.set(d(f), e(f2));
        return pointF;
    }

    private Rect b(Rect rect, Rect rect2) {
        rect2.set((int) d((float) rect.left), (int) e((float) rect.top), (int) d((float) rect.right), (int) e((float) rect.bottom));
        return rect2;
    }

    private PointF a(float f, float f2, float f3) {
        int paddingLeft = getPaddingLeft() + (((getWidth() - getPaddingRight()) - getPaddingLeft()) / 2);
        int paddingTop = getPaddingTop() + (((getHeight() - getPaddingBottom()) - getPaddingTop()) / 2);
        if (this.ar == null) {
            this.ar = new g(0.0f, new PointF(0.0f, 0.0f));
        }
        this.ar.a = f3;
        this.ar.b.set(((float) paddingLeft) - (f * f3), ((float) paddingTop) - (f2 * f3));
        a(true, this.ar);
        return this.ar.b;
    }

    private PointF a(float f, float f2, float f3, PointF pointF) {
        PointF a = a(f, f2, f3);
        pointF.set((((float) (getPaddingLeft() + (((getWidth() - getPaddingRight()) - getPaddingLeft()) / 2))) - a.x) / f3, (((float) (getPaddingTop() + (((getHeight() - getPaddingBottom()) - getPaddingTop()) / 2))) - a.y) / f3);
        return pointF;
    }

    private float o() {
        int paddingBottom = getPaddingBottom() + getPaddingTop();
        int paddingLeft = getPaddingLeft() + getPaddingRight();
        if (this.t == 2) {
            return ((float) (getWidth() - paddingLeft)) / ((float) m());
        }
        if (this.t != 3 || this.q <= 0.0f) {
            return Math.min(((float) (getWidth() - paddingLeft)) / ((float) m()), ((float) (getHeight() - paddingBottom)) / ((float) n()));
        }
        return this.q;
    }

    private float f(float f) {
        return Math.min(this.p, Math.max(o(), f));
    }

    private float a(int i, long j, float f, float f2, long j2) {
        switch (i) {
            case 1:
                return a(j, f, f2, j2);
            case 2:
                return b(j, f, f2, j2);
            default:
                throw new IllegalStateException("Unexpected easing type: " + i);
        }
    }

    private float a(long j, float f, float f2, long j2) {
        float f3 = ((float) j) / ((float) j2);
        return ((f3 - 2.0f) * ((-f2) * f3)) + f;
    }

    private float b(long j, float f, float f2, long j2) {
        float f3 = ((float) j) / (((float) j2) / 2.0f);
        if (f3 < 1.0f) {
            return (f3 * ((f2 / 2.0f) * f3)) + f;
        }
        f3 -= 1.0f;
        return (((f3 * (f3 - 2.0f)) - 1.0f) * ((-f2) / 2.0f)) + f;
    }

    public final void setRegionDecoderClass(Class<? extends com.davemorrissey.labs.subscaleview.a.d> cls) {
        if (cls == null) {
            throw new IllegalArgumentException("Decoder class cannot be set to null");
        }
        this.aa = new com.davemorrissey.labs.subscaleview.a.a(cls);
    }

    public final void setRegionDecoderFactory(com.davemorrissey.labs.subscaleview.a.b<? extends com.davemorrissey.labs.subscaleview.a.d> bVar) {
        if (bVar == null) {
            throw new IllegalArgumentException("Decoder factory cannot be set to null");
        }
        this.aa = bVar;
    }

    public final void setBitmapDecoderClass(Class<? extends com.davemorrissey.labs.subscaleview.a.c> cls) {
        if (cls == null) {
            throw new IllegalArgumentException("Decoder class cannot be set to null");
        }
        this.W = new com.davemorrissey.labs.subscaleview.a.a(cls);
    }

    public final void setBitmapDecoderFactory(com.davemorrissey.labs.subscaleview.a.b<? extends com.davemorrissey.labs.subscaleview.a.c> bVar) {
        if (bVar == null) {
            throw new IllegalArgumentException("Decoder factory cannot be set to null");
        }
        this.W = bVar;
    }

    public final void setPanLimit(int i) {
        if (f.contains(Integer.valueOf(i))) {
            this.s = i;
            if (b()) {
                c(true);
                invalidate();
                return;
            }
            return;
        }
        throw new IllegalArgumentException("Invalid pan limit: " + i);
    }

    public final void setMinimumScaleType(int i) {
        if (g.contains(Integer.valueOf(i))) {
            this.t = i;
            if (b()) {
                c(true);
                invalidate();
                return;
            }
            return;
        }
        throw new IllegalArgumentException("Invalid scale type: " + i);
    }

    public final void setMaxScale(float f) {
        this.p = f;
    }

    public final void setMinScale(float f) {
        this.q = f;
    }

    public final void setMinimumDpi(int i) {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        setMaxScale(((displayMetrics.ydpi + displayMetrics.xdpi) / 2.0f) / ((float) i));
    }

    public final void setMaximumDpi(int i) {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        setMinScale(((displayMetrics.ydpi + displayMetrics.xdpi) / 2.0f) / ((float) i));
    }

    public float getMaxScale() {
        return this.p;
    }

    public final float getMinScale() {
        return o();
    }

    public void setMinimumTileDpi(int i) {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        this.r = (int) Math.min((displayMetrics.ydpi + displayMetrics.xdpi) / 2.0f, (float) i);
        if (b()) {
            a(false);
            invalidate();
        }
    }

    public final PointF getCenter() {
        return a((float) (getWidth() / 2), (float) (getHeight() / 2));
    }

    public final float getScale() {
        return this.D;
    }

    public final void a(float f, PointF pointF) {
        this.ai = null;
        this.H = Float.valueOf(f);
        this.I = pointF;
        this.J = pointF;
        invalidate();
    }

    public final void setTranslate(PointF pointF) {
        this.F = pointF;
    }

    public final boolean b() {
        return this.aj;
    }

    protected void c() {
    }

    protected void d() {
    }

    public final int getSWidth() {
        return this.K;
    }

    public final int getSHeight() {
        return this.L;
    }

    public final int getOrientation() {
        return this.o;
    }

    public final int getAppliedOrientation() {
        return getRequiredRotation();
    }

    public final ImageViewState getState() {
        if (this.F == null || this.K <= 0 || this.L <= 0) {
            return null;
        }
        return new ImageViewState(getScale(), getCenter(), getOrientation());
    }

    public final void setZoomEnabled(boolean z) {
        this.y = z;
    }

    public final void setQuickScaleEnabled(boolean z) {
        this.z = z;
    }

    public final void setPanEnabled(boolean z) {
        this.x = z;
        if (!z && this.F != null && b()) {
            b(true);
            invalidate();
        }
    }

    public final void setTileBackgroundColor(int i) {
        if (Color.alpha(i) == 0) {
            this.aq = null;
        } else {
            this.aq = new Paint();
            this.aq.setStyle(Style.FILL);
            this.aq.setColor(i);
        }
        invalidate();
    }

    public final void setDoubleTapZoomScale(float f) {
        this.A = f;
    }

    public final void setDoubleTapZoomDpi(int i) {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        setDoubleTapZoomScale(((displayMetrics.ydpi + displayMetrics.xdpi) / 2.0f) / ((float) i));
    }

    public final void setDoubleTapZoomStyle(int i) {
        if (d.contains(Integer.valueOf(i))) {
            this.B = i;
            return;
        }
        throw new IllegalArgumentException("Invalid zoom style: " + i);
    }

    public final void setDoubleTapZoomDuration(int i) {
        this.C = Math.max(0, i);
    }

    public void setParallelLoadingEnabled(boolean z) {
        this.w = z;
    }

    public final void setDebug(boolean z) {
        this.n = z;
    }

    public void setOnLongClickListener(OnLongClickListener onLongClickListener) {
        this.am = onLongClickListener;
    }

    public void setOnImageEventListener(f fVar) {
        this.al = fVar;
    }
}
