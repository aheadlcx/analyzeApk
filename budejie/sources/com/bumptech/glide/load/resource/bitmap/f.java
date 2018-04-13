package com.bumptech.glide.load.resource.bitmap;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.os.Build.VERSION;
import android.util.Log;
import com.bumptech.glide.i.a;
import com.bumptech.glide.i.h;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.a.c;
import com.bumptech.glide.load.resource.bitmap.ImageHeaderParser.ImageType;
import java.io.InputStream;
import java.util.EnumSet;
import java.util.Queue;
import java.util.Set;

public abstract class f implements a<InputStream> {
    public static final f a = new f() {
        protected int a(int i, int i2, int i3, int i4) {
            return Math.min(i2 / i4, i / i3);
        }

        public String a() {
            return "AT_LEAST.com.bumptech.glide.load.data.bitmap";
        }
    };
    public static final f b = new f() {
        protected int a(int i, int i2, int i3, int i4) {
            int i5 = 1;
            int ceil = (int) Math.ceil((double) Math.max(((float) i2) / ((float) i4), ((float) i) / ((float) i3)));
            int max = Math.max(1, Integer.highestOneBit(ceil));
            if (max >= ceil) {
                i5 = 0;
            }
            return max << i5;
        }

        public String a() {
            return "AT_MOST.com.bumptech.glide.load.data.bitmap";
        }
    };
    public static final f c = new f() {
        protected int a(int i, int i2, int i3, int i4) {
            return 0;
        }

        public String a() {
            return "NONE.com.bumptech.glide.load.data.bitmap";
        }
    };
    private static final Set<ImageType> d = EnumSet.of(ImageType.JPEG, ImageType.PNG_A, ImageType.PNG);
    private static final Queue<Options> e = h.a(0);

    protected abstract int a(int i, int i2, int i3, int i4);

    public Bitmap a(InputStream inputStream, c cVar, int i, int i2, DecodeFormat decodeFormat) {
        int i3;
        a a = a.a();
        byte[] b = a.b();
        byte[] b2 = a.b();
        Options b3 = b();
        InputStream recyclableBufferedInputStream = new RecyclableBufferedInputStream(inputStream, b2);
        InputStream a2 = com.bumptech.glide.i.c.a(recyclableBufferedInputStream);
        com.bumptech.glide.i.f fVar = new com.bumptech.glide.i.f(a2);
        try {
            a2.mark(5242880);
            try {
                int c = new ImageHeaderParser(a2).c();
                a2.reset();
                i3 = c;
            } catch (Throwable e) {
                if (Log.isLoggable("Downsampler", 5)) {
                    Log.w("Downsampler", "Cannot determine the image orientation from header", e);
                }
                try {
                    a2.reset();
                    i3 = 0;
                } catch (Throwable e2) {
                    if (Log.isLoggable("Downsampler", 5)) {
                        Log.w("Downsampler", "Cannot reset the input stream", e2);
                    }
                    i3 = 0;
                }
            } catch (Throwable e22) {
                try {
                    a2.reset();
                } catch (Throwable e3) {
                    if (Log.isLoggable("Downsampler", 5)) {
                        Log.w("Downsampler", "Cannot reset the input stream", e3);
                    }
                }
                throw e22;
            }
        } catch (Throwable e32) {
            if (Log.isLoggable("Downsampler", 5)) {
                Log.w("Downsampler", "Cannot reset the input stream", e32);
            }
            i3 = c;
        } catch (Throwable e222) {
            a.a(b);
            a.a(b2);
            a2.b();
            a(b3);
            throw e222;
        }
        b3.inTempStorage = b;
        int[] a3 = a(fVar, recyclableBufferedInputStream, b3);
        int i4 = a3[0];
        int i5 = a3[1];
        Bitmap a4 = a(fVar, recyclableBufferedInputStream, b3, cVar, i4, i5, a(p.a(i3), i4, i5, i, i2), decodeFormat);
        Throwable e2222 = a2.a();
        if (e2222 != null) {
            throw new RuntimeException(e2222);
        }
        Bitmap bitmap = null;
        if (a4 != null) {
            bitmap = p.a(a4, cVar, i3);
            if (!(a4.equals(bitmap) || cVar.a(a4))) {
                a4.recycle();
            }
        }
        a.a(b);
        a.a(b2);
        a2.b();
        a(b3);
        return bitmap;
    }

    private int a(int i, int i2, int i3, int i4, int i5) {
        int a;
        if (i5 == Integer.MIN_VALUE) {
            i5 = i3;
        }
        if (i4 == Integer.MIN_VALUE) {
            i4 = i2;
        }
        if (i == 90 || i == 270) {
            a = a(i3, i2, i4, i5);
        } else {
            a = a(i2, i3, i4, i5);
        }
        return Math.max(1, a == 0 ? 0 : Integer.highestOneBit(a));
    }

