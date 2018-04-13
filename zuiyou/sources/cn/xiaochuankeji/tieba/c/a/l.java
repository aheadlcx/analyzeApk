package cn.xiaochuankeji.tieba.c.a;

import android.graphics.Bitmap;
import android.opengl.GLES20;
import android.opengl.GLUtils;
import cn.xiaochuankeji.tieba.c.c;
import java.nio.FloatBuffer;

public class l extends j {
    protected int i;
    protected int j;
    protected int k;
    protected int l;
    protected int m;
    private Bitmap n;
    private float o = 0.0f;
    private float p = 1.0f;
    private boolean q;
    private int r = -1;

    public l(String str) {
        super("uniform mat4 uMVPMatrix;\nuniform mat4 uTexMatrix;\nuniform mat4 uTexMatrix2;\nattribute vec4 aPosition;\nattribute vec4 aTextureCoord;\nattribute vec4 aTextureCoord2;\nvarying vec2 vTextureCoord;\nvarying vec2 vTextureCoord2;\nvoid main() {\n    gl_Position = uMVPMatrix * aPosition;\n    vTextureCoord = (uTexMatrix * aTextureCoord).xy;\n    vTextureCoord2 = (uTexMatrix2 * aTextureCoord2).xy;\n}\n", str);
    }

    protected void b() {
        super.b();
        this.i = GLES20.glGetUniformLocation(this.a, "uTexMatrix2");
        c.b(this.d, "uTexMatrix2");
        this.j = GLES20.glGetAttribLocation(this.a, "aTextureCoord2");
        this.k = GLES20.glGetUniformLocation(this.a, "sTexture2");
        c.b(this.k, "sTexture2");
        this.l = GLES20.glGetUniformLocation(this.a, "uMin");
        c.b(this.l, "uMin");
        this.m = GLES20.glGetUniformLocation(this.a, "uMax");
        c.b(this.m, "uMax");
        a(this.o, this.p);
    }

    public void a(final Bitmap bitmap, final boolean z) {
        if (bitmap == null || !bitmap.isRecycled()) {
            a(new Runnable(this) {
                final /* synthetic */ l c;

                public void run() {
                    this.c.j();
                    this.c.n = bitmap;
                    this.c.q = z;
                    if (this.c.n != null) {
                        if (this.c.r == -1) {
                            GLES20.glActiveTexture(33987);
                            this.c.r = c.a(3553);
                        }
                        GLES20.glBindTexture(3553, this.c.r);
                        GLUtils.texImage2D(3553, 0, bitmap, 0);
                    }
                }
            });
        }
    }

    public void a(float f, float f2) {
        this.o = f;
        this.p = f2;
        if (c()) {
            a(this.l, f);
            a(this.m, f2);
        }
    }

    protected void b(int i, float[] fArr, FloatBuffer floatBuffer, float[] fArr2, FloatBuffer floatBuffer2) {
        super.b(i, fArr, floatBuffer, fArr2, floatBuffer2);
        if (this.r != -1) {
            GLES20.glActiveTexture(33987);
            GLES20.glBindTexture(3553, this.r);
            GLES20.glUniform1i(this.k, 3);
            GLES20.glUniformMatrix4fv(this.i, 1, false, c.b, 0);
            c.a("glUniformMatrix4fv");
            if (this.j > 0) {
                GLES20.glEnableVertexAttribArray(this.j);
                c.a("glEnableVertexAttribArray");
                GLES20.glVertexAttribPointer(this.j, 2, 5126, false, 0, c.d);
                c.a("glVertexAttribPointer");
            }
        }
    }

    public int c(int i, float[] fArr, FloatBuffer floatBuffer, float[] fArr2, FloatBuffer floatBuffer2) {
        return this.n == null ? i : super.c(i, fArr, floatBuffer, fArr2, floatBuffer2);
    }

    private void j() {
        if (this.n != null) {
            if (!this.n.isRecycled() && this.q) {
                this.n.recycle();
            }
            this.n = null;
        }
    }

    protected void h() {
        super.h();
        if (this.r != -1) {
            GLES20.glDeleteTextures(1, new int[]{this.r}, 0);
            this.r = -1;
        }
        j();
    }
}
