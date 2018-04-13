package bdj.uk.co.senab.photoview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.Matrix.ScaleToFit;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.OnDoubleTapListener;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import java.lang.ref.WeakReference;

public class d implements OnTouchListener, OnGlobalLayoutListener, bdj.uk.co.senab.photoview.a.e, c {
    private static /* synthetic */ int[] G;
    static final Interpolator a = new AccelerateDecelerateInterpolator();
    private static final boolean c = Log.isLoggable("PhotoViewAttacher", 3);
    private int A;
    private b B;
    private int C;
    private int D;
    private boolean E;
    private ScaleType F;
    int b;
    private float d;
    private float e;
    private float f;
    private boolean g;
    private boolean h;
    private boolean i;
    private boolean j;
    private WeakReference<ImageView> k;
    private GestureDetector l;
    private bdj.uk.co.senab.photoview.a.d m;
    private final Matrix n;
    private final Matrix o;
    private final Matrix p;
    private final RectF q;
    private final float[] r;
    private c s;
    private d t;
    private f u;
    private OnLongClickListener v;
    private e w;
    private int x;
    private int y;
    private int z;

    private class a implements Runnable {
        final /* synthetic */ d a;
        private final float b;
        private final float c;
        private final long d = System.currentTimeMillis();
        private final float e;
        private final float f;

        public a(d dVar, float f, float f2, float f3, float f4) {
            this.a = dVar;
            this.b = f3;
            this.c = f4;
            this.e = f;
            this.f = f2;
        }

        public void run() {
            View c = this.a.c();
            if (c != null) {
                float a = a();
                this.a.a((this.e + ((this.f - this.e) * a)) / this.a.g(), this.b, this.c);
                if (a < 1.0f) {
                    a.a(c, this);
                }
            }
        }

        private float a() {
            return d.a.getInterpolation(Math.min(1.0f, (((float) (System.currentTimeMillis() - this.d)) * 1.0f) / ((float) this.a.b)));
        }
    }

    private class b implements Runnable {
        final /* synthetic */ d a;
        private final bdj.uk.co.senab.photoview.c.d b;
        private int c;
        private int d;

        public b(d dVar, Context context) {
            this.a = dVar;
            this.b = bdj.uk.co.senab.photoview.c.d.a(context);
        }

        public void a() {
            if (d.c) {
                bdj.uk.co.senab.photoview.b.a.a().a("PhotoViewAttacher", "Cancel Fling");
            }
            this.b.a(true);
        }

        public void a(int i, int i2, int i3, int i4) {
            RectF b = this.a.b();
            if (b != null) {
                int round;
                int i5;
                int round2;
                int i6;
                int round3 = Math.round(-b.left);
                if (((float) i) < b.width()) {
                    round = Math.round(b.width() - ((float) i));
                    i5 = 0;
                } else {
                    round = round3;
                    i5 = round3;
                }
                int round4 = Math.round(-b.top);
                if (((float) i2) < b.height()) {
                    round2 = Math.round(b.height() - ((float) i2));
                    i6 = 0;
                } else {
                    round2 = round4;
                    i6 = round4;
                }
                this.c = round3;
                this.d = round4;
                if (d.c) {
                    bdj.uk.co.senab.photoview.b.a.a().a("PhotoViewAttacher", "fling. StartX:" + round3 + " StartY:" + round4 + " MaxX:" + round + " MaxY:" + round2);
                }
                if (round3 != round || round4 != round2) {
                    this.b.a(round3, round4, i3, i4, i5, round, i6, round2, 0, 0);
                }
            }
        }

        public void run() {
            if (!this.b.b()) {
                View c = this.a.c();
                if (c != null && this.b.a()) {
                    int c2 = this.b.c();
                    int d = this.b.d();
                    if (d.c) {
                        bdj.uk.co.senab.photoview.b.a.a().a("PhotoViewAttacher", "fling run(). CurrentX:" + this.c + " CurrentY:" + this.d + " NewX:" + c2 + " NewY:" + d);
                    }
                    this.a.p.postTranslate((float) (this.c - c2), (float) (this.d - d));
                    this.a.b(this.a.m());
                    this.c = c2;
                    this.d = d;
                    a.a(c, this);
                }
            }
        }
    }

