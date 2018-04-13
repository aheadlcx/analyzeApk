package jp.co.cyberagent.android.gpuimage;

import android.opengl.GLES20;

public class aa extends ab {
    private float[] g;
    private int h;
    private float[] i;
    private int j;

    public aa() {
        this(0.0f, 0.0f, 0.5f, 1.0f, 0.0f, 0.0f);
    }

    public aa(float f, float f2, float f3, float f4, float f5, float f6) {
        this(new float[]{f, f2, f3}, new float[]{f4, f5, f6});
    }

    public aa(float[] fArr, float[] fArr2) {
        super("attribute vec4 position;\nattribute vec4 inputTextureCoordinate;\n \nvarying vec2 textureCoordinate;\n \nvoid main()\n{\n    gl_Position = position;\n    textureCoordinate = inputTextureCoordinate.xy;\n}", "precision lowp float;\n\nvarying highp vec2 textureCoordinate;\n\nuniform sampler2D inputImageTexture;\nuniform float intensity;\nuniform vec3 firstColor;\nuniform vec3 secondColor;\n\nconst mediump vec3 luminanceWeighting = vec3(0.2125, 0.7154, 0.0721);\n\nvoid main()\n{\nlowp vec4 textureColor = texture2D(inputImageTexture, textureCoordinate);\nfloat luminance = dot(textureColor.rgb, luminanceWeighting);\n\ngl_FragColor = vec4( mix(firstColor.rgb, secondColor.rgb, luminance), textureColor.a);\n}\n");
        this.g = fArr;
        this.i = fArr2;
    }

    public void a() {
        super.a();
        this.h = GLES20.glGetUniformLocation(l(), "firstColor");
        this.j = GLES20.glGetUniformLocation(l(), "secondColor");
    }

    public void c_() {
        super.c_();
        a(this.g);
        b(this.i);
    }

    public void a(float[] fArr) {
        this.g = fArr;
        a(this.h, fArr);
    }

    public void b(float[] fArr) {
        this.i = fArr;
        a(this.j, fArr);
    }
}
