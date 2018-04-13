package cn.xiaochuankeji.tieba.ui.videomaker.sticker.c;

import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import com.facebook.animated.webp.WebPImage;
import com.facebook.common.internal.b;
import com.facebook.common.internal.g;
import com.facebook.common.memory.PooledByteBuffer;
import com.facebook.imagepipeline.animated.base.j;
import com.facebook.imagepipeline.g.e;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class a extends Drawable implements Animatable {
    private final Paint a = new Paint(2);
    private ColorStateList b;
    private PorterDuffColorFilter c;
    private Mode d;
    private cn.xiaochuankeji.tieba.ui.videomaker.sticker.c.a.a e;
    private Bitmap f;
    private Bitmap g;
    private volatile boolean h = true;
    private boolean i = false;
    private Rect j;
    private Rect k = new Rect();
    private int l = -1;
    private int m = -1;
    private final ScheduledThreadPoolExecutor n;
    private ScheduledFuture<?> o;
    private WebPImage p;
    private int q;
    private int[] r;
    private int s;
    private int t;
    private int u;
    private String v;
    private Handler w = new Handler(Looper.getMainLooper());
    private final Runnable x = new Runnable(this) {
        final /* synthetic */ a a;

        {
            this.a = r1;
        }

        public void run() {
            this.a.invalidateSelf();
        }
    };
    private final Runnable y = new Runnable(this) {
        final /* synthetic */ a a;

        {
            this.a = r1;
        }

        public void run() {
            if (this.a.p != null) {
                if (this.a.g == null || this.a.g.isRecycled()) {
                    this.a.g = Bitmap.createBitmap(this.a.p.a(), this.a.p.b(), Config.ARGB_8888);
                }
                long a = this.a.a(this.a.l, this.a.g, this.a.p);
                if (a >= 0) {
                    this.a.l = this.a.l + 1;
                    this.a.l = this.a.l % this.a.q;
                    if (this.a.isVisible() && this.a.h) {
                        this.a.n.remove(this);
                        this.a.o = this.a.n.schedule(this, a, TimeUnit.MILLISECONDS);
                    }
                } else {
                    this.a.l = -1;
                    this.a.h = false;
                }
                if (this.a.isVisible()) {
                    this.a.f = this.a.g.copy(Config.ARGB_8888, true);
                    this.a.w.post(this.a.x);
                }
            }
        }
    };
    private Bitmap z;

    private long a(int i, Bitmap bitmap, WebPImage webPImage) {
        j a = webPImage.a(i);
        try {
            int c = a.c();
            long d = a.d();
            int e = a.e();
            int f = a.f();
            synchronized (this) {
                if (this.z == null) {
                    this.z = Bitmap.createBitmap(webPImage.a(), webPImage.b(), Config.ARGB_8888);
                }
                this.z.eraseColor(0);
                a.a(c, d, this.z);
                Canvas canvas = new Canvas(bitmap);
                canvas.drawColor(0, Mode.SRC);
                float a2 = ((float) this.t) / ((float) webPImage.a());
                float b = ((float) this.u) / ((float) webPImage.b());
                canvas.save();
                canvas.scale(a2, b);
                canvas.translate((float) e, (float) f);
                d = this.z;
                canvas.drawBitmap(d, 0.0f, 0.0f, this.a);
                canvas.restore();
            }
            d = (long) a.b();
            return d;
        } finally {
            a.a();
        }
    }

    private a(String str) {
        this.v = str;
        this.n = b.a();
        this.p = d();
    }

    private WebPImage d() {
        if (this.p == null) {
            this.p = b(this.v);
            if (this.p != null) {
                this.t = this.p.a();
                this.u = this.p.b();
                this.q = this.p.c();
                this.r = this.p.d();
                this.s = this.p.i();
            } else {
                this.t = 0;
                this.u = 0;
                this.q = 0;
                this.r = new int[0];
                this.s = 0;
            }
            this.j = new Rect(0, 0, this.t, this.u);
            setBounds(0, 0, this.t, this.u);
        }
        return this.p;
    }

    public void draw(@NonNull Canvas canvas) {
        a(canvas);
        if (this.h && this.l == -1) {
            this.l = 0;
            f();
            this.n.remove(this.y);
            this.o = this.n.schedule(this.y, 0, TimeUnit.MILLISECONDS);
        }
    }

    private void a(Canvas canvas) {
        if (this.f != null) {
            Object obj;
            canvas.save();
            if (this.c == null || this.a.getColorFilter() != null) {
                obj = null;
            } else {
                this.a.setColorFilter(this.c);
                obj = 1;
            }
            if (this.e == null) {
                canvas.drawBitmap(this.f, this.j, this.k, this.a);
            } else {
                this.e.a(canvas, this.a, this.f);
            }
            if (obj != null) {
                this.a.setColorFilter(null);
            }
            canvas.restore();
        }
    }

    private int b(long j) {
        int i = 0;
        if (this.p == null) {
            return -1;
        }
        long j2 = j % ((long) this.s);
        int i2 = 0;
        while (i < this.r.length) {
            i2 += this.r[i];
            if (((long) i2) > j2) {
                return i;
            }
            i++;
        }
        return -1;
    }

    public boolean a(long j) {
        return b(j) != this.m;
    }

    public void a(Canvas canvas, int i) {
        if (i == 0) {
            this.m = -1;
        }
        if (this.p != null) {
            if (this.g == null || this.g.isRecycled()) {
                this.g = Bitmap.createBitmap(this.p.a(), this.p.b(), Config.ARGB_8888);
            }
            int b = b((long) i);
            if (b != this.m) {
                a(b, this.g, this.p);
                this.m = b;
                this.f = this.g.copy(Config.ARGB_8888, true);
            }
            a(canvas);
        }
    }

    public void setAlpha(int i) {
        this.a.setAlpha(i);
        invalidateSelf();
    }

    public void setColorFilter(@Nullable ColorFilter colorFilter) {
        this.a.setColorFilter(colorFilter);
        invalidateSelf();
    }

    public int getOpacity() {
        return -3;
    }

    public int getIntrinsicWidth() {
        return this.t;
    }

    public int getIntrinsicHeight() {
        return this.u;
    }

    public int getMinimumWidth() {
        return this.t;
    }

    public int getMinimumHeight() {
        return this.u;
    }

    public void onBoundsChange(Rect rect) {
        this.k.set(rect);
        if (this.e != null) {
            this.e.a(rect);
        }
    }

    private PorterDuffColorFilter a(ColorStateList colorStateList, Mode mode) {
        if (colorStateList == null || mode == null) {
            return null;
        }
        return new PorterDuffColorFilter(colorStateList.getColorForState(getState(), 0), mode);
    }

    public void setTintList(ColorStateList colorStateList) {
        this.b = colorStateList;
        this.c = a(colorStateList, this.d);
        invalidateSelf();
    }

    public void setTintMode(@NonNull Mode mode) {
        this.d = mode;
        this.c = a(this.b, mode);
        invalidateSelf();
    }

    protected boolean onStateChange(int[] iArr) {
        if (this.b == null || this.d == null) {
            return false;
        }
        this.c = a(this.b, this.d);
        return true;
    }

    public boolean isStateful() {
        return super.isStateful() || (this.b != null && this.b.isStateful());
    }

    public boolean setVisible(boolean z, boolean z2) {
        boolean visible = super.setVisible(z, z2);
        if (z) {
            if (z2) {
                e();
            }
            if (visible && this.i) {
                start();
            }
        } else if (visible) {
            this.i = isRunning();
            if (this.i) {
                stop();
            }
        }
        return visible;
    }

    public void start() {
        synchronized (this) {
            if (this.h) {
                return;
            }
            this.h = true;
            this.l = -1;
            invalidateSelf();
        }
    }

    public void stop() {
        synchronized (this) {
            if (this.h) {
                this.h = false;
                f();
                return;
            }
        }
    }

    public boolean isRunning() {
        return this.h;
    }

    private void e() {
        this.l = 0;
        this.n.execute(this.y);
    }

    private void f() {
        this.w.removeCallbacks(this.x);
        if (this.o != null) {
            this.o.cancel(false);
        }
    }

    public void a() {
        b();
        if (!(this.g == null || this.g.isRecycled())) {
            this.g.recycle();
            this.g = null;
        }
        if (!(this.f == null || this.f.isRecycled())) {
            this.f.recycle();
            this.f = null;
        }
        if (this.z != null && !this.z.isRecycled()) {
            this.z.recycle();
            this.z = null;
        }
    }

    public void b() {
        this.h = false;
        f();
        this.n.remove(this.y);
        if (this.p != null) {
            this.p.h();
            this.p = null;
        }
    }

    public static a a(String str) {
        return new a(str);
    }

    private WebPImage b(String str) {
        Throwable th;
        try {
            Log.i("ExtractWebPDrawable", "source:" + str);
            InputStream fileInputStream = new FileInputStream(new File(str));
            com.facebook.common.references.a a;
            try {
                a = com.facebook.common.references.a.a(cn.xiaochuankeji.tieba.common.c.a.b().getPooledByteBufferFactory().newByteBuffer(fileInputStream));
                try {
                    com.facebook.common.references.a c = new e(a).c();
                    g.a(c);
                    PooledByteBuffer pooledByteBuffer = (PooledByteBuffer) c.a();
                    WebPImage a2 = WebPImage.a(pooledByteBuffer.getNativePtr(), pooledByteBuffer.size());
                    b.a(fileInputStream);
                    com.facebook.common.references.a.c(a);
                    return a2;
                } catch (Throwable th2) {
                    th = th2;
                    b.a(fileInputStream);
                    com.facebook.common.references.a.c(a);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                a = null;
                b.a(fileInputStream);
                com.facebook.common.references.a.c(a);
                throw th;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Rect c() {
        return this.k;
    }
}
