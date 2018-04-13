package pl.droidsonroids.gif;

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
import android.os.Build.VERSION;
import android.os.SystemClock;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.MediaController.MediaPlayerControl;
import java.io.IOException;
import java.util.Locale;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import pl.droidsonroids.gif.a.a;

public class b extends Drawable implements Animatable, MediaPlayerControl {
    final ScheduledThreadPoolExecutor a;
    volatile boolean b;
    long c;
    protected final Paint d;
    final Bitmap e;
    final GifInfoHandle f;
    final ConcurrentLinkedQueue<a> g;
    final boolean h;
    final d i;
    ScheduledFuture<?> j;
    private boolean k;
    private final Rect l;
    private ColorStateList m;
    private PorterDuffColorFilter n;
    private Mode o;
    private final g p;
    private final Rect q;
    private int r;
    private int s;
    private a t;
    private int u;
    private int v;

    public b(@NonNull String str) throws IOException {
        this(new GifInfoHandle(str), null, null, true);
    }

    b(GifInfoHandle gifInfoHandle, b bVar, ScheduledThreadPoolExecutor scheduledThreadPoolExecutor, boolean z) {
        boolean z2 = true;
        this.b = true;
        this.k = false;
        this.c = Long.MIN_VALUE;
        this.l = new Rect();
        this.d = new Paint(6);
        this.g = new ConcurrentLinkedQueue();
        this.p = new g(this);
        this.h = z;
        if (scheduledThreadPoolExecutor == null) {
            scheduledThreadPoolExecutor = c.a();
        }
        this.a = scheduledThreadPoolExecutor;
        this.f = gifInfoHandle;
        Bitmap bitmap = null;
        if (bVar != null) {
            synchronized (bVar.f) {
                if (!bVar.f.k() && bVar.f.m() >= this.f.m() && bVar.f.l() >= this.f.l()) {
                    bVar.g();
                    bitmap = bVar.e;
                    bitmap.eraseColor(0);
                }
            }
        }
        if (bitmap == null) {
            this.e = Bitmap.createBitmap(this.f.l(), this.f.m(), Config.ARGB_8888);
        } else {
            this.e = bitmap;
        }
        if (VERSION.SDK_INT >= 12) {
            bitmap = this.e;
            if (gifInfoHandle.o()) {
                z2 = false;
            }
            bitmap.setHasAlpha(z2);
        }
        this.q = new Rect(0, 0, this.f.l(), this.f.m());
        this.i = new d(this);
        this.p.a();
        this.r = this.f.l();
        this.s = this.f.m();
    }

    public void a() {
        g();
        this.e.recycle();
    }

    private void g() {
        this.b = false;
        this.i.removeMessages(-1);
        this.f.a();
    }

    public boolean b() {
        return this.f.k();
    }

    public int getIntrinsicHeight() {
        return this.s;
    }

    public int getIntrinsicWidth() {
        return this.r;
    }

    public void setAlpha(@IntRange(from = 0, to = 255) int i) {
        this.d.setAlpha(i);
    }

    public void setColorFilter(@Nullable ColorFilter colorFilter) {
        this.d.setColorFilter(colorFilter);
    }

    public int getOpacity() {
        if (!this.f.o() || this.d.getAlpha() < 255) {
            return -2;
        }
        return -1;
    }

    public void start() {
        synchronized (this) {
            if (this.b) {
                return;
            }
            this.b = true;
            a(this.f.b());
        }
    }

    void a(long j) {
        if (this.h) {
            this.c = 0;
            this.i.sendEmptyMessageAtTime(-1, 0);
            return;
        }
        h();
        this.j = this.a.schedule(this.p, Math.max(j, 0), TimeUnit.MILLISECONDS);
    }

    public void c() {
        this.a.execute(new b$1(this, this));
    }

    public void stop() {
        synchronized (this) {
            if (this.b) {
                this.b = false;
                h();
                this.f.d();
                return;
            }
        }
    }

    private void h() {
        if (this.j != null) {
            this.j.cancel(false);
        }
        this.i.removeMessages(-1);
    }

    public boolean isRunning() {
        return this.b;
    }

    public String toString() {
        return String.format(Locale.ENGLISH, "GIF: size: %dx%d, frames: %d, error: %d", new Object[]{Integer.valueOf(this.f.l()), Integer.valueOf(this.f.m()), Integer.valueOf(this.f.n()), Integer.valueOf(this.f.f())});
    }