    private Bitmap a(com.bumptech.glide.i.f fVar, RecyclableBufferedInputStream recyclableBufferedInputStream, Options options, c cVar, int i, int i2, int i3, DecodeFormat decodeFormat) {
        Config a = a((InputStream) fVar, decodeFormat);
        options.inSampleSize = i3;
        options.inPreferredConfig = a;
        if ((options.inSampleSize == 1 || 19 <= VERSION.SDK_INT) && a((InputStream) fVar)) {
            a(options, cVar.b((int) Math.ceil(((double) i) / ((double) i3)), (int) Math.ceil(((double) i2) / ((double) i3)), a));
        }
        return b(fVar, recyclableBufferedInputStream, options);
    }

    private static boolean a(InputStream inputStream) {
        if (19 <= VERSION.SDK_INT) {
            return true;
        }
        inputStream.mark(1024);
        try {
            boolean contains = d.contains(new ImageHeaderParser(inputStream).b());
            try {
                inputStream.reset();
                return contains;
            } catch (Throwable e) {
                if (!Log.isLoggable("Downsampler", 5)) {
                    return contains;
                }
                Log.w("Downsampler", "Cannot reset the input stream", e);
                return contains;
            }
        } catch (Throwable e2) {
            if (Log.isLoggable("Downsampler", 5)) {
                Log.w("Downsampler", "Cannot determine the image type from header", e2);
            }
            try {
                inputStream.reset();
            } catch (Throwable e22) {
                if (Log.isLoggable("Downsampler", 5)) {
                    Log.w("Downsampler", "Cannot reset the input stream", e22);
                }
            }
            return false;
        } catch (Throwable e222) {
            try {
                inputStream.reset();
            } catch (Throwable e3) {
                if (Log.isLoggable("Downsampler", 5)) {
                    Log.w("Downsampler", "Cannot reset the input stream", e3);
                }
            }
            throw e222;
        }
    }

    private static Config a(InputStream inputStream, DecodeFormat decodeFormat) {
        if (decodeFormat == DecodeFormat.ALWAYS_ARGB_8888 || decodeFormat == DecodeFormat.PREFER_ARGB_8888 || VERSION.SDK_INT == 16) {
            return Config.ARGB_8888;
        }
        boolean a;
        inputStream.mark(1024);
        try {
            a = new ImageHeaderParser(inputStream).a();
            try {
                inputStream.reset();
            } catch (Throwable e) {
                if (Log.isLoggable("Downsampler", 5)) {
                    Log.w("Downsampler", "Cannot reset the input stream", e);
                }
            }
        } catch (Throwable e2) {
            if (Log.isLoggable("Downsampler", 5)) {
                Log.w("Downsampler", "Cannot determine whether the image has alpha or not from header for format " + decodeFormat, e2);
            }
            try {
                inputStream.reset();
                a = false;
            } catch (Throwable e22) {
                if (Log.isLoggable("Downsampler", 5)) {
                    Log.w("Downsampler", "Cannot reset the input stream", e22);
                }
                a = false;
            }
        } catch (Throwable e222) {
            try {
                inputStream.reset();
            } catch (Throwable e3) {
                if (Log.isLoggable("Downsampler", 5)) {
                    Log.w("Downsampler", "Cannot reset the input stream", e3);
                }
            }
            throw e222;
        }
        if (a) {
            return Config.ARGB_8888;
        }
        return Config.RGB_565;
    }

    public int[] a(com.bumptech.glide.i.f fVar, RecyclableBufferedInputStream recyclableBufferedInputStream, Options options) {
        options.inJustDecodeBounds = true;
        b(fVar, recyclableBufferedInputStream, options);
        options.inJustDecodeBounds = false;
        return new int[]{options.outWidth, options.outHeight};
    }

    private static Bitmap b(com.bumptech.glide.i.f fVar, RecyclableBufferedInputStream recyclableBufferedInputStream, Options options) {
        if (options.inJustDecodeBounds) {
            fVar.mark(5242880);
        } else {
            recyclableBufferedInputStream.a();
        }
        Bitmap decodeStream = BitmapFactory.decodeStream(fVar, null, options);
        try {
            if (options.inJustDecodeBounds) {
                fVar.reset();
            }
        } catch (Throwable e) {
            if (Log.isLoggable("Downsampler", 6)) {
                Log.e("Downsampler", "Exception loading inDecodeBounds=" + options.inJustDecodeBounds + " sample=" + options.inSampleSize, e);
            }
        }
        return decodeStream;
    }

    @TargetApi(11)
    private static void a(Options options, Bitmap bitmap) {
        if (11 <= VERSION.SDK_INT) {
            options.inBitmap = bitmap;
        }
    }

    @TargetApi(11)
    private static synchronized Options b() {
        Options options;
        synchronized (f.class) {
            synchronized (e) {
                options = (Options) e.poll();
            }
            if (options == null) {
                options = new Options();
                b(options);
            }
        }
        return options;
    }

    private static void a(Options options) {
        b(options);
        synchronized (e) {
            e.offer(options);
        }
    }

    @TargetApi(11)
    private static void b(Options options) {
        options.inTempStorage = null;
        options.inDither = false;
        options.inScaled = false;
        options.inSampleSize = 1;
        options.inPreferredConfig = null;
        options.inJustDecodeBounds = false;
        options.outWidth = 0;
        options.outHeight = 0;
        options.outMimeType = null;
        if (11 <= VERSION.SDK_INT) {
            options.inBitmap = null;
            options.inMutable = true;
        }
    }
}
