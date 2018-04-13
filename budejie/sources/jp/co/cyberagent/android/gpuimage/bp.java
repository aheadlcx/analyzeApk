package jp.co.cyberagent.android.gpuimage;

import android.graphics.PointF;
import android.opengl.GLES20;

public class bp extends ab {
    private PointF g;
    private int h;
    private float i;
    private int j;
    private float k;
    private int l;
    private float m;
    private int n;

    public bp() {
        this(new PointF(0.5f, 0.5f), 0.25f, 0.71f);
    }

    public bp(PointF pointF, float f, float f2) {
        super("attribute vec4 position;\nattribute vec4 inputTextureCoordinate;\n \nvarying vec2 textureCoordinate;\n \nvoid main()\n{\n    gl_Position = position;\n    textureCoordinate = inputTextureCoordinate.xy;\n}", "varying highp vec2 textureCoordinate;\n\nuniform sampler2D inputImageTexture;\n\nuniform highp vec2 center;\nuniform highp float radius;\nuniform highp float aspectRatio;\nuniform highp float refractiveIndex;\n\nvoid main()\n{\nhighp vec2 textureCoordinateToUse = vec2(textureCoordinate.x, (textureCoordinate.y * aspectRatio + 0.5 - 0.5 * aspectRatio));\nhighp float distanceFromCenter = distance(center, textureCoordinateToUse);\nlowp float checkForPresenceWithinSphere = step(distanceFromCenter, radius);\n\ndistanceFromCenter = distanceFromCenter / radius;\n\nhighp float normalizedDepth = radius * sqrt(1.0 - distanceFromCenter * distanceFromCenter);\nhighp vec3 sphereNormal = normalize(vec3(textureCoordinateToUse - center, normalizedDepth));\n\nhighp vec3 refractedVector = refract(vec3(0.0, 0.0, -1.0), sphereNormal, refractiveIndex);\n\ngl_FragColor = texture2D(inputImageTexture, (refractedVector.xy + 1.0) * 0.5) * checkForPresenceWithinSphere;     \n}\n");
        this.g = pointF;
        this.i = f;
        this.m = f2;
    }

    public void a() {
        super.a();
        this.h = GLES20.glGetUniformLocation(l(), "center");
        this.j = GLES20.glGetUniformLocation(l(), "radius");
        this.l = GLES20.glGetUniformLocation(l(), "aspectRatio");
        this.n = GLES20.glGetUniformLocation(l(), "refractiveIndex");
    }

    public void c_() {
        super.c_();
        b(this.i);
        a(this.g);
        a(this.m);
    }

    public void a(int i, int i2) {
        this.k = ((float) i2) / ((float) i);
        c(this.k);
        super.a(i, i2);
    }

    private void c(float f) {
        this.k = f;
        a(this.l, f);
    }

    public void a(float f) {
        this.m = f;
        a(this.n, f);
    }

    public void a(PointF pointF) {
        this.g = pointF;
        a(this.h, pointF);
    }

    public void b(float f) {
        this.i = f;
        a(this.j, f);
    }
}
