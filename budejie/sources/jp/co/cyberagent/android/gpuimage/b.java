package jp.co.cyberagent.android.gpuimage;

import android.opengl.GLES20;

public class b extends ab {
    private int g;
    private int h;
    private boolean i;
    private float j;
    private float k;
    private float l;

    public b() {
        this("attribute vec4 position;\nattribute vec4 inputTextureCoordinate;\n \nvarying vec2 textureCoordinate;\n \nvoid main()\n{\n    gl_Position = position;\n    textureCoordinate = inputTextureCoordinate.xy;\n}");
    }

    public b(String str) {
        super("attribute vec4 position;\nattribute vec4 inputTextureCoordinate;\n\nuniform highp float texelWidth; \nuniform highp float texelHeight; \n\nvarying vec2 textureCoordinate;\nvarying vec2 leftTextureCoordinate;\nvarying vec2 rightTextureCoordinate;\n\nvarying vec2 topTextureCoordinate;\nvarying vec2 topLeftTextureCoordinate;\nvarying vec2 topRightTextureCoordinate;\n\nvarying vec2 bottomTextureCoordinate;\nvarying vec2 bottomLeftTextureCoordinate;\nvarying vec2 bottomRightTextureCoordinate;\n\nvoid main()\n{\n    gl_Position = position;\n\n    vec2 widthStep = vec2(texelWidth, 0.0);\n    vec2 heightStep = vec2(0.0, texelHeight);\n    vec2 widthHeightStep = vec2(texelWidth, texelHeight);\n    vec2 widthNegativeHeightStep = vec2(texelWidth, -texelHeight);\n\n    textureCoordinate = inputTextureCoordinate.xy;\n    leftTextureCoordinate = inputTextureCoordinate.xy - widthStep;\n    rightTextureCoordinate = inputTextureCoordinate.xy + widthStep;\n\n    topTextureCoordinate = inputTextureCoordinate.xy - heightStep;\n    topLeftTextureCoordinate = inputTextureCoordinate.xy - widthHeightStep;\n    topRightTextureCoordinate = inputTextureCoordinate.xy + widthNegativeHeightStep;\n\n    bottomTextureCoordinate = inputTextureCoordinate.xy + heightStep;\n    bottomLeftTextureCoordinate = inputTextureCoordinate.xy - widthNegativeHeightStep;\n    bottomRightTextureCoordinate = inputTextureCoordinate.xy + widthHeightStep;\n}", str);
        this.i = false;
        this.l = 1.0f;
    }

    public void a() {
        super.a();
        this.g = GLES20.glGetUniformLocation(l(), "texelWidth");
        this.h = GLES20.glGetUniformLocation(l(), "texelHeight");
        if (this.j != 0.0f) {
            c();
        }
    }

    public void a(int i, int i2) {
        super.a(i, i2);
        if (!this.i) {
            a(this.l);
        }
    }

    public void a(float f) {
        this.l = f;
        this.j = f / ((float) j());
        this.k = f / ((float) k());
        c();
    }

    private void c() {
        a(this.g, this.j);
        a(this.h, this.k);
    }
}
