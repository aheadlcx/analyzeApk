package jp.co.cyberagent.android.gpuimage;

import android.opengl.GLES20;

public class ao extends ab {
    private static final String g = ao.class.getSimpleName();
    private int h;
    private float[] i;
    private int j;
    private float[] k;
    private int l;
    private float[] m;
    private int n;
    private float[] o;
    private int p;
    private float[] q;

    public ao() {
        this(new float[]{0.0f, 0.0f, 0.0f}, new float[]{1.0f, 1.0f, 1.0f}, new float[]{1.0f, 1.0f, 1.0f}, new float[]{0.0f, 0.0f, 0.0f}, new float[]{1.0f, 1.0f, 1.0f});
    }

    private ao(float[] fArr, float[] fArr2, float[] fArr3, float[] fArr4, float[] fArr5) {
        super("attribute vec4 position;\nattribute vec4 inputTextureCoordinate;\n \nvarying vec2 textureCoordinate;\n \nvoid main()\n{\n    gl_Position = position;\n    textureCoordinate = inputTextureCoordinate.xy;\n}", " varying highp vec2 textureCoordinate;\n \n uniform sampler2D inputImageTexture;\n uniform mediump vec3 levelMinimum;\n uniform mediump vec3 levelMiddle;\n uniform mediump vec3 levelMaximum;\n uniform mediump vec3 minOutput;\n uniform mediump vec3 maxOutput;\n \n void main()\n {\n     mediump vec4 textureColor = texture2D(inputImageTexture, textureCoordinate);\n     \n     gl_FragColor = vec4( mix(minOutput, maxOutput, pow(min(max(textureColor.rgb -levelMinimum, vec3(0.0)) / (levelMaximum - levelMinimum  ), vec3(1.0)), 1.0 /levelMiddle)) , textureColor.a);\n }\n");
        this.i = fArr;
        this.k = fArr2;
        this.m = fArr3;
        this.o = fArr4;
        this.q = fArr5;
        a(0.0f, 1.0f, 1.0f, 0.0f, 1.0f);
    }

    public void a() {
        super.a();
        this.h = GLES20.glGetUniformLocation(l(), "levelMinimum");
        this.j = GLES20.glGetUniformLocation(l(), "levelMiddle");
        this.l = GLES20.glGetUniformLocation(l(), "levelMaximum");
        this.n = GLES20.glGetUniformLocation(l(), "minOutput");
        this.p = GLES20.glGetUniformLocation(l(), "maxOutput");
    }

    public void c_() {
        super.c_();
        c();
    }

    public void c() {
        a(this.h, this.i);
        a(this.j, this.k);
        a(this.l, this.m);
        a(this.n, this.o);
        a(this.p, this.q);
    }

    public void a(float f, float f2, float f3, float f4, float f5) {
        b(f, f2, f3, f4, f5);
        c(f, f2, f3, f4, f5);
        d(f, f2, f3, f4, f5);
    }

    public void a(float f, float f2, float f3) {
        a(f, f2, f3, 0.0f, 1.0f);
    }

    public void b(float f, float f2, float f3, float f4, float f5) {
        this.i[0] = f;
        this.k[0] = f2;
        this.m[0] = f3;
        this.o[0] = f4;
        this.q[0] = f5;
        c();
    }

    public void c(float f, float f2, float f3, float f4, float f5) {
        this.i[1] = f;
        this.k[1] = f2;
        this.m[1] = f3;
        this.o[1] = f4;
        this.q[1] = f5;
        c();
    }

    public void d(float f, float f2, float f3, float f4, float f5) {
        this.i[2] = f;
        this.k[2] = f2;
        this.m[2] = f3;
        this.o[2] = f4;
        this.q[2] = f5;
        c();
    }
}
