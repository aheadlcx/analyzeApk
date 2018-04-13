package jp.co.cyberagent.android.gpuimage;

import android.graphics.Bitmap;
import android.opengl.GLES20;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import jp.co.cyberagent.android.gpuimage.a.a;

public class bu extends ab {
    public int g;
    public int h;
    public int i;
    private ByteBuffer j;
    private Bitmap k;

    public bu(String str) {
        this("attribute vec4 position;\nattribute vec4 inputTextureCoordinate;\nattribute vec4 inputTextureCoordinate2;\n \nvarying vec2 textureCoordinate;\nvarying vec2 textureCoordinate2;\n \nvoid main()\n{\n    gl_Position = position;\n    textureCoordinate = inputTextureCoordinate.xy;\n    textureCoordinate2 = inputTextureCoordinate2.xy;\n}", str);
    }

    public bu(String str, String str2) {
        super(str, str2);
        this.i = -1;
        a(Rotation.NORMAL, false, false);
    }

    public void a() {
        super.a();
        this.g = GLES20.glGetAttribLocation(l(), "inputTextureCoordinate2");
        this.h = GLES20.glGetUniformLocation(l(), "inputImageTexture2");
        GLES20.glEnableVertexAttribArray(this.g);
        if (this.k != null && !this.k.isRecycled()) {
            a(this.k);
        }
    }

    public void a(final Bitmap bitmap) {
        if (bitmap == null || !bitmap.isRecycled()) {
            this.k = bitmap;
            if (this.k != null) {
                a(new Runnable(this) {
                    final /* synthetic */ bu b;

                    public void run() {
                        if (this.b.i == -1 && bitmap != null && !bitmap.isRecycled()) {
                            GLES20.glActiveTexture(33987);
                            this.b.i = ca.a(bitmap, -1, false);
                        }
                    }
                });
            }
        }
    }

    public void f() {
        super.f();
        GLES20.glDeleteTextures(1, new int[]{this.i}, 0);
        this.i = -1;
    }

    protected void g() {
        GLES20.glEnableVertexAttribArray(this.g);
        GLES20.glActiveTexture(33987);
        GLES20.glBindTexture(3553, this.i);
        GLES20.glUniform1i(this.h, 3);
        this.j.position(0);
        GLES20.glVertexAttribPointer(this.g, 2, 5126, false, 0, this.j);
    }

    public void a(Rotation rotation, boolean z, boolean z2) {
        float[] a = a.a(rotation, z, z2);
        ByteBuffer order = ByteBuffer.allocateDirect(32).order(ByteOrder.nativeOrder());
        FloatBuffer asFloatBuffer = order.asFloatBuffer();
        asFloatBuffer.put(a);
        asFloatBuffer.flip();
        this.j = order;
    }
}
