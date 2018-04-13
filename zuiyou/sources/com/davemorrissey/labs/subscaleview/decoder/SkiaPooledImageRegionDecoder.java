package com.davemorrissey.labs.subscaleview.decoder;

import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.content.ContentResolver;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory.Options;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Point;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Build.VERSION;
import android.support.annotation.Keep;
import android.text.TextUtils;
import android.util.Log;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;
import com.meizu.cloud.pushsdk.pushtracer.constant.Parameters;
import java.io.File;
import java.io.FileFilter;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.regex.Pattern;

public class SkiaPooledImageRegionDecoder implements d {
    private static final String a = SkiaPooledImageRegionDecoder.class.getSimpleName();
    private static boolean b = false;
    private b c;
    private final ReadWriteLock d;
    private final Config e;
    private Context f;
    private Uri g;
    private long h;
    private final Point i;
    private final AtomicBoolean j;

    class a implements FileFilter {
        final /* synthetic */ SkiaPooledImageRegionDecoder a;

        a(SkiaPooledImageRegionDecoder skiaPooledImageRegionDecoder) {
            this.a = skiaPooledImageRegionDecoder;
        }

        public boolean accept(File file) {
            return Pattern.matches("cpu[0-9]+", file.getName());
        }
    }

    private static class b {
        private final Semaphore a;
        private final Map<BitmapRegionDecoder, Boolean> b;

        private b() {
            this.a = new Semaphore(0, true);
            this.b = new ConcurrentHashMap();
        }

        private synchronized boolean a() {
            return this.b.isEmpty();
        }

        private synchronized int b() {
            return this.b.size();
        }

        private BitmapRegionDecoder c() {
            this.a.acquireUninterruptibly();
            return e();
        }

        private void a(BitmapRegionDecoder bitmapRegionDecoder) {
            if (c(bitmapRegionDecoder)) {
                this.a.release();
            }
        }

        private synchronized void b(BitmapRegionDecoder bitmapRegionDecoder) {
            this.b.put(bitmapRegionDecoder, Boolean.valueOf(false));
            this.a.release();
        }

        private synchronized void d() {
            while (!this.b.isEmpty()) {
                BitmapRegionDecoder c = c();
                c.recycle();
                this.b.remove(c);
            }
        }

        private synchronized BitmapRegionDecoder e() {
            BitmapRegionDecoder bitmapRegionDecoder;
            for (Entry entry : this.b.entrySet()) {
                if (!((Boolean) entry.getValue()).booleanValue()) {
                    entry.setValue(Boolean.valueOf(true));
                    bitmapRegionDecoder = (BitmapRegionDecoder) entry.getKey();
                    break;
                }
            }
            bitmapRegionDecoder = null;
            return bitmapRegionDecoder;
        }

        private synchronized boolean c(BitmapRegionDecoder bitmapRegionDecoder) {
            boolean z;
            for (Entry entry : this.b.entrySet()) {
                if (bitmapRegionDecoder == entry.getKey()) {
                    if (((Boolean) entry.getValue()).booleanValue()) {
                        entry.setValue(Boolean.valueOf(false));
                        z = true;
                    } else {
                        z = false;
                    }
                }
            }
            z = false;
            return z;
        }
    }

    @Keep
    public SkiaPooledImageRegionDecoder() {
        this(null);
    }

    public SkiaPooledImageRegionDecoder(Config config) {
        this.c = new b();
        this.d = new ReentrantReadWriteLock(true);
        this.h = Long.MAX_VALUE;
        this.i = new Point(0, 0);
        this.j = new AtomicBoolean(false);
        Config preferredBitmapConfig = SubsamplingScaleImageView.getPreferredBitmapConfig();
        if (config != null) {
            this.e = config;
        } else if (preferredBitmapConfig != null) {
            this.e = preferredBitmapConfig;
        } else {
            this.e = Config.RGB_565;
        }
    }

    @Keep
    public static void setDebug(boolean z) {
        b = z;
    }

    public Point a(Context context, Uri uri) throws Exception {
        this.f = context;
        this.g = uri;
        d();
        return this.i;
    }

    private void c() {
        if (this.j.compareAndSet(false, true) && this.h < Long.MAX_VALUE) {
            a("Starting lazy init of additional decoders");
            new Thread(this, "ImageRegionDecoder") {
                final /* synthetic */ SkiaPooledImageRegionDecoder a;

                public void run() {
                    while (this.a.c != null && this.a.a(this.a.c.b(), this.a.h)) {
                        try {
                            if (this.a.c != null) {
                                long currentTimeMillis = System.currentTimeMillis();
                                this.a.a("Starting decoder");
                                this.a.d();
                                this.a.a("Started decoder, took " + (System.currentTimeMillis() - currentTimeMillis) + Parameters.MESSAGE_SEQ);
                            }
                        } catch (Exception e) {
                            this.a.a("Failed to start decoder: " + e.getMessage());
                        }
                    }
                }
            }.start();
        }
    }

