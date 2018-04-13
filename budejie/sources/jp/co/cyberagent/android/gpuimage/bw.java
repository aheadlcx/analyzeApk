package jp.co.cyberagent.android.gpuimage;

import android.opengl.GLES20;

public class bw extends bv {
    public bw(String str, String str2, String str3, String str4) {
        super(str, str2, str3, str4);
    }

    public void a() {
        super.a();
        p();
    }

    protected void p() {
        float c = c();
        ab abVar = (ab) this.g.get(0);
        int glGetUniformLocation = GLES20.glGetUniformLocation(abVar.l(), "texelWidthOffset");
        int glGetUniformLocation2 = GLES20.glGetUniformLocation(abVar.l(), "texelHeightOffset");
        abVar.a(glGetUniformLocation, c / ((float) this.e));
        abVar.a(glGetUniformLocation2, 0.0f);
        c = b();
        abVar = (ab) this.g.get(1);
        glGetUniformLocation = GLES20.glGetUniformLocation(abVar.l(), "texelWidthOffset");
        glGetUniformLocation2 = GLES20.glGetUniformLocation(abVar.l(), "texelHeightOffset");
        abVar.a(glGetUniformLocation, 0.0f);
        abVar.a(glGetUniformLocation2, c / ((float) this.f));
    }

    public void a(int i, int i2) {
        super.a(i, i2);
        p();
    }

    public float b() {
        return 1.0f;
    }

    public float c() {
        return 1.0f;
    }
}
