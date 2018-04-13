package jp.co.cyberagent.android.gpuimage;

import android.graphics.PointF;
import android.opengl.GLES20;

public class bx extends ab {
    private int g;
    private PointF h;
    private int i;
    private float[] j;
    private int k;
    private float l;
    private int m;
    private float n;

    public bx() {
        this(new PointF(), new float[]{0.0f, 0.0f, 0.0f}, 0.3f, 0.75f);
    }

    public bx(PointF pointF, float[] fArr, float f, float f2) {
        super("attribute vec4 position;\nattribute vec4 inputTextureCoordinate;\n \nvarying vec2 textureCoordinate;\n \nvoid main()\n{\n    gl_Position = position;\n    textureCoordinate = inputTextureCoordinate.xy;\n}", " uniform sampler2D inputImageTexture;\n varying highp vec2 textureCoordinate;\n \n uniform lowp vec2 vignetteCenter;\n uniform lowp vec3 vignetteColor;\n uniform highp float vignetteStart;\n uniform highp float vignetteEnd;\n \n void main()\n {\n     /*\n     lowp vec3 rgb = texture2D(inputImageTexture, textureCoordinate).rgb;\n     lowp float d = distance(textureCoordinate, vec2(0.5,0.5));\n     rgb *= (1.0 - smoothstep(vignetteStart, vignetteEnd, d));\n     gl_FragColor = vec4(vec3(rgb),1.0);\n      */\n     \n     lowp vec3 rgb = texture2D(inputImageTexture, textureCoordinate).rgb;\n     lowp float d = distance(textureCoordinate, vec2(vignetteCenter.x, vignetteCenter.y));\n     lowp float percent = smoothstep(vignetteStart, vignetteEnd, d);\n     gl_FragColor = vec4(mix(rgb.x, vignetteColor.x, percent), mix(rgb.y, vignetteColor.y, percent), mix(rgb.z, vignetteColor.z, percent), 1.0);\n }");
        this.h = pointF;
        this.j = fArr;
        this.l = f;
        this.n = f2;
    }

    public void a() {
        super.a();
        this.g = GLES20.glGetUniformLocation(l(), "vignetteCenter");
        this.i = GLES20.glGetUniformLocation(l(), "vignetteColor");
        this.k = GLES20.glGetUniformLocation(l(), "vignetteStart");
        this.m = GLES20.glGetUniformLocation(l(), "vignetteEnd");
        a(this.h);
        a(this.j);
        a(this.l);
        b(this.n);
    }

    public void a(PointF pointF) {
        this.h = pointF;
        a(this.g, this.h);
    }

    public void a(float[] fArr) {
        this.j = fArr;
        a(this.i, this.j);
    }

    public void a(float f) {
        this.l = f;
        a(this.k, this.l);
    }

    public void b(float f) {
        this.n = f;
        a(this.m, this.n);
    }
}
