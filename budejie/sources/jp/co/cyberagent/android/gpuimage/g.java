package jp.co.cyberagent.android.gpuimage;

import android.graphics.PointF;
import android.opengl.GLES20;

public class g extends ab {
    private float g;
    private int h;
    private float i;
    private int j;
    private PointF k;
    private int l;
    private float m;
    private int n;

    public g() {
        this(0.25f, 0.5f, new PointF(0.5f, 0.5f));
    }

    public g(float f, float f2, PointF pointF) {
        super("attribute vec4 position;\nattribute vec4 inputTextureCoordinate;\n \nvarying vec2 textureCoordinate;\n \nvoid main()\n{\n    gl_Position = position;\n    textureCoordinate = inputTextureCoordinate.xy;\n}", "varying highp vec2 textureCoordinate;\n\nuniform sampler2D inputImageTexture;\n\nuniform highp float aspectRatio;\nuniform highp vec2 center;\nuniform highp float radius;\nuniform highp float scale;\n\nvoid main()\n{\nhighp vec2 textureCoordinateToUse = vec2(textureCoordinate.x, (textureCoordinate.y * aspectRatio + 0.5 - 0.5 * aspectRatio));\nhighp float dist = distance(center, textureCoordinateToUse);\ntextureCoordinateToUse = textureCoordinate;\n\nif (dist < radius)\n{\ntextureCoordinateToUse -= center;\nhighp float percent = 1.0 - ((radius - dist) / radius) * scale;\npercent = percent * percent;\n\ntextureCoordinateToUse = textureCoordinateToUse * percent;\ntextureCoordinateToUse += center;\n}\n\ngl_FragColor = texture2D(inputImageTexture, textureCoordinateToUse );    \n}\n");
        this.i = f;
        this.g = f2;
        this.k = pointF;
    }

    public void a() {
        super.a();
        this.h = GLES20.glGetUniformLocation(l(), "scale");
        this.j = GLES20.glGetUniformLocation(l(), "radius");
        this.l = GLES20.glGetUniformLocation(l(), "center");
        this.n = GLES20.glGetUniformLocation(l(), "aspectRatio");
    }

    public void c_() {
        super.c_();
        a(this.i);
        b(this.g);
        a(this.k);
    }

    public void a(int i, int i2) {
        this.m = ((float) i2) / ((float) i);
        c(this.m);
        super.a(i, i2);
    }

    private void c(float f) {
        this.m = f;
        a(this.n, f);
    }

    public void a(float f) {
        this.i = f;
        a(this.j, f);
    }

    public void b(float f) {
        this.g = f;
        a(this.h, f);
    }

    public void a(PointF pointF) {
        this.k = pointF;
        a(this.l, pointF);
    }
}
