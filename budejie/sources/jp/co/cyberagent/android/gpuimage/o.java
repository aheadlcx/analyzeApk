package jp.co.cyberagent.android.gpuimage;

import android.opengl.GLES20;

public class o extends ab {
    private float g;
    private float[] h;
    private int i;
    private int j;

    public o(float f, float[] fArr) {
        super("attribute vec4 position;\nattribute vec4 inputTextureCoordinate;\n \nvarying vec2 textureCoordinate;\n \nvoid main()\n{\n    gl_Position = position;\n    textureCoordinate = inputTextureCoordinate.xy;\n}", "varying highp vec2 textureCoordinate;\n\nuniform sampler2D inputImageTexture;\n\nuniform lowp mat4 colorMatrix;\nuniform lowp float intensity;\n\nvoid main()\n{\n    lowp vec4 textureColor = texture2D(inputImageTexture, textureCoordinate);\n    lowp vec4 outputColor = textureColor * colorMatrix;\n    \n    gl_FragColor = (intensity * outputColor) + ((1.0 - intensity) * textureColor);\n}");
        this.g = f;
        this.h = fArr;
    }

    public void a() {
        super.a();
        this.i = GLES20.glGetUniformLocation(l(), "colorMatrix");
        this.j = GLES20.glGetUniformLocation(l(), "intensity");
    }

    public void c_() {
        super.c_();
        a(this.g);
        a(this.h);
    }

    public void a(float f) {
        this.g = f;
        a(this.j, f);
    }

    public void a(float[] fArr) {
        this.h = fArr;
        c(this.i, fArr);
    }
}
