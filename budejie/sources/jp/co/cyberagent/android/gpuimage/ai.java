package jp.co.cyberagent.android.gpuimage;

import android.opengl.GLES20;

public class ai extends ab {
    private float g;
    private int h;
    private float i;
    private int j;

    public ai() {
        this(0.2f, 0.0f);
    }

    public ai(float f, float f2) {
        super("attribute vec4 position;\nattribute vec4 inputTextureCoordinate;\n \nvarying vec2 textureCoordinate;\n \nvoid main()\n{\n    gl_Position = position;\n    textureCoordinate = inputTextureCoordinate.xy;\n}", "varying highp vec2 textureCoordinate;\n\nuniform sampler2D inputImageTexture;\n\nuniform lowp float distance;\nuniform highp float slope;\n\nvoid main()\n{\n\t//todo reconsider precision modifiers\t \n\t highp vec4 color = vec4(1.0);//todo reimplement as a parameter\n\n\t highp float  d = textureCoordinate.y * slope  +  distance; \n\n\t highp vec4 c = texture2D(inputImageTexture, textureCoordinate) ; // consider using unpremultiply\n\n\t c = (c - d * color) / (1.0 -d);\n\n\t gl_FragColor = c; //consider using premultiply(c);\n}\n");
        this.g = f;
        this.i = f2;
    }

    public void a() {
        super.a();
        this.h = GLES20.glGetUniformLocation(l(), "distance");
        this.j = GLES20.glGetUniformLocation(l(), "slope");
    }

    public void c_() {
        super.c_();
        a(this.g);
        b(this.i);
    }

    public void a(float f) {
        this.g = f;
        a(this.h, f);
    }

    public void b(float f) {
        this.i = f;
        a(this.j, f);
    }
}
