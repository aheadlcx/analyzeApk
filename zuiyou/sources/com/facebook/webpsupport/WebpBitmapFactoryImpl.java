package com.facebook.webpsupport;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Rect;
import android.os.Build.VERSION;
import android.util.TypedValue;
import com.facebook.common.g.b;
import com.facebook.common.g.b.a;
import com.facebook.common.g.c;
import com.facebook.common.internal.d;
import java.io.BufferedInputStream;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.annotation.Nullable;

@d
public class WebpBitmapFactoryImpl implements b {
    public static final boolean a = (VERSION.SDK_INT >= 11);
    private static a b;
    private static com.facebook.common.g.a c;

    @d
    private static native Bitmap nativeDecodeByteArray(byte[] bArr, int i, int i2, Options options, float f, byte[] bArr2);

    @d
    private static native Bitmap nativeDecodeStream(InputStream inputStream, Options options, float f, byte[] bArr);

    @d
    private static native long nativeSeek(FileDescriptor fileDescriptor, long j, boolean z);

    public void a(com.facebook.common.g.a aVar) {
        c = aVar;
    }

    private static InputStream a(InputStream inputStream) {
        if (inputStream.markSupported()) {
            return inputStream;
        }
        return new BufferedInputStream(inputStream, 20);
    }

    private static byte[] a(InputStream inputStream, Options options) {
        byte[] bArr;
        inputStream.mark(20);
        if (options == null || options.inTempStorage == null || options.inTempStorage.length < 20) {
            bArr = new byte[20];
        } else {
            bArr = options.inTempStorage;
        }
        try {
            inputStream.read(bArr, 0, 20);
            inputStream.reset();
            return bArr;
        } catch (IOException e) {
            return null;
        }
    }

    private static void a(Bitmap bitmap, Options options) {
        if (bitmap != null && options != null) {
            int i = options.inDensity;
            if (i != 0) {
                bitmap.setDensity(i);
                int i2 = options.inTargetDensity;
                if (i2 != 0 && i != i2 && i != options.inScreenDensity && options.inScaled) {
                    bitmap.setDensity(i2);
                }
            } else if (a && options.inBitmap != null) {
                bitmap.setDensity(160);
            }
        }
    }

    public void a(a aVar) {
        b = aVar;
    }

    public Bitmap a(FileDescriptor fileDescriptor, Rect rect, Options options) {
        return hookDecodeFileDescriptor(fileDescriptor, rect, options);
    }

    @d
    public static Bitmap hookDecodeByteArray(byte[] bArr, int i, int i2, Options options) {
        Bitmap nativeDecodeByteArray;
        com.facebook.imagepipeline.nativecode.b.a();
        if (c.a && c.b(bArr, i, i2)) {
            nativeDecodeByteArray = nativeDecodeByteArray(bArr, i, i2, options, getScaleFromOptions(options), getInTempStorageFromOptions(options));
            if (nativeDecodeByteArray == null) {
                a("webp_direct_decode_array");
            }
            b(nativeDecodeByteArray, options);
        } else {
            nativeDecodeByteArray = originalDecodeByteArray(bArr, i, i2, options);
            if (nativeDecodeByteArray == null) {
                a("webp_direct_decode_array_failed_on_no_webp");
            }
        }
        return nativeDecodeByteArray;
    }

    @d
    private static Bitmap originalDecodeByteArray(byte[] bArr, int i, int i2, Options options) {
        return BitmapFactory.decodeByteArray(bArr, i, i2, options);
    }

    @d
    public static Bitmap hookDecodeByteArray(byte[] bArr, int i, int i2) {
        return hookDecodeByteArray(bArr, i, i2, null);
    }

    @d
    private static Bitmap originalDecodeByteArray(byte[] bArr, int i, int i2) {
        return BitmapFactory.decodeByteArray(bArr, i, i2);
    }

    @d
    public static Bitmap hookDecodeStream(InputStream inputStream, Rect rect, Options options) {
        Bitmap nativeDecodeStream;
        com.facebook.imagepipeline.nativecode.b.a();
        InputStream a = a(inputStream);
        byte[] a2 = a(a, options);
        if (c.a && c.b(a2, 0, 20)) {
            nativeDecodeStream = nativeDecodeStream(a, options, getScaleFromOptions(options), getInTempStorageFromOptions(options));
            if (nativeDecodeStream == null) {
                a("webp_direct_decode_stream");
            }
            b(nativeDecodeStream, options);
            setPaddingDefaultValues(rect);
        } else {
            nativeDecodeStream = originalDecodeStream(a, rect, options);
            if (nativeDecodeStream == null) {
                a("webp_direct_decode_stream_failed_on_no_webp");
            }
        }
        return nativeDecodeStream;
    }

