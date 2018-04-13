package com.soundcloud.android.crop;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.net.Uri;
import android.opengl.GLES10;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import com.soundcloud.android.crop.MonitoredActivity.LifeCycleListener;
import com.umeng.analytics.pro.b;
import com.xiaomi.mipush.sdk.Constants;
import java.io.Closeable;
import java.io.IOException;

public class CropImageActivity extends MonitoredActivity {
    private final Handler a = new Handler();
    private int b;
    private int c;
    private int d;
    private int e;
    private int f;
    private Uri g;
    private Uri h;
    private boolean i;
    private int j;
    private o k;
    private CropImageView l;
    private k m;

    private class a {
        final /* synthetic */ CropImageActivity a;

        private a(CropImageActivity cropImageActivity) {
            this.a = cropImageActivity;
        }

        private void a() {
            if (this.a.k != null) {
                int i;
                boolean z;
                k kVar = new k(this.a.l);
                int width = this.a.k.getWidth();
                int height = this.a.k.getHeight();
                Rect rect = new Rect(0, 0, width, height);
                int min = (Math.min(width, height) * 4) / 5;
                if (this.a.b == 0 || this.a.c == 0) {
                    i = min;
                } else if (this.a.b > this.a.c) {
                    i = (this.a.c * min) / this.a.b;
                } else {
                    int i2 = min;
                    min = (this.a.b * min) / this.a.c;
                    i = i2;
                }
                width = (width - min) / 2;
                height = (height - i) / 2;
                RectF rectF = new RectF((float) width, (float) height, (float) (min + width), (float) (i + height));
                Matrix unrotatedMatrix = this.a.l.getUnrotatedMatrix();
                if (this.a.b == 0 || this.a.c == 0) {
                    z = false;
                } else {
                    z = true;
                }
                kVar.setup(unrotatedMatrix, rect, rectF, z);
                this.a.l.add(kVar);
            }
        }

        public void crop() {
            this.a.a.post(new h(this));
        }
    }

    public /* bridge */ /* synthetic */ void addLifeCycleListener(LifeCycleListener lifeCycleListener) {
        super.addLifeCycleListener(lifeCycleListener);
    }

    public /* bridge */ /* synthetic */ void removeLifeCycleListener(LifeCycleListener lifeCycleListener) {
        super.removeLifeCycleListener(lifeCycleListener);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        a();
        b();
        c();
        if (this.k == null) {
            finish();
        } else {
            f();
        }
    }

    @TargetApi(19)
    private void a() {
        requestWindowFeature(1);
        if (VERSION.SDK_INT >= 19) {
            getWindow().clearFlags(67108864);
        }
    }

    private void b() {
        setContentView(R.layout.crop__activity_crop);
        this.l = (CropImageView) findViewById(R.id.crop_image);
        this.l.c = this;
        this.l.setRecycler(new a(this));
        findViewById(R.id.btn_cancel).setOnClickListener(new b(this));
        findViewById(R.id.btn_done).setOnClickListener(new c(this));
    }

