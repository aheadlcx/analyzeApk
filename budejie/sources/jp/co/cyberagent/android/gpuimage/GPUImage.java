package jp.co.cyberagent.android.gpuimage;

import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.net.Uri;
import android.opengl.GLSurfaceView;
import android.view.WindowManager;
import java.io.File;

public class GPUImage {
    private final Context a;
    private final be b;
    private GLSurfaceView c;
    private ab d;
    private Bitmap e;
    private GPUImage$ScaleType f = GPUImage$ScaleType.CENTER_CROP;

    public GPUImage(Context context) {
        if (a(context)) {
            this.a = context;
            this.d = new ab();
            this.b = new be(this.d);
            return;
        }
        throw new IllegalStateException("OpenGL ES 2.0 is not supported on this phone.");
    }

    private boolean a(Context context) {
        return ((ActivityManager) context.getSystemService("activity")).getDeviceConfigurationInfo().reqGlEsVersion >= 131072;
    }

    public void a(GLSurfaceView gLSurfaceView) {
        this.c = gLSurfaceView;
        this.c.setEGLContextClientVersion(2);
        this.c.setEGLConfigChooser(8, 8, 8, 8, 16, 0);
        this.c.getHolder().setFormat(1);
        this.c.setRenderer(this.b);
        this.c.setRenderMode(0);
        this.c.requestRender();
    }

    public void a() {
        if (this.c != null) {
            this.c.requestRender();
        }
    }

    public void a(ab abVar) {
        this.d = abVar;
        this.b.a(this.d);
        a();
    }

    public void a(Bitmap bitmap) {
        this.e = bitmap;
        this.b.a(bitmap, false);
        a();
    }

    public void a(GPUImage$ScaleType gPUImage$ScaleType) {
        this.f = gPUImage$ScaleType;
        this.b.a(gPUImage$ScaleType);
        this.b.a();
        this.e = null;
        a();
    }

    public void a(Rotation rotation) {
        this.b.a(rotation);
    }

    public void b() {
        this.b.a();
        this.e = null;
        a();
    }

    public void a(Uri uri) {
        new GPUImage$c(this, this, uri).execute(new Void[0]);
    }

    public void a(File file) {
        new GPUImage$a(this, this, file).execute(new Void[0]);
    }

    public Bitmap c() {
        return b(this.e);
    }

    public Bitmap b(Bitmap bitmap) {
        int i;
        if (this.c != null) {
            this.b.a();
            this.b.a(new GPUImage$1(this));
            synchronized (this.d) {
                a();
                try {
                    this.d.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        be beVar = new be(this.d);
        beVar.a(Rotation.NORMAL, this.b.d(), this.b.e());
        beVar.a(this.f);
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int i2 = height / width;
        if (height % width == 0 || i2 == 0) {
            i = i2;
        } else {
            i = i2 + 1;
        }
        Bitmap createBitmap;
        if (i > 2) {
            Bitmap createBitmap2 = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Config.ARGB_8888);
            Canvas canvas = new Canvas(createBitmap2);
            for (int i3 = 0; i3 < i / 2; i3++) {
                int i4 = width * 2;
                if ((i / 2) - 1 == i3) {
                    i4 = height - ((width * 2) * i3);
                }
                createBitmap = Bitmap.createBitmap(bitmap, 0, (width * 2) * i3, width, i4, null, false);
                cb cbVar = new cb(createBitmap.getWidth(), createBitmap.getHeight());
                cbVar.a(beVar);
                beVar.a(createBitmap, false);
                createBitmap = cbVar.a();
                if (createBitmap != null) {
                    canvas.drawBitmap(createBitmap, 0.0f, (float) ((width * 2) * i3), null);
                    createBitmap.recycle();
                }
                this.d.e();
                beVar.a();
                cbVar.b();
                this.b.a(this.d);
                a();
            }
            return createBitmap2;
        }
        cbVar = new cb(bitmap.getWidth(), bitmap.getHeight());
        cbVar.a(beVar);
        beVar.a(bitmap, false);
        createBitmap = cbVar.a();
        this.d.e();
        beVar.a();
        cbVar.b();
        this.b.a(this.d);
        if (this.e != null) {
            this.b.a(this.e, false);
        }
        a();
        return createBitmap;
    }

    private int d() {
        if (this.b != null && this.b.b() != 0) {
            return this.b.b();
        }
        if (this.e != null) {
            return this.e.getWidth();
        }
        return ((WindowManager) this.a.getSystemService("window")).getDefaultDisplay().getWidth();
    }

    private int e() {
        if (this.b != null && this.b.c() != 0) {
            return this.b.c();
        }
        if (this.e != null) {
            return this.e.getHeight();
        }
        return ((WindowManager) this.a.getSystemService("window")).getDefaultDisplay().getHeight();
    }
}
