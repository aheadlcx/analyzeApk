package jp.co.cyberagent.android.gpuimage;

import android.opengl.GLES20;

public class p extends ab {
    private int g;
    private float h;

    public p() {
        this(1.2f);
    }

    public p(float f) {
        super("attribute vec4 position;\nattribute vec4 inputTextureCoordinate;\n \nvarying vec2 textureCoordinate;\n \nvoid main()\n{\n    gl_Position = position;\n    textureCoordinate = inputTextureCoordinate.xy;\n}", "varying highp vec2 textureCoordinate;\n \n uniform sampler2D inputImageTexture;\n uniform lowp float contrast;\n \n void main()\n {\n     lowp vec4 textureColor = texture2D(inputImageTexture, textureCoordinate);\n     \n     gl_FragColor = vec4(((textureColor.rgb - vec3(0.5)) * contrast + vec3(0.5)), textureColor.w);\n }");
        this.h = f;
    }

    public void a() {
        super.a();
        this.g = GLES20.glGetUniformLocation(l(), "contrast");
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
