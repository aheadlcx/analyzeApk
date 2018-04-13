package pl.droidsonroids.gif;

import android.content.ContentResolver;
import android.content.res.AssetFileDescriptor;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.SystemClock;
import android.widget.MediaController.MediaPlayerControl;
import java.io.File;
import java.io.FileDescriptor;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Iterator;
import java.util.Locale;
import java.util.concurrent.ConcurrentLinkedQueue;

public class GifDrawable extends Drawable implements Animatable, MediaPlayerControl {
    protected final Paint a;
    private volatile long b;
    private volatile boolean c;
    private final int[] d;
    private final long e;
    private float f;
    private float g;
    private boolean h;
    private final Rect i;
    private boolean j;
    private int[] k;
    private final ConcurrentLinkedQueue<a> l;
    private final Runnable m;
    private final Runnable n;
    private final Runnable o;
    private final Runnable p;
    private int q;
    private int r;

    static native void free(long j);

    private static native long getAllocationByteCount(long j);

    private static native String getComment(long j);

    private static native int getCurrentPosition(long j);

    static native int getDuration(long j);

    static native int getLoopCount(long j);

    static native long openByteArray(int[] iArr, byte[] bArr, boolean z) throws GifIOException;

    static native long openDirectByteBuffer(int[] iArr, ByteBuffer byteBuffer, boolean z) throws GifIOException;

    static native long openFd(int[] iArr, FileDescriptor fileDescriptor, long j, boolean z) throws GifIOException;

    static native long openFile(int[] iArr, String str, boolean z) throws GifIOException;

    static native long openStream(int[] iArr, InputStream inputStream, boolean z) throws GifIOException;

    private static native boolean renderFrame(int[] iArr, long j, int[] iArr2);

    private static native void reset(long j);

    private static native void restoreRemainder(long j);

    private static native void saveRemainder(long j);

    private static native void seekToFrame(long j, int i, int[] iArr);

    private static native void seekToTime(long j, int i, int[] iArr);

    private static native void setSpeedFactor(long j, float f);

    static {
        System.loadLibrary("gif");
    }

    public void a(boolean z) {
        this.j = z;
    }

    private void a(Runnable runnable) {
        scheduleSelf(runnable, SystemClock.uptimeMillis());
    }

    public GifDrawable(Resources resources, int i) throws NotFoundException, IOException {
        this(resources.openRawResourceFd(i));
    }

    public GifDrawable(String str) throws IOException {
        this.c = true;
        this.d = new int[5];
        this.f = 1.0f;
        this.g = 1.0f;
        this.i = new Rect();
        this.a = new Paint(6);
        this.l = new ConcurrentLinkedQueue();
        this.m = new GifDrawable$1(this);
        this.n = new GifDrawable$2(this);
        this.o = new GifDrawable$3(this);
        this.p = new GifDrawable$4(this);
        this.q = -1996488705;
        this.r = 3;
        if (str == null) {
            throw new NullPointerException("Source is null");
        }
        this.e = new File(str).length();
        this.b = openFile(this.d, str, false);
        this.k = new int[(this.d[0] * this.d[1])];
    }

    public GifDrawable(File file) throws IOException {
        this.c = true;
        this.d = new int[5];
        this.f = 1.0f;
        this.g = 1.0f;
        this.i = new Rect();
        this.a = new Paint(6);
        this.l = new ConcurrentLinkedQueue();
        this.m = new GifDrawable$1(this);
        this.n = new GifDrawable$2(this);
        this.o = new GifDrawable$3(this);
        this.p = new GifDrawable$4(this);
        this.q = -1996488705;
        this.r = 3;
        if (file == null) {
            throw new NullPointerException("Source is null");
        }
        this.e = file.length();
        this.b = openFile(this.d, file.getPath(), false);
        this.k = new int[(this.d[0] * this.d[1])];
    }

    public GifDrawable(AssetFileDescriptor assetFileDescriptor) throws IOException {
        this.c = true;
        this.d = new int[5];
        this.f = 1.0f;
        this.g = 1.0f;
        this.i = new Rect();
        this.a = new Paint(6);
        this.l = new ConcurrentLinkedQueue();
        this.m = new GifDrawable$1(this);
        this.n = new GifDrawable$2(this);
        this.o = new GifDrawable$3(this);
        this.p = new GifDrawable$4(this);
        this.q = -1996488705;
        this.r = 3;
        if (assetFileDescriptor == null) {
            throw new NullPointerException("Source is null");
        }
        try {
            this.b = openFd(this.d, assetFileDescriptor.getFileDescriptor(), assetFileDescriptor.getStartOffset(), false);
            this.k = new int[(this.d[0] * this.d[1])];
            this.e = assetFileDescriptor.getLength();
        } catch (IOException e) {
            assetFileDescriptor.close();
            throw e;
        }
    }

    public GifDrawable(ContentResolver contentResolver, Uri uri) throws IOException {
        this(contentResolver.openAssetFileDescriptor(uri, "r"));
    }

    public void a() {
        this.c = false;
        long j = this.b;
        this.b = 0;
        this.k = null;
        free(j);
    }

