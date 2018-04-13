package jp.co.cyberagent.android.gpuimage;

import android.opengl.GLES20;

public class au extends ab {
    private int g;
    private float h;
    private int i;
    private float[] j;

    public au() {
        this(1.0f, new float[]{0.6f, 0.45f, 0.3f, 1.0f});
    }

    public au(float f, float[] fArr) {
        super("attribute vec4 position;\nattribute vec4 inputTextureCoordinate;\n \nvarying vec2 textureCoordinate;\n \nvoid main()\n{\n    gl_Position = position;\n    textureCoordinate = inputTextureCoordinate.xy;\n}", " precision lowp float;\n  \n  varying highp vec2 textureCoordinate;\n  \n  uniform sampler2D inputImageTexture;\n  uniform float intensity;\n  uniform vec3 filterColor;\n  \n  const mediump vec3 luminanceWeighting = vec3(0.2125, 0.7154, 0.0721);\n  \n  void main()\n  {\n \t//desat, then apply overlay blend\n \tlowp vec4 textureColor = texture2D(inputImageTexture, textureCoordinate);\n \tfloat luminance = dot(textureColor.rgb, luminanceWeighting);\n \t\n \tlowp vec4 desat = vec4(vec3(luminance), 1.0);\n \t\n \t//overlay\n \tlowp vec4 outputColor = vec4(\n                                  (desat.r < 0.5 ? (2.0 * desat.r * filterColor.r) : (1.0 - 2.0 * (1.0 - desat.r) * (1.0 - filterColor.r))),\n                                  (desat.g < 0.5 ? (2.0 * desat.g * filterColor.g) : (1.0 - 2.0 * (1.0 - desat.g) * (1.0 - filterColor.g))),\n                                  (desat.b < 0.5 ? (2.0 * desat.b * filterColor.b) : (1.0 - 2.0 * (1.0 - desat.b) * (1.0 - filterColor.b))),\n                                  1.0\n                                  );\n \t\n \t//which is better, or are they equal?\n \tgl_FragColor = vec4( mix(textureColor.rgb, outputColor.rgb, intensity), textureColor.a);\n  }");
        this.h = f;
        this.j = fArr;
    }

    public void a() {
        super.a();
        this.g = GLES20.glGetUniformLocation(l(), "intensity");
        this.i = GLES20.glGetUniformLocation(l(), "filterColor");
    }

    public void c_() {
        super.c_();
        a(1.0f);
        a(new float[]{0.6f, 0.45f, 0.3f, 1.0f});
    }

    public void a(float f) {
        this.h = f;
        a(this.g, this.h);
    }

    public void a(float[] fArr) {
        this.j = fArr;
        a(this.j[0], this.j[1], this.j[2]);
    }

    public void a(float f, float f2, float f3) {
        a(this.i, new float[]{f, f2, f3});
    }
}
