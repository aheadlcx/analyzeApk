package jp.co.cyberagent.android.gpuimage;

import android.graphics.PointF;
import android.opengl.GLES20;

public class br extends ab {
    private float g;
    private int h;
    private float i;
    private int j;
    private PointF k;
    private int l;

    public br() {
        this(0.5f, 1.0f, new PointF(0.5f, 0.5f));
    }

    public br(float f, float f2, PointF pointF) {
        super("attribute vec4 position;\nattribute vec4 inputTextureCoordinate;\n \nvarying vec2 textureCoordinate;\n \nvoid main()\n{\n    gl_Position = position;\n    textureCoordinate = inputTextureCoordinate.xy;\n}", "varying highp vec2 textureCoordinate;\n\nuniform sampler2D inputImageTexture;\n\nuniform highp vec2 center;\nuniform highp float radius;\nuniform highp float angle;\n\nvoid main()\n{\nhighp vec2 textureCoordinateToUse = textureCoordinate;\nhighp float dist = distance(center, textureCoordinate);\nif (dist < radius)\n{\ntextureCoordinateToUse -= center;\nhighp float percent = (radius - dist) / radius;\nhighp float theta = percent * percent * angle * 8.0;\nhighp float s = sin(theta);\nhighp float c = cos(theta);\ntextureCoordinateToUse = vec2(dot(textureCoordinateToUse, vec2(c, -s)), dot(textureCoordinateToUse, vec2(s, c)));\ntextureCoordinateToUse += center;\n}\n\ngl_FragColor = texture2D(inputImageTexture, textureCoordinateToUse );\n\n}\n");
        this.i = f;
        this.g = f2;
        this.k = pointF;
    }

    public void a() {
        super.a();
        this.h = GLES20.glGetUniformLocation(l(), "angle");
        this.j = GLES20.glGetUniformLocation(l(), "radius");
        this.l = GLES20.glGetUniformLocation(l(), "center");
    }

    public void c_() {
        super.c_();
        a(this.i);
        b(this.g);
        a(this.k);
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
