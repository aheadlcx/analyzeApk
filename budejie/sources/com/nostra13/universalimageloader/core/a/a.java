package com.nostra13.universalimageloader.core.a;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Matrix;
import com.nostra13.universalimageloader.b.d;
import com.nostra13.universalimageloader.b.e;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.c;
import com.nostra13.universalimageloader.core.download.ImageDownloader.Scheme;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;

public class a implements b {
    protected final boolean a;

    public a(boolean z) {
        this.a = z;
    }

    public Bitmap a(c cVar) throws IOException {
        Closeable b = b(cVar);
        try {
            a$b a = a((InputStream) b, cVar);
            b = b(b, cVar);
            Bitmap decodeStream = BitmapFactory.decodeStream(b, null, a(a.a, cVar));
            if (decodeStream != null) {
                return a(decodeStream, cVar, a.b.a, a.b.b);
            }
            e.d("Image can't be decoded [%s]", new Object[]{cVar.a()});
            return decodeStream;
        } finally {
            d.a(b);
        }
    }

    protected InputStream b(c cVar) throws IOException {
        return cVar.f().getStream(cVar.b(), cVar.g());
    }

    protected a$b a(InputStream inputStream, c cVar) throws IOException {
        a$a a;
        Options options = new Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(inputStream, null, options);
        String b = cVar.b();
        if (cVar.h() && a(b, options.outMimeType)) {
            a = a(b);
        } else {
            a = new a$a();
        }
        return new a$b(new c(options.outWidth, options.outHeight, a.a), a);
    }

    private boolean a(String str, String str2) {
        return "image/jpeg".equalsIgnoreCase(str2) && Scheme.ofUri(str) == Scheme.FILE;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected com.nostra13.universalimageloader.core.a.a$a a(java.lang.String r6) {
        /*
        r5 = this;
        r1 = 0;
        r0 = 1;
        r2 = new android.media.ExifInterface;	 Catch:{ IOException -> 0x002a }
        r3 = com.nostra13.universalimageloader.core.download.ImageDownloader.Scheme.FILE;	 Catch:{ IOException -> 0x002a }
        r3 = r3.crop(r6);	 Catch:{ IOException -> 0x002a }
        r2.<init>(r3);	 Catch:{ IOException -> 0x002a }
        r3 = "Orientation";
        r4 = 1;
        r2 = r2.getAttributeInt(r3, r4);	 Catch:{ IOException -> 0x002a }
        switch(r2) {
            case 1: goto L_0x0017;
            case 2: goto L_0x0018;
            case 3: goto L_0x0022;
            case 4: goto L_0x0023;
            case 5: goto L_0x0027;
            case 6: goto L_0x001e;
            case 7: goto L_0x001f;
            case 8: goto L_0x0026;
            default: goto L_0x0017;
        };
    L_0x0017:
        r0 = r1;
    L_0x0018:
        r2 = new com.nostra13.universalimageloader.core.a.a$a;
        r2.<init>(r1, r0);
        return r2;
    L_0x001e:
        r0 = r1;
    L_0x001f:
        r1 = 90;
        goto L_0x0018;
    L_0x0022:
        r0 = r1;
    L_0x0023:
        r1 = 180; // 0xb4 float:2.52E-43 double:8.9E-322;
        goto L_0x0018;
    L_0x0026:
        r0 = r1;
    L_0x0027:
        r1 = 270; // 0x10e float:3.78E-43 double:1.334E-321;
        goto L_0x0018;
    L_0x002a:
        r2 = move-exception;
        r2 = "Can't read EXIF tags from file [%s]";
        r0 = new java.lang.Object[r0];
        r0[r1] = r6;
        com.nostra13.universalimageloader.b.e.c(r2, r0);
        goto L_0x0017;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.nostra13.universalimageloader.core.a.a.a(java.lang.String):com.nostra13.universalimageloader.core.a.a$a");
    }

    protected Options a(c cVar, c cVar2) {
        int i;
        ImageScaleType d = cVar2.d();
        if (d == ImageScaleType.NONE) {
            i = 1;
        } else if (d == ImageScaleType.NONE_SAFE) {
            i = com.nostra13.universalimageloader.b.c.a(cVar);
        } else {
            boolean z;
            c c = cVar2.c();
            if (d == ImageScaleType.IN_SAMPLE_POWER_OF_2) {
                z = true;
            } else {
                z = false;
            }
            i = com.nostra13.universalimageloader.b.c.a(cVar, c, cVar2.e(), z);
        }
        if (i > 1 && this.a) {
            e.a("Subsample original image (%1$s) to %2$s (scale = %3$d) [%4$s]", new Object[]{cVar, cVar.a(i), Integer.valueOf(i), cVar2.a()});
        }
        Options i2 = cVar2.i();
        i2.inSampleSize = i;
        return i2;
    }

    protected InputStream b(InputStream inputStream, c cVar) throws IOException {
        try {
            inputStream.reset();
            return inputStream;
        } catch (IOException e) {
            d.a((Closeable) inputStream);
            return b(cVar);
        }
    }

    protected Bitmap a(Bitmap bitmap, c cVar, int i, boolean z) {
        Matrix matrix = new Matrix();
        ImageScaleType d = cVar.d();
        if (d == ImageScaleType.EXACTLY || d == ImageScaleType.EXACTLY_STRETCHED) {
            float b = com.nostra13.universalimageloader.b.c.b(new c(bitmap.getWidth(), bitmap.getHeight(), i), cVar.c(), cVar.e(), d == ImageScaleType.EXACTLY_STRETCHED);
            if (Float.compare(b, 1.0f) != 0) {
                matrix.setScale(b, b);
                if (this.a) {
                    e.a("Scale subsampled image (%1$s) to %2$s (scale = %3$.5f) [%4$s]", new Object[]{r2, r2.a(b), Float.valueOf(b), cVar.a()});
                }
            }
        }
        if (z) {
            matrix.postScale(-1.0f, 1.0f);
            if (this.a) {
                e.a("Flip image horizontally [%s]", new Object[]{cVar.a()});
            }
        }
        if (i != 0) {
            matrix.postRotate((float) i);
            if (this.a) {
                e.a("Rotate image on %1$d° [%2$s]", new Object[]{Integer.valueOf(i), cVar.a()});
            }
        }
        Bitmap createBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        if (createBitmap != bitmap) {
            bitmap.recycle();
        }
        return createBitmap;
    }
}
