package jp.co.cyberagent.android.gpuimage;

import android.opengl.GLES20;

public class i extends bu {
    private int j;
    private int k;
    private int l;
    private float m = 0.1f;
    private float n = 0.3f;
    private float[] o = new float[]{0.0f, 1.0f, 0.0f};

    public i() {
        super("varying highp vec2 textureCoordinate;\n varying highp vec2 textureCoordinate2;\n \n uniform sampler2D inputImageTexture;\n uniform sampler2D inputImageTexture2;\n \n highp float lum(lowp vec3 c) {\n     return dot(c, vec3(0.3, 0.59, 0.11));\n }\n \n lowp vec3 clipcolor(lowp vec3 c) {\n     highp float l = lum(c);\n     lowp float n = min(min(c.r, c.g), c.b);\n     lowp float x = max(max(c.r, c.g), c.b);\n     \n     if (n < 0.0) {\n         c.r = l + ((c.r - l) * l) / (l - n);\n         c.g = l + ((c.g - l) * l) / (l - n);\n         c.b = l + ((c.b - l) * l) / (l - n);\n     }\n     if (x > 1.0) {\n         c.r = l + ((c.r - l) * (1.0 - l)) / (x - l);\n         c.g = l + ((c.g - l) * (1.0 - l)) / (x - l);\n         c.b = l + ((c.b - l) * (1.0 - l)) / (x - l);\n     }\n     \n     return c;\n }\n\n lowp vec3 setlum(lowp vec3 c, highp float l) {\n     highp float d = l - lum(c);\n     c = c + vec3(d);\n     return clipcolor(c);\n }\n \n void main()\n {\n   highp vec4 baseColor = texture2D(inputImageTexture, textureCoordinate);\n   highp vec4 overlayColor = texture2D(inputImageTexture2, textureCoordinate2);\n\n     gl_FragColor = vec4(baseColor.rgb * (1.0 - overlayColor.a) + setlum(overlayColor.rgb, lum(baseColor.rgb)) * overlayColor.a, baseColor.a);\n }");
    }

    public void a() {
        super.a();
        this.j = GLES20.glGetUniformLocation(l(), "thresholdSensitivity");
        this.k = GLES20.glGetUniformLocation(l(), "smoothing");
        this.l = GLES20.glGetUniformLocation(l(), "colorToReplace");
    }

    public void c_() {
        super.c_();
        a(this.m);
        b(this.n);
        a(this.o[0], this.o[1], this.o[2]);
    }

    public void a(float f) {
        this.m = f;
        a(this.k, this.m);
    }

    public void b(float f) {
        this.n = f;
        a(this.j, this.n);
    }

    public void a(float f, float f2, float f3) {
        this.o = new float[]{f, f2, f3};
        a(this.l, this.o);
    }
}