    private void d() throws Exception {
        BitmapRegionDecoder newInstance;
        String uri = this.g.toString();
        long j = Long.MAX_VALUE;
        if (uri.startsWith("android.resource://")) {
            Resources resources;
            int identifier;
            long length;
            String authority = this.g.getAuthority();
            if (this.f.getPackageName().equals(authority)) {
                resources = this.f.getResources();
            } else {
                resources = this.f.getPackageManager().getResourcesForApplication(authority);
            }
            List pathSegments = this.g.getPathSegments();
            int size = pathSegments.size();
            if (size == 2 && ((String) pathSegments.get(0)).equals("drawable")) {
                identifier = resources.getIdentifier((String) pathSegments.get(1), "drawable", authority);
            } else if (size == 1 && TextUtils.isDigitsOnly((CharSequence) pathSegments.get(0))) {
                try {
                    identifier = Integer.parseInt((String) pathSegments.get(0));
                } catch (NumberFormatException e) {
                    identifier = 0;
                }
            } else {
                identifier = 0;
            }
            try {
                length = this.f.getResources().openRawResourceFd(identifier).getLength();
            } catch (Exception e2) {
                length = j;
            }
            long j2 = length;
            newInstance = BitmapRegionDecoder.newInstance(this.f.getResources().openRawResource(identifier), false);
            j = j2;
        } else if (uri.startsWith("file:///android_asset/")) {
            String substring = uri.substring("file:///android_asset/".length());
            try {
                j = this.f.getAssets().openFd(substring).getLength();
            } catch (Exception e3) {
            }
            newInstance = BitmapRegionDecoder.newInstance(this.f.getAssets().open(substring, 1), false);
        } else if (uri.startsWith("file://")) {
            newInstance = BitmapRegionDecoder.newInstance(uri.substring("file://".length()), false);
            try {
                File file = new File(uri);
                if (file.exists()) {
                    j = file.length();
                }
            } catch (Exception e4) {
            }
        } else {
            InputStream inputStream = null;
            try {
                ContentResolver contentResolver = this.f.getContentResolver();
                inputStream = contentResolver.openInputStream(this.g);
                newInstance = BitmapRegionDecoder.newInstance(inputStream, false);
                try {
                    AssetFileDescriptor openAssetFileDescriptor = contentResolver.openAssetFileDescriptor(this.g, "r");
                    if (openAssetFileDescriptor != null) {
                        j = openAssetFileDescriptor.getLength();
                    }
                } catch (Exception e5) {
                }
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (Exception e6) {
                    }
                }
            } catch (Throwable th) {
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (Exception e7) {
                    }
                }
            }
        }
        this.h = j;
        this.i.set(newInstance.getWidth(), newInstance.getHeight());
        this.d.writeLock().lock();
        try {
            if (this.c != null) {
                this.c.b(newInstance);
            }
            this.d.writeLock().unlock();
        } catch (Throwable th2) {
            this.d.writeLock().unlock();
        }
    }

    public Bitmap a(Rect rect, int i) {
        a("Decode region " + rect + " on thread " + Thread.currentThread().getName());
        if (rect.width() < this.i.x || rect.height() < this.i.y) {
            c();
        }
        this.d.readLock().lock();
        BitmapRegionDecoder b;
        try {
            if (this.c != null) {
                b = this.c.c();
                if (b != null) {
                    if (!b.isRecycled()) {
                        Options options = new Options();
                        options.inSampleSize = i;
                        options.inPreferredConfig = this.e;
                        Bitmap decodeRegion = b.decodeRegion(rect, options);
                        if (decodeRegion == null) {
                            throw new RuntimeException("Skia image decoder returned null bitmap - image format may not be supported");
                        }
                        if (b != null) {
                            this.c.a(b);
                        }
                        this.d.readLock().unlock();
                        return decodeRegion;
                    }
                }
                if (b != null) {
                    this.c.a(b);
                }
            }
            throw new IllegalStateException("Cannot decode region after decoder has been recycled");
        } catch (Throwable th) {
            this.d.readLock().unlock();
        }
    }

    public synchronized boolean a() {
        boolean z;
        z = (this.c == null || this.c.a()) ? false : true;
        return z;
    }

    public synchronized void b() {
        this.d.writeLock().lock();
        try {
            if (this.c != null) {
                this.c.d();
                this.c = null;
                this.f = null;
                this.g = null;
            }
            this.d.writeLock().unlock();
        } catch (Throwable th) {
            this.d.writeLock().unlock();
        }
    }

    protected boolean a(int i, long j) {
        if (i >= 4) {
            a("No additional decoders allowed, reached hard limit (4)");
            return false;
        } else if (((long) i) * j > 20971520) {
            a("No additional encoders allowed, reached hard memory limit (20Mb)");
            return false;
        } else if (i >= e()) {
            a("No additional encoders allowed, limited by CPU cores (" + e() + ")");
            return false;
        } else if (g()) {
            a("No additional encoders allowed, memory is low");
            return false;
        } else {
            a("Additional decoder allowed, current count is " + i + ", estimated native memory " + ((((long) i) * j) / 1048576) + "Mb");
            return true;
        }
    }

    private int e() {
        if (VERSION.SDK_INT >= 17) {
            return Runtime.getRuntime().availableProcessors();
        }
        return f();
    }

    private int f() {
        try {
            return new File("/sys/devices/system/cpu/").listFiles(new a(this)).length;
        } catch (Exception e) {
            return 1;
        }
    }

    private boolean g() {
        ActivityManager activityManager = (ActivityManager) this.f.getSystemService("activity");
        if (activityManager == null) {
            return true;
        }
        MemoryInfo memoryInfo = new MemoryInfo();
        activityManager.getMemoryInfo(memoryInfo);
        return memoryInfo.lowMemory;
    }

    private void a(String str) {
        if (b) {
            Log.d(a, str);
        }
    }
}