    @d
    private static Bitmap originalDecodeStream(InputStream inputStream, Rect rect, Options options) {
        return BitmapFactory.decodeStream(inputStream, rect, options);
    }

    @d
    public static Bitmap hookDecodeStream(InputStream inputStream) {
        return hookDecodeStream(inputStream, null, null);
    }

    @d
    private static Bitmap originalDecodeStream(InputStream inputStream) {
        return BitmapFactory.decodeStream(inputStream);
    }

    @d
    public static Bitmap hookDecodeFile(String str, Options options) {
        try {
            Throwable th;
            InputStream fileInputStream = new FileInputStream(str);
            Throwable th2 = null;
            try {
                Bitmap hookDecodeStream = hookDecodeStream(fileInputStream, null, options);
                if (fileInputStream == null) {
                    return hookDecodeStream;
                }
                if (null != null) {
                    try {
                        fileInputStream.close();
                        return hookDecodeStream;
                    } catch (Throwable th3) {
                        try {
                            th2.addSuppressed(th3);
                            return hookDecodeStream;
                        } catch (Exception e) {
                            return hookDecodeStream;
                        }
                    }
                }
                fileInputStream.close();
                return hookDecodeStream;
            } catch (Throwable th4) {
                th = th4;
            }
            if (fileInputStream != null) {
                if (th2 != null) {
                    try {
                        fileInputStream.close();
                    } catch (Throwable th5) {
                        th2.addSuppressed(th5);
                    }
                } else {
                    fileInputStream.close();
                }
            }
            throw th;
            throw th;
        } catch (Exception e2) {
            return null;
        }
    }

    @d
    public static Bitmap hookDecodeFile(String str) {
        return hookDecodeFile(str, null);
    }

    @d
    public static Bitmap hookDecodeResourceStream(Resources resources, TypedValue typedValue, InputStream inputStream, Rect rect, Options options) {
        if (options == null) {
            options = new Options();
        }
        if (options.inDensity == 0 && typedValue != null) {
            int i = typedValue.density;
            if (i == 0) {
                options.inDensity = 160;
            } else if (i != 65535) {
                options.inDensity = i;
            }
        }
        if (options.inTargetDensity == 0 && resources != null) {
            options.inTargetDensity = resources.getDisplayMetrics().densityDpi;
        }
        return hookDecodeStream(inputStream, rect, options);
    }

    @d
    private static Bitmap originalDecodeResourceStream(Resources resources, TypedValue typedValue, InputStream inputStream, Rect rect, Options options) {
        return BitmapFactory.decodeResourceStream(resources, typedValue, inputStream, rect, options);
    }

    @d
    public static Bitmap hookDecodeResource(Resources resources, int i, Options options) {
        TypedValue typedValue = new TypedValue();
        Bitmap hookDecodeResourceStream;
        try {
            Throwable th;
            InputStream openRawResource = resources.openRawResource(i, typedValue);
            Throwable th2 = null;
            try {
                hookDecodeResourceStream = hookDecodeResourceStream(resources, typedValue, openRawResource, null, options);
                if (openRawResource != null) {
                    if (null != null) {
                        try {
                            openRawResource.close();
                        } catch (Throwable th3) {
                            try {
                                th2.addSuppressed(th3);
                            } catch (Exception e) {
                            }
                        }
                    } else {
                        openRawResource.close();
                    }
                }
                if (!a || hookDecodeResourceStream != null || options == null || options.inBitmap == null) {
                    return hookDecodeResourceStream;
                }
                throw new IllegalArgumentException("Problem decoding into existing bitmap");
            } catch (Throwable th4) {
                th = th4;
            }
            throw th;
            if (openRawResource != null) {
                if (th2 != null) {
                    try {
                        openRawResource.close();
                    } catch (Throwable th5) {
                        th2.addSuppressed(th5);
                    }
                } else {
                    openRawResource.close();
                }
            }
            throw th;
        } catch (Exception e2) {
            hookDecodeResourceStream = null;
        }
    }

    @d
    private static Bitmap originalDecodeResource(Resources resources, int i, Options options) {
        return BitmapFactory.decodeResource(resources, i, options);
    }

    @d
    public static Bitmap hookDecodeResource(Resources resources, int i) {
        return hookDecodeResource(resources, i, null);
    }

    @d
    private static Bitmap originalDecodeResource(Resources resources, int i) {
        return BitmapFactory.decodeResource(resources, i);
    }