    public interface c {
        void a(RectF rectF);
    }

    public interface d {
        void a(View view, float f, float f2);
    }

    public interface e {
        void a(float f, float f2, float f3);
    }

    public interface f {
        void a(View view, float f, float f2);
    }

    static /* synthetic */ int[] p() {
        int[] iArr = G;
        if (iArr == null) {
            iArr = new int[ScaleType.values().length];
            try {
                iArr[ScaleType.CENTER.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[ScaleType.CENTER_CROP.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[ScaleType.CENTER_INSIDE.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[ScaleType.FIT_CENTER.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                iArr[ScaleType.FIT_END.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                iArr[ScaleType.FIT_START.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                iArr[ScaleType.FIT_XY.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                iArr[ScaleType.MATRIX.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
            G = iArr;
        }
        return iArr;
    }

    private static void b(float f, float f2, float f3) {
        if (f >= f2) {
            throw new IllegalArgumentException("MinZoom has to be less than MidZoom");
        } else if (f2 >= f3) {
            throw new IllegalArgumentException("MidZoom has to be less than MaxZoom");
        }
    }

    private static boolean a(ImageView imageView) {
        return (imageView == null || imageView.getDrawable() == null) ? false : true;
    }

    private static boolean b(ScaleType scaleType) {
        if (scaleType == null) {
            return false;
        }
        switch (p()[scaleType.ordinal()]) {
            case 8:
                throw new IllegalArgumentException(scaleType.name() + " is not supported in PhotoView");
            default:
                return true;
        }
    }

    private static void b(ImageView imageView) {
        if (imageView != null && !(imageView instanceof c) && !ScaleType.MATRIX.equals(imageView.getScaleType())) {
            imageView.setScaleType(ScaleType.MATRIX);
        }
    }

    public d(ImageView imageView) {
        this(imageView, true);
    }

    public d(ImageView imageView, boolean z) {
        this.b = 200;
        this.d = 1.0f;
        this.e = 1.75f;
        this.f = 3.0f;
        this.g = true;
        this.h = true;
        this.i = true;
        this.j = false;
        this.n = new Matrix();
        this.o = new Matrix();
        this.p = new Matrix();
        this.q = new RectF();
        this.r = new float[9];
        this.C = 2;
        this.D = 2;
        this.F = ScaleType.FIT_CENTER;
        this.k = new WeakReference(imageView);
        imageView.setDrawingCacheEnabled(true);
        imageView.setOnTouchListener(this);
        ViewTreeObserver viewTreeObserver = imageView.getViewTreeObserver();
        if (viewTreeObserver != null) {
            viewTreeObserver.addOnGlobalLayoutListener(this);
        }
        b(imageView);
        if (!imageView.isInEditMode()) {
            this.m = bdj.uk.co.senab.photoview.a.f.a(imageView.getContext(), this);
            this.l = new GestureDetector(imageView.getContext(), new SimpleOnGestureListener(this) {
                final /* synthetic */ d a;

                {
                    this.a = r1;
                }

                public void onLongPress(MotionEvent motionEvent) {
                    if (this.a.v != null) {
                        this.a.v.onLongClick(this.a.c());
                    }
                }
            });
            this.l.setOnDoubleTapListener(new b(this));
            d(z);
        }
    }

    public void a(OnDoubleTapListener onDoubleTapListener) {
        if (onDoubleTapListener != null) {
            this.l.setOnDoubleTapListener(onDoubleTapListener);
        } else {
            this.l.setOnDoubleTapListener(new b(this));
        }
    }

    public void a(e eVar) {
        this.w = eVar;
    }

    public void a() {
        if (this.k != null) {
            ImageView imageView = (ImageView) this.k.get();
            if (imageView != null) {
                ViewTreeObserver viewTreeObserver = imageView.getViewTreeObserver();
                if (viewTreeObserver != null && viewTreeObserver.isAlive()) {
                    viewTreeObserver.removeGlobalOnLayoutListener(this);
                }
                imageView.setOnTouchListener(null);
                q();
            }
            if (this.l != null) {
                this.l.setOnDoubleTapListener(null);
            }
            this.s = null;
            this.t = null;
            this.u = null;
            this.k = null;
        }
    }

    public RectF b() {
        t();
        return a(m());
    }

    public void a(float f) {
        this.p.setRotate(f % 360.0f);
        r();
    }

    public void b(float f) {
        this.p.postRotate(f % 360.0f);
        r();
    }

    public ImageView c() {
        ImageView imageView = null;
        if (this.k != null) {
            imageView = (ImageView) this.k.get();
        }
        if (imageView == null) {
            a();
            bdj.uk.co.senab.photoview.b.a.a().b("PhotoViewAttacher", "ImageView no longer exists. You should not use this PhotoViewAttacher any more.");
        }
        return imageView;
    }

    public float d() {
        return this.d;
    }

    public float e() {
        return this.e;
    }

    public float f() {
        return this.f;
    }

    public float g() {
        return (float) Math.sqrt((double) (((float) Math.pow((double) a(this.p, 0), 2.0d)) + ((float) Math.pow((double) a(this.p, 3), 2.0d))));
    }

    public ScaleType h() {
        return this.F;
    }

    public void a(float f, float f2) {
        if (!this.m.a()) {
            if (c) {
                bdj.uk.co.senab.photoview.b.a.a().a("PhotoViewAttacher", String.format("onDrag: dx: %.2f. dy: %.2f", new Object[]{Float.valueOf(f), Float.valueOf(f2)}));
            }
            ImageView c = c();
            this.p.postTranslate(f, f2);
            r();
            ViewParent parent = c.getParent();
            if ((this.g || this.h) && !this.m.a() && !this.j) {
                if (this.g && ((this.C == 2 || ((this.C == 0 && f >= 1.0f) || (this.C == 1 && f <= -1.0f))) && parent != null)) {
                    parent.requestDisallowInterceptTouchEvent(false);
                }
                if (!this.h) {
                    return;
                }
                if ((this.D == 2 || ((this.D == 3 && f2 >= 1.0f) || (this.D == 4 && f2 <= -1.0f))) && parent != null) {
                    parent.requestDisallowInterceptTouchEvent(false);
                }
            } else if (parent != null) {
                parent.requestDisallowInterceptTouchEvent(true);
            }
        }
    }

    public void a(float f, float f2, float f3, float f4) {
        if (c) {
            bdj.uk.co.senab.photoview.b.a.a().a("PhotoViewAttacher", "onFling. sX: " + f + " sY: " + f2 + " Vx: " + f3 + " Vy: " + f4);
        }
        ImageView c = c();
        this.B = new b(this, c.getContext());
        this.B.a(c(c), d(c), (int) f3, (int) f4);
        c.post(this.B);
    }

    public void onGlobalLayout() {
        ImageView c = c();
        if (c == null) {
            return;
        }
        if (this.E) {
            int top = c.getTop();
            int right = c.getRight();
            int bottom = c.getBottom();
            int left = c.getLeft();
            if (top != this.x || bottom != this.z || left != this.A || right != this.y) {
                a(c.getDrawable());
                this.x = top;
                this.y = right;
                this.z = bottom;
                this.A = left;
                return;
            }
            return;
        }
        a(c.getDrawable());
    }

    public void a(float f, float f2, float f3) {
        if (c) {
            bdj.uk.co.senab.photoview.b.a.a().a("PhotoViewAttacher", String.format("onScale: scale: %.2f. fX: %.2f. fY: %.2f", new Object[]{Float.valueOf(f), Float.valueOf(f2), Float.valueOf(f3)}));
        }
        if (g() < this.f || f < 1.0f) {
            if (this.w != null) {
                this.w.a(f, f2, f3);
            }
            this.p.postScale(f, f, f2, f3);
            r();
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @android.annotation.SuppressLint({"ClickableViewAccessibility"})
    public boolean onTouch(android.view.View r9, android.view.MotionEvent r10) {
        /*
        r8 = this;
        r6 = 0;
        r7 = 1;
        r0 = r8.i;
        if (r0 == 0) goto L_0x005f;
    L_0x0006:
        r0 = r8.E;
        if (r0 == 0) goto L_0x005f;
    L_0x000a:
        r0 = r9;
        r0 = (android.widget.ImageView) r0;
        r0 = a(r0);
        if (r0 == 0) goto L_0x005f;
    L_0x0013:
        r0 = r9.getParent();
        r1 = r10.getAction();
        switch(r1) {
            case 0: goto L_0x0060;
            case 1: goto L_0x0076;
            case 2: goto L_0x001e;
            case 3: goto L_0x0076;
            default: goto L_0x001e;
        };
    L_0x001e:
        r0 = r6;
    L_0x001f:
        r1 = r8.m;
        if (r1 == 0) goto L_0x0052;
    L_0x0023:
        r0 = r8.m;
        r1 = r0.a();
        r0 = r8.m;
        r3 = r0.b();
        r0 = r8.m;
        r0 = r0.c(r10);
        if (r1 != 0) goto L_0x009f;
    L_0x0037:
        r1 = r8.m;
        r1 = r1.a();
        if (r1 != 0) goto L_0x009f;
    L_0x003f:
        r2 = r7;
    L_0x0040:
        if (r3 != 0) goto L_0x00a1;
    L_0x0042:
        r1 = r8.m;
        r1 = r1.b();
        if (r1 != 0) goto L_0x00a1;
    L_0x004a:
        r1 = r7;
    L_0x004b:
        if (r2 == 0) goto L_0x0050;
    L_0x004d:
        if (r1 == 0) goto L_0x0050;
    L_0x004f:
        r6 = r7;
    L_0x0050:
        r8.j = r6;
    L_0x0052:
        r1 = r8.l;
        if (r1 == 0) goto L_0x00a3;
    L_0x0056:
        r1 = r8.l;
        r1 = r1.onTouchEvent(r10);
        if (r1 == 0) goto L_0x00a3;
    L_0x005e:
        r6 = r7;
    L_0x005f:
        return r6;
    L_0x0060:
        if (r0 == 0) goto L_0x006a;
    L_0x0062:
        r0.requestDisallowInterceptTouchEvent(r7);
    L_0x0065:
        r8.q();
        r0 = r6;
        goto L_0x001f;
    L_0x006a:
        r0 = bdj.uk.co.senab.photoview.b.a.a();
        r1 = "PhotoViewAttacher";
        r2 = "onTouch getParent() returned null";
        r0.b(r1, r2);
        goto L_0x0065;
    L_0x0076:
        r0 = r8.g();
        r1 = r8.d;
        r0 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1));
        if (r0 >= 0) goto L_0x001e;
    L_0x0080:
        r1 = r8.b();
        if (r1 == 0) goto L_0x001e;
    L_0x0086:
        r0 = new bdj.uk.co.senab.photoview.d$a;
        r2 = r8.g();
        r3 = r8.d;
        r4 = r1.centerX();
        r5 = r1.centerY();
        r1 = r8;
        r0.<init>(r1, r2, r3, r4, r5);
        r9.post(r0);
        r0 = r7;
        goto L_0x001f;
    L_0x009f:
        r2 = r6;
        goto L_0x0040;
    L_0x00a1:
        r1 = r6;
        goto L_0x004b;
    L_0x00a3:
        r6 = r0;
        goto L_0x005f;
        */
        throw new UnsupportedOperationException("Method not decompiled: bdj.uk.co.senab.photoview.d.onTouch(android.view.View, android.view.MotionEvent):boolean");
    }

    public void a(boolean z) {
        this.g = z;
    }

    public void b(boolean z) {
        this.g = z;
    }

    public void c(boolean z) {
        this.i = z;
    }

    public void c(float f) {
        b(f, this.e, this.f);
        this.d = f;
    }

    public void d(float f) {
        b(this.d, f, this.f);
        this.e = f;
    }

    public void e(float f) {
        b(this.d, this.e, f);
        this.f = f;
    }

    public void a(OnLongClickListener onLongClickListener) {
        this.v = onLongClickListener;
    }

    public void a(c cVar) {
        this.s = cVar;
    }

    public void a(d dVar) {
        this.t = dVar;
    }

    public d i() {
        return this.t;
    }

    public void a(f fVar) {
        this.u = fVar;
    }

    public f j() {
        return this.u;
    }

    public void f(float f) {
        a(f, false);
    }

    public void a(float f, boolean z) {
        ImageView c = c();
        if (c != null) {
            a(f, (float) (c.getRight() / 2), (float) (c.getBottom() / 2), z);
        }
    }

    public void a(float f, float f2, float f3, boolean z) {
        ImageView c = c();
        if (c == null) {
            return;
        }
        if (f < this.d || f > this.f) {
            bdj.uk.co.senab.photoview.b.a.a().b("PhotoViewAttacher", "Scale must be within the range of minScale and maxScale");
        } else if (z) {
            c.post(new a(this, g(), f, f2, f3));
        } else {
            this.p.setScale(f, f, f2, f3);
            r();
        }
    }

    public void a(ScaleType scaleType) {
        if (b(scaleType) && scaleType != this.F) {
            this.F = scaleType;
            k();
        }
    }

    public void d(boolean z) {
        this.E = z;
        k();
    }

    public void k() {
        ImageView c = c();
        if (c == null) {
            return;
        }
        if (this.E) {
            b(c);
            a(c.getDrawable());
            return;
        }
        u();
    }

    public Matrix l() {
        return new Matrix(m());
    }

    public Matrix m() {
        this.o.set(this.n);
        this.o.postConcat(this.p);
        return this.o;
    }

    private void q() {
        if (this.B != null) {
            this.B.a();
            this.B = null;
        }
    }

    private void r() {
        if (t()) {
            b(m());
        }
    }

    private void s() {
        ImageView c = c();
        if (c != null && !(c instanceof c) && !ScaleType.MATRIX.equals(c.getScaleType())) {
            throw new IllegalStateException("The ImageView's ScaleType has been changed since attaching a PhotoViewAttacher");
        }
    }

    private boolean t() {
        float f = 0.0f;
        ImageView c = c();
        if (c == null) {
            return false;
        }
        RectF a = a(m());
        if (a == null) {
            return false;
        }
        float height = a.height();
        float width = a.width();
        int d = d(c);
        if (height <= ((float) d)) {
            switch (p()[this.F.ordinal()]) {
                case 5:
                    height = (((float) d) - height) - a.top;
                    break;
                case 6:
                    height = -a.top;
                    break;
                default:
                    height = ((((float) d) - height) / 2.0f) - a.top;
                    break;
            }
            this.D = 2;
        } else if (a.top > 0.0f) {
            height = -a.top;
            this.D = 3;
        } else if (a.bottom < ((float) d)) {
            height = ((float) d) - a.bottom;
            this.D = 4;
        } else {
            this.D = -1;
            height = 0.0f;
        }
        int c2 = c(c);
        if (width <= ((float) c2)) {
            switch (p()[this.F.ordinal()]) {
                case 5:
                    f = (((float) c2) - width) - a.left;
                    break;
                case 6:
                    f = -a.left;
                    break;
                default:
                    f = ((((float) c2) - width) / 2.0f) - a.left;
                    break;
            }
            this.C = 2;
        } else if (a.left > 0.0f) {
            this.C = 0;
            f = -a.left;
        } else if (a.right < ((float) c2)) {
            f = ((float) c2) - a.right;
            this.C = 1;
        } else {
            this.C = -1;
        }
        this.p.postTranslate(f, height);
        return true;
    }

    private RectF a(Matrix matrix) {
        ImageView c = c();
        if (c != null) {
            Drawable drawable = c.getDrawable();
            if (drawable != null) {
                this.q.set(0.0f, 0.0f, (float) drawable.getIntrinsicWidth(), (float) drawable.getIntrinsicHeight());
                matrix.mapRect(this.q);
                return this.q;
            }
        }
        return null;
    }

    public Bitmap n() {
        ImageView c = c();
        return c == null ? null : c.getDrawingCache();
    }

    public void a(int i) {
        if (i < 0) {
            i = 200;
        }
        this.b = i;
    }

    private float a(Matrix matrix, int i) {
        matrix.getValues(this.r);
        return this.r[i];
    }

    private void u() {
        this.p.reset();
        b(m());
        t();
    }

    private void b(Matrix matrix) {
        ImageView c = c();
        if (c != null) {
            s();
            c.setImageMatrix(matrix);
            if (this.s != null) {
                RectF a = a(matrix);
                if (a != null) {
                    this.s.a(a);
                }
            }
        }
    }

    private void a(Drawable drawable) {
        ImageView c = c();
        if (c != null && drawable != null) {
            float c2 = (float) c(c);
            float d = (float) d(c);
            int intrinsicWidth = drawable.getIntrinsicWidth();
            int intrinsicHeight = drawable.getIntrinsicHeight();
            this.n.reset();
            float f = c2 / ((float) intrinsicWidth);
            float f2 = d / ((float) intrinsicHeight);
            if (this.F != ScaleType.CENTER) {
                if (this.F != ScaleType.CENTER_CROP) {
                    if (this.F != ScaleType.CENTER_INSIDE) {
                        RectF rectF = new RectF(0.0f, 0.0f, (float) intrinsicWidth, (float) intrinsicHeight);
                        RectF rectF2 = new RectF(0.0f, 0.0f, c2, d);
                        switch (p()[this.F.ordinal()]) {
                            case 4:
                                this.n.setRectToRect(rectF, rectF2, ScaleToFit.CENTER);
                                break;
                            case 5:
                                this.n.setRectToRect(rectF, rectF2, ScaleToFit.END);
                                break;
                            case 6:
                                this.n.setRectToRect(rectF, rectF2, ScaleToFit.START);
                                break;
                            case 7:
                                this.n.setRectToRect(rectF, rectF2, ScaleToFit.FILL);
                                break;
                            default:
                                break;
                        }
                    }
                    f = Math.min(1.0f, Math.min(f, f2));
                    this.n.postScale(f, f);
                    this.n.postTranslate((c2 - (((float) intrinsicWidth) * f)) / 2.0f, (d - (((float) intrinsicHeight) * f)) / 2.0f);
                } else {
                    d = Math.max(f, f2);
                    this.n.postScale(d, d);
                    this.n.postTranslate((c2 - (d * ((float) intrinsicWidth))) / 2.0f, 0.0f);
                }
            } else {
                this.n.postTranslate((c2 - ((float) intrinsicWidth)) / 2.0f, (d - ((float) intrinsicHeight)) / 2.0f);
            }
            u();
        }
    }

    private int c(ImageView imageView) {
        if (imageView == null) {
            return 0;
        }
        return (imageView.getWidth() - imageView.getPaddingLeft()) - imageView.getPaddingRight();
    }

    private int d(ImageView imageView) {
        if (imageView == null) {
            return 0;
        }
        return (imageView.getHeight() - imageView.getPaddingTop()) - imageView.getPaddingBottom();
    }
}
