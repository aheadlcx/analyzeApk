package cn.xiaochuankeji.tieba.c.a;

import android.opengl.GLES20;
import java.nio.FloatBuffer;
import java.util.ArrayList;

public abstract class a {
    private volatile boolean a;
    private final ArrayList<Runnable> b = new ArrayList();
    private int c;
    private int d;

    public void a() {
        this.a = true;
        b();
    }

    protected void b() {
    }

    public boolean c() {
        return this.a;
    }

    public void a(int i, int i2) {
        if (i == 0 || i2 == 0) {
            throw new IllegalArgumentException("Invalid size: " + i + "x" + i2);
        }
        Object obj = null;
        if (!(this.c == i && this.d == i2)) {
            obj = 1;
        }
        this.c = i;
        this.d = i2;
        if (obj != null) {
            b(i, i2);
        }
    }

    protected void b(int i, int i2) {
    }

    public int d() {
        return this.c;
    }

    public int e() {
        return this.d;
    }

    public int a(int i, float[] fArr, FloatBuffer floatBuffer, float[] fArr2, FloatBuffer floatBuffer2) {
        if (!this.a) {
            throw new IllegalStateException("must be call initialize first");
        } else if (this.c == 0 || this.d == 0) {
            return -1;
        } else {
            GLES20.glViewport(0, 0, this.c, this.d);
            GLES20.glUseProgram(f());
            i();
            b(i, fArr, floatBuffer, fArr2, floatBuffer2);
            int c = c(i, fArr, floatBuffer, fArr2, floatBuffer2);
            d(i, fArr, floatBuffer, fArr2, floatBuffer2);
            GLES20.glUseProgram(0);
            return c;
        }
    }

    protected int f() {
        return 0;
    }

    protected void b(int i, float[] fArr, FloatBuffer floatBuffer, float[] fArr2, FloatBuffer floatBuffer2) {
    }

    protected int c(int i, float[] fArr, FloatBuffer floatBuffer, float[] fArr2, FloatBuffer floatBuffer2) {
        return i;
    }

    protected void d(int i, float[] fArr, FloatBuffer floatBuffer, float[] fArr2, FloatBuffer floatBuffer2) {
    }

    public void g() {
        this.a = false;
        this.c = 0;
        this.d = 0;
        h();
    }

    protected void h() {
    }

    private void i() {
        synchronized (this.b) {
            while (!this.b.isEmpty()) {
                ((Runnable) this.b.remove(0)).run();
            }
        }
    }

    protected void a(Runnable runnable) {
        synchronized (this.b) {
            this.b.add(runnable);
        }
    }

    protected void a(final int i, final float f) {
        a(new Runnable(this) {
            final /* synthetic */ a c;

            public void run() {
                GLES20.glUniform1f(i, f);
            }
        });
    }

    protected void a(final int i, final float[] fArr) {
        a(new Runnable(this) {
            final /* synthetic */ a c;

            public void run() {
                GLES20.glUniform4fv(i, 1, FloatBuffer.wrap(fArr));
            }
        });
    }
}