    protected void finalize() throws Throwable {
        try {
            a();
        } finally {
            super.finalize();
        }
    }

    public int getIntrinsicHeight() {
        return this.d[1];
    }

    public int getIntrinsicWidth() {
        return this.d[0];
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
        a(this.n);
    }

    public void stop() {
        this.c = false;
        a(this.o);
    }

    public boolean isRunning() {
        return this.c;
    }

    public String toString() {
        return String.format(Locale.US, "GIF: size: %dx%d, frames: %d, error: %d", new Object[]{Integer.valueOf(this.d[0]), Integer.valueOf(this.d[1]), Integer.valueOf(this.d[2]), Integer.valueOf(this.d[3])});
    }

    public int b() {
        return this.d[2];
    }

    public void pause() {
        stop();
    }

    public int getDuration() {
        return getDuration(this.b);
    }

    public int getCurrentPosition() {
        return getCurrentPosition(this.b);
    }

    public void seekTo(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("Position is not positive");
        }
        a(new GifDrawable$5(this, i));
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
        return b() > 1;
    }

    public int getAudioSessionId() {
        return 0;
    }

    public long c() {
        long allocationByteCount = getAllocationByteCount(this.b);
        int[] iArr = this.k;
        return iArr == null ? allocationByteCount : allocationByteCount + ((long) (iArr.length * 4));
    }

    protected void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        this.h = true;
    }

    public void draw(Canvas canvas) {
        if (this.h) {
            this.i.set(getBounds());
            this.f = ((float) this.i.width()) / ((float) this.d[0]);
            this.g = ((float) this.i.height()) / ((float) this.d[1]);
            this.h = false;
        }
        if (this.a.getShader() == null) {
            if (!this.c) {
                this.d[4] = -1;
            } else if (renderFrame(this.k, this.b, this.d)) {
                Iterator it = this.l.iterator();
                while (it.hasNext()) {
                    ((a) it.next()).a();
                }
            }
            canvas.scale(this.f, this.g);
            int[] iArr = this.k;
            if (iArr != null) {
                if (!this.j || this.d[0] == 0 || this.d[1] == 0) {
                    canvas.drawBitmap(iArr, 0, this.d[0], 0.0f, 0.0f, this.d[0], this.d[1], true, this.a);
                } else {
                    a(canvas, Bitmap.createBitmap(iArr, this.d[0], this.d[1], Config.ARGB_8888));
                }
            }
            if (this.d[4] >= 0 && this.d[2] > 1) {
                unscheduleSelf(this.p);
                scheduleSelf(this.p, SystemClock.uptimeMillis() + ((long) this.d[4]));
                return;
            }
            return;
        }
        canvas.drawRect(this.i, this.a);
    }

    private void a(Canvas canvas, Bitmap bitmap) {
        float f = 0.0f;
        try {
            float f2;
            float min = (((float) Math.min(this.d[0], this.d[1])) / 2.0f) - ((float) this.r);
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            if (Math.max(width, height) == width) {
                f2 = ((float) (width - height)) / 2.0f;
            } else {
                float f3 = ((float) (height - width)) / 2.0f;
                f2 = 0.0f;
                f = f3;
            }
            Bitmap a = a(bitmap);
            if (a != null) {
                canvas.drawBitmap(a, f2, f, null);
            }
            a(canvas, min, this.q);
        } catch (Exception e) {
        }
    }

    public Bitmap a(Bitmap bitmap) {
        if (bitmap == null) {
            return null;
        }
        try {
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            if (height > width) {
                bitmap = Bitmap.createBitmap(bitmap, 0, (height - width) / 2, width, width);
            } else if (height < width) {
                bitmap = Bitmap.createBitmap(bitmap, (width - height) / 2, 0, height, height);
            }
            Bitmap createBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Config.ARGB_8888);
            Canvas canvas = new Canvas(createBitmap);
            Paint paint = new Paint();
            Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
            paint.setAntiAlias(true);
            paint.setFilterBitmap(true);
            paint.setDither(true);
            canvas.drawARGB(0, 0, 0, 0);
            canvas.drawCircle((float) (bitmap.getWidth() / 2), (float) (bitmap.getHeight() / 2), (float) ((bitmap.getWidth() / 2) - this.r), paint);
            paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
            canvas.drawBitmap(bitmap, rect, rect, paint);
            return createBitmap;
        } catch (OutOfMemoryError e) {
            return null;
        } catch (Exception e2) {
            return null;
        }
    }

    private void a(Canvas canvas, float f, int i) {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setFilterBitmap(true);
        paint.setDither(true);
        paint.setColor(i);
        paint.setStyle(Style.STROKE);
        paint.setStrokeWidth((float) this.r);
        canvas.drawCircle(((float) (this.i.left + this.i.right)) / 2.0f, ((float) (this.i.top + this.i.bottom)) / 2.0f, f, paint);
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
        return this.d[1];
    }

    public int getMinimumWidth() {
        return this.d[0];
    }

    public ColorFilter getColorFilter() {
        return this.a.getColorFilter();
    }

    public boolean d() {
        if (this.d == null || this.d[3] == 1) {
            return true;
        }
        return false;
    }
}
