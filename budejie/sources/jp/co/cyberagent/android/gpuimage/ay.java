package jp.co.cyberagent.android.gpuimage;

import android.opengl.GLES20;

public class ay extends ab {
    private int g;
    private float h;

    public ay() {
        this(1.0f);
    }

    public ay(float f) {
        super("attribute vec4 position;\nattribute vec4 inputTextureCoordinate;\n \nvarying vec2 textureCoordinate;\n \nvoid main()\n{\n    gl_Position = position;\n    textureCoordinate = inputTextureCoordinate.xy;\n}", "  varying highp vec2 textureCoordinate;\n  \n  uniform sampler2D inputImageTexture;\n  uniform lowp float opacity;\n  \n  void main()\n  {\n      lowp vec4 textureColor = texture2D(inputImageTexture, textureCoordinate);\n      \n      gl_FragColor = vec4(textureColor.rgb, textureColor.a * opacity);\n  }\n");
        this.h = f;
    }

    public void a() {
        super.a();
        this.g = GLES20.glGetUniformLocation(l(), "opacity");
    }

    public void c_() {
        super.c_();
        a(this.h);
    }

    public void a(float f) {
        this.h = f;
        a(this.g, this.h);
    }
}
