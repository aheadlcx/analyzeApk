package jp.co.cyberagent.android.gpuimage;

import android.opengl.GLES20;

class ab$1 implements Runnable {
    final /* synthetic */ int a;
    final /* synthetic */ int b;
    final /* synthetic */ ab c;

    ab$1(ab abVar, int i, int i2) {
        this.c = abVar;
        this.a = i;
        this.b = i2;
    }

    public void run() {
        GLES20.glUniform1i(this.a, this.b);
    }
}
