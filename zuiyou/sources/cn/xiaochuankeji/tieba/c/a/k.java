package cn.xiaochuankeji.tieba.c.a;

import android.annotation.TargetApi;
import android.opengl.GLES30;
import cn.xiaochuankeji.tieba.c.c;
import cn.xiaochuankeji.tieba.ui.videomaker.JNIHelper;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.util.LinkedList;

@TargetApi(18)
public class k extends i {
    private final j i = new j();
    private b j;
    private int k;
    private int[] l;
    private a[] m;
    private LinkedList<Integer> n = new LinkedList();
    private long o;
    private int p;

    public static class a {
        private final int a;
        private byte[] b;
        private long c;
        private int d = 0;
        private int e;

        public a(int i, int i2) {
            this.a = i;
            this.b = new byte[i2];
        }

        public byte[] a() {
            return this.b;
        }

        public long b() {
            return this.c;
        }

        private synchronized boolean e() {
            return this.d == 0;
        }

        private synchronized void a(long j) {
            this.d = 1;
            this.c = j;
        }

        private synchronized void a(ByteBuffer byteBuffer, int i) {
            this.d = 2;
            byteBuffer.get(this.b);
            this.e = i;
        }

        private synchronized boolean f() {
            return this.d == 2;
        }

        public synchronized boolean c() {
            boolean z;
            if (f()) {
                this.d = 3;
                z = true;
            } else {
                z = false;
            }
            return z;
        }

        public synchronized void d() {
            this.d = 0;
        }
    }

    public interface b {
        void a(a aVar);
    }

    public void a(b bVar) {
        this.j = bVar;
    }

    protected void b() {
        super.b();
        this.i.a();
        this.l = new int[4];
        GLES30.glGenBuffers(4, this.l, 0);
    }

    protected void b(int i, int i2) {
        super.b(i, i2);
        this.i.a(i, i2);
        this.k = (i * i2) * 4;
        int i3 = ((i * i2) * 3) / 2;
        this.m = new a[4];
        for (int i4 = 0; i4 < 4; i4++) {
            this.m[i4] = new a(i4, i3);
            GLES30.glBindBuffer(35051, this.l[i4]);
            GLES30.glBufferData(35051, this.k, null, 35045);
        }
        this.n.clear();
    }

    public void j() {
        for (int i = 0; i < 4; i++) {
            this.m[i].d();
        }
        this.n.clear();
        this.o = 0;
        this.p = 0;
    }

    public void a(long j) {
        this.o = j;
    }

    public void k() {
        while (!this.n.isEmpty()) {
            l();
        }
    }

    public int a(int i, float[] fArr, FloatBuffer floatBuffer, float[] fArr2, FloatBuffer floatBuffer2) {
        return super.a(this.i.a(i, fArr, floatBuffer, c.b, floatBuffer2), fArr, floatBuffer, fArr2, floatBuffer2);
    }

    protected void d(int i, float[] fArr, FloatBuffer floatBuffer, float[] fArr2, FloatBuffer floatBuffer2) {
        int i2;
        int i3 = -1;
        int i4 = 0;
        while (i4 < 4) {
            if (this.m[i4].e()) {
                break;
            }
            i4++;
        }
        i4 = -1;
        if (i4 < 0) {
            i2 = Integer.MAX_VALUE;
            int i5 = 0;
            while (i5 < 4) {
                if (this.m[i5].f() && this.m[i5].e < r1) {
                    i2 = this.m[i5].e;
                    i3 = i5;
                }
                i5++;
            }
            if (i3 < 0 || !this.m[i3].c()) {
                i3 = i4;
            } else {
                this.m[i3].d();
            }
            if (i3 < 0) {
                i2 = 0;
                while (i2 < 4) {
                    if (this.m[i2].c()) {
                        this.m[i2].d();
                        break;
                    }
                    i2++;
                }
            }
            i2 = i3;
        } else {
            i2 = i4;
        }
        if (i2 >= 0) {
            this.n.add(Integer.valueOf(i2));
            this.m[i2].a(this.o);
            GLES30.glBindBuffer(35051, this.l[i2]);
            JNIHelper.glReadPixels(0, 0, d(), e(), 6408, 5121);
        }
        GLES30.glBindBuffer(35051, 0);
        super.d(i, fArr, floatBuffer, fArr2, floatBuffer2);
    }

    public void l() {
        if (!this.n.isEmpty()) {
            int intValue = ((Integer) this.n.pop()).intValue();
            GLES30.glBindBuffer(35051, this.l[intValue]);
            ByteBuffer byteBuffer = (ByteBuffer) GLES30.glMapBufferRange(35051, 0, this.k, 1);
            GLES30.glUnmapBuffer(35051);
            a aVar = this.m[intValue];
            int i = this.p;
            this.p = i + 1;
            aVar.a(byteBuffer, i);
            if (this.j != null) {
                this.j.a(aVar);
            } else {
                aVar.d();
            }
            GLES30.glBindBuffer(35051, 0);
        }
    }

    protected void h() {
        super.h();
        this.i.g();
        GLES30.glDeleteBuffers(4, this.l, 0);
    }
}
