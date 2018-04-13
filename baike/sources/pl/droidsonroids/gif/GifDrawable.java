package pl.droidsonroids.gif;

import android.annotation.TargetApi;
import android.content.ContentResolver;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
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
import android.net.Uri;
import android.os.Build.VERSION;
import android.widget.MediaController.MediaPlayerControl;
import java.io.File;
import java.io.FileDescriptor;
import java.io.IOException;
import java.io.InputStream;
import java.lang.Thread.UncaughtExceptionHandler;
import java.nio.ByteBuffer;
import java.util.Locale;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import qsbk.app.core.model.CustomButton;

public class GifDrawable extends Drawable implements Animatable, MediaPlayerControl {
    protected final Paint a;
    private final ScheduledThreadPoolExecutor b;
    private volatile boolean c;
    private final long d;
    private final Rect e;
    private final Rect f;
    private final Bitmap g;
    private final GifInfoHandle h;
    private final ConcurrentLinkedQueue<AnimationListener> i;
    private ColorStateList j;
    private PorterDuffColorFilter k;
    private Mode l;
    private final Runnable m;
    private final Runnable n;
    private final Runnable o;

    private abstract class a implements Runnable {
        final /* synthetic */ GifDrawable c;

        protected abstract void doWork();

        private a(GifDrawable gifDrawable) {
            this.c = gifDrawable;
        }

        public final void run() {
            try {
                if (!this.c.isRecycled()) {
                    doWork();
                }
            } catch (Throwable th) {
                UncaughtExceptionHandler defaultUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
                if (defaultUncaughtExceptionHandler != null) {
                    defaultUncaughtExceptionHandler.uncaughtException(Thread.currentThread(), th);
                }
            }
        }
    }

    public GifDrawable(Resources resources, int i) throws NotFoundException, IOException {
        this(resources.openRawResourceFd(i));
    }

    public GifDrawable(AssetManager assetManager, String str) throws IOException {
        this(assetManager.openFd(str));
    }

    public GifDrawable(String str) throws IOException {
        this(GifInfoHandle.openFile(str, false), new File(str).length(), null, null);
    }

    public GifDrawable(File file) throws IOException {
        this(GifInfoHandle.openFile(file.getPath(), false), file.length(), null, null);
    }

    public GifDrawable(InputStream inputStream) throws IOException {
        this(GifInfoHandle.a(inputStream, false), -1, null, null);
    }

    public GifDrawable(AssetFileDescriptor assetFileDescriptor) throws IOException {
        this(GifInfoHandle.a(assetFileDescriptor, false), assetFileDescriptor.getLength(), null, null);
    }

    public GifDrawable(FileDescriptor fileDescriptor) throws IOException {
        this(GifInfoHandle.openFd(fileDescriptor, 0, false), -1, null, null);
    }

    public GifDrawable(byte[] bArr) throws IOException {
        this(GifInfoHandle.openByteArray(bArr, false), (long) bArr.length, null, null);
    }

    public GifDrawable(ByteBuffer byteBuffer) throws IOException {
        this(GifInfoHandle.openDirectByteBuffer(byteBuffer, false), (long) byteBuffer.capacity(), null, null);
    }

    public GifDrawable(ContentResolver contentResolver, Uri uri) throws IOException {
        this(contentResolver.openAssetFileDescriptor(uri, CustomButton.POSITION_RIGHT));
    }

    @TargetApi(19)
    GifDrawable(GifInfoHandle gifInfoHandle, long j, GifDrawable gifDrawable, ScheduledThreadPoolExecutor scheduledThreadPoolExecutor) {
        this.c = true;
        this.e = new Rect();
        this.a = new Paint(6);
        this.i = new ConcurrentLinkedQueue();
        this.m = new b(this);
        this.n = new c(this);
        this.o = new d(this);
        if (scheduledThreadPoolExecutor == null) {
            scheduledThreadPoolExecutor = j.getInstance();
        }
        this.b = scheduledThreadPoolExecutor;
        this.h = gifInfoHandle;
        this.d = j;
        Bitmap bitmap = null;
        if (VERSION.SDK_INT >= 19 && gifDrawable != null) {
            synchronized (gifDrawable.h) {
                if (!gifDrawable.h.k()) {
                    int height = gifDrawable.g.getHeight();
                    int width = gifDrawable.g.getWidth();
                    if (height >= this.h.b && width >= this.h.a) {
                        gifDrawable.a();
                        bitmap = gifDrawable.g;
                        bitmap.eraseColor(0);
                        bitmap.reconfigure(this.h.a, this.h.b, Config.ARGB_8888);
                    }
                }
            }
        }
        if (bitmap == null) {
            this.g = Bitmap.createBitmap(this.h.a, this.h.b, Config.ARGB_8888);
        } else {
            this.g = bitmap;
        }
        this.f = new Rect(0, 0, this.h.a, this.h.b);
        this.b.execute(this.n);
    }

    public void recycle() {
        a();
        this.g.recycle();
    }

    private void a() {
        this.c = false;
        unscheduleSelf(this.m);
        this.h.a();
    }

    public boolean isRecycled() {
        return this.h.k();
    }

    public int getIntrinsicHeight() {
        return this.h.b;
    }

    public int getIntrinsicWidth() {
        return this.h.a;
    }

    public void setAlpha(int i) {
        this.a.setAlpha(i);
    }

