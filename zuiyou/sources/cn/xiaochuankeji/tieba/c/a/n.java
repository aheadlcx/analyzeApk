package cn.xiaochuankeji.tieba.c.a;

import android.opengl.GLES20;

public class n extends m {
    public n(a aVar, a aVar2) {
        super(aVar, aVar2);
    }

    protected void b(int i, int i2) {
        super.b(i, i2);
        int f = this.a.f();
        int glGetUniformLocation = GLES20.glGetUniformLocation(f, "texelWidthOffset");
        f = GLES20.glGetUniformLocation(f, "texelHeightOffset");
        this.a.a(glGetUniformLocation, 1.0f / ((float) i));
        this.a.a(f, 0.0f);
        f = this.b.f();
        glGetUniformLocation = GLES20.glGetUniformLocation(f, "texelWidthOffset");
        f = GLES20.glGetUniformLocation(f, "texelHeightOffset");
        this.b.a(glGetUniformLocation, 0.0f);
        this.b.a(f, 1.0f / ((float) i2));
    }
}
