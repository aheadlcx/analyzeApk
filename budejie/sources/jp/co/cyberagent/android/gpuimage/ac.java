package jp.co.cyberagent.android.gpuimage;

import android.annotation.SuppressLint;
import android.opengl.GLES20;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import jp.co.cyberagent.android.gpuimage.a.a;

public class ac extends ab {
    protected List<ab> g;
    protected List<ab> h;
    private int[] i;
    private int[] j;
    private final FloatBuffer k;
    private final FloatBuffer l;
    private final FloatBuffer m;

    public ac() {
        this(null);
    }

    public ac(List<ab> list) {
        this.g = list;
        if (this.g == null) {
            this.g = new ArrayList();
        } else {
            o();
        }
        this.k = ByteBuffer.allocateDirect(be.a.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        this.k.put(be.a).position(0);
        this.l = ByteBuffer.allocateDirect(a.a.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        this.l.put(a.a).position(0);
        float[] a = a.a(Rotation.NORMAL, false, true);
        this.m = ByteBuffer.allocateDirect(a.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        this.m.put(a).position(0);
    }

    public void a(ab abVar) {
        if (abVar != null) {
            this.g.add(abVar);
            o();
        }
    }

    public void a() {
        super.a();
        for (ab d : this.g) {
            d.d();
        }
    }

    public void f() {
        c();
        for (ab e : this.g) {
            e.e();
        }
        super.f();
    }

    private void c() {
        if (this.j != null) {
            GLES20.glDeleteTextures(this.j.length, this.j, 0);
            this.j = null;
        }
        if (this.i != null) {
            GLES20.glDeleteFramebuffers(this.i.length, this.i, 0);
            this.i = null;
        }
    }

    public void a(int i, int i2) {
        super.a(i, i2);
        if (this.i != null) {
            c();
        }
        int size = this.g.size();
        for (int i3 = 0; i3 < size; i3++) {
            ((ab) this.g.get(i3)).a(i, i2);
        }
        if (this.h != null && this.h.size() > 0) {
            int size2 = this.h.size();
            this.i = new int[(size2 - 1)];
            this.j = new int[(size2 - 1)];
            for (int i4 = 0; i4 < size2 - 1; i4++) {
                GLES20.glGenFramebuffers(1, this.i, i4);
                GLES20.glGenTextures(1, this.j, i4);
                GLES20.glBindTexture(3553, this.j[i4]);
                GLES20.glTexImage2D(3553, 0, 6408, i, i2, 0, 6408, 5121, null);
                GLES20.glTexParameterf(3553, 10240, 9729.0f);
                GLES20.glTexParameterf(3553, 10241, 9729.0f);
                GLES20.glTexParameterf(3553, 10242, 33071.0f);
                GLES20.glTexParameterf(3553, 10243, 33071.0f);
                GLES20.glBindFramebuffer(36160, this.i[i4]);
                GLES20.glFramebufferTexture2D(36160, 36064, 3553, this.j[i4], 0);
                GLES20.glBindTexture(3553, 0);
                GLES20.glBindFramebuffer(36160, 0);
            }
        }
    }

    @SuppressLint({"WrongCall"})
    public void a(int i, FloatBuffer floatBuffer, FloatBuffer floatBuffer2) {
        h();
        if (i() && this.i != null && this.j != null && this.h != null) {
            int size = this.h.size();
            int i2 = 0;
            int i3 = i;
            while (i2 < size) {
                int i4;
                int i5;
                ab abVar = (ab) this.h.get(i2);
                if (i2 < size - 1) {
                    i4 = 1;
                } else {
                    i4 = 0;
                }
                if (i4 != 0) {
                    GLES20.glBindFramebuffer(36160, this.i[i2]);
                    GLES20.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
                }
                if (i2 == 0) {
                    abVar.a(i3, floatBuffer, floatBuffer2);
                } else if (i2 == size - 1) {
                    abVar.a(i3, this.k, size % 2 == 0 ? this.m : this.l);
                } else {
                    abVar.a(i3, this.k, this.l);
                }
                if (i4 != 0) {
                    GLES20.glBindFramebuffer(36160, 0);
                    i5 = this.j[i2];
                } else {
                    i5 = i3;
                }
                i2++;
                i3 = i5;
            }
        }
    }

    public List<ab> m() {
        return this.g;
    }

    public List<ab> n() {
        return this.h;
    }

    public void o() {
        if (this.g != null) {
            if (this.h == null) {
                this.h = new ArrayList();
            } else {
                this.h.clear();
            }
            for (ab abVar : this.g) {
                if (abVar instanceof ac) {
                    ((ac) abVar).o();
                    Collection n = ((ac) abVar).n();
                    if (!(n == null || n.isEmpty())) {
                        this.h.addAll(n);
                    }
                } else {
                    this.h.add(abVar);
                }
            }
        }
    }
}
