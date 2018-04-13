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
import android.support.annotation.AnyThread;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnLongClickListener;
import android.view.ViewParent;
import com.baidu.mobstat.Config;
import com.davemorrissey.labs.subscaleview.decoder.CompatDecoderFactory;
import com.davemorrissey.labs.subscaleview.decoder.DecoderFactory;
import com.davemorrissey.labs.subscaleview.decoder.ImageDecoder;
import com.davemorrissey.labs.subscaleview.decoder.ImageRegionDecoder;
import com.davemorrissey.labs.subscaleview.decoder.SkiaImageDecoder;
import com.davemorrissey.labs.subscaleview.decoder.SkiaImageRegionDecoder;
import com.xiaomi.mipush.sdk.Constants;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.Executor;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import qsbk.app.R;
import qsbk.app.api.BigCoverHelper;

public class SubsamplingScaleImageView extends View {
    public static final int EASE_IN_OUT_QUAD = 2;
    public static final int EASE_OUT_QUAD = 1;
    public static final int ORIENTATION_0 = 0;
    public static final int ORIENTATION_180 = 180;
    public static final int ORIENTATION_270 = 270;
    public static final int ORIENTATION_90 = 90;
    public static final int ORIENTATION_USE_EXIF = -1;
    public static final int ORIGIN_ANIM = 1;
    public static final int ORIGIN_DOUBLE_TAP_ZOOM = 4;
    public static final int ORIGIN_FLING = 3;
    public static final int ORIGIN_TOUCH = 2;
    public static final int PAN_LIMIT_CENTER = 3;
    public static final int PAN_LIMIT_INSIDE = 1;
    public static final int PAN_LIMIT_OUTSIDE = 2;
    public static final int SCALE_TYPE_CENTER_CROP = 2;
    public static final int SCALE_TYPE_CENTER_INSIDE = 1;
    public static final int SCALE_TYPE_CUSTOM = 3;
    public static final int SCALE_TYPE_START = 4;
    public static int TILE_SIZE_AUTO = Integer.MAX_VALUE;
    public static final int ZOOM_FOCUS_CENTER = 2;
    public static final int ZOOM_FOCUS_CENTER_IMMEDIATE = 3;
    public static final int ZOOM_FOCUS_FIXED = 1;
    private static final String a = SubsamplingScaleImageView.class.getSimpleName();
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
    private float O;
    private Rect P;
    private Rect Q;
    private boolean R;
    private boolean S;
    private boolean T;
    private int U;
    private GestureDetector V;
    private ImageRegionDecoder W;
    private DecoderFactory<? extends ImageDecoder> aa;
    private DecoderFactory<? extends ImageRegionDecoder> ab;
    private PointF ac;
    private float ad;
    private float ae;
    private boolean af;
    private PointF ag;
    private PointF ah;
    private PointF ai;
    private a aj;
    private boolean ak;
    private boolean al;
    private OnImageEventListener am;
    private OnStateChangedListener an;
    private OnLongClickListener ao;
    private Handler ap;
    private Paint aq;
    private Paint ar;
    private Paint as;
    private c at;
    private Matrix au;
    private RectF av;
    private float[] aw;
    private float[] ax;
    private float ay;
    private final Object g;
    private final float h;
    private Bitmap i;
    private boolean j;
    private boolean k;
    private Uri l;
    private int m;
    private Map<Integer, List<d>> n;
    private boolean o;
    private int p;
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

    public final class AnimationBuilder {
        final /* synthetic */ SubsamplingScaleImageView a;
        private final float b;
        private final PointF c;
        private final PointF d;
        private long e;
        private int f;
        private int g;
        private boolean h;
        private boolean i;
        private OnAnimationEventListener j;

