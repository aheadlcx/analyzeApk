package jp.co.cyberagent.android.gpuimage;

import android.opengl.GLES20;

public class bb extends ab {
    private int g;
    private int h;

    public bb() {
        this(10);
    }

    public bb(int i) {
        super("attribute vec4 position;\nattribute vec4 inputTextureCoordinate;\n \nvarying vec2 textureCoordinate;\n \nvoid main()\n{\n    gl_Position = position;\n    textureCoordinate = inputTextureCoordinate.xy;\n}", "varying highp vec2 textureCoordinate;\n\nuniform sampler2D inputImageTexture;\nuniform highp float colorLevels;\n\nvoid main()\n{\n   highp vec4 textureColor = texture2D(inputImageTexture, textureCoordinate);\n   \n   gl_FragColor = floor((textureColor * colorLevels) + vec4(0.5)) / colorLevels;\n}");
        this.h = i;
    }

    public void a() {
        super.a();
        this.g = GLES20.glGetUniformLocation(l(), "colorLevels");
        a(this.h);
    }

    public void a(int i) {
        this.h = i;
        a(this.g, (float) i);
    }
}
