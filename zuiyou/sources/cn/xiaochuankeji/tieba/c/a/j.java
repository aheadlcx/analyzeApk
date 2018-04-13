package cn.xiaochuankeji.tieba.c.a;

import android.opengl.GLES20;
import cn.xiaochuankeji.tieba.c.c;
import java.nio.FloatBuffer;

public class j extends a {
    protected int a;
    protected int b;
    protected int c;
    protected int d;
    protected int e;
    protected int f;
    protected int g;
    protected int h;
    private final String i;
    private final String j;

    public j() {
        this("uniform mat4 uMVPMatrix;\nuniform mat4 uTexMatrix;\nattribute vec4 aPosition;\nattribute vec4 aTextureCoord;\nvarying vec2 vTextureCoord;\nvoid main() {\n    gl_Position = uMVPMatrix * aPosition;\n    vTextureCoord = (uTexMatrix * aTextureCoord).xy;\n}\n", "precision mediump float;\nvarying vec2 vTextureCoord;\nuniform sampler2D sTexture;\nvoid main() {\n    gl_FragColor = texture2D(sTexture, vTextureCoord);\n}\n");
    }

    public j(String str) {
        this("uniform mat4 uMVPMatrix;\nuniform mat4 uTexMatrix;\nattribute vec4 aPosition;\nattribute vec4 aTextureCoord;\nvarying vec2 vTextureCoord;\nvoid main() {\n    gl_Position = uMVPMatrix * aPosition;\n    vTextureCoord = (uTexMatrix * aTextureCoord).xy;\n}\n", str);
    }

    public j(String str, String str2) {
        this.i = str;
        this.j = str2;
    }

    protected void b() {
        this.a = c.a(this.i, this.j);
        this.c = GLES20.glGetAttribLocation(this.a, "aPosition");
        c.b(this.c, "aPosition");
        this.e = GLES20.glGetAttribLocation(this.a, "aTextureCoord");
        c.b(this.e, "aTextureCoord");
        this.b = GLES20.glGetUniformLocation(this.a, "uMVPMatrix");
        c.b(this.b, "uMVPMatrix");
        this.d = GLES20.glGetUniformLocation(this.a, "uTexMatrix");
        c.b(this.d, "uTexMatrix");
        this.f = GLES20.glGetUniformLocation(this.a, "sTexture");
        c.b(this.f, "sTexture");
        int[] iArr = new int[1];
        GLES20.glGenFramebuffers(1, iArr, 0);
        this.g = iArr[0];
        GLES20.glBindFramebuffer(36160, this.g);
        c.a("Generate framebuffer");
        this.h = c.a(3553);
        GLES20.glFramebufferTexture2D(36160, 36064, 3553, this.h, 0);
        c.a("Attach texture to framebuffer");
        GLES20.glBindFramebuffer(36160, 0);
    }

    protected int f() {
        return this.a;
    }

    protected void b(int i, int i2) {
        super.b(i, i2);
        GLES20.glBindFramebuffer(36160, this.g);
        c.a("glBindFramebuffer");
        GLES20.glActiveTexture(33984);
        c.a(this.h, i, i2, 3553);
        int glCheckFramebufferStatus = GLES20.glCheckFramebufferStatus(36160);
        if (glCheckFramebufferStatus != 36053) {
            throw new IllegalStateException("Framebuffer not complete, status: " + glCheckFramebufferStatus);
        }
        GLES20.glBindFramebuffer(36160, 0);
    }

    protected void b(int i, float[] fArr, FloatBuffer floatBuffer, float[] fArr2, FloatBuffer floatBuffer2) {
        super.b(i, fArr, floatBuffer, fArr2, floatBuffer2);
        GLES20.glBindFramebuffer(36160, this.g);
        c.a("glBindFramebuffer");
    }

    protected int c(int i, float[] fArr, FloatBuffer floatBuffer, float[] fArr2, FloatBuffer floatBuffer2) {
        super.c(i, fArr, floatBuffer, fArr2, floatBuffer2);
        GLES20.glActiveTexture(33984);
        GLES20.glBindTexture(i(), i);
        GLES20.glUniform1i(this.f, 0);
        c.a("glUniform1i");
        GLES20.glUniformMatrix4fv(this.b, 1, false, fArr, 0);
        c.a("glUniformMatrix4fv");
        GLES20.glUniformMatrix4fv(this.d, 1, false, fArr2, 0);
        c.a("glUniformMatrix4fv");
        GLES20.glEnableVertexAttribArray(this.c);
        c.a("glEnableVertexAttribArray");
        GLES20.glVertexAttribPointer(this.c, 2, 5126, false, 0, floatBuffer);
        c.a("glVertexAttribPointer");
        GLES20.glEnableVertexAttribArray(this.e);
        c.a("glEnableVertexAttribArray");
        GLES20.glVertexAttribPointer(this.e, 2, 5126, false, 0, floatBuffer2);
        c.a("glVertexAttribPointer");
        GLES20.glDrawArrays(5, 0, 4);
        c.a("glDrawArrays");
        return this.h;
    }

    protected void d(int i, float[] fArr, FloatBuffer floatBuffer, float[] fArr2, FloatBuffer floatBuffer2) {
        super.d(i, fArr, floatBuffer, fArr2, floatBuffer2);
        GLES20.glDisableVertexAttribArray(this.c);
        GLES20.glDisableVertexAttribArray(this.e);
        GLES20.glActiveTexture(33984);
        GLES20.glBindTexture(i(), 0);
        GLES20.glBindFramebuffer(36160, 0);
        GLES20.glUseProgram(0);
    }

    protected void h() {
        GLES20.glDeleteProgram(this.a);
        GLES20.glDeleteTextures(1, new int[]{this.h}, 0);
        GLES20.glDeleteFramebuffers(1, new int[]{this.g}, 0);
    }

    protected int i() {
        return 3553;
    }
}