        private AnimationBuilder(SubsamplingScaleImageView subsamplingScaleImageView, PointF pointF) {
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

        private AnimationBuilder(SubsamplingScaleImageView subsamplingScaleImageView, float f) {
            this.a = subsamplingScaleImageView;
            this.e = 500;
            this.f = 2;
            this.g = 1;
            this.h = true;
            this.i = true;
            this.b = f;
            this.c = subsamplingScaleImageView.getCenter();
            this.d = null;
        }

        private AnimationBuilder(SubsamplingScaleImageView subsamplingScaleImageView, float f, PointF pointF) {
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

        private AnimationBuilder(SubsamplingScaleImageView subsamplingScaleImageView, float f, PointF pointF, PointF pointF2) {
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

        public AnimationBuilder withDuration(long j) {
            this.e = j;
            return this;
        }

        public AnimationBuilder withInterruptible(boolean z) {
            this.h = z;
            return this;
        }

        public AnimationBuilder withEasing(int i) {
            if (SubsamplingScaleImageView.d.contains(Integer.valueOf(i))) {
                this.f = i;
                return this;
            }
            throw new IllegalArgumentException("Unknown easing type: " + i);
        }

        public AnimationBuilder withOnAnimationEventListener(OnAnimationEventListener onAnimationEventListener) {
            this.j = onAnimationEventListener;
            return this;
        }

        private AnimationBuilder a(boolean z) {
            this.i = z;
            return this;
        }

        private AnimationBuilder a(int i) {
            this.g = i;
            return this;
        }

        public void start() {
            if (!(this.a.aj == null || this.a.aj.m == null)) {
                try {
                    this.a.aj.m.onInterruptedByNewAnim();
                } catch (Throwable e) {
                    Log.w(SubsamplingScaleImageView.a, "Error thrown by animation listener", e);
                }
            }
            int width = (((this.a.getWidth() - this.a.getPaddingRight()) - this.a.getPaddingLeft()) / 2) + this.a.getPaddingLeft();
            int height = (((this.a.getHeight() - this.a.getPaddingBottom()) - this.a.getPaddingTop()) / 2) + this.a.getPaddingTop();
            float c = this.a.f(this.b);
            PointF a = this.i ? this.a.a(this.c.x, this.c.y, c, new PointF()) : this.c;
            this.a.aj = new a();
            this.a.aj.a = this.a.D;
            this.a.aj.b = c;
            this.a.aj.l = System.currentTimeMillis();
            this.a.aj.e = a;
            this.a.aj.c = this.a.getCenter();
            this.a.aj.d = a;
            this.a.aj.f = this.a.sourceToViewCoord(a);
            this.a.aj.g = new PointF((float) width, (float) height);
            this.a.aj.h = this.e;
            this.a.aj.i = this.h;
            this.a.aj.j = this.f;
            this.a.aj.k = this.g;
            this.a.aj.l = System.currentTimeMillis();
            this.a.aj.m = this.j;
            if (this.d != null) {
                float f = this.d.x - (this.a.aj.c.x * c);
                float f2 = this.d.y - (this.a.aj.c.y * c);
                c cVar = new c(c, new PointF(f, f2));
                this.a.a(true, cVar);
                this.a.aj.g = new PointF((cVar.b.x - f) + this.d.x, (cVar.b.y - f2) + this.d.y);
            }
            this.a.invalidate();
        }
    }

    public interface OnAnimationEventListener {
        void onComplete();

        void onInterruptedByNewAnim();

        void onInterruptedByUser();
    }

    public static class DefaultOnAnimationEventListener implements OnAnimationEventListener {
        public void onComplete() {
        }

        public void onInterruptedByUser() {
        }

        public void onInterruptedByNewAnim() {
        }
    }

    public interface OnImageEventListener {
        void onImageLoadError(Exception exception);

        void onImageLoaded();

        void onPreviewLoadError(Exception exception);

        void onPreviewReleased();

        void onReady();

        void onTileLoadError(Exception exception);
    }

    public static class DefaultOnImageEventListener implements OnImageEventListener {
        public void onReady() {
        }

        public void onImageLoaded() {
        }

        public void onPreviewLoadError(Exception exception) {
        }

        public void onImageLoadError(Exception exception) {
        }

        public void onTileLoadError(Exception exception) {
        }

        public void onPreviewReleased() {
        }
    }

    public interface OnStateChangedListener {
        void onCenterChanged(PointF pointF, int i);

        void onScaleChanged(float f, int i);
    }

    public static class DefaultOnStateChangedListener implements OnStateChangedListener {
        public void onCenterChanged(PointF pointF, int i) {
        }

        public void onScaleChanged(float f, int i) {
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
        private int k;
        private long l;
        private OnAnimationEventListener m;

        private a() {
            this.h = 500;
            this.i = true;
            this.j = 2;
            this.k = 1;
            this.l = System.currentTimeMillis();
        }
    }

    private static class b extends AsyncTask<Void, Void, Integer> {
        private final WeakReference<SubsamplingScaleImageView> a;
        private final WeakReference<Context> b;
        private final WeakReference<DecoderFactory<? extends ImageDecoder>> c;
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

        b(SubsamplingScaleImageView subsamplingScaleImageView, Context context, DecoderFactory<? extends ImageDecoder> decoderFactory, Uri uri, boolean z) {
            this.a = new WeakReference(subsamplingScaleImageView);
            this.b = new WeakReference(context);
            this.c = new WeakReference(decoderFactory);
            this.d = uri;
            this.e = z;
        }

        protected Integer a(Void... voidArr) {
            try {
                String uri = this.d.toString();
                Context context = (Context) this.b.get();
                DecoderFactory decoderFactory = (DecoderFactory) this.c.get();
                SubsamplingScaleImageView subsamplingScaleImageView = (SubsamplingScaleImageView) this.a.get();
                if (!(context == null || decoderFactory == null || subsamplingScaleImageView == null)) {
                    subsamplingScaleImageView.a("BitmapLoadTask.doInBackground", new Object[0]);
                    this.f = ((ImageDecoder) decoderFactory.make()).decode(context, this.d);
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
                if (this.g != null && subsamplingScaleImageView.am != null) {
                    if (this.e) {
                        subsamplingScaleImageView.am.onPreviewLoadError(this.g);
                    } else {
                        subsamplingScaleImageView.am.onImageLoadError(this.g);
                    }
                }
            } else if (this.e) {
                subsamplingScaleImageView.a(this.f);
            } else {
                subsamplingScaleImageView.a(this.f, num.intValue(), false);
            }
        }
    }

    private static class c {
        private float a;
        private PointF b;

        private c(float f, PointF pointF) {
            this.a = f;
            this.b = pointF;
        }
    }

    private static class d {
        private Rect a;
        private int b;
        private Bitmap c;
        private boolean d;
        private boolean e;
        private Rect f;
        private Rect g;

        private d() {
        }
    }

    private static class e extends AsyncTask<Void, Void, Bitmap> {
        private final WeakReference<SubsamplingScaleImageView> a;
        private final WeakReference<ImageRegionDecoder> b;
        private final WeakReference<d> c;
        private Exception d;

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return a((Void[]) objArr);
        }

        protected /* synthetic */ void onPostExecute(Object obj) {
            a((Bitmap) obj);
        }

        e(SubsamplingScaleImageView subsamplingScaleImageView, ImageRegionDecoder imageRegionDecoder, d dVar) {
            this.a = new WeakReference(subsamplingScaleImageView);
            this.b = new WeakReference(imageRegionDecoder);
            this.c = new WeakReference(dVar);
            dVar.d = true;
        }

        protected Bitmap a(Void... voidArr) {
            try {
                SubsamplingScaleImageView subsamplingScaleImageView = (SubsamplingScaleImageView) this.a.get();
                ImageRegionDecoder imageRegionDecoder = (ImageRegionDecoder) this.b.get();
                d dVar = (d) this.c.get();
                if (imageRegionDecoder == null || dVar == null || subsamplingScaleImageView == null || !imageRegionDecoder.isReady() || !dVar.e) {
                    if (dVar != null) {
                        dVar.d = false;
                    }
                    return null;
                }
                Bitmap decodeRegion;
                subsamplingScaleImageView.a("TileLoadTask.doInBackground, tile.sRect=%s, tile.sampleSize=%d", dVar.a, Integer.valueOf(dVar.b));
                synchronized (subsamplingScaleImageView.g) {
                    subsamplingScaleImageView.a(dVar.a, dVar.g);
                    if (subsamplingScaleImageView.P != null) {
                        dVar.g.offset(subsamplingScaleImageView.P.left, subsamplingScaleImageView.P.top);
                    }
                    decodeRegion = imageRegionDecoder.decodeRegion(dVar.g, dVar.b);
                }
                return decodeRegion;
            } catch (Throwable e) {
                Log.e(SubsamplingScaleImageView.a, "Failed to decode tile", e);
                this.d = e;
            } catch (Throwable e2) {
                Log.e(SubsamplingScaleImageView.a, "Failed to decode tile - OutOfMemoryError", e2);
                this.d = new RuntimeException(e2);
            }
        }

        protected void a(Bitmap bitmap) {
            SubsamplingScaleImageView subsamplingScaleImageView = (SubsamplingScaleImageView) this.a.get();
            d dVar = (d) this.c.get();
            if (subsamplingScaleImageView != null && dVar != null) {
                if (bitmap != null) {
                    dVar.c = bitmap;
                    dVar.d = false;
                    subsamplingScaleImageView.j();
                } else if (this.d != null && subsamplingScaleImageView.am != null) {
                    subsamplingScaleImageView.am.onTileLoadError(this.d);
                }
            }
        }
    }

    private static class f extends AsyncTask<Void, Void, int[]> {
        private final WeakReference<SubsamplingScaleImageView> a;
        private final WeakReference<Context> b;
        private final WeakReference<DecoderFactory<? extends ImageRegionDecoder>> c;
        private final Uri d;
        private ImageRegionDecoder e;
        private Exception f;

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return a((Void[]) objArr);
        }

        protected /* synthetic */ void onPostExecute(Object obj) {
            a((int[]) obj);
        }

        f(SubsamplingScaleImageView subsamplingScaleImageView, Context context, DecoderFactory<? extends ImageRegionDecoder> decoderFactory, Uri uri) {
            this.a = new WeakReference(subsamplingScaleImageView);
            this.b = new WeakReference(context);
            this.c = new WeakReference(decoderFactory);
            this.d = uri;
        }

        protected int[] a(Void... voidArr) {
            try {
                String uri = this.d.toString();
                Context context = (Context) this.b.get();
                DecoderFactory decoderFactory = (DecoderFactory) this.c.get();
                SubsamplingScaleImageView subsamplingScaleImageView = (SubsamplingScaleImageView) this.a.get();
                if (!(context == null || decoderFactory == null || subsamplingScaleImageView == null)) {
                    int width;
                    subsamplingScaleImageView.a("TilesInitTask.doInBackground", new Object[0]);
                    this.e = (ImageRegionDecoder) decoderFactory.make();
                    Point init = this.e.init(context, this.d);
                    int i = init.x;
                    int i2 = init.y;
                    int a = subsamplingScaleImageView.a(context, uri);
                    if (subsamplingScaleImageView.P != null) {
                        width = subsamplingScaleImageView.P.width();
                        i2 = subsamplingScaleImageView.P.height();
                    } else {
                        width = i;
                    }
                    return new int[]{width, i2, a};
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
            } else if (this.f != null && subsamplingScaleImageView.am != null) {
                subsamplingScaleImageView.am.onImageLoadError(this.f);
            }
        }
    }

    public SubsamplingScaleImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.g = new Object();
        this.p = 0;
        this.q = 2.0f;
        this.r = -1;
        this.s = 1;
        this.t = 1;
        this.u = TILE_SIZE_AUTO;
        this.v = TILE_SIZE_AUTO;
        this.x = true;
        this.y = true;
        this.z = true;
        this.A = 1.0f;
        this.B = 1;
        this.C = 500;
        this.O = m();
        this.aa = new CompatDecoderFactory(SkiaImageDecoder.class);
        this.ab = new CompatDecoderFactory(SkiaImageRegionDecoder.class);
        this.aw = new float[8];
        this.ax = new float[8];
        this.ay = getResources().getDisplayMetrics().density;
        setMinimumDpi(BigCoverHelper.REQCODE_CAREMA);
        setDoubleTapZoomDpi(BigCoverHelper.REQCODE_CAREMA);
        setGestureDetector(context);
        this.ap = new Handler(new a(this));
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.SubsamplingScaleImageView);
            if (obtainStyledAttributes.hasValue(1)) {
                String string = obtainStyledAttributes.getString(1);
                if (string != null && string.length() > 0) {
                    setImage(ImageSource.asset(string).tilingEnabled());
                }
            }
            if (obtainStyledAttributes.hasValue(0)) {
                int resourceId = obtainStyledAttributes.getResourceId(0, 0);
                if (resourceId > 0) {
                    setImage(ImageSource.resource(resourceId).tilingEnabled());
                }
            }
            if (obtainStyledAttributes.hasValue(2)) {
                setPanEnabled(obtainStyledAttributes.getBoolean(2, true));
            }
            if (obtainStyledAttributes.hasValue(3)) {
                setZoomEnabled(obtainStyledAttributes.getBoolean(3, true));
            }
            if (obtainStyledAttributes.hasValue(4)) {
                setQuickScaleEnabled(obtainStyledAttributes.getBoolean(4, true));
            }
            if (obtainStyledAttributes.hasValue(5)) {
                setTileBackgroundColor(obtainStyledAttributes.getColor(5, Color.argb(0, 0, 0, 0)));
            }
            obtainStyledAttributes.recycle();
        }
        this.h = TypedValue.applyDimension(1, 20.0f, context.getResources().getDisplayMetrics());
    }

