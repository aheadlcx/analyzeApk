package cn.xiaochuankeji.tieba.ui.videomaker.roundedvideo;

import android.graphics.Point;
import android.graphics.RectF;
import android.opengl.GLES20;
import cn.xiaochuankeji.tieba.c.c;
import cn.xiaochuankeji.tieba.ui.videomaker.roundedvideo.a.a;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

public class b extends cn.xiaochuankeji.tieba.c.b {
    private final RectF a = new RectF();
    private float[] b;
    private short[] c;
    private FloatBuffer d;
    private ShortBuffer e;
    private a f = new a();
    private final Point g = new Point();
    private final RectF h = new RectF(-1.0f, 1.0f, 1.0f, -1.0f);

    public void a(float f, float f2, float f3, float f4) {
        this.a.left = f;
        this.a.top = f2;
        this.a.right = f3;
        this.a.bottom = f4;
    }

    private void b() {
        a a = this.f.a(this.a, this.h, this.g);
        this.b = a.a;
        this.c = a.b;
        if (this.d != null) {
            this.d.clear();
        } else {
            this.d = ByteBuffer.allocateDirect(this.b.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        }
        if (this.e != null) {
            this.e.clear();
        } else {
            this.e = ByteBuffer.allocateDirect(this.c.length * 2).order(ByteOrder.nativeOrder()).asShortBuffer();
        }
        this.d.put(this.b).position(0);
        this.e.put(this.c).position(0);
    }

    protected void a(a aVar, int i, int i2, int i3, int i4) {
        if (!(this.g.x == i3 && this.g.y == i4)) {
            this.g.set(i3, i4);
            b();
        }
        GLES20.glEnableVertexAttribArray(aVar.b);
        c.a("glEnableVertexAttribArray");
        this.d.position(0);
        GLES20.glVertexAttribPointer(aVar.b, 3, 5126, false, 20, this.d);
        c.a("glVertexAttribPointer");
        GLES20.glEnableVertexAttribArray(aVar.d);
        c.a("glEnableVertexAttribArray");
        this.d.position(3);
        GLES20.glVertexAttribPointer(aVar.d, 3, 5126, false, 20, this.d);
        c.a("glVertexAttribPointer");
        GLES20.glViewport(i, i2, i3, i4);
        GLES20.glDrawElements(4, this.c.length, 5123, this.e);
        c.a("glDrawArrays");
    }
}
