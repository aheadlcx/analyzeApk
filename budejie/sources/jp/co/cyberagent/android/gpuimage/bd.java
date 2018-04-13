package jp.co.cyberagent.android.gpuimage;

import android.opengl.GLES20;

public class bd extends ab {
    private int g;
    private float h;
    private int i;
    private float j;
    private int k;
    private float l;
    private boolean m;

    public bd() {
        this(1.0f, 1.0f, 1.0f);
    }

    public bd(float f, float f2, float f3) {
        super("attribute vec4 position;\nattribute vec4 inputTextureCoordinate;\n \nvarying vec2 textureCoordinate;\n \nvoid main()\n{\n    gl_Position = position;\n    textureCoordinate = inputTextureCoordinate.xy;\n}", "  varying highp vec2 textureCoordinate;\n  \n  uniform sampler2D inputImageTexture;\n  uniform highp float red;\n  uniform highp float green;\n  uniform highp float blue;\n  \n  void main()\n  {\n      highp vec4 textureColor = texture2D(inputImageTexture, textureCoordinate);\n      \n      gl_FragColor = vec4(textureColor.r * red, textureColor.g * green, textureColor.b * blue, 1.0);\n  }\n");
        this.m = false;
        this.h = f;
        this.j = f2;
        this.l = f3;
    }

    public void a() {
        super.a();
        this.g = GLES20.glGetUniformLocation(l(), "red");
        this.i = GLES20.glGetUniformLocation(l(), "green");
        this.k = GLES20.glGetUniformLocation(l(), "blue");
        this.m = true;
        a(this.h);
        b(this.j);
        c(this.l);
    }

    public void a(float f) {
        this.h = f;
        if (this.m) {
            a(this.g, this.h);
        }
    }

    public void b(float f) {
        this.j = f;
        if (this.m) {
            a(this.i, this.j);
        }
    }

    public void c(float f) {
        this.l = f;
        if (this.m) {
            a(this.k, this.l);
        }
    }
}
