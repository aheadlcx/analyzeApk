package jp.co.cyberagent.android.gpuimage;

import android.opengl.GLES20;

class ab$6 implements Runnable {
    final /* synthetic */ int a;
    final /* synthetic */ float[] b;
    final /* synthetic */ ab c;

    ab$6(ab abVar, int i, float[] fArr) {
        this.c = abVar;
        this.a = i;
        this.b = fArr;
    }

    public void run() {
        GLES20.glUniformMatrix4fv(this.a, 1, false, this.b, 0);
    }
}