    public void setColorFilter(ColorFilter colorFilter) {
        this.a.setColorFilter(colorFilter);
    }

    public int getOpacity() {
        return -2;
    }

    public void start() {
        this.c = true;
        this.b.execute(new e(this));
    }

    public void reset() {
        this.b.execute(new f(this));
    }

    public void stop() {
        this.c = false;
        unscheduleSelf(this.m);
        this.b.execute(new g(this));
    }

    public boolean isRunning() {
        return this.c;
    }

    public String getComment() {
        return this.h.e();
    }

    public int getLoopCount() {
        return this.h.f();
    }

    public String toString() {
        return String.format(Locale.US, "GIF: size: %dx%d, frames: %d, error: %d", new Object[]{Integer.valueOf(this.h.a), Integer.valueOf(this.h.b), Integer.valueOf(this.h.c), Integer.valueOf(this.h.g())});
    }

    public int getNumberOfFrames() {
        return this.h.c;
    }

    public GifError getError() {
        return GifError.a(this.h.g());
    }

    public static GifDrawable createFromResource(Resources resources, int i) {
        try {
            return new GifDrawable(resources, i);
        } catch (IOException e) {
            return null;
        }
    }

    public void setSpeed(float f) {
        if (f <= 0.0f) {
            throw new IllegalArgumentException("Speed factor is not positive");
        }
        this.h.a(f);
    }

    public void pause() {
        stop();
    }

    public int getDuration() {
        return this.h.h();
    }

    public int getCurrentPosition() {
        return this.h.i();
    }

    public void seekTo(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("Position is not positive");
        }
        this.b.execute(new h(this, i));
    }

    public void seekToFrame(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("frameIndex is not positive");
        }
        this.b.execute(new i(this, i));
    }

    public boolean isPlaying() {
        return this.c;
    }

    public int getBufferPercentage() {
        return 100;
    }

    public boolean canPause() {
        return true;
    }

    public boolean canSeekBackward() {
        return false;
    }

    public boolean canSeekForward() {
        return getNumberOfFrames() > 1;
    }

    public int getAudioSessionId() {
        return 0;
    }

    public int getFrameByteCount() {
        return this.g.getRowBytes() * this.g.getHeight();
    }

    @TargetApi(19)
    public long getAllocationByteCount() {
        long j = this.h.j();
        if (VERSION.SDK_INT >= 19) {
            return j + ((long) this.g.getAllocationByteCount());
        }
        return j + ((long) (this.g.getRowBytes() * this.g.getHeight()));
    }

    public long getInputSourceByteCount() {
        return this.d;
    }

    public void getPixels(int[] iArr) {
        this.g.getPixels(iArr, 0, this.h.a, 0, 0, this.h.a, this.h.b);
    }

    public int getPixel(int i, int i2) {
        return this.g.getPixel(i, i2);
    }

    protected void onBoundsChange(Rect rect) {
        this.e.set(getBounds());
    }

    public void draw(Canvas canvas) {
        Object obj;
        if (this.k == null || this.a.getColorFilter() != null) {
            obj = null;
        } else {
            this.a.setColorFilter(this.k);
            obj = 1;
        }
        if (this.a.getShader() == null) {
            canvas.drawBitmap(this.g, this.f, this.e, this.a);
        } else {
            canvas.drawRect(this.e, this.a);
        }
        if (obj != null) {
            this.a.setColorFilter(null);
        }
    }

    public final Paint getPaint() {
        return this.a;
    }

    public int getAlpha() {
        return this.a.getAlpha();
    }

    public void setFilterBitmap(boolean z) {
        this.a.setFilterBitmap(z);
        invalidateSelf();
    }

    public void setDither(boolean z) {
        this.a.setDither(z);
        invalidateSelf();
    }

    public int getMinimumHeight() {
        return this.h.b;
    }

    public int getMinimumWidth() {
        return this.h.a;
    }

    public void addAnimationListener(AnimationListener animationListener) {
        this.i.add(animationListener);
    }

    public boolean removeAnimationListener(AnimationListener animationListener) {
        return this.i.remove(animationListener);
    }

    public ColorFilter getColorFilter() {
        return this.a.getColorFilter();
    }

    public Bitmap getCurrentFrame() {
        return this.g.copy(this.g.getConfig(), this.g.isMutable());
    }

    private PorterDuffColorFilter a(ColorStateList colorStateList, Mode mode) {
        if (colorStateList == null || mode == null) {
            return null;
        }
        return new PorterDuffColorFilter(colorStateList.getColorForState(getState(), 0), mode);
    }

    public void setTintList(ColorStateList colorStateList) {
        this.j = colorStateList;
        this.k = a(colorStateList, this.l);
        invalidateSelf();
    }

    public void setTintMode(Mode mode) {
        this.l = mode;
        this.k = a(this.j, mode);
        invalidateSelf();
    }

    protected boolean onStateChange(int[] iArr) {
        if (this.j == null || this.l == null) {
            return false;
        }
        this.k = a(this.j, this.l);
        return true;
    }

    public boolean isStateful() {
        return super.isStateful() || (this.j != null && this.j.isStateful());
    }

    public boolean setVisible(boolean z, boolean z2) {
        boolean visible = super.setVisible(z, z2);
        if (z) {
            if (z2) {
                reset();
            }
            if (visible) {
                start();
            }
        } else if (visible) {
            stop();
        }
        return visible;
    }
}