    private void c() {
        Closeable closeable = null;
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            this.b = extras.getInt("aspect_x");
            this.c = extras.getInt("aspect_y");
            this.d = extras.getInt("max_x");
            this.e = extras.getInt("max_y");
            this.h = (Uri) extras.getParcelable("output");
        }
        this.g = intent.getData();
        if (this.g != null) {
            this.f = i.getExifRotation(i.getFromMediaUri(this, getContentResolver(), this.g));
            try {
                this.j = a(this.g);
                closeable = getContentResolver().openInputStream(this.g);
                Options options = new Options();
                options.inSampleSize = this.j;
                this.k = new o(BitmapFactory.decodeStream(closeable, null, options), this.f);
            } catch (Throwable e) {
                n.e("Error reading image: " + e.getMessage(), e);
                a(e);
            } catch (Throwable e2) {
                n.e("OOM reading image: " + e2.getMessage(), e2);
                a(e2);
            } finally {
                i.closeSilently(closeable);
            }
        }
    }

    private int a(Uri uri) throws IOException {
        Closeable closeable = null;
        int i = 1;
        Options options = new Options();
        options.inJustDecodeBounds = true;
        try {
            closeable = getContentResolver().openInputStream(uri);
            BitmapFactory.decodeStream(closeable, null, options);
            int d = d();
            while (true) {
                if (options.outHeight / i <= d && options.outWidth / i <= d) {
                    return i;
                }
                i <<= 1;
            }
        } finally {
            i.closeSilently(closeable);
        }
    }

    private int d() {
        int e = e();
        if (e == 0) {
            return 2048;
        }
        return Math.min(e, 4096);
    }

    private int e() {
        int[] iArr = new int[1];
        GLES10.glGetIntegerv(3379, iArr, 0);
        return iArr[0];
    }

    private void f() {
        if (!isFinishing()) {
            this.l.setImageRotateBitmapResetBase(this.k, true);
            i.startBackgroundJob(this, null, getResources().getString(R.string.crop__wait), new d(this), this.a);
        }
    }

    private void g() {
        if (this.m != null && !this.i) {
            this.i = true;
            Rect scaledCropRect = this.m.getScaledCropRect((float) this.j);
            int width = scaledCropRect.width();
            int height = scaledCropRect.height();
            if (this.d > 0 && this.e > 0 && (width > this.d || height > this.e)) {
                float f = ((float) width) / ((float) height);
                if (((float) this.d) / ((float) this.e) > f) {
                    height = this.e;
                    width = (int) ((((float) this.e) * f) + 0.5f);
                } else {
                    width = this.d;
                    height = (int) ((((float) this.d) / f) + 0.5f);
                }
            }
            try {
                Bitmap a = a(scaledCropRect, width, height);
                if (a != null) {
                    this.l.setImageRotateBitmapResetBase(new o(a, this.f), true);
                    this.l.a();
                    this.l.a.clear();
                }
                a(a);
            } catch (Throwable e) {
                a(e);
                finish();
            }
        }
    }

    private void a(Bitmap bitmap) {
        if (bitmap != null) {
            i.startBackgroundJob(this, null, getResources().getString(R.string.crop__saving), new f(this, bitmap), this.a);
        } else {
            finish();
        }
    }

    private Bitmap a(Rect rect, int i, int i2) {
        Closeable openInputStream;
        Bitmap decodeRegion;
        Throwable e;
        Closeable closeable;
        Throwable th;
        Throwable th2;
        Bitmap bitmap = null;
        float f = 0.0f;
        h();
        try {
            openInputStream = getContentResolver().openInputStream(this.g);
            try {
                BitmapRegionDecoder newInstance = BitmapRegionDecoder.newInstance(openInputStream, false);
                int width = newInstance.getWidth();
                int height = newInstance.getHeight();
                if (this.f != 0) {
                    Matrix matrix = new Matrix();
                    matrix.setRotate((float) (-this.f));
                    RectF rectF = new RectF();
                    matrix.mapRect(rectF, new RectF(rect));
                    float f2 = rectF.left < 0.0f ? (float) width : 0.0f;
                    if (rectF.top < 0.0f) {
                        f = (float) height;
                    }
                    rectF.offset(f2, f);
                    rect = new Rect((int) rectF.left, (int) rectF.top, (int) rectF.right, (int) rectF.bottom);
                }
                try {
                    decodeRegion = newInstance.decodeRegion(rect, new Options());
                } catch (IllegalArgumentException e2) {
                    e = e2;
                    try {
                        throw new IllegalArgumentException("Rectangle " + rect + " is outside of the image (" + width + Constants.ACCEPT_TIME_SEPARATOR_SP + height + Constants.ACCEPT_TIME_SEPARATOR_SP + this.f + ")", e);
                    } catch (Throwable e3) {
                        closeable = openInputStream;
                        Bitmap bitmap2 = bitmap;
                        th = e3;
                        decodeRegion = bitmap2;
                        try {
                            n.e("Error cropping image: " + th.getMessage(), th);
                            a(th);
                            i.closeSilently(closeable);
                            return decodeRegion;
                        } catch (Throwable th3) {
                            e3 = th3;
                            openInputStream = closeable;
                            i.closeSilently(openInputStream);
                            throw e3;
                        }
                    } catch (Throwable e32) {
                        th2 = e32;
                        decodeRegion = bitmap;
                        th = th2;
                        try {
                            n.e("OOM cropping image: " + th.getMessage(), th);
                            a(th);
                            i.closeSilently(openInputStream);
                            return decodeRegion;
                        } catch (Throwable th4) {
                            e32 = th4;
                            i.closeSilently(openInputStream);
                            throw e32;
                        }
                    }
                }
                try {
                    if (rect.width() > i || rect.height() > i2) {
                        Matrix matrix2 = new Matrix();
                        matrix2.postScale(((float) i) / ((float) rect.width()), ((float) i2) / ((float) rect.height()));
                        decodeRegion = Bitmap.createBitmap(decodeRegion, 0, 0, decodeRegion.getWidth(), decodeRegion.getHeight(), matrix2, true);
                    }
                    i.closeSilently(openInputStream);
                } catch (Throwable th5) {
                    th2 = th5;
                    bitmap = decodeRegion;
                    e32 = th2;
                    throw new IllegalArgumentException("Rectangle " + rect + " is outside of the image (" + width + Constants.ACCEPT_TIME_SEPARATOR_SP + height + Constants.ACCEPT_TIME_SEPARATOR_SP + this.f + ")", e32);
                } catch (IOException e4) {
                    th5 = e4;
                    closeable = openInputStream;
                    n.e("Error cropping image: " + th5.getMessage(), th5);
                    a(th5);
                    i.closeSilently(closeable);
                    return decodeRegion;
                } catch (OutOfMemoryError e5) {
                    th5 = e5;
                    n.e("OOM cropping image: " + th5.getMessage(), th5);
                    a(th5);
                    i.closeSilently(openInputStream);
                    return decodeRegion;
                }
            } catch (Throwable e322) {
                closeable = openInputStream;
                th5 = e322;
                decodeRegion = null;
                n.e("Error cropping image: " + th5.getMessage(), th5);
                a(th5);
                i.closeSilently(closeable);
                return decodeRegion;
            } catch (Throwable e3222) {
                th2 = e3222;
                decodeRegion = null;
                th5 = th2;
                n.e("OOM cropping image: " + th5.getMessage(), th5);
                a(th5);
                i.closeSilently(openInputStream);
                return decodeRegion;
            }
        } catch (Throwable e32222) {
            closeable = null;
            th5 = e32222;
            decodeRegion = null;
            n.e("Error cropping image: " + th5.getMessage(), th5);
            a(th5);
            i.closeSilently(closeable);
            return decodeRegion;
        } catch (Throwable e322222) {
            openInputStream = null;
            th5 = e322222;
            decodeRegion = null;
            n.e("OOM cropping image: " + th5.getMessage(), th5);
            a(th5);
            i.closeSilently(openInputStream);
            return decodeRegion;
        } catch (Throwable th6) {
            e322222 = th6;
            openInputStream = null;
            i.closeSilently(openInputStream);
            throw e322222;
        }
        return decodeRegion;
    }

    private void h() {
        this.l.clear();
        if (this.k != null) {
            this.k.recycle();
        }
        System.gc();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void b(android.graphics.Bitmap r5) {
        /*
        r4 = this;
        r0 = r4.h;
        if (r0 == 0) goto L_0x0037;
    L_0x0004:
        r1 = 0;
        r0 = r4.getContentResolver();	 Catch:{ IOException -> 0x0045 }
        r2 = r4.h;	 Catch:{ IOException -> 0x0045 }
        r1 = r0.openOutputStream(r2);	 Catch:{ IOException -> 0x0045 }
        if (r1 == 0) goto L_0x0018;
    L_0x0011:
        r0 = android.graphics.Bitmap.CompressFormat.JPEG;	 Catch:{ IOException -> 0x0045 }
        r2 = 90;
        r5.compress(r0, r2, r1);	 Catch:{ IOException -> 0x0045 }
    L_0x0018:
        com.soundcloud.android.crop.i.closeSilently(r1);
    L_0x001b:
        r0 = r4.getContentResolver();
        r1 = r4.g;
        r0 = com.soundcloud.android.crop.i.getFromMediaUri(r4, r0, r1);
        r1 = r4.getContentResolver();
        r2 = r4.h;
        r1 = com.soundcloud.android.crop.i.getFromMediaUri(r4, r1, r2);
        com.soundcloud.android.crop.i.copyExifRotation(r0, r1);
        r0 = r4.h;
        r4.b(r0);
    L_0x0037:
        r0 = r4.a;
        r1 = new com.soundcloud.android.crop.g;
        r1.<init>(r4, r5);
        r0.post(r1);
        r4.finish();
        return;
    L_0x0045:
        r0 = move-exception;
        r4.a(r0);	 Catch:{ all -> 0x0065 }
        r2 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0065 }
        r2.<init>();	 Catch:{ all -> 0x0065 }
        r3 = "Cannot open file: ";
        r2 = r2.append(r3);	 Catch:{ all -> 0x0065 }
        r3 = r4.h;	 Catch:{ all -> 0x0065 }
        r2 = r2.append(r3);	 Catch:{ all -> 0x0065 }
        r2 = r2.toString();	 Catch:{ all -> 0x0065 }
        com.soundcloud.android.crop.n.e(r2, r0);	 Catch:{ all -> 0x0065 }
        com.soundcloud.android.crop.i.closeSilently(r1);
        goto L_0x001b;
    L_0x0065:
        r0 = move-exception;
        com.soundcloud.android.crop.i.closeSilently(r1);
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.soundcloud.android.crop.CropImageActivity.b(android.graphics.Bitmap):void");
    }

    protected void onDestroy() {
        super.onDestroy();
        if (this.k != null) {
            this.k.recycle();
        }
    }

    public boolean onSearchRequested() {
        return false;
    }

    public boolean isSaving() {
        return this.i;
    }

    private void b(Uri uri) {
        setResult(-1, new Intent().putExtra("output", uri));
    }

    private void a(Throwable th) {
        setResult(404, new Intent().putExtra(b.J, th));
    }
}
