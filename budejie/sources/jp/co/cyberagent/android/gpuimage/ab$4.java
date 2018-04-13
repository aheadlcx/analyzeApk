package jp.co.cyberagent.android.gpuimage;

import android.graphics.PointF;
import android.opengl.GLES20;

class ab$4 implements Runnable {
    final /* synthetic */ PointF a;
    final /* synthetic */ int b;
    final /* synthetic */ ab c;

    ab$4(ab abVar, PointF pointF, int i) {
        this.c = abVar;
        this.a = pointF;
        this.b = i;
    }

    public void run() {
        GLES20.glUniform2fv(this.b, 1, new float[]{this.a.x, this.a.y}, 0);
    }
}