    public SubsamplingScaleImageView(Context context) {
        this(context, null);
    }

    public final void setImage(ImageSource imageSource) {
        setImage(imageSource, null, null);
    }

    public final void setImage(ImageSource imageSource, ImageViewState imageViewState) {
        setImage(imageSource, null, imageViewState);
    }

    public final void setImage(ImageSource imageSource, ImageSource imageSource2) {
        setImage(imageSource, imageSource2, null);
    }

    public final void setImage(ImageSource imageSource, ImageSource imageSource2, ImageViewState imageViewState) {
        if (imageSource == null) {
            throw new NullPointerException("imageSource must not be null");
        }
        a(true);
        if (imageViewState != null) {
            a(imageViewState);
        }
        if (imageSource2 != null) {
            if (imageSource.b() != null) {
                throw new IllegalArgumentException("Preview image cannot be used when a bitmap is provided for the main image");
            } else if (imageSource.e() <= 0 || imageSource.f() <= 0) {
                throw new IllegalArgumentException("Preview image cannot be used unless dimensions are provided for the main image");
            } else {
                this.L = imageSource.e();
                this.M = imageSource.f();
                this.Q = imageSource2.g();
                if (imageSource2.b() != null) {
                    this.k = imageSource2.h();
                    a(imageSource2.b());
                } else {
                    Uri a = imageSource2.a();
                    if (a == null && imageSource2.c() != null) {
                        a = Uri.parse("android.resource://" + getContext().getPackageName() + MqttTopic.TOPIC_LEVEL_SEPARATOR + imageSource2.c());
                    }
                    a(new b(this, getContext(), this.aa, a, true));
                }
            }
        }
        if (imageSource.b() != null && imageSource.g() != null) {
            a(Bitmap.createBitmap(imageSource.b(), imageSource.g().left, imageSource.g().top, imageSource.g().width(), imageSource.g().height()), 0, false);
        } else if (imageSource.b() != null) {
            a(imageSource.b(), 0, imageSource.h());
        } else {
            this.P = imageSource.g();
            this.l = imageSource.a();
            if (this.l == null && imageSource.c() != null) {
                this.l = Uri.parse("android.resource://" + getContext().getPackageName() + MqttTopic.TOPIC_LEVEL_SEPARATOR + imageSource.c());
            }
            if (imageSource.d() || this.P != null) {
                a(new f(this, getContext(), this.ab, this.l));
            } else {
                a(new b(this, getContext(), this.aa, this.l, false));
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
        this.R = false;
        this.S = false;
        this.T = false;
        this.U = 0;
        this.m = 0;
        this.ac = null;
        this.ad = 0.0f;
        this.ae = 0.0f;
        this.af = false;
        this.ah = null;
        this.ag = null;
        this.ai = null;
        this.aj = null;
        this.at = null;
        this.au = null;
        this.av = null;
        if (z) {
            this.l = null;
            if (this.W != null) {
                synchronized (this.g) {
                    this.W.recycle();
                    this.W = null;
                }
            }
            if (!(this.i == null || this.k)) {
                this.i.recycle();
            }
            if (!(this.i == null || !this.k || this.am == null)) {
                this.am.onPreviewReleased();
            }
            this.L = 0;
            this.M = 0;
            this.N = 0;
            this.P = null;
            this.Q = null;
            this.ak = false;
            this.al = false;
            this.i = null;
            this.j = false;
            this.k = false;
        }
        if (this.n != null) {
            for (Entry value : this.n.entrySet()) {
                for (d dVar : (List) value.getValue()) {
                    dVar.e = false;
                    if (dVar.c != null) {
                        dVar.c.recycle();
                        dVar.c = null;
                    }
                }
            }
            this.n = null;
        }
        setGestureDetector(getContext());
    }

    private void setGestureDetector(Context context) {
        this.V = new GestureDetector(context, new b(this, context));
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        a("onSizeChanged %dx%d -> %dx%d", Integer.valueOf(i3), Integer.valueOf(i4), Integer.valueOf(i), Integer.valueOf(i2));
        PointF center = getCenter();
        if (this.ak && center != null) {
            this.aj = null;
            this.I = Float.valueOf(this.D);
            this.J = center;
        }
    }

    protected void onMeasure(int i, int i2) {
        int l;
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
                    l = (int) ((((double) l()) / ((double) k())) * ((double) size));
                    i3 = size;
                } else if (obj2 != null) {
                    i3 = (int) ((((double) k()) / ((double) l())) * ((double) size2));
                    l = size2;
                }
                setMeasuredDimension(Math.max(i3, getSuggestedMinimumWidth()), Math.max(l, getSuggestedMinimumHeight()));
            }
            i3 = k();
            l = l();
            setMeasuredDimension(Math.max(i3, getSuggestedMinimumWidth()), Math.max(l, getSuggestedMinimumHeight()));
        }
        l = size2;
        i3 = size;
        setMeasuredDimension(Math.max(i3, getSuggestedMinimumWidth()), Math.max(l, getSuggestedMinimumHeight()));
    }

