package jp.co.cyberagent.android.gpuimage;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.hardware.Camera.PreviewCallback;
import android.hardware.Camera.Size;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView.Renderer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.LinkedList;
import java.util.Queue;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
import jp.co.cyberagent.android.gpuimage.a.a;

@TargetApi(11)
public class be implements PreviewCallback, Renderer {
    static final float[] a = new float[]{-1.0f, -1.0f, 1.0f, -1.0f, -1.0f, 1.0f, 1.0f, 1.0f};
    public final Object b = new Object();
    private ab c;
    private int d = -1;
    private SurfaceTexture e = null;
    private final FloatBuffer f;
    private final FloatBuffer g;
    private IntBuffer h;
    private int i;
    private int j;
    private int k;
    private int l;
    private int m;
    private final Queue<Runnable> n;
    private final Queue<Runnable> o;
    private Rotation p;
    private boolean q;
    private boolean r;
    private GPUImage$ScaleType s = GPUImage$ScaleType.CENTER_CROP;

    public be(ab abVar) {
        this.c = abVar;
        this.n = new LinkedList();
        this.o = new LinkedList();
        this.f = ByteBuffer.allocateDirect(a.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        this.f.put(a).position(0);
        this.g = ByteBuffer.allocateDirect(a.a.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        a(Rotation.NORMAL, false, false);
    }

    public void onSurfaceCreated(GL10 gl10, EGLConfig eGLConfig) {
        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        GLES20.glDisable(2929);
        this.c.d();
    }

    public void onSurfaceChanged(GL10 gl10, int i, int i2) {
        this.i = i;
        this.j = i2;
        GLES20.glViewport(0, 0, i, i2);
        GLES20.glUseProgram(this.c.l());
        this.c.a(i, i2);
        f();
        synchronized (this.b) {
            this.b.notifyAll();
        }
    }

    public void onDrawFrame(GL10 gl10) {
        GLES20.glClear(16640);
        a(this.n);
        this.c.a(this.d, this.f, this.g);
        a(this.o);
        if (this.e != null) {
            this.e.updateTexImage();
        }
    }

    private void a(Queue<Runnable> queue) {
        synchronized (queue) {
            while (!queue.isEmpty()) {
                ((Runnable) queue.poll()).run();
            }
        }
    }

    public void onPreviewFrame(final byte[] bArr, final Camera camera) {
        final Size previewSize = camera.getParameters().getPreviewSize();
        if (this.h == null) {
            this.h = IntBuffer.allocate(previewSize.width * previewSize.height);
        }
        if (this.n.isEmpty()) {
            a(new Runnable(this) {
                final /* synthetic */ be d;

                public void run() {
                    GPUImageNativeLibrary.YUVtoRBGA(bArr, previewSize.width, previewSize.height, this.d.h.array());
                    this.d.d = ca.a(this.d.h, previewSize, this.d.d);
                    camera.addCallbackBuffer(bArr);
                    if (this.d.k != previewSize.width) {
                        this.d.k = previewSize.width;
                        this.d.l = previewSize.height;
                        this.d.f();
                    }
                }
            });
        }
    }

    public void a(final ab abVar) {
        a(new Runnable(this) {
            final /* synthetic */ be b;

            public void run() {
                ab e = this.b.c;
                this.b.c = abVar;
                if (e != null) {
                    e.e();
                }
                this.b.c.d();
                GLES20.glUseProgram(this.b.c.l());
                this.b.c.a(this.b.i, this.b.j);
            }
        });
    }

    public void a() {
        a(new Runnable(this) {
            final /* synthetic */ be a;

            {
                this.a = r1;
            }

            public void run() {
                GLES20.glDeleteTextures(1, new int[]{this.a.d}, 0);
                this.a.d = -1;
            }
        });
    }

    public void a(final Bitmap bitmap, final boolean z) {
        if (bitmap != null) {
            a(new Runnable(this) {
                final /* synthetic */ be c;

                public void run() {
                    Bitmap createBitmap;
                    if (bitmap.getWidth() % 2 == 1) {
                        createBitmap = Bitmap.createBitmap(bitmap.getWidth() + 1, bitmap.getHeight(), Config.ARGB_8888);
                        Canvas canvas = new Canvas(createBitmap);
                        canvas.drawARGB(0, 0, 0, 0);
                        canvas.drawBitmap(bitmap, 0.0f, 0.0f, null);
                        this.c.m = 1;
                    } else {
                        this.c.m = 0;
                        createBitmap = null;
                    }
                    this.c.d = ca.a(createBitmap != null ? createBitmap : bitmap, this.c.d, z);
                    if (createBitmap != null) {
                        createBitmap.recycle();
                    }
                    this.c.k = bitmap.getWidth();
                    this.c.l = bitmap.getHeight();
                    this.c.f();
                }
            });
        }
    }

    public void a(GPUImage$ScaleType gPUImage$ScaleType) {
        this.s = gPUImage$ScaleType;
    }

    protected int b() {
        return this.i;
    }

    protected int c() {
        return this.j;
    }

    private void f() {
        float[] fArr;
        float f = (float) this.i;
        float f2 = (float) this.j;
        if (this.p == Rotation.ROTATION_270 || this.p == Rotation.ROTATION_90) {
            f = (float) this.j;
            f2 = (float) this.i;
        }
        float max = Math.max(f / ((float) this.k), f2 / ((float) this.l));
        float round = ((float) Math.round(((float) this.k) * max)) / f;
        float round2 = ((float) Math.round(max * ((float) this.l))) / f2;
        float[] fArr2 = a;
        float[] a = a.a(this.p, this.q, this.r);
        if (this.s == GPUImage$ScaleType.CENTER_CROP) {
            round = (1.0f - (1.0f / round)) / 2.0f;
            round2 = (1.0f - (1.0f / round2)) / 2.0f;
            fArr = new float[]{a(a[0], round), a(a[1], round2), a(a[2], round), a(a[3], round2), a(a[4], round), a(a[5], round2), a(a[6], round), a(a[7], round2)};
            a = fArr2;
        } else {
            float[] fArr3 = a;
            a = new float[]{a[0] / round2, a[1] / round, a[2] / round2, a[3] / round, a[4] / round2, a[5] / round, a[6] / round2, a[7] / round};
            fArr = fArr3;
        }
        this.f.clear();
        this.f.put(a).position(0);
        this.g.clear();
        this.g.put(fArr).position(0);
    }

    private float a(float f, float f2) {
        return f == 0.0f ? f2 : 1.0f - f2;
    }

    public void a(Rotation rotation) {
        this.p = rotation;
        f();
    }

    public void a(Rotation rotation, boolean z, boolean z2) {
        this.q = z;
        this.r = z2;
        a(rotation);
    }

    public boolean d() {
        return this.q;
    }

    public boolean e() {
        return this.r;
    }

    protected void a(Runnable runnable) {
        synchronized (this.n) {
            this.n.add(runnable);
        }
    }
}
