package cn.xiaochuankeji.tieba.c;

import android.opengl.GLES20;
import java.util.IdentityHashMap;
import java.util.Map;

public class b {
    private final Map<String, a> a = new IdentityHashMap();

    protected static class a {
        public int a;
        public int b;
        public int c;
        public int d;
        public int e;

        protected a() {
        }
    }

    public void a(int i, float[] fArr, int i2, int i3, int i4, int i5) {
        a a = a("precision mediump float;\nvarying vec2 vTextureCoord;\nuniform sampler2D sTexture;\nvoid main() {\n    gl_FragColor = texture2D(sTexture, vTextureCoord);\n}\n", fArr);
        GLES20.glActiveTexture(33984);
        GLES20.glBindTexture(3553, i);
        a(a, i2, i3, i4, i5);
        GLES20.glBindTexture(3553, 0);
        GLES20.glUseProgram(0);
    }

    protected void a(a aVar, int i, int i2, int i3, int i4) {
        GLES20.glEnableVertexAttribArray(aVar.b);
        c.a("glEnableVertexAttribArray");
        GLES20.glVertexAttribPointer(aVar.b, 2, 5126, false, 0, c.c);
        c.a("glVertexAttribPointer");
        GLES20.glEnableVertexAttribArray(aVar.d);
        c.a("glEnableVertexAttribArray");
        GLES20.glVertexAttribPointer(aVar.d, 2, 5126, false, 0, c.d);
        c.a("glVertexAttribPointer");
        GLES20.glViewport(i, i2, i3, i4);
        GLES20.glDrawArrays(5, 0, 4);
        c.a("glDrawArrays");
    }

    private a a(String str, float[] fArr) {
        a aVar;
        if (this.a.containsKey(str)) {
            aVar = (a) this.a.get(str);
        } else {
            aVar = new a();
            aVar.a = c.a("uniform mat4 uTexMatrix;\nattribute vec4 aPosition;\nattribute vec4 aTextureCoord;\nvarying vec2 vTextureCoord;\nvoid main() {\n    gl_Position = aPosition;\n    vTextureCoord = (uTexMatrix * aTextureCoord).xy;\n}\n", str);
            aVar.b = GLES20.glGetAttribLocation(aVar.a, "aPosition");
            c.b(aVar.b, "aPosition");
            aVar.d = GLES20.glGetAttribLocation(aVar.a, "aTextureCoord");
            c.b(aVar.d, "aTextureCoord");
            aVar.c = GLES20.glGetUniformLocation(aVar.a, "uTexMatrix");
            c.b(aVar.c, "uTexMatrix");
            aVar.e = GLES20.glGetUniformLocation(aVar.a, "sTexture");
            c.b(aVar.e, "sTexture");
            this.a.put(str, aVar);
        }
        GLES20.glUseProgram(aVar.a);
        GLES20.glUniform1i(aVar.e, 0);
        c.a("glUniform1i");
        GLES20.glUniformMatrix4fv(aVar.c, 1, false, fArr, 0);
        c.a("glUniformMatrix4fv");
        return aVar;
    }

    public void a() {
        for (a aVar : this.a.values()) {
            GLES20.glDeleteProgram(aVar.a);
        }
        this.a.clear();
    }
}