    public boolean onTouchEvent(@NonNull MotionEvent motionEvent) {
        boolean z = false;
        if (this.aj == null || this.aj.i) {
            if (!(this.aj == null || this.aj.m == null)) {
                try {
                    this.aj.m.onInterruptedByUser();
                } catch (Throwable e) {
                    Log.w(a, "Error thrown by animation listener", e);
                }
            }
            this.aj = null;
            if (this.F == null) {
                return true;
            }
            if (this.T || !(this.V == null || this.V.onTouchEvent(motionEvent))) {
                if (this.G == null) {
                    this.G = new PointF(0.0f, 0.0f);
                }
                if (this.H == null) {
                    this.H = new PointF(0.0f, 0.0f);
                }
                if (this.ac == null) {
                    this.ac = new PointF(0.0f, 0.0f);
                }
                float f = this.D;
                this.H.set(this.F);
                boolean a = a(motionEvent);
                a(f, this.H, 2);
                if (a || super.onTouchEvent(motionEvent)) {
                    z = true;
                }
                return z;
            }
            this.R = false;
            this.S = false;
            this.U = 0;
            return true;
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
                this.aj = null;
                b(true);
                this.U = Math.max(this.U, pointerCount);
                if (pointerCount >= 2) {
                    if (this.y) {
                        a = a(motionEvent.getX(0), motionEvent.getX(1), motionEvent.getY(0), motionEvent.getY(1));
                        this.E = this.D;
                        this.ad = a;
                        this.G.set(this.F.x, this.F.y);
                        this.ac.set((motionEvent.getX(0) + motionEvent.getX(1)) / 2.0f, (motionEvent.getY(0) + motionEvent.getY(1)) / 2.0f);
                    } else {
                        this.U = 0;
                    }
                    this.ap.removeMessages(1);
                    return true;
                } else if (this.T) {
                    return true;
                } else {
                    this.G.set(this.F.x, this.F.y);
                    this.ac.set(motionEvent.getX(), motionEvent.getY());
                    this.ap.sendEmptyMessageDelayed(1, 600);
                    return true;
                }
            case 1:
            case 6:
            case 262:
                this.ap.removeMessages(1);
                if (this.T) {
                    this.T = false;
                    if (!this.af) {
                        a(this.ah, this.ac);
                    }
                }
                if (this.U > 0 && (this.R || this.S)) {
                    if (this.R && pointerCount == 2) {
                        this.S = true;
                        this.G.set(this.F.x, this.F.y);
                        if (motionEvent.getActionIndex() == 1) {
                            this.ac.set(motionEvent.getX(0), motionEvent.getY(0));
                        } else {
                            this.ac.set(motionEvent.getX(1), motionEvent.getY(1));
                        }
                    }
                    if (pointerCount < 3) {
                        this.R = false;
                    }
                    if (pointerCount < 2) {
                        this.S = false;
                        this.U = 0;
                    }
                    c(true);
                    return true;
                } else if (pointerCount != 1) {
                    return true;
                } else {
                    this.R = false;
                    this.S = false;
                    this.U = 0;
                    return true;
                }
            case 2:
                boolean z;
                if (this.U > 0) {
                    float x;
                    float y;
                    double d;
                    if (pointerCount >= 2) {
                        a = a(motionEvent.getX(0), motionEvent.getX(1), motionEvent.getY(0), motionEvent.getY(1));
                        x = (motionEvent.getX(0) + motionEvent.getX(1)) / 2.0f;
                        y = (motionEvent.getY(0) + motionEvent.getY(1)) / 2.0f;
                        if (this.y && (a(this.ac.x, x, this.ac.y, y) > 5.0f || Math.abs(a - this.ad) > 5.0f || this.S)) {
                            this.R = true;
                            this.S = true;
                            d = (double) this.D;
                            this.D = Math.min(this.q, (a / this.ad) * this.E);
                            if (this.D <= m()) {
                                this.ad = a;
                                this.E = m();
                                this.ac.set(x, y);
                                this.G.set(this.F);
                            } else if (this.x) {
                                float f = (this.ac.y - this.G.y) * (this.D / this.E);
                                this.F.x = x - ((this.ac.x - this.G.x) * (this.D / this.E));
                                this.F.y = y - f;
                                if ((((double) l()) * d < ((double) getHeight()) && this.D * ((float) l()) >= ((float) getHeight())) || (d * ((double) k()) < ((double) getWidth()) && this.D * ((float) k()) >= ((float) getWidth()))) {
                                    d(true);
                                    this.ac.set(x, y);
                                    this.G.set(this.F);
                                    this.E = this.D;
                                    this.ad = a;
                                }
                            } else if (this.K != null) {
                                this.F.x = ((float) (getWidth() / 2)) - (this.D * this.K.x);
                                this.F.y = ((float) (getHeight() / 2)) - (this.D * this.K.y);
                            } else {
                                this.F.x = ((float) (getWidth() / 2)) - (this.D * ((float) (k() / 2)));
                                this.F.y = ((float) (getHeight() / 2)) - (this.D * ((float) (l() / 2)));
                            }
                            d(true);
                            c(false);
                        }
                    } else if (this.T) {
                        y = this.h + (Math.abs(this.ai.y - motionEvent.getY()) * 2.0f);
                        if (this.ae == -1.0f) {
                            this.ae = y;
                        }
                        z = motionEvent.getY() > this.ag.y;
                        this.ag.set(0.0f, motionEvent.getY());
                        r5 = Math.abs(1.0f - (y / this.ae)) * 0.5f;
                        if (r5 > 0.03f || this.af) {
                            this.af = true;
                            a = this.ae > 0.0f ? z ? 1.0f + r5 : 1.0f - r5 : 1.0f;
                            d = (double) this.D;
                            this.D = Math.max(m(), Math.min(this.q, a * this.D));
                            if (this.x) {
                                x = (this.ac.y - this.G.y) * (this.D / this.E);
                                this.F.x = this.ac.x - ((this.ac.x - this.G.x) * (this.D / this.E));
                                this.F.y = this.ac.y - x;
                                if ((((double) l()) * d >= ((double) getHeight()) || this.D * ((float) l()) < ((float) getHeight())) && (d * ((double) k()) >= ((double) getWidth()) || this.D * ((float) k()) < ((float) getWidth()))) {
                                    a = y;
                                } else {
                                    d(true);
                                    this.ac.set(sourceToViewCoord(this.ah));
                                    this.G.set(this.F);
                                    this.E = this.D;
                                    a = 0.0f;
                                }
                                y = a;
                            } else if (this.K != null) {
                                this.F.x = ((float) (getWidth() / 2)) - (this.D * this.K.x);
                                this.F.y = ((float) (getHeight() / 2)) - (this.D * this.K.y);
                            } else {
                                this.F.x = ((float) (getWidth() / 2)) - (this.D * ((float) (k() / 2)));
                                this.F.y = ((float) (getHeight() / 2)) - (this.D * ((float) (l() / 2)));
                            }
                        }
                        this.ae = y;
                        d(true);
                        c(false);
                        z = true;
                        if (z) {
                            this.ap.removeMessages(1);
                            invalidate();
                            return true;
                        }
                    } else if (!this.R) {
                        r5 = Math.abs(motionEvent.getX() - this.ac.x);
                        x = Math.abs(motionEvent.getY() - this.ac.y);
                        float f2 = this.ay * 5.0f;
                        if (r5 > f2 || x > f2 || this.S) {
                            this.F.x = this.G.x + (motionEvent.getX() - this.ac.x);
                            this.F.y = this.G.y + (motionEvent.getY() - this.ac.y);
                            a = this.F.x;
                            float f3 = this.F.y;
                            d(true);
                            boolean z2 = a != this.F.x;
                            if (!z2 || r5 <= x || this.S) {
                                z = false;
                            } else {
                                z = true;
                            }
                            boolean z3;
                            if (f3 != this.F.y || x <= 3.0f * f2) {
                                z3 = false;
                            } else {
                                z3 = true;
                            }
                            if (!z && (!z2 || r3 || this.S)) {
                                this.S = true;
                            } else if (r5 > f2) {
                                this.U = 0;
                                this.ap.removeMessages(1);
                                b(false);
                            }
                            if (!this.x) {
                                this.F.x = this.G.x;
                                this.F.y = this.G.y;
                                b(false);
                            }
                            c(false);
                        }
                    }
                    z = true;
                    if (z) {
                        this.ap.removeMessages(1);
                        invalidate();
                        return true;
                    }
                }
                z = false;
                if (z) {
                    this.ap.removeMessages(1);
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
                pointF.x = (float) (k() / 2);
                pointF.y = (float) (l() / 2);
            }
        }
        float min = Math.min(this.q, this.A);
        Object obj = ((double) this.D) <= ((double) min) * 0.9d ? 1 : null;
        if (obj == null) {
            min = m();
        }
        if (this.B == 3) {
            setScaleAndCenter(min, pointF);
        } else if (this.B == 2 || obj == null || !this.x) {
            new AnimationBuilder(min, pointF).withInterruptible(false).withDuration((long) this.C).a(4).start();
        } else if (this.B == 1) {
            new AnimationBuilder(min, pointF, pointF2).withInterruptible(false).withDuration((long) this.C).a(4).start();
        }
        invalidate();
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        h();
        if (this.L != 0 && this.M != 0 && getWidth() != 0 && getHeight() != 0) {
            if (this.n == null && this.W != null) {
                a(a(canvas));
            }
            if (f()) {
                float a;
                PointF pointF;
                i();
                if (this.aj != null) {
                    boolean z;
                    float f = this.D;
                    if (this.H == null) {
                        this.H = new PointF(0.0f, 0.0f);
                    }
                    this.H.set(this.F);
                    long currentTimeMillis = System.currentTimeMillis() - this.aj.l;
                    if (currentTimeMillis > this.aj.h) {
                        z = true;
                    } else {
                        z = false;
                    }
                    currentTimeMillis = Math.min(currentTimeMillis, this.aj.h);
                    this.D = a(this.aj.j, currentTimeMillis, this.aj.a, this.aj.b - this.aj.a, this.aj.h);
                    float a2 = a(this.aj.j, currentTimeMillis, this.aj.f.x, this.aj.g.x - this.aj.f.x, this.aj.h);
                    a = a(this.aj.j, currentTimeMillis, this.aj.f.y, this.aj.g.y - this.aj.f.y, this.aj.h);
                    pointF = this.F;
                    pointF.x -= d(this.aj.d.x) - a2;
                    pointF = this.F;
                    pointF.y -= e(this.aj.d.y) - a;
                    boolean z2 = z || this.aj.a == this.aj.b;
                    d(z2);
                    a(f, this.H, this.aj.k);
                    c(z);
                    if (z) {
                        if (this.aj.m != null) {
                            try {
                                this.aj.m.onComplete();
                            } catch (Throwable e) {
                                Log.w(a, "Error thrown by animation listener", e);
                            }
                        }
                        this.aj = null;
                    }
                    invalidate();
                }
                if (this.n != null && e()) {
                    int min = Math.min(this.m, a(this.D));
                    Object obj = null;
                    for (Entry entry : this.n.entrySet()) {
                        if (((Integer) entry.getKey()).intValue() == min) {
                            for (d dVar : (List) entry.getValue()) {
                                if (dVar.e && (dVar.d || dVar.c == null)) {
                                    obj = 1;
                                }
                            }
                        }
                        obj = obj;
                    }
                    for (Entry entry2 : this.n.entrySet()) {
                        if (((Integer) entry2.getKey()).intValue() == min || r13 != null) {
                            for (d dVar2 : (List) entry2.getValue()) {
                                b(dVar2.a, dVar2.f);
                                if (!dVar2.d && dVar2.c != null) {
                                    if (this.as != null) {
                                        canvas.drawRect(dVar2.f, this.as);
                                    }
                                    if (this.au == null) {
                                        this.au = new Matrix();
                                    }
                                    this.au.reset();
                                    a(this.aw, 0.0f, 0.0f, (float) dVar2.c.getWidth(), 0.0f, (float) dVar2.c.getWidth(), (float) dVar2.c.getHeight(), 0.0f, (float) dVar2.c.getHeight());
                                    if (getRequiredRotation() == 0) {
                                        a(this.ax, (float) dVar2.f.left, (float) dVar2.f.top, (float) dVar2.f.right, (float) dVar2.f.top, (float) dVar2.f.right, (float) dVar2.f.bottom, (float) dVar2.f.left, (float) dVar2.f.bottom);
                                    } else if (getRequiredRotation() == 90) {
                                        a(this.ax, (float) dVar2.f.right, (float) dVar2.f.top, (float) dVar2.f.right, (float) dVar2.f.bottom, (float) dVar2.f.left, (float) dVar2.f.bottom, (float) dVar2.f.left, (float) dVar2.f.top);
                                    } else if (getRequiredRotation() == 180) {
                                        a(this.ax, (float) dVar2.f.right, (float) dVar2.f.bottom, (float) dVar2.f.left, (float) dVar2.f.bottom, (float) dVar2.f.left, (float) dVar2.f.top, (float) dVar2.f.right, (float) dVar2.f.top);
                                    } else if (getRequiredRotation() == 270) {
                                        a(this.ax, (float) dVar2.f.left, (float) dVar2.f.bottom, (float) dVar2.f.left, (float) dVar2.f.top, (float) dVar2.f.right, (float) dVar2.f.top, (float) dVar2.f.right, (float) dVar2.f.bottom);
                                    }
                                    this.au.setPolyToPoly(this.aw, 0, this.ax, 0, 4);
                                    canvas.drawBitmap(dVar2.c, this.au, this.aq);
                                    if (this.o) {
                                        canvas.drawRect(dVar2.f, this.ar);
                                    }
                                } else if (dVar2.d && this.o) {
                                    canvas.drawText("LOADING", (float) (dVar2.f.left + 5), (float) (dVar2.f.top + 35), this.ar);
                                }
                                if (dVar2.e && this.o) {
                                    canvas.drawText("ISS " + dVar2.b + " RECT " + dVar2.a.top + Constants.ACCEPT_TIME_SEPARATOR_SP + dVar2.a.left + Constants.ACCEPT_TIME_SEPARATOR_SP + dVar2.a.bottom + Constants.ACCEPT_TIME_SEPARATOR_SP + dVar2.a.right, (float) (dVar2.f.left + 5), (float) (dVar2.f.top + 15), this.ar);
                                }
                            }
                        }
                    }
                } else if (this.i != null) {
                    float f2 = this.D;
                    a = this.D;
                    if (this.j) {
                        f2 = (((float) this.L) / ((float) this.i.getWidth())) * this.D;
                        a = this.D * (((float) this.M) / ((float) this.i.getHeight()));
                    }
                    if (this.au == null) {
                        this.au = new Matrix();
                    }
                    this.au.reset();
                    this.au.postScale(f2, a);
                    this.au.postRotate((float) getRequiredRotation());
                    this.au.postTranslate(this.F.x, this.F.y);
                    if (getRequiredRotation() == 180) {
                        this.au.postTranslate(this.D * ((float) this.L), this.D * ((float) this.M));
                    } else if (getRequiredRotation() == 90) {
                        this.au.postTranslate(this.D * ((float) this.M), 0.0f);
                    } else if (getRequiredRotation() == 270) {
                        this.au.postTranslate(0.0f, this.D * ((float) this.L));
                    }
                    if (this.as != null) {
                        if (this.av == null) {
                            this.av = new RectF();
                        }
                        this.av.set(0.0f, 0.0f, this.j ? (float) this.i.getWidth() : (float) this.L, this.j ? (float) this.i.getHeight() : (float) this.M);
                        this.au.mapRect(this.av);
                        canvas.drawRect(this.av, this.as);
                    }
                    canvas.drawBitmap(this.i, this.au, this.aq);
                }
                if (this.o) {
                    canvas.drawText("Scale: " + String.format(Locale.ENGLISH, "%.2f", new Object[]{Float.valueOf(this.D)}), 5.0f, 15.0f, this.ar);
                    canvas.drawText("Translate: " + String.format(Locale.ENGLISH, "%.2f", new Object[]{Float.valueOf(this.F.x)}) + Config.TRACE_TODAY_VISIT_SPLIT + String.format(Locale.ENGLISH, "%.2f", new Object[]{Float.valueOf(this.F.y)}), 5.0f, 35.0f, this.ar);
                    PointF center = getCenter();
                    canvas.drawText("Source center: " + String.format(Locale.ENGLISH, "%.2f", new Object[]{Float.valueOf(center.x)}) + Config.TRACE_TODAY_VISIT_SPLIT + String.format(Locale.ENGLISH, "%.2f", new Object[]{Float.valueOf(center.y)}), 5.0f, 55.0f, this.ar);
                    this.ar.setStrokeWidth(2.0f);
                    if (this.aj != null) {
                        center = sourceToViewCoord(this.aj.c);
                        pointF = sourceToViewCoord(this.aj.e);
                        PointF sourceToViewCoord = sourceToViewCoord(this.aj.d);
                        canvas.drawCircle(center.x, center.y, 10.0f, this.ar);
                        this.ar.setColor(-65536);
                        canvas.drawCircle(pointF.x, pointF.y, 20.0f, this.ar);
                        this.ar.setColor(-16776961);
                        canvas.drawCircle(sourceToViewCoord.x, sourceToViewCoord.y, 25.0f, this.ar);
                        this.ar.setColor(-16711681);
                        canvas.drawCircle((float) (getWidth() / 2), (float) (getHeight() / 2), 30.0f, this.ar);
                    }
                    if (this.ac != null) {
                        this.ar.setColor(-65536);
                        canvas.drawCircle(this.ac.x, this.ac.y, 20.0f, this.ar);
                    }
                    if (this.ah != null) {
                        this.ar.setColor(-16776961);
                        canvas.drawCircle(d(this.ah.x), e(this.ah.y), 35.0f, this.ar);
                    }
                    if (this.ai != null) {
                        this.ar.setColor(-16711681);
                        canvas.drawCircle(this.ai.x, this.ai.y, 30.0f, this.ar);
                    }
                    this.ar.setColor(-65281);
                    this.ar.setStrokeWidth(1.0f);
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

    private boolean e() {
        if (this.i != null && !this.j) {
            return true;
        }
        if (this.n == null) {
            return false;
        }
        boolean z = true;
        for (Entry entry : this.n.entrySet()) {
            if (((Integer) entry.getKey()).intValue() == this.m) {
                for (d dVar : (List) entry.getValue()) {
                    if (dVar.d || dVar.c == null) {
                        z = false;
                    }
                }
            }
            z = z;
        }
        return z;
    }

    private boolean f() {
        boolean z = getWidth() > 0 && getHeight() > 0 && this.L > 0 && this.M > 0 && (this.i != null || e());
        if (!this.ak && z) {
            i();
            this.ak = true;
            a();
            if (this.am != null) {
                this.am.onReady();
            }
        }
        return z;
    }

    private boolean g() {
        boolean e = e();
        if (!this.al && e) {
            i();
            this.al = true;
            b();
            if (this.am != null) {
                this.am.onImageLoaded();
            }
        }
        return e;
    }

    private void h() {
        if (this.aq == null) {
            this.aq = new Paint();
            this.aq.setAntiAlias(true);
            this.aq.setFilterBitmap(true);
            this.aq.setDither(true);
        }
        if (this.ar == null && this.o) {
            this.ar = new Paint();
            this.ar.setTextSize(18.0f);
            this.ar.setColor(-65281);
            this.ar.setStyle(Style.STROKE);
        }
    }

    private synchronized void a(Point point) {
        a("initialiseBaseLayer maxTileDimensions=%dx%d", Integer.valueOf(point.x), Integer.valueOf(point.y));
        this.at = new c(0.0f, new PointF(0.0f, 0.0f));
        a(true, this.at);
        this.m = a(this.at.a);
        if (this.m > 1) {
            this.m /= 2;
        }
        if (this.m != 1 || this.P != null || k() >= point.x || l() >= point.y) {
            b(point);
            for (d eVar : (List) this.n.get(Integer.valueOf(this.m))) {
                a(new e(this, this.W, eVar));
            }
            c(true);
        } else {
            this.W.recycle();
            this.W = null;
            a(new b(this, getContext(), this.aa, this.l, false));
        }
    }

    private void c(boolean z) {
        if (this.W != null && this.n != null) {
            int min = Math.min(this.m, a(this.D));
            for (Entry value : this.n.entrySet()) {
                for (d dVar : (List) value.getValue()) {
                    if (dVar.b < min || (dVar.b > min && dVar.b != this.m)) {
                        dVar.e = false;
                        if (dVar.c != null) {
                            dVar.c.recycle();
                            dVar.c = null;
                        }
                    }
                    if (dVar.b == min) {
                        if (a(dVar)) {
                            dVar.e = true;
                            if (!dVar.d && dVar.c == null && z) {
                                a(new e(this, this.W, dVar));
                            }
                        } else if (dVar.b != this.m) {
                            dVar.e = false;
                            if (dVar.c != null) {
                                dVar.c.recycle();
                                dVar.c = null;
                            }
                        }
                    } else if (dVar.b == this.m) {
                        dVar.e = true;
                    }
                }
            }
        }
    }

    private boolean a(d dVar) {
        return b(0.0f) <= ((float) dVar.a.right) && ((float) dVar.a.left) <= b((float) getWidth()) && c(0.0f) <= ((float) dVar.a.bottom) && ((float) dVar.a.top) <= c((float) getHeight());
    }

    private void i() {
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
        if (this.r > 0) {
            DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
            f *= ((float) this.r) / ((displayMetrics.ydpi + displayMetrics.xdpi) / 2.0f);
        }
        int k = (int) (((float) k()) * f);
        int l = (int) (((float) l()) * f);
        if (k == 0 || l == 0) {
            return 32;
        }
        if (l() > l || k() > k) {
            l = Math.round(((float) l()) / ((float) l));
            k = Math.round(((float) k()) / ((float) k));
            if (l >= k) {
                l = k;
            }
        } else {
            l = 1;
        }
        k = 1;
        while (k * 2 < l) {
            k *= 2;
        }
        return k;
    }

    private void a(boolean z, c cVar) {
        float paddingLeft;
        float max;
        float f = 0.5f;
        if (this.s == 2 && isReady()) {
            z = false;
        }
        PointF b = cVar.b;
        float f2 = f(cVar.a);
        float k = f2 * ((float) k());
        float l = f2 * ((float) l());
        if (this.s == 3 && isReady()) {
            b.x = Math.max(b.x, ((float) (getWidth() / 2)) - k);
            b.y = Math.max(b.y, ((float) (getHeight() / 2)) - l);
        } else if (z) {
            b.x = Math.max(b.x, ((float) getWidth()) - k);
            b.y = Math.max(b.y, ((float) getHeight()) - l);
        } else {
            b.x = Math.max(b.x, -k);
            b.y = Math.max(b.y, -l);
        }
        if (getPaddingLeft() > 0 || getPaddingRight() > 0) {
            paddingLeft = ((float) getPaddingLeft()) / ((float) (getPaddingLeft() + getPaddingRight()));
        } else {
            paddingLeft = 0.5f;
        }
        if (getPaddingTop() > 0 || getPaddingBottom() > 0) {
            f = ((float) getPaddingTop()) / ((float) (getPaddingTop() + getPaddingBottom()));
        }
        if (this.s == 3 && isReady()) {
            paddingLeft = (float) Math.max(0, getWidth() / 2);
            max = (float) Math.max(0, getHeight() / 2);
        } else if (z) {
            paddingLeft = Math.max(0.0f, (((float) getWidth()) - k) * paddingLeft);
            max = Math.max(0.0f, (((float) getHeight()) - l) * f);
        } else {
            paddingLeft = (float) Math.max(0, getWidth());
            max = (float) Math.max(0, getHeight());
        }
        b.x = Math.min(b.x, paddingLeft);
        b.y = Math.min(b.y, max);
        cVar.a = f2;
    }

    private void d(boolean z) {
        Object obj = null;
        if (this.F == null) {
            obj = 1;
            this.F = new PointF(0.0f, 0.0f);
        }
        if (this.at == null) {
            this.at = new c(0.0f, new PointF(0.0f, 0.0f));
        }
        this.at.a = this.D;
        this.at.b.set(this.F);
        a(z, this.at);
        this.D = this.at.a;
        this.F.set(this.at.b);
        if (obj != null && this.t != 4) {
            this.F.set(a((float) (k() / 2), (float) (l() / 2), this.D));
        }
    }

    private void b(Point point) {
        a("initialiseTileMap maxTileDimensions=%dx%d", Integer.valueOf(point.x), Integer.valueOf(point.y));
        this.n = new LinkedHashMap();
        int i = this.m;
        int i2 = 1;
        int i3 = 1;
        while (true) {
            int k = k() / i2;
            int l = l() / i3;
            int i4 = k / i;
            int i5 = l / i;
            while (true) {
                if ((i4 + i2) + 1 > point.x || (((double) i4) > ((double) getWidth()) * 1.25d && i < this.m)) {
                    k = i2 + 1;
                    i4 = k() / k;
                    i2 = k;
                    k = i4;
                    i4 /= i;
                }
            }
            i4 = i5;
            i5 = l;
            while (true) {
                if ((i4 + i3) + 1 > point.y || (((double) i4) > ((double) getHeight()) * 1.25d && i < this.m)) {
                    i5 = i3 + 1;
                    i4 = l() / i5;
                    i3 = i5;
                    i5 = i4;
                    i4 /= i;
                }
            }
            List arrayList = new ArrayList(i2 * i3);
            int i6 = 0;
            while (i6 < i2) {
                for (int i7 = 0; i7 < i3; i7++) {
                    d dVar = new d();
                    dVar.b = i;
                    dVar.e = i == this.m;
                    int i8 = i6 * k;
                    int i9 = i7 * i5;
                    l = i6 == i2 + -1 ? k() : (i6 + 1) * k;
                    if (i7 == i3 - 1) {
                        i4 = l();
                    } else {
                        i4 = (i7 + 1) * i5;
                    }
                    dVar.a = new Rect(i8, i9, l, i4);
                    dVar.f = new Rect(0, 0, 0, 0);
                    dVar.g = new Rect(dVar.a);
                    arrayList.add(dVar);
                }
                i6++;
            }
            this.n.put(Integer.valueOf(i), arrayList);
            if (i != 1) {
                i /= 2;
            } else {
                return;
            }
        }
    }

    private synchronized void a(ImageRegionDecoder imageRegionDecoder, int i, int i2, int i3) {
        a("onTilesInited sWidth=%d, sHeight=%d, sOrientation=%d", Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(this.p));
        if (this.L > 0 && this.M > 0 && !(this.L == i && this.M == i2)) {
            a(false);
            if (this.i != null) {
                if (!this.k) {
                    this.i.recycle();
                }
                this.i = null;
                if (this.am != null && this.k) {
                    this.am.onPreviewReleased();
                }
                this.j = false;
                this.k = false;
            }
        }
        this.W = imageRegionDecoder;
        this.L = i;
        this.M = i2;
        this.N = i3;
        f();
        if (!g() && this.u > 0 && this.u != TILE_SIZE_AUTO && this.v > 0 && this.v != TILE_SIZE_AUTO && getWidth() > 0 && getHeight() > 0) {
            a(new Point(this.u, this.v));
        }
        invalidate();
        requestLayout();
    }

    private synchronized void j() {
        a("onTileLoaded", new Object[0]);
        f();
        g();
        if (e() && this.i != null) {
            if (!this.k) {
                this.i.recycle();
            }
            this.i = null;
            if (this.am != null && this.k) {
                this.am.onPreviewReleased();
            }
            this.j = false;
            this.k = false;
        }
        invalidate();
    }

    private synchronized void a(Bitmap bitmap) {
        a("onPreviewLoaded", new Object[0]);
        if (this.i != null || this.al) {
            bitmap.recycle();
        } else {
            if (this.Q != null) {
                this.i = Bitmap.createBitmap(bitmap, this.Q.left, this.Q.top, this.Q.width(), this.Q.height());
            } else {
                this.i = bitmap;
            }
            this.j = true;
            if (f()) {
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
        if (!(this.i == null || this.k)) {
            this.i.recycle();
        }
        if (!(this.i == null || !this.k || this.am == null)) {
            this.am.onPreviewReleased();
        }
        this.j = false;
        this.k = z;
        this.i = bitmap;
        this.L = bitmap.getWidth();
        this.M = bitmap.getHeight();
        this.N = i;
        boolean f = f();
        boolean g = g();
        if (f || g) {
            invalidate();
            requestLayout();
        }
    }

    @AnyThread
    private int a(Context context, String str) {
        Cursor query;
        int i;
        Cursor cursor;
        Throwable th;
        if (str.startsWith("content")) {
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
                Log.w(a, "Unsupported EXIF orientation: " + i);
                return 0;
            } catch (Exception e3) {
                Log.w(a, "Could not get EXIF orientation of image");
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
                Log.i(a, "Failed to execute AsyncTask on thread pool executor, falling back to single threaded executor", e);
            }
        }
        asyncTask.execute(new Void[0]);
    }

    private void a(ImageViewState imageViewState) {
        if (imageViewState != null && imageViewState.getCenter() != null && b.contains(Integer.valueOf(imageViewState.getOrientation()))) {
            this.p = imageViewState.getOrientation();
            this.I = Float.valueOf(imageViewState.getScale());
            this.J = imageViewState.getCenter();
            invalidate();
        }
    }

    public void setMaxTileSize(int i) {
        this.u = i;
        this.v = i;
    }

    public void setMaxTileSize(int i, int i2) {
        this.u = i;
        this.v = i2;
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

    private int k() {
        int requiredRotation = getRequiredRotation();
        if (requiredRotation == 90 || requiredRotation == 270) {
            return this.M;
        }
        return this.L;
    }

    private int l() {
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
        if (this.p == -1) {
            return this.N;
        }
        return this.p;
    }

    private float a(float f, float f2, float f3, float f4) {
        float f5 = f - f2;
        float f6 = f3 - f4;
        return (float) Math.sqrt((double) ((f5 * f5) + (f6 * f6)));
    }

    public void recycle() {
        a(true);
        this.aq = null;
        this.ar = null;
        this.as = null;
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

    public final PointF viewToSourceCoord(PointF pointF) {
        return viewToSourceCoord(pointF.x, pointF.y, new PointF());
    }

    public final PointF viewToSourceCoord(float f, float f2) {
        return viewToSourceCoord(f, f2, new PointF());
    }

    public final PointF viewToSourceCoord(PointF pointF, PointF pointF2) {
        return viewToSourceCoord(pointF.x, pointF.y, pointF2);
    }

    public final PointF viewToSourceCoord(float f, float f2, PointF pointF) {
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

    public final PointF sourceToViewCoord(PointF pointF) {
        return sourceToViewCoord(pointF.x, pointF.y, new PointF());
    }

    public final PointF sourceToViewCoord(float f, float f2) {
        return sourceToViewCoord(f, f2, new PointF());
    }

    public final PointF sourceToViewCoord(PointF pointF, PointF pointF2) {
        return sourceToViewCoord(pointF.x, pointF.y, pointF2);
    }

    public final PointF sourceToViewCoord(float f, float f2, PointF pointF) {
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
        if (this.at == null) {
            this.at = new c(0.0f, new PointF(0.0f, 0.0f));
        }
        this.at.a = f3;
        this.at.b.set(((float) paddingLeft) - (f * f3), ((float) paddingTop) - (f2 * f3));
        a(true, this.at);
        return this.at.b;
    }

    private PointF a(float f, float f2, float f3, PointF pointF) {
        PointF a = a(f, f2, f3);
        pointF.set((((float) (getPaddingLeft() + (((getWidth() - getPaddingRight()) - getPaddingLeft()) / 2))) - a.x) / f3, (((float) (getPaddingTop() + (((getHeight() - getPaddingBottom()) - getPaddingTop()) / 2))) - a.y) / f3);
        return pointF;
    }

    private float m() {
        int paddingBottom = getPaddingBottom() + getPaddingTop();
        int paddingLeft = getPaddingLeft() + getPaddingRight();
        if (this.t == 2 || this.t == 4) {
            return Math.max(((float) (getWidth() - paddingLeft)) / ((float) k()), ((float) (getHeight() - paddingBottom)) / ((float) l()));
        }
        if (this.t != 3 || this.O <= 0.0f) {
            return Math.min(((float) (getWidth() - paddingLeft)) / ((float) k()), ((float) (getHeight() - paddingBottom)) / ((float) l()));
        }
        return this.O;
    }

    private float f(float f) {
        return Math.min(this.q, Math.max(m(), f));
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
        if (this.o) {
            Log.d(a, String.format(str, objArr));
        }
    }

    public final void setRegionDecoderClass(Class<? extends ImageRegionDecoder> cls) {
        if (cls == null) {
            throw new IllegalArgumentException("Decoder class cannot be set to null");
        }
        this.ab = new CompatDecoderFactory(cls);
    }

    public final void setRegionDecoderFactory(DecoderFactory<? extends ImageRegionDecoder> decoderFactory) {
        if (decoderFactory == null) {
            throw new IllegalArgumentException("Decoder factory cannot be set to null");
        }
        this.ab = decoderFactory;
    }

    public final void setBitmapDecoderClass(Class<? extends ImageDecoder> cls) {
        if (cls == null) {
            throw new IllegalArgumentException("Decoder class cannot be set to null");
        }
        this.aa = new CompatDecoderFactory(cls);
    }

    public final void setBitmapDecoderFactory(DecoderFactory<? extends ImageDecoder> decoderFactory) {
        if (decoderFactory == null) {
            throw new IllegalArgumentException("Decoder factory cannot be set to null");
        }
        this.aa = decoderFactory;
    }

    public final void setPanLimit(int i) {
        if (e.contains(Integer.valueOf(i))) {
            this.s = i;
            if (isReady()) {
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
            this.t = i;
            if (isReady()) {
                d(true);
                invalidate();
                return;
            }
            return;
        }
        throw new IllegalArgumentException("Invalid scale type: " + i);
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
        return this.q;
    }

    public final void setMaxScale(float f) {
        this.q = f;
    }

    public final float getMinScale() {
        return m();
    }

    public final void setMinScale(float f) {
        this.O = f;
    }

    public void setMinimumTileDpi(int i) {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        this.r = (int) Math.min((displayMetrics.ydpi + displayMetrics.xdpi) / 2.0f, (float) i);
        if (isReady()) {
            a(false);
            invalidate();
        }
    }

    public final PointF getCenter() {
        return viewToSourceCoord((float) (getWidth() / 2), (float) (getHeight() / 2));
    }

    public final float getScale() {
        return this.D;
    }

    public final void setScaleAndCenter(float f, PointF pointF) {
        this.aj = null;
        this.I = Float.valueOf(f);
        this.J = pointF;
        this.K = pointF;
        invalidate();
    }

    public final void resetScaleAndCenter() {
        this.aj = null;
        this.I = Float.valueOf(f(0.0f));
        if (isReady()) {
            this.J = new PointF((float) (k() / 2), (float) (l() / 2));
        } else {
            this.J = new PointF(0.0f, 0.0f);
        }
        invalidate();
    }

    public final boolean isReady() {
        return this.ak;
    }

    protected void a() {
    }

    public final boolean isImageLoaded() {
        return this.al;
    }

    protected void b() {
    }

    public final int getSWidth() {
        return this.L;
    }

    public final int getSHeight() {
        return this.M;
    }

    public final int getOrientation() {
        return this.p;
    }

    public final void setOrientation(int i) {
        if (b.contains(Integer.valueOf(i))) {
            this.p = i;
            a(false);
            invalidate();
            requestLayout();
            return;
        }
        throw new IllegalArgumentException("Invalid orientation: " + i);
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

    public final boolean isZoomEnabled() {
        return this.y;
    }

    public final void setZoomEnabled(boolean z) {
        this.y = z;
    }

    public final boolean isQuickScaleEnabled() {
        return this.z;
    }

    public final void setQuickScaleEnabled(boolean z) {
        this.z = z;
    }

    public final boolean isPanEnabled() {
        return this.x;
    }

    public final void setPanEnabled(boolean z) {
        this.x = z;
        if (!z && this.F != null) {
            this.F.x = ((float) (getWidth() / 2)) - (this.D * ((float) (k() / 2)));
            this.F.y = ((float) (getHeight() / 2)) - (this.D * ((float) (l() / 2)));
            if (isReady()) {
                c(true);
                invalidate();
            }
        }
    }

    public final void setTileBackgroundColor(int i) {
        if (Color.alpha(i) == 0) {
            this.as = null;
        } else {
            this.as = new Paint();
            this.as.setStyle(Style.FILL);
            this.as.setColor(i);
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

    public void setParallelLoadingEnabled(boolean z) {
        this.w = z;
    }

    public final void setDebug(boolean z) {
        this.o = z;
    }

    public boolean hasImage() {
        return (this.l == null && this.i == null) ? false : true;
    }

    public void setOnLongClickListener(OnLongClickListener onLongClickListener) {
        this.ao = onLongClickListener;
    }

    public void setOnImageEventListener(OnImageEventListener onImageEventListener) {
        this.am = onImageEventListener;
    }

    public void setOnStateChangedListener(OnStateChangedListener onStateChangedListener) {
        this.an = onStateChangedListener;
    }

    private void a(float f, PointF pointF, int i) {
        if (this.an != null) {
            if (this.D != f) {
                this.an.onScaleChanged(this.D, i);
            }
            if (!this.F.equals(pointF)) {
                this.an.onCenterChanged(getCenter(), i);
            }
        }
    }

    public AnimationBuilder animateCenter(PointF pointF) {
        if (isReady()) {
            return new AnimationBuilder(pointF);
        }
        return null;
    }

    public AnimationBuilder animateScale(float f) {
        if (isReady()) {
            return new AnimationBuilder(f);
        }
        return null;
    }

    public AnimationBuilder animateScaleAndCenter(float f, PointF pointF) {
        if (isReady()) {
            return new AnimationBuilder(f, pointF);
        }
        return null;
    }
}
