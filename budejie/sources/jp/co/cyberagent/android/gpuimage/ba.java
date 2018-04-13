package jp.co.cyberagent.android.gpuimage;

import android.opengl.GLES20;

public class ba extends ab {
    private int g;
    private int h;
    private float i = 1.0f;
    private int j;

    public ba() {
        super("attribute vec4 position;\nattribute vec4 inputTextureCoordinate;\n \nvarying vec2 textureCoordinate;\n \nvoid main()\n{\n    gl_Position = position;\n    textureCoordinate = inputTextureCoordinate.xy;\n}", "precision highp float;\nvarying vec2 textureCoordinate;\nuniform float imageWidthFactor;\nuniform float imageHeightFactor;\nuniform sampler2D inputImageTexture;\nuniform float pixel;\nvoid main()\n{\n  vec2 uv  = textureCoordinate.xy;\n  float dx = pixel * imageWidthFactor;\n  float dy = pixel * imageHeightFactor;\n  vec2 coord = vec2(dx * floor(uv.x / dx), dy * floor(uv.y / dy));\n  vec3 tc = texture2D(inputImageTexture, coord).xyz;\n  gl_FragColor = vec4(tc, 1.0);\n}");
    }

    public void a() {
        super.a();
        this.g = GLES20.glGetUniformLocation(l(), "imageWidthFactor");
        this.h = GLES20.glGetUniformLocation(l(), "imageHeightFactor");
        this.j = GLES20.glGetUniformLocation(l(), "pixel");
        a(this.i);
    }

    public void a(int i, int i2) {
        super.a(i, i2);
        a(this.g, 1.0f / ((float) i));
        a(this.h, 1.0f / ((float) i2));
    }

    public void a(float f) {
        this.i = f;
        a(this.j, this.i);
    }
}