    @d
    private static boolean setOutDimensions(Options options, int i, int i2) {
        if (options == null || !options.inJustDecodeBounds) {
            return false;
        }
        options.outWidth = i;
        options.outHeight = i2;
        return true;
    }

    @d
    private static void setPaddingDefaultValues(@Nullable Rect rect) {
        if (rect != null) {
            rect.top = -1;
            rect.left = -1;
            rect.bottom = -1;
            rect.right = -1;
        }
    }

    @d
    private static void setBitmapSize(@Nullable Options options, int i, int i2) {
        if (options != null) {
            options.outWidth = i;
            options.outHeight = i2;
        }
    }

    @d
    private static Bitmap originalDecodeFile(String str, Options options) {
        return BitmapFactory.decodeFile(str, options);
    }

    @d
    private static Bitmap originalDecodeFile(String str) {
        return BitmapFactory.decodeFile(str);
    }

    @d
    public static Bitmap hookDecodeFileDescriptor(FileDescriptor fileDescriptor, Rect rect, Options options) {
        com.facebook.imagepipeline.nativecode.b.a();
        long nativeSeek = nativeSeek(fileDescriptor, 0, false);
        Bitmap nativeDecodeStream;
        if (nativeSeek != -1) {
            InputStream a = a(new FileInputStream(fileDescriptor));
            try {
                byte[] a2 = a(a, options);
                if (c.a && c.b(a2, 0, 20)) {
                    nativeDecodeStream = nativeDecodeStream(a, options, getScaleFromOptions(options), getInTempStorageFromOptions(options));
                    if (nativeDecodeStream == null) {
                        a("webp_direct_decode_fd");
                    }
                    setPaddingDefaultValues(rect);
                    b(nativeDecodeStream, options);
                } else {
                    nativeSeek(fileDescriptor, nativeSeek, true);
                    nativeDecodeStream = originalDecodeFileDescriptor(fileDescriptor, rect, options);
                    if (nativeDecodeStream == null) {
                        a("webp_direct_decode_fd_failed_on_no_webp");
                    }
                }
                try {
                    a.close();
                    return nativeDecodeStream;
                } catch (Throwable th) {
                    return nativeDecodeStream;
                }
            } catch (Throwable th2) {
            }
        } else {
            nativeDecodeStream = hookDecodeStream(new FileInputStream(fileDescriptor), rect, options);
            setPaddingDefaultValues(rect);
            return nativeDecodeStream;
        }
    }

    @d
    private static Bitmap originalDecodeFileDescriptor(FileDescriptor fileDescriptor, Rect rect, Options options) {
        return BitmapFactory.decodeFileDescriptor(fileDescriptor, rect, options);
    }

    @d
    public static Bitmap hookDecodeFileDescriptor(FileDescriptor fileDescriptor) {
        return hookDecodeFileDescriptor(fileDescriptor, null, null);
    }

    @d
    private static Bitmap originalDecodeFileDescriptor(FileDescriptor fileDescriptor) {
        return BitmapFactory.decodeFileDescriptor(fileDescriptor);
    }

    private static void b(Bitmap bitmap, Options options) {
        a(bitmap, options);
        if (options != null) {
            options.outMimeType = "image/webp";
        }
    }

    @d
    @SuppressLint({"NewApi"})
    private static boolean shouldPremultiply(Options options) {
        if (VERSION.SDK_INT < 19 || options == null) {
            return true;
        }
        return options.inPremultiplied;
    }

    @d
    private static Bitmap createBitmap(int i, int i2, Options options) {
        if (!a || options == null || options.inBitmap == null || !options.inBitmap.isMutable()) {
            return c.a(i, i2, Config.ARGB_8888);
        }
        return options.inBitmap;
    }

    @d
    private static byte[] getInTempStorageFromOptions(@Nullable Options options) {
        if (options == null || options.inTempStorage == null) {
            return new byte[8192];
        }
        return options.inTempStorage;
    }

    @d
    private static float getScaleFromOptions(Options options) {
        float f = 1.0f;
        if (options == null) {
            return 1.0f;
        }
        int i = options.inSampleSize;
        if (i > 1) {
            f = 1.0f / ((float) i);
        }
        if (!options.inScaled) {
            return f;
        }
        i = options.inDensity;
        int i2 = options.inTargetDensity;
        int i3 = options.inScreenDensity;
        if (i == 0 || i2 == 0 || i == i3) {
            return f;
        }
        return ((float) i2) / ((float) i);
    }

    private static void a(String str) {
        if (b != null) {
            b.a(str, "decoding_failure");
        }
    }
}
