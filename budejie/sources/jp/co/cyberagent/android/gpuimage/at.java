package jp.co.cyberagent.android.gpuimage;

import android.opengl.GLES20;

public class at extends bu {
    private int j;
    private float k;

    public at(String str) {
        this(str, 0.5f);
    }

    public at(String str, float f) {
        super(str);
        this.k = f;
    }

    public void a() {
        super.a();
        this.j = GLES20.glGetUniformLocation(l(), "mixturePercent");
    }

    public void c_() {
        super.c_();
        a(this.k);
    }

    public void a(float f) {
        this.k = f;
        a(this.j, this.k);
    }
}
