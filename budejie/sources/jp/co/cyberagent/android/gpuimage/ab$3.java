package jp.co.cyberagent.android.gpuimage;

import android.opengl.GLES20;
import java.nio.FloatBuffer;

class ab$3 implements Runnable {
    final /* synthetic */ int a;
    final /* synthetic */ float[] b;
    final /* synthetic */ ab c;

    ab$3(ab abVar, int i, float[] fArr) {
        this.c = abVar;
        this.a = i;
        this.b = fArr;
    }

    public void run() {
        GLES20.glUniform3fv(this.a, 1, FloatBuffer.wrap(this.b));
    }
}
