package jp.co.cyberagent.android.gpuimage;

import android.opengl.GLES20;

class ab$2 implements Runnable {
    final /* synthetic */ int a;
    final /* synthetic */ float b;
    final /* synthetic */ ab c;

    ab$2(ab abVar, int i, float f) {
        this.c = abVar;
        this.a = i;
        this.b = f;
    }

    public void run() {
        GLES20.glUniform1f(this.a, this.b);
    }
}