    public int d() {
        return this.f.n();
    }

    public void pause() {
        stop();
    }

    public int getDuration() {
        return this.f.g();
    }

    public int getCurrentPosition() {
        return this.f.h();
    }

    public void seekTo(@IntRange(from = 0, to = 2147483647L) int i) {
        if (i < 0) {
            throw new IllegalArgumentException("Position is not positive");
        }
        this.a.execute(new b$2(this, this, i));
    }

    public boolean isPlaying() {
        return this.b;
    }

    public int getBufferPercentage() {
        return 100;
    }

    public boolean canPause() {
        return true;
    }

    public boolean canSeekBackward() {
        return d() > 1;
    }

    public boolean canSeekForward() {
        return d() > 1;
    }

    public int getAudioSessionId() {
        return 0;
    }

    protected void onBoundsChange(Rect rect) {
        this.l.set(rect);
        if (this.t != null) {
            this.t.a(rect);
        }
    }

    public void draw(@NonNull Canvas canvas) {
        a(canvas);
        if (this.h && this.b && this.c != Long.MIN_VALUE) {
            long max = Math.max(0, this.c - SystemClock.uptimeMillis());
            this.c = Long.MIN_VALUE;
            this.a.remove(this.p);
            this.j = this.a.schedule(this.p, max, TimeUnit.MILLISECONDS);
        }
    }

    private void a(Canvas canvas) {
        Object obj;
        if (this.n == null || this.d.getColorFilter() != null) {
            obj = null;
        } else {
            this.d.setColorFilter(this.n);
            obj = 1;
        }
        if (this.t == null) {
            canvas.drawBitmap(this.e, this.q, this.l, this.d);
        } else {
            this.t.a(canvas, this.d, this.e);
        }
        if (obj != null) {
            this.d.setColorFilter(null);
        }
    }

    public boolean b(long j) {
        long duration = j % ((long) getDuration());
        return duration >= ((long) this.u) || duration < ((long) (this.u - this.v));
    }

    public void a(Canvas canvas, int i) {
        int duration = getDuration();
        int i2 = i % duration;
        if (i2 == 0) {
            this.u = -1;
            this.v = 0;
            this.f.c();
        }
        synchronized (this.f) {
            this.f.a(i2, this.e);
            this.v = this.f.a(this.f.i());
            this.u = this.f.h();
            if (this.u == 0) {
                this.u = duration;
            }
        }
        a(canvas);
    }

    public int getAlpha() {
        return this.d.getAlpha();
    }

    public void setFilterBitmap(boolean z) {
        this.d.setFilterBitmap(z);
        invalidateSelf();
    }

    @Deprecated
    public void setDither(boolean z) {
        this.d.setDither(z);
        invalidateSelf();
    }

    public ColorFilter getColorFilter() {
        return this.d.getColorFilter();
    }

    private PorterDuffColorFilter a(ColorStateList colorStateList, Mode mode) {
        if (colorStateList == null || mode == null) {
            return null;
        }
        return new PorterDuffColorFilter(colorStateList.getColorForState(getState(), 0), mode);
    }

    public void setTintList(ColorStateList colorStateList) {
        this.m = colorStateList;
        this.n = a(colorStateList, this.o);
        invalidateSelf();
    }

    public void setTintMode(@NonNull Mode mode) {
        this.o = mode;
        this.n = a(this.m, mode);
        invalidateSelf();
    }

    protected boolean onStateChange(int[] iArr) {
        if (this.m == null || this.o == null) {
            return false;
        }
        this.n = a(this.m, this.o);
        return true;
    }

    public boolean isStateful() {
        return super.isStateful() || (this.m != null && this.m.isStateful());
    }

    public boolean setVisible(boolean z, boolean z2) {
        boolean visible = super.setVisible(z, z2);
        if (!this.h) {
            if (z) {
                if (z2) {
                    c();
                }
                if (visible && this.k) {
                    start();
                }
            } else if (visible) {
                this.k = isRunning();
                if (this.k) {
                    stop();
                }
            }
        }
        return visible;
    }

    public int e() {
        return this.f.i();
    }

    public int f() {
        int j = this.f.j();
        return (j == 0 || j < this.f.e()) ? j : j - 1;
    }
}
