package jp.co.cyberagent.android.gpuimage;

import android.graphics.PointF;
import android.opengl.GLES20;
import java.nio.FloatBuffer;
import java.util.LinkedList;

public class ab {
    protected int a;
    protected int b;
    protected int c;
    protected int d;
    protected int e;
    protected int f;
    private final LinkedList<Runnable> g;
    private final String h;
    private final String i;
    private boolean j;

    public ab() {
        this("attribute vec4 position;\nattribute vec4 inputTextureCoordinate;\n \nvarying vec2 textureCoordinate;\n \nvoid main()\n{\n    gl_Position = position;\n    textureCoordinate = inputTextureCoordinate.xy;\n}", "varying highp vec2 textureCoordinate;\n \nuniform sampler2D inputImageTexture;\n \nvoid main()\n{\n     gl_FragColor = texture2D(inputImageTexture, textureCoordinate);\n}");
    }

    public ab(String str, String str2) {
        this.g = new LinkedList();
        this.h = str;
        this.i = str2;
    }

    public final void d() {
        a();
        this.j = true;
        c_();
    }

    public void a() {
        this.a = ca.a(this.h, this.i);
        this.b = GLES20.glGetAttribLocation(this.a, "position");
        this.c = GLES20.glGetUniformLocation(this.a, "inputImageTexture");
        this.d = GLES20.glGetAttribLocation(this.a, "inputTextureCoordinate");
        this.j = true;
    }

    public void c_() {
    }

    public final void e() {
        this.j = false;
        GLES20.glDeleteProgram(this.a);
        f();
    }

    public void f() {
    }

    public void a(int i, int i2) {
        this.e = i;
        this.f = i2;
    }

    public void a(int i, FloatBuffer floatBuffer, FloatBuffer floatBuffer2) {
        GLES20.glUseProgram(this.a);
        h();
        if (this.j) {
            floatBuffer.position(0);
            GLES20.glVertexAttribPointer(this.b, 2, 5126, false, 0, floatBuffer);
            GLES20.glEnableVertexAttribArray(this.b);
            floatBuffer2.position(0);
            GLES20.glVertexAttribPointer(this.d, 2, 5126, false, 0, floatBuffer2);
            GLES20.glEnableVertexAttribArray(this.d);
            if (i != -1) {
                GLES20.glActiveTexture(33984);
                GLES20.glBindTexture(3553, i);
                GLES20.glUniform1i(this.c, 0);
            }
            g();
            GLES20.glDrawArrays(5, 0, 4);
            GLES20.glDisableVertexAttribArray(this.b);
            GLES20.glDisableVertexAttribArray(this.d);
            GLES20.glBindTexture(3553, 0);
        }
    }

    protected void g() {
    }

    protected void h() {
        while (!this.g.isEmpty()) {
            ((Runnable) this.g.removeFirst()).run();
        }
    }

    public boolean i() {
        return this.j;
    }

    public int j() {
        return this.e;
    }

    public int k() {
        return this.f;
    }

    public int l() {
        return this.a;
    }

    protected void b(int i, int i2) {
        a(new ab$1(this, i, i2));
    }

    protected void a(int i, float f) {
        a(new ab$2(this, i, f));
    }

    protected void a(int i, float[] fArr) {
        a(new ab$3(this, i, fArr));
    }

    protected void a(int i, PointF pointF) {
        a(new ab$4(this, pointF, i));
    }

    protected void b(int i, float[] fArr) {
        a(new ab$5(this, i, fArr));
    }

    protected void c(int i, float[] fArr) {
        a(new ab$6(this, i, fArr));
    }

    protected void a(Runnable runnable) {
        synchronized (this.g) {
            this.g.addLast(runnable);
        }
    }
}
