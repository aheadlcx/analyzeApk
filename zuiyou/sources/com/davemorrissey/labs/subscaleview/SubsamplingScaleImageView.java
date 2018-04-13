package com.davemorrissey.labs.subscaleview;

import android.content.Context;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.support.annotation.AnyThread;
import android.support.annotation.NonNull;
import android.support.media.ExifInterface;
import android.support.v4.internal.view.SupportMenu;
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
import android.view.ViewParent;
import com.davemorrissey.labs.subscaleview.decoder.SkiaImageDecoder;
import com.davemorrissey.labs.subscaleview.decoder.SkiaImageRegionDecoder;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.Executor;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class SubsamplingScaleImageView extends View {
    private static final String a = SubsamplingScaleImageView.class.getSimpleName();
    private static Config aC;
    private static final List<Integer> b = Arrays.asList(new Integer[]{Integer.valueOf(0), Integer.valueOf(90), Integer.valueOf(180), Integer.valueOf(270), Integer.valueOf(-1)});
    private static final List<Integer> c = Arrays.asList(new Integer[]{Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(3)});
    private static final List<Integer> d = Arrays.asList(new Integer[]{Integer.valueOf(2), Integer.valueOf(1)});
    private static final List<Integer> e = Arrays.asList(new Integer[]{Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(3)});
    private static final List<Integer> f = Arrays.asList(new Integer[]{Integer.valueOf(2), Integer.valueOf(1), Integer.valueOf(3), Integer.valueOf(4)});
    private float A;
    private int B;
    private int C;
    private float D;
    private float E;
    private PointF F;
    private PointF G;
    private PointF H;
    private Float I;
    private PointF J;
    private PointF K;
    private int L;
    private int M;
    private int N;
    private Rect O;
    private Rect P;
    private boolean Q;
    private boolean R;
    private boolean S;
    private int T;
    private GestureDetector U;
    private GestureDetector V;
    private com.davemorrissey.labs.subscaleview.decoder.d W;
    private final float[] aA;
    private final float aB;
    private final ReadWriteLock aa;
    private com.davemorrissey.labs.subscaleview.decoder.b<? extends com.davemorrissey.labs.subscaleview.decoder.c> ab;
    private com.davemorrissey.labs.subscaleview.decoder.b<? extends com.davemorrissey.labs.subscaleview.decoder.d> ac;
    private PointF ad;
    private float ae;
    private final float af;
    private float ag;
    private boolean ah;
    private PointF ai;
    private PointF aj;
    private PointF ak;
    private a al;
    private boolean am;
    private boolean an;
    private e ao;
    private f ap;
    private OnLongClickListener aq;
    private final Handler ar;
    private Paint as;
    private Paint at;
    private Paint au;
    private Paint av;
    private g aw;
    private Matrix ax;
    private RectF ay;
    private final float[] az;
    private Bitmap g;
    private boolean h;
    private boolean i;
    private Uri j;
    private int k;
    private Map<Integer, List<h>> l;
    private boolean m;
    private int n;
    private float o;
    private float p;
    private int q;
    private int r;
    private int s;
    private int t;
    private int u;
    private Executor v;
    private boolean w;
    private boolean x;
    private boolean y;
    private boolean z;

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
        private int k;
        private long l;
        private d m;

        private a() {
            this.h = 500;
            this.i = true;
            this.j = 2;
            this.k = 1;
            this.l = System.currentTimeMillis();
        }
    }

    public final class b {
        final /* synthetic */ SubsamplingScaleImageView a;
        private final float b;
        private final PointF c;
        private final PointF d;
        private long e;
        private int f;
        private int g;
        private boolean h;
        private boolean i;
        private d j;

        private b(SubsamplingScaleImageView subsamplingScaleImageView, PointF pointF) {
            this.a = subsamplingScaleImageView;
            this.e = 500;
            this.f = 2;
            this.g = 1;
            this.h = true;
            this.i = true;
            this.b = subsamplingScaleImageView.D;
            this.c = pointF;
            this.d = null;
        }

        private b(SubsamplingScaleImageView subsamplingScaleImageView, float f, PointF pointF) {
            this.a = subsamplingScaleImageView;
            this.e = 500;
            this.f = 2;
            this.g = 1;
            this.h = true;
            this.i = true;
            this.b = f;
            this.c = pointF;
            this.d = null;
        }

        private b(SubsamplingScaleImageView subsamplingScaleImageView, float f, PointF pointF, PointF pointF2) {
            this.a = subsamplingScaleImageView;
            this.e = 500;
            this.f = 2;
            this.g = 1;
            this.h = true;
            this.i = true;
            this.b = f;
            this.c = pointF;
            this.d = pointF2;
        }

        public b a(long j) {
            this.e = j;
            return this;
        }

        public b a(boolean z) {
            this.h = z;
            return this;
        }

        public b a(int i) {
            if (SubsamplingScaleImageView.d.contains(Integer.valueOf(i))) {
                this.f = i;
                return this;
            }
            throw new IllegalArgumentException("Unknown easing type: " + i);
        }

        private b b(boolean z) {
            this.i = z;
            return this;
        }

        private b b(int i) {
            this.g = i;
            return this;
        }

        public void a() {
            if (!(this.a.al == null || this.a.al.m == null)) {
                try {
                    this.a.al.m.c();
                } catch (Throwable e) {
                    Log.w(SubsamplingScaleImageView.a, "Error thrown by animation listener", e);
                }
            }
            int width = (((this.a.getWidth() - this.a.getPaddingRight()) - this.a.getPaddingLeft()) / 2) + this.a.getPaddingLeft();
            int height = (((this.a.getHeight() - this.a.getPaddingBottom()) - this.a.getPaddingTop()) / 2) + this.a.getPaddingTop();
            float c = this.a.f(this.b);
            PointF a = this.i ? this.a.a(this.c.x, this.c.y, c, new PointF()) : this.c;
            this.a.al = new a();
            this.a.al.a = this.a.D;
            this.a.al.b = c;
            this.a.al.l = System.currentTimeMillis();
            this.a.al.e = a;
            this.a.al.c = this.a.getCenter();
            this.a.al.d = a;
            this.a.al.f = this.a.b(a);
            this.a.al.g = new PointF((float) width, (float) height);
            this.a.al.h = this.e;
            this.a.al.i = this.h;
            this.a.al.j = this.f;
            this.a.al.k = this.g;
            this.a.al.l = System.currentTimeMillis();
            this.a.al.m = this.j;
            if (this.d != null) {
                float f = this.d.x - (this.a.al.c.x * c);
                float f2 = this.d.y - (this.a.al.c.y * c);
                g gVar = new g(c, new PointF(f, f2));
                this.a.a(true, gVar);
                this.a.al.g = new PointF((gVar.b.x - f) + this.d.x, (gVar.b.y - f2) + this.d.y);
            }
            this.a.invalidate();
        }
    }

    private static class c extends AsyncTask<Void, Void, Integer> {
        private final WeakReference<SubsamplingScaleImageView> a;
        private final WeakReference<Context> b;
        private final WeakReference<com.davemorrissey.labs.subscaleview.decoder.b<? extends com.davemorrissey.labs.subscaleview.decoder.c>> c;
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

        c(SubsamplingScaleImageView subsamplingScaleImageView, Context context, com.davemorrissey.labs.subscaleview.decoder.b<? extends com.davemorrissey.labs.subscaleview.decoder.c> bVar, Uri uri, boolean z) {
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
                com.davemorrissey.labs.subscaleview.decoder.b bVar = (com.davemorrissey.labs.subscaleview.decoder.b) this.c.get();
                SubsamplingScaleImageView subsamplingScaleImageView = (SubsamplingScaleImageView) this.a.get();
                if (!(context == null || bVar == null || subsamplingScaleImageView == null)) {
                    subsamplingScaleImageView.a("BitmapLoadTask.doInBackground", new Object[0]);
                    this.f = ((com.davemorrissey.labs.subscaleview.decoder.c) bVar.a()).a(context, this.d);
                    return Integer.valueOf(subsamplingScaleImageView.a(context, uri));
                }
            } catch (Throwable e) {
                Log.e(SubsamplingScaleImageView.a, "Failed to load bitmap", e);
                this.g = e;
            } catch (Throwable e2) {
                Log.e(SubsamplingScaleImageView.a, "Failed to load bitmap - OutOfMemoryError", e2);
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
                if (this.g != null && subsamplingScaleImageView.ao != null) {
                    if (this.e) {
                        subsamplingScaleImageView.ao.a(this.g);
                    } else {
                        subsamplingScaleImageView.ao.b(this.g);
                    }
                }
            } else if (this.e) {
                subsamplingScaleImageView.a(this.f);
            } else {
                subsamplingScaleImageView.a(this.f, num.intValue(), false);
            }
        }
    }

    public interface d {
        void a();

        void b();

        void c();
    }

    public interface e {
        void a();

        void a(Exception exception);

        void b();

        void b(Exception exception);

        void c();

        void c(Exception exception);
    }

    public interface f {
        void a(float f, int i);

        void a(PointF pointF, int i);
    }

    private static class g {
        private float a;
        private final PointF b;

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
        private final WeakReference<com.davemorrissey.labs.subscaleview.decoder.d> b;
        private final WeakReference<h> c;
        private Exception d;

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return a((Void[]) objArr);
        }

        protected /* synthetic */ void onPostExecute(Object obj) {
            a((Bitmap) obj);
        }

        i(SubsamplingScaleImageView subsamplingScaleImageView, com.davemorrissey.labs.subscaleview.decoder.d dVar, h hVar) {
            this.a = new WeakReference(subsamplingScaleImageView);
            this.b = new WeakReference(dVar);
            this.c = new WeakReference(hVar);
            hVar.d = true;
        }

        protected Bitmap a(Void... voidArr) {
            SubsamplingScaleImageView subsamplingScaleImageView;
            try {
                subsamplingScaleImageView = (SubsamplingScaleImageView) this.a.get();
                com.davemorrissey.labs.subscaleview.decoder.d dVar = (com.davemorrissey.labs.subscaleview.decoder.d) this.b.get();
                h hVar = (h) this.c.get();
                if (dVar == null || hVar == null || subsamplingScaleImageView == null || !dVar.a() || !hVar.e) {
                    if (hVar != null) {
                        hVar.d = false;
                    }
                    return null;
                }
                subsamplingScaleImageView.a("TileLoadTask.doInBackground, tile.sRect=%s, tile.sampleSize=%d", hVar.a, Integer.valueOf(hVar.b));
                subsamplingScaleImageView.aa.readLock().lock();
                if (dVar.a()) {
                    subsamplingScaleImageView.a(hVar.a, hVar.g);
                    if (subsamplingScaleImageView.O != null) {
                        hVar.g.offset(subsamplingScaleImageView.O.left, subsamplingScaleImageView.O.top);
                    }
                    Bitmap a = dVar.a(hVar.g, hVar.b);
                    subsamplingScaleImageView.aa.readLock().unlock();
                    return a;
                }
                hVar.d = false;
                subsamplingScaleImageView.aa.readLock().unlock();
                return null;
            } catch (Throwable e) {
                Log.e(SubsamplingScaleImageView.a, "Failed to decode tile", e);
                this.d = e;
            } catch (Throwable e2) {
                Log.e(SubsamplingScaleImageView.a, "Failed to decode tile - OutOfMemoryError", e2);
                this.d = new RuntimeException(e2);
            } catch (Throwable th) {
                subsamplingScaleImageView.aa.readLock().unlock();
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
                } else if (this.d != null && subsamplingScaleImageView.ao != null) {
                    subsamplingScaleImageView.ao.c(this.d);
                }
            }
        }
    }

    private static class j extends AsyncTask<Void, Void, int[]> {
        private final WeakReference<SubsamplingScaleImageView> a;
        private final WeakReference<Context> b;
        private final WeakReference<com.davemorrissey.labs.subscaleview.decoder.b<? extends com.davemorrissey.labs.subscaleview.decoder.d>> c;
        private final Uri d;
        private com.davemorrissey.labs.subscaleview.decoder.d e;
        private Exception f;

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return a((Void[]) objArr);
        }

        protected /* synthetic */ void onPostExecute(Object obj) {
            a((int[]) obj);
        }

        j(SubsamplingScaleImageView subsamplingScaleImageView, Context context, com.davemorrissey.labs.subscaleview.decoder.b<? extends com.davemorrissey.labs.subscaleview.decoder.d> bVar, Uri uri) {
            this.a = new WeakReference(subsamplingScaleImageView);
            this.b = new WeakReference(context);
            this.c = new WeakReference(bVar);
            this.d = uri;
        }

        protected int[] a(Void... voidArr) {
            try {
                String uri = this.d.toString();
                Context context = (Context) this.b.get();
                com.davemorrissey.labs.subscaleview.decoder.b bVar = (com.davemorrissey.labs.subscaleview.decoder.b) this.c.get();
                SubsamplingScaleImageView subsamplingScaleImageView = (SubsamplingScaleImageView) this.a.get();
                if (!(context == null || bVar == null || subsamplingScaleImageView == null)) {
                    int width;
                    subsamplingScaleImageView.a("TilesInitTask.doInBackground", new Object[0]);
                    this.e = (com.davemorrissey.labs.subscaleview.decoder.d) bVar.a();
                    Point a = this.e.a(context, this.d);
                    int i = a.x;
                    int i2 = a.y;
                    int a2 = subsamplingScaleImageView.a(context, uri);
                    if (subsamplingScaleImageView.O != null) {
                        subsamplingScaleImageView.O.left = Math.max(0, subsamplingScaleImageView.O.left);
                        subsamplingScaleImageView.O.top = Math.max(0, subsamplingScaleImageView.O.top);
                        subsamplingScaleImageView.O.right = Math.min(i, subsamplingScaleImageView.O.right);
                        subsamplingScaleImageView.O.bottom = Math.min(i2, subsamplingScaleImageView.O.bottom);
                        width = subsamplingScaleImageView.O.width();
                        i2 = subsamplingScaleImageView.O.height();
                    } else {
                        width = i;
                    }
                    return new int[]{width, i2, a2};
                }
            } catch (Throwable e) {
                Log.e(SubsamplingScaleImageView.a, "Failed to initialise bitmap decoder", e);
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
            } else if (this.f != null && subsamplingScaleImageView.ao != null) {
                subsamplingScaleImageView.ao.b(this.f);
            }
        }
    }

    public SubsamplingScaleImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.n = 0;
        this.o = 2.0f;
        this.p = o();
        this.q = -1;
        this.r = 1;
        this.s = 1;
        this.t = Integer.MAX_VALUE;
        this.u = Integer.MAX_VALUE;
        this.v = AsyncTask.THREAD_POOL_EXECUTOR;
        this.w = true;
        this.x = true;
        this.y = true;
        this.z = true;
        this.A = 1.0f;
        this.B = 1;
        this.C = 500;
        this.aa = new ReentrantReadWriteLock(true);
        this.ab = new com.davemorrissey.labs.subscaleview.decoder.a(SkiaImageDecoder.class);
        this.ac = new com.davemorrissey.labs.subscaleview.decoder.a(SkiaImageRegionDecoder.class);
        this.az = new float[8];
        this.aA = new float[8];
        this.aB = getResources().getDisplayMetrics().density;
        setMinimumDpi(160);
        setDoubleTapZoomDpi(160);
        setMinimumTileDpi(320);
        setGestureDetector(context);
        this.ar = new Handler(new Callback(this) {
            final /* synthetic */ SubsamplingScaleImageView a;

            {
                this.a = r1;
            }

            public boolean handleMessage(Message message) {
                if (message.what == 1 && this.a.aq != null) {
                    this.a.T = 0;
                    super.setOnLongClickListener(this.a.aq);
                    this.a.performLongClick();
                    super.setOnLongClickListener(null);
                }
                return true;
            }
        });
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, cn.xiaochuankeji.a.a.g.SubsamplingScaleImageView);
            if (obtainStyledAttributes.hasValue(cn.xiaochuankeji.a.a.g.SubsamplingScaleImageView_assetName)) {
                String string = obtainStyledAttributes.getString(cn.xiaochuankeji.a.a.g.SubsamplingScaleImageView_assetName);
                if (string != null && string.length() > 0) {
                    setImage(a.a(string).a());
                }
            }
            if (obtainStyledAttributes.hasValue(cn.xiaochuankeji.a.a.g.SubsamplingScaleImageView_src)) {
                int resourceId = obtainStyledAttributes.getResourceId(cn.xiaochuankeji.a.a.g.SubsamplingScaleImageView_src, 0);
                if (resourceId > 0) {
                    setImage(a.a(resourceId).a());
                }
            }
            if (obtainStyledAttributes.hasValue(cn.xiaochuankeji.a.a.g.SubsamplingScaleImageView_panEnabled)) {
                setPanEnabled(obtainStyledAttributes.getBoolean(cn.xiaochuankeji.a.a.g.SubsamplingScaleImageView_panEnabled, true));
            }
            if (obtainStyledAttributes.hasValue(cn.xiaochuankeji.a.a.g.SubsamplingScaleImageView_zoomEnabled)) {
                setZoomEnabled(obtainStyledAttributes.getBoolean(cn.xiaochuankeji.a.a.g.SubsamplingScaleImageView_zoomEnabled, true));
            }
            if (obtainStyledAttributes.hasValue(cn.xiaochuankeji.a.a.g.SubsamplingScaleImageView_quickScaleEnabled)) {
                setQuickScaleEnabled(obtainStyledAttributes.getBoolean(cn.xiaochuankeji.a.a.g.SubsamplingScaleImageView_quickScaleEnabled, true));
            }
            if (obtainStyledAttributes.hasValue(cn.xiaochuankeji.a.a.g.SubsamplingScaleImageView_tileBackgroundColor)) {
                setTileBackgroundColor(obtainStyledAttributes.getColor(cn.xiaochuankeji.a.a.g.SubsamplingScaleImageView_tileBackgroundColor, Color.argb(0, 0, 0, 0)));
            }
            obtainStyledAttributes.recycle();
        }
        this.af = TypedValue.applyDimension(1, 20.0f, context.getResources().getDisplayMetrics());
    }

    public SubsamplingScaleImageView(Context context) {
        this(context, null);
    }

    public static Config getPreferredBitmapConfig() {
        return aC;
    }

    public static void setPreferredBitmapConfig(Config config) {
        aC = config;
    }

    public final void setOrientation(int i) {
        if (b.contains(Integer.valueOf(i))) {
            this.n = i;
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
                this.L = aVar.f();
                this.M = aVar.g();
                this.P = aVar2.h();
                if (aVar2.c() != null) {
                    this.i = aVar2.i();
                    a(aVar2.c());
                } else {
                    Uri b = aVar2.b();
                    if (b == null && aVar2.d() != null) {
                        b = Uri.parse("android.resource://" + getContext().getPackageName() + "/" + aVar2.d());
                    }
                    a(new c(this, getContext(), this.ab, b, true));
                }
            }
        }
        if (aVar.c() != null && aVar.h() != null) {
            a(Bitmap.createBitmap(aVar.c(), aVar.h().left, aVar.h().top, aVar.h().width(), aVar.h().height()), 0, false);
        } else if (aVar.c() != null) {
            a(aVar.c(), 0, aVar.i());
        } else {
            this.O = aVar.h();
            this.j = aVar.b();
            if (this.j == null && aVar.d() != null) {
                this.j = Uri.parse("android.resource://" + getContext().getPackageName() + "/" + aVar.d());
            }
            if (aVar.e() || this.O != null) {
                a(new j(this, getContext(), this.ac, this.j));
            } else {
                a(new c(this, getContext(), this.ab, this.j, false));
            }
        }
    }

    private void a(boolean z) {
        a("reset newImage=" + z, new Object[0]);
        this.D = 0.0f;
        this.E = 0.0f;
        this.F = null;
        this.G = null;
        this.H = null;
        this.I = Float.valueOf(0.0f);
        this.J = null;
        this.K = null;
        this.Q = false;
        this.R = false;
        this.S = false;
        this.T = 0;
        this.k = 0;
        this.ad = null;
        this.ae = 0.0f;
        this.ag = 0.0f;
        this.ah = false;
        this.aj = null;
        this.ai = null;
        this.ak = null;
        this.al = null;
        this.aw = null;
        this.ax = null;
        this.ay = null;
        if (z) {
            this.j = null;
            this.aa.writeLock().lock();
            try {
                if (this.W != null) {
                    this.W.b();
                    this.W = null;
                }
                this.aa.writeLock().unlock();
                if (!(this.g == null || this.i)) {
                    this.g.recycle();
                }
                if (!(this.g == null || !this.i || this.ao == null)) {
                    this.ao.c();
                }
                this.L = 0;
                this.M = 0;
                this.N = 0;
                this.O = null;
                this.P = null;
                this.am = false;
                this.an = false;
                this.g = null;
                this.h = false;
                this.i = false;
            } catch (Throwable th) {
                this.aa.writeLock().unlock();
            }
        }
        if (this.l != null) {
            for (Entry value : this.l.entrySet()) {
                for (h hVar : (List) value.getValue()) {
                    hVar.e = false;
                    if (hVar.c != null) {
                        hVar.c.recycle();
                        hVar.c = null;
                    }
                }
            }
            this.l = null;
        }
        setGestureDetector(getContext());
    }

    private void setGestureDetector(final Context context) {
        this.U = new GestureDetector(context, new SimpleOnGestureListener(this) {
            final /* synthetic */ SubsamplingScaleImageView b;

            public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
                if (!this.b.x || !this.b.am || this.b.F == null || motionEvent == null || motionEvent2 == null || ((Math.abs(motionEvent.getX() - motionEvent2.getX()) <= 50.0f && Math.abs(motionEvent.getY() - motionEvent2.getY()) <= 50.0f) || ((Math.abs(f) <= 500.0f && Math.abs(f2) <= 500.0f) || this.b.Q))) {
                    return super.onFling(motionEvent, motionEvent2, f, f2);
                }
                PointF pointF = new PointF(this.b.F.x + (f * 0.25f), this.b.F.y + (0.25f * f2));
                new b(new PointF((((float) (this.b.getWidth() / 2)) - pointF.x) / this.b.D, (((float) (this.b.getHeight() / 2)) - pointF.y) / this.b.D)).a(1).b(false).b(3).a();
                return true;
            }

            public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
                this.b.performClick();
                return true;
            }

            public boolean onDoubleTap(MotionEvent motionEvent) {
                if (!this.b.y || !this.b.am || this.b.F == null) {
                    return super.onDoubleTapEvent(motionEvent);
                }
                this.b.setGestureDetector(context);
                if (this.b.z) {
                    this.b.ad = new PointF(motionEvent.getX(), motionEvent.getY());
                    this.b.G = new PointF(this.b.F.x, this.b.F.y);
                    this.b.E = this.b.D;
                    this.b.S = true;
                    this.b.Q = true;
                    this.b.ag = -1.0f;
                    this.b.aj = this.b.a(this.b.ad);
                    this.b.ak = new PointF(motionEvent.getX(), motionEvent.getY());
                    this.b.ai = new PointF(this.b.aj.x, this.b.aj.y);
                    this.b.ah = false;
                    return false;
                }
                this.b.a(this.b.a(new PointF(motionEvent.getX(), motionEvent.getY())), new PointF(motionEvent.getX(), motionEvent.getY()));
                return true;
            }
        });
        this.V = new GestureDetector(context, new SimpleOnGestureListener(this) {
            final /* synthetic */ SubsamplingScaleImageView a;

            {
                this.a = r1;
            }

            public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
                this.a.performClick();
                return true;
            }
        });
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        a("onSizeChanged %dx%d -> %dx%d", Integer.valueOf(i3), Integer.valueOf(i4), Integer.valueOf(i), Integer.valueOf(i2));
        PointF center = getCenter();
        if (this.am && center != null) {
            this.al = null;
            this.I = Float.valueOf(this.D);
            this.J = center;
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
        if (this.L > 0 && this.M > 0) {
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
        if (this.al == null || this.al.i) {
            if (!(this.al == null || this.al.m == null)) {
                try {
                    this.al.m.b();
                } catch (Throwable e) {
                    Log.w(a, "Error thrown by animation listener", e);
                }
            }
            this.al = null;
            if (this.F == null) {
                if (this.V == null) {
                    return true;
                }
                this.V.onTouchEvent(motionEvent);
                return true;
            } else if (this.S || !(this.U == null || this.U.onTouchEvent(motionEvent))) {
                if (this.G == null) {
                    this.G = new PointF(0.0f, 0.0f);
                }
                if (this.H == null) {
                    this.H = new PointF(0.0f, 0.0f);
                }
                if (this.ad == null) {
                    this.ad = new PointF(0.0f, 0.0f);
                }
                float f = this.D;
                this.H.set(this.F);
                boolean a = a(motionEvent);
                a(f, this.H, 2);
                if (a || super.onTouchEvent(motionEvent)) {
                    z = true;
                }
                return z;
            } else {
                this.Q = false;
                this.R = false;
                this.T = 0;
                return true;
            }
        }
        b(true);
        return true;
    }

    private boolean a(@NonNull MotionEvent motionEvent) {
        int pointerCount = motionEvent.getPointerCount();
        float a;
        switch (motionEvent.getAction()) {
            case 0:
            case 5:
            case 261:
                this.al = null;
                b(true);
                this.T = Math.max(this.T, pointerCount);
                if (pointerCount >= 2) {
                    if (this.y) {
                        a = a(motionEvent.getX(0), motionEvent.getX(1), motionEvent.getY(0), motionEvent.getY(1));
                        this.E = this.D;
                        this.ae = a;
                        this.G.set(this.F.x, this.F.y);
                        this.ad.set((motionEvent.getX(0) + motionEvent.getX(1)) / 2.0f, (motionEvent.getY(0) + motionEvent.getY(1)) / 2.0f);
                    } else {
                        this.T = 0;
                    }
                    this.ar.removeMessages(1);
                    return true;
                } else if (this.S) {
                    return true;
                } else {
                    this.G.set(this.F.x, this.F.y);
                    this.ad.set(motionEvent.getX(), motionEvent.getY());
                    this.ar.sendEmptyMessageDelayed(1, 600);
                    return true;
                }
            case 1:
            case 6:
            case 262:
                this.ar.removeMessages(1);
                if (this.S) {
                    this.S = false;
                    if (!this.ah) {
                        a(this.aj, this.ad);
                    }
                }
                if (this.T > 0 && (this.Q || this.R)) {
                    if (this.Q && pointerCount == 2) {
                        this.R = true;
                        this.G.set(this.F.x, this.F.y);
                        if (motionEvent.getActionIndex() == 1) {
                            this.ad.set(motionEvent.getX(0), motionEvent.getY(0));
                        } else {
                            this.ad.set(motionEvent.getX(1), motionEvent.getY(1));
                        }
                    }
                    if (pointerCount < 3) {
                        this.Q = false;
                    }
                    if (pointerCount < 2) {
                        this.R = false;
                        this.T = 0;
                    }
                    c(true);
                    return true;
                } else if (pointerCount != 1) {
                    return true;
                } else {
                    this.Q = false;
                    this.R = false;
                    this.T = 0;
                    return true;
                }
            case 2:
                boolean z;
                if (this.T > 0) {
                    float x;
                    float y;
                    double d;
                    float f;
                    if (pointerCount >= 2) {
                        a = a(motionEvent.getX(0), motionEvent.getX(1), motionEvent.getY(0), motionEvent.getY(1));
                        x = (motionEvent.getX(0) + motionEvent.getX(1)) / 2.0f;
                        y = (motionEvent.getY(0) + motionEvent.getY(1)) / 2.0f;
                        if (this.y && (a(this.ad.x, x, this.ad.y, y) > 5.0f || Math.abs(a - this.ae) > 5.0f || this.R)) {
                            this.Q = true;
                            this.R = true;
                            d = (double) this.D;
                            this.D = Math.min(this.o, (a / this.ae) * this.E);
                            if (this.D <= o()) {
                                this.ae = a;
                                this.E = o();
                                this.ad.set(x, y);
                                this.G.set(this.F);
                            } else if (this.x) {
                                f = (this.ad.y - this.G.y) * (this.D / this.E);
                                this.F.x = x - ((this.ad.x - this.G.x) * (this.D / this.E));
                                this.F.y = y - f;
                                if ((((double) n()) * d < ((double) getHeight()) && this.D * ((float) n()) >= ((float) getHeight())) || (d * ((double) m()) < ((double) getWidth()) && this.D * ((float) m()) >= ((float) getWidth()))) {
                                    d(true);
                                    this.ad.set(x, y);
                                    this.G.set(this.F);
                                    this.E = this.D;
                                    this.ae = a;
                                }
                            } else if (this.K != null) {
                                this.F.x = ((float) (getWidth() / 2)) - (this.D * this.K.x);
                                this.F.y = ((float) (getHeight() / 2)) - (this.D * this.K.y);
                            } else {
                                this.F.x = ((float) (getWidth() / 2)) - (this.D * ((float) (m() / 2)));
                                this.F.y = ((float) (getHeight() / 2)) - (this.D * ((float) (n() / 2)));
                            }
                            d(true);
                            c(this.w);
                        }
                    } else if (this.S) {
                        y = this.af + (Math.abs(this.ak.y - motionEvent.getY()) * 2.0f);
                        if (this.ag == -1.0f) {
                            this.ag = y;
                        }
                        z = motionEvent.getY() > this.ai.y;
                        this.ai.set(0.0f, motionEvent.getY());
                        float abs = Math.abs(1.0f - (y / this.ag)) * 0.5f;
                        if (abs > 0.03f || this.ah) {
                            this.ah = true;
                            a = this.ag > 0.0f ? z ? 1.0f + abs : 1.0f - abs : 1.0f;
                            d = (double) this.D;
                            this.D = Math.max(o(), Math.min(this.o, a * this.D));
                            if (this.x) {
                                x = (this.ad.y - this.G.y) * (this.D / this.E);
                                this.F.x = this.ad.x - ((this.ad.x - this.G.x) * (this.D / this.E));
                                this.F.y = this.ad.y - x;
                                if ((((double) n()) * d >= ((double) getHeight()) || this.D * ((float) n()) < ((float) getHeight())) && (d * ((double) m()) >= ((double) getWidth()) || this.D * ((float) m()) < ((float) getWidth()))) {
                                    a = y;
                                } else {
                                    d(true);
                                    this.ad.set(b(this.aj));
                                    this.G.set(this.F);
                                    this.E = this.D;
                                    a = 0.0f;
                                }
                                y = a;
                            } else if (this.K != null) {
                                this.F.x = ((float) (getWidth() / 2)) - (this.D * this.K.x);
                                this.F.y = ((float) (getHeight() / 2)) - (this.D * this.K.y);
                            } else {
                                this.F.x = ((float) (getWidth() / 2)) - (this.D * ((float) (m() / 2)));
                                this.F.y = ((float) (getHeight() / 2)) - (this.D * ((float) (n() / 2)));
                            }
                        }
                        this.ag = y;
                        d(true);
                        c(this.w);
                        z = true;
                        if (z) {
                            this.ar.removeMessages(1);
                            invalidate();
                            return true;
                        }
                    } else if (!this.Q) {
                        float abs2 = Math.abs(motionEvent.getX() - this.ad.x);
                        f = Math.abs(motionEvent.getY() - this.ad.y);
                        float f2 = this.aB * 5.0f;
                        if (abs2 > f2 || f > f2 || this.R) {
                            boolean z2;
                            boolean z3;
                            this.F.x = this.G.x + (motionEvent.getX() - this.ad.x);
                            this.F.y = this.G.y + (motionEvent.getY() - this.ad.y);
                            a = this.F.x;
                            y = this.F.y;
                            d(true);
                            z = a != this.F.x;
                            if (y != this.F.y) {
                                z2 = true;
                            } else {
                                z2 = false;
                            }
                            if (!z || abs2 <= f || this.R) {
                                z3 = false;
                            } else {
                                z3 = true;
                            }
                            boolean z4;
                            if (!z2 || f <= abs2 || this.R) {
                                z4 = false;
                            } else {
                                z4 = true;
                            }
                            boolean z5;
                            if (y != this.F.y || f <= 3.0f * f2) {
                                z5 = false;
                            } else {
                                z5 = true;
                            }
                            if (!z3 && !r3 && (!z || !z2 || r4 || this.R)) {
                                this.R = true;
                            } else if (abs2 > f2 || f > f2) {
                                this.T = 0;
                                this.ar.removeMessages(1);
                                b(false);
                            }
                            if (!this.x) {
                                this.F.x = this.G.x;
                                this.F.y = this.G.y;
                                b(false);
                            }
                            c(this.w);
                        }
                    }
                    z = true;
                    if (z) {
                        this.ar.removeMessages(1);
                        invalidate();
                        return true;
                    }
                }
                z = false;
                if (z) {
                    this.ar.removeMessages(1);
                    invalidate();
                    return true;
                }
                break;
        }
        return false;
    }

    private void b(boolean z) {
        ViewParent parent = getParent();
        if (parent != null) {
            parent.requestDisallowInterceptTouchEvent(z);
        }
    }

    private void a(PointF pointF, PointF pointF2) {
        if (!this.x) {
            if (this.K != null) {
                pointF.x = this.K.x;
                pointF.y = this.K.y;
            } else {
                pointF.x = (float) (m() / 2);
                pointF.y = (float) (n() / 2);
            }
        }
        float min = Math.min(this.o, this.A);
        Object obj = (((double) this.D) <= ((double) min) * 0.9d || this.D == this.p) ? 1 : null;
        if (obj == null) {
            min = o();
        }
        if (this.B == 3) {
            a(min, pointF);
        } else if (this.B == 2 || obj == null || !this.x) {
            new b(min, pointF).a(false).a((long) this.C).b(4).a();
        } else if (this.B == 1) {
            new b(min, pointF, pointF2).a(false).a((long) this.C).b(4).a();
        }
        invalidate();
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        j();
        if (this.L != 0 && this.M != 0 && getWidth() != 0 && getHeight() != 0) {
            if (this.l == null && this.W != null) {
                a(a(canvas));
            }
            if (h()) {
                float a;
                PointF pointF;
                k();
                if (!(this.al == null || this.al.f == null)) {
                    boolean z;
                    float f = this.D;
                    if (this.H == null) {
                        this.H = new PointF(0.0f, 0.0f);
                    }
                    this.H.set(this.F);
                    long currentTimeMillis = System.currentTimeMillis() - this.al.l;
                    if (currentTimeMillis > this.al.h) {
                        z = true;
                    } else {
                        z = false;
                    }
                    currentTimeMillis = Math.min(currentTimeMillis, this.al.h);
                    this.D = a(this.al.j, currentTimeMillis, this.al.a, this.al.b - this.al.a, this.al.h);
                    float a2 = a(this.al.j, currentTimeMillis, this.al.f.x, this.al.g.x - this.al.f.x, this.al.h);
                    a = a(this.al.j, currentTimeMillis, this.al.f.y, this.al.g.y - this.al.f.y, this.al.h);
                    pointF = this.F;
                    pointF.x -= d(this.al.d.x) - a2;
                    pointF = this.F;
                    pointF.y -= e(this.al.d.y) - a;
                    boolean z2 = z || this.al.a == this.al.b;
                    d(z2);
                    a(f, this.H, this.al.k);
                    c(z);
                    if (z) {
                        if (this.al.m != null) {
                            try {
                                this.al.m.a();
                            } catch (Throwable e) {
                                Log.w(a, "Error thrown by animation listener", e);
                            }
                        }
                        this.al = null;
                    }
                    invalidate();
                }
                if (this.l != null && g()) {
                    int min = Math.min(this.k, a(this.D));
                    Object obj = null;
                    for (Entry entry : this.l.entrySet()) {
                        if (((Integer) entry.getKey()).intValue() == min) {
                            for (h hVar : (List) entry.getValue()) {
                                if (hVar.e && (hVar.d || hVar.c == null)) {
                                    obj = 1;
                                }
                            }
                        }
                        obj = obj;
                    }
                    for (Entry entry2 : this.l.entrySet()) {
                        if (((Integer) entry2.getKey()).intValue() == min || r13 != null) {
                            for (h hVar2 : (List) entry2.getValue()) {
                                b(hVar2.a, hVar2.f);
                                if (!hVar2.d && hVar2.c != null) {
                                    if (this.av != null) {
                                        canvas.drawRect(hVar2.f, this.av);
                                    }
                                    if (this.ax == null) {
                                        this.ax = new Matrix();
                                    }
                                    this.ax.reset();
                                    a(this.az, 0.0f, 0.0f, (float) hVar2.c.getWidth(), 0.0f, (float) hVar2.c.getWidth(), (float) hVar2.c.getHeight(), 0.0f, (float) hVar2.c.getHeight());
                                    if (getRequiredRotation() == 0) {
                                        a(this.aA, (float) hVar2.f.left, (float) hVar2.f.top, (float) hVar2.f.right, (float) hVar2.f.top, (float) hVar2.f.right, (float) hVar2.f.bottom, (float) hVar2.f.left, (float) hVar2.f.bottom);
                                    } else if (getRequiredRotation() == 90) {
                                        a(this.aA, (float) hVar2.f.right, (float) hVar2.f.top, (float) hVar2.f.right, (float) hVar2.f.bottom, (float) hVar2.f.left, (float) hVar2.f.bottom, (float) hVar2.f.left, (float) hVar2.f.top);
                                    } else if (getRequiredRotation() == 180) {
                                        a(this.aA, (float) hVar2.f.right, (float) hVar2.f.bottom, (float) hVar2.f.left, (float) hVar2.f.bottom, (float) hVar2.f.left, (float) hVar2.f.top, (float) hVar2.f.right, (float) hVar2.f.top);
                                    } else if (getRequiredRotation() == 270) {
                                        a(this.aA, (float) hVar2.f.left, (float) hVar2.f.bottom, (float) hVar2.f.left, (float) hVar2.f.top, (float) hVar2.f.right, (float) hVar2.f.top, (float) hVar2.f.right, (float) hVar2.f.bottom);
                                    }
                                    this.ax.setPolyToPoly(this.az, 0, this.aA, 0, 4);
                                    canvas.drawBitmap(hVar2.c, this.ax, this.as);
                                    if (this.m) {
                                        canvas.drawRect(hVar2.f, this.au);
                                    }
                                } else if (hVar2.d && this.m) {
                                    canvas.drawText("LOADING", (float) (hVar2.f.left + a(5)), (float) (hVar2.f.top + a(35)), this.at);
                                }
                                if (hVar2.e && this.m) {
                                    canvas.drawText("ISS " + hVar2.b + " RECT " + hVar2.a.top + "," + hVar2.a.left + "," + hVar2.a.bottom + "," + hVar2.a.right, (float) (hVar2.f.left + a(5)), (float) (hVar2.f.top + a(15)), this.at);
                                }
                            }
                        }
                    }
                } else if (this.g != null) {
                    float f2 = this.D;
                    a = this.D;
                    if (this.h) {
                        f2 = (((float) this.L) / ((float) this.g.getWidth())) * this.D;
                        a = this.D * (((float) this.M) / ((float) this.g.getHeight()));
                    }
                    if (this.ax == null) {
                        this.ax = new Matrix();
                    }
                    this.ax.reset();
                    this.ax.postScale(f2, a);
                    this.ax.postRotate((float) getRequiredRotation());
                    this.ax.postTranslate(this.F.x, this.F.y);
                    if (getRequiredRotation() == 180) {
                        this.ax.postTranslate(this.D * ((float) this.L), this.D * ((float) this.M));
                    } else if (getRequiredRotation() == 90) {
                        this.ax.postTranslate(this.D * ((float) this.M), 0.0f);
                    } else if (getRequiredRotation() == 270) {
                        this.ax.postTranslate(0.0f, this.D * ((float) this.L));
                    }
                    if (this.av != null) {
                        if (this.ay == null) {
                            this.ay = new RectF();
                        }
                        this.ay.set(0.0f, 0.0f, this.h ? (float) this.g.getWidth() : (float) this.L, this.h ? (float) this.g.getHeight() : (float) this.M);
                        this.ax.mapRect(this.ay);
                        canvas.drawRect(this.ay, this.av);
                    }
                    canvas.drawBitmap(this.g, this.ax, this.as);
                }
                if (this.m) {
                    canvas.drawText("Scale: " + String.format(Locale.ENGLISH, "%.2f", new Object[]{Float.valueOf(this.D)}) + " (" + String.format(Locale.ENGLISH, "%.2f", new Object[]{Float.valueOf(o())}) + " - " + String.format(Locale.ENGLISH, "%.2f", new Object[]{Float.valueOf(this.o)}) + ")", (float) a(5), (float) a(15), this.at);
                    canvas.drawText("Translate: " + String.format(Locale.ENGLISH, "%.2f", new Object[]{Float.valueOf(this.F.x)}) + ":" + String.format(Locale.ENGLISH, "%.2f", new Object[]{Float.valueOf(this.F.y)}), (float) a(5), (float) a(30), this.at);
                    PointF center = getCenter();
                    canvas.drawText("Source center: " + String.format(Locale.ENGLISH, "%.2f", new Object[]{Float.valueOf(center.x)}) + ":" + String.format(Locale.ENGLISH, "%.2f", new Object[]{Float.valueOf(center.y)}), (float) a(5), (float) a(45), this.at);
                    if (this.al != null) {
                        center = b(this.al.c);
                        pointF = b(this.al.e);
                        PointF b = b(this.al.d);
                        canvas.drawCircle(center.x, center.y, (float) a(10), this.au);
                        this.au.setColor(SupportMenu.CATEGORY_MASK);
                        canvas.drawCircle(pointF.x, pointF.y, (float) a(20), this.au);
                        this.au.setColor(-16776961);
                        canvas.drawCircle(b.x, b.y, (float) a(25), this.au);
                        this.au.setColor(-16711681);
                        canvas.drawCircle((float) (getWidth() / 2), (float) (getHeight() / 2), (float) a(30), this.au);
                    }
                    if (this.ad != null) {
                        this.au.setColor(SupportMenu.CATEGORY_MASK);
                        canvas.drawCircle(this.ad.x, this.ad.y, (float) a(20), this.au);
                    }
                    if (this.aj != null) {
                        this.au.setColor(-16776961);
                        canvas.drawCircle(d(this.aj.x), e(this.aj.y), (float) a(35), this.au);
                    }
                    if (this.ak != null && this.S) {
                        this.au.setColor(-16711681);
                        canvas.drawCircle(this.ak.x, this.ak.y, (float) a(30), this.au);
                    }
                    this.au.setColor(-65281);
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
        if (this.g != null && !this.h) {
            return true;
        }
        if (this.l == null) {
            return false;
        }
        boolean z = true;
        for (Entry entry : this.l.entrySet()) {
            if (((Integer) entry.getKey()).intValue() == this.k) {
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
        boolean z = getWidth() > 0 && getHeight() > 0 && this.L > 0 && this.M > 0 && (this.g != null || g());
        if (!this.am && z) {
            k();
            this.am = true;
            c();
            if (this.ao != null) {
                this.ao.a();
            }
        }
        return z;
    }

    private boolean i() {
        boolean g = g();
        if (!this.an && g) {
            k();
            this.an = true;
            d();
            if (this.ao != null) {
                this.ao.b();
            }
        }
        return g;
    }

    private void j() {
        if (this.as == null) {
            this.as = new Paint();
            this.as.setAntiAlias(true);
            this.as.setFilterBitmap(true);
            this.as.setDither(true);
        }
        if ((this.at == null || this.au == null) && this.m) {
            this.at = new Paint();
            this.at.setTextSize((float) a(12));
            this.at.setColor(-65281);
            this.at.setStyle(Style.FILL);
            this.au = new Paint();
            this.au.setColor(-65281);
            this.au.setStyle(Style.STROKE);
            this.au.setStrokeWidth((float) a(1));
        }
    }

    private synchronized void a(Point point) {
        a("initialiseBaseLayer maxTileDimensions=%dx%d", Integer.valueOf(point.x), Integer.valueOf(point.y));
        this.aw = new g(0.0f, new PointF(0.0f, 0.0f));
        a(true, this.aw);
        this.k = a(this.aw.a);
        if (this.k > 1) {
            this.k /= 2;
        }
        if (this.k != 1 || this.O != null || m() >= point.x || n() >= point.y) {
            b(point);
            for (h iVar : (List) this.l.get(Integer.valueOf(this.k))) {
                a(new i(this, this.W, iVar));
            }
            c(true);
        } else {
            this.W.b();
            this.W = null;
            a(new c(this, getContext(), this.ab, this.j, false));
        }
    }

    private void c(boolean z) {
        if (this.W != null && this.l != null) {
            int min = Math.min(this.k, a(this.D));
            for (Entry value : this.l.entrySet()) {
                for (h hVar : (List) value.getValue()) {
                    if (hVar.b < min || (hVar.b > min && hVar.b != this.k)) {
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
                                a(new i(this, this.W, hVar));
                            }
                        } else if (hVar.b != this.k) {
                            hVar.e = false;
                            if (hVar.c != null) {
                                hVar.c.recycle();
                                hVar.c = null;
                            }
                        }
                    } else if (hVar.b == this.k) {
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
        if (getWidth() != 0 && getHeight() != 0 && this.L > 0 && this.M > 0) {
            if (!(this.J == null || this.I == null)) {
                this.D = this.I.floatValue();
                if (this.F == null) {
                    this.F = new PointF();
                }
                this.F.x = ((float) (getWidth() / 2)) - (this.D * this.J.x);
                this.F.y = ((float) (getHeight() / 2)) - (this.D * this.J.y);
                this.J = null;
                this.I = null;
                d(true);
                c(true);
            }
            d(false);
        }
    }

    private int a(float f) {
        if (this.q > 0) {
            DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
            f *= ((float) this.q) / ((displayMetrics.ydpi + displayMetrics.xdpi) / 2.0f);
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
        if (this.r == 2 && b()) {
            z = false;
        }
        PointF b = gVar.b;
        float f2 = f(gVar.a);
        float m = f2 * ((float) m());
        float n = f2 * ((float) n());
        if (this.r == 3 && b()) {
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
        if (this.r == 3 && b()) {
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

    private void d(boolean z) {
        Object obj = null;
        if (this.F == null) {
            obj = 1;
            this.F = new PointF(0.0f, 0.0f);
        }
        if (this.aw == null) {
            this.aw = new g(0.0f, new PointF(0.0f, 0.0f));
        }
        this.aw.a = this.D;
        this.aw.b.set(this.F);
        a(z, this.aw);
        this.D = this.aw.a;
        this.F.set(this.aw.b);
        if (obj != null && this.s != 4) {
            this.F.set(a((float) (m() / 2), (float) (n() / 2), this.D));
        }
    }

    private void b(Point point) {
        a("initialiseTileMap maxTileDimensions=%dx%d", Integer.valueOf(point.x), Integer.valueOf(point.y));
        this.l = new LinkedHashMap();
        int i = this.k;
        int i2 = 1;
        int i3 = 1;
        while (true) {
            int m = m() / i2;
            int n = n() / i3;
            int i4 = m / i;
            int i5 = n / i;
            while (true) {
                if ((i4 + i2) + 1 > point.x || (((double) i4) > ((double) getWidth()) * 1.25d && i < this.k)) {
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
                if ((i4 + i3) + 1 > point.y || (((double) i4) > ((double) getHeight()) * 1.25d && i < this.k)) {
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
                    hVar.e = i == this.k;
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
            this.l.put(Integer.valueOf(i), arrayList);
            if (i != 1) {
                i /= 2;
            } else {
                return;
            }
        }
    }

    private synchronized void a(com.davemorrissey.labs.subscaleview.decoder.d dVar, int i, int i2, int i3) {
        a("onTilesInited sWidth=%d, sHeight=%d, sOrientation=%d", Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(this.n));
        if (this.L > 0 && this.M > 0 && !(this.L == i && this.M == i2)) {
            a(false);
            if (this.g != null) {
                if (!this.i) {
                    this.g.recycle();
                }
                this.g = null;
                if (this.ao != null && this.i) {
                    this.ao.c();
                }
                this.h = false;
                this.i = false;
            }
        }
        this.W = dVar;
        this.L = i;
        this.M = i2;
        this.N = i3;
        h();
        if (!i() && this.t > 0 && this.t != Integer.MAX_VALUE && this.u > 0 && this.u != Integer.MAX_VALUE && getWidth() > 0 && getHeight() > 0) {
            a(new Point(this.t, this.u));
        }
        invalidate();
        requestLayout();
    }

    private synchronized void l() {
        a("onTileLoaded", new Object[0]);
        h();
        i();
        if (g() && this.g != null) {
            if (!this.i) {
                this.g.recycle();
            }
            this.g = null;
            if (this.ao != null && this.i) {
                this.ao.c();
            }
            this.h = false;
            this.i = false;
        }
        invalidate();
    }

    private synchronized void a(Bitmap bitmap) {
        a("onPreviewLoaded", new Object[0]);
        if (this.g != null || this.an) {
            bitmap.recycle();
        } else {
            if (this.P != null) {
                this.g = Bitmap.createBitmap(bitmap, this.P.left, this.P.top, this.P.width(), this.P.height());
            } else {
                this.g = bitmap;
            }
            this.h = true;
            if (h()) {
                invalidate();
                requestLayout();
            }
        }
    }

    private synchronized void a(Bitmap bitmap, int i, boolean z) {
        a("onImageLoaded", new Object[0]);
        if (this.L > 0 && this.M > 0 && !(this.L == bitmap.getWidth() && this.M == bitmap.getHeight())) {
            a(false);
        }
        if (!(this.g == null || this.i)) {
            this.g.recycle();
        }
        if (!(this.g == null || !this.i || this.ao == null)) {
            this.ao.c();
        }
        this.h = false;
        this.i = z;
        this.g = bitmap;
        this.L = bitmap.getWidth();
        this.M = bitmap.getHeight();
        this.N = i;
        int width = getWidth();
        if (width > 0) {
            float max = Math.max(getMaxScale(), ((float) width) / ((float) this.L));
            setMaxScale(Math.min(max, 128.0f));
            setDoubleTapZoomScale(Math.min(max, 128.0f));
        }
        boolean h = h();
        boolean i2 = i();
        if (h || i2) {
            invalidate();
            requestLayout();
        }
    }

    @AnyThread
    private int a(Context context, String str) {
        Cursor cursor;
        Throwable th;
        int i;
        if (str.startsWith("content")) {
            Cursor query;
            try {
                query = context.getContentResolver().query(Uri.parse(str), new String[]{"orientation"}, null, null, null);
                if (query != null) {
                    try {
                        if (query.moveToFirst()) {
                            i = query.getInt(0);
                            if (!b.contains(Integer.valueOf(i)) || i == -1) {
                                Log.w(a, "Unsupported orientation: " + i);
                            }
                            if (query != null) {
                                query.close();
                            }
                            return i;
                        }
                    } catch (Exception e) {
                        cursor = query;
                        try {
                            Log.w(a, "Could not get orientation of image from media store");
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
                Log.w(a, "Could not get orientation of image from media store");
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
                i = new ExifInterface(str.substring("file:///".length() - 1)).getAttributeInt(ExifInterface.TAG_ORIENTATION, 1);
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
                Log.w(a, "Unsupported EXIF orientation: " + i);
                return 0;
            } catch (Exception e3) {
                Log.w(a, "Could not get EXIF orientation of image");
                return 0;
            }
        }
    }

    private void a(AsyncTask<Void, Void, ?> asyncTask) {
        asyncTask.executeOnExecutor(this.v, new Void[0]);
    }

    private void a(ImageViewState imageViewState) {
        if (imageViewState != null && imageViewState.getCenter() != null && b.contains(Integer.valueOf(imageViewState.getOrientation()))) {
            this.n = imageViewState.getOrientation();
            this.I = Float.valueOf(imageViewState.getScale());
            this.J = imageViewState.getCenter();
            invalidate();
        }
    }

    public void setMaxTileSize(int i) {
        this.t = i;
        this.u = i;
    }

    private Point a(Canvas canvas) {
        return new Point(Math.min(canvas.getMaximumBitmapWidth(), this.t), Math.min(canvas.getMaximumBitmapHeight(), this.u));
    }

    private int m() {
        int requiredRotation = getRequiredRotation();
        if (requiredRotation == 90 || requiredRotation == 270) {
            return this.M;
        }
        return this.L;
    }

    private int n() {
        int requiredRotation = getRequiredRotation();
        if (requiredRotation == 90 || requiredRotation == 270) {
            return this.L;
        }
        return this.M;
    }

    @AnyThread
    private void a(Rect rect, Rect rect2) {
        if (getRequiredRotation() == 0) {
            rect2.set(rect);
        } else if (getRequiredRotation() == 90) {
            rect2.set(rect.top, this.M - rect.right, rect.bottom, this.M - rect.left);
        } else if (getRequiredRotation() == 180) {
            rect2.set(this.L - rect.right, this.M - rect.bottom, this.L - rect.left, this.M - rect.top);
        } else {
            rect2.set(this.L - rect.bottom, rect.left, this.L - rect.top, rect.right);
        }
    }

    @AnyThread
    private int getRequiredRotation() {
        if (this.n == -1) {
            return this.N;
        }
        return this.n;
    }

    private float a(float f, float f2, float f3, float f4) {
        float f5 = f - f2;
        float f6 = f3 - f4;
        return (float) Math.sqrt((double) ((f5 * f5) + (f6 * f6)));
    }

    public void a() {
        a(true);
        this.as = null;
        this.at = null;
        this.au = null;
        this.av = null;
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

    private void b(Rect rect, Rect rect2) {
        rect2.set((int) d((float) rect.left), (int) e((float) rect.top), (int) d((float) rect.right), (int) e((float) rect.bottom));
    }

    private PointF a(float f, float f2, float f3) {
        int paddingLeft = getPaddingLeft() + (((getWidth() - getPaddingRight()) - getPaddingLeft()) / 2);
        int paddingTop = getPaddingTop() + (((getHeight() - getPaddingBottom()) - getPaddingTop()) / 2);
        if (this.aw == null) {
            this.aw = new g(0.0f, new PointF(0.0f, 0.0f));
        }
        this.aw.a = f3;
        this.aw.b.set(((float) paddingLeft) - (f * f3), ((float) paddingTop) - (f2 * f3));
        a(true, this.aw);
        return this.aw.b;
    }

    private PointF a(float f, float f2, float f3, PointF pointF) {
        PointF a = a(f, f2, f3);
        pointF.set((((float) (getPaddingLeft() + (((getWidth() - getPaddingRight()) - getPaddingLeft()) / 2))) - a.x) / f3, (((float) (getPaddingTop() + (((getHeight() - getPaddingBottom()) - getPaddingTop()) / 2))) - a.y) / f3);
        return pointF;
    }

    private float o() {
        int paddingBottom = getPaddingBottom() + getPaddingTop();
        int paddingLeft = getPaddingLeft() + getPaddingRight();
        if (this.s == 2 || this.s == 4) {
            return Math.max(((float) (getWidth() - paddingLeft)) / ((float) m()), ((float) (getHeight() - paddingBottom)) / ((float) n()));
        }
        if (this.s != 3 || this.p <= 0.0f) {
            return Math.min(((float) (getWidth() - paddingLeft)) / ((float) m()), ((float) (getHeight() - paddingBottom)) / ((float) n()));
        }
        return this.p;
    }

    private float f(float f) {
        return Math.min(this.o, Math.max(o(), f));
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

    @AnyThread
    private void a(String str, Object... objArr) {
        if (this.m) {
            Log.d(a, String.format(str, objArr));
        }
    }

    private int a(int i) {
        return (int) (this.aB * ((float) i));
    }

    public final void setRegionDecoderClass(Class<? extends com.davemorrissey.labs.subscaleview.decoder.d> cls) {
        if (cls == null) {
            throw new IllegalArgumentException("Decoder class cannot be set to null");
        }
        this.ac = new com.davemorrissey.labs.subscaleview.decoder.a(cls);
    }

    public final void setRegionDecoderFactory(com.davemorrissey.labs.subscaleview.decoder.b<? extends com.davemorrissey.labs.subscaleview.decoder.d> bVar) {
        if (bVar == null) {
            throw new IllegalArgumentException("Decoder factory cannot be set to null");
        }
        this.ac = bVar;
    }

    public final void setBitmapDecoderClass(Class<? extends com.davemorrissey.labs.subscaleview.decoder.c> cls) {
        if (cls == null) {
            throw new IllegalArgumentException("Decoder class cannot be set to null");
        }
        this.ab = new com.davemorrissey.labs.subscaleview.decoder.a(cls);
    }

    public final void setBitmapDecoderFactory(com.davemorrissey.labs.subscaleview.decoder.b<? extends com.davemorrissey.labs.subscaleview.decoder.c> bVar) {
        if (bVar == null) {
            throw new IllegalArgumentException("Decoder factory cannot be set to null");
        }
        this.ab = bVar;
    }

    public final void setPanLimit(int i) {
        if (e.contains(Integer.valueOf(i))) {
            this.r = i;
            if (b()) {
                d(true);
                invalidate();
                return;
            }
            return;
        }
        throw new IllegalArgumentException("Invalid pan limit: " + i);
    }

    public final void setMinimumScaleType(int i) {
        if (f.contains(Integer.valueOf(i))) {
            this.s = i;
            if (b()) {
                d(true);
                invalidate();
                return;
            }
            return;
        }
        throw new IllegalArgumentException("Invalid scale type: " + i);
    }

    public final void setMaxScale(float f) {
        this.o = f;
    }

    public final void setMinScale(float f) {
        this.p = f;
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
        return this.o;
    }

    public final float getMinScale() {
        return o();
    }

    public void setMinimumTileDpi(int i) {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        this.q = (int) Math.min((displayMetrics.ydpi + displayMetrics.xdpi) / 2.0f, (float) i);
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
        this.al = null;
        this.I = Float.valueOf(f);
        this.J = pointF;
        this.K = pointF;
        invalidate();
    }

    public final boolean b() {
        return this.am;
    }

    protected void c() {
    }

    protected void d() {
    }

    public final int getSWidth() {
        return this.L;
    }

    public final int getSHeight() {
        return this.M;
    }

    public final int getOrientation() {
        return this.n;
    }

    public final int getAppliedOrientation() {
        return getRequiredRotation();
    }

    public final ImageViewState getState() {
        if (this.F == null || this.L <= 0 || this.M <= 0) {
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
        if (!z && this.F != null) {
            this.F.x = ((float) (getWidth() / 2)) - (this.D * ((float) (m() / 2)));
            this.F.y = ((float) (getHeight() / 2)) - (this.D * ((float) (n() / 2)));
            if (b()) {
                c(true);
                invalidate();
            }
        }
    }

    public final void setTileBackgroundColor(int i) {
        if (Color.alpha(i) == 0) {
            this.av = null;
        } else {
            this.av = new Paint();
            this.av.setStyle(Style.FILL);
            this.av.setColor(i);
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
        if (c.contains(Integer.valueOf(i))) {
            this.B = i;
            return;
        }
        throw new IllegalArgumentException("Invalid zoom style: " + i);
    }

    public final void setDoubleTapZoomDuration(int i) {
        this.C = Math.max(0, i);
    }

    public void setExecutor(Executor executor) {
        if (executor == null) {
            throw new NullPointerException("Executor must not be null");
        }
        this.v = executor;
    }

    public void setEagerLoadingEnabled(boolean z) {
        this.w = z;
    }

    public final void setDebug(boolean z) {
        this.m = z;
    }

    public void setOnLongClickListener(OnLongClickListener onLongClickListener) {
        this.aq = onLongClickListener;
    }

    public void setOnImageEventListener(e eVar) {
        this.ao = eVar;
    }

    public void setOnStateChangedListener(f fVar) {
        this.ap = fVar;
    }

    private void a(float f, PointF pointF, int i) {
        if (!(this.ap == null || this.D == f)) {
            this.ap.a(this.D, i);
        }
        if (this.ap != null && !this.F.equals(pointF)) {
            this.ap.a(getCenter(), i);
        }
    }

    public b b(float f, PointF pointF) {
        if (b()) {
            return new b(f, pointF);
        }
        return null;
    }
}
